package com.navibees.core.model.metadata.json;

import com.navibees.core.util.NaviBeesUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Portal extends EsriPOI {
    public static final Map<String, Integer> COST = new HashMap();
    public static final String[] PORTAL_TYPE = {"elevator", "stair", "escalator"};
    public static final String[] PORTAL_TYPE_ARABIC = {"المصعد", "السلم", "السلم المتحرك"};
    public int indexInList;
    public String type;

    static {
        COST.put(PORTAL_TYPE[0], Integer.valueOf(0));
        COST.put(PORTAL_TYPE[1], Integer.valueOf(5));
        COST.put(PORTAL_TYPE[2], Integer.valueOf(1));
    }

    public Portal(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
    }

    public String getTypeWRTLang() {
        if (NaviBeesUtils.isArabicLang().booleanValue() && this.type != null) {
            int i = 0;
            while (true) {
                String[] strArr = PORTAL_TYPE;
                if (i >= strArr.length) {
                    break;
                } else if (strArr[i].equalsIgnoreCase(this.type)) {
                    return PORTAL_TYPE_ARABIC[i];
                } else {
                    i++;
                }
            }
        } else if (!this.type.startsWith("e")) {
            this.type = "E".concat(this.type.substring(1));
        } else if (!this.type.startsWith("s")) {
            this.type = "S".concat(this.type.substring(1));
        }
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public JSONObject toJson() {
        return super.toJson();
    }
}
