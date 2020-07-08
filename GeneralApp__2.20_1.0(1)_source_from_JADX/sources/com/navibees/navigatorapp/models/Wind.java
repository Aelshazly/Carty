package com.navibees.navigatorapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("deg")
    @Expose
    public int deg;
    @SerializedName("speed")
    @Expose
    public double speed;
}
