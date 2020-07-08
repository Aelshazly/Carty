package com.navibees.navigatorapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exhibitor implements Parcelable {
    public static final Creator<Exhibitor> CREATOR = new Creator<Exhibitor>() {
        public Exhibitor createFromParcel(Parcel in) {
            return new Exhibitor(in);
        }

        public Exhibitor[] newArray(int size) {
            return new Exhibitor[size];
        }
    };
    @SerializedName("About_ar")
    @Expose
    public String aboutAr;
    @SerializedName("About_Du")
    @Expose
    public String aboutDu;
    @SerializedName("About_en")
    @Expose
    public String aboutEn;
    @SerializedName("About_Fr")
    @Expose
    public String aboutFr;
    @SerializedName("About_Ur")
    @Expose
    public String aboutUr;
    @SerializedName("Booth Number")
    @Expose
    public String boothNumber;
    @SerializedName("Email ID")
    @Expose
    public String emailID;
    @SerializedName("ID")
    @Expose

    /* renamed from: id */
    public int f205id;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Nav-id")
    @Expose
    public String navId;
    @SerializedName("Phone")
    @Expose
    public String phone;
    @SerializedName("Place")
    @Expose
    public String place;
    @SerializedName("Title_ar")
    @Expose
    public String titleAr;
    @SerializedName("Title_en")
    @Expose
    public String titleEn;
    @SerializedName("Website")
    @Expose
    public String website;

    protected Exhibitor(Parcel in) {
        this.f205id = ((Integer) in.readValue(Integer.TYPE.getClassLoader())).intValue();
        this.name = (String) in.readValue(String.class.getClassLoader());
        this.titleEn = (String) in.readValue(String.class.getClassLoader());
        this.titleAr = (String) in.readValue(String.class.getClassLoader());
        this.aboutEn = (String) in.readValue(String.class.getClassLoader());
        this.aboutAr = (String) in.readValue(String.class.getClassLoader());
        this.aboutFr = (String) in.readValue(String.class.getClassLoader());
        this.aboutDu = (String) in.readValue(String.class.getClassLoader());
        this.aboutUr = (String) in.readValue(String.class.getClassLoader());
        this.emailID = (String) in.readValue(String.class.getClassLoader());
        this.phone = (String) in.readValue(String.class.getClassLoader());
        this.website = (String) in.readValue(String.class.getClassLoader());
        this.place = (String) in.readValue(String.class.getClassLoader());
        this.boothNumber = (String) in.readValue(String.class.getClassLoader());
        this.navId = (String) in.readValue(String.class.getClassLoader());
    }

    public Exhibitor() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(Integer.valueOf(this.f205id));
        dest.writeValue(this.name);
        dest.writeValue(this.titleEn);
        dest.writeValue(this.titleAr);
        dest.writeValue(this.aboutEn);
        dest.writeValue(this.aboutAr);
        dest.writeValue(this.aboutFr);
        dest.writeValue(this.aboutDu);
        dest.writeValue(this.aboutUr);
        dest.writeValue(this.emailID);
        dest.writeValue(this.phone);
        dest.writeValue(this.website);
        dest.writeValue(this.place);
        dest.writeValue(this.boothNumber);
        dest.writeValue(this.navId);
    }

    public int describeContents() {
        return 0;
    }
}
