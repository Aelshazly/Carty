package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.navibees.core.util.NaviBeesUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Facility implements Parcelable {
    public static final Creator<Facility> CREATOR = new Creator<Facility>() {
        public Facility createFromParcel(Parcel parcel) {
            return new Facility(parcel);
        }

        public Facility[] newArray(int i) {
            return new Facility[i];
        }
    };
    private static final String JSON_KEY_ACTIVE = "active";
    private static final String JSON_KEY_CATEGORY_ID = "category_id";
    private static final String JSON_KEY_DESCRIPTION = "description";
    private static final String JSON_KEY_ID = "id";
    private static final String JSON_KEY_IMAGES = "images";
    private static final String JSON_KEY_LOGO = "logo";
    private static final String JSON_KEY_NAME = "name";
    private static final String JSON_KEY_PROPERTIES = "properties";
    public boolean active;
    public String buildingId;
    public String categoryId;
    public Text description;

    /* renamed from: id */
    public String f1330id;
    public List<Image> images;
    public Image logo;
    public Text name;
    public HashMap<String, String> properties;

    public Facility() {
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Facility) && ((Facility) obj).f1330id.equals(this.f1330id);
    }

    public List<? extends POI> getPois() {
        return null;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_KEY_ID, this.f1330id);
            jSONObject.put("name", this.name.toJson());
            jSONObject.put(JSON_KEY_CATEGORY_ID, this.categoryId);
            if (this.description != null) {
                jSONObject.put(JSON_KEY_DESCRIPTION, this.description.toJson());
            }
            if (this.logo != null) {
                jSONObject.put(JSON_KEY_LOGO, this.logo.toJson());
            }
            if (this.images != null && !this.images.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (Image json : this.images) {
                    jSONArray.put(json.toJson());
                }
                jSONObject.put(JSON_KEY_IMAGES, jSONArray);
            }
            jSONObject.put("active", this.active);
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
        parcel.writeString(this.f1330id);
        parcel.writeValue(this.name);
        parcel.writeValue(this.description);
        parcel.writeValue(this.logo);
        if (this.images == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.images);
        }
        parcel.writeString(this.categoryId);
        parcel.writeByte(this.active ? (byte) 1 : 0);
        if (this.properties == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeBundle(NaviBeesUtils.convertMapToBundle(this.properties));
        }
        parcel.writeString(this.buildingId);
    }

    public Facility(JSONObject jSONObject) throws JSONException {
        String str = JSON_KEY_ID;
        if (!jSONObject.isNull(str)) {
            String str2 = "name";
            if (!jSONObject.isNull(str2)) {
                String str3 = JSON_KEY_CATEGORY_ID;
                if (!jSONObject.isNull(str3)) {
                    this.f1330id = jSONObject.getString(str);
                    this.name = new Text(jSONObject.getJSONObject(str2));
                    this.categoryId = jSONObject.getString(str3);
                    String str4 = JSON_KEY_DESCRIPTION;
                    if (!jSONObject.isNull(str4)) {
                        try {
                            this.description = new Text(jSONObject.getJSONObject(str4));
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
                    String str7 = "active";
                    if (!jSONObject.isNull(str7)) {
                        this.active = jSONObject.optBoolean(str7);
                    }
                    String str8 = JSON_KEY_PROPERTIES;
                    if (!jSONObject.isNull(str8)) {
                        this.properties = new HashMap<>();
                        JSONObject jSONObject2 = jSONObject.getJSONObject(str8);
                        Iterator keys = jSONObject2.keys();
                        while (keys.hasNext()) {
                            String str9 = (String) keys.next();
                            this.properties.put(str9, jSONObject2.getString(str9));
                        }
                        return;
                    }
                    return;
                }
            }
        }
        throw new JSONException("Facility must contains id, name, category_id key with NON NULL value");
    }

    protected Facility(Parcel parcel) {
        this.f1330id = parcel.readString();
        this.name = (Text) parcel.readValue(Text.class.getClassLoader());
        this.description = (Text) parcel.readValue(Text.class.getClassLoader());
        this.logo = (Image) parcel.readValue(Image.class.getClassLoader());
        if (parcel.readByte() == 1) {
            this.images = new ArrayList();
            parcel.readList(this.images, Image.class.getClassLoader());
        } else {
            this.images = null;
        }
        this.categoryId = parcel.readString();
        this.active = parcel.readByte() != 0;
        if (parcel.readByte() == 1) {
            this.properties = NaviBeesUtils.convertBundleToMap(parcel.readBundle());
        } else {
            this.properties = null;
        }
        this.buildingId = parcel.readString();
    }
}
