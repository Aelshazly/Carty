package com.navibees.core.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.preference.PreferenceManager;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.activity.NBScrollView.OnScrollViewListener;
import com.navibees.core.model.metadata.MetaDataManager;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Facility;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.POICategory;
import com.navibees.core.model.metadata.json.RecentSelectedObject;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.core.p021b.C1614c;
import com.navibees.core.util.ComparatorType;
import com.navibees.sdk.C1266R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class RouteToActivity extends NaviBeesActivity {

    /* renamed from: A */
    static final String f1089A = "RouteToActivity";
    /* access modifiers changed from: private */

    /* renamed from: a */
    public ExpandableListView f1090a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C1614c f1091b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public SearchTask f1092c = null;

    /* renamed from: d */
    private ComparatorType f1093d = ComparatorType.BY_ID;

    /* renamed from: e */
    private ComparatorType f1094e;

    /* renamed from: f */
    private ComparatorType f1095f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Toolbar f1096g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public View f1097h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public View f1098i;

    /* renamed from: j */
    public EditText f1099j;

    /* renamed from: k */
    public EditText f1100k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public List<Object> f1101l;

    /* renamed from: m */
    final String f1102m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public CardView f1103n;

    /* renamed from: o */
    private ListView f1104o;

    /* renamed from: p */
    private List<POICategory> f1105p;

    /* renamed from: q */
    private List<? extends POI> f1106q;

    /* renamed from: r */
    private List<? extends Facility> f1107r;
    /* access modifiers changed from: private */

    /* renamed from: s */
    public LinearLayout f1108s;

    /* renamed from: t */
    private ImageView f1109t;
    /* access modifiers changed from: private */

    /* renamed from: u */
    public TextView f1110u;
    /* access modifiers changed from: private */

    /* renamed from: v */
    public ListView f1111v;
    /* access modifiers changed from: private */

    /* renamed from: w */
    public NBScrollView f1112w;
    /* access modifiers changed from: private */

    /* renamed from: x */
    public RelativeLayout f1113x;
    /* access modifiers changed from: private */

    /* renamed from: y */
    public CardView f1114y;

    /* renamed from: z */
    private ListView f1115z;

    class ObjectListAdapter extends BaseAdapter {

        /* renamed from: a */
        List<Object> f1131a;

        /* renamed from: b */
        boolean f1132b;

        public ObjectListAdapter(List<Object> list, boolean z) {
            this.f1131a = list;
            this.f1132b = z;
        }

        public int getCount() {
            List<Object> list = this.f1131a;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            List<Object> list = this.f1131a;
            if (list != null) {
                return list.get(i);
            }
            return null;
        }

        public long getItemId(int i) {
            if (this.f1131a != null) {
                return (long) i;
            }
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ((LayoutInflater) RouteToActivity.this.getApplicationContext().getSystemService("layout_inflater")).inflate(C1266R.layout.expanded_category_poi_list_item, null);
            }
            TextView textView = (TextView) view.findViewById(C1266R.C1269id.expanded_category_poi_or_facility_name);
            textView.setText(RouteToActivity.this.m751a(this.f1131a.get(i)));
            if (this.f1132b && RouteToActivity.this.f1091b != null) {
                RouteToActivity.this.f1091b.mo28904a(textView, RouteToActivity.this.f1099j.getText().toString());
            }
            return view;
        }
    }

    class RecentSelectedObjectListAdapter extends BaseAdapter {
        RecentSelectedObjectListAdapter() {
        }

        public int getCount() {
            if (RouteToActivity.this.f1101l != null) {
                return RouteToActivity.this.f1101l.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            if (RouteToActivity.this.f1101l != null) {
                return RouteToActivity.this.f1101l.get(i);
            }
            return null;
        }

        public long getItemId(int i) {
            if (RouteToActivity.this.f1101l != null) {
                return (long) i;
            }
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ((LayoutInflater) RouteToActivity.this.getApplicationContext().getSystemService("layout_inflater")).inflate(C1266R.layout.com_navibees_sdk_recent_search_item, null);
            }
            TextView textView = (TextView) view.findViewById(C1266R.C1269id.recent_selected_object_name);
            RouteToActivity routeToActivity = RouteToActivity.this;
            textView.setText(routeToActivity.m751a(routeToActivity.f1101l.get(i)));
            return view;
        }
    }

    private class SearchTask extends AsyncTask<String, String, List<Object>> {
        private SearchTask() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
        }

        /* access modifiers changed from: protected */
        public List<Object> doInBackground(String... strArr) {
            publishProgress(new String[]{strArr[0]});
            ArrayList arrayList = new ArrayList();
            MetaDataManager metaDataManager = NaviBeesManager.getInstance(RouteToActivity.this.getApplication()).getMetaDataManager();
            List searchInPOIs = metaDataManager.searchInPOIs(strArr[0]);
            if (searchInPOIs != null) {
                arrayList.addAll(searchInPOIs);
            }
            List searchInFacilities = metaDataManager.searchInFacilities(strArr[0]);
            if (searchInFacilities != null) {
                arrayList.addAll(searchInFacilities);
            }
            return arrayList;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(List<Object> list) {
            RouteToActivity.this.m764b(list);
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(String... strArr) {
            super.onProgressUpdate(strArr);
        }
    }

    public RouteToActivity() {
        ComparatorType comparatorType = ComparatorType.BY_NAME;
        this.f1094e = comparatorType;
        this.f1095f = comparatorType;
        this.f1102m = "recent_selected_";
    }

    public void onBackPressed() {
        String str = "";
        if (this.f1114y.getVisibility() == 0) {
            this.f1099j.setText(str);
        } else if (this.f1112w.getVisibility() == 8 && !TextUtils.isEmpty(this.f1099j.getText())) {
            this.f1099j.setText(str);
        } else if (this.f1108s.getVisibility() == 0) {
            m753a((View) this.f1108s);
            m774f();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1266R.layout.com_navibees_sdk_route_to_activity);
        if (VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setStatusBarColor(Color.parseColor("#20000000"));
        }
        this.f1113x = (RelativeLayout) findViewById(C1266R.C1269id.main_content);
        this.f1112w = (NBScrollView) findViewById(C1266R.C1269id.main_content_linear_layout);
        this.f1112w.setOnScrollViewListener(new OnScrollViewListener() {
            /* renamed from: a */
            public void mo28849a(NBScrollView nBScrollView, int i, int i2, int i3, int i4) {
                RouteToActivity.this.m772e();
            }
        });
        m781j();
        m776g();
        m780i();
        m752a(getIntent());
        if (NaviBeesManager.getInstance(getApplication()).isInitialized()) {
            Venue currentVenue = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue();
            if (currentVenue != null) {
                this.f1105p = currentVenue.getCategories(getApplication());
                this.f1106q = currentVenue.getPOIs();
                this.f1107r = currentVenue.getFacilities();
            }
            C1614c cVar = new C1614c(getApplicationContext(), this.f1105p, this.f1093d, this.f1106q, this.f1094e, this.f1107r, this.f1095f);
            this.f1091b = cVar;
            this.f1090a = (ExpandableListView) findViewById(C1266R.C1269id.pois_expandable_list);
            this.f1090a.setAdapter(this.f1091b);
            this.f1090a.setGroupIndicator(null);
            this.f1090a.setOnGroupExpandListener(new OnGroupExpandListener() {
                public void onGroupExpand(int i) {
                    POICategory group = RouteToActivity.this.f1091b.getGroup(i);
                    if (group != null) {
                        RouteToActivity.this.m772e();
                        RouteToActivity.this.f1110u.setText(group.name.getText());
                        RouteToActivity.this.f1090a.collapseGroup(i);
                        RouteToActivity.this.f1108s.setVisibility(0);
                        RouteToActivity routeToActivity = RouteToActivity.this;
                        routeToActivity.m760b((View) routeToActivity.f1108s);
                        final List a = RouteToActivity.this.f1091b.mo28902a(group);
                        RouteToActivity.this.f1111v.setAdapter(new ObjectListAdapter(a, false));
                        RouteToActivity.this.f1111v.setOnItemClickListener(new OnItemClickListener() {
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                                RouteToActivity.this.m763b(a.get(i));
                                RouteToActivity.this.m768c(a.get(i));
                            }
                        });
                    }
                }
            });
            m778h();
            List<Object> list = this.f1101l;
            if (list != null && list.size() > 0) {
                this.f1103n.setVisibility(0);
                this.f1104o.setAdapter(new RecentSelectedObjectListAdapter());
                this.f1104o.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        if (RouteToActivity.this.f1101l != null) {
                            RouteToActivity routeToActivity = RouteToActivity.this;
                            routeToActivity.m763b(routeToActivity.f1101l.get(i));
                            RouteToActivity routeToActivity2 = RouteToActivity.this;
                            routeToActivity2.m768c(routeToActivity2.f1101l.get(i));
                        }
                    }
                });
            }
            if (!TextUtils.isEmpty(this.f1099j.getText().toString())) {
                EditText editText = this.f1099j;
                editText.setText(editText.getText().toString());
                EditText editText2 = this.f1099j;
                editText2.setSelection(editText2.getText().length());
                return;
            }
            return;
        }
        Toast.makeText(getApplicationContext(), "NaviBees was not initialized", 0).show();
    }

    /* renamed from: d */
    private List<RecentSelectedObject> m770d() {
        String str;
        try {
            Building currentBuilding = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentBuilding();
            if (currentBuilding != null) {
                str = currentBuilding.f1329id;
            } else {
                str = "";
            }
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            StringBuilder sb = new StringBuilder();
            sb.append("recent_selected_");
            sb.append(str);
            JSONArray jSONArray = new JSONArray(defaultSharedPreferences.getString(sb.toString(), null));
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(new RecentSelectedObject(jSONArray.getJSONObject(i)));
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m772e() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m774f() {
        this.f1096g.setVisibility(0);
        this.f1112w.setVisibility(0);
        this.f1113x.setBackgroundColor(getResources().getColor(C1266R.C1267color.route_to_background_color_normal));
        this.f1091b.mo28906b();
        m759b();
        m784k();
    }

    /* renamed from: g */
    private void m776g() {
        this.f1108s = (LinearLayout) findViewById(C1266R.C1269id.category_expand_linear_layout);
        this.f1109t = (ImageView) this.f1108s.findViewById(C1266R.C1269id.collapse_arrow);
        this.f1110u = (TextView) this.f1108s.findViewById(C1266R.C1269id.expanded_category_name);
        this.f1111v = (ListView) this.f1108s.findViewById(C1266R.C1269id.expanded_category_poi_list);
        this.f1109t.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RouteToActivity routeToActivity = RouteToActivity.this;
                routeToActivity.m753a((View) routeToActivity.f1108s);
                RouteToActivity.this.m774f();
            }
        });
    }

    /* renamed from: h */
    private void m778h() {
        this.f1103n = (CardView) findViewById(C1266R.C1269id.recent_poi_selected_cardview);
        this.f1104o = (ListView) findViewById(C1266R.C1269id.recent_selected_poi_list);
        this.f1101l = new ArrayList();
        m784k();
    }

    /* renamed from: i */
    private void m780i() {
        this.f1114y = (CardView) findViewById(C1266R.C1269id.search_result_cardview);
        this.f1115z = (ListView) findViewById(C1266R.C1269id.search_result_list);
    }

    /* renamed from: j */
    private void m781j() {
        this.f1096g = (Toolbar) findViewById(C1266R.C1269id.toolbar);
        setSupportActionBar(this.f1096g);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
        this.f1096g.setBackgroundColor(-1);
        this.f1096g.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RouteToActivity.this.m772e();
                RouteToActivity.this.onBackPressed();
            }
        });
        this.f1099j = (EditText) this.f1096g.findViewById(C1266R.C1269id.search_edit_text);
        EditText editText = this.f1099j;
        String str = NaviBeesConstants.ROUTE_TO_INITIAL_SEARCH_TERM_KEY;
        if (editText != null) {
            String string = getResources().getString(C1266R.string.search_query_hint);
            String text = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue().name.getText();
            if (text != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(string);
                sb.append(" ");
                sb.append(text);
                string = sb.toString();
            }
            this.f1099j.setHint(string);
            if (MapHoverActivity.isClickedOnSearchStartPoint) {
                this.f1099j.setText(getIntent().getStringExtra(NaviBeesConstants.ROUTE_TO_DESTINATION_SEARCH_TERM_KEY));
                findViewById(C1266R.C1269id.search_start_container).setVisibility(0);
                findViewById(C1266R.C1269id.start_point_bottom_line).setVisibility(0);
                this.f1099j.setFocusable(false);
                this.f1099j.setCursorVisible(false);
            } else {
                this.f1099j.setText(getIntent().getStringExtra(str));
                findViewById(C1266R.C1269id.search_start_container).setVisibility(8);
                findViewById(C1266R.C1269id.start_point_bottom_line).setVisibility(8);
                EditText editText2 = this.f1099j;
                editText2.setSelection(editText2.getText().length());
                this.f1099j.setFocusable(true);
                this.f1099j.setCursorVisible(true);
            }
            this.f1099j.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                    if (editable.length() > 0 && RouteToActivity.this.f1097h != null) {
                        RouteToActivity.this.f1097h.setVisibility(0);
                    }
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (RouteToActivity.this.f1091b != null) {
                        if (RouteToActivity.this.f1092c != null) {
                            RouteToActivity.this.f1092c.cancel(true);
                        }
                        if (TextUtils.isEmpty(charSequence.toString())) {
                            RouteToActivity.this.f1114y.setVisibility(8);
                            RouteToActivity.this.m774f();
                        } else if (!MapHoverActivity.isClickedOnSearchStartPoint) {
                            if (RouteToActivity.this.f1103n != null) {
                                RouteToActivity.this.f1103n.setVisibility(8);
                            }
                            RouteToActivity.this.f1112w.setVisibility(8);
                            RouteToActivity.this.f1091b.mo28903a();
                            RouteToActivity routeToActivity = RouteToActivity.this;
                            routeToActivity.f1092c = new SearchTask();
                            RouteToActivity.this.f1092c.execute(new String[]{charSequence.toString()});
                        }
                    }
                }
            });
        }
        this.f1100k = (EditText) this.f1096g.findViewById(C1266R.C1269id.search_start_edit_text);
        if (this.f1100k != null) {
            this.f1100k.setHint(getResources().getString(C1266R.string.search_start_hint));
            this.f1100k.setCursorVisible(true);
            this.f1100k.setText(getIntent().getStringExtra(str));
            EditText editText3 = this.f1100k;
            editText3.setSelection(editText3.getText().length());
            this.f1100k.setFocusable(true);
            this.f1100k.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                    if (editable.length() > 0 && RouteToActivity.this.f1097h != null) {
                        RouteToActivity.this.f1098i.setVisibility(0);
                    }
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (RouteToActivity.this.f1091b != null) {
                        if (RouteToActivity.this.f1092c != null) {
                            RouteToActivity.this.f1092c.cancel(true);
                        }
                        if (TextUtils.isEmpty(charSequence.toString())) {
                            RouteToActivity.this.f1114y.setVisibility(8);
                            RouteToActivity.this.m774f();
                        } else {
                            if (RouteToActivity.this.f1103n != null) {
                                RouteToActivity.this.f1103n.setVisibility(8);
                            }
                            RouteToActivity.this.f1112w.setVisibility(8);
                            RouteToActivity.this.f1091b.mo28903a();
                            RouteToActivity routeToActivity = RouteToActivity.this;
                            routeToActivity.f1092c = new SearchTask();
                            RouteToActivity.this.f1092c.execute(new String[]{charSequence.toString()});
                        }
                    }
                }
            });
        }
        this.f1097h = this.f1096g.findViewById(C1266R.C1269id.search_clear);
        if (this.f1097h != null) {
            if (TextUtils.isEmpty(this.f1099j.getText().toString())) {
                this.f1097h.setVisibility(8);
            } else {
                this.f1097h.setVisibility(0);
            }
            this.f1097h.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MapHoverActivity.isClickedOnSearchStartPoint = false;
                    RouteToActivity.this.f1099j.setFocusable(true);
                    RouteToActivity.this.f1099j.setCursorVisible(true);
                    RouteToActivity.this.f1097h.setVisibility(8);
                    RouteToActivity.this.f1099j.setText("");
                    if (RouteToActivity.this.f1091b != null) {
                        RouteToActivity.this.f1091b.mo28906b();
                        RouteToActivity.this.m759b();
                    }
                }
            });
        }
        this.f1098i = this.f1096g.findViewById(C1266R.C1269id.search_start_clear);
        if (this.f1098i != null) {
            if (TextUtils.isEmpty(this.f1100k.getText().toString())) {
                this.f1098i.setVisibility(8);
            } else {
                this.f1098i.setVisibility(0);
            }
            this.f1098i.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    RouteToActivity.this.f1098i.setVisibility(8);
                    RouteToActivity.this.f1100k.setText("");
                    if (RouteToActivity.this.f1091b != null) {
                        RouteToActivity.this.f1091b.mo28906b();
                        RouteToActivity.this.m759b();
                    }
                }
            });
        }
    }

    /* renamed from: k */
    private void m784k() {
        this.f1101l.clear();
        try {
            Building currentBuilding = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentBuilding();
            List<RecentSelectedObject> d = m770d();
            if (d != null) {
                if (currentBuilding != null) {
                    for (RecentSelectedObject recentSelectedObject : d) {
                        if (recentSelectedObject.getType() == 1) {
                            POI poi = (POI) currentBuilding.poisDictionary.get(recentSelectedObject.getId());
                            if (poi != null) {
                                this.f1101l.add(poi);
                            }
                        } else if (recentSelectedObject.getType() == 2) {
                            Facility facility = (Facility) currentBuilding.facilitiesDictionary.get(recentSelectedObject.getId());
                            if (facility != null) {
                                this.f1101l.add(facility);
                            }
                        }
                    }
                    if (this.f1104o == null || this.f1103n == null || this.f1101l.size() <= 0) {
                        this.f1103n.setVisibility(8);
                    } else {
                        ((BaseAdapter) this.f1104o.getAdapter()).notifyDataSetChanged();
                        this.f1103n.setVisibility(0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m768c(Object obj) {
        Intent intent = new Intent();
        boolean z = obj instanceof POI;
        String str = NaviBeesConstants.SELECTED_PLACE_BUILDING_KEY;
        String str2 = NaviBeesConstants.SELECTED_PLACE_TYPE_KEY;
        String str3 = NaviBeesConstants.SELECTED_PLACE_ID_KEY;
        if (z) {
            POI poi = (POI) obj;
            intent.putExtra(str3, poi.f1339id);
            intent.putExtra(str2, 1);
            intent.putExtra(str, poi.buildingId);
        } else if (obj instanceof Facility) {
            Facility facility = (Facility) obj;
            intent.putExtra(str3, facility.f1330id);
            intent.putExtra(str2, 2);
            intent.putExtra(str, facility.buildingId);
        }
        setResult(-1, intent);
        m772e();
        finish();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m763b(Object obj) {
        RecentSelectedObject recentSelectedObject = new RecentSelectedObject();
        if (obj instanceof POI) {
            recentSelectedObject.setId(((POI) obj).f1339id);
            recentSelectedObject.setType(1);
        } else if (obj instanceof Facility) {
            recentSelectedObject.setId(((Facility) obj).f1330id);
            recentSelectedObject.setType(2);
        }
        List d = m770d();
        ArrayList arrayList = new ArrayList();
        if (d != null) {
            arrayList.addAll(d);
        }
        if (arrayList.isEmpty()) {
            arrayList.add(recentSelectedObject);
        } else {
            RecentSelectedObject recentSelectedObject2 = (RecentSelectedObject) arrayList.get(0);
            if (recentSelectedObject2.getType() != recentSelectedObject.getType() || !recentSelectedObject2.getId().equals(recentSelectedObject.getId())) {
                arrayList.add(0, recentSelectedObject);
                if (arrayList.size() > 2) {
                    arrayList.remove(arrayList.size() - 1);
                }
            }
        }
        m757a((List<RecentSelectedObject>) arrayList);
    }

    /* renamed from: a */
    private void m752a(Intent intent) {
        if (intent != null) {
            String str = NaviBeesConstants.ROUTE_TO_CATEGORY_SORT_TYPE_KEY;
            if (intent.getSerializableExtra(str) != null) {
                this.f1093d = (ComparatorType) intent.getSerializableExtra(str);
            }
            String str2 = NaviBeesConstants.ROUTE_TO_POI_SORT_TYPE_KEY;
            if (intent.getSerializableExtra(str2) != null) {
                this.f1094e = (ComparatorType) intent.getSerializableExtra(str2);
            }
            String str3 = NaviBeesConstants.ROUTE_TO_FACILITY_SORT_TYPE_KEY;
            if (intent.getSerializableExtra(str3) != null) {
                this.f1095f = (ComparatorType) intent.getSerializableExtra(str3);
            }
        }
    }

    /* renamed from: a */
    private void m757a(List<RecentSelectedObject> list) {
        String str;
        Building currentBuilding = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentBuilding();
        if (currentBuilding != null) {
            str = currentBuilding.f1329id;
        } else {
            str = "";
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (list != null) {
            JSONArray jSONArray = new JSONArray();
            for (RecentSelectedObject json : list) {
                jSONArray.put(json.toJson());
            }
            Editor edit = defaultSharedPreferences.edit();
            StringBuilder sb = new StringBuilder();
            sb.append("recent_selected_");
            sb.append(str);
            edit.putString(sb.toString(), jSONArray.toString());
            edit.apply();
        }
    }

    /* renamed from: c */
    private void m765c() {
        for (int groupCount = this.f1091b.getGroupCount() - 1; groupCount >= 0; groupCount--) {
            this.f1090a.expandGroup(groupCount, true);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public String m751a(Object obj) {
        if (obj instanceof POI) {
            return ((POI) obj).getDescProperty();
        }
        if (obj instanceof Facility) {
            return ((Facility) obj).name.getText();
        }
        return "";
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m753a(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, C1266R.anim.slide_down);
        view.clearAnimation();
        view.startAnimation(loadAnimation);
        view.postOnAnimation(new Runnable() {
            public void run() {
                RouteToActivity.this.f1108s.setVisibility(8);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m759b() {
        int groupCount = this.f1091b.getGroupCount();
        for (int i = 0; i < groupCount; i++) {
            this.f1090a.collapseGroup(i);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m764b(final List<Object> list) {
        if (list == null || list.size() <= 0) {
            this.f1114y.setVisibility(8);
            return;
        }
        this.f1114y.setVisibility(0);
        this.f1115z.setAdapter(new ObjectListAdapter(list, true));
        this.f1115z.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                RouteToActivity.this.m763b(list.get(i));
                RouteToActivity.this.m768c(list.get(i));
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m760b(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, C1266R.anim.slide_up);
        view.clearAnimation();
        view.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                RouteToActivity.this.f1113x.setBackgroundColor(RouteToActivity.this.getResources().getColor(C1266R.C1267color.route_to_background_color_category_expanded));
                RouteToActivity.this.f1096g.setVisibility(8);
                RouteToActivity.this.f1112w.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
    }
}
