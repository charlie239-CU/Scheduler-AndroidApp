package com.example.scheduler.Adapter;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scheduler.Helper.Connection;
import com.example.scheduler.Helper.Helper;
import com.example.scheduler.R;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class TaskAdapter extends BaseAdapter
{
    String time[];
    String repeat_task[];
    String todo[];
    String date[];
  //  String uid[];
    Context context;

    LayoutInflater layoutInflater=null;
    public TaskAdapter(Context context, String[] time, String[] repeat_task, String[] todo, String[] date)
    {
        this.context=context;
        this.time=time;

        this.repeat_task=repeat_task;
        this.todo=todo;
        this.date=date;
        layoutInflater=LayoutInflater.from(context);

    }
    @Override
    public int getCount()
    {
        return time.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=layoutInflater.inflate(R.layout.taskcontent,null);
        TextView txttodo=(TextView)view.findViewById(R.id.textView13);
        TextView txtrepeat_task=(TextView)view.findViewById(R.id.textView16);
        TextView txtdate=(TextView)view.findViewById(R.id.textView15);
        TextView txttime=(TextView)view.findViewById(R.id.textView14);
        ImageView clock=(ImageView)view.findViewById(R.id.imageView9);
        ImageView cal=(ImageView)view.findViewById(R.id.imageView10);
        ImageView rep=(ImageView)view.findViewById(R.id.imageView11);
        ImageView del=(ImageView)view.findViewById(R.id.imageView12);
        clock.setImageResource(R.drawable.ic_alarm_clock);
        cal.setImageResource(R.drawable.ic_calendar);
        rep.setImageResource(R.drawable.ic_repeat);
        del.setImageResource(R.drawable.ic_icon);
        txttodo.setText(todo[i]);
        txtrepeat_task.setText(repeat_task[i]);
        txtdate.setText(date[i]);
        txttime.setText(time[i]);
      /*  del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }


        });*/

        return view;
    }
   /* public void delete() {
        try {
            HttpURLConnection conn = Connection.createConnection("delete_task.php");
            OutputStream outputStream = conn.getOutputStream();
            //  Log.d("uid2",uid2);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            String data2 = URLEncoder.encode("t1", "UTF-8") + "=" + URLEncoder.encode(uid[0], "UTF-8");

            bufferedWriter.write(data2);
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();
        }
        catch(Exception e)
        {
            Log.d("delete",e.toString());
        }
    }*/
}
