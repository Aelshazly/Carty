package com.navibees.core.p021b;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.collection.SimpleArrayMap;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.activity.ActivitiesActivity;
import com.navibees.core.model.metadata.json.Activity;
import com.navibees.core.model.metadata.json.ActivityGroup;
import com.navibees.core.util.NaviBeesUtils;
import com.navibees.sdk.C1266R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/* renamed from: com.navibees.core.b.b */
/* compiled from: ActivitiesRecyclerAdapter */
public class C1611b extends Adapter<C1613b> implements StickyRecyclerHeadersAdapter<C1612a> {

    /* renamed from: h */
    public static final int f1137h = 1;

    /* renamed from: i */
    public static final int f1138i = 2;

    /* renamed from: j */
    public static final int f1139j = 3;

    /* renamed from: a */
    private List<Activity> f1140a;

    /* renamed from: b */
    private SimpleArrayMap<String, String> f1141b;

    /* renamed from: c */
    private List<ActivityGroup> f1142c;

    /* renamed from: d */
    private List<Activity> f1143d;

    /* renamed from: e */
    private int f1144e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public Context f1145f;

    /* renamed from: g */
    private String f1146g;

    /* renamed from: com.navibees.core.b.b$a */
    /* compiled from: ActivitiesRecyclerAdapter */
    public class C1612a extends ViewHolder {

        /* renamed from: a */
        public TextView f1147a;

        public C1612a(View view) {
            super(view);
            this.f1147a = (TextView) view.findViewById(C1266R.C1269id.activityHeaderTextView);
        }
    }

    /* renamed from: com.navibees.core.b.b$b */
    /* compiled from: ActivitiesRecyclerAdapter */
    public class C1613b extends ViewHolder implements OnClickListener {

        /* renamed from: a */
        public TextView f1149a;

        /* renamed from: b */
        public TextView f1150b;

        /* renamed from: c */
        public ImageView f1151c;

        public C1613b(View view) {
            super(view);
            this.f1149a = (TextView) view.findViewById(C1266R.C1269id.activityNameTextView);
            this.f1150b = (TextView) view.findViewById(C1266R.C1269id.activityStartEndDateTextView);
            this.f1151c = (ImageView) view.findViewById(C1266R.C1269id.activityInfoImageView);
            if (C1611b.this.f1145f instanceof ActivitiesActivity) {
                ((ActivitiesActivity) C1611b.this.f1145f).customiseActivityInfoIcon(this.f1151c);
                this.f1151c.setOnClickListener(this);
                view.setOnClickListener(this);
            }
        }

        public void onClick(View view) {
            if (view.getId() == C1266R.C1269id.activityInfoImageView) {
                if (view.getTag() != null && (view.getTag() instanceof Activity)) {
                    Activity activity = (Activity) view.getTag();
                    if (C1611b.this.f1145f instanceof ActivitiesActivity) {
                        ((ActivitiesActivity) C1611b.this.f1145f).mo28755a(activity);
                    }
                }
            } else if (view.getTag() != null) {
                int intValue = ((Integer) view.getTag()).intValue();
                if (C1611b.this.f1145f instanceof ActivitiesActivity) {
                    ActivitiesActivity activitiesActivity = (ActivitiesActivity) C1611b.this.f1145f;
                    Intent intent = new Intent();
                    intent.putExtra(NaviBeesConstants.SELECTED_ACTIVITY_POI_ID, intValue);
                    activitiesActivity.setResult(-1, intent);
                    activitiesActivity.finish();
                }
            }
        }
    }

    public C1611b(List<ActivityGroup> list, boolean z, Context context) {
        this.f1146g = "";
        this.f1142c = list;
        this.f1145f = context;
        this.f1140a = new ArrayList();
        this.f1143d = new ArrayList();
        this.f1141b = new SimpleArrayMap<>();
        for (ActivityGroup activityGroup : list) {
            for (Activity activity : activityGroup.activities) {
                if (!z || activity.isHappeningNow()) {
                    this.f1140a.add(activity);
                }
                activity.groupId = activityGroup.f1326id;
            }
            this.f1141b.put(activityGroup.f1326id, activityGroup.name.getText());
        }
        this.f1144e = 1;
        this.f1143d.addAll(this.f1140a);
    }

