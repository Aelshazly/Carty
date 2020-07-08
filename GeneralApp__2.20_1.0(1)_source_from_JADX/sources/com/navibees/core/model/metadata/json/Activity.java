package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.navibees.core.util.NaviBeesUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Activity implements Parcelable {
    public static final Creator<Activity> CREATOR = new Creator<Activity>() {
        public Activity createFromParcel(Parcel parcel) {
            return new Activity(parcel);
        }

        public Activity[] newArray(int i) {
            return new Activity[i];
        }
    };
    private static String JSON_KEY_DESCRIPTION = "description";
    private static String JSON_KEY_END_DATE = "end_date";
    private static String JSON_KEY_ID = "_id";
    private static String JSON_KEY_IMAGES = "images";
    private static String JSON_KEY_LOGO = "logo";
    private static String JSON_KEY_NAME = "name";
    private static String JSON_KEY_OWNER = "owner";
    private static String JSON_KEY_POI_ID = "poi_id";
    private static String JSON_KEY_PROPERTIES = "properties";
    private static String JSON_KEY_START_DATE = "start_date";
    public Text description;
    public String endDate;
    public String groupId;

    /* renamed from: id */
    public String f1325id;
    public List<Image> images;
    public Image logo;
    public Text name;
    public Text owner;
    public String poiId;
    public HashMap<String, String> properties;
    public String startDate;

    public Activity(JSONObject jSONObject) throws JSONException {
        if (jSONObject.isNull(JSON_KEY_ID) || jSONObject.isNull(JSON_KEY_NAME) || jSONObject.isNull(JSON_KEY_POI_ID)) {
            StringBuilder sb = new StringBuilder();
            sb.append("activity must contains ");
            sb.append(JSON_KEY_ID);
            String str = ", ";
            sb.append(str);
            sb.append(JSON_KEY_NAME);
            sb.append(str);
            sb.append(JSON_KEY_POI_ID);
            sb.append("keys with NON Null value");
            throw new JSONException(sb.toString());
        }
        this.f1325id = jSONObject.getString(JSON_KEY_ID);
        this.name = new Text(jSONObject.getJSONObject(JSON_KEY_NAME));
        this.poiId = jSONObject.getString(JSON_KEY_POI_ID);
        this.startDate = jSONObject.optString(JSON_KEY_START_DATE);
        this.endDate = jSONObject.optString(JSON_KEY_END_DATE);
        if (!jSONObject.isNull(JSON_KEY_OWNER)) {
            try {
                this.owner = new Text(jSONObject.optJSONObject(JSON_KEY_OWNER));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!jSONObject.isNull(JSON_KEY_DESCRIPTION)) {
            try {
                this.description = new Text(jSONObject.optJSONObject(JSON_KEY_DESCRIPTION));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (!jSONObject.isNull(JSON_KEY_LOGO)) {
            try {
                this.logo = new Image(jSONObject.getJSONObject(JSON_KEY_LOGO));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        if (!jSONObject.isNull(JSON_KEY_IMAGES)) {
            this.images = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_IMAGES);
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    this.images.add(new Image(optJSONArray.getJSONObject(i)));
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
        if (!jSONObject.isNull(JSON_KEY_PROPERTIES)) {
            this.properties = new HashMap<>();
            JSONObject jSONObject2 = jSONObject.getJSONObject(JSON_KEY_PROPERTIES);
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                this.properties.put(str2, jSONObject2.getString(str2));
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean isHappeningNow() {
        boolean z = false;
        try {
            Date time = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", Locale.US);
            Date parse = simpleDateFormat.parse(this.startDate);
            Date parse2 = simpleDateFormat.parse(this.endDate);
            if (time.after(parse) && time.before(parse2)) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            return false;
        }
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_KEY_ID, this.f1325id);
            jSONObject.put(JSON_KEY_NAME, this.name.toJson());
            jSONObject.put(JSON_KEY_POI_ID, this.poiId);
            jSONObject.put(JSON_KEY_START_DATE, this.startDate);
            jSONObject.put(JSON_KEY_END_DATE, this.endDate);
            if (this.owner != null) {
                jSONObject.put(JSON_KEY_OWNER, this.owner.toJson());
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
        parcel.writeString(this.f1325id);
        parcel.writeValue(this.name);
        parcel.writeString(this.poiId);
        parcel.writeString(this.startDate);
        parcel.writeString(this.endDate);
        parcel.writeValue(this.owner);
        parcel.writeValue(this.description);
        parcel.writeValue(this.logo);
        if (this.images == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.images);
        }
        if (this.properties == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeBundle(NaviBeesUtils.convertMapToBundle(this.properties));
        }
        parcel.writeString(this.groupId);
    }

    private Activity() {
    }

    protected Activity(Parcel parcel) {
        this.f1325id = parcel.readString();
        this.name = (Text) parcel.readValue(Text.class.getClassLoader());
        this.poiId = parcel.readString();
        this.startDate = parcel.readString();
        this.endDate = parcel.readString();
        this.owner = (Text) parcel.readValue(Text.class.getClassLoader());
        this.description = (Text) parcel.readValue(Text.class.getClassLoader());
        this.logo = (Image) parcel.readValue(Image.class.getClassLoader());
        if (parcel.readByte() == 1) {
            this.images = new ArrayList();
            parcel.readList(this.images, Image.class.getClassLoader());
        } else {
            this.images = null;
        }
        if (parcel.readByte() == 1) {
            this.properties = NaviBeesUtils.convertBundleToMap(parcel.readBundle());
        } else {
            this.properties = null;
        }
        this.groupId = parcel.readString();
    }
}
