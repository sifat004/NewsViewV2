package com.sifatullahchowdhury.rokomari.newsview.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sifat Ullah on 2/18/2019.
 */

public class ArticleListResponse {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("totalResults")
    @Expose
    public Integer totalResults;
    @SerializedName("articles")
    @Expose
    public List<Article> articles = null;

    public String getStatus() {
        return status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}