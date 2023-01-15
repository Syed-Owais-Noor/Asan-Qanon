package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String role, email;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    Button basicLaw, corporateLaw, criminalLaw, cyberLaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent objIntent = getIntent();

        role = objIntent.getStringExtra("role");
        email = objIntent.getStringExtra("email");

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        Menu menu = navigationView.getMenu();

        if (role.equals("user")){
            menu.findItem(R.id.dashboard).setVisible(false);
            menu.findItem(R.id.manage_lawyer).setVisible(false);
            menu.findItem(R.id.reply_query).setVisible(false);
        }else{
            menu.findItem(R.id.contact_lawyer).setVisible(false);
            menu.findItem(R.id.ask_query).setVisible(false);
            menu.findItem(R.id.query_reply).setVisible(false);
        }

        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.home);

        basicLaw = findViewById(R.id.basic);
        corporateLaw = findViewById(R.id.corporate);
        criminalLaw = findViewById(R.id.criminal);
        cyberLaw = findViewById(R.id.cyber);

        basicLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent = new Intent(getApplicationContext(), Basic_law.class);

                objIntent.putExtra("role", role);
                objIntent.putExtra("email", email);

                objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent);
                finish();
            }
        });

        corporateLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent = new Intent(getApplicationContext(), CorporateLaw.class);

                objIntent.putExtra("role", role);
                objIntent.putExtra("email", email);

                objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent);
                finish();
            }
        });

        criminalLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent = new Intent(getApplicationContext(), CriminalLaw.class);

                objIntent.putExtra("role", role);
                objIntent.putExtra("email", email);

                objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent);
                finish();
            }
        });

        cyberLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent = new Intent(getApplicationContext(), CyberLaw.class);

                objIntent.putExtra("role", role);
                objIntent.putExtra("email", email);

                objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                break;
            case R.id.contact_lawyer:
                Intent objIntent0 = new Intent(getApplicationContext(), contactLawyer.class);

                objIntent0.putExtra("role", role);
                objIntent0.putExtra("email", email);

                objIntent0.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent0);
                finish();
                break;
            case R.id.ask_query:
                Intent objIntent1 = new Intent(getApplicationContext(), AskQuery.class);

                objIntent1.putExtra("role", role);
                objIntent1.putExtra("email", email);

                objIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent1);
                finish();
                break;
            case R.id.query_reply:
                Intent objIntent2 = new Intent(getApplicationContext(), Reply.class);

                objIntent2.putExtra("role", role);
                objIntent2.putExtra("email", email);

                objIntent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent2);
                finish();
                break;
            case R.id.dashboard:
                Intent objIntent3 = new Intent(getApplicationContext(), Dashboard.class);

                objIntent3.putExtra("role", role);
                objIntent3.putExtra("email", email);

                objIntent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent3);
                finish();
                break;
            case R.id.manage_lawyer:
                Intent objIntent4 = new Intent(getApplicationContext(), ManageLawyer.class);

                objIntent4.putExtra("role", role);
                objIntent4.putExtra("email", email);

                objIntent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent4);
                finish();
                break;
            case R.id.reply_query:
                Intent objIntent5 = new Intent(getApplicationContext(), ReplyQuery.class);

                objIntent5.putExtra("role", role);
                objIntent5.putExtra("email", email);

                objIntent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent5);
                finish();
                break;
            case R.id.logout:
                Intent objIntent6 = new Intent(getApplicationContext(), Login.class);
                objIntent6.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(objIntent6);
                finish();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}