package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.navibees.core.util.NaviBeesUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Text implements Parcelable {
    public static final Creator<Text> CREATOR = new Creator<Text>() {
        public Text createFromParcel(Parcel parcel) {
            return new Text(parcel);
        }

        public Text[] newArray(int i) {
            return new Text[i];
        }
    };
    static final String ENGLISH = "en";
    private Map<String, String> langs = new HashMap();

    public Text(JSONObject jSONObject) throws JSONException {
        String str = ENGLISH;
        String str2 = "name must contains \"en\" key with NON NULL value, for:";
        if (jSONObject.isNull(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(jSONObject.toString());
            throw new JSONException(sb.toString());
        } else if (!TextUtils.isEmpty(jSONObject.getString(str))) {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                if (!str3.equals("_id") && !jSONObject.isNull(str3)) {
                    this.langs.put(str3, jSONObject.optString(str3));
                }
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append(jSONObject.toString());
            throw new JSONException(sb2.toString());
        }
    }

    public int describeContents() {
        return 0;
    }

    public String getText() {
        String str = (String) this.langs.get(NaviBeesUtils.getAppLang());
        return TextUtils.isEmpty(str) ? (String) this.langs.get(ENGLISH) : str;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : this.langs.keySet()) {
                if (!TextUtils.isEmpty((CharSequence) this.langs.get(str))) {
                    jSONObject.put(str, this.langs.get(str));
                }
            }
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.langs == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeMap(this.langs);
    }

    protected Text(Parcel parcel) {
        if (parcel.readByte() == 1) {
            this.langs = parcel.readHashMap(Text.class.getClassLoader());
        } else {
            this.langs = null;
        }
    }
}
