package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sigin extends AppCompatActivity {

    EditText name,email,password;
    Button button;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        name=findViewById(R.id.x_name);
        email=findViewById(R.id.et_email_signin);
        password=findViewById(R.id.et_password_sigin);
        progressBar=findViewById(R.id.x_progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }

    public void changeclicked(View view) {
        String temail=email.getText().toString().trim();
        String tpass=password.getText().toString().trim();
        if(temail.isEmpty())
        {
            email.setError("Email is required");
            return;
        }
        if(tpass.isEmpty())
        {
            password.setError("Password is required");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //Authenticate

        firebaseAuth.createUserWithEmailAndPassword(temail,tpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Sigin.this,"User Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Sigin.this,MainActivity.class));
                }
                else
                {
                    Toast.makeText(Sigin.this,"Error !"+task.getException(),Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        });



    }
}