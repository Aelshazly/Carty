package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzac;
import com.google.android.gms.internal.vision.zzaj;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public class Line implements Text {
    private zzac zzdz;
    private List<Element> zzea;

    Line(zzac zzac) {
        this.zzdz = zzac;
    }

    public String getLanguage() {
        return this.zzdz.zzed;
    }

    public String getValue() {
        return this.zzdz.zzem;
    }

    public Rect getBoundingBox() {
        return zzc.zza((Text) this);
    }

    public Point[] getCornerPoints() {
        return zzc.zza(this.zzdz.zzej);
    }

    public List<? extends Text> getComponents() {
        if (this.zzdz.zzei.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzea == null) {
            this.zzea = new ArrayList(this.zzdz.zzei.length);
            for (zzaj element : this.zzdz.zzei) {
                this.zzea.add(new Element(element));
            }
        }
        return this.zzea;
    }

    public float getAngle() {
        return this.zzdz.zzej.zzeh;
    }

    public boolean isVertical() {
        return this.zzdz.zzeo;
    }
}
