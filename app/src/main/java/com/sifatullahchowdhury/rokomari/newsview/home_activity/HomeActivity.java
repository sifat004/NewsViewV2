package com.sifatullahchowdhury.rokomari.newsview.home_activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.sifatullahchowdhury.rokomari.newsview.R;
import com.sifatullahchowdhury.rokomari.newsview.adapter.ArticleListRecyclerAdapter;
import com.sifatullahchowdhury.rokomari.newsview.model.Article;
import com.sifatullahchowdhury.rokomari.newsview.utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ArticleListContract.View,NavigationView.OnNavigationItemSelectedListener  {

    private static final String TAG = "HomeActivity";
    DrawerLayout mDrawerLayout;

    private ArticleListPresenter articleListPresenter;
    private RecyclerView rvArticles;
    private List<Article> articles;
    private ArticleListRecyclerAdapter rvAdapter;
    private ProgressBar pbLoading;


    private GridLayoutManager mLayoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       getSupportActionBar().setTitle(getString(R.string.latest_news));
        articles = new ArrayList<>();


        articleListPresenter = new ArticleListPresenter(this);

        articleListPresenter.requestDataFromServer();
    }

    private void initUI() {

        rvArticles = findViewById(R.id.rv_articles);

        rvAdapter = new ArticleListRecyclerAdapter( articles,this);

        mLayoutManager = new GridLayoutManager(this, 2);
        rvArticles.setLayoutManager(mLayoutManager);
        rvArticles.setItemAnimator(new DefaultItemAnimator());
        rvArticles.setAdapter(rvAdapter);
        pbLoading = findViewById(R.id.pb_loading);


    }




    @Override
    public void showProgress() {

//        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {

     //  pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Article> articles) {

        this.articles.addAll(articles);
        Log.e(TAG, String.valueOf(articles.size()));

        initUI();



    }

    @Override
    public void onResponseFailure(Throwable throwable) {

        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        articleListPresenter.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();


        if (id == R.id.nav_home) {

            return true;

        }

        else if (id==R.id.nav_about){
            mDrawerLayout.closeDrawers();
            Utility.aboutDialog(this);

            return true;


        }

        else if (id==R.id.nav_exit){
            try {
                mDrawerLayout.closeDrawers();
            }
            catch (Exception e){}

            finish();
            return true;

        }
        else
            return super.onOptionsItemSelected(menuItem);





    }


    void drawer() {
        mDrawerLayout =  findViewById(R.id.drawer_layout);
        NavigationView navigationView =  findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
