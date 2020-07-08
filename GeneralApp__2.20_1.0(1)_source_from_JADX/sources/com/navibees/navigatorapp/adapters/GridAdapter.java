package com.navibees.navigatorapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.navibees.navigatorapp.C1170R;
import com.navibees.navigatorapp.utils.MenuStatic;
import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter {
    private ArrayList<String> data;
    private MenuStatic menu;

    public GridAdapter(Context context, MenuStatic menu2) {
        super(context, C1170R.layout.layout_dashboard_item, menu2.getItems(context));
        this.menu = menu2;
        this.data = menu2.getItems(context);
    }

    public View getView(int position, View v, ViewGroup g) {
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(C1170R.layout.layout_dashboard_item, null);
        }
        if (this.data.get(position) != null) {
            TextView tv = (TextView) v.findViewById(C1170R.C1173id.iconText);
            ((ImageView) v.findViewById(C1170R.C1173id.iconImg)).setImageResource(this.menu.getResoutceIndex(position));
            tv.setText((CharSequence) this.data.get(position));
        }
        return v;
    }
}
