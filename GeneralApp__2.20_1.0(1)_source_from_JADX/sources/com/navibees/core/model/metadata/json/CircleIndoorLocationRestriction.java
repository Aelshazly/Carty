package com.navibees.core.model.metadata.json;

import com.navibees.core.model.postioning.C1697c;
import org.json.JSONException;
import org.json.JSONObject;

public class CircleIndoorLocationRestriction extends IndoorLocationRestriction {
    public IndoorLocation center;
    public double radius;

    public CircleIndoorLocationRestriction(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        this.center = new IndoorLocation(jSONObject.getJSONObject("center"));
        this.radius = jSONObject.getDouble("radius");
    }

    public double calculateDistance(IndoorLocation indoorLocation) {
        return C1697c.m1049b(this, indoorLocation);
    }

    public IndoorLocation calculateNewCoordinates(IndoorLocation indoorLocation) {
        return C1697c.m1043a(this, indoorLocation);
    }

    public boolean isInside(IndoorLocation indoorLocation) {
        return C1697c.m1062c(this, indoorLocation);
    }

    public double[] isInsideWithinRange(IndoorLocation indoorLocation) {
        return new double[]{0.0d, 0.0d};
    }

    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            if (json == null) {
                return null;
            }
            json.put("center", this.center);
            json.put("radius", this.radius);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
