package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zziq<E> extends zzex<E> implements RandomAccess {
    private static final zziq<Object> zzzw;
    private int size;
    private E[] zzly;

    public static <E> zziq<E> zzhr() {
        return zzzw;
    }

    zziq() {
        this(new Object[10], 0);
    }

    private zziq(E[] eArr, int i) {
        this.zzly = eArr;
        this.size = i;
    }

    public final boolean add(E e) {
        zzdq();
        int i = this.size;
        E[] eArr = this.zzly;
        if (i == eArr.length) {
            this.zzly = Arrays.copyOf(eArr, ((i * 3) / 2) + 1);
        }
        E[] eArr2 = this.zzly;
        int i2 = this.size;
        this.size = i2 + 1;
        eArr2[i2] = e;
        this.modCount++;
        return true;
    }

    public final void add(int i, E e) {
        zzdq();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                E[] eArr = this.zzly;
                if (i2 < eArr.length) {
                    System.arraycopy(eArr, i, eArr, i + 1, i2 - i);
                } else {
                    E[] eArr2 = new Object[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(eArr, 0, eArr2, 0, i);
                    System.arraycopy(this.zzly, i, eArr2, i + 1, this.size - i);
                    this.zzly = eArr2;
                }
                this.zzly[i] = e;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzag(i));
    }

    public final E get(int i) {
        zzaf(i);
        return this.zzly[i];
    }

    public final E remove(int i) {
        zzdq();
        zzaf(i);
        E[] eArr = this.zzly;
        E e = eArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(eArr, i + 1, eArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return e;
    }

    public final E set(int i, E e) {
        zzdq();
        zzaf(i);
        E[] eArr = this.zzly;
        E e2 = eArr[i];
        eArr[i] = e;
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.size;
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

    public final /* synthetic */ zzgz zzah(int i) {
        if (i >= this.size) {
            return new zziq(Arrays.copyOf(this.zzly, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    static {
        zziq<Object> zziq = new zziq<>(new Object[0], 0);
        zzzw = zziq;
        zziq.zzdp();
    }
}
