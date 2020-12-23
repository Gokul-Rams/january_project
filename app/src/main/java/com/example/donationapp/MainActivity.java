package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.donationapp.enhancers.CustomUntouchableLoading;
import com.example.donationapp.entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    FrameLayout fragmentcontainer;
    BottomNavigationView navview;
    Fragment fragtoadd = null;
    Toolbar toolbar;
    FirebaseAuth auth;
    FirebaseFirestore db;
    CustomUntouchableLoading anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        final FirebaseUser currentauthuser = auth.getCurrentUser();

        if(currentauthuser!=null) {
            navview = findViewById(R.id.navigation_bottom);
            navview.setOnNavigationItemSelectedListener(this);

            fragmentcontainer = findViewById(R.id.frame_main_container);

            toolbar = findViewById(R.id.toolbar_home);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Donate Food");

            fragtoadd = new HomeFragment();
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.replace(R.id.frame_main_container, fragtoadd);
            trans.commit();

            anim = new CustomUntouchableLoading(this);
            anim.startanimation();
            db = FirebaseFirestore.getInstance();
            db.collection("userdetails").document(currentauthuser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot doc = task.getResult();
                        anim.stopanimation();

                        if(!doc.exists()){
                            AlertDialog.Builder tempbuild = new AlertDialog.Builder(MainActivity.this);
                            tempbuild.setMessage("Looks like your details are not added Add Your Details to proceed");
                            tempbuild.setCancelable(false);
                            tempbuild.setPositiveButton("Add Detail", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, EditdetailsActivity.class);
                                    intent.putExtra("addflag", true);
                                    intent.putExtra("uid", currentauthuser.getUid());
                                    intent.putExtra("email", currentauthuser.getEmail());
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            AlertDialog dialog = tempbuild.create();
                            dialog.show();
                        }
                        else{
                            ApplicationClass.currentuser = doc.toObject(User.class);
                        }

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Check your Internet connection" + task.getException(), Toast.LENGTH_SHORT).show();
                        System.out.println(task.getException());
                    }
                }
            });
        }
        else {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homenav_menu:
                fragtoadd = new HomeFragment();
                break;
            case R.id.socialnav_menu:
                fragtoadd = new SocialFragment();
                break;
            case R.id.libnav_menu:
                fragtoadd = new LibraryFragment();
                break;
        }
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.frame_main_container,fragtoadd);
        trans.commit();
        return true;
    }
}