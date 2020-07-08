package com.navibees.navigatorapp.utils;

import android.content.Context;
import com.navibees.navigatorapp.C1170R;
import com.navibees.navigatorapp.data.Prefs;
import java.util.ArrayList;
import java.util.Arrays;

public class MenuStatic {
    ArrayList<Integer> icons = new ArrayList<>();
    Prefs prefs;

    public MenuStatic(Context context) {
        this.prefs = Prefs.getInstance(context);
        this.icons.add(Integer.valueOf(C1170R.C1172drawable.map));
        this.icons.add(Integer.valueOf(C1170R.C1172drawable.poi));
        this.icons.add(Integer.valueOf(C1170R.C1172drawable.facilities));
        this.icons.add(Integer.valueOf(C1170R.C1172drawable.way_finding));
    }

    public ArrayList<String> getItems(Context cntx) {
        return new ArrayList<>(Arrays.asList(LocaleUtils.getLocalizedResources(cntx).getStringArray(C1170R.array.items)));
    }

    public int getResoutceIndex(int index) {
        return ((Integer) this.icons.get(index)).intValue();
    }
}
