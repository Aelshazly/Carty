package com.navibees.navigatorapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chairperson implements Parcelable {
    public static final Creator<Chairperson> CREATOR = new Creator<Chairperson>() {
        public Chairperson createFromParcel(Parcel in) {
            return new Chairperson(in);
        }

        public Chairperson[] newArray(int size) {
            return new Chairperson[size];
        }
    };
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("name")
    @Expose
    public String name;

    protected Chairperson(Parcel in) {
        this.name = (String) in.readValue(String.class.getClassLoader());
        this.image = (String) in.readValue(String.class.getClassLoader());
    }

    public Chairperson() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.name);
        dest.writeValue(this.image);
    }

    public int describeContents() {
        return 0;
    }
}
