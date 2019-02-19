package com.sifatullahchowdhury.rokomari.newsview.trivia_activity;

import android.util.Log;

import com.sifatullahchowdhury.rokomari.newsview.R;
import com.sifatullahchowdhury.rokomari.newsview.home_activity.ArticleListContract;
import com.sifatullahchowdhury.rokomari.newsview.home_activity.ArticleListModel;
import com.sifatullahchowdhury.rokomari.newsview.model.Article;
import com.sifatullahchowdhury.rokomari.newsview.utility.Utility;

import java.util.List;

/**
 * Created by Sifat Ullah on 2/18/2019.
 */

public class TriviaPresenter implements TriviaContract.Presenter,TriviaContract.Model.OnTriviaFetchedListener{

    private TriviaContract.View view;

    private TriviaContract.Model model;

    public TriviaPresenter(TriviaContract.View view) {

        this.view=view;
        model=new TriviaModel();
    }



    @Override
    public void onFetched(String trivia) {

        view.showTrivia(trivia);
        view.hideProgress();


    }

    @Override
    public void onFailure(Throwable t) {


        view.onResponseFailure(t);
        view.hideProgress();

    }

    @Override
    public void onDestroy() {

        this.view = null;
    }

    @Override
    public void requestDataFromServer() {

        view.showProgress();
        model.getRandomTrivia(this);
    }

    @Override
    public void requestDataFromServer(int number) {
        view.showProgress();
        model.getTriviaFromNumber(this,number);

    }

    @Override
    public void requestDataFromServer(int month, int date) {

        view.showProgress();
        model.getTriviaFromDate(this,month,date);
    }



}
