package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class Dashboard extends AppCompatActivity {

    ImageButton back;

    EditText fullName, email, phoneNo, whatsAppNo, experience, cases;

    Button add;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent objIntent = getIntent();

        String role = objIntent.getStringExtra("role");
        String userEmail = objIntent.getStringExtra("email");

        back = findViewById(R.id.back);

        fullName = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        phoneNo = findViewById(R.id.phone_number);
        whatsAppNo = findViewById(R.id.whatsApp_number);
        experience = findViewById(R.id.experience);
        cases = findViewById(R.id.cases);

        add = findViewById(R.id.add_lawyer);

        firebaseFirestore = FirebaseFirestore.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent = new Intent(getApplicationContext(), Home.class);

                objIntent.putExtra("role", role);
                objIntent.putExtra("email", userEmail);

                objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent);
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getEditTText(fullName);
                String mail = getEditTText(email);
                String number = getEditTText(phoneNo);
                String whatsApp = getEditTText(whatsAppNo);
                String exp = getEditTText(experience);
                String handle_cases = getEditTText(cases);

                Integer expLength = exp.length();
                Integer caseLength = handle_cases.length();

                String doc = name+"_"+number;

                if (!name.equals("") || !mail.equals("") || !number.equals("") || !whatsApp.equals("") || !exp.equals("") || !handle_cases.equals("")){
                    if (Patterns.EMAIL_ADDRESS.matcher(mail).matches() && Patterns.PHONE.matcher(number).matches() && Patterns.PHONE.matcher(whatsApp).matches()){
                        if ((expLength > 0 && expLength < 20) || (caseLength > 0 && caseLength < 20)) {
                            firebaseFirestore.collection("Lawyer")
                                    .document(doc)
                                    .set(new LawyerModel(name, mail, number, whatsApp, exp, handle_cases));

                            Toast.makeText(Dashboard.this, "Record added successfully!!!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else Toast.makeText(Dashboard.this, "Missing a field", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected String getEditTText(EditText edt){
        return edt.getText().toString();
    }
}