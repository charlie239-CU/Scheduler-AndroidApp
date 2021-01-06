package com.example.scheduler;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.scheduler.Data.Data;
import com.example.scheduler.Helper.Helper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

public class task extends AppCompatActivity {

   TextView date,repeat,repeatSelect;
   ImageView img1,img2,img3;
   EditText todo;
String[] listItems;
   Button btn;


   private static final String TAG="task";
   private TextView mDisplayDate;
   private DatePickerDialog.OnDateSetListener mDataSetListener;
    TimePickerDialog timePickerDialog;
    TextView mDisplayTime;
    String ampm,uid;




    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task);
        Helper.createStrictMode();

        img1=(ImageView)findViewById(R.drawable.ic_calendar);
        img2=(ImageView)findViewById(R.drawable.ic_alarm_clock);
        img2=(ImageView)findViewById(R.drawable.ic_repeat);
        btn=(Button) findViewById(R.id.button);
        date=(TextView)findViewById(R.id.date);
        todo=(EditText) findViewById(R.id.editText);
        repeat=(TextView)findViewById(R.id.nav_name);
        repeatSelect=(TextView)findViewById(R.id.textView6);




        mDisplayDate=(TextView)findViewById(R.id.textView2);

        mDisplayTime=(TextView)findViewById(R.id.textView5);
        sharedpref();//geting uid






        repeatSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create list of items
                listItems=new String[]{"Hourly","Daily","Monthly","Weekly"};
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(task.this);
                mBuilder.setTitle("Choose an Item");
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        repeatSelect.setText(listItems[i]);
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog mDialog=mBuilder.create();
                mDialog.show();
            }
        });


       mDisplayTime.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Calendar cal=Calendar.getInstance();
               int hour=cal.get(Calendar.HOUR_OF_DAY);
               int minute=cal.get(Calendar.MINUTE);

               timePickerDialog=new TimePickerDialog(task.this, new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                       if(hour>=12)
                       {
                           ampm="PM";
                       }
                       else
                       {
                           ampm="AM";
                       }
                      mDisplayTime.setText(String.format("%02d:%02d" ,hour,minute)+ampm);

                   }
               },hour,minute,true);
               timePickerDialog.show();

           }
       });
        mDisplayDate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(task.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDataSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDataSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                Log.d(TAG,"onDateSet: mm/dd/yyyy:"+ month+"/"+"/"+day+"/"+year);
                String date=year+"-"+month+"-"+day;
                mDisplayDate.setText(date);
            }
        };
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data data=new Data();
                String date1=mDisplayDate.getText().toString();
                String repeat1=mDisplayTime.getText().toString();
                String repeatselect1=repeatSelect.getText().toString();
                String todo1=todo.getText().toString();
                String arr[]={date1,repeat1,repeatselect1,todo1};

                //customAdapter customAdapter=new customAdapter(getApplicationContext(),arr,);
                try {

                    String data1= URLEncoder.encode("t1","UTF-8")+"="+URLEncoder.encode(date1,"UTF-8")+
                            "&"+URLEncoder.encode("t2","UTF-8")+"="+URLEncoder.encode(repeat1,"UTF-8")+
                            "&"+URLEncoder.encode("t3","UTF-8")+"="+URLEncoder.encode(repeatselect1,"UTF-8")+
                            "&"+URLEncoder.encode("t4","UTF-8")+"="+URLEncoder.encode(todo1,"UTF-8")+
                            "&"+URLEncoder.encode("t5","UTF-8")+"="+URLEncoder.encode(uid,"UTF-8")+
                            "&"+URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode("ADD","UTF-8");
                    data.insertData(getApplicationContext(),"add_task.php",data1);
                    pass(uid);
                    Intent intent = new Intent(getApplicationContext(), Navigation.class);
                    startActivity(intent);

                } catch (Exception e) {

                    Log.d("DATA","in act"+e.toString());
                }

            }

         private void pass(String uid) throws UnsupportedEncodingException {
                    Data data2=new Data();
                    String data1= URLEncoder.encode("t1","UTF-8")+"="+URLEncoder.encode(uid,"UTF-8");
                    data2.insertData(getApplicationContext(),"fetch.php",data1);
                    Log.d("pass",uid);
            }
        });





    }

    public void sharedpref() {
        SharedPreferences sharedPreferences=getSharedPreferences("stuPref",0);

         uid=sharedPreferences.getString("username","NA");

    }

}



