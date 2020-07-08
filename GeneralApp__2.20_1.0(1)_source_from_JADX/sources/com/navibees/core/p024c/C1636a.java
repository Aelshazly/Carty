package com.navibees.core.p024c;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnCloseListener;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.activity.ActivitiesActivity;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;
import com.navibees.core.model.metadata.json.Activity;
import com.navibees.core.model.metadata.json.ActivityGroup;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.p021b.C1611b;
import com.navibees.sdk.C1266R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.navibees.core.c.a */
/* compiled from: ActivitiesListFragment */
public class C1636a extends Fragment implements OnQueryTextListener {

    /* renamed from: a */
    private C1639c f1236a = null;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public TextView f1237b = null;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public C1611b f1238c = null;

    /* renamed from: com.navibees.core.c.a$a */
    /* compiled from: ActivitiesListFragment */
    class C1637a implements OnClickListener {
        C1637a() {
        }

        public void onClick(View view) {
            if (C1636a.this.f1237b != null) {
                C1636a.this.f1237b.setVisibility(8);
            }
        }
    }

    /* renamed from: com.navibees.core.c.a$b */
    /* compiled from: ActivitiesListFragment */
    class C1638b implements OnCloseListener {
        C1638b() {
        }

        public boolean onClose() {
            if (C1636a.this.f1237b != null) {
                C1636a.this.f1237b.setText(C1636a.this.getActivity().getTitle());
                C1636a.this.f1237b.setVisibility(0);
            }
            C1636a.this.f1238c.mo28900b();
            return false;
        }
    }

    /* renamed from: com.navibees.core.c.a$c */
    /* compiled from: ActivitiesListFragment */
    private class C1639c extends AsyncTask<String, String, List<Activity>> {
        private C1639c() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public List<Activity> doInBackground(String... strArr) {
            publishProgress(new String[]{strArr[0]});
            return C1636a.this.f1238c.mo28895a(strArr[0]);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void onProgressUpdate(String... strArr) {
            super.onProgressUpdate(strArr);
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
        }

        /* synthetic */ C1639c(C1636a aVar, C1637a aVar2) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(List<Activity> list) {
            C1636a.this.f1238c.mo28899a(list);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1266R.layout.com_navibees_sdk_activities_list_fragment, viewGroup, false);
        try {
            NaviBeesManager.getInstance(getActivity().getApplication()).getLicenseManager().mo29048a(NaviBeesFeature.Temporal_Based_Event_Activities_Notification);
            boolean z = getArguments().getBoolean("currentFilter");
            Building currentBuilding = NaviBeesManager.getInstance(getActivity().getApplication()).getMetaDataManager().getCurrentBuilding();
            List<ActivityGroup> list = null;
            if (currentBuilding != null) {
                list = currentBuilding.activityGroups;
            }
            if (list == null) {
                list = new ArrayList<>();
            }
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(C1266R.C1269id.activitiesRecyclerView);
            this.f1238c = new C1611b(list, z, getActivity());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(this.f1238c);
            recyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(this.f1238c));
            if (!z) {
                m911a();
            }
        } catch (NaviBeesLicenseExpireException | NaviBeesLicenseNotAuthorizedException e) {
            e.printStackTrace();
        }
        return inflate;
    }

    public boolean onQueryTextChange(String str) {
        C1639c cVar = this.f1236a;
        if (cVar != null) {
            cVar.cancel(true);
        }
        if (TextUtils.isEmpty(str)) {
            this.f1238c.mo28900b();
        } else {
            this.f1238c.mo28896a();
            this.f1236a = new C1639c(this, null);
            this.f1236a.execute(new String[]{str});
        }
        return true;
    }

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    /* renamed from: a */
    public static C1636a m910a(boolean z) {
        C1636a aVar = new C1636a();
        Bundle bundle = new Bundle();
        bundle.putBoolean("currentFilter", z);
        aVar.setArguments(bundle);
        return aVar;
    }

    /* renamed from: a */
    private void m911a() {
        SearchView c = ((ActivitiesActivity) getActivity()).mo28757c();
        this.f1237b = ((ActivitiesActivity) getActivity()).mo28756b();
        if (c != null) {
            c.setVisibility(0);
            c.setOnSearchClickListener(new C1637a());
            c.setOnCloseListener(new C1638b());
            c.setOnQueryTextListener(this);
        }
    }
}
