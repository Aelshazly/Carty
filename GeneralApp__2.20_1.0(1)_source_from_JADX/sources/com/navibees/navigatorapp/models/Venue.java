package com.navibees.navigatorapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.json.JSONException;
import org.json.JSONObject;

public class Venue {
    @SerializedName("app_id")
    @Expose
    public String appId;
    @SerializedName("authentication_token")
    @Expose
    public String authenticationToken;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("venue")
    @Expose
    public String venue;

    public Venue(JSONObject venueJson) throws JSONException {
        try {
            this.venue = venueJson.getString("venue");
            this.userId = venueJson.getString("user_id");
            this.userName = venueJson.getString("user_name");
            this.password = venueJson.getString("password");
            this.appId = venueJson.getString("app_id");
            this.authenticationToken = venueJson.getString("authentication_token");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
