package com.stackmybiz.demo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class ResetpassActivity extends AppCompatActivity {

    EditText user_email,user_pass,confrm_password;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        user_email=findViewById(R.id.user_email);
        user_pass=findViewById(R.id.user_pass);
        confrm_password=findViewById(R.id.confm_password);
      //  FirebaseAuth.initializeApp(RegistrationActivity.this);

        FirebaseApp.initializeApp(this);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void Reset(View view) {
        if (validate()){
            ProgressDialog progressDialog;
            progressDialog= new ProgressDialog(ResetpassActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
           // new ResiterLoader(RegistrationActivity.this).execute();
            firebaseAuth.createUserWithEmailAndPassword(user_email.getText().toString(),user_pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (progressDialog.isShowing())progressDialog.dismiss();
                    if(task.isSuccessful()){
                        UserData userData=new UserData();
                        userData.setEmail(user_email.getText().toString());
                      //  userData.setMobile(user_mobile.getText().toString());
                        CommonPref.setUserDetails(ResetpassActivity.this,userData);
                        if (userData.getMobile()==null)userData.setMobile("");
                        Toast.makeText(ResetpassActivity.this, "Resitration Success !", Toast.LENGTH_SHORT).show();
                        Log.d("result string",task.toString());
                        finish();
                    }
                    else{
                        Toast.makeText(ResetpassActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("result string",task.getException().getMessage().toString());
                    }

                }

            });
        }
    }

    public void takeImage(View view) {
    }

    private boolean validate(){
        boolean isValidateed=false;
        if (!Utiilties.emailValidator(user_email.getText().toString())){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            isValidateed=false;
        }
        else  if (user_pass.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            isValidateed=false;
        }
        else  if (confrm_password.getText().toString().equals("") && !(confrm_password.getText().toString().equals(user_pass.getText().toString()))){
            Toast.makeText(this, "Enter same Password", Toast.LENGTH_SHORT).show();
            isValidateed=false;
        }else if(!Utiilties.isOnline(ResetpassActivity.this)){
            isValidateed=false;
            Toast.makeText(this, "Please go Online|", Toast.LENGTH_SHORT).show();
        }
        else{

            isValidateed=true;
        }
        return isValidateed;
    }
  /*  private class ResiterLoader extends AsyncTask<String,Void,Task<AuthResult>>{


        Activity activity;
        Task<AuthResult> task2;
         ResiterLoader(Activity activity){
             this.activity=activity;

             progressDialog=new ProgressDialog(activity);
         }
         @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected Task<AuthResult> doInBackground(String... strings) {

            return task2;
        }
        @Override
        protected void onPostExecute(Task<AuthResult> task) {
            super.onPostExecute(task);
           if (progressDialog.isShowing())progressDialog.dismiss();
            if(task.isSuccessful()){
                Toast.makeText(activity, "Resitration Success !", Toast.LENGTH_SHORT).show();
                Log.d("result string",task.toString());
                finish();
            }
            else{
                Toast.makeText(activity, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }*/
}