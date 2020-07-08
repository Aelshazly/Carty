package com.navibees.visioglobe;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.SurfaceHolder;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.navibees.core.NaviBeesConstants.MapInteraction;
import com.visioglobe.libVisioMove.VgIApplication;
import com.visioglobe.libVisioMove.VgICamera;
import com.visioglobe.libVisioMove.VgPosition;
import com.visioglobe.libVisioMove.VgSRSConstRefPtr;
import com.visioglobe.libVisioMove.VgSurfaceView;
import java.util.Iterator;
import java.util.LinkedList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public final class VgMySurfaceView extends VgSurfaceView implements OnGestureListener, OnScaleGestureListener {

    /* renamed from: h */
    public static boolean f707h = false;

    /* renamed from: a */
    protected LinkedList<C1471a> f708a = new LinkedList<>();

    /* renamed from: b */
    protected LocalBroadcastManager f709b;

    /* renamed from: c */
    private float f710c;

    /* renamed from: d */
    private float f711d;

    /* renamed from: e */
    private boolean f712e = false;

    /* renamed from: f */
    private GestureDetector f713f;

    /* renamed from: g */
    private ScaleGestureDetector f714g;

    /* renamed from: com.navibees.visioglobe.VgMySurfaceView$a */
    public interface C1471a {
        void onSurfaceChanged(GL10 gl10, int i, int i2);

        void onSurfaceDestroyed();
    }

    static {
        System.loadLibrary("VisioMove");
    }

    public VgMySurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, false);
        this.f709b = LocalBroadcastManager.getInstance(context);
        this.f713f = new GestureDetector(context, this);
        this.f714g = new ScaleGestureDetector(context, this);
    }

    /* renamed from: a */
    public void mo28536a(C1471a aVar) {
        this.f708a.add(aVar);
    }

    /* renamed from: b */
    public void mo28537b(C1471a aVar) {
        this.f708a.remove(aVar);
    }

    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.f712e) {
            Intent intent = new Intent("com.navibees.map.gesture");
            intent.putExtra("com.navibees.map.gesture.action", "com.navibees.map.gesture.stop_automode");
            this.f709b.sendBroadcast(intent);
        }
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        VgIApplication application = getApplication();
        if (application != null) {
            VgICamera editCamera = application.editEngine().editCamera();
            if (editCamera != null) {
                VgPosition vgPosition = new VgPosition();
                if (editCamera.pickGeographicPoint((double) this.f710c, (double) this.f711d, vgPosition)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(vgPosition.getMXOrLongitude());
                    String str = "";
                    sb.append(str);
                    String sb2 = sb.toString();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(vgPosition.getMYOrLatitude());
                    sb3.append(str);
                    String sb4 = sb3.toString();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(vgPosition.getMSRS().isGeoReferenced());
                    sb5.append(str);
                    String sb6 = sb5.toString();
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("x,y,srs:");
                    sb7.append(sb2);
                    String str2 = ", ";
                    sb7.append(str2);
                    sb7.append(sb4);
                    sb7.append(str2);
                    sb7.append(sb6);
                    String str3 = "intersectionPosition";
                    Log.d(str3, sb7.toString());
                    VgPosition vgPosition2 = new VgPosition(vgPosition);
                    application.editEngine().getPositionToolbox().geoConvert(vgPosition2, VgSRSConstRefPtr.getNull());
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append(vgPosition2.getMXOrLongitude());
                    sb8.append(str);
                    String sb9 = sb8.toString();
                    StringBuilder sb10 = new StringBuilder();
                    sb10.append(vgPosition2.getMYOrLatitude());
                    sb10.append(str);
                    String sb11 = sb10.toString();
                    StringBuilder sb12 = new StringBuilder();
                    sb12.append(vgPosition2.getMSRS().isGeoReferenced());
                    sb12.append(str);
                    String sb13 = sb12.toString();
                    StringBuilder sb14 = new StringBuilder();
                    sb14.append("x1,y1,srs1:");
                    sb14.append(sb9);
                    sb14.append(str2);
                    sb14.append(sb11);
                    sb14.append(str2);
                    sb14.append(sb13);
                    Log.d(str3, sb14.toString());
                    Intent intent = new Intent("NaviBeesShareLocation");
                    intent.putExtra("NaviBees_SHARE_LOCATION_Z", vgPosition.getMZOrAltitude());
                    intent.putExtra("NaviBees_SHARE_LOCATION_X_Longitude", vgPosition2.getMXOrLongitude());
                    intent.putExtra("NaviBees_SHARE_LOCATION_Y_Latitude", vgPosition2.getMYOrLatitude());
                    this.f709b.sendBroadcast(intent);
                    f707h = true;
                }
            }
        }
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        this.f712e = true;
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        this.f712e = true;
        Intent intent = new Intent("com.navibees.map.gesture");
        intent.putExtra("com.navibees.map.gesture.action", "com.navibees.map.gesture.try_start_automode");
        this.f709b.sendBroadcast(intent);
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.f712e) {
            Intent intent = new Intent("com.navibees.map.gesture");
            intent.putExtra("com.navibees.map.gesture.action", "com.navibees.map.gesture.stop_automode");
            this.f709b.sendBroadcast(intent);
        }
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        super.onSurfaceChanged(gl10, i, i2);
        Iterator it = this.f708a.iterator();
        while (it.hasNext()) {
            ((C1471a) it.next()).onSurfaceChanged(gl10, i, i2);
        }
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        super.onSurfaceCreated(gl10, eGLConfig);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        String str = "com.navibees.map.gesture.action";
        String str2 = "com.navibees.map.gesture";
        if (motionEvent.getAction() == 0) {
            this.f710c = motionEvent.getX() / ((float) getWidth());
            this.f711d = motionEvent.getY() / ((float) getHeight());
            this.f712e = false;
            Intent intent = new Intent(str2);
            intent.putExtra(str, "com.navibees.map.gesture.pause_automode");
            this.f709b.sendBroadcast(intent);
        }
        this.f714g.onTouchEvent(motionEvent);
        this.f713f.onTouchEvent(motionEvent);
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            Intent intent2 = new Intent(str2);
            intent2.putExtra(str, "com.navibees.map.gesture.resume_automode");
            this.f709b.sendBroadcast(intent2);
        }
        boolean onTouch = super.onTouch(view, motionEvent);
        this.f709b.sendBroadcast(new Intent(MapInteraction.ACTION));
        return onTouch;
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Iterator it = this.f708a.iterator();
        while (it.hasNext()) {
            ((C1471a) it.next()).onSurfaceDestroyed();
        }
        mo28535a();
        pauseRendering();
        super.surfaceDestroyed(surfaceHolder);
    }

    /* renamed from: a */
    public void mo28535a() {
        this.f708a.clear();
    }

    public VgMySurfaceView(Context context) {
        super(context, false);
        this.f709b = LocalBroadcastManager.getInstance(context);
        this.f713f = new GestureDetector(context, this);
        this.f714g = new ScaleGestureDetector(context, this);
    }
}
