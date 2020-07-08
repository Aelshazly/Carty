package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzff extends zzex<Boolean> implements zzgz<Boolean>, zzio, RandomAccess {
    private static final zzff zzry;
    private int size;
    private boolean[] zzrz;

    zzff() {
        this(new boolean[10], 0);
    }

    private zzff(boolean[] zArr, int i) {
        this.zzrz = zArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzdq();
        if (i2 >= i) {
            boolean[] zArr = this.zzrz;
            System.arraycopy(zArr, i2, zArr, i, this.size - i2);
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
        if (!(obj instanceof zzff)) {
            return super.equals(obj);
        }
        zzff zzff = (zzff) obj;
        if (this.size != zzff.size) {
            return false;
        }
        boolean[] zArr = zzff.zzrz;
        for (int i = 0; i < this.size; i++) {
            if (this.zzrz[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzgt.zzm(this.zzrz[i2]);
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int size2 = size();
        for (int i = 0; i < size2; i++) {
            if (this.zzrz[i] == booleanValue) {
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

    public final void addBoolean(boolean z) {
        zzdq();
        int i = this.size;
        boolean[] zArr = this.zzrz;
        if (i == zArr.length) {
            boolean[] zArr2 = new boolean[(((i * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            this.zzrz = zArr2;
        }
        boolean[] zArr3 = this.zzrz;
        int i2 = this.size;
        this.size = i2 + 1;
        zArr3[i2] = z;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzdq();
        zzgt.checkNotNull(collection);
        if (!(collection instanceof zzff)) {
            return super.addAll(collection);
        }
        zzff zzff = (zzff) collection;
        int i = zzff.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.zzrz;
            if (i3 > zArr.length) {
                this.zzrz = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(zzff.zzrz, 0, this.zzrz, this.size, zzff.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzdq();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzrz[i]))) {
                boolean[] zArr = this.zzrz;
                System.arraycopy(zArr, i + 1, zArr, i, (this.size - i) - 1);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzdq();
        zzaf(i);
        boolean[] zArr = this.zzrz;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ Object remove(int i) {
        zzdq();
        zzaf(i);
        boolean[] zArr = this.zzrz;
        boolean z = zArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzdq();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                boolean[] zArr = this.zzrz;
                if (i2 < zArr.length) {
                    System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
                } else {
                    boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(zArr, 0, zArr2, 0, i);
                    System.arraycopy(this.zzrz, i, zArr2, i + 1, this.size - i);
                    this.zzrz = zArr2;
                }
                this.zzrz[i] = booleanValue;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzag(i));
    }

    public final /* synthetic */ boolean add(Object obj) {
        addBoolean(((Boolean) obj).booleanValue());
        return true;
    }

    public final /* synthetic */ zzgz zzah(int i) {
        if (i >= this.size) {
            return new zzff(Arrays.copyOf(this.zzrz, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzaf(i);
        return Boolean.valueOf(this.zzrz[i]);
    }

    static {
        zzff zzff = new zzff(new boolean[0], 0);
        zzry = zzff;
        zzff.zzdp();
    }
}
