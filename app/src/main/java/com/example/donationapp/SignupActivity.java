package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donationapp.enhancers.CustomUntouchableLoading;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    EditText etname,etemail,etpassword,etconpass;
    Button btnsubmit;
    FirebaseAuth firebaseAuth;
    Toolbar toolbar;
    CustomUntouchableLoading anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        toolbar = findViewById(R.id.toolbar_signup_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        anim = new CustomUntouchableLoading(this);
        //intilizing UI variables..
        etname=findViewById(R.id.etusername_signup_activity);
        etemail=findViewById(R.id.etemail_signup_activity);
        etpassword=findViewById(R.id.etpass_signup_activity);
        etconpass = findViewById(R.id.etconpass_signup_activity);

        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void changeclicked(View view) {
        anim.startanimation();
        String email=etemail.getText().toString().trim();
        String pass=etpassword.getText().toString().trim();
        if(email.isEmpty())
        {
            etemail.setError("Email is required");
            return;
        }
        if(pass.isEmpty())
        {
            etpassword.setError("Password is required");
            return;
        }

        //Authenticate

        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                anim.stopanimation();
                if(task.isSuccessful())
                {
                    Toast.makeText(SignupActivity.this,"User Created Login to continue..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(SignupActivity.this,"Error !"+task.getException(),Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}