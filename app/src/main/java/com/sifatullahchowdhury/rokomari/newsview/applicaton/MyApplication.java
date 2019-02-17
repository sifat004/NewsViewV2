package com.sifatullahchowdhury.rokomari.newsview.applicaton;

import android.app.Application;
import android.content.Context;

/**
 * Created by Sifat Ullah on 10/29/2018.
 */

public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();


    }



    public static Context getAppContext() {
        return MyApplication.context;
    }

}