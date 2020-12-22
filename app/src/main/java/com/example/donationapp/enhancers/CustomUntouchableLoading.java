package com.example.donationapp.enhancers;

import android.app.AlertDialog;
import android.content.Context;

import com.example.donationapp.R;

public class CustomUntouchableLoading {
    AlertDialog dilog;
    Context parentcontext;

    public CustomUntouchableLoading(Context context) {

        parentcontext = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(parentcontext);
        builder.setView(R.layout.custom_loadingdialog_layout);
        builder.setCancelable(false);
        dilog = builder.create();
    }

    public void startanimation(){
        dilog.show();
    }

    public void stopanimation(){
        dilog.dismiss();
    }
}
