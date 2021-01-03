package com.stackmybiz.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    EditText edt_mailid,edt_pass;
    FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_mailid=(EditText)findViewById(R.id.edt_user_name) ;
        edt_pass=(EditText)findViewById(R.id.edt_password) ;
        FirebaseApp.initializeApp(this);
        firebaseauth=FirebaseAuth.getInstance();
    }
public void forget_password(View view){
        if(Utiilties.emailValidator(edt_mailid.getText().toString())){
            firebaseauth.sendPasswordResetEmail(edt_mailid.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("reset password", "Email sent.");
                                Toast.makeText(LoginActivity.this, "Reset Password is sent On:"+edt_mailid.getText().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(this, "Please enter valid Email Id", Toast.LENGTH_SHORT).show();
        }

}
    public void Login(View view) {
if(!Utiilties.emailValidator(edt_mailid.getText().toString())){
    Toast.makeText(this, "please enter mail id", Toast.LENGTH_SHORT).show();
}else if(edt_pass.getText().toString().equals("")){
    Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
}else if(!Utiilties.isOnline(LoginActivity.this)){
    Toast.makeText(this, "Please Go Online", Toast.LENGTH_SHORT).show();

}else{
    firebaseauth.signInWithEmailAndPassword(edt_mailid.getText().toString(),edt_pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull  Task<AuthResult> task) {
            if(task.isSuccessful()){
                /*UserData userData=CommonPref.getUserDetails(LoginActivity.this);
                if (userData.getName()==null) userData.setName("");
                userData.setEmail(edt_mailid.getText().toString().trim());
                if (userData.getMobile()==null)userData.setMobile("");
                CommonPref.setUserDetails(LoginActivity.this,userData);*/
                 Toast.makeText(LoginActivity.this, "Login suceesfully", Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
                 finish();
            }else{
                Toast.makeText(LoginActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

            }

        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull  Exception e) {
            Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    });

}

    }

    public void Register(View view) {
        Intent i=new Intent(LoginActivity.this,RegistrationActivity.class);
        startActivity(i);
    }


}