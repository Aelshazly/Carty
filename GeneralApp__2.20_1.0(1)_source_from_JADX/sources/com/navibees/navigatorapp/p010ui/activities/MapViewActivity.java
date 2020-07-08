package com.navibees.navigatorapp.p010ui.activities;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import androidx.collection.SimpleArrayMap;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.firebase.dynamiclinks.DynamicLink.AndroidParameters.Builder;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.navibees.core.NaviBeesConstants.MapInteraction;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.IndoorLocationConfidence;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.maps.MapActivity;
import com.navibees.maps.MapControls;
import com.navibees.navigatorapp.BuildConfig;
import com.navibees.navigatorapp.C1170R;
import com.navibees.navigatorapp.utils.ShareUtility;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import p008cz.msebera.android.httpclient.protocol.HTTP;

/* renamed from: com.navibees.navigatorapp.ui.activities.MapViewActivity */
public class MapViewActivity extends MapActivity {
    private Uri dynamicLink;
    IndoorLocation indoorLocation;
    private final BroadcastReceiver shareLocationReceiver = new BroadcastReceiver() {
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0059  */
        /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceive(android.content.Context r10, android.content.Intent r11) {
            /*
                r9 = this;
                java.lang.String r0 = r11.getAction()
                java.lang.String r1 = "com.navibees.action_share_location"
                boolean r0 = r1.equals(r0)
                if (r0 == 0) goto L_0x00ad
                r0 = 0
                r1 = 0
                android.os.Bundle r2 = r11.getExtras()
                if (r2 == 0) goto L_0x002e
                android.os.Bundle r2 = r11.getExtras()
                java.lang.String r3 = "com.navibees.shared_location_key"
                boolean r2 = r2.containsKey(r3)
                if (r2 == 0) goto L_0x002e
                android.os.Parcelable r2 = r11.getParcelableExtra(r3)
                com.navibees.core.model.metadata.json.IndoorLocation r2 = (com.navibees.core.model.metadata.json.IndoorLocation) r2
                int r3 = r2.floor
                r4 = -1
                if (r3 == r4) goto L_0x002d
                r0 = r2
                r1 = 0
            L_0x002d:
                goto L_0x0057
            L_0x002e:
                com.navibees.navigatorapp.ui.activities.MapViewActivity r2 = com.navibees.navigatorapp.p010ui.activities.MapViewActivity.this
                android.app.Application r2 = r2.getApplication()
                com.navibees.core.NaviBeesManager r2 = com.navibees.core.NaviBeesManager.getInstance(r2)
                com.navibees.core.model.postioning.PositionManager r2 = r2.getPositionManager()
                if (r2 == 0) goto L_0x004a
                com.navibees.core.model.metadata.json.IndoorLocation r3 = r2.getLastLocationCoordinateLatLong()
                if (r3 == 0) goto L_0x004a
                com.navibees.core.model.metadata.json.IndoorLocation r0 = r2.getLastLocationCoordinateLatLong()
                r1 = 1
                goto L_0x0057
            L_0x004a:
                com.navibees.navigatorapp.ui.activities.MapViewActivity r3 = com.navibees.navigatorapp.p010ui.activities.MapViewActivity.this
                r4 = 2131886352(0x7f120110, float:1.940728E38)
                r5 = 0
                android.widget.Toast r3 = android.widget.Toast.makeText(r3, r4, r5)
                r3.show()
            L_0x0057:
                if (r0 == 0) goto L_0x00ad
                com.navibees.navigatorapp.ui.activities.MapViewActivity r2 = com.navibees.navigatorapp.p010ui.activities.MapViewActivity.this
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                double r4 = r0.f1332x
                r3.append(r4)
                java.lang.String r4 = ""
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                double r6 = r0.f1333y
                r5.append(r6)
                r5.append(r4)
                java.lang.String r5 = r5.toString()
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                int r7 = r0.floor
                r6.append(r7)
                r6.append(r4)
                java.lang.String r6 = r6.toString()
                java.lang.String r7 = r0.buildingId
                java.lang.String r8 = r0.venueId
                r4 = r5
                r5 = r6
                r6 = r7
                r7 = r8
                java.util.Map r2 = r2.getCustomParamsMap(r3, r4, r5, r6, r7)
                com.navibees.navigatorapp.ui.activities.MapViewActivity r3 = com.navibees.navigatorapp.p010ui.activities.MapViewActivity.this
                android.net.Uri r3 = r3.getDeepLink(r2)
                if (r3 == 0) goto L_0x00ad
                com.navibees.navigatorapp.ui.activities.MapViewActivity r4 = com.navibees.navigatorapp.p010ui.activities.MapViewActivity.this
                java.lang.String r5 = r3.toString()
                r4.shareDeepLink(r5, r1)
            L_0x00ad:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.navibees.navigatorapp.p010ui.activities.MapViewActivity.C12503.onReceive(android.content.Context, android.content.Intent):void");
        }
    };
    /* access modifiers changed from: private */
    public Venue venue;
    private SimpleArrayMap<String, Venue> venues;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(C1170R.style.MapActivityTheme);
        this.venues = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getVenues();
        MapControls.SHOW_POPUP_VIEW = true;
        MapControls.SHOW_BACK_BUTTON = true;
        if (this.venues == null) {
            finish();
        }
        this.venue = (Venue) this.venues.valueAt(0);
        String str = "deeplink";
        if (getIntent().hasExtra(str)) {
            processReceivedDynamicLink(getIntent().getStringExtra(str));
        } else {
            this.venue = (Venue) this.venues.valueAt(0);
        }
    }

    public void onPOISelected(POI selectedPOI) {
        if (selectedPOI != null && selectedPOI.properties != null) {
            if (PreferenceManager.getDefaultSharedPreferences(mContext).getString(getString(C1170R.string.preference_gender), "Male").equalsIgnoreCase("Female")) {
                String data = (String) selectedPOI.properties.get("FemaleWaypoints");
                if (data != null) {
                    String[] arr = data.split(",");
                    this.f275d = new ArrayList();
                    for (String id : arr) {
                        for (POI poi : NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue().getPOIs()) {
                            if (poi.f1339id.equalsIgnoreCase(id)) {
                                this.f275d.add(poi);
                            }
                        }
                    }
                } else {
                    return;
                }
            }
            super.onPOISelected(selectedPOI);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.shareLocationReceiver, new IntentFilter(MapInteraction.ACTION_SHARE_LOCATION));
        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {
                    if (MapViewActivity.this.getIsMapLoaded()) {
                        MapViewActivity.this.zoomToUTMLocation(MapViewActivity.this.venue.initialLocation);
                        MapViewActivity.this.onMyLocationClick(true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 1200);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.shareLocationReceiver);
    }

    /* access modifiers changed from: private */
    public Uri getDeepLink(Map<String, String> customParameters) {
        String link = "https://www.navibees.com";
        String queryParameters = "";
        if (customParameters == null) {
            return null;
        }
        try {
            queryParameters = ShareUtility.generateQueryParameters(customParameters);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(link);
        sb.append("?");
        sb.append(queryParameters);
        return FirebaseDynamicLinks.getInstance().createDynamicLink().setLink(Uri.parse(sb.toString())).setDomainUriPrefix("https://navigatorapp.page.link").setAndroidParameters(new Builder(BuildConfig.APPLICATION_ID).build()).buildDynamicLink().getUri();
    }

    private void processReceivedDynamicLink(String link) {
        if (link != null) {
            String link2 = link.split("\\?")[1];
            System.out.println(link2);
            String[] paramsArr = link2.split("&");
            Map<String, String> customParameters = new HashMap<>();
            for (String s : paramsArr) {
                String[] arr = s.split("=");
                customParameters.put(arr[0], arr[1]);
            }
            double x = Double.valueOf((String) customParameters.get("x")).doubleValue();
            double y = Double.valueOf((String) customParameters.get("y")).doubleValue();
            String venue2 = (String) customParameters.get("venue");
            String building = (String) customParameters.get("building");
            int floor_index = Integer.valueOf((String) customParameters.get("floor_index")).intValue();
            this.indoorLocation = new IndoorLocation(x, y);
            IndoorLocation indoorLocation2 = this.indoorLocation;
            indoorLocation2.floor = floor_index;
            indoorLocation2.buildingId = building;
            indoorLocation2.venueId = venue2;
            indoorLocation2.confidence = IndoorLocationConfidence.High;
            this.venue = (Venue) this.venues.get(venue2);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    try {
                        if (MapViewActivity.this.indoorLocation != null) {
                            MapViewActivity.this.initiateRouting(MapViewActivity.this.indoorLocation, MapViewActivity.this.getString(C1170R.string.share_location_dialog_title), MapViewActivity.this);
                            MapViewActivity.this.zoomToLongLatLocation(MapViewActivity.this.indoorLocation);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }, 1200);
        }
    }

    /* access modifiers changed from: private */
    public Map<String, String> getCustomParamsMap(String x, String y, String floorIndex, String buildingId, String venueId) {
        if (x == null || y == null || floorIndex == null || buildingId == null) {
            return null;
        }
        Map<String, String> customParameters = new HashMap<>();
        customParameters.put("x", x);
        customParameters.put("y", y);
        customParameters.put("floor_index", floorIndex);
        customParameters.put("building", buildingId);
        customParameters.put("venue", venueId);
        return customParameters;
    }

    /* access modifiers changed from: private */
    public void shareDeepLink(String dynamicLink2, boolean isMyLocation) {
        String msg;
        if (!TextUtils.isEmpty(dynamicLink2)) {
            String str = "\n";
            if (isMyLocation) {
                String string = getResources().getString(C1170R.string.share_my_location_subject);
                StringBuilder sb = new StringBuilder();
                sb.append(getResources().getString(C1170R.string.share_my_location_msg));
                sb.append(str);
                sb.append(dynamicLink2);
                msg = sb.toString();
            } else {
                String string2 = getResources().getString(C1170R.string.share_location_subject);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(getResources().getString(C1170R.string.share_location_msg));
                sb2.append(str);
                sb2.append(dynamicLink2);
                msg = sb2.toString();
            }
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType(HTTP.PLAIN_TEXT_TYPE);
            intent.putExtra("android.intent.extra.TEXT", msg);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(intent, "Share"));
            }
        }
    }
}
