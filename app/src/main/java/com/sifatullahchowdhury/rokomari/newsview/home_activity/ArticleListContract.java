package com.sifatullahchowdhury.rokomari.newsview.home_activity;


import com.sifatullahchowdhury.rokomari.newsview.model.Article;

import java.util.List;


/**
 * Created by Sifat Ullah on 2/18/2019.
 */

public interface ArticleListContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Article> articles);

            void onFailure(Throwable t);
        }

        void getLatestArticlesOfCountry(OnFinishedListener onFinishedListener, String countryCode);

        void getLatestArticlesOfSource(OnFinishedListener onFinishedListener, String source);

        void getArticlesOfTopic(OnFinishedListener onFinishedListener, String topic);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Article> articles);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();


        void requestDataFromServer(int type,String param);

    }
}
