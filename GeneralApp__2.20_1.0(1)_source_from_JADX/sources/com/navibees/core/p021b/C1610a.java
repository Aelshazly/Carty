package com.navibees.core.p021b;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.navibees.core.p024c.C1636a;
import com.navibees.sdk.C1266R;

/* renamed from: com.navibees.core.b.a */
/* compiled from: ActivitiesPagerAdapter */
public class C1610a extends FragmentPagerAdapter {

    /* renamed from: a */
    private Context f1136a;

    public C1610a(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.f1136a = context;
    }

    public int getCount() {
        return 2;
    }

    public Fragment getItem(int i) {
        if (i == 0) {
            return C1636a.m910a(false);
        }
        if (i != 1) {
            return C1636a.m910a(false);
        }
        return C1636a.m910a(true);
    }

    public CharSequence getPageTitle(int i) {
        if (i != 0) {
            return i != 1 ? "" : this.f1136a.getString(C1266R.string.current_tab_title);
        }
        return this.f1136a.getString(C1266R.string.all_tab_title);
    }
}
