package com.sifatullahchowdhury.rokomari.newsview.utility;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sifatullahchowdhury.rokomari.newsview.R;
import com.sifatullahchowdhury.rokomari.newsview.applicaton.MyApplication;
import com.sifatullahchowdhury.rokomari.newsview.model.Article;
import com.sifatullahchowdhury.rokomari.newsview.show_full_article_activity.ShowArticleActivity;
import com.sifatullahchowdhury.rokomari.newsview.trivia_activity.TriviaActivity;

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


    public static void searchPopup(final Context mContext) {
        final Dialog mDialog;
        mDialog=new Dialog(mContext);


        mDialog.setContentView(R.layout.searchpopup);


        final EditText numInput,dateInput,monthInput;
        final TextView tv,sug;

        final Button go,number,date;
        final RelativeLayout l1,l2;

        numInput=mDialog.findViewById(R.id.et_number_input);
        dateInput=mDialog.findViewById(R.id.et_date_input);
        monthInput=mDialog.findViewById(R.id.et_month_input);

        tv=mDialog.findViewById(R.id.tv);
        sug=mDialog.findViewById(R.id.suggestion);


        go=mDialog.findViewById(R.id.go);
        number=mDialog.findViewById(R.id.number);
        date=mDialog.findViewById(R.id.date);

        l1=mDialog.findViewById(R.id.layout1);
        l2=mDialog.findViewById(R.id.layout2);



        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
                sug.setVisibility(View.GONE);
                go.setVisibility(View.VISIBLE);
                numInput.setVisibility(View.VISIBLE);
                dateInput.setVisibility(View.GONE);
                monthInput.setVisibility(View.GONE);

                tv.setText(getStringResource(R.string.give_a_number));
            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
                sug.setVisibility(View.GONE);

                go.setVisibility(View.VISIBLE);
                numInput.setVisibility(View.GONE);
                dateInput.setVisibility(View.VISIBLE);
                monthInput.setVisibility(View.VISIBLE);
                tv.setText(getStringResource(R.string.give_a_date));

            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext, TriviaActivity.class);

                if (!numInput.getText().toString().equals("")){

                    int n= Integer.parseInt(numInput.getText().toString());
                    intent.putExtra("number",n);

                }

                else if (!dateInput.getText().toString().equals("") && !monthInput.getText().toString().equals("")){

                    int date= Integer.parseInt(dateInput.getText().toString());
                    int month= Integer.parseInt(monthInput.getText().toString());

                    intent.putExtra("date",date);
                    intent.putExtra("month",month);


                }



                mDialog.dismiss();

                mContext.startActivity(intent);
            }
        });


        mDialog.show();
    }




}

