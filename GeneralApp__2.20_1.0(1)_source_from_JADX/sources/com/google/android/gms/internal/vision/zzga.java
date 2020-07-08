package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public abstract class zzga extends zzfi {
    private static final Logger logger = Logger.getLogger(zzga.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzsx = zzjp.zzij();
    zzgc zzsy = this;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    public static class zza extends IOException {
        zza() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zza(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        zza(String str, Throwable th) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String valueOf2 = String.valueOf(str);
            super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), th);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    static class zzb extends zzga {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zzb(byte[] bArr, int i, int i2) {
            super();
            if (bArr != null) {
                int i3 = i2 + 0;
                if ((i2 | 0 | (bArr.length - i3)) >= 0) {
                    this.buffer = bArr;
                    this.offset = 0;
                    this.position = 0;
                    this.limit = i3;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(0), Integer.valueOf(i2)}));
            }
            throw new NullPointerException("buffer");
        }

        public final void writeTag(int i, int i2) throws IOException {
            zzay((i << 3) | i2);
        }

        public final void zzg(int i, int i2) throws IOException {
            writeTag(i, 0);
            zzax(i2);
        }

        public final void zzh(int i, int i2) throws IOException {
            writeTag(i, 0);
            zzay(i2);
        }

        public final void zzj(int i, int i2) throws IOException {
            writeTag(i, 5);
            zzba(i2);
        }

        public final void zza(int i, long j) throws IOException {
            writeTag(i, 0);
            zzs(j);
        }

        public final void zzc(int i, long j) throws IOException {
            writeTag(i, 1);
            zzu(j);
        }

        public final void zza(int i, boolean z) throws IOException {
            writeTag(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zza(int i, String str) throws IOException {
            writeTag(i, 2);
            zzx(str);
        }

        public final void zza(int i, zzfh zzfh) throws IOException {
            writeTag(i, 2);
            zza(zzfh);
        }

        public final void zza(zzfh zzfh) throws IOException {
            zzay(zzfh.size());
            zzfh.zza((zzfi) this);
        }

        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzay(i2);
            write(bArr, 0, i2);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(int i, zzic zzic, zzir zzir) throws IOException {
            writeTag(i, 2);
            zzet zzet = (zzet) zzic;
            int zzdl = zzet.zzdl();
            if (zzdl == -1) {
                zzdl = zzir.zzs(zzet);
                zzet.zzae(zzdl);
            }
            zzay(zzdl);
            zzir.zza(zzic, this.zzsy);
        }

        public final void zza(int i, zzic zzic) throws IOException {
            writeTag(1, 3);
            zzh(2, i);
            writeTag(3, 2);
            zzb(zzic);
            writeTag(1, 4);
        }

        public final void zzb(int i, zzfh zzfh) throws IOException {
            writeTag(1, 3);
            zzh(2, i);
            zza(3, zzfh);
            writeTag(1, 4);
        }

        public final void zzb(zzic zzic) throws IOException {
            zzay(zzic.zzgf());
            zzic.zzb(this);
        }

        public final void zzc(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzax(int i) throws IOException {
            if (i >= 0) {
                zzay(i);
            } else {
                zzs((long) i);
            }
        }

        public final void zzay(int i) throws IOException {
            if (!zzga.zzsx || zzfa.zzdr() || zzfg() < 5) {
                while ((i & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr2[i3] = (byte) i;
                } catch (IndexOutOfBoundsException e) {
                    throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            } else if ((i & -128) == 0) {
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzjp.zza(bArr3, (long) i4, (byte) i);
            } else {
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                zzjp.zza(bArr4, (long) i5, (byte) (i | 128));
                int i6 = i >>> 7;
                if ((i6 & -128) == 0) {
                    byte[] bArr5 = this.buffer;
                    int i7 = this.position;
                    this.position = i7 + 1;
                    zzjp.zza(bArr5, (long) i7, (byte) i6);
                    return;
                }
                byte[] bArr6 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                zzjp.zza(bArr6, (long) i8, (byte) (i6 | 128));
                int i9 = i6 >>> 7;
                if ((i9 & -128) == 0) {
                    byte[] bArr7 = this.buffer;
                    int i10 = this.position;
                    this.position = i10 + 1;
                    zzjp.zza(bArr7, (long) i10, (byte) i9);
                    return;
                }
                byte[] bArr8 = this.buffer;
                int i11 = this.position;
                this.position = i11 + 1;
                zzjp.zza(bArr8, (long) i11, (byte) (i9 | 128));
                int i12 = i9 >>> 7;
                if ((i12 & -128) == 0) {
                    byte[] bArr9 = this.buffer;
                    int i13 = this.position;
                    this.position = i13 + 1;
                    zzjp.zza(bArr9, (long) i13, (byte) i12);
                    return;
                }
                byte[] bArr10 = this.buffer;
                int i14 = this.position;
                this.position = i14 + 1;
                zzjp.zza(bArr10, (long) i14, (byte) (i12 | 128));
                int i15 = i12 >>> 7;
                byte[] bArr11 = this.buffer;
                int i16 = this.position;
                this.position = i16 + 1;
                zzjp.zza(bArr11, (long) i16, (byte) i15);
            }
        }

        public final void zzba(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr2[i3] = (byte) (i >> 8);
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr3[i4] = (byte) (i >> 16);
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr4[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzs(long j) throws IOException {
            if (!zzga.zzsx || zzfg() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e) {
                    throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    zzjp.zza(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzjp.zza(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void zzu(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) j);
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr2[i2] = (byte) ((int) (j >> 8));
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) ((int) (j >> 16));
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr4[i4] = (byte) ((int) (j >> 24));
                byte[] bArr5 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr5[i5] = (byte) ((int) (j >> 32));
                byte[] bArr6 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr6[i6] = (byte) ((int) (j >> 40));
                byte[] bArr7 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr7[i7] = (byte) ((int) (j >> 48));
                byte[] bArr8 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                bArr8[i8] = (byte) ((int) (j >> 56));
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        private final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zzc(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzx(String str) throws IOException {
            int i = this.position;
            try {
                int zzbd = zzbd(str.length() * 3);
                int zzbd2 = zzbd(str.length());
                if (zzbd2 == zzbd) {
                    this.position = i + zzbd2;
                    int zza = zzjs.zza(str, this.buffer, this.position, zzfg());
                    this.position = i;
                    zzay((zza - i) - zzbd2);
                    this.position = zza;
                    return;
                }
                zzay(zzjs.zza(str));
                this.position = zzjs.zza(str, this.buffer, this.position, zzfg());
            } catch (zzjv e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zza(e2);
            }
        }

        public final int zzfg() {
            return this.limit - this.position;
        }
    }

    public static zzga zze(byte[] bArr) {
        return new zzb(bArr, 0, bArr.length);
    }

    public abstract void writeTag(int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzfh zzfh) throws IOException;

    public abstract void zza(int i, zzic zzic) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zza(int i, zzic zzic, zzir zzir) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public abstract void zza(zzfh zzfh) throws IOException;

    public abstract void zzax(int i) throws IOException;

    public abstract void zzay(int i) throws IOException;

    public abstract void zzb(int i, zzfh zzfh) throws IOException;

    public abstract void zzb(zzic zzic) throws IOException;

    public abstract void zzba(int i) throws IOException;

    public abstract void zzc(byte b) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zze(byte[] bArr, int i, int i2) throws IOException;

    public abstract int zzfg();

    public abstract void zzg(int i, int i2) throws IOException;

    public abstract void zzh(int i, int i2) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzs(long j) throws IOException;

    public abstract void zzu(long j) throws IOException;

    public abstract void zzx(String str) throws IOException;

    private zzga() {
    }

    public final void zzi(int i, int i2) throws IOException {
        zzh(i, zzbi(i2));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzaa(j));
    }

    public final void zza(int i, float f) throws IOException {
        zzj(i, Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zzaz(int i) throws IOException {
        zzay(zzbi(i));
    }

    public final void zzt(long j) throws IOException {
        zzs(zzaa(j));
    }

    public final void zzs(float f) throws IOException {
        zzba(Float.floatToRawIntBits(f));
    }

    public final void zza(double d) throws IOException {
        zzu(Double.doubleToRawLongBits(d));
    }

    public final void zzk(boolean z) throws IOException {
        zzc(z ? (byte) 1 : 0);
    }

    public static int zzk(int i, int i2) {
        return zzbb(i) + zzbc(i2);
    }

    public static int zzl(int i, int i2) {
        return zzbb(i) + zzbd(i2);
    }

    public static int zzm(int i, int i2) {
        return zzbb(i) + zzbd(zzbi(i2));
    }

    public static int zzn(int i, int i2) {
        return zzbb(i) + 4;
    }

    public static int zzo(int i, int i2) {
        return zzbb(i) + 4;
    }

    public static int zzd(int i, long j) {
        return zzbb(i) + zzw(j);
    }

    public static int zze(int i, long j) {
        return zzbb(i) + zzw(j);
    }

    public static int zzf(int i, long j) {
        return zzbb(i) + zzw(zzaa(j));
    }

    public static int zzg(int i, long j) {
        return zzbb(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzbb(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzbb(i) + 4;
    }

    public static int zzb(int i, double d) {
        return zzbb(i) + 8;
    }

    public static int zzb(int i, boolean z) {
        return zzbb(i) + 1;
    }

    public static int zzp(int i, int i2) {
        return zzbb(i) + zzbc(i2);
    }

    public static int zzb(int i, String str) {
        return zzbb(i) + zzy(str);
    }

    public static int zzc(int i, zzfh zzfh) {
        int zzbb = zzbb(i);
        int size = zzfh.size();
        return zzbb + zzbd(size) + size;
    }

    public static int zza(int i, zzhh zzhh) {
        int zzbb = zzbb(i);
        int zzgf = zzhh.zzgf();
        return zzbb + zzbd(zzgf) + zzgf;
    }

    static int zzb(int i, zzic zzic, zzir zzir) {
        return zzbb(i) + zza(zzic, zzir);
    }

    public static int zzb(int i, zzic zzic) {
        return (zzbb(1) << 1) + zzl(2, i) + zzbb(3) + zzc(zzic);
    }

    public static int zzd(int i, zzfh zzfh) {
        return (zzbb(1) << 1) + zzl(2, i) + zzc(3, zzfh);
    }

    public static int zzb(int i, zzhh zzhh) {
        return (zzbb(1) << 1) + zzl(2, i) + zza(3, zzhh);
    }

    public static int zzbb(int i) {
        return zzbd(i << 3);
    }

    public static int zzbc(int i) {
        if (i >= 0) {
            return zzbd(i);
        }
        return 10;
    }

    public static int zzbd(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((i & -268435456) == 0) {
            return 4;
        }
        return 5;
    }

    public static int zzbe(int i) {
        return zzbd(zzbi(i));
    }

    public static int zzbf(int i) {
        return 4;
    }

    public static int zzbg(int i) {
        return 4;
    }

    public static int zzv(long j) {
        return zzw(j);
    }

    public static int zzw(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        if ((j & -16384) != 0) {
            i++;
        }
        return i;
    }

    public static int zzx(long j) {
        return zzw(zzaa(j));
    }

    public static int zzy(long j) {
        return 8;
    }

    public static int zzz(long j) {
        return 8;
    }

    public static int zzt(float f) {
        return 4;
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzl(boolean z) {
        return 1;
    }

    public static int zzbh(int i) {
        return zzbc(i);
    }

    public static int zzy(String str) {
        int i;
        try {
            i = zzjs.zza(str);
        } catch (zzjv e) {
            i = str.getBytes(zzgt.UTF_8).length;
        }
        return zzbd(i) + i;
    }

    public static int zza(zzhh zzhh) {
        int zzgf = zzhh.zzgf();
        return zzbd(zzgf) + zzgf;
    }

    public static int zzb(zzfh zzfh) {
        int size = zzfh.size();
        return zzbd(size) + size;
    }

    public static int zzf(byte[] bArr) {
        int length = bArr.length;
        return zzbd(length) + length;
    }

    public static int zzc(zzic zzic) {
        int zzgf = zzic.zzgf();
        return zzbd(zzgf) + zzgf;
    }

    static int zza(zzic zzic, zzir zzir) {
        zzet zzet = (zzet) zzic;
        int zzdl = zzet.zzdl();
        if (zzdl == -1) {
            zzdl = zzir.zzs(zzet);
            zzet.zzae(zzdl);
        }
        return zzbd(zzdl) + zzdl;
    }

    private static int zzbi(int i) {
        return (i >> 31) ^ (i << 1);
    }

    private static long zzaa(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public final void zzfh() {
        if (zzfg() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(String str, zzjv zzjv) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzjv);
        byte[] bytes = str.getBytes(zzgt.UTF_8);
        try {
            zzay(bytes.length);
            zzc(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zza(e);
        } catch (zza e2) {
            throw e2;
        }
    }

    @Deprecated
    static int zzc(int i, zzic zzic, zzir zzir) {
        int zzbb = zzbb(i) << 1;
        zzet zzet = (zzet) zzic;
        int zzdl = zzet.zzdl();
        if (zzdl == -1) {
            zzdl = zzir.zzs(zzet);
            zzet.zzae(zzdl);
        }
        return zzbb + zzdl;
    }

    @Deprecated
    public static int zzd(zzic zzic) {
        return zzic.zzgf();
    }

    @Deprecated
    public static int zzbj(int i) {
        return zzbd(i);
    }
}
