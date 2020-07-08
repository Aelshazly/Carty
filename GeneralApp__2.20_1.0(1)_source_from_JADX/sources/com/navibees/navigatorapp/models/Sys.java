package com.navibees.navigatorapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    public int f209id;
    @SerializedName("message")
    @Expose
    public double message;
    @SerializedName("sunrise")
    @Expose
    public int sunrise;
    @SerializedName("sunset")
    @Expose
    public int sunset;
    @SerializedName("type")
    @Expose
    public int type;
}
