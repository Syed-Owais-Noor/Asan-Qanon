package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

public class Action extends AppCompatActivity {

    ImageButton back;

    Button update, delete;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        Intent objIntent = getIntent();

        String role = objIntent.getStringExtra("role");
        String email = objIntent.getStringExtra("email");
        String phoneNo = objIntent.getStringExtra("phoneNo");

        back = findViewById(R.id.back);

        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent = new Intent(getApplicationContext(), ManageLawyer.class);

                objIntent.putExtra("role", role);
                objIntent.putExtra("email", email);

                objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent);
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent = new Intent(getApplicationContext(), UpdateLawyer.class);

                objIntent.putExtra("role", role);
                objIntent.putExtra("email", email);
                objIntent.putExtra("phoneNo", phoneNo);

                objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore = FirebaseFirestore.getInstance();

                firebaseFirestore.collection("Lawyer")
                    .whereEqualTo("phoneNo", phoneNo.toString())
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            WriteBatch wb = firebaseFirestore.batch();

                            for(DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()){
                                wb.delete(snapshot.getReference());
                            }
                            wb.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Action.this, "Lawyer deleted!!!", Toast.LENGTH_LONG).show();

                                    Intent objIntent = new Intent(getApplicationContext(), ManageLawyer.class);

                                    objIntent.putExtra("role", role);
                                    objIntent.putExtra("email", email);

                                    objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(objIntent);
                                    finish();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Action.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
            }
        });
    }
}