package com.navibees.core.activity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBar.LayoutParams;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.POICategory;
import com.navibees.sdk.C1266R;
import java.util.List;

public class POIsCategoriesFilterActivity extends NaviBeesActivity {

    /* renamed from: a */
    private ListView f1078a;

    /* renamed from: b */
    private POICategoryFilterListAdapter f1079b;

    /* renamed from: c */
    private SharedPreferences f1080c;

    /* renamed from: d */
    private Editor f1081d;

    /* renamed from: e */
    private int f1082e = C1266R.layout.com_navibees_sdk_pois_category_filter_activity;

    private class POICategoryFilterListAdapter extends BaseAdapter implements OnClickListener {

        /* renamed from: a */
        private List<POICategory> f1084a;

        public POICategoryFilterListAdapter(List<POICategory> list) {
            this.f1084a = list;
        }

        public int getCount() {
            List<POICategory> list = this.f1084a;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            return this.f1084a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ((LayoutInflater) POIsCategoriesFilterActivity.this.getApplicationContext().getSystemService("layout_inflater")).inflate(C1266R.layout.com_navibees_sdk_pois_category_filter_activity_list_item, null);
                POIsCategoriesFilterActivity.this.customizeView(view);
            }
            ((TextView) view.findViewById(C1266R.C1269id.poi_category_name)).setText(((POICategory) this.f1084a.get(i)).name.getText());
            Switch switchR = (Switch) view.findViewById(C1266R.C1269id.toggleButton);
            switchR.setTag(Integer.valueOf(i));
            switchR.setOnClickListener(this);
            return view;
        }

        public void onClick(View view) {
            if (view.getId() == C1266R.C1269id.toggleButton) {
                POICategory pOICategory = (POICategory) this.f1084a.get(Integer.parseInt(view.getTag().toString()));
                ((Switch) view).isChecked();
                notifyDataSetChanged();
                POIsCategoriesFilterActivity.this.m746a(pOICategory);
            }
        }
    }

    /* renamed from: b */
    private void m747b() {
        try {
            if (getActionBarLayoutRes() != -1) {
                View inflate = LayoutInflater.from(this).inflate(getActionBarLayoutRes(), null);
                ActionBar supportActionBar = getSupportActionBar();
                supportActionBar.setDisplayOptions(16);
                supportActionBar.setDisplayHomeAsUpEnabled(false);
                supportActionBar.setDisplayShowCustomEnabled(true);
                supportActionBar.setDisplayShowTitleEnabled(false);
                supportActionBar.setDisplayUseLogoEnabled(false);
                supportActionBar.setDisplayShowHomeEnabled(false);
                supportActionBar.setCustomView(inflate, new LayoutParams(-1, -1, 17));
                ((Toolbar) inflate.getParent()).setContentInsetsAbsolute(0, 0);
                inflate.setBackgroundResource(getActionBarLayoutBackgroundRes());
                TextView textView = (TextView) inflate.findViewById(C1266R.C1269id.action_bar_title);
                if (textView != null) {
                    textView.setText(getTitle());
                    customiseActionBarTitle(textView);
                }
                inflate.findViewById(C1266R.C1269id.up_navigation).setBackgroundResource(getActionBarLayoutBackgroundRes());
                inflate.findViewById(C1266R.C1269id.up_navigation).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        POIsCategoriesFilterActivity.this.onBackPressed();
                    }
                });
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    private int getContentViewRes() {
        return this.f1082e;
    }

    /* access modifiers changed from: protected */
    public void customiseActionBarTitle(TextView textView) {
    }

    /* access modifiers changed from: protected */
    public void customizeView(View view) {
    }

    /* access modifiers changed from: protected */
    public int getActionBarLayoutBackgroundRes() {
        return C1266R.C1268drawable.f213x5d4690c8;
    }

    /* access modifiers changed from: protected */
    public int getActionBarLayoutRes() {
        return C1266R.layout.com_navibees_sdk_pois_category_filter_activity_action_bar;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getContentViewRes());
        if (NaviBeesManager.getInstance(getApplication()).isInitialized()) {
            Building currentBuilding = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentBuilding();
            if (currentBuilding != null) {
                this.f1079b = new POICategoryFilterListAdapter(currentBuilding.categories);
                this.f1078a = (ListView) findViewById(C1266R.C1269id.list);
                this.f1078a.setAdapter(this.f1079b);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Can't Load MetaData Files", 0).show();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m746a(POICategory pOICategory) {
        this.f1080c = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f1081d = this.f1080c.edit();
        this.f1081d.apply();
    }
}
