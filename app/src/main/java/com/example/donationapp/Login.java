package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email,password;
    TextView newuser;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initalize();
    }

    private void initalize() {
        email=findViewById(R.id.login_name);
        password=findViewById(R.id.login_pass);
        newuser=findViewById(R.id.newuser);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void login(View view) {
        String temail=email.getText().toString().trim();
        String tpass=password.getText().toString().trim();
        if(TextUtils.isEmpty(temail))
        {
            email.setError("Email is required");
            return;
        }
        if(TextUtils.isEmpty(tpass))
        {
            password.setError("Password is required");
            return;
        }
        //Authentication
        firebaseAuth.signInWithEmailAndPassword(temail,tpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    Toast.makeText(Login.this,"User Sigin",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else
                {
                    Toast.makeText(Login.this,"Error !"+task.getException(),Toast.LENGTH_LONG).show();


                }
            }
        });
    }

    public void newUser(View view) {
        startActivity(new Intent(getApplicationContext(),Sigin.class));
    }
}