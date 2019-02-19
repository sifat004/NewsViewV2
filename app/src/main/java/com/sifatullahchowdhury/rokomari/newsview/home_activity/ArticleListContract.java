package com.sifatullahchowdhury.rokomari.newsview.home_activity;


import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
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

        void loadArticles();

        void onResponseFailure(Throwable throwable);

        void finish();

        void signIn(GoogleSignInClient mGoogleSignInClient);


        void signOut();

    }

    interface Presenter {

        void onDestroy();


        void requestNewsAboutCountry(String param);
        void requestNewsFromSpecificSource(String param);
        void requestNewsOnSpecificTopic(String param);



    }
}
