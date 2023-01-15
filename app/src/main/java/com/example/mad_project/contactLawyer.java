package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class contactLawyer extends AppCompatActivity {

    ImageButton back;

    LinearLayout addCard;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_lawyer);

        Intent objIntent = getIntent();

        String role = objIntent.getStringExtra("role");
        String email = objIntent.getStringExtra("email");

        back = findViewById(R.id.back);

        addCard = findViewById(R.id.add_card);

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

        firebaseFirestore.collection("Lawyer")
            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for(DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()){
                        addView(snapshot.getString("name"), snapshot.getString("experience"), snapshot.getString("cases"), snapshot.getString("email"), snapshot.getString("phoneNo"), snapshot.getString("whatsAppNo"));
                    }
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(contactLawyer.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
    }

    protected void addView(String name, String exp, String cases, String email, String phoneNo, String whatsAppNo){
        View view = getLayoutInflater().inflate(R.layout.contact_lawyer_card, null, false);

        TextView userDetail1 = view.findViewById(R.id.user_detail1);
        TextView userDetail2 = view.findViewById(R.id.user_detail2);

        String info1 = "Name: "+name+"\nExperience: "+exp+"\nCases: "+cases;
        String info2 = "Email: "+email+"\nPhone Number: "+phoneNo+"\nWhatsApp: "+whatsAppNo;

        userDetail1.setText(info1);
        userDetail2.setText(info2);

        addCard.addView(view);
    }
}