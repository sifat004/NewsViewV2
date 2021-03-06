package com.sifatullahchowdhury.rokomari.newsview.home_activity;

import android.util.Log;

import com.sifatullahchowdhury.rokomari.newsview.R;
import com.sifatullahchowdhury.rokomari.newsview.model.Article;
import com.sifatullahchowdhury.rokomari.newsview.utility.Utility;

import java.util.List;

/**
 * Created by Sifat Ullah on 2/18/2019.
 */

public class ArticleListPresenter implements ArticleListContract.Presenter,ArticleListContract.Model.OnFinishedListener{

    private ArticleListContract.View articleListView;

    private ArticleListContract.Model articleListModel;

    public ArticleListPresenter(ArticleListContract.View articleListView) {
        this.articleListView = articleListView;
        articleListModel = new ArticleListModel();
    }

    @Override
    public void onFinished(List<Article> articles) {

        Log.e("presenter", String.valueOf(articles.size()));
        articleListView.setDataToRecyclerView(articles);
        if (articleListView != null) {
            articleListView.hideProgress();
        }

    }

    @Override
    public void onFailure(Throwable t) {

        articleListView.onResponseFailure(t);
        if (articleListView != null) {
            articleListView.hideProgress();
        }

    }

    @Override
    public void onDestroy() {

        this.articleListView = null;
    }

    @Override
    public void requestNewsAboutCountry(String param) {

        articleListView.showProgress();
        articleListModel.getLatestArticlesOfCountry(this, param);

    }

    @Override
    public void requestNewsFromSpecificSource(String param) {
        articleListView.showProgress();

        articleListModel.getLatestArticlesOfSource(this, param);

    }

    @Override
    public void requestNewsOnSpecificTopic(String param) {

        articleListView.showProgress();

        articleListModel.getArticlesOfTopic(this, param);

    }



}
