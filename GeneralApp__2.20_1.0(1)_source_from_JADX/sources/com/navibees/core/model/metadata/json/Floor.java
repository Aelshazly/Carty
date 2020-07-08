package com.navibees.core.model.metadata.json;

import org.json.JSONException;
import org.json.JSONObject;

public class Floor {
    public String basemap;
    public String buildingId;
    public int floorIndex;

    /* renamed from: id */
    public String f1331id;
    public Text name;
    public String updatedAt;

    public Floor() {
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.f1331id);
            jSONObject.put("name", this.name.toJson());
            jSONObject.put("floor_index", this.floorIndex);
            if (this.basemap != null) {
                jSONObject.put("basemap", this.basemap);
            }
            if (this.updatedAt != null) {
                jSONObject.put("updated_at", this.updatedAt);
            }
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Floor(JSONObject jSONObject) throws JSONException {
        String str = "id";
        if (!jSONObject.isNull(str)) {
            String str2 = "name";
            if (!jSONObject.isNull(str2)) {
                String str3 = "floor_index";
                if (!jSONObject.isNull(str3)) {
                    this.f1331id = jSONObject.getString(str);
                    this.name = new Text(jSONObject.getJSONObject(str2));
                    this.floorIndex = jSONObject.getInt(str3);
                    String str4 = "basemap";
                    if (!jSONObject.isNull(str4)) {
                        this.basemap = jSONObject.optString(str4);
                    }
                    String str5 = "updated_at";
                    if (!jSONObject.isNull(str5)) {
                        this.updatedAt = jSONObject.optString(str5);
                        return;
                    }
                    return;
                }
            }
        }
        throw new JSONException("Floor must contains \"id\", \"name\" and \"floor_index\" keys with NON NULL value");
    }
}
