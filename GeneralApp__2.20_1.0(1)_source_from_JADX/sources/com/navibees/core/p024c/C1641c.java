package com.navibees.core.p024c;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.activity.ActivitiesActivity;
import com.navibees.core.model.license.NaviBeesFeature;
import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;
import com.navibees.core.model.metadata.json.Activity;
import com.navibees.sdk.C1266R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/* renamed from: com.navibees.core.c.c */
/* compiled from: ActivityDescriptionFragment */
public class C1641c extends Fragment implements OnClickListener {

    /* renamed from: a */
    private static final String f1242a = "activity";

    /* renamed from: a */
    public static C1641c m917a(Activity activity) {
        C1641c cVar = new C1641c();
        Bundle bundle = new Bundle();
        bundle.putParcelable(f1242a, activity);
        cVar.setArguments(bundle);
        return cVar;
    }

    public void onClick(View view) {
        if (view.getId() == C1266R.C1269id.com_uqu_navibees_sdk_activity_desc_routeTo_button && view.getTag() != null) {
            int intValue = ((Integer) view.getTag()).intValue();
            Intent intent = new Intent();
            intent.putExtra(NaviBeesConstants.SELECTED_ACTIVITY_POI_ID, intValue);
            getActivity().setResult(-1, intent);
            getActivity().finish();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str;
        View inflate = layoutInflater.inflate(C1266R.layout.com_navibees_sdk_activity_description_fragment, viewGroup, false);
        try {
            NaviBeesManager.getInstance(getActivity().getApplication()).getLicenseManager().mo29048a(NaviBeesFeature.Temporal_Based_Event_Activities_Notification);
            Activity activity = (Activity) getArguments().getParcelable(f1242a);
            if (activity != null) {
                ((TextView) inflate.findViewById(C1266R.C1269id.com_uqu_navibees_sdk_activity_title)).setText(activity.name.getText());
                ((TextView) inflate.findViewById(C1266R.C1269id.com_uqu_navibees_sdk_activity_owner)).setText(activity.owner.getText());
                ((TextView) inflate.findViewById(C1266R.C1269id.com_uqu_navibees_sdk_activity_desc)).setText(activity.description.getText());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                try {
                    Date parse = simpleDateFormat.parse(activity.startDate);
                    Date parse2 = simpleDateFormat.parse(activity.endDate);
                    StringBuilder sb = new StringBuilder();
                    sb.append(simpleDateFormat2.format(parse));
                    sb.append(" - ");
                    sb.append(simpleDateFormat2.format(parse2));
                    str = sb.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    str = "";
                }
                ((TextView) inflate.findViewById(C1266R.C1269id.com_uqu_navibees_sdk_activity_date)).setText(str);
                ImageView imageView = (ImageView) inflate.findViewById(C1266R.C1269id.com_uqu_navibees_sdk_activity_desc_routeTo_button);
                imageView.setTag(activity.poiId);
                imageView.setOnClickListener(this);
            }
            m918a();
        } catch (NaviBeesLicenseExpireException | NaviBeesLicenseNotAuthorizedException e2) {
            e2.printStackTrace();
        }
        return inflate;
    }

    /* renamed from: a */
    private void m918a() {
        SearchView c = ((ActivitiesActivity) getActivity()).mo28757c();
        TextView b = ((ActivitiesActivity) getActivity()).mo28756b();
        if (c != null) {
            c.setVisibility(8);
            c.onActionViewCollapsed();
            if (b != null) {
                b.setText(getActivity().getTitle());
                b.setVisibility(0);
            }
        }
    }

    /* renamed from: a */
    private void m919a(View view, String str) {
        ((Toolbar) view.findViewById(C1266R.C1269id.activitiesToolBar)).setTitle((CharSequence) str);
    }
}
