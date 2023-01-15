package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class UpdateLawyer extends AppCompatActivity {

    ImageButton back;

    EditText fullName, email, phone, whatsAppNo, experience, cases;

    Button update;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lawyer);

        Intent objIntent = getIntent();

        String role = objIntent.getStringExtra("role");
        String userEmail = objIntent.getStringExtra("email");
        String phoneNo = objIntent.getStringExtra("phoneNo");

        back = findViewById(R.id.back);

        fullName = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone_number);
        whatsAppNo = findViewById(R.id.whatsApp_number);
        experience = findViewById(R.id.experience);
        cases = findViewById(R.id.cases);

        update = findViewById(R.id.update_lawyer);

        firebaseFirestore = FirebaseFirestore.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent = new Intent(getApplicationContext(), Action.class);

                objIntent.putExtra("role", role);
                objIntent.putExtra("email", userEmail);
                objIntent.putExtra("phoneNo", phoneNo);

                objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent);
                finish();
            }
        });

        firebaseFirestore.collection("Lawyer")
            .whereEqualTo("phoneNo", phoneNo.toString())
            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for(DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()){
                        fullName.setText(snapshot.getString("name"));
                        email.setText(snapshot.getString("email"));
                        phone.setText(snapshot.getString("phoneNo"));
                        whatsAppNo.setText(snapshot.getString("whatsAppNo"));
                        experience.setText(snapshot.getString("experience"));
                        cases.setText(snapshot.getString("cases"));
                    }
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UpdateLawyer.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getEditTText(fullName);
                String mail = getEditTText(email);
                String phoneNum = getEditTText(phone);
                String whatsApp = getEditTText(whatsAppNo);
                String exp = getEditTText(experience);
                String handleCases = getEditTText(cases);

                Map<String, Object> userDetail = new HashMap<>();
                userDetail.put("name", name);
                userDetail.put("email", mail);
                userDetail.put("phoneNo", phoneNum);
                userDetail.put("whatsAppNo", whatsApp);
                userDetail.put("experience", exp);
                userDetail.put("cases", handleCases);

                firebaseFirestore.collection("Lawyer")
                    .whereEqualTo("phoneNo", phoneNo.toString())
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && !task.getResult().isEmpty()){
                                DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);

                                String docID = documentSnapshot.getId();

                                firebaseFirestore.collection("Lawyer")
                                    .document(docID)
                                    .update(userDetail)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(UpdateLawyer.this, "Updated Successfully!!!", Toast.LENGTH_LONG).show();

                                            Intent objIntent = new Intent(getApplicationContext(), ManageLawyer.class);

                                            objIntent.putExtra("role", role);
                                            objIntent.putExtra("email", userEmail);

                                            objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(objIntent);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(UpdateLawyer.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                            }
                        }
                    });
            }
        });
    }

    protected String getEditTText(EditText edt){
        return edt.getText().toString();
    }
}