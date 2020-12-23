package com.example.donationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donationapp.customadapters.SettingListAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView lvsettinglist;
    ExtendedFloatingActionButton editbtn,viewbtn;
    TextView tvname,tvtype;
    ImageView ivuserimage;
    SettingListAdapter adap;
    ArrayList<String> nameslist;
    ArrayList<Integer> iconlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar = findViewById(R.id.toolbar_setting_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Setting");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvname = findViewById(R.id.tvname_setting_activity);
        tvtype = findViewById(R.id.tvtype_setting_activity);
        ivuserimage = findViewById(R.id.ivuserimage_fragment_library);
        editbtn = findViewById(R.id.btneditprofile_setting_activity);
        viewbtn = findViewById(R.id.btnprofileview_setting_activity);
        lvsettinglist = findViewById(R.id.lvsetting_setting_activity);

        nameslist = new ArrayList<>();
        iconlist = new ArrayList<>();
        nameslist.addAll(Arrays.asList(new String[]{"Notification", "Personolize"}));
        iconlist.addAll(Arrays.asList(new Integer[]{R.drawable.icon_notification, R.drawable.icon_notification}));

        adap = new SettingListAdapter(nameslist,iconlist,this);

        lvsettinglist.setAdapter(adap);

        tvname.setText(ApplicationClass.currentuser.getName());
        if(ApplicationClass.currentuser.getIsdonor()) {
            tvtype.setText("Donor");
        }
        else{
            tvtype.setText("Acceptor");
        }

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,EditdetailsActivity.class);
                intent.putExtra("addflag",false);
                startActivity(intent);
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,ViewProfileActivity.class);
                startActivity(intent);
            }
        });

        lvsettinglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SettingActivity.this, ""+nameslist.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}