package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class POICategory implements Parcelable {
    public static final Creator<POICategory> CREATOR = new Creator<POICategory>() {
        public POICategory createFromParcel(Parcel parcel) {
            return new POICategory(parcel);
        }

        public POICategory[] newArray(int i) {
            return new POICategory[i];
        }
    };
    private static final String JSON_KEY_DEFAULT = "default";
    private static final String JSON_KEY_DESCRIPTION = "description";
    private static final String JSON_KEY_ID = "id";
    private static final String JSON_KEY_IMAGES = "images";
    private static final String JSON_KEY_LOGO = "logo";
    private static final String JSON_KEY_NAME = "name";
    private static final String JSON_KEY_PROPERTIES = "properties";
    public String defaultString;
    public Text description;

    /* renamed from: id */
    public String f1340id;
    public List<Image> images;
    public Image logo;
    public Text name;
    public HashMap<String, String> properties;

    public POICategory(JSONObject jSONObject) throws JSONException {
        String str = JSON_KEY_ID;
        if (!jSONObject.isNull(str)) {
            this.f1340id = jSONObject.getString(str);
            String str2 = "name";
            if (!jSONObject.isNull(str2)) {
                this.name = new Text(jSONObject.getJSONObject(str2));
                String str3 = JSON_KEY_DEFAULT;
                if (!jSONObject.isNull(str3)) {
                    this.defaultString = jSONObject.optString(str3, null);
                }
                String str4 = JSON_KEY_DESCRIPTION;
                if (!jSONObject.isNull(str4)) {
                    try {
                        this.description = new Text(jSONObject.optJSONObject(str4));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                String str5 = JSON_KEY_LOGO;
                if (!jSONObject.isNull(str5)) {
                    try {
                        this.logo = new Image(jSONObject.optJSONObject(str5));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                String str6 = JSON_KEY_IMAGES;
                if (!jSONObject.isNull(str6)) {
                    this.images = new ArrayList();
                    JSONArray optJSONArray = jSONObject.optJSONArray(str6);
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        try {
                            this.images.add(new Image(optJSONArray.getJSONObject(i)));
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
                String str7 = JSON_KEY_PROPERTIES;
                if (!jSONObject.isNull(str7)) {
                    this.properties = new HashMap<>();
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str7);
                    Iterator keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String str8 = (String) keys.next();
                        this.properties.put(str8, jSONObject2.getString(str8));
                    }
                    return;
                }
                return;
            }
            throw new JSONException("POICategory must contains \"name\" key with NON NULL value");
        }
        throw new JSONException("POICategory must contains \"id\" key with NON NULL value");
    }

    public int describeContents() {
        return 0;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_KEY_ID, this.f1340id);
            jSONObject.put("name", this.name.toJson());
            if (this.defaultString != null) {
                jSONObject.put(JSON_KEY_DEFAULT, this.defaultString);
            }
            if (this.description != null) {
                jSONObject.put(JSON_KEY_DESCRIPTION, this.description.toJson());
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
            if (this.properties != null) {
                jSONObject.put(JSON_KEY_PROPERTIES, new JSONObject(this.properties));
            }
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1340id);
        parcel.writeValue(this.name);
        parcel.writeString(this.defaultString);
        parcel.writeValue(this.description);
        parcel.writeValue(this.logo);
        if (this.images == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeList(this.images);
    }

    protected POICategory(Parcel parcel) {
        this.f1340id = parcel.readString();
        this.name = (Text) parcel.readValue(Text.class.getClassLoader());
        this.defaultString = parcel.readString();
        this.description = (Text) parcel.readValue(Text.class.getClassLoader());
        this.logo = (Image) parcel.readValue(Image.class.getClassLoader());
        if (parcel.readByte() == 1) {
            this.images = new ArrayList();
            parcel.readList(this.images, Image.class.getClassLoader());
            return;
        }
        this.images = null;
    }
}
