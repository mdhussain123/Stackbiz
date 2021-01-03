package com.stackmybiz.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    TextView text_name,text_mobile,text_email;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        text_name=findViewById(R.id.text_name);
        text_mobile=findViewById(R.id.text_mobile);
        text_email=findViewById(R.id.text_email);
        UserData userData=CommonPref.getUserDetails(ProfileActivity.this);
        text_name.setText(userData.getName());
        text_mobile.setText(userData.getMobile());
        text_email.setText(userData.getEmail());
        FirebaseApp.initializeApp(ProfileActivity.this);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void Logout(View view) {
        firebaseAuth.signOut();
        startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
        finish();
    }
}