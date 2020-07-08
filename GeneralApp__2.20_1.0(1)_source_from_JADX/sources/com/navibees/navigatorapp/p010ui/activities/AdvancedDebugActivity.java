package com.navibees.navigatorapp.p010ui.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.android.material.textfield.TextInputEditText;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.activity.NaviBeesActivity;
import com.navibees.core.model.metadata.BeaconNode;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Floor;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.postioning.AdvancedLocationListener;
import com.navibees.core.model.postioning.BeaconNodeListener;
import com.navibees.core.model.postioning.NBLocationListener;
import com.navibees.core.model.postioning.PositionLocator.CalculatedLocation;
import com.navibees.core.model.postioning.PositionLocator.LOCALIZER_ALGORITHM;
import com.navibees.core.model.postioning.PositionManager;
import com.navibees.core.model.postioning.RSSIMAP;
import com.navibees.core.model.postioning.TrillaterationPositionLocator;
import com.navibees.maps.MapFragment;
import com.navibees.navigatorapp.C1170R;
import com.navibees.navigatorapp.data.Prefs;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/* renamed from: com.navibees.navigatorapp.ui.activities.AdvancedDebugActivity */
public class AdvancedDebugActivity extends NaviBeesActivity {
    /* access modifiers changed from: private */
    public Adapter beaconListAdapter;
    private BeaconNodeListener beaconNodeListener;
    /* access modifiers changed from: private */
    public Map<String, BeaconNode> beaconNodeMap = new HashMap();
    /* access modifiers changed from: private */
    public List<BeaconNode> beaconNodes = new ArrayList();
    boolean bothAlgo = false;
    /* access modifiers changed from: private */
    public Queue<CalculatedLocation> calculatedLocationQueue = new ArrayBlockingQueue(10);
    private final long clearListAfter = 5000;
    AdvancedLocationListener continuousListener = new AdvancedLocationListener() {
        public void locationCallback(CalculatedLocation rawLocation, CalculatedLocation currentLocation, boolean isWalking) {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            CalculatedLocation calculatedLocation = rawLocation;
            CalculatedLocation calculatedLocation2 = currentLocation;
            Floor floor = null;
            for (Floor f : ((Building) NaviBeesManager.getInstance(AdvancedDebugActivity.this.getApplication()).getMetaDataManager().getBuildings().get(calculatedLocation2.indoorLocation.buildingId)).floors) {
                if (f.floorIndex == calculatedLocation2.indoorLocation.floor) {
                    floor = f;
                }
            }
            TextView access$300 = AdvancedDebugActivity.this.txtIsWalking;
            StringBuilder sb = new StringBuilder();
            sb.append("is Walking: ");
            sb.append(isWalking);
            access$300.setText(sb.toString());
            String str6 = "PC-WC: ";
            String str7 = "\nmeanRSSI:";
            String str8 = "\nMinor:";
            String str9 = "Major: ";
            String str10 = "#.#";
            String str11 = "\n Loc diff: ";
            String str12 = " Count: ";
            if (PositionManager.ALGO_TO_USE == LOCALIZER_ALGORITHM.WEIGHTED_CENTROID) {
                TextView access$1400 = AdvancedDebugActivity.this.txtPathConfidenceWC;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str6);
                sb2.append(calculatedLocation2.f1367pc);
                sb2.append(str12);
                sb2.append(calculatedLocation2.pcCounter);
                sb2.append(str11);
                str2 = str6;
                sb2.append(new DecimalFormat(str10).format(calculatedLocation2.locDifference));
                access$1400.setText(sb2.toString());
                AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(calculatedLocation2.indoorLocation, 1, floor, (String) null);
                int green = 1;
                int i = 0;
                while (i < calculatedLocation2.beaconNodes.size()) {
                    BeaconNode beacon = (BeaconNode) calculatedLocation2.beaconNodes.get(i);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str9);
                    sb3.append(beacon.major);
                    sb3.append(str8);
                    sb3.append(beacon.minor);
                    sb3.append(str7);
                    String str13 = str7;
                    String str14 = str8;
                    sb3.append(beacon.meanRSSI());
                    AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(beacon, green + 10, floor, sb3.toString());
                    green++;
                    i++;
                    str7 = str13;
                    str8 = str14;
                }
                str3 = str7;
                str = str8;
            } else {
                str2 = str6;
                str3 = str7;
                str = str8;
            }
            String str15 = "PC-Tri: ";
            if (PositionManager.ALGO_TO_USE == LOCALIZER_ALGORITHM.TRILLATERATION) {
                TextView access$1500 = AdvancedDebugActivity.this.txtPathConfidenceTri;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str15);
                sb4.append(calculatedLocation2.f1367pc);
                sb4.append(str12);
                sb4.append(calculatedLocation2.pcCounter);
                sb4.append(str11);
                str5 = str15;
                sb4.append(new DecimalFormat(str10).format(calculatedLocation2.locDifference));
                access$1500.setText(sb4.toString());
                AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(calculatedLocation2.indoorLocation, 2, floor, (String) null);
                int red = 1;
                int i2 = 0;
                while (i2 < calculatedLocation2.beaconNodes.size()) {
                    BeaconNode beacon2 = (BeaconNode) calculatedLocation2.beaconNodes.get(i2);
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(str9);
                    sb5.append(beacon2.major);
                    String str16 = str;
                    sb5.append(str16);
                    sb5.append(beacon2.minor);
                    sb5.append(str3);
                    String str17 = str16;
                    sb5.append(beacon2.meanRSSI());
                    AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(beacon2, red + 20, floor, sb5.toString());
                    red++;
                    i2++;
                    str = str17;
                }
                str4 = str;
            } else {
                str5 = str15;
                str4 = str;
            }
            if (PositionManager.ALGO_TO_USE == LOCALIZER_ALGORITHM.BOTH) {
                AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(calculatedLocation2.indoorLocation, 1, floor, (String) null);
                AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(calculatedLocation.indoorLocation, 2, floor, (String) null);
                TextView access$14002 = AdvancedDebugActivity.this.txtPathConfidenceWC;
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str2);
                sb6.append(calculatedLocation2.f1367pc);
                sb6.append(str12);
                sb6.append(calculatedLocation2.pcCounter);
                sb6.append(str11);
                sb6.append(new DecimalFormat(str10).format(calculatedLocation2.locDifference));
                access$14002.setText(sb6.toString());
                TextView access$15002 = AdvancedDebugActivity.this.txtPathConfidenceTri;
                StringBuilder sb7 = new StringBuilder();
                sb7.append(str5);
                sb7.append(calculatedLocation.f1367pc);
                sb7.append(str12);
                sb7.append(calculatedLocation.pcCounter);
                sb7.append(str11);
                sb7.append(new DecimalFormat(str10).format(calculatedLocation.locDifference));
                access$15002.setText(sb7.toString());
                int red2 = 1;
                int i3 = 0;
                while (i3 < calculatedLocation2.beaconNodes.size()) {
                    BeaconNode beacon3 = (BeaconNode) calculatedLocation2.beaconNodes.get(i3);
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append(str9);
                    sb8.append(beacon3.major);
                    String str18 = str4;
                    sb8.append(str18);
                    sb8.append(beacon3.minor);
                    sb8.append(str3);
                    sb8.append(beacon3.meanRSSI());
                    String beaconInfo = sb8.toString();
                    if (red2 <= 3) {
                        AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(beacon3, red2 + 10, floor, beaconInfo);
                    } else {
                        AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(beacon3, red2 + 20, floor, beaconInfo);
                    }
                    red2++;
                    i3++;
                    str4 = str18;
                }
            }
        }
    };
    BroadcastReceiver dbgRssiReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String val = intent.getStringExtra("value");
            RSSIMAP obj = (RSSIMAP) intent.getSerializableExtra("map");
            if (AdvancedDebugActivity.this.listening) {
                AdvancedDebugActivity.this.floorRssiTextView.setText(val);
                String text = "";
                String str = ")";
                String str2 = ":";
                if (obj.map.containsKey(Integer.valueOf(PositionManager.currentFloor))) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(text);
                    sb.append("MR(F: ");
                    sb.append(PositionManager.currentFloor);
                    sb.append(str2);
                    sb.append(((Double) obj.map.get(Integer.valueOf(PositionManager.currentFloor))).intValue());
                    sb.append(str);
                    text = sb.toString();
                }
                if (obj.map.containsKey(Integer.valueOf(PositionManager.newFloor))) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(text);
                    sb2.append(" | NR: (F:");
                    sb2.append(PositionManager.newFloor);
                    sb2.append(str2);
                    sb2.append(((Double) obj.map.get(Integer.valueOf(PositionManager.newFloor))).intValue());
                    sb2.append(str);
                    text = sb2.toString();
                }
                AdvancedDebugActivity.this.mr_nr.setText(text);
            }
        }
    };
    /* access modifiers changed from: private */
    public TextView floorRssiTextView;
    /* access modifiers changed from: private */
    public long lastCleared = 0;
    /* access modifiers changed from: private */
    public boolean listening;
    /* access modifiers changed from: private */
    public TextView locationTextView;
    /* access modifiers changed from: private */
    public MapFragment mapFragment;
    /* access modifiers changed from: private */
    public TextView mr_nr;
    /* access modifiers changed from: private */
    public Button pathConfidence;
    private boolean pathConfidenceEnabled = false;
    private final int positionsThreshold = 10;
    private RecyclerView recyclerView;
    private Button startStop;
    AdvancedLocationListener stepByStepListener = new AdvancedLocationListener() {
        public void locationCallback(CalculatedLocation rawLocation, CalculatedLocation currentLocation, boolean isWalking) {
            String str;
            String str2;
            CalculatedLocation calculatedLocation = rawLocation;
            CalculatedLocation calculatedLocation2 = currentLocation;
            Floor floor = null;
            for (Floor f : ((Building) NaviBeesManager.getInstance(AdvancedDebugActivity.this.getApplication()).getMetaDataManager().getBuildings().get(calculatedLocation2.indoorLocation.buildingId)).floors) {
                if (f.floorIndex == calculatedLocation2.indoorLocation.floor) {
                    floor = f;
                }
            }
            TextView access$300 = AdvancedDebugActivity.this.txtIsWalking;
            StringBuilder sb = new StringBuilder();
            sb.append("is Walking: ");
            sb.append(isWalking);
            access$300.setText(sb.toString());
            String str3 = "PC-WC: ";
            String str4 = "\nmeanRSSI:";
            String str5 = "\nMinor:";
            String str6 = "Major: ";
            String str7 = "#.#";
            String str8 = "\n Loc diff: ";
            String str9 = " Count: ";
            if (PositionManager.ALGO_TO_USE == LOCALIZER_ALGORITHM.WEIGHTED_CENTROID) {
                AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(calculatedLocation2.indoorLocation, 1, floor, (String) null);
                TextView access$1400 = AdvancedDebugActivity.this.txtPathConfidenceWC;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str3);
                sb2.append(calculatedLocation2.f1367pc);
                sb2.append(str9);
                sb2.append(calculatedLocation2.pcCounter);
                sb2.append(str8);
                str = str3;
                sb2.append(new DecimalFormat(str7).format(calculatedLocation2.locDifference));
                access$1400.setText(sb2.toString());
                int green = 1;
                for (int i = 0; i < calculatedLocation2.beaconNodes.size(); i++) {
                    BeaconNode beacon = (BeaconNode) calculatedLocation2.beaconNodes.get(i);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str6);
                    sb3.append(beacon.major);
                    sb3.append(str5);
                    sb3.append(beacon.minor);
                    sb3.append(str4);
                    sb3.append(beacon.meanRSSI());
                    AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(beacon, green + 10, floor, sb3.toString());
                    green++;
                }
            } else {
                str = str3;
            }
            String str10 = "PC-Tri: ";
            if (PositionManager.ALGO_TO_USE == LOCALIZER_ALGORITHM.TRILLATERATION) {
                AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(calculatedLocation2.indoorLocation, 2, floor, (String) null);
                TextView access$1500 = AdvancedDebugActivity.this.txtPathConfidenceTri;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str10);
                sb4.append(calculatedLocation2.f1367pc);
                sb4.append(str9);
                sb4.append(calculatedLocation2.pcCounter);
                sb4.append(str8);
                String str11 = str4;
                sb4.append(new DecimalFormat(str7).format(calculatedLocation2.locDifference));
                access$1500.setText(sb4.toString());
                int red = 1;
                int i2 = 0;
                while (i2 < calculatedLocation2.beaconNodes.size()) {
                    BeaconNode beacon2 = (BeaconNode) calculatedLocation2.beaconNodes.get(i2);
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(str6);
                    sb5.append(beacon2.major);
                    sb5.append(str5);
                    sb5.append(beacon2.minor);
                    String str12 = str11;
                    sb5.append(str12);
                    String str13 = str12;
                    sb5.append(beacon2.meanRSSI());
                    AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(beacon2, red + 20, floor, sb5.toString());
                    red++;
                    i2++;
                    str11 = str13;
                }
                str2 = str11;
            } else {
                str2 = str4;
            }
            if (PositionManager.ALGO_TO_USE == LOCALIZER_ALGORITHM.BOTH) {
                AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(calculatedLocation2.indoorLocation, 1, floor, (String) null);
                AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(calculatedLocation.indoorLocation, 2, floor, (String) null);
                TextView access$14002 = AdvancedDebugActivity.this.txtPathConfidenceWC;
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str);
                sb6.append(calculatedLocation2.f1367pc);
                sb6.append(str9);
                sb6.append(calculatedLocation2.pcCounter);
                sb6.append(str8);
                sb6.append(new DecimalFormat(str7).format(calculatedLocation2.locDifference));
                access$14002.setText(sb6.toString());
                TextView access$15002 = AdvancedDebugActivity.this.txtPathConfidenceTri;
                StringBuilder sb7 = new StringBuilder();
                sb7.append(str10);
                sb7.append(calculatedLocation.f1367pc);
                sb7.append(str9);
                sb7.append(calculatedLocation.pcCounter);
                sb7.append(str8);
                sb7.append(new DecimalFormat(str7).format(calculatedLocation.locDifference));
                access$15002.setText(sb7.toString());
                int red2 = 1;
                int i3 = 0;
                while (i3 < calculatedLocation2.beaconNodes.size()) {
                    BeaconNode beacon3 = (BeaconNode) calculatedLocation2.beaconNodes.get(i3);
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append(str6);
                    sb8.append(beacon3.major);
                    sb8.append(str5);
                    sb8.append(beacon3.minor);
                    String str14 = str2;
                    sb8.append(str14);
                    sb8.append(beacon3.meanRSSI());
                    String beaconInfo = sb8.toString();
                    if (red2 <= 3) {
                        AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(beacon3, red2 + 10, floor, beaconInfo);
                    } else {
                        AdvancedDebugActivity.this.mapFragment.addMarkerAtLocation(beacon3, red2 + 20, floor, beaconInfo);
                    }
                    red2++;
                    i3++;
                    str2 = str14;
                }
            }
            NaviBeesManager.getInstance(AdvancedDebugActivity.this.getApplication()).getPositionManager().removeAdvancedLocationListeners();
        }
    };
    private Toolbar toolbar;
    boolean triAlgo = false;
    /* access modifiers changed from: private */
    public TextView txtIsWalking;
    /* access modifiers changed from: private */
    public TextView txtPathConfidenceTri;
    /* access modifiers changed from: private */
    public TextView txtPathConfidenceWC;
    boolean wcAlgo = false;

    /* renamed from: com.navibees.navigatorapp.ui.activities.AdvancedDebugActivity$BeaconViewHolder */
    private class BeaconViewHolder extends ViewHolder {
        private TextView detail;
        private TextView major;
        private TextView minor;
        private TextView rssi;

        public BeaconViewHolder(View itemView) {
            super(itemView);
            this.major = (TextView) itemView.findViewById(C1170R.C1173id.majorValue);
            this.minor = (TextView) itemView.findViewById(C1170R.C1173id.minorValue);
            this.rssi = (TextView) itemView.findViewById(C1170R.C1173id.rssiValue);
            this.detail = (TextView) itemView.findViewById(C1170R.C1173id.detail);
        }

        public void bind(BeaconNode beaconNode) {
            TextView textView = this.major;
            StringBuilder sb = new StringBuilder();
            String str = "";
            sb.append(str);
            sb.append(beaconNode.major);
            textView.setText(sb.toString());
            TextView textView2 = this.minor;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(beaconNode.minor);
            textView2.setText(sb2.toString());
            TextView textView3 = this.rssi;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(beaconNode.meanRSSI());
            textView3.setText(sb3.toString());
            TextView textView4 = this.detail;
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            sb4.append(beaconNode.location.floor);
            textView4.setText(sb4.toString());
        }
    }

    public static void open(Activity activity) {
        activity.startActivity(new Intent(activity, AdvancedDebugActivity.class));
    }

    private void registerDbgRssiListener() {
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(this.dbgRssiReceiver, new IntentFilter("avg_rssi_dbg"));
    }

    private void unRegisterDbgRssiListener() {
        if (this.dbgRssiReceiver != null) {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(this.dbgRssiReceiver);
        }
    }

    private void listenForCurrentLocation() {
        NaviBeesManager.getInstance(getApplication()).getPositionManager().addLocationListener(new NBLocationListener() {
            public void locationCallback(IndoorLocation currentLocationWithoutSmoothing, IndoorLocation currentLocationAfterSmoothing, int numOfValidBeacons, boolean isIndoor, boolean isWalking) {
                if (currentLocationAfterSmoothing != null) {
                    TextView access$300 = AdvancedDebugActivity.this.txtIsWalking;
                    StringBuilder sb = new StringBuilder();
                    sb.append("is Walking: ");
                    sb.append(isWalking);
                    access$300.setText(sb.toString());
                    if (AdvancedDebugActivity.this.listening) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Confidence: ");
                        sb2.append(currentLocationAfterSmoothing.confidence.name());
                        sb2.append(" | FC: ");
                        sb2.append(PositionManager.changeFloorCounter);
                        AdvancedDebugActivity.this.locationTextView.setText(sb2.toString());
                    }
                }
            }

            public boolean isBackground() {
                return false;
            }
        });
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r5) {
        /*
            r4 = this;
            super.onCreate(r5)
            android.content.Context r0 = r4.getApplicationContext()
            com.navibees.navigatorapp.data.Prefs r0 = com.navibees.navigatorapp.data.Prefs.getInstance(r0)
            java.lang.String r0 = r0.getOperationVenueName()
            int r1 = r0.hashCode()
            r2 = -442449034(0xffffffffe5a0c376, float:-9.489803E22)
            r3 = 1
            if (r1 == r2) goto L_0x0029
            r2 = 2304049(0x232831, float:3.22866E-39)
            if (r1 == r2) goto L_0x001f
        L_0x001e:
            goto L_0x0033
        L_0x001f:
            java.lang.String r1 = "KFMC"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x001e
            r0 = 1
            goto L_0x0034
        L_0x0029:
            java.lang.String r1 = "Masjid_ul_Haram"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x001e
            r0 = 0
            goto L_0x0034
        L_0x0033:
            r0 = -1
        L_0x0034:
            if (r0 == 0) goto L_0x0046
            if (r0 == r3) goto L_0x003f
            r0 = 2131951625(0x7f130009, float:1.953967E38)
            r4.setTheme(r0)
            goto L_0x004d
        L_0x003f:
            r0 = 2131951626(0x7f13000a, float:1.9539672E38)
            r4.setTheme(r0)
            goto L_0x004d
        L_0x0046:
            r0 = 2131951627(0x7f13000b, float:1.9539674E38)
            r4.setTheme(r0)
        L_0x004d:
            r0 = 2131558430(0x7f0d001e, float:1.8742176E38)
            r4.setContentView(r0)
            r4.initView()
            r4.addMapFragment()
            r4.startListeningBeacons()
            r4.listenForCurrentLocation()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.navigatorapp.p010ui.activities.AdvancedDebugActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        registerDbgRssiListener();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        unRegisterDbgRssiListener();
    }

    /* access modifiers changed from: private */
    public void startListeningBeacons() {
        this.startStop.setText("Stop");
        String str = "";
        this.locationTextView.setText(str);
        this.floorRssiTextView.setText(str);
        this.beaconNodeMap.clear();
        this.beaconNodes.clear();
        this.beaconListAdapter.notifyDataSetChanged();
        this.listening = true;
        NaviBeesManager.getInstance(getApplication()).getNaviBeesBeaconManager().addBeaconNodeListener(getBeaconNodeListener());
    }

    /* access modifiers changed from: private */
    public void stopListening() {
        this.startStop.setText("Start");
        this.listening = false;
        NaviBeesManager.getInstance(getApplication()).getNaviBeesBeaconManager().removeBeaconNodeListener(getBeaconNodeListener());
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void addMapFragment() {
        this.mapFragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().add((int) C1170R.C1173id.content, (Fragment) this.mapFragment).commit();
        this.mapFragment.onMapLoaded(new Runnable() {
            public void run() {
                AdvancedDebugActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        AdvancedDebugActivity.this.setupMap();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void setupMap() {
        this.mapFragment.setLocationProvider(true);
    }

    private void initView() {
        this.txtPathConfidenceWC = (TextView) findViewById(C1170R.C1173id.txtPathConfidenceWC);
        this.txtPathConfidenceTri = (TextView) findViewById(C1170R.C1173id.txtPathConfidenceTri);
        this.txtIsWalking = (TextView) findViewById(C1170R.C1173id.txtIsWalking);
        this.recyclerView = (RecyclerView) findViewById(C1170R.C1173id.beacon_list);
        this.floorRssiTextView = (TextView) findViewById(C1170R.C1173id.dbg_rssi);
        setupBeaconListView();
        this.startStop = (Button) findViewById(C1170R.C1173id.start_stop);
        this.pathConfidence = (Button) findViewById(C1170R.C1173id.path_confidence);
        this.locationTextView = (TextView) findViewById(C1170R.C1173id.location_tv);
        this.mr_nr = (TextView) findViewById(C1170R.C1173id.mr_nr);
        this.startStop.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (AdvancedDebugActivity.this.listening) {
                    AdvancedDebugActivity.this.stopListening();
                } else {
                    AdvancedDebugActivity.this.startListeningBeacons();
                }
            }
        });
        this.pathConfidence.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (PositionManager.PATH_CONFIDENCE_ON) {
                    PositionManager.PATH_CONFIDENCE_ON = false;
                    AdvancedDebugActivity.this.pathConfidence.setText("Enable PC");
                    AdvancedDebugActivity.this.pathConfidence.setTextColor(SupportMenu.CATEGORY_MASK);
                    return;
                }
                PositionManager.PATH_CONFIDENCE_ON = true;
                AdvancedDebugActivity.this.pathConfidence.setText("Disable PC");
                AdvancedDebugActivity.this.pathConfidence.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            }
        });
        if (!PositionManager.PATH_CONFIDENCE_ON) {
            this.pathConfidence.setText("Enable PC");
            this.pathConfidence.setTextColor(SupportMenu.CATEGORY_MASK);
        } else {
            this.pathConfidence.setText("Disable PC");
            this.pathConfidence.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        }
        findViewById(C1170R.C1173id.btnSettings).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AdvancedDebugActivity.this.showDialog();
            }
        });
        PositionManager.showMarkers = true;
        final Button markersBtn = (Button) findViewById(C1170R.C1173id.btnMarkers);
        markersBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (PositionManager.showMarkers) {
                    AdvancedDebugActivity.this.mapFragment.clearCustomMarkers();
                    PositionManager.showMarkers = false;
                    markersBtn.setText("Show markers");
                    return;
                }
                PositionManager.showMarkers = true;
                markersBtn.setText("Hide markers");
            }
        });
        findViewById(C1170R.C1173id.btnSteps).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                NaviBeesManager.STEP_BY_STEP = true;
                AdvancedDebugActivity.this.mapFragment.clearCustomMarkers();
                AdvancedDebugActivity.this.calculatedLocationQueue.clear();
                NaviBeesManager.getInstance(AdvancedDebugActivity.this.getApplication()).getPositionManager().removeAdvancedLocationListeners();
                NaviBeesManager.getInstance(AdvancedDebugActivity.this.getApplication()).getPositionManager().addAdvancedLocationListener(AdvancedDebugActivity.this.stepByStepListener);
            }
        });
        findViewById(C1170R.C1173id.btnCont).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                NaviBeesManager.STEP_BY_STEP = true;
                AdvancedDebugActivity.this.mapFragment.clearCustomMarkers();
                AdvancedDebugActivity.this.calculatedLocationQueue.clear();
                NaviBeesManager.getInstance(AdvancedDebugActivity.this.getApplication()).getPositionManager().removeAdvancedLocationListeners();
                NaviBeesManager.getInstance(AdvancedDebugActivity.this.getApplication()).getPositionManager().addAdvancedLocationListener(AdvancedDebugActivity.this.continuousListener);
            }
        });
        String str = "PATH_INC_CONFIDENCE";
        PositionManager.PATH_INC_CONFIDENCE = Prefs.getInstance(getApplicationContext()).getInt(str) != -1 ? Prefs.getInstance(getApplicationContext()).getInt(str) : PositionManager.PATH_INC_CONFIDENCE;
        String str2 = "PATH_MAX_CONFIDENCE";
        PositionManager.PATH_MAX_CONFIDENCE = Prefs.getInstance(getApplicationContext()).getInt(str2) != -1 ? Prefs.getInstance(getApplicationContext()).getInt(str2) : PositionManager.PATH_MAX_CONFIDENCE;
        String str3 = "LOCATION_DIFF_RANGE";
        PositionManager.LOCATION_DIFF_RANGE = Prefs.getInstance(getApplicationContext()).getInt(str3) != -1 ? (double) Prefs.getInstance(getApplicationContext()).getInt(str3) : PositionManager.LOCATION_DIFF_RANGE;
        String str4 = "initialPathConfidence";
        PositionManager.initialPathConfidence = Prefs.getInstance(getApplicationContext()).getInt(str4) != -1 ? Prefs.getInstance(getApplicationContext()).getInt(str4) : PositionManager.initialPathConfidence;
        String str5 = "pathSwitchingDecisionPercentage";
        PositionManager.pathSwitchingDecisionPercentage = Prefs.getInstance(getApplicationContext()).getInt(str5) != -1 ? Prefs.getInstance(getApplicationContext()).getInt(str5) : PositionManager.pathSwitchingDecisionPercentage;
        String str6 = "minBeacon";
        TrillaterationPositionLocator.f1402K = Prefs.getInstance(getApplicationContext()).getInt(str6) != -1 ? Prefs.getInstance(getApplicationContext()).getInt(str6) : TrillaterationPositionLocator.f1402K;
    }

    private void setupBeaconListView() {
        this.beaconListAdapter = getBeaconListAdapter();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.recyclerView.setAdapter(this.beaconListAdapter);
    }

    private Adapter getBeaconListAdapter() {
        return new Adapter() {
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new BeaconViewHolder(LayoutInflater.from(parent.getContext()).inflate(C1170R.layout.beacon_list_item, null));
            }

            public void onBindViewHolder(ViewHolder holder, int position) {
                ((BeaconViewHolder) holder).bind((BeaconNode) AdvancedDebugActivity.this.beaconNodes.get(position));
            }

            public int getItemCount() {
                return AdvancedDebugActivity.this.beaconNodes.size();
            }
        };
    }

    private BeaconNodeListener getBeaconNodeListener() {
        BeaconNodeListener beaconNodeListener2 = this.beaconNodeListener;
        if (beaconNodeListener2 != null) {
            return beaconNodeListener2;
        }
        C120111 r0 = new BeaconNodeListener() {
            public void beaconNodeCallback(List<BeaconNode> beaconNodes) {
                if (beaconNodes.size() != 0) {
                    if (System.currentTimeMillis() > AdvancedDebugActivity.this.lastCleared + 5000) {
                        AdvancedDebugActivity.this.beaconNodeMap.clear();
                        AdvancedDebugActivity.this.lastCleared = System.currentTimeMillis();
                    }
                    for (BeaconNode beaconNode : beaconNodes) {
                        Map access$1200 = AdvancedDebugActivity.this.beaconNodeMap;
                        StringBuilder sb = new StringBuilder();
                        sb.append(beaconNode.major);
                        sb.append(":");
                        sb.append(beaconNode.minor);
                        access$1200.put(sb.toString(), beaconNode);
                    }
                    List<BeaconNode> sorted = new ArrayList<>(AdvancedDebugActivity.this.beaconNodeMap.values());
                    Collections.sort(sorted, new Comparator<BeaconNode>() {
                        public int compare(BeaconNode o1, BeaconNode o2) {
                            return Integer.valueOf((int) o2.meanRSSI()).compareTo(Integer.valueOf((int) o1.meanRSSI()));
                        }
                    });
                    AdvancedDebugActivity.this.beaconNodes = sorted;
                    AdvancedDebugActivity.this.beaconListAdapter.notifyDataSetChanged();
                }
            }
        };
        this.beaconNodeListener = r0;
        return r0;
    }

    public void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(false);
        dialog.setContentView(C1170R.layout.dialog);
        RadioGroup rgAlgo = (RadioGroup) dialog.findViewById(C1170R.C1173id.rgAlgo);
        RadioButton rbWeightedCentroid = (RadioButton) dialog.findViewById(C1170R.C1173id.rbWeightedCentroid);
        RadioButton rbTrillateration = (RadioButton) dialog.findViewById(C1170R.C1173id.rbTrillateration);
        RadioButton rbBoth = (RadioButton) dialog.findViewById(C1170R.C1173id.rbBoth);
        TextInputEditText txtMinBeacon = (TextInputEditText) dialog.findViewById(C1170R.C1173id.txtMinBeacon);
        TextInputEditText txtInitialPathConfidence = (TextInputEditText) dialog.findViewById(C1170R.C1173id.txtInitialPathConfidence);
        TextInputEditText txtMaxPathConfidence = (TextInputEditText) dialog.findViewById(C1170R.C1173id.txtMaxPathConfidence);
        TextInputEditText txtLocationDifferenceRange = (TextInputEditText) dialog.findViewById(C1170R.C1173id.txtLocationDifferenceRange);
        TextInputEditText txtConfidenceIncDecFactor = (TextInputEditText) dialog.findViewById(C1170R.C1173id.txtConfidenceIncDecFactor);
        TextInputEditText txtPathSwitchingDecisionPercentage = (TextInputEditText) dialog.findViewById(C1170R.C1173id.txtPathSwitchingDecisionPercentage);
        Button btnClose = (Button) dialog.findViewById(C1170R.C1173id.btnClose);
        Button btnClear = (Button) dialog.findViewById(C1170R.C1173id.btnClear);
        StringBuilder sb = new StringBuilder();
        String str = "";
        sb.append(str);
        Button btnClear2 = btnClear;
        sb.append(TrillaterationPositionLocator.f1402K);
        txtMinBeacon.setText(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(PositionManager.initialPathConfidence);
        txtInitialPathConfidence.setText(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append(PositionManager.PATH_MAX_CONFIDENCE);
        txtMaxPathConfidence.setText(sb3.toString());
        StringBuilder sb4 = new StringBuilder();
        sb4.append(str);
        Button btnClose2 = btnClose;
        sb4.append(PositionManager.LOCATION_DIFF_RANGE);
        txtLocationDifferenceRange.setText(sb4.toString());
        StringBuilder sb5 = new StringBuilder();
        sb5.append(str);
        sb5.append(PositionManager.PATH_INC_CONFIDENCE);
        txtConfidenceIncDecFactor.setText(sb5.toString());
        StringBuilder sb6 = new StringBuilder();
        sb6.append(str);
        sb6.append(PositionManager.pathSwitchingDecisionPercentage);
        txtPathSwitchingDecisionPercentage.setText(sb6.toString());
        rbWeightedCentroid.setChecked(this.wcAlgo);
        rbTrillateration.setChecked(this.triAlgo);
        rbBoth.setChecked(this.bothAlgo);
        Button btnClear3 = btnClear2;
        final RadioButton radioButton = rbWeightedCentroid;
        RadioButton radioButton2 = rbWeightedCentroid;
        Button btnClose3 = btnClose2;
        Button btnClose4 = btnClear3;
        C120312 r9 = r0;
        final RadioButton radioButton3 = rbTrillateration;
        TextInputEditText txtPathSwitchingDecisionPercentage2 = txtPathSwitchingDecisionPercentage;
        final RadioButton radioButton4 = rbBoth;
        TextInputEditText txtConfidenceIncDecFactor2 = txtConfidenceIncDecFactor;
        final Dialog dialog2 = dialog;
        C120312 r0 = new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                AdvancedDebugActivity.this.mapFragment.hideLocation();
                if (checkedId == C1170R.C1173id.rbWeightedCentroid) {
                    AdvancedDebugActivity.this.wcAlgo = radioButton.isChecked();
                    AdvancedDebugActivity advancedDebugActivity = AdvancedDebugActivity.this;
                    advancedDebugActivity.triAlgo = false;
                    advancedDebugActivity.bothAlgo = false;
                }
                if (checkedId == C1170R.C1173id.rbTrillateration) {
                    AdvancedDebugActivity.this.triAlgo = radioButton3.isChecked();
                    AdvancedDebugActivity advancedDebugActivity2 = AdvancedDebugActivity.this;
                    advancedDebugActivity2.wcAlgo = false;
                    advancedDebugActivity2.bothAlgo = false;
                }
                if (checkedId == C1170R.C1173id.rbBoth) {
                    AdvancedDebugActivity.this.bothAlgo = radioButton4.isChecked();
                    AdvancedDebugActivity advancedDebugActivity3 = AdvancedDebugActivity.this;
                    advancedDebugActivity3.triAlgo = false;
                    advancedDebugActivity3.wcAlgo = false;
                }
                if (AdvancedDebugActivity.this.wcAlgo) {
                    PositionManager.ALGO_TO_USE = LOCALIZER_ALGORITHM.WEIGHTED_CENTROID;
                } else if (AdvancedDebugActivity.this.triAlgo) {
                    PositionManager.ALGO_TO_USE = LOCALIZER_ALGORITHM.TRILLATERATION;
                } else if (AdvancedDebugActivity.this.bothAlgo) {
                    PositionManager.ALGO_TO_USE = LOCALIZER_ALGORITHM.BOTH;
                }
                dialog2.dismiss();
            }
        };
        rgAlgo.setOnCheckedChangeListener(r9);
        final TextInputEditText textInputEditText = txtMinBeacon;
        final TextInputEditText textInputEditText2 = txtInitialPathConfidence;
        final TextInputEditText textInputEditText3 = txtMaxPathConfidence;
        final TextInputEditText textInputEditText4 = txtLocationDifferenceRange;
        TextInputEditText txtLocationDifferenceRange2 = txtLocationDifferenceRange;
        final TextInputEditText txtLocationDifferenceRange3 = txtConfidenceIncDecFactor2;
        TextInputEditText txtMaxPathConfidence2 = txtMaxPathConfidence;
        final TextInputEditText txtMaxPathConfidence3 = txtPathSwitchingDecisionPercentage2;
        TextInputEditText txtInitialPathConfidence2 = txtInitialPathConfidence;
        final Dialog dialog3 = dialog;
        C120413 r02 = new OnClickListener() {
            public void onClick(View v) {
                if (!TextUtils.isEmpty(textInputEditText.getText().toString())) {
                    TrillaterationPositionLocator.f1402K = Integer.valueOf(textInputEditText.getText().toString()).intValue();
                }
                if (!TextUtils.isEmpty(textInputEditText2.getText().toString())) {
                    PositionManager.initialPathConfidence = Integer.valueOf(textInputEditText2.getText().toString()).intValue();
                }
                if (!TextUtils.isEmpty(textInputEditText3.getText().toString())) {
                    PositionManager.PATH_MAX_CONFIDENCE = Integer.valueOf(textInputEditText3.getText().toString()).intValue();
                }
                if (!TextUtils.isEmpty(textInputEditText4.getText().toString())) {
                    PositionManager.LOCATION_DIFF_RANGE = Double.valueOf(textInputEditText4.getText().toString()).doubleValue();
                }
                if (!TextUtils.isEmpty(txtLocationDifferenceRange3.getText().toString())) {
                    PositionManager.PATH_INC_CONFIDENCE = Integer.valueOf(txtLocationDifferenceRange3.getText().toString()).intValue();
                }
                if (!TextUtils.isEmpty(txtMaxPathConfidence3.getText().toString())) {
                    PositionManager.pathSwitchingDecisionPercentage = Integer.valueOf(txtMaxPathConfidence3.getText().toString()).intValue();
                }
                Prefs.getInstance(AdvancedDebugActivity.this.getApplicationContext()).save("minBeacon", TrillaterationPositionLocator.f1402K);
                Prefs.getInstance(AdvancedDebugActivity.this.getApplicationContext()).save("initialPathConfidence", PositionManager.initialPathConfidence);
                Prefs.getInstance(AdvancedDebugActivity.this.getApplicationContext()).save("PATH_MAX_CONFIDENCE", PositionManager.PATH_MAX_CONFIDENCE);
                Prefs.getInstance(AdvancedDebugActivity.this.getApplicationContext()).save("LOCATION_DIFF_RANGE", (int) PositionManager.LOCATION_DIFF_RANGE);
                Prefs.getInstance(AdvancedDebugActivity.this.getApplicationContext()).save("PATH_INC_CONFIDENCE", PositionManager.PATH_INC_CONFIDENCE);
                Prefs.getInstance(AdvancedDebugActivity.this.getApplicationContext()).save("pathSwitchingDecisionPercentage", PositionManager.pathSwitchingDecisionPercentage);
                dialog3.dismiss();
            }
        };
        btnClose3.setOnClickListener(r02);
        final TextInputEditText textInputEditText5 = txtInitialPathConfidence2;
        final TextInputEditText textInputEditText6 = txtMaxPathConfidence2;
        final TextInputEditText textInputEditText7 = txtLocationDifferenceRange2;
        C120514 r03 = new OnClickListener() {
            public void onClick(View v) {
                textInputEditText.getText().clear();
                textInputEditText5.getText().clear();
                textInputEditText6.getText().clear();
                textInputEditText7.getText().clear();
                txtLocationDifferenceRange3.getText().clear();
                txtMaxPathConfidence3.getText().clear();
            }
        };
        btnClose4.setOnClickListener(r03);
        dialog.show();
    }

    private void showLocation(CalculatedLocation calculatedLocation, int type, Floor floor) {
        addToQueue(calculatedLocation);
        Queue<CalculatedLocation> calculatedLocationTempQueue = new ArrayBlockingQueue<>(this.calculatedLocationQueue.size());
        calculatedLocationTempQueue.addAll(this.calculatedLocationQueue);
        for (int i = 0; i < 10; i++) {
            CalculatedLocation cl = (CalculatedLocation) this.calculatedLocationQueue.poll();
            int j = (type * 40) + i;
            if (cl != null) {
                if (i == this.calculatedLocationQueue.size()) {
                    this.mapFragment.addMarkerAtLocation(cl.indoorLocation, type, floor, (String) null);
                } else {
                    this.mapFragment.addMarkerAtLocation(cl.indoorLocation, j, floor, (String) null);
                }
                if (cl.modifiedLocation != null) {
                    this.mapFragment.addMarkerAtLocation(cl.modifiedLocation, j, floor, (String) null);
                }
            }
        }
        this.calculatedLocationQueue.addAll(calculatedLocationTempQueue);
    }

    private void addToQueue(CalculatedLocation calculatedLocation) {
        if (((ArrayBlockingQueue) this.calculatedLocationQueue).remainingCapacity() == 0) {
            this.calculatedLocationQueue.remove();
            addToQueue(calculatedLocation);
            return;
        }
        this.calculatedLocationQueue.add(calculatedLocation);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        NaviBeesManager.STEP_BY_STEP = false;
        NaviBeesManager.getInstance(getApplication()).getPositionManager().removeAdvancedLocationListeners();
    }
}
