package com.navibees.core.model.metadata.json;

import org.json.JSONException;
import org.json.JSONObject;

public class RecentSelectedObject {
    public static final int FACILITY_TYPE = 2;
    public static final int POI_TYPE = 1;

    /* renamed from: id */
    private String f1341id;
    private int type;

    public RecentSelectedObject(JSONObject jSONObject) throws JSONException {
        this.type = jSONObject.getInt("type");
        this.f1341id = jSONObject.getString("id");
    }

    public String getId() {
        return this.f1341id;
    }

    public int getType() {
        return this.type;
    }

    public void setId(String str) {
        this.f1341id = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", this.type);
            jSONObject.put("id", this.f1341id);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RecentSelectedObject() {
    }
}
