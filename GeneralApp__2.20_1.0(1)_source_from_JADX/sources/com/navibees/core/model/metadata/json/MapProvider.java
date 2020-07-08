package com.navibees.core.model.metadata.json;

import org.json.JSONException;
import org.json.JSONObject;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;

public class MapProvider {
    public static final int ESRI_TYPE = 0;
    public static final int VISIO_TYPE = 1;
    public String hashString;
    public int type;
    public String version;

    public MapProvider(JSONObject jSONObject) throws JSONException {
        this.type = jSONObject.getInt("type");
        String str = "hash";
        if (!jSONObject.isNull(str)) {
            this.hashString = jSONObject.optString(str);
        }
        String str2 = ClientCookie.VERSION_ATTR;
        if (!jSONObject.isNull(str2)) {
            this.version = jSONObject.optString(str2);
        }
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", this.type);
            if (this.hashString != null) {
                jSONObject.put("hash", this.hashString);
            }
            jSONObject.put(ClientCookie.VERSION_ATTR, this.version);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
