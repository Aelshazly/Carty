package com.google.android.gms.vision;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public abstract class FocusingProcessor<T> implements Processor<T> {
    private Tracker<T> zzaj;
    private int zzak = 3;
    private boolean zzal = false;
    private int zzam;
    private int zzan = 0;
    private Detector<T> zzt;

    public FocusingProcessor(Detector<T> detector, Tracker<T> tracker) {
        this.zzt = detector;
        this.zzaj = tracker;
    }

    public abstract int selectFocus(Detections<T> detections);

    public void release() {
        this.zzaj.onDone();
    }

    public void receiveDetections(Detections<T> detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        if (detectedItems.size() == 0) {
            if (this.zzan == this.zzak) {
                this.zzaj.onDone();
                this.zzal = false;
            } else {
                this.zzaj.onMissing(detections);
            }
            this.zzan++;
            return;
        }
        this.zzan = 0;
        if (this.zzal) {
            Object obj = detectedItems.get(this.zzam);
            if (obj != null) {
                this.zzaj.onUpdate(detections, obj);
                return;
            } else {
                this.zzaj.onDone();
                this.zzal = false;
            }
        }
        int selectFocus = selectFocus(detections);
        Object obj2 = detectedItems.get(selectFocus);
        if (obj2 == null) {
            StringBuilder sb = new StringBuilder(35);
            sb.append("Invalid focus selected: ");
            sb.append(selectFocus);
            Log.w("FocusingProcessor", sb.toString());
            return;
        }
        this.zzal = true;
        this.zzam = selectFocus;
        this.zzt.setFocus(this.zzam);
        this.zzaj.onNewItem(this.zzam, obj2);
        this.zzaj.onUpdate(detections, obj2);
    }

    /* access modifiers changed from: protected */
    public final void zza(int i) {
        if (i >= 0) {
            this.zzak = i;
            return;
        }
        StringBuilder sb = new StringBuilder(28);
        sb.append("Invalid max gap: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }
}
