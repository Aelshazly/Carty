package com.navibees.routing;

import com.navibees.core.model.metadata.json.POI;
import java.util.List;

public interface GetNearestPOIsCallback {
    void onNearestPOIsFailure(String str);

    void onNearestPOIsFound(List<POI> list);
}
