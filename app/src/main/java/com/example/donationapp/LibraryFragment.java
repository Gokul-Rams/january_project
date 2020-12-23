package com.example.donationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class LibraryFragment extends Fragment {

    TextView tvusername,tvusertype;
    ImageView ivuserimage;
    ExtendedFloatingActionButton btnlogout, btnsetting;
    FirebaseAuth auth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library,container,false);
        tvusername = view.findViewById(R.id.tvusername_library_fragment);
        tvusertype = view.findViewById(R.id.tvusertype_library_fragment);
        ivuserimage = view.findViewById(R.id.ivuserimage_library_fragment);
        btnlogout = view.findViewById(R.id.btnlogout_library_fragment);
        btnsetting = view.findViewById(R.id.btnsetting_library_fragment);

        auth = FirebaseAuth.getInstance();

        tvusername.setText(ApplicationClass.currentuser.getName());
        if(ApplicationClass.currentuser.getIsdonor()) {
            tvusertype.setText("Donor");
        }
        else{
            tvusertype.setText("Acceptor");
        }

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SettingActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
