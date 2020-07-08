package com.navibees.core.model.metadata.json;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.navibees.core.NaviBeesManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EsriPOI extends POI implements Parcelable {
    public static final Creator<EsriPOI> CREATOR = new Creator<EsriPOI>() {
        public EsriPOI createFromParcel(Parcel parcel) {
            return new EsriPOI(parcel);
        }

        public EsriPOI[] newArray(int i) {
            return new EsriPOI[i];
        }
    };
    public List<IndoorLocation> entryPoints;
    public List<IndoorLocation> locations;

    public EsriPOI(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        JSONArray jSONArray = jSONObject.getJSONArray("locations");
        this.locations = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                this.locations.add(new IndoorLocation(jSONArray.getJSONObject(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSONArray jSONArray2 = jSONObject.getJSONArray("entry_points");
        this.entryPoints = new ArrayList();
        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
            try {
                this.entryPoints.add(new IndoorLocation(jSONArray2.getJSONObject(i2)));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public List<IndoorLocation> entryPointsAtFloor(int i) {
        ArrayList arrayList = new ArrayList();
        List<IndoorLocation> list = this.entryPoints;
        if (list != null) {
            for (IndoorLocation indoorLocation : list) {
                if (i == indoorLocation.floor) {
                    arrayList.add(indoorLocation);
                }
            }
            if (arrayList.size() != 0) {
                return arrayList;
            }
        }
        return null;
    }

    public Floor getPOIFloor(Application application) {
        List<Floor> list = ((Building) NaviBeesManager.getInstance(application).getMetaDataManager().getBuildings().get(this.buildingId)).floors;
        if (list != null) {
            List<IndoorLocation> list2 = this.locations;
            if (list2 != null && list2.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (((Floor) list.get(i)).floorIndex == ((IndoorLocation) this.locations.get(0)).floor) {
                        return (Floor) list.get(i);
                    }
                }
            }
        }
        return null;
    }

    public List<IndoorLocation> locationsAtFloor(int i) {
        ArrayList arrayList = new ArrayList();
        List<IndoorLocation> list = this.locations;
        if (list != null) {
            for (IndoorLocation indoorLocation : list) {
                if (i == indoorLocation.floor) {
                    arrayList.add(indoorLocation);
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
            JSONArray jSONArray = new JSONArray();
            for (IndoorLocation json2 : this.locations) {
                JSONObject json3 = json2.toJson();
                if (json3 != null) {
                    jSONArray.put(json3);
                }
            }
            json.put("locations", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (IndoorLocation json4 : this.entryPoints) {
                JSONObject json5 = json4.toJson();
                if (json5 != null) {
                    jSONArray2.put(json5);
                }
            }
            json.put("entry_points", jSONArray2);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        if (this.locations == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.locations);
        }
        if (this.entryPoints == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeList(this.entryPoints);
    }

    protected EsriPOI(Parcel parcel) {
        super(parcel);
        if (parcel.readByte() == 1) {
            this.locations = new ArrayList();
            parcel.readList(this.locations, IndoorLocation.class.getClassLoader());
        } else {
            this.locations = null;
        }
        if (parcel.readByte() == 1) {
            this.entryPoints = new ArrayList();
            parcel.readList(this.entryPoints, IndoorLocation.class.getClassLoader());
            return;
        }
        this.entryPoints = null;
    }
}
