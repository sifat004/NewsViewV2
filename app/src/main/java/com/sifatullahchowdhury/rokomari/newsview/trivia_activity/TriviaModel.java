package com.sifatullahchowdhury.rokomari.newsview.trivia_activity;


import android.util.Log;

import com.sifatullahchowdhury.rokomari.newsview.network.ApiClient;

import com.sifatullahchowdhury.rokomari.newsview.network.NumbersApiInterface;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sifat Ullah on 2/19/2019.
 */

public class TriviaModel implements TriviaContract.Model {
    @Override
    public void getTriviaFromNumber(final OnTriviaFetchedListener onTriviaFetchedListener, int num) {

        NumbersApiInterface numbersApiInterface=ApiClient.getNumbersApiClient().create(NumbersApiInterface.class);
        Call<String> trivia = numbersApiInterface.number(num);

        trivia.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String trivia= response.body();
                Log.e("Trivia",trivia);
                onTriviaFetchedListener.onFetched(trivia);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                onTriviaFetchedListener.onFailure(t);
            }


        });


    }

    @Override
    public void getTriviaFromDate(final OnTriviaFetchedListener onTriviaFetchedListener, int month, int date) {

        NumbersApiInterface numbersApiInterface=ApiClient.getNumbersApiClient().create(NumbersApiInterface.class);
        Call<String> trivia = numbersApiInterface.date(month,date);

        trivia.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String trivia= response.body();
                onTriviaFetchedListener.onFetched(trivia);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                onTriviaFetchedListener.onFailure(t);
            }


        });
    }

    @Override
    public void getRandomTrivia(final OnTriviaFetchedListener onTriviaFetchedListener) {


        NumbersApiInterface numbersApiInterface=ApiClient.getNumbersApiClient().create(NumbersApiInterface.class);
        Call<String> trivia = numbersApiInterface.randomTrivia();

        trivia.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String trivia= response.body();
                onTriviaFetchedListener.onFetched(trivia);
                Log.e("Trivia",response.toString());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                onTriviaFetchedListener.onFailure(t);
                Log.e("Trivia_error",t.toString());

            }


        });

    }
}
