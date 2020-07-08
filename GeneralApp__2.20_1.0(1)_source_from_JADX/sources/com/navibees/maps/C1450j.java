package com.navibees.maps;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.visioglobe.libVisioMove.VgIGeometryCallback;
import com.visioglobe.libVisioMove.VgIGeometryEvent;

/* renamed from: com.navibees.maps.j */
/* compiled from: VgMyPoiListener */
public class C1450j extends VgIGeometryCallback {

    /* renamed from: a */
    Activity f611a;

    /* renamed from: b */
    String f612b;

    /* renamed from: com.navibees.maps.j$a */
    /* compiled from: VgMyPoiListener */
    class C1451a implements Runnable {

        /* renamed from: com.navibees.maps.j$a$a */
        /* compiled from: VgMyPoiListener */
        class C1452a implements OnClickListener {
            C1452a(C1451a aVar) {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }

        C1451a() {
        }

        public void run() {
            Builder builder = new Builder(C1450j.this.f611a);
            builder.setTitle("Beacon Info");
            builder.setMessage(C1450j.this.f612b);
            builder.setPositiveButton("OK", new C1452a(this));
            builder.create().show();
        }
    }

    C1450j(Activity activity, String str) {
        this.f611a = activity;
        this.f612b = str;
    }

    public void handleGeometryEvent(VgIGeometryEvent vgIGeometryEvent) {
        this.f611a.runOnUiThread(new C1451a());
    }
}
