package com.example.donationapp;

import android.app.Application;

import com.example.donationapp.entities.User;

public class ApplicationClass extends Application {
    public static User currentuser;

    public ApplicationClass() {
        currentuser = new User();
    }
}
