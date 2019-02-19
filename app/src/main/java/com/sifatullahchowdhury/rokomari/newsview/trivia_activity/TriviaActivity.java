package com.sifatullahchowdhury.rokomari.newsview.trivia_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sifatullahchowdhury.rokomari.newsview.R;

public class TriviaActivity extends AppCompatActivity implements TriviaContract.View {

    private ProgressBar progressBar;
    private TextView tv_trivia;
    private TriviaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        initUi();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    void initUi(){


        progressBar=findViewById(R.id.pb_loading);
        tv_trivia=findViewById(R.id.tv_trivia);
    }

    void loadData(){

        presenter=new TriviaPresenter(this);

        if (getIntent().hasExtra("number"))

        presenter.requestDataFromServer(getIntent().getIntExtra("number",0));

        else if (getIntent().hasExtra("date") && getIntent().hasExtra("month"))
            presenter.requestDataFromServer(getIntent().getIntExtra("month",0),getIntent().getIntExtra("date",0));


        else presenter.requestDataFromServer();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        tv_trivia.setVisibility(View.GONE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        tv_trivia.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTrivia(String trivia) {

        tv_trivia.setText(trivia);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
