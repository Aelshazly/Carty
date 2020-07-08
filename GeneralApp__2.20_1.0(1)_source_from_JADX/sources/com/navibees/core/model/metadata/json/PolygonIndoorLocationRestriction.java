package com.navibees.core.model.metadata.json;

import com.navibees.core.model.postioning.C1697c;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PolygonIndoorLocationRestriction extends IndoorLocationRestriction {
    public ArrayList<IndoorLocation> vertices = new ArrayList<>();

    public PolygonIndoorLocationRestriction(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        JSONArray jSONArray = jSONObject.getJSONArray("points");
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                this.vertices.add(new IndoorLocation(jSONArray.getJSONObject(i)));
            } catch (Exception e) {
            }
        }
    }

    public double calculateDistance(IndoorLocation indoorLocation) {
        return C1697c.m1053b(this, indoorLocation);
    }

    public IndoorLocation calculateNewCoordinates(IndoorLocation indoorLocation) {
        return C1697c.m1046a(this, indoorLocation);
    }

    public boolean isInside(IndoorLocation indoorLocation) {
        return C1697c.m1063c(this, indoorLocation);
    }

    public double[] isInsideWithinRange(IndoorLocation indoorLocation) {
        return new double[]{0.0d, 0.0d};
    }

    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            JSONArray jSONArray = new JSONArray();
            Iterator it = this.vertices.iterator();
            while (it.hasNext()) {
                JSONObject json2 = ((IndoorLocation) it.next()).toJson();
                if (json2 != null) {
                    jSONArray.put(json2);
                }
            }
            json.put("points", jSONArray);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
