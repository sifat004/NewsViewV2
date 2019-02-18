package com.sifatullahchowdhury.rokomari.newsview.home_activity;

import android.util.Log;


import com.sifatullahchowdhury.rokomari.newsview.model.Article;
import com.sifatullahchowdhury.rokomari.newsview.model.ArticleListResponse;
import com.sifatullahchowdhury.rokomari.newsview.network.ApiClient;
import com.sifatullahchowdhury.rokomari.newsview.network.ApiInterface;
import com.sifatullahchowdhury.rokomari.newsview.utility.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sifat Ullah on 2/18/2019.
 */


public class ArticleListModel implements ArticleListContract.Model {
    private final String TAG = "ArticleListModel";

    @Override
    public void getLatestArticlesOfCountry(final OnFinishedListener onFinishedListener, String countryCode) {

        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);

        Call<ArticleListResponse> latestArticles = apiInterface.getLatestNewsOfCountry(Utility.NEWS_API_KEY,countryCode);

        latestArticles.enqueue(new Callback<ArticleListResponse>() {
            @Override
            public void onResponse(Call<ArticleListResponse> call, Response<ArticleListResponse> response) {

                List<Article> articles= response.body().getArticles();
                Log.d(TAG, "Number of articles received: " + articles.size());
                onFinishedListener.onFinished(articles);
            }

            @Override
            public void onFailure(Call<ArticleListResponse> call, Throwable t) {

                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);

            }
        });

    }

    @Override
    public void getLatestArticlesOfSource(final OnFinishedListener onFinishedListener, String source) {

        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);

        Call<ArticleListResponse> latestArticles = apiInterface.getLatestNewsOfSource(Utility.NEWS_API_KEY,source);

        latestArticles.enqueue(new Callback<ArticleListResponse>() {
            @Override
            public void onResponse(Call<ArticleListResponse> call, Response<ArticleListResponse> response) {

                List<Article> articles= response.body().getArticles();
                Log.d(TAG, "Number of articles received: " + articles.size());
                onFinishedListener.onFinished(articles);
            }

            @Override
            public void onFailure(Call<ArticleListResponse> call, Throwable t) {

                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);

            }
        });

    }

    @Override
    public void getArticlesOfTopic(final OnFinishedListener onFinishedListener, String topic) {

        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);

        Call<ArticleListResponse> latestArticles = apiInterface.getEverythingAboutTopic(Utility.NEWS_API_KEY,topic);

        latestArticles.enqueue(new Callback<ArticleListResponse>() {
            @Override
            public void onResponse(Call<ArticleListResponse> call, Response<ArticleListResponse> response) {

                List<Article> articles= response.body().getArticles();
                Log.d(TAG, "Number of articles received: " + articles.size());
                onFinishedListener.onFinished(articles);
            }

            @Override
            public void onFailure(Call<ArticleListResponse> call, Throwable t) {

                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);

            }
        });

    }
}