    /* renamed from: b */
    public void mo28900b() {
        this.f1146g = "";
        this.f1143d.clear();
        this.f1143d.addAll(this.f1140a);
        notifyDataSetChanged();
    }

    public long getHeaderId(int i) {
        int i2 = this.f1144e;
        if (i2 == 1) {
            return (long) ((Activity) this.f1143d.get(i)).groupId.hashCode();
        }
        if (i2 != 2) {
            return (long) ((Activity) this.f1143d.get(i)).groupId.hashCode();
        }
        return (long) ((Activity) this.f1143d.get(i)).startDate.hashCode();
    }

    public int getItemCount() {
        return this.f1143d.size();
    }

    /* renamed from: a */
    public void onBindViewHolder(C1613b bVar, int i) {
        String str;
        String str2 = "--";
        Activity activity = (Activity) this.f1143d.get(i);
        bVar.f1149a.setText(activity.name.getText());
        m793a(bVar.f1149a, this.f1146g);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        try {
            str = simpleDateFormat2.format(simpleDateFormat.parse(activity.startDate));
            try {
                str2 = simpleDateFormat2.format(simpleDateFormat.parse(activity.endDate));
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                TextView textView = bVar.f1150b;
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" - ");
                sb.append(str2);
                textView.setText(sb.toString());
                bVar.f1151c.setTag(activity);
                bVar.itemView.setTag(activity.poiId);
            }
        } catch (Exception e2) {
            e = e2;
            str = str2;
            e.printStackTrace();
            TextView textView2 = bVar.f1150b;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(" - ");
            sb2.append(str2);
            textView2.setText(sb2.toString());
            bVar.f1151c.setTag(activity);
            bVar.itemView.setTag(activity.poiId);
        }
        TextView textView22 = bVar.f1150b;
        StringBuilder sb22 = new StringBuilder();
        sb22.append(str);
        sb22.append(" - ");
        sb22.append(str2);
        textView22.setText(sb22.toString());
        bVar.f1151c.setTag(activity);
        bVar.itemView.setTag(activity.poiId);
    }

    public C1612a onCreateHeaderViewHolder(ViewGroup viewGroup) {
        return new C1612a(LayoutInflater.from(viewGroup.getContext()).inflate(C1266R.layout.com_navibees_sdk_activity_header_item, viewGroup, false));
    }

    public C1613b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new C1613b(LayoutInflater.from(viewGroup.getContext()).inflate(C1266R.layout.com_navibees_sdk_activity_item, viewGroup, false));
    }

    public C1611b(List<ActivityGroup> list, boolean z, Context context, int i) {
        this(list, z, context);
        this.f1144e = i;
    }

    /* renamed from: a */
    public void onBindHeaderViewHolder(C1612a aVar, int i) {
        aVar.f1147a.setText((String) this.f1141b.get(((Activity) this.f1143d.get(i)).groupId));
    }

    /* renamed from: a */
    public List<Activity> mo28895a(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.toUpperCase().split(" ");
            ArrayList arrayList = new ArrayList();
            for (ActivityGroup activityGroup : this.f1142c) {
                List<Activity> list = activityGroup.activities;
                if (NaviBeesUtils.searchInString(activityGroup.name.getText(), split)) {
                    if (list != null) {
                        arrayList.addAll(list);
                    }
                } else if (list != null) {
                    for (Activity activity : list) {
                        if (NaviBeesUtils.searchInString(activity.name.getText(), split)) {
                            arrayList.add(activity);
                        }
                    }
                }
            }
            this.f1146g = str;
            return arrayList;
        }
        this.f1146g = "";
        return new ArrayList(this.f1140a);
    }

    /* renamed from: a */
    public void mo28899a(List<Activity> list) {
        this.f1143d = list;
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void mo28896a() {
        this.f1143d.clear();
        notifyDataSetChanged();
    }

    /* renamed from: a */
    private void m793a(TextView textView, String str) {
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
            spannableString.setSpan(new ForegroundColorSpan(-16776961), i, length, 17);
            textView.setText(spannableString);
        }
    }
}
