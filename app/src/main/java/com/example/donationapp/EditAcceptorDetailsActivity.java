package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.donationapp.enhancers.CustomUntouchableLoading;
import com.example.donationapp.entities.Acceptor;
import com.example.donationapp.entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class EditAcceptorDetailsActivity extends AppCompatActivity {

    Boolean adddetailsflag;
    EditText etminqty;
    EditText etmaxqty;
    TextInputLayout tides;
    EditText etconpanyname;
    Spinner spinnertype;
    ArrayList<String> typeslist;
    Toolbar toolbar;
    FirebaseFirestore db;
    CustomUntouchableLoading anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_acceptor_details);

        toolbar = findViewById(R.id.toolbar_editacceptordetails_activity);
        setSupportActionBar(toolbar);

        adddetailsflag = getIntent().getBooleanExtra("addflag",false);

        etminqty = findViewById(R.id.etminqty_editacceptor_activity);
        etmaxqty = findViewById(R.id.etmaxqty_editacceptor_activity);
        tides = findViewById(R.id.tidescription_editacceptor_activity);
        etconpanyname = findViewById(R.id.etcompanyname_editacceptor_activity);
        spinnertype = findViewById(R.id.spinnertypr_editacceptor_activity);

        db = FirebaseFirestore.getInstance();

        anim = new CustomUntouchableLoading(this);

        typeslist = new ArrayList<>();
        typeslist.addAll(Arrays.asList(new String[]{"Indudival", "Orfanage", "NGO's", "Self help groups"}));
        spinnertype.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,typeslist));

        spinnertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    etconpanyname.setHint("Enter the " + typeslist.get(position) + " Name");
                    etconpanyname.setVisibility(View.VISIBLE);
                }
                else{
                    etconpanyname.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //new user
        if(adddetailsflag){
            getSupportActionBar().setTitle("Few details to get donated food..");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //old user
        else{
            getSupportActionBar().setTitle("Edit Your Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_editacceptor_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_done_editacceptor){
            addacceptor();
        }
        return true;
    }

    private void addacceptor() {
        String email,name,phno,uid;
        Boolean isdonor;
        email = getIntent().getStringExtra("email");
        name = getIntent().getStringExtra("name");
        phno = getIntent().getStringExtra("phno");
        uid = getIntent().getStringExtra("uid");
        isdonor = false;

        String des,type,companyname=null;
        Integer minqty,maxqty;
        Boolean isindividual;
        des = tides.getEditText().getText().toString();
        type = spinnertype.getSelectedItem().toString();
        minqty = Integer.parseInt(etminqty.getText().toString());
        maxqty = Integer.parseInt(etmaxqty.getText().toString());
        if(spinnertype.getSelectedItemPosition() > 0){
            isindividual = false;
            companyname = etconpanyname.getText().toString();
        }
        else{
            isindividual = true;
        }

        User tempuser = new User(uid,name,phno,email,isdonor);
        Acceptor tempacceptor = new Acceptor(des,type,minqty,maxqty,companyname,isindividual);

        anim.startanimation();
        WriteBatch addacceptorbatch = db.batch();
        addacceptorbatch.set(db.collection("userdetails").document(uid),tempuser);
        addacceptorbatch.set(db.collection("acceptordetails").document(uid),tempacceptor);
        addacceptorbatch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                anim.stopanimation();
                if(task.isSuccessful()){
                    Toast.makeText(EditAcceptorDetailsActivity.this, "Your details are added You will be notified when food avaliable", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditAcceptorDetailsActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    if(task.getException()!=null){
                        Toast.makeText(EditAcceptorDetailsActivity.this, "Exception " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(EditAcceptorDetailsActivity.this, "Check your Internet :(", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}