package com.sifatullahchowdhury.rokomari.newsview.home_activity;

import android.util.Log;


import com.sifatullahchowdhury.rokomari.newsview.model.Article;
import com.sifatullahchowdhury.rokomari.newsview.model.ArticleListResponse;
import com.sifatullahchowdhury.rokomari.newsview.network.ApiClient;
import com.sifatullahchowdhury.rokomari.newsview.network.NewsApiInterface;
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

        NewsApiInterface newsApiInterface = ApiClient.getNewsApiClient().create(NewsApiInterface.class);

        Call<ArticleListResponse> latestArticles = newsApiInterface.getLatestNewsOfCountry(countryCode,Utility.NEWS_API_KEY);

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

        NewsApiInterface newsApiInterface = ApiClient.getNewsApiClient().create(NewsApiInterface.class);

        Call<ArticleListResponse> latestArticles = newsApiInterface.getLatestNewsOfSource(source,Utility.NEWS_API_KEY);

        latestArticles.enqueue(new Callback<ArticleListResponse>() {
            @Override
            public void onResponse(Call<ArticleListResponse> call, Response<ArticleListResponse> response) {

                Log.e(TAG, "response " + response);

                List<Article> articles= response.body().getArticles();
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

        NewsApiInterface newsApiInterface = ApiClient.getNewsApiClient().create(NewsApiInterface.class);

        Call<ArticleListResponse> latestArticles = newsApiInterface.getEverythingAboutTopic(topic,Utility.NEWS_API_KEY);

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
