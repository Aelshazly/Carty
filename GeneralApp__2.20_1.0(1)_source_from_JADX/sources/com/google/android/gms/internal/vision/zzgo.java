package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzgo extends zzex<Float> implements zzgz<Float>, zzio, RandomAccess {
    private static final zzgo zzvw;
    private int size;
    private float[] zzvx;

    zzgo() {
        this(new float[10], 0);
    }

    private zzgo(float[] fArr, int i) {
        this.zzvx = fArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzdq();
        if (i2 >= i) {
            float[] fArr = this.zzvx;
            System.arraycopy(fArr, i2, fArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgo)) {
            return super.equals(obj);
        }
        zzgo zzgo = (zzgo) obj;
        if (this.size != zzgo.size) {
            return false;
        }
        float[] fArr = zzgo.zzvx;
        for (int i = 0; i < this.size; i++) {
            if (Float.floatToIntBits(this.zzvx[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzvx[i2]);
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float floatValue = ((Float) obj).floatValue();
        int size2 = size();
        for (int i = 0; i < size2; i++) {
            if (this.zzvx[i] == floatValue) {
                return i;
            }
        }
        return -1;
    }

    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int size() {
        return this.size;
    }

    public final void zzu(float f) {
        zzdq();
        int i = this.size;
        float[] fArr = this.zzvx;
        if (i == fArr.length) {
            float[] fArr2 = new float[(((i * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            this.zzvx = fArr2;
        }
        float[] fArr3 = this.zzvx;
        int i2 = this.size;
        this.size = i2 + 1;
        fArr3[i2] = f;
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        zzdq();
        zzgt.checkNotNull(collection);
        if (!(collection instanceof zzgo)) {
            return super.addAll(collection);
        }
        zzgo zzgo = (zzgo) collection;
        int i = zzgo.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            float[] fArr = this.zzvx;
            if (i3 > fArr.length) {
                this.zzvx = Arrays.copyOf(fArr, i3);
            }
            System.arraycopy(zzgo.zzvx, 0, this.zzvx, this.size, zzgo.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzdq();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzvx[i]))) {
                float[] fArr = this.zzvx;
                System.arraycopy(fArr, i + 1, fArr, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzaf(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzag(i));
        }
    }

    private final String zzag(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zzdq();
        zzaf(i);
        float[] fArr = this.zzvx;
        float f = fArr[i];
        fArr[i] = floatValue;
        return Float.valueOf(f);
    }

    public final /* synthetic */ Object remove(int i) {
        zzdq();
        zzaf(i);
        float[] fArr = this.zzvx;
        float f = fArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zzdq();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                float[] fArr = this.zzvx;
                if (i2 < fArr.length) {
                    System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
                } else {
                    float[] fArr2 = new float[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(fArr, 0, fArr2, 0, i);
                    System.arraycopy(this.zzvx, i, fArr2, i + 1, this.size - i);
                    this.zzvx = fArr2;
                }
                this.zzvx[i] = floatValue;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzag(i));
    }

    public final /* synthetic */ boolean add(Object obj) {
        zzu(((Float) obj).floatValue());
        return true;
    }

    public final /* synthetic */ zzgz zzah(int i) {
        if (i >= this.size) {
            return new zzgo(Arrays.copyOf(this.zzvx, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzaf(i);
        return Float.valueOf(this.zzvx[i]);
    }

    static {
        zzgo zzgo = new zzgo(new float[0], 0);
        zzvw = zzgo;
        zzgo.zzdp();
    }
}
