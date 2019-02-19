package com.sifatullahchowdhury.rokomari.newsview.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Sifat Ullah on 2/18/2019.
 */

public class ApiClient
{
    public static final String NEWS_API_BASE_URL = "https://newsapi.org/v2/";
    public static final String NUMBERS_API_BASE_URL = "http://numbersapi.com/";



    private static Retrofit retrofitNEWSApi = null;
    private static Retrofit retrofitNUMBERSApi = null;


    /**
     * This method returns retrofitNEWSApi client instance
     *
     * @return Retrofit object
     */
    public static Retrofit getNewsApiClient() {
        if (retrofitNEWSApi == null) {
            retrofitNEWSApi = new Retrofit.Builder()
                    .baseUrl(NEWS_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitNEWSApi;
    }

    public static Retrofit getNumbersApiClient() {
        if (retrofitNUMBERSApi == null) {
            retrofitNUMBERSApi = new Retrofit.Builder()
                    .baseUrl(NUMBERS_API_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create()) //scalarsfactory for getting string response
                    .build();
        }
        return retrofitNUMBERSApi;
    }
}
