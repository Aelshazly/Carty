package com.navibees.navigatorapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("humidity")
    @Expose
    public int humidity;
    @SerializedName("pressure")
    @Expose
    public int pressure;
    @SerializedName("temp")
    @Expose
    public double temp;
    @SerializedName("temp_max")
    @Expose
    public double tempMax;
    @SerializedName("temp_min")
    @Expose
    public double tempMin;
}
