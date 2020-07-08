package com.navibees.navigatorapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Agenda implements Parcelable {
    public static final Creator<Agenda> CREATOR = new Creator<Agenda>() {
        public Agenda createFromParcel(Parcel in) {
            return new Agenda(in);
        }

        public Agenda[] newArray(int size) {
            return new Agenda[size];
        }
    };
    @SerializedName("info")
    @Expose
    public List<Info> info = null;
    @SerializedName("title")
    @Expose
    public String title;

    protected Agenda(Parcel in) {
        this.title = (String) in.readValue(String.class.getClassLoader());
        in.readList(this.info, Info.class.getClassLoader());
    }

    public Agenda() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.title);
        dest.writeList(this.info);
    }

    public int describeContents() {
        return 0;
    }
}
