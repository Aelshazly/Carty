package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VisioFacility extends Facility implements Parcelable {
    public static final Creator<VisioFacility> CREATOR = new Creator<VisioFacility>() {
        public VisioFacility createFromParcel(Parcel parcel) {
            return new VisioFacility(parcel);
        }

        public VisioFacility[] newArray(int i) {
            return new VisioFacility[i];
        }
    };
    List<VisioPOI> pois = new ArrayList();

    public VisioFacility(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        String str = "pois";
        if (jSONObject.optJSONArray(str) != null) {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            this.pois = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    this.pois.add(new VisioPOI(jSONArray.getJSONObject(i)));
                } catch (Exception e) {
                }
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public List<VisioPOI> getPois() {
        return this.pois;
    }

    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            if (this.pois != null && this.pois.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (VisioPOI json2 : this.pois) {
                    JSONObject json3 = json2.toJson();
                    if (json3 != null) {
                        jSONArray.put(json3);
                    }
                }
                json.put("pois", jSONArray);
            }
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        if (this.pois == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeList(this.pois);
    }

    protected VisioFacility(Parcel parcel) {
        super(parcel);
        if (parcel.readByte() == 1) {
            this.pois = new ArrayList();
            parcel.readList(this.pois, VisioPOI.class.getClassLoader());
            return;
        }
        this.pois = null;
    }
}
