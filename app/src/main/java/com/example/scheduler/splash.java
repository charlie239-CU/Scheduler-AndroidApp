package com.example.scheduler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.scheduler.Helper.Helper;

public class splash extends AppCompatActivity {
String flag="0",uid3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedpref();

      //  Helper.makeToast(getApplicationContext(),""+flag);
        Thread thread=new Thread()
        {
            public void run()
            {
                try
                {
                   Thread.sleep(3000);

                    if(flag.equals("1")) {
                        Intent intent1 = new Intent(getApplicationContext(), Navigation.class);
                        startActivity(intent1);
                    }
                    else
                    {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
                catch (Exception e)
                {

                }
            }
        };thread.start();
    }
    public void sharedpref() {
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("stuPref1",0);

        flag=sharedPreferences.getString("flag1","0");

    }
}
