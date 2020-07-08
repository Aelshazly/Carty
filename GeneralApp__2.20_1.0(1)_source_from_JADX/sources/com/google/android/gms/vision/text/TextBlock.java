package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzac;
import com.google.android.gms.internal.vision.zzw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-vision@@20.0.0 */
public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzac[] zzeb;
    private List<Line> zzec;
    private String zzed;
    private Rect zzee;

    TextBlock(SparseArray<zzac> sparseArray) {
        this.zzeb = new zzac[sparseArray.size()];
        int i = 0;
        while (true) {
            zzac[] zzacArr = this.zzeb;
            if (i < zzacArr.length) {
                zzacArr[i] = (zzac) sparseArray.valueAt(i);
                i++;
            } else {
                return;
            }
        }
    }

    public String getLanguage() {
        zzac[] zzacArr;
        int i;
        String str = this.zzed;
        if (str != null) {
            return str;
        }
        HashMap hashMap = new HashMap();
        for (zzac zzac : this.zzeb) {
            if (hashMap.containsKey(zzac.zzed)) {
                i = ((Integer) hashMap.get(zzac.zzed)).intValue();
            } else {
                i = 0;
            }
            hashMap.put(zzac.zzed, Integer.valueOf(i + 1));
        }
        this.zzed = (String) ((Entry) Collections.max(hashMap.entrySet(), new zza(this))).getKey();
        String str2 = this.zzed;
        if (str2 == null || str2.isEmpty()) {
            this.zzed = "und";
        }
        return this.zzed;
    }

    public String getValue() {
        zzac[] zzacArr = this.zzeb;
        if (zzacArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(zzacArr[0].zzem);
        for (int i = 1; i < this.zzeb.length; i++) {
            sb.append("\n");
            sb.append(this.zzeb[i].zzem);
        }
        return sb.toString();
    }

    public Point[] getCornerPoints() {
        zzac[] zzacArr;
        TextBlock textBlock = this;
        if (textBlock.cornerPoints == null) {
            char c = 0;
            if (textBlock.zzeb.length == 0) {
                textBlock.cornerPoints = new Point[0];
            } else {
                int i = Integer.MAX_VALUE;
                int i2 = 0;
                int i3 = Integer.MAX_VALUE;
                int i4 = Integer.MIN_VALUE;
                int i5 = Integer.MIN_VALUE;
                while (true) {
                    zzacArr = textBlock.zzeb;
                    if (i2 >= zzacArr.length) {
                        break;
                    }
                    zzw zzw = zzacArr[i2].zzej;
                    zzw zzw2 = textBlock.zzeb[c].zzej;
                    int i6 = -zzw2.left;
                    int i7 = -zzw2.top;
                    double sin = Math.sin(Math.toRadians((double) zzw2.zzeh));
                    int i8 = i6;
                    double cos = Math.cos(Math.toRadians((double) zzw2.zzeh));
                    Point[] pointArr = new Point[4];
                    pointArr[c] = new Point(zzw.left, zzw.top);
                    pointArr[c].offset(i8, i7);
                    int i9 = i;
                    int i10 = (int) ((((double) pointArr[c].x) * cos) + (((double) pointArr[c].y) * sin));
                    int i11 = (int) ((((double) (-pointArr[0].x)) * sin) + (((double) pointArr[0].y) * cos));
                    pointArr[0].x = i10;
                    pointArr[0].y = i11;
                    pointArr[1] = new Point(zzw.width + i10, i11);
                    pointArr[2] = new Point(zzw.width + i10, zzw.height + i11);
                    pointArr[3] = new Point(i10, i11 + zzw.height);
                    i = i9;
                    for (int i12 = 0; i12 < 4; i12++) {
                        Point point = pointArr[i12];
                        i = Math.min(i, point.x);
                        i4 = Math.max(i4, point.x);
                        i3 = Math.min(i3, point.y);
                        i5 = Math.max(i5, point.y);
                    }
                    i2++;
                    c = 0;
                    textBlock = this;
                }
                int i13 = i;
                zzw zzw3 = zzacArr[0].zzej;
                int i14 = zzw3.left;
                int i15 = zzw3.top;
                double sin2 = Math.sin(Math.toRadians((double) zzw3.zzeh));
                double cos2 = Math.cos(Math.toRadians((double) zzw3.zzeh));
                int i16 = i13;
                Point[] pointArr2 = {new Point(i16, i3), new Point(i4, i3), new Point(i4, i5), new Point(i16, i5)};
                for (int i17 = 0; i17 < 4; i17++) {
                    int i18 = (int) ((((double) pointArr2[i17].x) * sin2) + (((double) pointArr2[i17].y) * cos2));
                    pointArr2[i17].x = (int) ((((double) pointArr2[i17].x) * cos2) - (((double) pointArr2[i17].y) * sin2));
                    pointArr2[i17].y = i18;
                    pointArr2[i17].offset(i14, i15);
                }
                textBlock = this;
                textBlock.cornerPoints = pointArr2;
            }
        }
        return textBlock.cornerPoints;
    }

    public List<? extends Text> getComponents() {
        zzac[] zzacArr = this.zzeb;
        if (zzacArr.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzec == null) {
            this.zzec = new ArrayList(zzacArr.length);
            for (zzac line : this.zzeb) {
                this.zzec.add(new Line(line));
            }
        }
        return this.zzec;
    }

    public Rect getBoundingBox() {
        if (this.zzee == null) {
            this.zzee = zzc.zza((Text) this);
        }
        return this.zzee;
    }
}
