package com.navibees.navigatorapp.p010ui.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.navibees.core.NaviBeesManager;
import com.navibees.core.activity.NaviBeesActivity;
import com.navibees.core.model.metadata.BeaconNode;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.postioning.BeaconNodeListener;
import com.navibees.core.model.postioning.NBLocationListener;
import com.navibees.core.model.postioning.PositionManager;
import com.navibees.core.model.postioning.RSSIMAP;
import com.navibees.maps.MapFragment;
import com.navibees.navigatorapp.C1170R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.navibees.navigatorapp.ui.activities.DebugIndoorLocationActivity */
public class DebugIndoorLocationActivity extends NaviBeesActivity {
    /* access modifiers changed from: private */
    public Adapter beaconListAdapter;
    private BeaconNodeListener beaconNodeListener;
    /* access modifiers changed from: private */
    public Map<String, BeaconNode> beaconNodeMap = new HashMap();
    /* access modifiers changed from: private */
    public List<BeaconNode> beaconNodes = new ArrayList();
    private final long clearListAfter = 5000;
    BroadcastReceiver dbgRssiReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String val = intent.getStringExtra("value");
            RSSIMAP obj = (RSSIMAP) intent.getSerializableExtra("map");
            if (DebugIndoorLocationActivity.this.listening) {
                DebugIndoorLocationActivity.this.floorRssiTextView.setText(val);
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
                DebugIndoorLocationActivity.this.mr_nr.setText(text);
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
    private MapFragment mapFragment;
    /* access modifiers changed from: private */
    public TextView mr_nr;
    /* access modifiers changed from: private */
    public Button pathConfidence;
    /* access modifiers changed from: private */
    public boolean pathConfidenceEnabled = false;
    private RecyclerView recyclerView;
    private Button startStop;
    private Toolbar toolbar;

    /* renamed from: com.navibees.navigatorapp.ui.activities.DebugIndoorLocationActivity$BeaconViewHolder */
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
        activity.startActivity(new Intent(activity, DebugIndoorLocationActivity.class));
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
                if (currentLocationAfterSmoothing != null && DebugIndoorLocationActivity.this.listening) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Confidence: ");
                    sb.append(currentLocationAfterSmoothing.confidence.name());
                    sb.append(" | FC: ");
                    sb.append(PositionManager.changeFloorCounter);
                    DebugIndoorLocationActivity.this.locationTextView.setText(sb.toString());
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
            r0 = 2131558431(0x7f0d001f, float:1.8742178E38)
            r4.setContentView(r0)
            r4.initView()
            r4.addMapFragment()
            r4.startListeningBeacons()
            r4.listenForCurrentLocation()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.navigatorapp.p010ui.activities.DebugIndoorLocationActivity.onCreate(android.os.Bundle):void");
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
                DebugIndoorLocationActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        DebugIndoorLocationActivity.this.setupMap();
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
        this.toolbar = (Toolbar) findViewById(C1170R.C1173id.toolbar);
        setSupportActionBar(this.toolbar);
        setTitle("Debug");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.recyclerView = (RecyclerView) findViewById(C1170R.C1173id.beacon_list);
        this.floorRssiTextView = (TextView) findViewById(C1170R.C1173id.dbg_rssi);
        setupBeaconListView();
        this.startStop = (Button) findViewById(C1170R.C1173id.start_stop);
        this.pathConfidence = (Button) findViewById(C1170R.C1173id.path_confidence);
        this.locationTextView = (TextView) findViewById(C1170R.C1173id.location_tv);
        this.mr_nr = (TextView) findViewById(C1170R.C1173id.mr_nr);
        this.startStop.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (DebugIndoorLocationActivity.this.listening) {
                    DebugIndoorLocationActivity.this.stopListening();
                } else {
                    DebugIndoorLocationActivity.this.startListeningBeacons();
                }
            }
        });
        this.pathConfidence.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (DebugIndoorLocationActivity.this.pathConfidenceEnabled) {
                    DebugIndoorLocationActivity.this.pathConfidenceEnabled = false;
                    DebugIndoorLocationActivity.this.pathConfidence.setText("Enable PC");
                    PositionManager.PATH_CONFIDENCE_ON = false;
                    DebugIndoorLocationActivity.this.pathConfidence.setTextColor(SupportMenu.CATEGORY_MASK);
                    return;
                }
                DebugIndoorLocationActivity.this.pathConfidenceEnabled = true;
                DebugIndoorLocationActivity.this.pathConfidence.setText("Disable PC");
                PositionManager.PATH_CONFIDENCE_ON = true;
                DebugIndoorLocationActivity.this.pathConfidence.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            }
        });
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
                ((BeaconViewHolder) holder).bind((BeaconNode) DebugIndoorLocationActivity.this.beaconNodes.get(position));
            }

            public int getItemCount() {
                return DebugIndoorLocationActivity.this.beaconNodes.size();
            }
        };
    }

    private BeaconNodeListener getBeaconNodeListener() {
        BeaconNodeListener beaconNodeListener2 = this.beaconNodeListener;
        if (beaconNodeListener2 != null) {
            return beaconNodeListener2;
        }
        C12247 r0 = new BeaconNodeListener() {
            public void beaconNodeCallback(List<BeaconNode> beaconNodes) {
                if (beaconNodes.size() != 0) {
                    if (System.currentTimeMillis() > DebugIndoorLocationActivity.this.lastCleared + 5000) {
                        DebugIndoorLocationActivity.this.lastCleared = System.currentTimeMillis();
                    }
                    DebugIndoorLocationActivity.this.beaconNodeMap.clear();
                    for (BeaconNode beaconNode : beaconNodes) {
                        Map access$1000 = DebugIndoorLocationActivity.this.beaconNodeMap;
                        StringBuilder sb = new StringBuilder();
                        sb.append(beaconNode.major);
                        sb.append(":");
                        sb.append(beaconNode.minor);
                        access$1000.put(sb.toString(), beaconNode);
                    }
                    List<BeaconNode> sorted = new ArrayList<>(DebugIndoorLocationActivity.this.beaconNodeMap.values());
                    Collections.sort(sorted, new Comparator<BeaconNode>() {
                        public int compare(BeaconNode o1, BeaconNode o2) {
                            return Integer.valueOf((int) o2.meanRSSI()).compareTo(Integer.valueOf((int) o1.meanRSSI()));
                        }
                    });
                    DebugIndoorLocationActivity.this.beaconNodes = sorted;
                    DebugIndoorLocationActivity.this.beaconListAdapter.notifyDataSetChanged();
                }
            }
        };
        this.beaconNodeListener = r0;
        return r0;
    }
}
