package com.example.scheduler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment=null;
    String flag,uid;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        starting();
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview=navigationView.getHeaderView(0);

        ImageView photu=(ImageView)findViewById(R.id.photu);
      TextView nav_uid=(TextView)findViewById(R.id.nav_uid);
        TextView nav_name=(TextView)findViewById(R.id.nav_name);
        sharedpref1();
          //nav_uid.setText("123123");

    }

    public void sharedpref1() {
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("stuPref",0);



        uid=sharedPreferences.getString("username","NA");


    }

    public void starting() {
        fragment=new ContactUs();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentmain,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home)
        {
            fragment=new ContactUs();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentmain,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.ch_pass) {
            fragment=new change_pass();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentmain,fragment);
            fragmentTransaction.commit();


        } else if (id == R.id.old_task) {


        } else if (id == R.id.logout) {
            Intent intent=new Intent(this, MainActivity.class);
            sharedpref2();
            startActivity(intent);

        }/* else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void sharedpref2() {
        SharedPreferences sharedPreferences1=getSharedPreferences("stuPref1",0);

        SharedPreferences.Editor editor=sharedPreferences1.edit();
        flag="0";
        editor.putString("flag1",flag);
        editor.commit();
    }
}
