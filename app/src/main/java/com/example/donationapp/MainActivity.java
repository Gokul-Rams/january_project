package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    FrameLayout fragmentcontainer;
    BottomNavigationView navview;
    Fragment fragtoadd = null;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navview = findViewById(R.id.navigation_bottom);
        navview.setOnNavigationItemSelectedListener(this);

        fragmentcontainer = findViewById(R.id.frame_main_container);

        toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Donate Food");

        fragtoadd = new HomeFragment();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.frame_main_container,fragtoadd);
        trans.commit();
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