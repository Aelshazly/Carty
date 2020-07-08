package com.navibees.navigatorapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    public int f210id;
    @SerializedName("main")
    @Expose
    public String main;
}
