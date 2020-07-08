package com.navibees.core.model.routing;

import android.content.Context;
import android.util.SparseArray;
import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.LineIndoorLocationRestriction;
import com.navibees.core.model.metadata.json.Portal;
import com.navibees.core.model.postioning.C1697c;
import com.navibees.core.model.postioning.p025h.C1714c;
import java.util.ArrayList;
import java.util.List;

public final class TurnByTurnNavigation {
    private static final double DEF_EPSILON = 0.5d;
    private static final double DETECT_TURN_THRESHOLD = 5.0d;
    private static final double DISTANCE_FROM_FINAL_THRESHOLD = 5.0d;
    private static final double THETA_EPSILON = 5.0d;
    private static TurnByTurnNavigation mSingleton;
    private Portal mPortal;
    private SparseArray<List<LineIndoorLocationRestriction>> mSegmentsPerFloor;
    private int mTargetFloor = -1;

    public class NavigationResult {
        public String mMessage;
        public double mRotationAngle;
        public LineIndoorLocationRestriction mSegment;

        public NavigationResult() {
        }
    }

    public class NearestPointOnRoute {
        public double distanceFromRealPoint;
        public IndoorLocation mPointOnRoute;

        public NearestPointOnRoute() {
        }
    }

    private String getArriveMessage(Context context) {
        return "turn_by_turn_message_arrive";
    }

    private String getCompleteTurnMessage(Context context) {
        return "turn_by_turn_message_compltete_turn";
    }

    private String getDistanceToDestinationMessage(Context context) {
        return "turn_by_turn_message_distance_to_destination";
    }

    private String getDistanceToPortalMessage(Context context) {
        return "turn_by_turn_message_distance_to_portal";
    }

    private String getGentleMessage(Context context) {
        return "turn_by_turn_message_gentle";
    }

    private String getGoStraightMessage(Context context) {
        return "turn_by_turn_message_go_straight";
    }

    private String getGotoFloorMessage(Context context) {
        return "turn_by_turn_message_go_to_floor";
    }

    public static TurnByTurnNavigation getInstance(Context context) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException {
        if (mSingleton == null) {
            mSingleton = new TurnByTurnNavigation();
        }
        return mSingleton;
    }

    private String getNavigationMessage(Context context, LineIndoorLocationRestriction lineIndoorLocationRestriction, LineIndoorLocationRestriction lineIndoorLocationRestriction2, IndoorLocation indoorLocation, IndoorLocation indoorLocation2) {
        Context context2 = context;
        LineIndoorLocationRestriction lineIndoorLocationRestriction3 = lineIndoorLocationRestriction;
        LineIndoorLocationRestriction lineIndoorLocationRestriction4 = lineIndoorLocationRestriction2;
        IndoorLocation indoorLocation3 = indoorLocation;
        double d = C1697c.m1064d(indoorLocation, indoorLocation2);
        if (d <= 5.0d) {
            return getNearbyDestinationMessage(context2, indoorLocation3, (int) Math.round(d));
        }
        double d2 = C1697c.m1064d(indoorLocation3, lineIndoorLocationRestriction3.end);
        int round = (int) Math.round(d2);
        if (lineIndoorLocationRestriction4 == null || d2 > 5.0d) {
            return getGoStraightMessage(context);
        }
        double radians = Math.toRadians(C1697c.m1066e(lineIndoorLocationRestriction3.start, lineIndoorLocationRestriction3.end));
        IndoorLocation indoorLocation4 = lineIndoorLocationRestriction3.start;
        C1714c cVar = new C1714c(3, 3);
        cVar.mo29389a(1.0d, 0.0d, -indoorLocation4.f1332x, 0.0d, 1.0d, -indoorLocation4.f1333y, 0.0d, 0.0d, 1.0d);
        C1714c cVar2 = new C1714c(3, 3);
        cVar2.mo29389a(Math.cos(radians), -Math.sin(radians), 0.0d, Math.sin(radians), Math.cos(radians), 0.0d, 0.0d, 0.0d, 1.0d);
        C1714c cVar3 = new C1714c(3, 3);
        cVar3.mo29389a(1.0d, 0.0d, indoorLocation4.f1332x, 0.0d, 1.0d, indoorLocation4.f1333y, 0.0d, 0.0d, 1.0d);
        C1714c cVar4 = new C1714c(3, 3);
        C1714c.m1126c(cVar3, cVar2, cVar4);
        C1714c cVar5 = new C1714c(3, 3);
        C1714c.m1126c(cVar4, cVar, cVar5);
        IndoorLocation indoorLocation5 = lineIndoorLocationRestriction4.start;
        IndoorLocation indoorLocation6 = lineIndoorLocationRestriction4.end;
        C1714c cVar6 = new C1714c(3, 1);
        C1714c cVar7 = new C1714c(3, 1);
        cVar7.mo29389a(indoorLocation5.f1332x, indoorLocation5.f1333y, 1.0d);
        C1714c.m1126c(cVar5, cVar7, cVar6);
        double[][] dArr = cVar6.f1555c;
        IndoorLocation indoorLocation7 = new IndoorLocation(dArr[0][0], dArr[1][0]);
        cVar7.mo29389a(indoorLocation6.f1332x, indoorLocation6.f1333y, 1.0d);
        C1714c.m1126c(cVar5, cVar7, cVar6);
        double[][] dArr2 = cVar6.f1555c;
        IndoorLocation indoorLocation8 = new IndoorLocation(dArr2[0][0], dArr2[1][0]);
        String stringRepresentAngle = getStringRepresentAngle(context2, C1697c.m1038a(lineIndoorLocationRestriction, lineIndoorLocationRestriction2));
        String turnLeftMessage = getTurnLeftMessage(context);
        if (indoorLocation8.f1332x > indoorLocation7.f1332x) {
            turnLeftMessage = getTurnRightMessage(context);
        }
        return String.format(getCompleteTurnMessage(context), new Object[]{stringRepresentAngle, turnLeftMessage, Integer.valueOf(round)});
    }

