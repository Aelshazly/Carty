package com.navibees.navigatorapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Speaker implements Parcelable {
    public static final Creator<Speaker> CREATOR = new Creator<Speaker>() {
        public Speaker createFromParcel(Parcel in) {
            return new Speaker(in);
        }

        public Speaker[] newArray(int size) {
            return new Speaker[size];
        }
    };
    @SerializedName("About")
    @Expose
    public String about;
    @SerializedName("Email ID")
    @Expose
    public String emailID;
    @SerializedName("ID")
    @Expose

    /* renamed from: id */
    public int f207id;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Phone")
    @Expose
    public String phone;
    @SerializedName("Place")
    @Expose
    public String place;
    @SerializedName("Session Day")
    @Expose
    public String sessionDay;
    @SerializedName("Session Time")
    @Expose
    public String sessionTime;
    @SerializedName("Title")
    @Expose
    public String title;

    protected Speaker(Parcel in) {
        this.f207id = ((Integer) in.readValue(Integer.TYPE.getClassLoader())).intValue();
        this.name = (String) in.readValue(String.class.getClassLoader());
        this.title = (String) in.readValue(String.class.getClassLoader());
        this.about = (String) in.readValue(String.class.getClassLoader());
        this.sessionDay = (String) in.readValue(String.class.getClassLoader());
        this.sessionTime = (String) in.readValue(String.class.getClassLoader());
        this.place = (String) in.readValue(String.class.getClassLoader());
        this.emailID = (String) in.readValue(String.class.getClassLoader());
        this.phone = (String) in.readValue(String.class.getClassLoader());
    }

    public Speaker() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(Integer.valueOf(this.f207id));
        dest.writeValue(this.name);
        dest.writeValue(this.title);
        dest.writeValue(this.about);
        dest.writeValue(this.sessionDay);
        dest.writeValue(this.sessionTime);
        dest.writeValue(this.place);
        dest.writeValue(this.emailID);
        dest.writeValue(this.phone);
    }

    public int describeContents() {
        return 0;
    }
}
