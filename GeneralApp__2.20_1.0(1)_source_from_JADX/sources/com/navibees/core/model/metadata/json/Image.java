package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.json.JSONException;
import org.json.JSONObject;

public class Image implements Parcelable {
    public static final Creator<Image> CREATOR = new Creator<Image>() {
        public Image createFromParcel(Parcel parcel) {
            return new Image(parcel);
        }

        public Image[] newArray(int i) {
            return new Image[i];
        }
    };
    private static String JSON_KEY_URL = "url";
    public String url;

    public Image(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.isNull(JSON_KEY_URL)) {
            this.url = jSONObject.getString(JSON_KEY_URL);
            return;
        }
        throw new JSONException("Image should contain url");
    }

    public int describeContents() {
        return 0;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_KEY_URL, this.url);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
    }

    protected Image(Parcel parcel) {
        this.url = parcel.readString();
    }
}