    private String getNearbyDestinationMessage(Context context, IndoorLocation indoorLocation, int i) {
        String str;
        if (indoorLocation.floor != this.mTargetFloor) {
            return "";
        }
        if (i <= 2) {
            str = getArriveMessage(context);
        } else {
            str = String.format(getDistanceToDestinationMessage(context), new Object[]{Integer.valueOf(i)});
        }
        return str;
    }

    private List<LineIndoorLocationRestriction> getSegments(IndoorLocation[] indoorLocationArr) {
        String str;
        IndoorLocation[] indoorLocationArr2 = indoorLocationArr;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(indoorLocationArr2[0]);
        double d = 0.0d;
        int i = 1;
        double d2 = 0.0d;
        int i2 = 1;
        boolean z = true;
        while (true) {
            str = "";
            if (i2 >= indoorLocationArr2.length) {
                break;
            }
            IndoorLocation indoorLocation = (IndoorLocation) arrayList2.get(arrayList2.size() - i);
            int i3 = i2;
            double d3 = indoorLocationArr2[i2].f1332x - indoorLocation.f1332x;
            if (d3 == d) {
                d3 = 1.0E-4d;
            }
            double degrees = Math.toDegrees(Math.atan((indoorLocationArr2[i3].f1333y - indoorLocation.f1333y) / d3));
            if (C1697c.m1064d(indoorLocationArr2[i3], indoorLocation) > DEF_EPSILON) {
                if (!z && Math.abs(degrees - d2) > 5.0d) {
                    if (arrayList2.size() > 1) {
                        arrayList.add(new LineIndoorLocationRestriction(str, 0, (IndoorLocation) arrayList2.get(0), (IndoorLocation) arrayList2.get(arrayList2.size() - 1)));
                    }
                    arrayList2.clear();
                    arrayList2.add(indoorLocation);
                    d2 = degrees;
                }
                if (z) {
                    z = false;
                } else {
                    degrees = d2;
                }
                arrayList2.add(indoorLocationArr2[i3]);
                d2 = degrees;
            }
            i2 = i3 + 1;
            d = 0.0d;
            i = 1;
        }
        if (arrayList2.size() > 1) {
            arrayList.add(new LineIndoorLocationRestriction(str, 0, (IndoorLocation) arrayList2.get(0), (IndoorLocation) arrayList2.get(arrayList2.size() - 1)));
        }
        return arrayList;
    }

    private String getSharpMessage(Context context) {
        return "turn_by_turn_message_sharp";
    }

    private String getStringRepresentAngle(Context context, double d) {
        int round = (int) Math.round(d);
        if (round > 110) {
            return getGentleMessage(context);
        }
        if (round < 70) {
            return getSharpMessage(context);
        }
        return "";
    }

