package com.example.scheduler;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class signup extends AppCompatActivity {

    EditText uid,name,email,password;
    Button sign;
    String first,second,third,forth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        StrictMode.ThreadPolicy threadPolicy=new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(threadPolicy);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        bindWidget();
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
                openmain();
            }
        });


    }
    public void openmain()
    {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void bindWidget() {
        uid=(EditText)findViewById(R.id.username2);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password2) ;
        sign=(Button)findViewById(R.id.signup2);
    }
    public HttpURLConnection createConnection() {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://192.168.43.136/scheduler/u_details.php");
            connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            Log.d("connection","established");
        }
        catch(Exception e)
        {
            Log.d("connection","e.toString()");
        }
        return connection;

    }
    public void insert()
    {
        first=uid.getText().toString();
        second=name.getText().toString();
        third=email.getText().toString();
        forth=password.getText().toString();
        try
        {
            HttpURLConnection connection=createConnection();
            OutputStream outputStream=connection.getOutputStream();
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");
            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
            String data= URLEncoder.encode("t1","UTF-8")+"="+URLEncoder.encode(first,"UTF-8")+
                    "&"+URLEncoder.encode("t2","UTF-8")+"="+URLEncoder.encode(second,"UTF-8")+
                    "&"+URLEncoder.encode("t3","UTF-8")+"="+URLEncoder.encode(third,"UTF-8")+
            "&"+URLEncoder.encode("t4","UTF-8")+"="+URLEncoder.encode(forth,"UTF-8")+
                  "&"+  URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode("SUBMIT","UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            InputStream inputStream=connection.getInputStream();
            connection.disconnect();
            Log.d("data","insert"+data);
        }
        catch(Exception e)
        {
            Log.d("data",e.toString());
        }
    }

}
