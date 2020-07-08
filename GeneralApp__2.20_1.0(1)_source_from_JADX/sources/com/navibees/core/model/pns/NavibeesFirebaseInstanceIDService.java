package com.navibees.core.model.pns;

import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.navibees.core.NaviBeesManager;

public class NavibeesFirebaseInstanceIDService extends FirebaseMessagingService {

    /* renamed from: com.navibees.core.model.pns.NavibeesFirebaseInstanceIDService$a */
    class C1687a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f1343a;

        C1687a(String str) {
            this.f1343a = str;
        }

        public void run() {
            NaviBeesManager.getInstance(NavibeesFirebaseInstanceIDService.this.getApplication()).getServerManager().registerPNSDeviceIdentifier(NavibeesFirebaseInstanceIDService.this.getApplication(), this.f1343a);
        }
    }

    public void onNewToken(@NonNull String str) {
        super.onNewToken(str);
        String token = FirebaseInstanceId.getInstance().getToken();
        StringBuilder sb = new StringBuilder();
        sb.append("Refreshed token: ");
        sb.append(token);
        Log.d("NBFBInstanceIDService", sb.toString());
        if (NaviBeesManager.getInstance(getApplication()).isInitialized()) {
            new Handler(getMainLooper()).post(new C1687a(token));
        }
    }
}
