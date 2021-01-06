package com.example.scheduler;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scheduler.Helper.Connection;
import com.example.scheduler.Helper.Helper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;


public class start extends AppCompatActivity {

    Button add;
    TextView t;
    String name = "", uid = "";
    String uid1[];
    String time[];
    String repeat_task[];
    String todo[];
    String date[];




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("metadata","start");
        setContentView(R.layout.activity_start);

        add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentask();
            }

        });
        selectData();

     /*   Toast.makeText(getApplicationContext(), " " + name, Toast.LENGTH_LONG).show();
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        t = (TextView) findViewById(R.id.textView11);
       Helper help = new Helper();
      help.makeToast(getApplicationContext(), formattedDate);*/

    }

    public void opentask() {
        Intent intent = new Intent(getApplicationContext(), task.class);
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("uid");
        intent.putExtra("uid",uid);
        startActivity(intent);

    }
    Helper help=new Helper();
   
    public void selectData()
    {
        String line="";
        StringBuilder stringBuilder=new StringBuilder();
        try
        {
           HttpURLConnection conn= Connection.createConnection("fetch.php");
            InputStream inputStream=conn.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }
            String data=stringBuilder.toString();
            JSONArray jsonArray=new JSONArray(data);
            //init the array;
             uid1 = new String[jsonArray.length()];
             date = new String[jsonArray.length()];
             time = new String[jsonArray.length()];
             repeat_task = new String[jsonArray.length()];
             todo = new String[jsonArray.length()];
            JSONObject jsonObject=null;
            for (int i=0;i<uid1.length;i++)
            {
                jsonObject=jsonArray.getJSONObject(i);
                uid1[i]=jsonObject.getString("uid");
                Log.d("metadata",uid1[i]);
                date[i]=jsonObject.getString("date");
                Log.d("metadata",date[i]);
                time[i]=jsonObject.getString("time");
                Log.d("metadata",time[i]);
                repeat_task[i]=jsonObject.getString("repeat_task");
                Log.d("metadata",repeat_task[i]);
                todo[i]=jsonObject.getString("todo");
                Log.d("metadata",todo[i]);
            }
        }
        catch(Exception e)
        {

        }

    }
}

