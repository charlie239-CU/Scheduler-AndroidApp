package com.example.scheduler;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scheduler.Helper.Connection;
import com.example.scheduler.Helper.Helper;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;


/**
 * A simple {@link Fragment} subclass.
 */
public class change_pass extends Fragment {
TextView top,cur_p1,new_p1,conf_p1;
EditText cur_p,new_p,conf_p;
Button ch_button;
boolean flag=true;
String current_pass,new_pass,conf_pass,uid;
    View view;

    public change_pass() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_change_pass, container, false);

        Log.d("hero","12312587778");
        init();
        sharedpref();

        Log.d("x123","12312");
        ch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ch_pass();
            }


        });

        return view;
    }

    public void init() {
        top=(TextView)view.findViewById(R.id.top);
        cur_p1=(TextView)view.findViewById(R.id.cur_p1);
        new_p1=(TextView)view.findViewById(R.id.new_p1);
        conf_p1=(TextView)view.findViewById(R.id.conf_p1);
        cur_p=(EditText)view.findViewById(R.id.cur_p);
        new_p=(EditText)view.findViewById(R.id.new_p);
        conf_p=(EditText)view.findViewById(R.id.conf_p);
        ch_button=(Button) view.findViewById(R.id.ch_button);




    }
    public void ch_pass() {
        String line="";
        StringBuilder stringBuilder=new StringBuilder();
        current_pass=cur_p.getText().toString();
        new_pass=new_p.getText().toString();
        conf_pass=conf_p.getText().toString();
        flag=check_match();
        if(flag) {

            try {
                Log.d("x123", "123");
                HttpURLConnection conn = Connection.createConnection("ch_pass.php");
                OutputStream outputStream = conn.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String data2 = URLEncoder.encode("t2", "UTF-8") + "=" + URLEncoder.encode(current_pass, "UTF-8")
                        + "&" + URLEncoder.encode("t3", "UTF-8") + "=" + URLEncoder.encode(new_pass, "UTF-8")
                        + "&" + URLEncoder.encode("t1", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8");


                bufferedWriter.write(data2);
                bufferedWriter.close();
                outputStreamWriter.close();
                outputStream.close();
                InputStream inputStream = conn.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                String data1 = stringBuilder.toString();
                Log.d("Data", data1);

                JSONObject jsonObject = new JSONObject(data1);
                int x = jsonObject.getInt("code");
                Helper.makeToast(getContext(), "" + x);
                if (x == 1) {
                    Helper.makeToast(getContext(), "password changed successfully");

                } else {
                    Helper.makeToast(getContext(), "incorrect current password");
                }
            } catch (Exception e) {
                Log.d("error3", e.toString());
            }
                                                                                                                                          }
        else{
            Helper.makeToast(getActivity(),"new password and confrim password doesn't match");
        }
    }

    public boolean check_match() {
        if(new_pass.equals(conf_pass))
        {
           return true;
        }
        else
        {
            return false;
        }

    }

    public void sharedpref() {
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("stuPref",0);

        uid=sharedPreferences.getString("username","NA");

    }


}
