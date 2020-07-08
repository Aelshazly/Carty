package com.navibees.core.model.metadata.json;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Brand {
    private static String JSON_KEY_BRAND_URL = "url";
    private static String JSON_KEY_DESCRIPTION = "description";
    private static String JSON_KEY_ID = "id";
    private static String JSON_KEY_IMAGES = "images";
    private static String JSON_KEY_LOGO = "logo";
    private static String JSON_KEY_NAME = "display_name";
    public String brandId;
    public String brandUrl;
    public Text descriptionText;
    public List<Image> images;
    public Image logo;
    public Text name;

    public Brand(JSONObject jSONObject) throws JSONException {
        if (jSONObject.isNull(JSON_KEY_ID) || jSONObject.isNull(JSON_KEY_NAME)) {
            throw new JSONException("Brand must contain \"id\" and \"name\" key with NON NULL value");
        }
        this.brandId = jSONObject.getString(JSON_KEY_ID);
        this.name = new Text(jSONObject.getJSONObject(JSON_KEY_NAME));
        if (!jSONObject.isNull(JSON_KEY_DESCRIPTION)) {
            try {
                this.descriptionText = new Text(jSONObject.optJSONObject(JSON_KEY_DESCRIPTION));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!jSONObject.isNull(JSON_KEY_LOGO)) {
            try {
                this.logo = new Image(jSONObject.optJSONObject(JSON_KEY_LOGO));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (!jSONObject.isNull(JSON_KEY_IMAGES)) {
            this.images = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_IMAGES);
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    this.images.add(new Image(optJSONArray.getJSONObject(i)));
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (!jSONObject.isNull(JSON_KEY_BRAND_URL)) {
            this.brandUrl = jSONObject.optString(JSON_KEY_BRAND_URL);
        }
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_KEY_ID, this.brandId);
            jSONObject.put(JSON_KEY_NAME, this.name.toJson());
            if (!(this.descriptionText == null || this.descriptionText.toJson() == null)) {
                jSONObject.put(JSON_KEY_DESCRIPTION, this.descriptionText.toJson());
            }
            if (this.logo != null) {
                jSONObject.put(JSON_KEY_LOGO, this.logo.toJson());
            }
            if (this.images != null) {
                JSONArray jSONArray = new JSONArray();
                for (Image json : this.images) {
                    jSONArray.put(json.toJson());
                }
                jSONObject.put(JSON_KEY_IMAGES, jSONArray);
            }
            if (this.brandUrl != null) {
                jSONObject.put(JSON_KEY_BRAND_URL, this.brandUrl);
            }
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
