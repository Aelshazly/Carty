package com.navibees.core.model.metadata.json;

import org.json.JSONException;
import org.json.JSONObject;

public class EsriFloor extends Floor {
    public String geodatabase;
    public String networkDataset;
    public String tilePackage;

    public EsriFloor(Text text, String str) {
        this.name = text;
        this.basemap = str;
    }

    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("geodatabase", this.geodatabase);
            jSONObject.put("networkDataset", this.networkDataset);
            jSONObject.put("tilePackage", this.tilePackage);
            json.put("meta_data", jSONObject);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public EsriFloor(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("meta_data");
        this.geodatabase = jSONObject2.getString("geodatabase");
        this.networkDataset = jSONObject2.getString("networkDataset");
        this.tilePackage = jSONObject2.getString("tilePackage");
    }
}
