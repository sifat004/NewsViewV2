package com.sifatullahchowdhury.rokomari.newsview.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.sifatullahchowdhury.rokomari.newsview.applicaton.MyApplication;

/**
 * Created by Sifat Ullah on 2/17/2019.
 */

public class PreferenceManager {
    private static final String PREFERENCE = "newsViews";
    private static final String FIRST_LAUNCH = "firstLaunch";

    public static SharedPreferences getSp() {
        return MyApplication.getAppContext().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
    }

    public static void setFirstTimeLaunch(boolean isFirstTime) {
        getSp().edit().putBoolean(FIRST_LAUNCH, isFirstTime).apply();

    }

    public static boolean isFirstLaunch() {
        return getSp().getBoolean(FIRST_LAUNCH, true);
    }
}