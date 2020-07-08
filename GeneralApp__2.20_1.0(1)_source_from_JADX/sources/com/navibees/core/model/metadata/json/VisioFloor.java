package com.navibees.core.model.metadata.json;

import org.json.JSONException;
import org.json.JSONObject;

public class VisioFloor extends Floor {
    public String vgfloorId;

    public VisioFloor(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        this.vgfloorId = jSONObject.getString("provider_id");
    }

    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            json.put("provider_id", this.vgfloorId);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
