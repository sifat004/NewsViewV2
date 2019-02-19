package com.sifatullahchowdhury.rokomari.newsview.utility;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sifatullahchowdhury.rokomari.newsview.R;
import com.sifatullahchowdhury.rokomari.newsview.applicaton.MyApplication;

/**
 * Created by Sifat Ullah on 2/17/2019.
 */

public class Utility {

    public  static Context APP_CONTEXT = MyApplication.getAppContext();
    public static String NEWS_API_KEY = getStringResource(R.string.api_key);

    public static final int NEWS_OF_COUNTRY=1;
    public static final int NEWS_OF_SOURCE=2;
    public static final int NEWS_OF_TOPIC=3;

    public static final String NEWS_OF_US=getStringResource(R.string.US);
    public static final String NEWS_OF_UK=getStringResource(R.string.UK);
    public static final String NEWS_OF_SPORTS=getStringResource(R.string.sports);
    public static final String NEWS_OF_TECH=getStringResource(R.string.tech);
    public static final String NEWS_OF_CNN =getStringResource(R.string.cnn);
    public static final String NEWS_OF_MIRROR=getStringResource(R.string.mirror);




    public static String getStringResource(int id){return APP_CONTEXT.getString(id);}

    public static void textDialog(Context context,String title,String content,int icon) {


        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle(title);
                alertDialog.setIcon(icon);
                alertDialog.setMessage(content);
               alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, context.getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }




}

