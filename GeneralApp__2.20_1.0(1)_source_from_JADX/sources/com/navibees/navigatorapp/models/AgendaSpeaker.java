package com.navibees.navigatorapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgendaSpeaker implements Parcelable {
    public static final Creator<AgendaSpeaker> CREATOR = new Creator<AgendaSpeaker>() {
        public AgendaSpeaker createFromParcel(Parcel in) {
            return new AgendaSpeaker(in);
        }

        public AgendaSpeaker[] newArray(int size) {
            return new AgendaSpeaker[size];
        }
    };
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("name")
    @Expose
    public String name;

    protected AgendaSpeaker(Parcel in) {
        this.name = (String) in.readValue(String.class.getClassLoader());
        this.image = (String) in.readValue(String.class.getClassLoader());
    }

    public AgendaSpeaker() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.name);
        dest.writeValue(this.image);
    }

    public int describeContents() {
        return 0;
    }
}