    private String getTurnLeftMessage(Context context) {
        return "turn_by_turn_message_turn_left";
    }

    private String getTurnRightMessage(Context context) {
        return "turn_by_turn_message_turn_right";
    }

    public NavigationResult getCurrentResult(Context context, IndoorLocation indoorLocation) {
        IndoorLocation indoorLocation2 = indoorLocation;
        SparseArray<List<LineIndoorLocationRestriction>> sparseArray = this.mSegmentsPerFloor;
        if (sparseArray == null) {
            return null;
        }
        List list = (List) sparseArray.get(indoorLocation2.floor);
        if (list == null) {
            return null;
        }
        double d = 2.147483647E9d;
        NavigationResult navigationResult = null;
        for (int i = 0; i < list.size(); i++) {
            LineIndoorLocationRestriction lineIndoorLocationRestriction = (LineIndoorLocationRestriction) list.get(i);
            double[] a = C1697c.m1048a(lineIndoorLocationRestriction, indoorLocation2, 3.0d);
            boolean z = a[0] != 0.0d;
            double d2 = a[1];
            if (z && d2 < d) {
                NavigationResult navigationResult2 = new NavigationResult();
                navigationResult2.mSegment = lineIndoorLocationRestriction;
                navigationResult2.mRotationAngle = C1697c.m1066e(lineIndoorLocationRestriction.start, lineIndoorLocationRestriction.end);
                int i2 = i + 1;
                navigationResult2.mMessage = getNavigationMessage(context, lineIndoorLocationRestriction, i2 < list.size() ? (LineIndoorLocationRestriction) list.get(i2) : null, indoorLocation, ((LineIndoorLocationRestriction) list.get(list.size() - 1)).end);
                navigationResult = navigationResult2;
                d = d2;
            }
        }
        if (navigationResult == null) {
            double d3 = C1697c.m1064d(indoorLocation2, ((LineIndoorLocationRestriction) list.get(list.size() - 1)).end);
            if (d3 <= 5.0d) {
                NavigationResult navigationResult3 = new NavigationResult();
                navigationResult3.mMessage = getNearbyDestinationMessage(context, indoorLocation2, (int) Math.round(d3));
                return navigationResult3;
            }
        } else {
            IndoorLocation c = C1697c.m1061c(navigationResult.mSegment, indoorLocation2);
            if (c != null) {
                indoorLocation2.f1332x = c.f1332x;
                indoorLocation2.f1333y = c.f1333y;
            }
        }
        return navigationResult;
    }

    public Portal getCurrentUsedPortal() {
        return this.mPortal;
    }

    public NearestPointOnRoute getNearestPointOnRoute(IndoorLocation indoorLocation) {
        SparseArray<List<LineIndoorLocationRestriction>> sparseArray = this.mSegmentsPerFloor;
        NearestPointOnRoute nearestPointOnRoute = null;
        if (sparseArray != null) {
            List<LineIndoorLocationRestriction> list = (List) sparseArray.get(indoorLocation.floor);
            if (list != null) {
                double d = 2.147483647E9d;
                for (LineIndoorLocationRestriction c : list) {
                    IndoorLocation c2 = C1697c.m1061c(c, indoorLocation);
                    if (c2 != null) {
                        double d2 = C1697c.m1064d(indoorLocation, c2);
                        if (d2 < d) {
                            nearestPointOnRoute = new NearestPointOnRoute();
                            nearestPointOnRoute.mPointOnRoute = c2;
                            nearestPointOnRoute.distanceFromRealPoint = d2;
                            d = d2;
                        }
                    }
                }
            }
        }
        return nearestPointOnRoute;
    }

    public void reset() {
        this.mSegmentsPerFloor = null;
        this.mTargetFloor = -1;
        this.mPortal = null;
    }

    public void updateEsriRoute(SparseArray<IndoorLocation[]> sparseArray, int i, Portal portal) {
        SparseArray<List<LineIndoorLocationRestriction>> sparseArray2 = new SparseArray<>();
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            int keyAt = sparseArray.keyAt(i2);
            sparseArray2.put(keyAt, getSegments((IndoorLocation[]) sparseArray.get(keyAt)));
        }
        this.mTargetFloor = i;
        if (sparseArray.size() == 1) {
            this.mTargetFloor = sparseArray.keyAt(0);
        }
        this.mSegmentsPerFloor = sparseArray2;
        this.mPortal = portal;
    }
}
