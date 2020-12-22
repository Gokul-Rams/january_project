package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donationapp.enhancers.CustomUntouchableLoading;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText etemail,etpassword;
    FirebaseAuth firebaseAuth;
    Toolbar toolbar;
    CustomUntouchableLoading anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar_login_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");

        anim = new CustomUntouchableLoading(this);
        etemail=findViewById(R.id.etname_login_activity);
        etpassword=findViewById(R.id.etpass_login_activity);

        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void login(View view) {
        anim.startanimation();
        String temail=etemail.getText().toString().trim();
        String tpass=etpassword.getText().toString().trim();
        if(TextUtils.isEmpty(temail))
        {
            etemail.setError("Email is required");
            return;
        }
        if(TextUtils.isEmpty(tpass))
        {
            etpassword.setError("Password is required");
            return;
        }
        //Authentication
        firebaseAuth.signInWithEmailAndPassword(temail,tpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                anim.stopanimation();
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this,"Login Sucessfull..",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login Failed Check your Internet Connection.."+task.getException(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
     public void newuserbtnclicked(View view){
         startActivity(new Intent(LoginActivity.this,SignupActivity.class));
     }
}