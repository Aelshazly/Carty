package com.navibees.maps;

import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import com.navibees.C1164R;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.activity.MapHoverActivity;
import com.navibees.core.interfaces.MapHoverInterface;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Floor;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.VisioPOI;
import com.navibees.visioglobe.p020g.C1513c;
import com.navibees.visioglobe.p020g.C1514d;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class MapActivity extends MapHoverActivity {

    /* renamed from: a */
    protected MapFragment f272a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public List<POI> f273b;

    /* renamed from: c */
    private String f274c;

    /* renamed from: d */
    public ArrayList<POI> f275d = null;

    /* renamed from: e */
    private C1434g f276e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public boolean f277f = false;

    /* renamed from: g */
    C1514d f278g = new C1348e(this);

    /* renamed from: com.navibees.maps.MapActivity$a */
    class C1344a implements OnClickListener {

        /* renamed from: a */
        final /* synthetic */ ArrayAdapter f279a;

        /* renamed from: b */
        final /* synthetic */ HashMap f280b;

        C1344a(ArrayAdapter arrayAdapter, HashMap hashMap) {
            this.f279a = arrayAdapter;
            this.f280b = hashMap;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            String buildingIdByName = NaviBeesManager.getInstance(MapActivity.this.getApplication()).getMetaDataManager().getBuildingIdByName((String) this.f279a.getItem(i));
            MapActivity.this.m148a((HashMap) this.f280b.get(buildingIdByName), buildingIdByName);
            dialogInterface.dismiss();
        }
    }

    /* renamed from: com.navibees.maps.MapActivity$b */
    class C1345b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Builder f282a;

        C1345b(MapActivity mapActivity, Builder builder) {
            this.f282a = builder;
        }

        public void run() {
            this.f282a.create().show();
        }
    }

    /* renamed from: com.navibees.maps.MapActivity$c */
    class C1346c implements OnClickListener {

        /* renamed from: a */
        final /* synthetic */ ArrayAdapter f283a;

        /* renamed from: b */
        final /* synthetic */ Building f284b;

        C1346c(ArrayAdapter arrayAdapter, Building building) {
            this.f283a = arrayAdapter;
            this.f284b = building;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Floor floor;
            String str = "";
            int intValue = Integer.valueOf(((String) this.f283a.getItem(i)).replace("Current", str).replace("Floor", str).replace(" ", str).replace(":", str)).intValue();
            Iterator it = this.f284b.floors.iterator();
            while (true) {
                if (!it.hasNext()) {
                    floor = null;
                    break;
                }
                floor = (Floor) it.next();
                if (floor.floorIndex == intValue) {
                    break;
                }
            }
            if (MapActivity.this.f277f) {
                MapActivity mapActivity = MapActivity.this;
                mapActivity.f272a.showDestinationMarkers(mapActivity.f273b, this.f284b, floor);
                return;
            }
            MapActivity mapActivity2 = MapActivity.this;
            mapActivity2.f272a.showDestinationMarkers(mapActivity2.f273b, null, floor);
        }
    }

    /* renamed from: com.navibees.maps.MapActivity$d */
    class C1347d implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Builder f286a;

        C1347d(MapActivity mapActivity, Builder builder) {
            this.f286a = builder;
        }

        public void run() {
            this.f286a.create().show();
        }
    }

    /* renamed from: com.navibees.maps.MapActivity$e */
    class C1348e implements C1514d {
        C1348e(MapActivity mapActivity) {
        }

        /* renamed from: c */
        public void mo28202c(int i) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("Instruction Number: ");
            sb.append(i);
            printStream.println(sb.toString());
        }
    }

    /* renamed from: com.navibees.maps.MapActivity$f */
    class C1349f extends BroadcastReceiver {
        C1349f(MapActivity mapActivity) {
        }

        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("dest");
            double doubleExtra = intent.getDoubleExtra("length", 0.0d);
            double doubleExtra2 = intent.getDoubleExtra("duration", 0.0d);
            double doubleExtra3 = intent.getDoubleExtra("x", 0.0d);
            double doubleExtra4 = intent.getDoubleExtra("y", 0.0d);
            double doubleExtra5 = intent.getDoubleExtra("z", 0.0d);
            StringBuilder sb = new StringBuilder();
            sb.append("X: ");
            sb.append(doubleExtra3);
            sb.append("\nY: ");
            sb.append(doubleExtra4);
            sb.append("\nAltitude: ");
            sb.append(doubleExtra5);
            sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("dest: ");
            sb2.append(stringExtra);
            sb2.append("\nlength: ");
            sb2.append(doubleExtra);
            sb2.append("\nduration: ");
            sb2.append(doubleExtra2);
            sb2.toString();
        }
    }

    /* renamed from: com.navibees.maps.MapActivity$g */
    class C1350g implements Runnable {
        C1350g() {
        }

        public void run() {
            MapActivity.this.startNavigationIV.callOnClick();
        }
    }

    public MapActivity() {
        new C1349f(this);
    }

    /* access modifiers changed from: protected */
    public void clearDisplayedRoute() {
        this.f272a.mo28205a();
    }

    /* access modifiers changed from: protected */
    public void clearDisplayedRoutingInstructions() {
        this.f272a.mo28218b();
    }

    /* access modifiers changed from: protected */
    public boolean getIsMapLoaded() {
        return this.f272a.getIsMapLoaded();
    }

    /* access modifiers changed from: protected */
    public void gotoActivitiesActivity() {
        super.gotoActivitiesActivity();
    }

    /* access modifiers changed from: protected */
    public void initiateRouting(POI poi, MapHoverInterface mapHoverInterface) {
        super.initiateRouting(poi, mapHoverInterface);
        ArrayList<POI> arrayList = this.f275d;
        if (arrayList == null) {
            this.f272a.initiateRouting((VisioPOI) poi, mapHoverInterface);
        } else if (arrayList.size() > 0) {
            this.f272a.initiateRouting((VisioPOI) poi, this.f275d, mapHoverInterface);
        }
    }

    public void notifyMapIsLoaded() {
        super.notifyMapIsLoaded();
        if (((getIntent().getParcelableExtra(NaviBeesConstants.ROUTE_TO_POI_KEY) != null) | (getIntent().getParcelableExtra(NaviBeesConstants.ROUTE_TO_FACILITY_KEY) != null)) || (getIntent().getParcelableExtra(NaviBeesConstants.ROUTE_TO_LOCATION_KEY) != null)) {
            this.f272a.setLocationProvider(false);
        } else if (getIntent().hasExtra("poi_category_id")) {
            this.f272a.setLocationProvider(false);
            m153c();
        } else {
            this.f272a.setLocationProvider(true);
        }
    }

    public void onBackPressed() {
        getSupportFragmentManager().beginTransaction().remove(this.f272a).commit();
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onBuildingButtonClick() {
        this.f272a.mo28228d();
    }

    /* access modifiers changed from: protected */
    public void onBuildingSelected(Building building) {
        this.f272a.mo28206a(building);
    }

    /* access modifiers changed from: protected */
    public void onClearRouteClick() {
        this.f272a.mo28229e();
    }

    /* access modifiers changed from: protected */
    public void onClearSearchViewClick() {
        this.f272a.mo28230f();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f272a = new MapFragment();
        this.f272a.setMapHoverInterface(this);
        if (NaviBeesManager.getInstance(getApplication()).isInitialized()) {
            getSupportFragmentManager().beginTransaction().add(C1164R.C1167id.mapViewFrameContainer, (Fragment) this.f272a).commit();
            this.myLocationIV.setEnabled(true);
        }
        String str = "poi_category_id";
        if (getIntent().hasExtra(str)) {
            this.f274c = getIntent().getStringExtra(str);
            this.f272a.setLocationProvider(false);
        }
        Intent intent = getIntent();
        String str2 = NaviBeesConstants.ROUTE_WAYPOINTS;
        if (intent.hasExtra(str2)) {
            this.f275d = getIntent().getParcelableArrayListExtra(str2);
        }
        Intent intent2 = getIntent();
        String str3 = NaviBeesConstants.ENABLE_ROUTING_WHEN_OUT_OF_VENUE;
        if (intent2.hasExtra(str3)) {
            MapHoverActivity.enableRoutingWhenOutOfVenue = getIntent().getBooleanExtra(str3, false);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.f272a = null;
        MapHoverActivity.selectedOrigin = null;
        showStartPointSearchBar(false);
    }

    public void onEnterOrExitCoverageArea(boolean z) {
        if (!z) {
            Intent intent = getIntent();
            String str = NaviBeesConstants.ENABLE_ROUTING_WHEN_OUT_OF_VENUE;
            if (intent.hasExtra(str)) {
                MapHoverActivity.enableRoutingWhenOutOfVenue = getIntent().getBooleanExtra(str, false);
            }
        } else if (MapHoverActivity.enableRoutingWhenOutOfVenue) {
            onClearRouteClick();
            MapHoverActivity.enableRoutingWhenOutOfVenue = false;
            showStartPointSearchBar(false);
            new Handler().postDelayed(new C1350g(), 500);
        }
    }

    /* access modifiers changed from: protected */
    public void onFloorButtonClick() {
        this.f272a.mo28231g();
    }

    /* access modifiers changed from: protected */
    public void onFloorSelected(Floor floor) {
        this.f272a.mo28207a(floor);
    }

    /* access modifiers changed from: protected */
    public void onMyLocationClick(boolean z) {
        MapFragment mapFragment = this.f272a;
        if (!mapFragment.isOutOfCoverageArea) {
            mapFragment.mo28212a(z);
            this.f272a.showLocation();
        }
    }

    public void onPOISelected(POI poi) {
        if (MapHoverActivity.isClickedOnSearchStartPoint) {
            MapHoverActivity.selectedOrigin = poi;
            if (poi.name != null) {
                this.searchStartEditText.setText(poi.getDescProperty());
            }
            this.clearStartSearchView.setVisibility(0);
            this.f272a.f313r.mo28420h(((VisioPOI) poi).vgId);
            zoomToPOI(poi);
            return;
        }
        super.onPOISelected(poi);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MapHoverActivity.mContext = getApplicationContext();
        m151b();
    }

    /* access modifiers changed from: protected */
    public void onStartNavigationClick() {
        if (MapHoverActivity.enableRoutingWhenOutOfVenue && NaviBeesManager.getInstance(getApplication()).getPositionManager().getLastLocationCoordinateLatLong() == null && MapHoverActivity.selectedOrigin == null) {
            showStartPointSearchBar(true);
            return;
        }
        C1434g gVar = this.f276e;
        if (gVar != null && gVar.isVisible()) {
            this.f276e.hide();
        }
        if (NaviBeesManager.getInstance(getApplication()).getPositionManager().getLastLocationCoordinateLatLong() == null && this.f272a.f313r.mo28419h() && MapHoverActivity.enableRoutingWhenOutOfVenue) {
            FrameLayout frameLayout = (FrameLayout) findViewById(C1164R.C1167id.SurfaceViewFrame);
            MapFragment mapFragment = this.f272a;
            C1434g gVar2 = new C1434g(this, mapFragment.f313r, mapFragment.f312q, frameLayout, this);
            this.f276e = gVar2;
            LinkedList<C1513c> linkedList = this.f272a.f313r.f496B;
            linkedList.removeAll(linkedList);
            this.f272a.f313r.mo28367a((C1513c) this.f276e);
        } else if (this.f272a.f313r.mo28419h()) {
            if (MapHoverActivity.enableRoutingWhenOutOfVenue) {
                MapHoverActivity.enableRoutingWhenOutOfVenue = false;
                this.clearStartSearchView.callOnClick();
            }
            C1432f fVar = new C1432f(this, this.f278g, this.f272a.f312q, (FrameLayout) findViewById(C1164R.C1167id.NavBar), this);
            LinkedList<C1513c> linkedList2 = this.f272a.f313r.f496B;
            linkedList2.removeAll(linkedList2);
            this.f272a.f313r.mo28367a((C1513c) fVar);
        }
        super.onStartNavigationClick();
        ArrayList<POI> arrayList = this.f275d;
        if (arrayList == null || arrayList.size() <= 0) {
            POI poi = this.currentlySelectedPOI;
            if (poi != null) {
                this.f272a.mo28210a((VisioPOI) poi);
            } else {
                IndoorLocation indoorLocation = this.destinationLocation;
                if (indoorLocation != null) {
                    this.f272a.mo28208a(indoorLocation);
                }
            }
        } else {
            POI poi2 = this.currentlySelectedPOI;
            if (poi2 != null) {
                this.f272a.mo28211a((VisioPOI) poi2, (List<POI>) this.f275d);
            } else {
                IndoorLocation indoorLocation2 = this.destinationLocation;
                if (indoorLocation2 != null) {
                    this.f272a.mo28209a(indoorLocation2, (List<POI>) this.f275d);
                }
            }
        }
    }

    public void setIsCompassActive(boolean z) {
        MapFragment mapFragment = this.f272a;
        if (mapFragment != null) {
            mapFragment.setIsCompassActive(z);
        }
    }

    /* access modifiers changed from: protected */
    public void showDestinationMarker(POI poi) {
        if (poi != null && (poi instanceof VisioPOI)) {
            this.f272a.showDestinationMarker((VisioPOI) poi);
        }
    }

    public void showNavigationInstructionsBar(boolean z) {
        if (!MapControls.SHOW_NAVIGATION_VIEW) {
            super.showNavigationInstructionsBar(false);
        } else {
            super.showNavigationInstructionsBar(z);
        }
    }

    /* access modifiers changed from: protected */
    public void showRealLocation(boolean z) {
        if (z) {
            this.f272a.showLocation();
        } else {
            this.f272a.hideLocation();
        }
    }

    /* access modifiers changed from: protected */
    public void stopAutomaticMode() {
        super.stopAutomaticMode();
        MapFragment mapFragment = this.f272a;
        if (mapFragment != null) {
            mapFragment.stopAutomaticMode();
        }
    }

    /* access modifiers changed from: protected */
    public void zoomToLongLatLocation(IndoorLocation indoorLocation) {
        this.f272a.zoomToLongLatLocation(indoorLocation);
    }

    /* access modifiers changed from: protected */
    public void zoomToPOI(POI poi) {
        if (poi != null && (poi instanceof VisioPOI)) {
            this.f272a.zoomToPOI((VisioPOI) poi);
        }
    }

    /* access modifiers changed from: protected */
    public void zoomToUTMLocation(IndoorLocation indoorLocation) {
        this.f272a.zoomToUTMLocation(indoorLocation);
    }

    /* renamed from: b */
    private void m151b() {
        if (!MapControls.SHOW_SEARCH_BAR) {
            findViewById(C1164R.C1167id.search_container).setVisibility(8);
            getSupportActionBar().hide();
        }
        if (!MapControls.SHOW_BACK_BUTTON) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (!MapControls.SHOW_COMPASS_BUTTON) {
            findViewById(C1164R.C1167id.compass_Image).setVisibility(8);
        }
        if (!MapControls.SHOW_POI_INFO_VIEW_BUTTON) {
            this.showBottomSheet = false;
            findViewById(C1164R.C1167id.bottom_sheet).setVisibility(8);
        }
        if (!MapControls.SHOW_BUILDING_BUTTON_VIEW) {
            findViewById(C1164R.C1167id.building_action_button).setVisibility(8);
        }
        boolean z = MapControls.SHOW_NAVIGATE_BUTTON;
        if (!z) {
            hideNavigateButton(z);
        }
        if (!MapControls.SHOW_FLOOR_BUTTON) {
            findViewById(C1164R.C1167id.floor_action_button).setVisibility(8);
        }
        if (!MapControls.SHOW_TRACKER_BUTTON) {
            findViewById(C1164R.C1167id.my_location_fab).setVisibility(8);
        }
        if (!MapControls.SHOW_NAVIGATION_VIEW) {
            findViewById(C1164R.C1167id.NavBar).setVisibility(8);
        }
        if (!MapControls.SHOW_SAVE_LOCATION_BUTTON) {
            this.showSavedLocationButton = false;
        }
    }

    /* renamed from: c */
    private void m153c() {
        this.f273b = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue().getPOIsOfCategory(this.f274c);
        LinkedHashMap a = m145a();
        if (NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue().buildings.size() > 1) {
            m147a((HashMap<String, HashMap<Integer, List<POI>>>) a);
            return;
        }
        r1 = null;
        for (String str : a.keySet()) {
        }
        if (str != null) {
            m148a((HashMap) a.get(str), str);
        }
    }

    /* renamed from: a */
    private Building m144a(String str) {
        for (int i = 0; i < NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue().buildings.size(); i++) {
            Building building = (Building) NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue().buildings.valueAt(i);
            if (building.f1329id.equalsIgnoreCase(str)) {
                return building;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void showDestinationMarker(IndoorLocation indoorLocation) {
        this.f272a.showDestinationMarker(indoorLocation);
    }

    /* renamed from: a */
    private void m147a(HashMap<String, HashMap<Integer, List<POI>>> hashMap) {
        Builder builder = new Builder(this);
        builder.setTitle(getString(C1164R.string.dialog_title_select_building));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367058);
        for (String str : hashMap.keySet()) {
            arrayAdapter.add(((Building) NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue().buildings.get(str)).name.getText());
        }
        builder.setAdapter(arrayAdapter, new C1344a(arrayAdapter, hashMap));
        runOnUiThread(new C1345b(this, builder));
    }

    /* access modifiers changed from: protected */
    public void initiateRouting(Object obj, MapHoverInterface mapHoverInterface) {
        super.initiateRouting(obj, mapHoverInterface);
        ArrayList<POI> arrayList = this.f275d;
        if (arrayList == null) {
            this.f272a.initiateRouting(obj, mapHoverInterface);
        } else if (arrayList.size() > 0) {
            this.f272a.initiateRouting(obj, this.f275d, mapHoverInterface);
        }
    }

    /* access modifiers changed from: protected */
    public void initiateRouting(IndoorLocation indoorLocation, String str, MapHoverInterface mapHoverInterface) {
        super.initiateRouting(indoorLocation, str, mapHoverInterface);
        if (indoorLocation != null) {
            ArrayList<POI> arrayList = this.f275d;
            if (arrayList == null) {
                this.f272a.initiateRouting(indoorLocation, mapHoverInterface);
            } else if (arrayList.size() > 0) {
                this.f272a.initiateRouting(indoorLocation, this.f275d, mapHoverInterface);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m148a(HashMap<Integer, List<POI>> hashMap, String str) {
        Builder builder = new Builder(this);
        builder.setTitle(getString(C1164R.string.dialog_title_select_floor));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367058);
        IndoorLocation lastLocationCoordinateLatLong = NaviBeesManager.getInstance(getApplication()).getPositionManager().getLastLocationCoordinateLatLong();
        Building a = m144a(str);
        if (hashMap.keySet().size() == 1) {
            Iterator it = hashMap.keySet().iterator();
            if (it.hasNext()) {
                Integer num = (Integer) it.next();
                Floor floor = null;
                for (Floor floor2 : a.floors) {
                    if (floor2.floorIndex == num.intValue()) {
                        floor = floor2;
                    }
                }
                if (this.f277f) {
                    this.f272a.showDestinationMarkers(this.f273b, a, floor);
                } else {
                    this.f272a.showDestinationMarkers(this.f273b, null, floor);
                }
            }
            return;
        }
        for (Integer num2 : hashMap.keySet()) {
            if (lastLocationCoordinateLatLong == null || lastLocationCoordinateLatLong.floor != num2.intValue()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Floor ");
                sb.append(num2);
                arrayAdapter.add(sb.toString());
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Current Floor: ");
                sb2.append(num2);
                arrayAdapter.add(sb2.toString());
            }
        }
        builder.setAdapter(arrayAdapter, new C1346c(arrayAdapter, a));
        runOnUiThread(new C1347d(this, builder));
    }

    /* renamed from: a */
    private LinkedHashMap<String, HashMap<Integer, List<POI>>> m145a() {
        LinkedHashMap<String, HashMap<Integer, List<POI>>> linkedHashMap = new LinkedHashMap<>();
        Iterator it = this.f273b.iterator();
        while (it.hasNext()) {
            VisioPOI visioPOI = (VisioPOI) it.next();
            if (linkedHashMap.containsKey(visioPOI.buildingId)) {
                HashMap hashMap = (HashMap) linkedHashMap.get(visioPOI.buildingId);
                if (hashMap.containsKey(Integer.valueOf(visioPOI.floorIndex))) {
                    List list = (List) hashMap.get(Integer.valueOf(visioPOI.floorIndex));
                    list.add(visioPOI);
                    hashMap.put(Integer.valueOf(visioPOI.floorIndex), list);
                    linkedHashMap.put(visioPOI.buildingId, hashMap);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(visioPOI);
                    hashMap.put(Integer.valueOf(visioPOI.floorIndex), arrayList);
                    linkedHashMap.put(visioPOI.buildingId, hashMap);
                }
            } else {
                HashMap hashMap2 = new HashMap();
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(visioPOI);
                hashMap2.put(Integer.valueOf(visioPOI.floorIndex), arrayList2);
                linkedHashMap.put(visioPOI.buildingId, hashMap2);
            }
        }
        if (linkedHashMap.keySet().size() > 1) {
            this.f277f = true;
        }
        return linkedHashMap;
    }
}
