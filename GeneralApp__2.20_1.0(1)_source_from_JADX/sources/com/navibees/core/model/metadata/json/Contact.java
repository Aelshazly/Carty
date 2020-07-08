package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.json.JSONException;
import org.json.JSONObject;

public class Contact implements Parcelable {
    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel parcel) {
            return new Contact(parcel);
        }

        public Contact[] newArray(int i) {
            return new Contact[i];
        }
    };
    public String phoneNumber;

    public Contact(JSONObject jSONObject) throws JSONException {
        this.phoneNumber = jSONObject.getString("phone_number");
    }

    public int describeContents() {
        return 0;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("phone_number", this.phoneNumber);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.phoneNumber);
    }

    protected Contact(Parcel parcel) {
        this.phoneNumber = parcel.readString();
    }
}
