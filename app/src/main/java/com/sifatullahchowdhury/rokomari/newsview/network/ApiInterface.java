package com.sifatullahchowdhury.rokomari.newsview.network;


import com.sifatullahchowdhury.rokomari.newsview.model.ArticleListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Sifat Ullah on 2/18/2019.
 */


public interface ApiInterface
{


    @GET("top-headlines")
    Call<ArticleListResponse> getLatestNewsOfSource(@Query("sources") String source,@Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<ArticleListResponse> getLatestNewsOfCountry(@Query("country") String countryCode,@Query("apiKey") String apiKey);


    @GET("everything")
    Call<ArticleListResponse> getEverythingAboutTopic(@Query("q") String topic,@Query("apiKey") String apiKey);

    @GET("sources")
    Call<ArticleListResponse> getAllSources(@Query("apiKey") String apiKey);

}
