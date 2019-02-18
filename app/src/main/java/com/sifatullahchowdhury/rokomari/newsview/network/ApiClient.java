package com.sifatullahchowdhury.rokomari.newsview.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sifat Ullah on 2/18/2019.
 */

public class ApiClient
{
    public static final String BASE_URL = "https://newsapi.org/";

    private static Retrofit retrofit = null;


    /**
     * This method returns retrofit client instance
     *
     * @return Retrofit object
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
