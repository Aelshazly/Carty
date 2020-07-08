package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzhq extends zzex<Long> implements zzgz<Long>, zzio, RandomAccess {
    private static final zzhq zzyp;
    private int size;
    private long[] zzyq;

    zzhq() {
        this(new long[10], 0);
    }

    private zzhq(long[] jArr, int i) {
        this.zzyq = jArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzdq();
        if (i2 >= i) {
            long[] jArr = this.zzyq;
            System.arraycopy(jArr, i2, jArr, i, this.size - i2);
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
        if (!(obj instanceof zzhq)) {
            return super.equals(obj);
        }
        zzhq zzhq = (zzhq) obj;
        if (this.size != zzhq.size) {
            return false;
        }
        long[] jArr = zzhq.zzyq;
        for (int i = 0; i < this.size; i++) {
            if (this.zzyq[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzgt.zzab(this.zzyq[i2]);
        }
        return i;
    }

    public final long getLong(int i) {
        zzaf(i);
        return this.zzyq[i];
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int size2 = size();
        for (int i = 0; i < size2; i++) {
            if (this.zzyq[i] == longValue) {
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

    public final void zzac(long j) {
        zzdq();
        int i = this.size;
        long[] jArr = this.zzyq;
        if (i == jArr.length) {
            long[] jArr2 = new long[(((i * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            this.zzyq = jArr2;
        }
        long[] jArr3 = this.zzyq;
        int i2 = this.size;
        this.size = i2 + 1;
        jArr3[i2] = j;
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zzdq();
        zzgt.checkNotNull(collection);
        if (!(collection instanceof zzhq)) {
            return super.addAll(collection);
        }
        zzhq zzhq = (zzhq) collection;
        int i = zzhq.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.zzyq;
            if (i3 > jArr.length) {
                this.zzyq = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(zzhq.zzyq, 0, this.zzyq, this.size, zzhq.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzdq();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzyq[i]))) {
                long[] jArr = this.zzyq;
                System.arraycopy(jArr, i + 1, jArr, i, (this.size - i) - 1);
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
        long longValue = ((Long) obj).longValue();
        zzdq();
        zzaf(i);
        long[] jArr = this.zzyq;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    public final /* synthetic */ Object remove(int i) {
        zzdq();
        zzaf(i);
        long[] jArr = this.zzyq;
        long j = jArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzdq();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                long[] jArr = this.zzyq;
                if (i2 < jArr.length) {
                    System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
                } else {
                    long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(jArr, 0, jArr2, 0, i);
                    System.arraycopy(this.zzyq, i, jArr2, i + 1, this.size - i);
                    this.zzyq = jArr2;
                }
                this.zzyq[i] = longValue;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzag(i));
    }

    public final /* synthetic */ boolean add(Object obj) {
        zzac(((Long) obj).longValue());
        return true;
    }

    public final /* synthetic */ zzgz zzah(int i) {
        if (i >= this.size) {
            return new zzhq(Arrays.copyOf(this.zzyq, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    static {
        zzhq zzhq = new zzhq(new long[0], 0);
        zzyp = zzhq;
        zzhq.zzdp();
    }
}
