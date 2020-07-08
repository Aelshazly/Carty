package com.navibees.navigatorapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Info implements Parcelable {
    public static final Creator<Info> CREATOR = new Creator<Info>() {
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
    @SerializedName("Activity")
    @Expose
    public String activity;
    @SerializedName("Category")
    @Expose
    public String category;
    @SerializedName("Chairperson")
    @Expose
    public List<Chairperson> chairperson = null;
    @SerializedName("Date")
    @Expose
    public String date;
    @SerializedName("ID")
    @Expose

    /* renamed from: iD */
    public int f206iD;
    @SerializedName("Place")
    @Expose
    public String place;
    @SerializedName("Speakers")
    @Expose
    public List<AgendaSpeaker> speakers = null;
    @SerializedName("Time")
    @Expose
    public String time;

    protected Info(Parcel in) {
        this.f206iD = ((Integer) in.readValue(Integer.TYPE.getClassLoader())).intValue();
        this.date = (String) in.readValue(String.class.getClassLoader());
        this.time = (String) in.readValue(String.class.getClassLoader());
        this.place = (String) in.readValue(String.class.getClassLoader());
        this.activity = (String) in.readValue(String.class.getClassLoader());
        in.readList(this.chairperson, Chairperson.class.getClassLoader());
        in.readList(this.speakers, Speaker.class.getClassLoader());
        this.category = (String) in.readValue(String.class.getClassLoader());
    }

    public Info() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(Integer.valueOf(this.f206iD));
        dest.writeValue(this.date);
        dest.writeValue(this.time);
        dest.writeValue(this.place);
        dest.writeValue(this.activity);
        dest.writeList(this.chairperson);
        dest.writeList(this.speakers);
        dest.writeValue(this.category);
    }

    public int describeContents() {
        return 0;
    }
}
