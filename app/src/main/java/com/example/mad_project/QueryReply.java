package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class QueryReply extends AppCompatActivity {

    String role, email, userEmail, query;

    ImageButton back;

    EditText reply;

    Button submit;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_reply);

        Intent objIntent = getIntent();

        role = objIntent.getStringExtra("role");
        email = objIntent.getStringExtra("email");
        userEmail = objIntent.getStringExtra("userEmail");
        query = objIntent.getStringExtra("query");

        back = findViewById(R.id.back);
        reply = findViewById(R.id.reply);

        submit = findViewById(R.id.submit);

        firebaseFirestore = FirebaseFirestore.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String queryReply = getEditTText(reply);

                if (!queryReply.equals("")){
                    firebaseFirestore.collection("Reply")
                            .document()
                            .set(new ReplyModel(email, userEmail, query, queryReply));

                    Toast.makeText(QueryReply.this, "Replyed successfully!!!", Toast.LENGTH_LONG).show();

                    Intent objIntent = new Intent(getApplicationContext(), ReplyQuery.class);

                    objIntent.putExtra("role", role);
                    objIntent.putExtra("email", email);

                    objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(objIntent);
                    finish();
                }
                else Toast.makeText(QueryReply.this, "Missing a field", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected String getEditTText(EditText edt){
        return edt.getText().toString();
    }
}