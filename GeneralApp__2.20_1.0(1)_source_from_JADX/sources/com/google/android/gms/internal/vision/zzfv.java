package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzfv extends zzft {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzsq;
    private int zzsr;
    private int zzss;
    private int zzst;
    private int zzsu;

    private zzfv(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzsu = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzss = this.pos;
        this.zzsq = z;
    }

    public final int zzex() throws IOException {
        if (zzdt()) {
            this.zzst = 0;
            return 0;
        }
        this.zzst = zzfa();
        int i = this.zzst;
        if ((i >>> 3) != 0) {
            return i;
        }
        throw zzhc.zzgp();
    }

    public final void zzar(int i) throws zzhc {
        if (this.zzst != i) {
            throw zzhc.zzgq();
        }
    }

    public final boolean zzas(int i) throws IOException {
        int zzex;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.limit - this.pos >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.buffer;
                    int i4 = this.pos;
                    this.pos = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzhc.zzgo();
            }
            while (i3 < 10) {
                if (zzff() < 0) {
                    i3++;
                }
            }
            throw zzhc.zzgo();
            return true;
        } else if (i2 == 1) {
            zzaw(8);
            return true;
        } else if (i2 == 2) {
            zzaw(zzfa());
            return true;
        } else if (i2 == 3) {
            do {
                zzex = zzex();
                if (zzex == 0) {
                    break;
                }
            } while (zzas(zzex));
            zzar(((i >>> 3) << 3) | 4);
            return true;
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzaw(4);
                return true;
            }
            throw zzhc.zzgr();
        }
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzfd());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzfc());
    }

    public final long zzdw() throws IOException {
        return zzfb();
    }

    public final long zzdx() throws IOException {
        return zzfb();
    }

    public final int zzdy() throws IOException {
        return zzfa();
    }

    public final long zzdz() throws IOException {
        return zzfd();
    }

    public final int zzea() throws IOException {
        return zzfc();
    }

    public final boolean zzeb() throws IOException {
        return zzfb() != 0;
    }

    public final String readString() throws IOException {
        int zzfa = zzfa();
        if (zzfa > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzfa <= i - i2) {
                String str = new String(this.buffer, i2, zzfa, zzgt.UTF_8);
                this.pos += zzfa;
                return str;
            }
        }
        if (zzfa == 0) {
            return "";
        }
        if (zzfa < 0) {
            throw zzhc.zzgn();
        }
        throw zzhc.zzgm();
    }

    public final String zzec() throws IOException {
        int zzfa = zzfa();
        if (zzfa > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzfa <= i - i2) {
                String zzh = zzjs.zzh(this.buffer, i2, zzfa);
                this.pos += zzfa;
                return zzh;
            }
        }
        if (zzfa == 0) {
            return "";
        }
        if (zzfa <= 0) {
            throw zzhc.zzgn();
        }
        throw zzhc.zzgm();
    }

    public final zzfh zzed() throws IOException {
        byte[] bArr;
        int zzfa = zzfa();
        if (zzfa > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzfa <= i - i2) {
                zzfh zza = zzfh.zza(this.buffer, i2, zzfa);
                this.pos += zzfa;
                return zza;
            }
        }
        if (zzfa == 0) {
            return zzfh.zzsd;
        }
        if (zzfa > 0) {
            int i3 = this.limit;
            int i4 = this.pos;
            if (zzfa <= i3 - i4) {
                this.pos = zzfa + i4;
                bArr = Arrays.copyOfRange(this.buffer, i4, this.pos);
                return zzfh.zzd(bArr);
            }
        }
        if (zzfa > 0) {
            throw zzhc.zzgm();
        } else if (zzfa == 0) {
            bArr = zzgt.zzxi;
            return zzfh.zzd(bArr);
        } else {
            throw zzhc.zzgn();
        }
    }

    public final int zzee() throws IOException {
        return zzfa();
    }

    public final int zzef() throws IOException {
        return zzfa();
    }

    public final int zzeg() throws IOException {
        return zzfc();
    }

    public final long zzeh() throws IOException {
        return zzfd();
    }

    public final int zzei() throws IOException {
        return zzav(zzfa());
    }

    public final long zzej() throws IOException {
        return zzr(zzfb());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        if (r2[r3] >= 0) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzfa() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.limit
            if (r1 == r0) goto L_0x006d
            byte[] r2 = r5.buffer
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0011
            r5.pos = r3
            return r0
        L_0x0011:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x006d
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0022
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x006a
        L_0x0022:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x002f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            r1 = r3
            goto L_0x006a
        L_0x002f:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x003d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x006a
        L_0x003d:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x0069
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x006a
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x0069
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x006a
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x0069
            int r1 = r3 + 1
            byte r2 = r2[r3]
            if (r2 < 0) goto L_0x006d
            goto L_0x006a
        L_0x0069:
            r1 = r3
        L_0x006a:
            r5.pos = r1
            return r0
        L_0x006d:
            long r0 = r5.zzey()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzfv.zzfa():int");
    }

    private final long zzfb() throws IOException {
        long j;
        int i = this.pos;
        int i2 = this.limit;
        if (i2 != i) {
            byte[] bArr = this.buffer;
            int i3 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                this.pos = i3;
                return (long) b;
            } else if (i2 - i3 >= 9) {
                int i4 = i3 + 1;
                byte b2 = b ^ (bArr[i3] << 7);
                if (b2 < 0) {
                    j = (long) (b2 ^ ByteCompanionObject.MIN_VALUE);
                } else {
                    int i5 = i4 + 1;
                    byte b3 = b2 ^ (bArr[i4] << 14);
                    if (b3 >= 0) {
                        i4 = i5;
                        j = (long) (b3 ^ ByteCompanionObject.MIN_VALUE);
                    } else {
                        i4 = i5 + 1;
                        byte b4 = b3 ^ (bArr[i5] << 21);
                        if (b4 < 0) {
                            j = (long) (b4 ^ ByteCompanionObject.MIN_VALUE);
                        } else {
                            long j2 = (long) b4;
                            int i6 = i4 + 1;
                            long j3 = j2 ^ (((long) bArr[i4]) << 28);
                            if (j3 >= 0) {
                                j = j3 ^ 266354560;
                                i4 = i6;
                            } else {
                                i4 = i6 + 1;
                                long j4 = j3 ^ (((long) bArr[i6]) << 35);
                                if (j4 < 0) {
                                    j = j4 ^ -34093383808L;
                                } else {
                                    int i7 = i4 + 1;
                                    long j5 = j4 ^ (((long) bArr[i4]) << 42);
                                    if (j5 >= 0) {
                                        j = j5 ^ 4363953127296L;
                                        i4 = i7;
                                    } else {
                                        i4 = i7 + 1;
                                        long j6 = j5 ^ (((long) bArr[i7]) << 49);
                                        if (j6 < 0) {
                                            j = j6 ^ -558586000294016L;
                                        } else {
                                            int i8 = i4 + 1;
                                            long j7 = (j6 ^ (((long) bArr[i4]) << 56)) ^ 71499008037633920L;
                                            if (j7 < 0) {
                                                i4 = i8 + 1;
                                                if (((long) bArr[i8]) >= 0) {
                                                    j = j7;
                                                }
                                            } else {
                                                i4 = i8;
                                                j = j7;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.pos = i4;
                return j;
            }
        }
        return zzey();
    }

    /* access modifiers changed from: 0000 */
    public final long zzey() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzff = zzff();
            j |= ((long) (zzff & ByteCompanionObject.MAX_VALUE)) << i;
            if ((zzff & ByteCompanionObject.MIN_VALUE) == 0) {
                return j;
            }
        }
        throw zzhc.zzgo();
    }

    private final int zzfc() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 4) {
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
        }
        throw zzhc.zzgm();
    }

    private final long zzfd() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 8) {
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }
        throw zzhc.zzgm();
    }

    public final int zzat(int i) throws zzhc {
        if (i >= 0) {
            int zzez = i + zzez();
            int i2 = this.zzsu;
            if (zzez <= i2) {
                this.zzsu = zzez;
                zzfe();
                return i2;
            }
            throw zzhc.zzgm();
        }
        throw zzhc.zzgn();
    }

    private final void zzfe() {
        this.limit += this.zzsr;
        int i = this.limit;
        int i2 = i - this.zzss;
        int i3 = this.zzsu;
        if (i2 > i3) {
            this.zzsr = i2 - i3;
            this.limit = i - this.zzsr;
            return;
        }
        this.zzsr = 0;
    }

    public final void zzau(int i) {
        this.zzsu = i;
        zzfe();
    }

    public final boolean zzdt() throws IOException {
        return this.pos == this.limit;
    }

    public final int zzez() {
        return this.pos - this.zzss;
    }

    private final byte zzff() throws IOException {
        int i = this.pos;
        if (i != this.limit) {
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzhc.zzgm();
    }

    private final void zzaw(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.limit;
            int i3 = this.pos;
            if (i <= i2 - i3) {
                this.pos = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzhc.zzgn();
        }
        throw zzhc.zzgm();
    }
}
