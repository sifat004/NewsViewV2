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
    public void requestDataFromServer(int type,String param) {

        if (articleListView != null) {
            articleListView.showProgress();
        }


        if (type==Utility.NEWS_OF_COUNTRY)
        articleListModel.getLatestArticlesOfCountry(this, param);

        else if (type==Utility.NEWS_OF_SOURCE)
        articleListModel.getLatestArticlesOfSource(this, param);

        else if (type==Utility.NEWS_OF_TOPIC)
            articleListModel.getArticlesOfTopic(this, param);

        else articleListModel.getLatestArticlesOfCountry(this, Utility.getStringResource(R.string.US));


    }
}
