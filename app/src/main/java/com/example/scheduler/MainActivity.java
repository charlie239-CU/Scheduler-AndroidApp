package com.example.scheduler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    Button sign1 ,login;
    EditText txt1,txt2;
    TextView txt;
    String first,second;
    String count;
    String flag="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign1=(Button)findViewById(R.id.signup);
        login=(Button)findViewById(R.id.login);
        txt1=(EditText)findViewById(R.id.username2);
        txt2=(EditText)findViewById(R.id.password2);
        txt=(TextView) findViewById(R.id.textView3);
        sign1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensignup();
            }

        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin();
            }

        });
        StrictMode.ThreadPolicy threadPolicy=new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(threadPolicy);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }
    public HttpURLConnection createConnection() {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://192.168.43.136/scheduler/u_details.php");
            connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            Log.d("connection","established");
        }
        catch(Exception e)
        {
            Log.d("connection","e.toString()");
        }
        return connection;

    }
    public void opensignup()
    {
        Intent intent=new Intent(this, signup.class);
        startActivity(intent);

    }
    public void openlogin()
    {
        insert();


    }
    public void insert()
    {
        first=txt1.getText().toString();
        second=txt2.getText().toString();
        StringBuilder stringBuilder=new StringBuilder();
        StringBuilder stringBuilder1=new StringBuilder();
        String line="",line2="";
        try
        {
            HttpURLConnection connection=createConnection();
            OutputStream outputStream=connection.getOutputStream();
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");
            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
            String data= URLEncoder.encode("t1","UTF-8")+"="+URLEncoder.encode(first,"UTF-8")+
                    "&"+URLEncoder.encode("t2","UTF-8")+"="+URLEncoder.encode(second,"UTF-8")+
                    "&"+  URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode("LOGIN","UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            InputStream inputStream=connection.getInputStream();

            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);


            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }
            String data1=stringBuilder.toString();
            Log.d("Data",data1);

            JSONObject jsonObject=new JSONObject(data1);
            int x=jsonObject.getInt("code");
            if(x==1)
            {
               /* try
                {
                    HttpURLConnection conn = Connection.createConnection("name_fetch.php");
                    InputStream inputStream1 = conn.getInputStream();

                    InputStreamReader inputStreamReader1 = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader);


                    while ((line2 = bufferedReader1.readLine()) != null) {
                        stringBuilder1.append(line + "\n");
                    }
                    String data2 = stringBuilder.toString();
                    Log.d("Data", data1);

                    JSONObject jsonObject1 = new JSONObject(data2);
                    String name = jsonObject1.getString("code");
                    Log.d("nameof",name);
                }
                catch(Exception e)
                {
                    Log.d("insider",e.toString());
                }*/
                sharedpref2();
                Helper.makeToast(getApplicationContext(),""+flag);
                sharedpref(first);
                Intent intent = new Intent(getApplicationContext(), Navigation.class);
                startActivity(intent);


            }
            else
            {
                Toast.makeText(this,"invalid username and password",Toast.LENGTH_LONG).show();
            }
            connection.disconnect();
            Log.d("data","insert"+data);
        }
        catch(Exception e)
        {
            Log.d("data",e.toString());
        }
    }

    public void sharedpref2() {
        SharedPreferences sharedPreferences1=getSharedPreferences("stuPref1",0);

        SharedPreferences.Editor editor=sharedPreferences1.edit();
        flag="1";
        editor.putString("flag1",flag);
        editor.commit();
    }

    public void sharedpref(String first) {
        SharedPreferences sharedPreferences=getSharedPreferences("stuPref",0);

        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString("username",first);
        editor.commit();
    }

}
