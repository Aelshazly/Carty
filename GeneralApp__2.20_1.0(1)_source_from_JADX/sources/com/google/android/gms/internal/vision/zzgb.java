package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzgb extends zzex<Double> implements zzgz<Double>, zzio, RandomAccess {
    private static final zzgb zzsz;
    private int size;
    private double[] zzta;

    zzgb() {
        this(new double[10], 0);
    }

    private zzgb(double[] dArr, int i) {
        this.zzta = dArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzdq();
        if (i2 >= i) {
            double[] dArr = this.zzta;
            System.arraycopy(dArr, i2, dArr, i, this.size - i2);
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
        if (!(obj instanceof zzgb)) {
            return super.equals(obj);
        }
        zzgb zzgb = (zzgb) obj;
        if (this.size != zzgb.size) {
            return false;
        }
        double[] dArr = zzgb.zzta;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzta[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzgt.zzab(Double.doubleToLongBits(this.zzta[i2]));
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double doubleValue = ((Double) obj).doubleValue();
        int size2 = size();
        for (int i = 0; i < size2; i++) {
            if (this.zzta[i] == doubleValue) {
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

    public final void zzc(double d) {
        zzdq();
        int i = this.size;
        double[] dArr = this.zzta;
        if (i == dArr.length) {
            double[] dArr2 = new double[(((i * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            this.zzta = dArr2;
        }
        double[] dArr3 = this.zzta;
        int i2 = this.size;
        this.size = i2 + 1;
        dArr3[i2] = d;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzdq();
        zzgt.checkNotNull(collection);
        if (!(collection instanceof zzgb)) {
            return super.addAll(collection);
        }
        zzgb zzgb = (zzgb) collection;
        int i = zzgb.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.zzta;
            if (i3 > dArr.length) {
                this.zzta = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(zzgb.zzta, 0, this.zzta, this.size, zzgb.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzdq();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzta[i]))) {
                double[] dArr = this.zzta;
                System.arraycopy(dArr, i + 1, dArr, i, (this.size - i) - 1);
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
        double doubleValue = ((Double) obj).doubleValue();
        zzdq();
        zzaf(i);
        double[] dArr = this.zzta;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ Object remove(int i) {
        zzdq();
        zzaf(i);
        double[] dArr = this.zzta;
        double d = dArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzdq();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                double[] dArr = this.zzta;
                if (i2 < dArr.length) {
                    System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
                } else {
                    double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(dArr, 0, dArr2, 0, i);
                    System.arraycopy(this.zzta, i, dArr2, i + 1, this.size - i);
                    this.zzta = dArr2;
                }
                this.zzta[i] = doubleValue;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzag(i));
    }

    public final /* synthetic */ boolean add(Object obj) {
        zzc(((Double) obj).doubleValue());
        return true;
    }

    public final /* synthetic */ zzgz zzah(int i) {
        if (i >= this.size) {
            return new zzgb(Arrays.copyOf(this.zzta, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzaf(i);
        return Double.valueOf(this.zzta[i]);
    }

    static {
        zzgb zzgb = new zzgb(new double[0], 0);
        zzsz = zzgb;
        zzgb.zzdp();
    }
}
