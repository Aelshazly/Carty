package com.navibees.core.model.metadata.json;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.navibees.core.NaviBeesManager;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VisioPOI extends POI implements Parcelable {
    public static final Creator<VisioPOI> CREATOR = new Creator<VisioPOI>() {
        public VisioPOI createFromParcel(Parcel parcel) {
            return new VisioPOI(parcel);
        }

        public VisioPOI[] newArray(int i) {
            return new VisioPOI[i];
        }
    };
    public int floorIndex;
    public String vgId;

    public VisioPOI(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        this.vgId = jSONObject.getString("provider_id");
        this.floorIndex = jSONObject.getJSONArray("locations").getJSONObject(0).getInt("floor_index");
    }

    public int describeContents() {
        return 0;
    }

    public Floor getPOIFloor(Application application) {
        List<Floor> list = ((Building) NaviBeesManager.getInstance(application).getMetaDataManager().getBuildings().get(this.buildingId)).floors;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (this.floorIndex == ((Floor) list.get(i)).floorIndex) {
                    return (Floor) list.get(i);
                }
            }
        }
        return null;
    }

    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            json.put("provider_id", this.vgId);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("floor_index", this.floorIndex);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            json.put("locations", jSONArray);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.vgId);
        parcel.writeInt(this.floorIndex);
    }

    protected VisioPOI(Parcel parcel) {
        super(parcel);
        this.vgId = parcel.readString();
        this.floorIndex = parcel.readInt();
    }
}
