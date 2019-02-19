package com.sifatullahchowdhury.rokomari.newsview.network;


import com.sifatullahchowdhury.rokomari.newsview.model.ArticleListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Sifat Ullah on 2/18/2019.
 */


public interface NumbersApiInterface
{


    @GET("{number}/trivia")
    Call<String> number(@Path("number") int number);

    @GET("{month}/{date}/date")
    Call<String> date(@Path("month") int month,@Path("date") int date);

    @GET("{year}/year")
    Call<String> year(@Path("year") int month);


    @GET("random/math")
    Call<String> randomTrivia();
}
