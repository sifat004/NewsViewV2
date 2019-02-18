package com.sifatullahchowdhury.rokomari.newsview.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sifat Ullah on 2/18/2019.
 */

public class Source {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}