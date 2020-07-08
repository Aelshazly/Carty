package com.navibees.navigatorapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sponsor implements Parcelable {
    public static final Creator<Sponsor> CREATOR = new Creator<Sponsor>() {
        public Sponsor createFromParcel(Parcel in) {
            return new Sponsor(in);
        }

        public Sponsor[] newArray(int size) {
            return new Sponsor[size];
        }
    };
    @SerializedName("ID")
    @Expose

    /* renamed from: id */
    public int f208id;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Phone")
    @Expose
    public String phone;
    @SerializedName("Title_Ar")
    @Expose
    public String titleAr;
    @SerializedName("Title_en")
    @Expose
    public String titleEn;
    @SerializedName("Website")
    @Expose
    public String website;

    protected Sponsor(Parcel in) {
        this.f208id = ((Integer) in.readValue(Integer.TYPE.getClassLoader())).intValue();
        this.name = (String) in.readValue(String.class.getClassLoader());
        this.titleEn = (String) in.readValue(String.class.getClassLoader());
        this.titleAr = (String) in.readValue(String.class.getClassLoader());
        this.phone = (String) in.readValue(String.class.getClassLoader());
        this.website = (String) in.readValue(String.class.getClassLoader());
    }

    public Sponsor() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(Integer.valueOf(this.f208id));
        dest.writeValue(this.name);
        dest.writeValue(this.titleEn);
        dest.writeValue(this.titleAr);
        dest.writeValue(this.phone);
        dest.writeValue(this.website);
    }

    public int describeContents() {
        return 0;
    }
}
