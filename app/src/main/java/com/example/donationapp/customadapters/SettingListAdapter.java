package com.example.donationapp.customadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.donationapp.R;

import java.net.ContentHandler;
import java.util.ArrayList;

public class SettingListAdapter extends BaseAdapter {

    ArrayList<String> nameslist;
    ArrayList<Integer> iconlist;
    Context parentcontext;

    public SettingListAdapter(ArrayList<String> nameslist, ArrayList<Integer> iconlist, Context parentcontext) {
        this.nameslist = nameslist;
        this.iconlist = iconlist;
        this.parentcontext = parentcontext;
    }

    @Override
    public int getCount() {
        return nameslist.size();
    }

    @Override
    public Object getItem(int position) {
        return nameslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parentcontext);
        View view = inflater.inflate(R.layout.custom_setting_listlayout,parent,false);

        TextView tvname = view.findViewById(R.id.tvsettingname_custom_settinglist);
        ImageView ivicon = view.findViewById(R.id.ivsettingicon_custom_settinglist);
        tvname.setText(nameslist.get(position));
        ivicon.setImageResource(iconlist.get(position));

        return view;
    }
}
