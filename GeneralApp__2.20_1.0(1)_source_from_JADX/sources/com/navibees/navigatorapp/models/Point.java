package com.navibees.navigatorapp.models;

import android.app.Application;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.util.NaviBeesUtils;

public class Point {
    @SerializedName("lat")
    @Expose
    public double lat;
    @SerializedName("lng")
    @Expose
    public double lng;

    public Point(double lat2, double lng2) {
        this.lat = lat2;
        this.lng = lng2;
    }

    public LatLng getLatLng(Application application) {
        IndoorLocation location = new IndoorLocation(this.lat, this.lng);
        location.floor = 0;
        IndoorLocation location2 = NaviBeesUtils.convertUTMToLatLong(location);
        return new LatLng(location2.f1332x, location2.f1333y);
    }

    public LatLng getLatLng() {
        return new LatLng(this.lat, this.lng);
    }
}
