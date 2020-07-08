package com.navibees.core.p024c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.google.android.material.tabs.TabLayout;
import com.navibees.core.activity.ActivitiesActivity;
import com.navibees.core.p021b.C1610a;
import com.navibees.sdk.C1266R;

/* renamed from: com.navibees.core.c.b */
/* compiled from: ActivitiesPagerFragment */
public class C1640b extends Fragment implements OnPageChangeListener {
    /* renamed from: a */
    private void m916a(View view) {
        ((Toolbar) view.findViewById(C1266R.C1269id.activitiesToolBar)).setTitle(getActivity().getTitle());
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(C1266R.layout.com_navibees_sdk_activities_pager_fragment, viewGroup, false);
        ViewPager viewPager = (ViewPager) inflate.findViewById(C1266R.C1269id.activitiesViewPager);
        viewPager.setAdapter(new C1610a(getActivity(), getChildFragmentManager()));
        ((TabLayout) inflate.findViewById(C1266R.C1269id.activitiesTabs)).setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(this);
        return inflate;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        SearchView c = ((ActivitiesActivity) getActivity()).mo28757c();
        TextView b = ((ActivitiesActivity) getActivity()).mo28756b();
        if (c != null && b != null) {
            if (i == 0) {
                c.setVisibility(0);
                if (!c.isIconified()) {
                    b.setVisibility(8);
                }
            } else if (i == 1) {
                c.setVisibility(8);
                b.setVisibility(0);
            }
        }
    }
}
