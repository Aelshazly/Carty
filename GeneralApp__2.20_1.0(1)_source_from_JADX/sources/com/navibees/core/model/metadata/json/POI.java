package com.navibees.core.model.metadata.json;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty;
import com.navibees.core.util.NaviBeesUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class POI implements Parcelable {
    public static final Creator<POI> CREATOR = new Creator<POI>() {
        public POI createFromParcel(Parcel parcel) {
            return new POI(parcel);
        }

        public POI[] newArray(int i) {
            return new POI[i];
        }
    };
    private static final String JSON_KEY_DISPLAY_NAME = "display_name";
    private static String JSON_KEY_IMAGES = "images";
    private static String JSON_KEY_LOGO = "logo";
    private static String JSON_KEY_MAIN_BRAND_ID = "main_brand_id";
    private static final String JSON_KEY_NAME = "name";
    private static String JSON_KEY_PROPERTIES = "properties";
    private static String JSON_KEY_RELATED_BRAND_IDS = "related_brand_ids";
    public boolean active = true;
    public String buildingId;
    public String categoryId;
    public List<Contact> contacts;
    public Text description;

    /* renamed from: id */
    public String f1339id;
    public List<Image> images;
    public Image logo;
    public String mainBrandId;
    public Text name;
    public List<OpeningPeriod> openingPeriods;
    public HashMap<String, String> properties;
    public Text realName;
    public List<String> relatedBrandIds;
    public double routingDistance;
    public double routingDuration;
    public List<String> tags;

    public POI(JSONObject jSONObject) throws JSONException {
        String str = "id";
        if (!jSONObject.isNull(str)) {
            this.f1339id = jSONObject.getString(str);
            String str2 = JSON_KEY_DISPLAY_NAME;
            if (!jSONObject.isNull(str2)) {
                try {
                    this.name = new Text(jSONObject.getJSONObject(str2));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.name != null) {
                String str3 = "name";
                if (!jSONObject.isNull(str3)) {
                    try {
                        this.realName = new Text(jSONObject.getJSONObject(str3));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (!jSONObject.isNull(JSON_KEY_MAIN_BRAND_ID)) {
                    this.mainBrandId = jSONObject.optString(JSON_KEY_MAIN_BRAND_ID);
                }
                String str4 = "description";
                if (!jSONObject.isNull(str4)) {
                    try {
                        this.description = new Text(jSONObject.optJSONObject(str4));
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
                if (!jSONObject.isNull(JSON_KEY_LOGO)) {
                    try {
                        this.logo = new Image(jSONObject.getJSONObject(JSON_KEY_LOGO));
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
                if (!jSONObject.isNull(JSON_KEY_IMAGES)) {
                    this.images = new ArrayList();
                    JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_IMAGES);
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        try {
                            this.images.add(new Image(optJSONArray.getJSONObject(i)));
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                }
                String str5 = "category_id";
                if (!jSONObject.isNull(str5)) {
                    this.categoryId = jSONObject.getString(str5);
                }
                String str6 = "tags";
                if (jSONObject.optJSONArray(str6) != null) {
                    JSONArray jSONArray = jSONObject.getJSONArray(str6);
                    this.tags = new ArrayList();
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        if (!TextUtils.isEmpty(jSONArray.getString(i2))) {
                            this.tags.add(jSONArray.getString(i2));
                        }
                    }
                }
                String str7 = "contacts";
                if (jSONObject.optJSONArray(str7) != null) {
                    JSONArray jSONArray2 = jSONObject.getJSONArray(str7);
                    this.contacts = new ArrayList();
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        try {
                            this.contacts.add(new Contact(jSONArray2.getJSONObject(i3)));
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                    }
                }
                String str8 = "opening_periods";
                if (jSONObject.optJSONArray(str8) != null) {
                    JSONArray jSONArray3 = jSONObject.getJSONArray(str8);
                    this.openingPeriods = new ArrayList();
                    for (int i4 = 0; i4 < jSONArray3.length(); i4++) {
                        try {
                            this.openingPeriods.add(new OpeningPeriod(jSONArray3.getJSONObject(i4)));
                        } catch (Exception e7) {
                        }
                    }
                }
                String str9 = ConditionalUserProperty.ACTIVE;
                if (!jSONObject.isNull(str9)) {
                    this.active = jSONObject.optBoolean(str9);
                }
                if (!jSONObject.isNull(JSON_KEY_PROPERTIES)) {
                    this.properties = new HashMap<>();
                    JSONObject jSONObject2 = jSONObject.getJSONObject(JSON_KEY_PROPERTIES);
                    Iterator keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String str10 = (String) keys.next();
                        this.properties.put(str10, jSONObject2.getString(str10));
                    }
                }
                if (!jSONObject.isNull(JSON_KEY_RELATED_BRAND_IDS)) {
                    JSONArray optJSONArray2 = jSONObject.optJSONArray(JSON_KEY_RELATED_BRAND_IDS);
                    this.relatedBrandIds = new ArrayList();
                    for (int i5 = 0; i5 < optJSONArray2.length(); i5++) {
                        if (!optJSONArray2.isNull(i5)) {
                            String optString = optJSONArray2.optString(i5);
                            if (optString != null) {
                                this.relatedBrandIds.add(optString);
                            }
                        }
                    }
                    return;
                }
                return;
            }
            throw new JSONException("POI must contains \"display_name\" key with NON NULL value");
        }
        throw new JSONException("POI must contains \"id\" key with NON NULL value");
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof POI)) {
            return false;
        }
        return this.f1339id.equals(((POI) obj).f1339id);
    }

    public String getAdjustedName() {
        if (!NaviBeesUtils.isLangRTL()) {
            return this.name.getText();
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String text = this.name.getText();
        StringBuilder sb3 = sb;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i = 0; i < text.length(); i++) {
            Character valueOf = Character.valueOf(text.charAt(i));
            if (Character.isDigit(valueOf.charValue())) {
                sb3.append(valueOf);
                z = true;
                z2 = true;
            } else {
                if (sb3.length() > 0) {
                    sb2.append(sb3.reverse().toString());
                    sb3 = new StringBuilder();
                }
                sb2.append(valueOf);
                z = false;
                z3 = true;
            }
        }
        if (z) {
            sb2.append(sb3.reverse());
        }
        if (z2 && z3) {
            text = sb2.toString();
        }
        return text;
    }

    public String getDescProperty() {
        HashMap<String, String> hashMap = this.properties;
        String str = " - ";
        if (hashMap != null) {
            StringBuilder sb = new StringBuilder();
            String str2 = "desc-";
            sb.append(str2);
            sb.append(NaviBeesUtils.getAppLang());
            if (hashMap.get(sb.toString()) != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.name.getText());
                sb2.append(str);
                HashMap<String, String> hashMap2 = this.properties;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str2);
                sb3.append(NaviBeesUtils.getAppLang());
                sb2.append((String) hashMap2.get(sb3.toString()));
                return sb2.toString();
            }
        }
        HashMap<String, String> hashMap3 = this.properties;
        if (hashMap3 != null) {
            String str3 = "desc-en";
            if (hashMap3.get(str3) != null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(this.name.getText());
                sb4.append(str);
                sb4.append((String) this.properties.get(str3));
                return sb4.toString();
            }
        }
        return this.name.getText();
    }

    public Floor getPOIFloor(Application application) {
        return null;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.f1339id);
            jSONObject.put(JSON_KEY_DISPLAY_NAME, this.name.toJson());
            if (this.realName != null) {
                jSONObject.put("name", this.realName.toJson());
            }
            if (this.description != null) {
                jSONObject.put("description", this.description.toJson());
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
            jSONObject.put("category_id", this.categoryId);
            if (this.tags != null && !this.tags.isEmpty()) {
                JSONArray jSONArray2 = new JSONArray();
                for (String put : this.tags) {
                    jSONArray2.put(put);
                }
                jSONObject.put("tags", jSONArray2);
            }
            if (this.contacts != null && !this.contacts.isEmpty()) {
                JSONArray jSONArray3 = new JSONArray();
                for (Contact json2 : this.contacts) {
                    jSONArray3.put(json2.toJson());
                }
                jSONObject.put("contacts", jSONArray3);
            }
            if (this.openingPeriods != null && !this.openingPeriods.isEmpty()) {
                JSONArray jSONArray4 = new JSONArray();
                for (OpeningPeriod json3 : this.openingPeriods) {
                    jSONArray4.put(json3.toJson());
                }
                jSONObject.put("opening_periods", jSONArray4);
            }
            jSONObject.put(ConditionalUserProperty.ACTIVE, this.active);
            if (this.properties != null) {
                jSONObject.put(JSON_KEY_PROPERTIES, new JSONObject(this.properties));
            }
            if (this.mainBrandId != null) {
                jSONObject.put(JSON_KEY_MAIN_BRAND_ID, this.mainBrandId);
            }
            if (this.relatedBrandIds != null) {
                JSONArray jSONArray5 = new JSONArray();
                for (String put2 : this.relatedBrandIds) {
                    jSONArray5.put(put2);
                }
                jSONObject.put(JSON_KEY_RELATED_BRAND_IDS, jSONArray5);
            }
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return this.name.getText();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1339id);
        parcel.writeValue(this.name);
        parcel.writeValue(this.realName);
        parcel.writeValue(this.description);
        parcel.writeValue(this.logo);
        if (this.images == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.images);
        }
        parcel.writeString(this.categoryId);
        parcel.writeString(this.buildingId);
        if (this.tags == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.tags);
        }
        if (this.contacts == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.contacts);
        }
        if (this.openingPeriods == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.openingPeriods);
        }
        parcel.writeByte(this.active ? (byte) 1 : 0);
        if (this.properties == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeBundle(NaviBeesUtils.convertMapToBundle(this.properties));
        }
        parcel.writeString(this.mainBrandId);
        if (this.relatedBrandIds == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeList(this.relatedBrandIds);
    }

    protected POI(Parcel parcel) {
        this.f1339id = parcel.readString();
        this.name = (Text) parcel.readValue(Text.class.getClassLoader());
        this.realName = (Text) parcel.readValue(Text.class.getClassLoader());
        this.description = (Text) parcel.readValue(Text.class.getClassLoader());
        this.logo = (Image) parcel.readValue(Image.class.getClassLoader());
        if (parcel.readByte() == 1) {
            this.images = new ArrayList();
            parcel.readList(this.images, Image.class.getClassLoader());
        } else {
            this.images = null;
        }
        this.categoryId = parcel.readString();
        this.buildingId = parcel.readString();
        if (parcel.readByte() == 1) {
            this.tags = new ArrayList();
            parcel.readList(this.tags, String.class.getClassLoader());
        } else {
            this.tags = null;
        }
        if (parcel.readByte() == 1) {
            this.contacts = new ArrayList();
            parcel.readList(this.contacts, Contact.class.getClassLoader());
        } else {
            this.contacts = null;
        }
        if (parcel.readByte() == 1) {
            this.openingPeriods = new ArrayList();
            parcel.readList(this.openingPeriods, OpeningPeriod.class.getClassLoader());
        } else {
            this.openingPeriods = null;
        }
        this.active = parcel.readByte() != 0;
        if (parcel.readByte() == 1) {
            this.properties = NaviBeesUtils.convertBundleToMap(parcel.readBundle());
        } else {
            this.properties = null;
        }
        this.mainBrandId = parcel.readString();
        if (parcel.readByte() == 1) {
            this.relatedBrandIds = new ArrayList();
            parcel.readList(this.relatedBrandIds, String.class.getClassLoader());
            return;
        }
        this.relatedBrandIds = null;
    }
}
