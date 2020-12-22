package com.example.donationapp;

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

public class LibraryFragment extends Fragment {

    TextView tvusername,tvusertype;
    ImageView ivuserimage;
    ExtendedFloatingActionButton btnlogout,btneditprofile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library,container,false);
        tvusername = view.findViewById(R.id.tvusername_library_fragment);
        tvusertype = view.findViewById(R.id.tvusertype_library_fragment);
        ivuserimage = view.findViewById(R.id.ivuserimage_library_fragment);
        btnlogout = view.findViewById(R.id.btnlogout_library_fragment);
        btneditprofile = view.findViewById(R.id.btnedit_profile_library_fragment);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btneditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
