package com.navibees.navigatorapp.p010ui.activities;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.Facility;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.POICategory;
import com.navibees.maps.MapActivity;
import com.navibees.navigatorapp.C1170R;
import com.navibees.navigatorapp.utils.LocaleUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.navibees.navigatorapp.ui.activities.POIActivity */
public class POIActivity extends AppCompatActivity {
    private BaseAdapter POIAdapter = new BaseAdapter() {
        public int getCount() {
            return POIActivity.this.poiList.size();
        }

        public Object getItem(int position) {
            return POIActivity.this.poiList.get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(POIActivity.this).inflate(17367043, parent, false);
                holder = new Holder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.textView.setText(((POI) POIActivity.this.poiList.get(position)).name.getText());
            return convertView;
        }
    };
    private BaseAdapter POICategoriesAdapter = new BaseAdapter() {
        public int getCount() {
            return POIActivity.this.poiCategories.size();
        }

        public Object getItem(int position) {
            return POIActivity.this.poiCategories.get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(POIActivity.this).inflate(17367043, parent, false);
                holder = new Holder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.textView.setText(((POICategory) POIActivity.this.poiCategories.get(position)).name.getText());
            holder.textView.setTypeface(null, 1);
            return convertView;
        }
    };
    /* access modifiers changed from: private */
    public List<Facility> facilities;
    private BaseAdapter facilitiesAdapter = new BaseAdapter() {
        public int getCount() {
            return POIActivity.this.facilities.size();
        }

        public Object getItem(int position) {
            return POIActivity.this.facilities.get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(POIActivity.this).inflate(17367043, parent, false);
                holder = new Holder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.textView.setText(((Facility) POIActivity.this.facilities.get(position)).name.getText());
            holder.textView.setTypeface(null, 1);
            return convertView;
        }
    };
    private boolean isFacilities;
    private boolean isPOI;
    /* access modifiers changed from: private */
    public boolean isStageTwo;
    private ListView lsPOI;
    /* access modifiers changed from: private */
    public List<POICategory> poiCategories;
    /* access modifiers changed from: private */
    public List<POI> poiList;
    private Toolbar toolbar;

    /* renamed from: com.navibees.navigatorapp.ui.activities.POIActivity$Holder */
    class Holder {
        TextView textView;

        Holder(View v) {
            this.textView = (TextView) v.findViewById(16908308);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r6) {
        /*
            r5 = this;
            super.onCreate(r6)
            android.content.Context r0 = r5.getApplicationContext()
            com.navibees.navigatorapp.data.Prefs r0 = com.navibees.navigatorapp.data.Prefs.getInstance(r0)
            java.lang.String r0 = r0.getOperationVenueName()
            int r1 = r0.hashCode()
            r2 = -442449034(0xffffffffe5a0c376, float:-9.489803E22)
            r3 = 1
            r4 = 0
            if (r1 == r2) goto L_0x002a
            r2 = 2304049(0x232831, float:3.22866E-39)
            if (r1 == r2) goto L_0x0020
        L_0x001f:
            goto L_0x0034
        L_0x0020:
            java.lang.String r1 = "KFMC"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x001f
            r0 = 1
            goto L_0x0035
        L_0x002a:
            java.lang.String r1 = "Masjid_ul_Haram"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x001f
            r0 = 0
            goto L_0x0035
        L_0x0034:
            r0 = -1
        L_0x0035:
            if (r0 == 0) goto L_0x0047
            if (r0 == r3) goto L_0x0040
            r0 = 2131951625(0x7f130009, float:1.953967E38)
            r5.setTheme(r0)
            goto L_0x004e
        L_0x0040:
            r0 = 2131951626(0x7f13000a, float:1.9539672E38)
            r5.setTheme(r0)
            goto L_0x004e
        L_0x0047:
            r0 = 2131951627(0x7f13000b, float:1.9539674E38)
            r5.setTheme(r0)
        L_0x004e:
            r0 = 2131558437(0x7f0d0025, float:1.874219E38)
            r5.setContentView(r0)
            r5.setupToolBar()
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r1 = "isPOI"
            boolean r0 = r0.getBooleanExtra(r1, r4)
            r5.isPOI = r0
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r1 = "isFacilities"
            boolean r0 = r0.getBooleanExtra(r1, r4)
            r5.isFacilities = r0
            boolean r0 = r5.isPOI
            if (r0 == 0) goto L_0x0077
            r5.setupPOICategoriesList()
            goto L_0x0082
        L_0x0077:
            boolean r0 = r5.isFacilities
            if (r0 == 0) goto L_0x007f
            r5.setupFacilitiesList()
            goto L_0x0082
        L_0x007f:
            r5.finish()
        L_0x0082:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.navigatorapp.p010ui.activities.POIActivity.onCreate(android.os.Bundle):void");
    }

    private void setupToolBar() {
        this.toolbar = (Toolbar) findViewById(C1170R.C1173id.toolbar);
        setSupportActionBar(this.toolbar);
        setTitle(LocaleUtils.getLocalizedResources(this).getStringArray(C1170R.array.items)[getIntent().getIntExtra(Param.INDEX, 0)]);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            this.toolbar.setNavigationOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    POIActivity.this.onBackPressed();
                }
            });
        }
    }

    private void setupFacilitiesList() {
        this.facilities = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue().getFacilities();
        this.lsPOI = (ListView) findViewById(C1170R.C1173id.lsPOI);
        this.lsPOI.setAdapter(this.facilitiesAdapter);
        this.lsPOI.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                List<? extends POI> pois = ((Facility) POIActivity.this.facilities.get(position)).getPois();
                POIActivity.this.poiList = new ArrayList();
                for (POI poi : pois) {
                    POIActivity.this.poiList.add(poi);
                }
                POIActivity.this.isStageTwo = true;
                POIActivity.this.setupListViewForPOIList();
            }
        });
    }

    private void setupPOICategoriesList() {
        this.poiList = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue().getPOIs();
        setupListViewForPOIList();
    }

    /* access modifiers changed from: private */
    public void setupListViewForPOIList() {
        this.lsPOI = (ListView) findViewById(C1170R.C1173id.lsPOI);
        this.lsPOI.setAdapter(this.POIAdapter);
        this.lsPOI.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(POIActivity.this, MapActivity.class);
                intent.putExtra(NaviBeesConstants.ENABLE_ROUTING_WHEN_OUT_OF_VENUE, true);
                intent.putExtra(NaviBeesConstants.ROUTE_TO_POI_KEY, (Parcelable) POIActivity.this.poiList.get(position));
                POIActivity.this.startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        if (this.isStageTwo) {
            setupFacilitiesList();
            this.isStageTwo = false;
            return;
        }
        super.onBackPressed();
    }
}
