package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzgu extends zzex<Integer> implements zzgz<Integer>, zzio, RandomAccess {
    private static final zzgu zzxl;
    private int size;
    private int[] zzxm;

    public static zzgu zzgl() {
        return zzxl;
    }

    zzgu() {
        this(new int[10], 0);
    }

    private zzgu(int[] iArr, int i) {
        this.zzxm = iArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzdq();
        if (i2 >= i) {
            int[] iArr = this.zzxm;
            System.arraycopy(iArr, i2, iArr, i, this.size - i2);
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
        if (!(obj instanceof zzgu)) {
            return super.equals(obj);
        }
        zzgu zzgu = (zzgu) obj;
        if (this.size != zzgu.size) {
            return false;
        }
        int[] iArr = zzgu.zzxm;
        for (int i = 0; i < this.size; i++) {
            if (this.zzxm[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzxm[i2];
        }
        return i;
    }

    public final int getInt(int i) {
        zzaf(i);
        return this.zzxm[i];
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue();
        int size2 = size();
        for (int i = 0; i < size2; i++) {
            if (this.zzxm[i] == intValue) {
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

    public final void zzbm(int i) {
        zzdq();
        int i2 = this.size;
        int[] iArr = this.zzxm;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[(((i2 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.zzxm = iArr2;
        }
        int[] iArr3 = this.zzxm;
        int i3 = this.size;
        this.size = i3 + 1;
        iArr3[i3] = i;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzdq();
        zzgt.checkNotNull(collection);
        if (!(collection instanceof zzgu)) {
            return super.addAll(collection);
        }
        zzgu zzgu = (zzgu) collection;
        int i = zzgu.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.zzxm;
            if (i3 > iArr.length) {
                this.zzxm = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(zzgu.zzxm, 0, this.zzxm, this.size, zzgu.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzdq();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzxm[i]))) {
                int[] iArr = this.zzxm;
                System.arraycopy(iArr, i + 1, iArr, i, (this.size - i) - 1);
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
        int intValue = ((Integer) obj).intValue();
        zzdq();
        zzaf(i);
        int[] iArr = this.zzxm;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ Object remove(int i) {
        zzdq();
        zzaf(i);
        int[] iArr = this.zzxm;
        int i2 = iArr[i];
        int i3 = this.size;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i3 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzdq();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                int[] iArr = this.zzxm;
                if (i2 < iArr.length) {
                    System.arraycopy(iArr, i, iArr, i + 1, i2 - i);
                } else {
                    int[] iArr2 = new int[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(iArr, 0, iArr2, 0, i);
                    System.arraycopy(this.zzxm, i, iArr2, i + 1, this.size - i);
                    this.zzxm = iArr2;
                }
                this.zzxm[i] = intValue;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzag(i));
    }

    public final /* synthetic */ boolean add(Object obj) {
        zzbm(((Integer) obj).intValue());
        return true;
    }

    public final /* synthetic */ zzgz zzah(int i) {
        if (i >= this.size) {
            return new zzgu(Arrays.copyOf(this.zzxm, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    static {
        zzgu zzgu = new zzgu(new int[0], 0);
        zzxl = zzgu;
        zzgu.zzdp();
    }
}
