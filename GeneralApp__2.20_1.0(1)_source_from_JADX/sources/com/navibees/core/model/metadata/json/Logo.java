package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Logo implements Parcelable {
    public static final Creator<Logo> CREATOR = new Creator<Logo>() {
        public Logo createFromParcel(Parcel parcel) {
            return new Logo(parcel);
        }

        public Logo[] newArray(int i) {
            return new Logo[i];
        }
    };
    private String url;

    protected Logo(Parcel parcel) {
        this.url = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
    }
}
