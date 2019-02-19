package com.sifatullahchowdhury.rokomari.newsview.show_full_article_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.sifatullahchowdhury.rokomari.newsview.R;

public class ShowArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_article);

        WebView webView=findViewById(R.id.wv_content);

        Intent intent=getIntent();
        String contentUrl=intent.getStringExtra(getString(R.string.content_url));

        webView.loadUrl(contentUrl);
    }
}
