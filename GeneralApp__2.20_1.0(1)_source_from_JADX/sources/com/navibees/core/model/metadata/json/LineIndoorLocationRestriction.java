package com.navibees.core.model.metadata.json;

import com.navibees.core.model.postioning.C1697c;
import org.json.JSONException;
import org.json.JSONObject;

public class LineIndoorLocationRestriction extends IndoorLocationRestriction {
    public IndoorLocation end;
    public IndoorLocation start;

    public LineIndoorLocationRestriction(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        this.start = new IndoorLocation(jSONObject.getJSONObject("start_point"));
        this.end = new IndoorLocation(jSONObject.getJSONObject("end_point"));
    }

    public double calculateDistance(IndoorLocation indoorLocation) {
        return C1697c.m1052b(this, indoorLocation);
    }

    public IndoorLocation calculateNewCoordinates(IndoorLocation indoorLocation) {
        IndoorLocation a = C1697c.m1045a(this, indoorLocation);
        return C1697c.m1065d(this, a) ? a : indoorLocation;
    }

    public boolean isInside(IndoorLocation indoorLocation) {
        return C1697c.m1065d(this, indoorLocation);
    }

    public double[] isInsideWithinRange(IndoorLocation indoorLocation) {
        return C1697c.m1048a(this, indoorLocation, 4.0d);
    }

    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            json.put("type", "line");
            json.put("start_point", this.start.toJson());
            json.put("end_point", this.end.toJson());
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LineIndoorLocationRestriction(String str, int i, IndoorLocation indoorLocation, IndoorLocation indoorLocation2) {
        this.f1334id = str;
        this.floor = i;
        this.start = indoorLocation;
        this.end = indoorLocation2;
    }

    public LineIndoorLocationRestriction() {
    }
}
