package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ReplyQuery extends AppCompatActivity {

    String role, email;

    LinearLayout addCard;

    ImageButton back;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_query);

        Intent objIntent = getIntent();

        role = objIntent.getStringExtra("role");
        email = objIntent.getStringExtra("email");

        addCard = findViewById(R.id.add_card);

        back = findViewById(R.id.back);

        firebaseFirestore = FirebaseFirestore.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent = new Intent(getApplicationContext(), Home.class);

                objIntent.putExtra("role", role);
                objIntent.putExtra("email", email);

                objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent);
                finish();
            }
        });

        firebaseFirestore.collection("Query")
            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for(DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()){
                        addView(snapshot.getString("email"), snapshot.getString("query"));
                    }
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ReplyQuery.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
    }

    protected void addView(String userEmail, String query){
        View view = getLayoutInflater().inflate(R.layout.reply_query_card, null, false);

        TextView userDetail1 = view.findViewById(R.id.user_detail1);
        TextView userDetail2 = view.findViewById(R.id.user_detail2);

        String info1 = "Email: "+userEmail;

        userDetail1.setText(info1);
        userDetail2.setText(query);

        userDetail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent = new Intent(getApplicationContext(), DeleteQuery.class);

                objIntent.putExtra("role", role);
                objIntent.putExtra("email", email);
                objIntent.putExtra("userEmail", userEmail);
                objIntent.putExtra("query", query);

                objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent);
                finish();
            }
        });

        userDetail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent = new Intent(getApplicationContext(), DeleteQuery.class);

                objIntent.putExtra("role", role);
                objIntent.putExtra("email", email);
                objIntent.putExtra("userEmail", userEmail);
                objIntent.putExtra("query", query);

                objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent);
                finish();
            }
        });

        addCard.addView(view);
    }
}