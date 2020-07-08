package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzaj;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public class Element implements Text {
    private zzaj zzdy;

    Element(zzaj zzaj) {
        this.zzdy = zzaj;
    }

    public String getLanguage() {
        return this.zzdy.zzed;
    }

    public String getValue() {
        return this.zzdy.zzem;
    }

    public Rect getBoundingBox() {
        return zzc.zza((Text) this);
    }

    public Point[] getCornerPoints() {
        return zzc.zza(this.zzdy.zzej);
    }

    public List<? extends Text> getComponents() {
        return new ArrayList();
    }
}
