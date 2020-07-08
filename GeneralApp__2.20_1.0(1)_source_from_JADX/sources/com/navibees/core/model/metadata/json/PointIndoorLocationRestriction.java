package com.navibees.core.model.metadata.json;

import com.navibees.core.model.postioning.C1697c;
import org.json.JSONException;
import org.json.JSONObject;

public class PointIndoorLocationRestriction extends IndoorLocationRestriction {
    public IndoorLocation point;

    public PointIndoorLocationRestriction(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        this.point = new IndoorLocation(jSONObject.getJSONObject("point"));
    }

    public double calculateDistance(IndoorLocation indoorLocation) {
        return C1697c.m1064d(this.point, indoorLocation);
    }

    public IndoorLocation calculateNewCoordinates(IndoorLocation indoorLocation) {
        return this.point;
    }

    public boolean isInside(IndoorLocation indoorLocation) {
        IndoorLocation indoorLocation2 = this.point;
        return indoorLocation2.f1332x == indoorLocation.f1332x && indoorLocation2.f1333y == indoorLocation.f1333y;
    }

    public double[] isInsideWithinRange(IndoorLocation indoorLocation) {
        return new double[]{0.0d, 0.0d};
    }

    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            json.put("point", this.point.toJson());
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
