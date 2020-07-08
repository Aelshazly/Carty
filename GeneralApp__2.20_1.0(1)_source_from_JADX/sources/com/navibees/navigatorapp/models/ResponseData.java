package com.navibees.navigatorapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseData {
    @SerializedName("Exhibitor")
    @Expose
    public List<Exhibitor> exhibitors;
    @SerializedName("Speakers")
    @Expose
    public List<Speaker> speakers;
    @SerializedName("Sponser")
    @Expose
    public List<Sponsor> sponsers;
}
