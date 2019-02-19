package com.sifatullahchowdhury.rokomari.newsview.trivia_activity;


import com.sifatullahchowdhury.rokomari.newsview.model.Article;

import java.util.List;


/**
 * Created by Sifat Ullah on 2/18/2019.
 */

public interface TriviaContract {

    interface Model {

        interface OnTriviaFetchedListener {
            void onFetched(String trivia);

            void onFailure(Throwable t);
        }



        void getTriviaFromNumber(OnTriviaFetchedListener onTriviaFetchedListener,int num);
        void getTriviaFromDate(OnTriviaFetchedListener onTriviaFetchedListener,int month,int date);
        void getRandomTrivia(OnTriviaFetchedListener onTriviaFetchedListener);


    }

    interface View {

        void showProgress();

        void hideProgress();

        void showTrivia(String trivia);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();


        void requestDataFromServer();
        void requestDataFromServer(int number );
        void requestDataFromServer(int month, int date );

    }
}
