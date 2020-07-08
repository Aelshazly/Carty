package com.navibees.navigatorapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OperationApplication {
    @SerializedName("admin_password")
    @Expose
    public String adminPassword;
    @SerializedName("admin_username")
    @Expose
    public String adminUsername;
    @SerializedName("ApiVer")
    @Expose
    public String apiVer;
    @SerializedName("venues")
    @Expose
    public List<Venue> venues = null;

    public OperationApplication(String JsonString) throws JSONException {
        try {
            JSONObject operationApplicationJson = new JSONObject(JsonString).getJSONObject("operation_application");
            this.apiVer = operationApplicationJson.getString("ApiVer");
            this.adminUsername = operationApplicationJson.getString("admin_username");
            this.adminPassword = operationApplicationJson.getString("admin_password");
            this.venues = new ArrayList();
            JSONArray venuesArray = operationApplicationJson.getJSONArray("venues");
            for (int i = 0; i < venuesArray.length(); i++) {
                this.venues.add(new Venue(venuesArray.getJSONObject(i)));
            }
            JSONObject venueJson = new JSONObject();
            venueJson.put("venue", "UQU");
            venueJson.put("user_id", "5");
            venueJson.put("user_name", "UQU_user1");
            venueJson.put("password", "1234");
            venueJson.put("app_id", "5be40f012f09bf696326173a");
            venueJson.put("authentication_token", "kRSfWFyyWz/gggGAhtapJwElFq4qkQShocQjkbT2x+hpRkqrJeiheyR2Ky0zsv0h0boP07487sM81B4cb1bpMw==");
            this.venues.add(new Venue(venueJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
