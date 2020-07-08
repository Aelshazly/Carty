package com.navibees.core.model.postioning;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender.SendIntentException;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest.Builder;
import com.google.android.gms.location.LocationSettingsResult;
import com.navibees.core.interfaces.OutdoorLocationListener;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.IndoorLocationConfidence;
import com.navibees.core.util.NaviBeesUtils;
import java.util.Random;

/* renamed from: com.navibees.core.model.postioning.d */
/* compiled from: OutdoorPositionManager */
public class C1701d implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    /* renamed from: i */
    private static final String f1469i = "OutdoorPositionManager";

    /* renamed from: j */
    private static int f1470j = 3000;

    /* renamed from: k */
    private static int f1471k = 1000;

    /* renamed from: l */
    private static int f1472l = 5;

    /* renamed from: m */
    static Location[] f1473m = new Location[2];

    /* renamed from: a */
    private Location f1474a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public GoogleApiClient f1475b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public LocationRequest f1476c;

    /* renamed from: d */
    private OutdoorLocationListener f1477d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public Context f1478e;

    /* renamed from: f */
    private Activity f1479f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public boolean f1480g = false;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public boolean f1481h = false;

    /* renamed from: com.navibees.core.model.postioning.d$a */
    /* compiled from: OutdoorPositionManager */
    class C1702a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ GoogleApiAvailability f1482a;

        /* renamed from: b */
        final /* synthetic */ Activity f1483b;

        /* renamed from: c */
        final /* synthetic */ int f1484c;

        C1702a(GoogleApiAvailability googleApiAvailability, Activity activity, int i) {
            this.f1482a = googleApiAvailability;
            this.f1483b = activity;
            this.f1484c = i;
        }

        public void run() {
            this.f1482a.getErrorDialog(this.f1483b, this.f1484c, 6).show();
        }
    }

    /* renamed from: com.navibees.core.model.postioning.d$b */
    /* compiled from: OutdoorPositionManager */
    class C1703b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Activity f1486a;

        C1703b(Activity activity) {
            this.f1486a = activity;
        }

        public void run() {
            if (NaviBeesUtils.isMarshmallowOrAbove()) {
                String str = "android.permission.ACCESS_FINE_LOCATION";
                if (!C1701d.this.f1481h && ActivityCompat.checkSelfPermission(C1701d.this.f1478e, str) != 0) {
                    Activity activity = this.f1486a;
                    if (activity != null) {
                        NaviBeesUtils.requestLocationPermission(activity);
                        C1701d.this.f1481h = true;
                    }
                } else if (ActivityCompat.checkSelfPermission(C1701d.this.f1478e, str) == 0) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(C1701d.this.f1475b, C1701d.this.f1476c, (LocationListener) C1701d.this);
                    C1701d.this.f1480g = true;
                }
            } else {
                LocationServices.FusedLocationApi.requestLocationUpdates(C1701d.this.f1475b, C1701d.this.f1476c, (LocationListener) C1701d.this);
                C1701d.this.f1480g = true;
            }
        }
    }

    /* renamed from: com.navibees.core.model.postioning.d$c */
    /* compiled from: OutdoorPositionManager */
    class C1704c implements Runnable {
        C1704c() {
        }

        public void run() {
            if (C1701d.this.f1480g && C1701d.this.f1475b != null && C1701d.this.f1475b.isConnected()) {
                C1701d.this.f1480g = false;
                LocationServices.FusedLocationApi.removeLocationUpdates(C1701d.this.f1475b, (LocationListener) C1701d.this);
            }
        }
    }

    /* renamed from: com.navibees.core.model.postioning.d$d */
    /* compiled from: OutdoorPositionManager */
    class C1705d implements ResultCallback<LocationSettingsResult> {

        /* renamed from: a */
        final /* synthetic */ Activity f1489a;

        C1705d(Activity activity) {
            this.f1489a = activity;
        }

        /* renamed from: a */
        public void onResult(LocationSettingsResult locationSettingsResult) {
            Status status = locationSettingsResult.getStatus();
            int statusCode = status.getStatusCode();
            if (statusCode == 0) {
                C1701d.this.mo29363c(this.f1489a);
            } else if (statusCode == 6) {
                try {
                    if (this.f1489a != null) {
                        status.startResolutionForResult(this.f1489a, 6);
                    }
                } catch (SendIntentException e) {
                }
            }
        }
    }

    static {
        String str = "Fused";
        f1473m[0] = new Location(str);
        f1473m[0].setLongitude(39.95331483046043d);
        f1473m[0].setLatitude(21.32902894331999d);
        f1473m[0].setAccuracy(10.0f);
        f1473m[1] = new Location(str);
        f1473m[1].setLongitude(39.953386040559955d);
        f1473m[1].setLatitude(21.328865258296634d);
        f1473m[1].setAccuracy(10.0f);
    }

    public C1701d(Context context, OutdoorLocationListener outdoorLocationListener) {
        this.f1477d = outdoorLocationListener;
        this.f1478e = context;
    }

    public void onConnected(Bundle bundle) {
        m1086d(this.f1479f);
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection failed: ConnectionResult.getErrorCode() = ");
        sb.append(connectionResult.getErrorCode());
        Log.i(f1469i, sb.toString());
    }

    public void onConnectionSuspended(int i) {
        this.f1475b.connect();
    }

    public void onLocationChanged(Location location) {
        this.f1474a = location;
        if (this.f1474a != null && this.f1477d != null) {
            IndoorLocation indoorLocation = new IndoorLocation(location.getLongitude(), location.getLatitude());
            indoorLocation.confidence = IndoorLocationConfidence.Average;
            indoorLocation.floor = -1;
            if (location.hasAccuracy()) {
                this.f1477d.onLocationUpdate(indoorLocation, (int) location.getAccuracy());
            }
        }
    }

    /* renamed from: c */
    private void m1083c() {
        this.f1476c = new LocationRequest();
        this.f1476c.setInterval((long) f1470j);
        this.f1476c.setFastestInterval((long) f1471k);
        this.f1476c.setPriority(100);
    }

    /* renamed from: d */
    private IndoorLocation m1085d() {
        int nextInt = new Random().nextInt(f1473m.length);
        return new IndoorLocation(f1473m[nextInt].getLongitude(), f1473m[nextInt].getLatitude());
    }

    /* renamed from: a */
    public void mo29361a(Activity activity) {
        this.f1479f = activity;
        if (this.f1475b == null && m1076a(this.f1478e, activity)) {
            m1080b();
            m1083c();
        }
        GoogleApiClient googleApiClient = this.f1475b;
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    /* renamed from: b */
    public void mo29362b(Activity activity) {
        GoogleApiClient googleApiClient = this.f1475b;
        if (googleApiClient != null && googleApiClient.isConnected()) {
            this.f1475b.disconnect();
        }
        if (this.f1479f == activity) {
            this.f1479f = null;
        }
    }

    /* renamed from: d */
    private void m1086d(Activity activity) {
        LocationServices.SettingsApi.checkLocationSettings(this.f1475b, new Builder().addLocationRequest(this.f1476c).setAlwaysShow(true).build()).setResultCallback(new C1705d(activity));
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(23)
    /* renamed from: c */
    public void mo29363c(Activity activity) {
        if (!this.f1480g) {
            GoogleApiClient googleApiClient = this.f1475b;
            if (googleApiClient != null && googleApiClient.isConnected()) {
                new Handler(Looper.getMainLooper()).post(new C1703b(activity));
            }
        }
    }

    /* renamed from: b */
    private synchronized void m1080b() {
        this.f1475b = new GoogleApiClient.Builder(this.f1478e).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }

    /* renamed from: a */
    private boolean m1076a(Context context, Activity activity) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable == 0) {
            return true;
        }
        if (instance.isUserResolvableError(isGooglePlayServicesAvailable) && activity != null) {
            activity.runOnUiThread(new C1702a(instance, activity, isGooglePlayServicesAvailable));
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo29360a() {
        new Handler(Looper.getMainLooper()).post(new C1704c());
    }
}
