package com.example.scheduler.Data;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.scheduler.R;

public class customAdapter extends BaseAdapter {


    String cname[];
    String ccap[];
    Context context;
    LayoutInflater layoutInflater;
    public customAdapter(Context context, String cname[], String... ccap)
    {
        this.context=context;
        this.cname=cname;
        this.ccap=ccap;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return cname.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.layout,null,false);
        TextView todo=(TextView)convertView.findViewById(R.id.textView7);
        TextView time=(TextView)convertView.findViewById(R.id.textView8);
        TextView date=(TextView)convertView.findViewById(R.id.textView9);
        TextView repetation=(TextView)convertView.findViewById(R.id.textView10);


        return convertView;
    }
}
