package com.navibees.navigatorapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherDetails {
    @SerializedName("base")
    @Expose
    public String base;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("cod")
    @Expose
    public int cod;
    @SerializedName("coord")
    @Expose
    public Coord coord;
    @SerializedName("dt")
    @Expose

    /* renamed from: dt */
    public int f211dt;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    public int f212id;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("visibility")
    @Expose
    public int visibility;
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;
    @SerializedName("wind")
    @Expose
    public Wind wind;
}
