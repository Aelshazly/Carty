package com.navibees.core.model.metadata.json;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class IndoorLocationRestriction {
    public int floor;

    /* renamed from: id */
    public String f1334id;

    IndoorLocationRestriction() {
    }

    public abstract double calculateDistance(IndoorLocation indoorLocation);

    public abstract IndoorLocation calculateNewCoordinates(IndoorLocation indoorLocation);

    public abstract boolean isInside(IndoorLocation indoorLocation);

    public abstract double[] isInsideWithinRange(IndoorLocation indoorLocation);

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.f1334id);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("floor_index", this.floor);
            jSONObject.put("floor", jSONObject2);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public IndoorLocationRestriction(JSONObject jSONObject) throws JSONException {
        this.f1334id = jSONObject.getString("id");
        this.floor = jSONObject.getJSONObject("floor").getInt("floor_index");
    }
}
