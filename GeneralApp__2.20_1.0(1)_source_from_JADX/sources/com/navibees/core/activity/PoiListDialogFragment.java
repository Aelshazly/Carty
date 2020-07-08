package com.navibees.core.activity;

import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.DialogFragment;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import java.util.List;

public class PoiListDialogFragment extends DialogFragment {

    /* renamed from: a */
    private String f1086a;

    /* renamed from: b */
    private String f1087b;

    /* renamed from: c */
    private List<String> f1088c = new ArrayList();

    /* renamed from: a */
    private void m748a() {
        String string = getArguments().getString("dest");
        double d = getArguments().getDouble("length", 0.0d);
        double d2 = getArguments().getDouble("duration", 0.0d);
        double d3 = getArguments().getDouble("x", 0.0d);
        double d4 = getArguments().getDouble("y", 0.0d);
        double d5 = getArguments().getDouble("z", 0.0d);
        StringBuilder sb = new StringBuilder();
        sb.append("X: ");
        sb.append(d3);
        sb.append("\nY: ");
        sb.append(d4);
        sb.append("\nAltitude: ");
        sb.append(d5);
        this.f1086a = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("dest: ");
        sb2.append(string);
        sb2.append("\nlength: ");
        sb2.append(d);
        sb2.append("\nduration: ");
        sb2.append(d2);
        this.f1087b = sb2.toString();
        this.f1088c.add(this.f1087b);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        this.f1086a = arguments.getString("loc");
        this.f1088c = arguments.getStringArrayList(Param.ITEMS);
        List<String> list = this.f1088c;
        String[] strArr = (String[]) list.toArray(new String[list.size()]);
        Builder builder = new Builder(getActivity());
        builder.setTitle((CharSequence) this.f1086a).setItems((CharSequence[]) strArr, (OnClickListener) null);
        return builder.create();
    }
}
