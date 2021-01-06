/*package com.example.scheduler;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SharedPrefrences extends AppCompatActivity {

    SharedPreferences sharedPreferences=null;
    String PrefName="DATA";
    EditText txtname,txtpass;
    Button addpref,deletepref,updatepref,selectpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefrences);
        sharedPreferences =getSharedPreferences(PrefName, Context.MODE_PRIVATE);
        addpref.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("username",txtname.getText().toString());
                editor.putString("password",txtpass.getText().toString());

                editor.commit();
            }
        });
        updatepref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("username",txtname.getText().toString());
                editor.putString("password",txtpass.getText().toString());

                editor.commit();
            }
        });
        deletepref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                //editor.putString("username",null);
                editor.remove("username");
                editor.remove("password");
                editor.commit();

            }
        });

        selectpref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String username=sharedPreferences.getString("username","");
                String pass=sharedPreferences.getString("password","");
                int sal=sharedPreferences.getInt("salary",0);
                Log.d("DATA ","username"+username+"Password"+pass+"Salary is "+sal);

            }
        });
    }
    public void bindControl()
    {
        txtname=(EditText)findViewById(R.id.editText6);
        txtpass=(EditText)findViewById(R.id.editText7);
        addpref=(Button)findViewById(R.id.button12);
        deletepref=(Button)findViewById(R.id.button14);
        updatepref=(Button)findViewById(R.id.button13);
        selectpref=(Button)findViewById(R.id.button15);

    }
}*/
