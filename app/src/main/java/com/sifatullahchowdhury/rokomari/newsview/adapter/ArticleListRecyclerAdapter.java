package com.sifatullahchowdhury.rokomari.newsview.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.sifatullahchowdhury.rokomari.newsview.R;
import com.sifatullahchowdhury.rokomari.newsview.model.Article;

import java.util.List;

/**
 * Created by Sifat Ullah on 2/18/2019.
 */

public class ArticleListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private List<Article> articles;
    private Context mContext;

    public ArticleListRecyclerAdapter(List<Article> articles, Context mContext) {
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
        RelativeLayout background;

        ViewHolder(View itemView) {
            super(itemView);

            articleTitle =itemView.findViewById(R.id.article_title);
            publishedDate =itemView.findViewById(R.id.article_date);
            author =itemView.findViewById(R.id.article_author);
            articleImg = itemView.findViewById(R.id.article_img);
            source=itemView.findViewById(R.id.article_source);
            desc=itemView.findViewById(R.id.article_desc);
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
}
