package com.navibees.core.model.metadata.json;

import com.navibees.core.model.postioning.C1697c;
import org.json.JSONException;
import org.json.JSONObject;

public class OutdoorRegion {
    private static final String JSON_KEY_CENTER = "center";
    private static final String JSON_KEY_CIRCLE_POINT = "circle_point";
    private IndoorLocation center;
    private IndoorLocation circlePoint;

    public OutdoorRegion(JSONObject jSONObject) throws JSONException {
        this.center = new IndoorLocation(jSONObject.getJSONObject(JSON_KEY_CENTER));
        this.circlePoint = new IndoorLocation(jSONObject.getJSONObject(JSON_KEY_CIRCLE_POINT));
    }

    public boolean contains(IndoorLocation indoorLocation) {
        return C1697c.m1064d(this.center, indoorLocation) <= C1697c.m1064d(this.center, this.circlePoint);
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_KEY_CENTER, this.center.toJson());
            jSONObject.put(JSON_KEY_CIRCLE_POINT, this.circlePoint.toJson());
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
