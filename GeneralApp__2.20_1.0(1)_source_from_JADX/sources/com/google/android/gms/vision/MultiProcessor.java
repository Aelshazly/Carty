package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;
import java.util.HashSet;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public class MultiProcessor<T> implements Processor<T> {
    /* access modifiers changed from: private */
    public int zzak;
    /* access modifiers changed from: private */
    public Factory<T> zzax;
    private SparseArray<zza> zzay;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static class Builder<T> {
        private MultiProcessor<T> zzba = new MultiProcessor<>();

        public Builder(Factory<T> factory) {
            if (factory != null) {
                this.zzba.zzax = factory;
                return;
            }
            throw new IllegalArgumentException("No factory supplied.");
        }

        public Builder<T> setMaxGapFrames(int i) {
            if (i >= 0) {
                this.zzba.zzak = i;
                return this;
            }
            StringBuilder sb = new StringBuilder(28);
            sb.append("Invalid max gap: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public MultiProcessor<T> build() {
            return this.zzba;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public interface Factory<T> {
        Tracker<T> create(T t);
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    class zza {
        /* access modifiers changed from: private */
        public Tracker<T> zzaj;
        /* access modifiers changed from: private */
        public int zzan;

        private zza(MultiProcessor multiProcessor) {
            this.zzan = 0;
        }
    }

    public void release() {
        for (int i = 0; i < this.zzay.size(); i++) {
            ((zza) this.zzay.valueAt(i)).zzaj.onDone();
        }
        this.zzay.clear();
    }

    public void receiveDetections(Detections<T> detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            Object valueAt = detectedItems.valueAt(i);
            if (this.zzay.get(keyAt) == null) {
                zza zza2 = new zza();
                zza2.zzaj = this.zzax.create(valueAt);
                zza2.zzaj.onNewItem(keyAt, valueAt);
                this.zzay.append(keyAt, zza2);
            }
        }
        SparseArray detectedItems2 = detections.getDetectedItems();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i2 = 0; i2 < this.zzay.size(); i2++) {
            int keyAt2 = this.zzay.keyAt(i2);
            if (detectedItems2.get(keyAt2) == null) {
                zza zza3 = (zza) this.zzay.valueAt(i2);
                zza3.zzan = zza3.zzan + 1;
                if (zza3.zzan >= this.zzak) {
                    zza3.zzaj.onDone();
                    hashSet.add(Integer.valueOf(keyAt2));
                } else {
                    zza3.zzaj.onMissing(detections);
                }
            }
        }
        for (Integer intValue : hashSet) {
            this.zzay.delete(intValue.intValue());
        }
        SparseArray detectedItems3 = detections.getDetectedItems();
        for (int i3 = 0; i3 < detectedItems3.size(); i3++) {
            int keyAt3 = detectedItems3.keyAt(i3);
            Object valueAt2 = detectedItems3.valueAt(i3);
            zza zza4 = (zza) this.zzay.get(keyAt3);
            zza4.zzan = 0;
            zza4.zzaj.onUpdate(detections, valueAt2);
        }
    }

    private MultiProcessor() {
        this.zzay = new SparseArray<>();
        this.zzak = 3;
    }
}
