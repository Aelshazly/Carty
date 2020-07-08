package com.navibees.core.model.metadata.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EsriFacility extends Facility implements Parcelable {
    public static final Creator<EsriFacility> CREATOR = new Creator<EsriFacility>() {
        public EsriFacility createFromParcel(Parcel parcel) {
            return new EsriFacility(parcel);
        }

        public EsriFacility[] newArray(int i) {
            return new EsriFacility[i];
        }
    };
    private List<EsriPOI> pois = new ArrayList();

    public EsriFacility(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        String str = "pois";
        if (jSONObject.optJSONArray(str) != null) {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            this.pois = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    this.pois.add(new EsriPOI(jSONArray.getJSONObject(i)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public List<IndoorLocation> entryPointsAtFloor(Integer num) {
        ArrayList arrayList = new ArrayList();
        List<EsriPOI> list = this.pois;
        if (list != null) {
            for (EsriPOI entryPointsAtFloor : list) {
                List<IndoorLocation> entryPointsAtFloor2 = entryPointsAtFloor.entryPointsAtFloor(num.intValue());
                if (entryPointsAtFloor2 != null) {
                    for (IndoorLocation indoorLocation : entryPointsAtFloor2) {
                        if (num.equals(Integer.valueOf(indoorLocation.floor))) {
                            arrayList.add(indoorLocation);
                        }
                    }
                }
            }
            if (arrayList.size() != 0) {
                return arrayList;
            }
        }
        return null;
    }

    public List<EsriPOI> getPois() {
        return this.pois;
    }

    public List<IndoorLocation> locationsAtFloor(Integer num) {
        ArrayList arrayList = new ArrayList();
        List<EsriPOI> list = this.pois;
        if (list != null) {
            for (EsriPOI locationsAtFloor : list) {
                List<IndoorLocation> locationsAtFloor2 = locationsAtFloor.locationsAtFloor(num.intValue());
                if (locationsAtFloor2 != null) {
                    for (IndoorLocation indoorLocation : locationsAtFloor2) {
                        if (num.equals(Integer.valueOf(indoorLocation.floor))) {
                            arrayList.add(indoorLocation);
                        }
                    }
                }
            }
            if (arrayList.size() != 0) {
                return arrayList;
            }
        }
        return null;
    }

    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            if (this.pois != null && this.pois.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (EsriPOI json2 : this.pois) {
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

    protected EsriFacility(Parcel parcel) {
        super(parcel);
        if (parcel.readByte() == 1) {
            this.pois = new ArrayList();
            parcel.readList(this.pois, EsriPOI.class.getClassLoader());
            return;
        }
        this.pois = null;
    }
}
