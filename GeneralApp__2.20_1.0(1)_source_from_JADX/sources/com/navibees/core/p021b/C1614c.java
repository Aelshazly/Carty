package com.navibees.core.p021b;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.navibees.core.model.metadata.json.Facility;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.POICategory;
import com.navibees.core.util.ComparatorType;
import com.navibees.core.util.Log;
import com.navibees.core.util.NaviBeesAlphanumComparator;
import com.navibees.sdk.C1266R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.navibees.core.b.c */
/* compiled from: POIExpandableListAdapter */
public class C1614c extends BaseExpandableListAdapter {

    /* renamed from: f */
    static final String f1153f = "POIExpandableListAdapter";

    /* renamed from: a */
    private List<POICategory> f1154a;

    /* renamed from: b */
    private Map<String, List<Object>> f1155b;

    /* renamed from: c */
    private Map<String, List<Object>> f1156c;

    /* renamed from: d */
    private Context f1157d;

    /* renamed from: e */
    private String f1158e = "";

    public C1614c(Context context, List<POICategory> list, ComparatorType comparatorType, List<? extends POI> list2, ComparatorType comparatorType2, List<? extends Facility> list3, ComparatorType comparatorType3) {
        this.f1157d = context;
        this.f1154a = list;
        this.f1155b = new LinkedHashMap();
        this.f1156c = new LinkedHashMap();
        if (list != null) {
            Collections.sort(list, new NaviBeesAlphanumComparator(comparatorType));
            for (int i = 0; i < list.size(); i++) {
                this.f1155b.put(((POICategory) list.get(i)).f1340id, new ArrayList());
            }
        }
        String str = f1153f;
        String str2 = " , is Not EXIST in Categories";
        if (list3 != null) {
            Collections.sort(list3, new NaviBeesAlphanumComparator(comparatorType3));
            for (Facility facility : list3) {
                List list4 = (List) this.f1155b.get(facility.categoryId);
                if (list4 != null) {
                    list4.add(facility);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("facility.getCategoryId():");
                    sb.append(facility.categoryId);
                    sb.append(str2);
                    Log.m1172e(str, sb.toString());
                }
            }
        }
        if (list2 != null) {
            Collections.sort(list2, new NaviBeesAlphanumComparator(comparatorType2));
            for (POI poi : list2) {
                List list5 = (List) this.f1155b.get(poi.categoryId);
                if (list5 != null) {
                    list5.add(poi);
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("poi.getCategoryId():");
                    sb2.append(poi.categoryId);
                    sb2.append(str2);
                    Log.m1172e(str, sb2.toString());
                }
            }
        }
        this.f1156c.putAll(this.f1155b);
    }

    /* renamed from: a */
    private POICategory m800a(String str) {
        for (POICategory pOICategory : this.f1154a) {
            if (str.equals(pOICategory.f1340id)) {
                return pOICategory;
            }
        }
        return null;
    }

    /* renamed from: b */
    public void mo28906b() {
        this.f1158e = "";
        this.f1156c.clear();
        this.f1156c.putAll(this.f1155b);
        notifyDataSetChanged();
    }

    public Object getChild(int i, int i2) {
        return ((List) this.f1156c.get((String) this.f1156c.keySet().toArray()[i])).get(i2);
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.f1157d.getSystemService("layout_inflater")).inflate(C1266R.layout.com_navibees_sdk_route_to_activity_poi_list_item, null);
        }
        TextView textView = (TextView) view.findViewById(C1266R.C1269id.poi_name);
        if (getChild(i, i2) instanceof POI) {
            textView.setText(((POI) getChild(i, i2)).name.getText());
        } else if (getChild(i, i2) instanceof Facility) {
            textView.setText(((Facility) getChild(i, i2)).name.getText());
        }
        if (!this.f1158e.isEmpty()) {
            mo28904a(textView, this.f1158e);
        }
        return view;
    }

    public int getChildrenCount(int i) {
        Map<String, List<Object>> map = this.f1156c;
        if (map == null) {
            return 0;
        }
        return ((List) this.f1156c.get((String) map.keySet().toArray()[i])).size();
    }

    public int getGroupCount() {
        Map<String, List<Object>> map = this.f1156c;
        if (map != null) {
            return map.keySet().size();
        }
        return 0;
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.f1157d.getSystemService("layout_inflater")).inflate(C1266R.layout.com_navibees_sdk_route_to_activity_category_list_item, null);
        }
        TextView textView = (TextView) view.findViewById(C1266R.C1269id.poi_category_name);
        textView.setText(getGroup(i).name.getText());
        if (!this.f1158e.isEmpty()) {
            mo28904a(textView, this.f1158e);
        }
        return view;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public POICategory getGroup(int i) {
        return m800a((String) this.f1156c.keySet().toArray()[i]);
    }

    /* renamed from: a */
    public void mo28905a(Map<String, List<Object>> map) {
        this.f1156c = map;
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void mo28903a() {
        this.f1156c.clear();
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void mo28904a(TextView textView, String str) {
        String charSequence = textView.getText().toString();
        int i = -1;
        do {
            i = charSequence.toLowerCase().indexOf(str.toLowerCase(), i + 1);
            if (i <= 0) {
                break;
            }
        } while (charSequence.charAt(i - 1) != ' ');
        if (i != -1) {
            int length = str.length() + i;
            SpannableString spannableString = new SpannableString(charSequence);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#9B9B9B")), i, length, 17);
            textView.setText(spannableString);
        }
    }

    /* renamed from: a */
    public List<Object> mo28902a(POICategory pOICategory) {
        if (pOICategory != null) {
            Map<String, List<Object>> map = this.f1155b;
            if (map != null && map.size() > 0) {
                return (List) this.f1155b.get(pOICategory.f1340id);
            }
        }
        return null;
    }
}
