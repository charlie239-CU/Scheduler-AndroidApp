package com.example.scheduler;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String Channel_1_ID="channel1";
    public static final String Channel_2_ID="channel2";

    @Override
    public void onCreate()
    {
        super.onCreate();
        createNotificationChannels();
    }
    private void createNotificationChannels()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channell=new NotificationChannel(Channel_1_ID,"channel 1", NotificationManager.IMPORTANCE_HIGH);
            channell.setDescription("This is Channel 1");
            NotificationChannel channel2=new NotificationChannel(Channel_2_ID,"channel 1", NotificationManager.IMPORTANCE_HIGH);
            channell.setDescription("This is Channel 1");
        }




    }
}
