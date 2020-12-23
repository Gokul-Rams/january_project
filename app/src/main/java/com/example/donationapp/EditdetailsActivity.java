package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.donationapp.enhancers.CustomUntouchableLoading;
import com.example.donationapp.entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditdetailsActivity extends AppCompatActivity {

    Boolean adddetailflag;
    Toolbar toolbar;
    EditText etname,etphno;
    RadioButton rbdonor,rbacceptor;
    RadioGroup rbgrp;
    FirebaseFirestore db;
    CardView loadinganim;
    CustomUntouchableLoading anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdetails);

        toolbar = findViewById(R.id.toolbar_editdetail_activity);
        setSupportActionBar(toolbar);

        adddetailflag = getIntent().getBooleanExtra("addflag",false);

        db = FirebaseFirestore.getInstance();
        anim = new CustomUntouchableLoading(this);

        rbdonor = findViewById(R.id.rbfooddonor_editdetails_activity);
        rbacceptor = findViewById(R.id.rbacceptor_editdetails_activity);
        rbgrp = findViewById(R.id.rbgrp_editdetails_activity);
        etname = findViewById(R.id.etusername_editdetails_activity);
        etphno = findViewById(R.id.etphno_editdetails_activity);

        loadinganim = findViewById(R.id.onscreenloading_editdetails_activity);

        //new user
        if(adddetailflag){
            getSupportActionBar().setTitle("Add Details To Proceed..");
        }
        //old user
        else{
            getSupportActionBar().setTitle("Edit User Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void donebtnclicked(View view){
        String name,phno,email,uid;
        name = etname.getText().toString();
        phno = etphno.getText().toString();
        email = getIntent().getStringExtra("email");
        uid = getIntent().getStringExtra("uid");

        if(name.isEmpty()){
            Toast.makeText(this, "Enter the name to proceed..", Toast.LENGTH_SHORT).show();
            return;
        }
        if(phno.isEmpty()){
            Toast.makeText(this, "Enter phno to proceed", Toast.LENGTH_SHORT).show();
            return;
        }

        if(rbgrp.getCheckedRadioButtonId() == R.id.rbfooddonor_editdetails_activity) {
            anim.startanimation();
            User temp = new User(getIntent().getStringExtra("uid"), name, phno, getIntent().getStringExtra("email"),true);
            db.collection("userdetails").document(getIntent().getStringExtra("uid")).set(temp).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    anim.stopanimation();
                    if (task.isSuccessful()) {
                        Toast.makeText(EditdetailsActivity.this, "Your details Sucessfull Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditdetailsActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(EditdetailsActivity.this, "Internet trouble check your internet..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            Intent intent = new Intent(this,EditAcceptorDetailsActivity.class);
            intent.putExtra("addflag", adddetailflag);
            intent.putExtra("uid",uid);
            intent.putExtra("email", email);
            intent.putExtra("name",name);
            intent.putExtra("phno",phno);
            intent.putExtra("isdonor",false);
            startActivity(intent);
        }
    }
}