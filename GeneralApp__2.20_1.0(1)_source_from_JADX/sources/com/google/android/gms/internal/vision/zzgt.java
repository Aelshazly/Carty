package com.google.android.gms.internal.vision;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzgt {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final byte[] zzxi;
    private static final ByteBuffer zzxj;
    private static final zzft zzxk;

    static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean zzg(byte[] bArr) {
        return zzjs.zzg(bArr);
    }

    public static String zzh(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int zzab(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int zzm(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int zza = zza(length, bArr, 0, length);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }

    static boolean zzf(zzic zzic) {
        if (zzic instanceof zzev) {
            zzev zzev = (zzev) zzic;
        }
        return false;
    }

    static Object zzb(Object obj, Object obj2) {
        return ((zzic) obj).zzgi().zza((zzic) obj2).zzgb();
    }

    static {
        byte[] bArr = new byte[0];
        zzxi = bArr;
        zzxj = ByteBuffer.wrap(bArr);
        byte[] bArr2 = zzxi;
        zzxk = zzft.zza(bArr2, 0, bArr2.length, false);
    }
}
