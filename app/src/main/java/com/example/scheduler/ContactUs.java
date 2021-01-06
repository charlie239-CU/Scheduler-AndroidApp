package com.example.scheduler;


import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scheduler.Adapter.TaskAdapter;
import com.example.scheduler.Helper.Connection;
import com.example.scheduler.Helper.Helper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.List;


public class ContactUs extends Fragment {
    Button add;
    TextView t;
    String name = "", uid = "";
    String uid1[];
    String time[];
    String repeat_task[];
    String todo[];
    String date[];
    Helper help=new Helper();
    ListView list;
    String uid2,uid3,status;
    ImageView img;
    int length;
    public ContactUs()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);

        StrictMode.ThreadPolicy threadPolicy=new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(threadPolicy);
        if (android.os.Build.VERSION.SDK_INT >
                9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        add =(Button)view.findViewById(R.id.add1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),task.class);
                startActivity(intent);
            }

        });
        Log.d("pref","123123");
        sharedpref();
        Log.d("pref",uid2);
        list=(ListView)view.findViewById(R.id.tasklist);
        int l=selectData();
        Helper.makeToast(getActivity(),""+l);
        if(l>0) {
            TaskAdapter adp = new TaskAdapter(getActivity().getApplicationContext(), time, repeat_task, todo, date);
            list.setAdapter(adp);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   // ImageView img = (ImageView) view.findViewById(R.id.imageView12);

                }
            });
        }
        else
        {
       //     Helper.makeToast(getActivity(),"no task");
        }

        return view;

    }
    public void sharedpref() {
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("stuPref",0);



        uid2=sharedPreferences.getString("username","NA");


    }


    public int selectData()
    {
        String line="";
        StringBuilder stringBuilder=new StringBuilder();

        try
        {

            HttpURLConnection conn= Connection.createConnection("fetch.php");
            status="pending";
            OutputStream outputStream= conn.getOutputStream();
            Log.d("uid2",uid2);
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");
            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
            String data2=URLEncoder.encode("t1","UTF-8")+"="+URLEncoder.encode(uid2,"UTF-8")
            +"&"+URLEncoder.encode("t2","UTF-8")+"="+URLEncoder.encode(status,"UTF-8");
            bufferedWriter.write(data2);
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            InputStream inputStream=conn.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }
            String data=stringBuilder.toString();
            Log.d("log1",data);
           Toast.makeText(getActivity().getApplicationContext(),data,Toast.LENGTH_LONG).show();
            JSONArray jsonArray=new JSONArray(data);
            //init the array;
            //uid1=new String[jsonArray.length()];
            date = new String[jsonArray.length()];
            time = new String[jsonArray.length()];
            repeat_task = new String[jsonArray.length()];
            todo = new String[jsonArray.length()];
            JSONObject jsonObject=null;
            length=jsonArray.length();
            for (int i=0;i<jsonArray.length();i++)
            {
                jsonObject=jsonArray.getJSONObject(i);

             //   uid1[i]=jsonObject.getString("uid");
                date[i]=jsonObject.getString("date");
                Log.d("metadata",date[i]);
                time[i]=jsonObject.getString("time");
                Log.d("metadata",time[i]);
                repeat_task[i]=jsonObject.getString("repeat_task");
                Log.d("metadata",repeat_task[i]);
                todo[i]=jsonObject.getString("todo");
                Log.d("metadata",todo[i]);
                Toast.makeText(getActivity().getApplicationContext(),time[i],Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
          Log.d("error2",e.toString());
        }
        return length;

    }

}
