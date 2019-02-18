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
    public static String getStringResource(int id){return APP_CONTEXT.getString(id);}

    public static void aboutDialog(Context context) {


        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle(R.string.about);
                alertDialog.setIcon(R.drawable.about);
                alertDialog.setMessage(context.getResources().getString(R.string.about_text));
               alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, context.getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }




}

