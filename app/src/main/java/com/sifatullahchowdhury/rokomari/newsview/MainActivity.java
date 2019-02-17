package com.sifatullahchowdhury.rokomari.newsview;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sifatullahchowdhury.rokomari.newsview.applicaton.MyApplication;
import com.sifatullahchowdhury.rokomari.newsview.utility.Utility;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();


        if (id == R.id.nav_home) {

            return true;

        }

        else if (id==R.id.nav_about){
            mDrawerLayout.closeDrawers();
            Utility.aboutDialog(this);

            return true;


        }

        else if (id==R.id.nav_exit){
            try {
                mDrawerLayout.closeDrawers();
            }
            catch (Exception e){}

            finish();
            return true;

        }
        else
        return super.onOptionsItemSelected(menuItem);





    }


    void drawer() {
         mDrawerLayout =  findViewById(R.id.drawer_layout);
        NavigationView navigationView =  findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}