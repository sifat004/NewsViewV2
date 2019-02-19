package com.sifatullahchowdhury.rokomari.newsview.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.sifatullahchowdhury.rokomari.newsview.R;
import com.sifatullahchowdhury.rokomari.newsview.show_full_article_activity.ShowArticleActivity;
import com.sifatullahchowdhury.rokomari.newsview.model.Article;
import com.sifatullahchowdhury.rokomari.newsview.utility.Utility;

import java.util.List;

/**
 * Created by Sifat Ullah on 2/18/2019.
 */

public class ArticleCardRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private List<Article> articles;
    private Context mContext;

    public ArticleCardRecyclerAdapter(List<Article> articles, Context mContext) {
        this.articles = articles;
        this.mContext = mContext;
        Log.e("Adapter", String.valueOf(articles.size()));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.single_card, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder vh = (ViewHolder) viewHolder;
        final Article article= articles.get(i);
        vh.articleTitle.setText(article.getTitle());
        vh.publishedDate.setText(article.getPublishedAt());
        vh.author.setText(article.getAuthor());
        vh.source.setText(article.getSource().getName());
        vh.desc.setText(article.getDescription());
        Log.e("Adapter", String.valueOf(article.getTitle()));


        vh.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(article);
            }
        });
        Glide.with(mContext)
                        .load(article.getUrlToImage())
                        .into(vh.articleImg);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {





        TextView articleTitle, publishedDate, author,source,desc;
        ImageView articleImg;
        CardView background;

        ViewHolder(View itemView) {
            super(itemView);

            articleTitle =itemView.findViewById(R.id.tv_article_title);
            publishedDate =itemView.findViewById(R.id.tv_article_date);
            author =itemView.findViewById(R.id.tv_article_author);
            articleImg = itemView.findViewById(R.id.iv_article_img);
            source=itemView.findViewById(R.id.tv_article_source);
            desc=itemView.findViewById(R.id.tv_article_desc);
            background= itemView.findViewById(R.id.background);





        }


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void showPopup(final Article article) {
        final Dialog mDialog;
        mDialog=new Dialog(mContext);


        mDialog.setContentView(R.layout.custompopup);


        TextView articleTitle, publishedDate, author,source,desc;
        ImageView articleImg;

        Button yes,no;

        articleTitle =mDialog.findViewById(R.id.tv_article_title);
        publishedDate =mDialog.findViewById(R.id.tv_article_date);
        author =mDialog.findViewById(R.id.tv_article_author);
        articleImg = mDialog.findViewById(R.id.iv_article_img);
        source=mDialog.findViewById(R.id.tv_article_source);
        desc=mDialog.findViewById(R.id.tv_article_desc);
        yes=mDialog.findViewById(R.id.btn_yes);
        no=mDialog.findViewById(R.id.btn_no);


        articleTitle.setText(article.getTitle());
        publishedDate.setText(article.getPublishedAt());
        author.setText(article.getAuthor());
        source.setText(article.getSource().getName());
        desc.setText(article.getDescription());




        Glide.with(mContext)
                .load(article.getUrlToImage())
                .into(articleImg);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.dismiss();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
                mContext.startActivity(browserIntent);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.dismiss();
                Intent intent=new Intent(mContext, ShowArticleActivity.class);
                intent.putExtra(Utility.getStringResource(R.string.content_url),article.getUrl());
                mContext.startActivity(intent);

            }
        });

        mDialog.show();
    }
}
