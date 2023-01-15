package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class AskQuery extends AppCompatActivity {

    String role, email;

    ImageButton back;

    EditText query;

    Button submit;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_query);

        Intent objIntent = getIntent();

        role = objIntent.getStringExtra("role");
        email = objIntent.getStringExtra("email");

        back = findViewById(R.id.back);
        query = findViewById(R.id.query);

        submit = findViewById(R.id.submit);

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qry = getEditTText(query);

                if (!qry.equals("")){
                    firebaseFirestore.collection("Query")
                            .document()
                            .set(new QueryModel(email, qry));

                    Toast.makeText(AskQuery.this, "Submitted successfully!!!", Toast.LENGTH_LONG).show();

                    Intent objIntent = new Intent(getApplicationContext(), Home.class);

                    objIntent.putExtra("role", role);
                    objIntent.putExtra("email", email);

                    objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(objIntent);
                    finish();
                }
                else Toast.makeText(AskQuery.this, "You forget to write your query", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected String getEditTText(EditText edt){
        return edt.getText().toString();
    }
}