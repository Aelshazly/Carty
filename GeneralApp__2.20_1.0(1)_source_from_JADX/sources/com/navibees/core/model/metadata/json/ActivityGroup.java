package com.navibees.core.model.metadata.json;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityGroup {
    private static String JSON_KEY_ACTIVITIES = "activities";
    private static String JSON_KEY_ID = "id";
    private static String JSON_KEY_NAME = "name";
    public List<Activity> activities;

    /* renamed from: id */
    public String f1326id;
    public Text name;

    public ActivityGroup(JSONObject jSONObject) throws JSONException {
        if (jSONObject.isNull(JSON_KEY_NAME) || jSONObject.isNull(JSON_KEY_ID)) {
            StringBuilder sb = new StringBuilder();
            sb.append("activityGroup must contains ");
            sb.append(JSON_KEY_NAME);
            sb.append(", ");
            sb.append(JSON_KEY_ID);
            sb.append("keys with NON NULL values");
            throw new JSONException(sb.toString());
        }
        this.f1326id = jSONObject.getString(JSON_KEY_ID);
        this.name = new Text(jSONObject.getJSONObject(JSON_KEY_NAME));
        if (jSONObject.optJSONArray(JSON_KEY_ACTIVITIES) != null) {
            JSONArray jSONArray = jSONObject.getJSONArray(JSON_KEY_ACTIVITIES);
            this.activities = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    this.activities.add(new Activity(jSONArray.getJSONObject(i)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_KEY_ID, this.f1326id);
            jSONObject.put(JSON_KEY_NAME, this.name.toJson());
            if (this.activities != null) {
                JSONArray jSONArray = new JSONArray();
                for (Activity json : this.activities) {
                    JSONObject json2 = json.toJson();
                    if (json2 != null) {
                        jSONArray.put(json2);
                    }
                }
                jSONObject.put(JSON_KEY_ACTIVITIES, jSONArray);
            }
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
