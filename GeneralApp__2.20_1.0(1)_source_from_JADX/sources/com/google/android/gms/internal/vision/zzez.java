package com.google.android.gms.internal.vision;

import java.io.IOException;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzez {
    static int zza(byte[] bArr, int i, zzfb zzfb) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza((int) b, bArr, i2, zzfb);
        }
        zzfb.zzru = b;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzfb zzfb) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzfb.zzru = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & ByteCompanionObject.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzfb.zzru = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & ByteCompanionObject.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzfb.zzru = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & ByteCompanionObject.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzfb.zzru = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & ByteCompanionObject.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzfb.zzru = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzb(byte[] bArr, int i, zzfb zzfb) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzfb.zzrv = j;
            return i2;
        }
        long j2 = j & 127;
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j3 = j2 | (((long) (b & ByteCompanionObject.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j3 |= ((long) (b2 & ByteCompanionObject.MAX_VALUE)) << i4;
            int i6 = i5;
            b = b2;
            i3 = i6;
        }
        zzfb.zzrv = j3;
        return i3;
    }

    static int zza(byte[] bArr, int i) {
        return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
    }

    static long zzb(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double zzc(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzb(bArr, i));
    }

    static float zzd(byte[] bArr, int i) {
        return Float.intBitsToFloat(zza(bArr, i));
    }

    static int zzc(byte[] bArr, int i, zzfb zzfb) throws zzhc {
        int zza = zza(bArr, i, zzfb);
        int i2 = zzfb.zzru;
        if (i2 < 0) {
            throw zzhc.zzgn();
        } else if (i2 == 0) {
            zzfb.zzrw = "";
            return zza;
        } else {
            zzfb.zzrw = new String(bArr, zza, i2, zzgt.UTF_8);
            return zza + i2;
        }
    }

    static int zzd(byte[] bArr, int i, zzfb zzfb) throws zzhc {
        int zza = zza(bArr, i, zzfb);
        int i2 = zzfb.zzru;
        if (i2 < 0) {
            throw zzhc.zzgn();
        } else if (i2 == 0) {
            zzfb.zzrw = "";
            return zza;
        } else {
            zzfb.zzrw = zzjs.zzh(bArr, zza, i2);
            return zza + i2;
        }
    }

    static int zze(byte[] bArr, int i, zzfb zzfb) throws zzhc {
        int zza = zza(bArr, i, zzfb);
        int i2 = zzfb.zzru;
        if (i2 < 0) {
            throw zzhc.zzgn();
        } else if (i2 > bArr.length - zza) {
            throw zzhc.zzgm();
        } else if (i2 == 0) {
            zzfb.zzrw = zzfh.zzsd;
            return zza;
        } else {
            zzfb.zzrw = zzfh.zza(bArr, zza, i2);
            return zza + i2;
        }
    }

    static int zza(zzir zzir, byte[] bArr, int i, int i2, zzfb zzfb) throws IOException {
        int i3;
        int i4;
        int i5 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            i4 = zza((int) b, bArr, i5, zzfb);
            i3 = zzfb.zzru;
        } else {
            i4 = i5;
            i3 = b;
        }
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzhc.zzgm();
        }
        Object newInstance = zzir.newInstance();
        int i6 = i3 + i4;
        zzir.zza(newInstance, bArr, i4, i6, zzfb);
        zzir.zzh(newInstance);
        zzfb.zzrw = newInstance;
        return i6;
    }

    static int zza(zzir zzir, byte[] bArr, int i, int i2, int i3, zzfb zzfb) throws IOException {
        zzig zzig = (zzig) zzir;
        Object newInstance = zzig.newInstance();
        int zza = zzig.zza(newInstance, bArr, i, i2, i3, zzfb);
        zzig.zzh(newInstance);
        zzfb.zzrw = newInstance;
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzgz<?> zzgz, zzfb zzfb) {
        zzgu zzgu = (zzgu) zzgz;
        int zza = zza(bArr, i2, zzfb);
        zzgu.zzbm(zzfb.zzru);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzfb);
            if (i != zzfb.zzru) {
                break;
            }
            zza = zza(bArr, zza2, zzfb);
            zzgu.zzbm(zzfb.zzru);
        }
        return zza;
    }

    static int zza(byte[] bArr, int i, zzgz<?> zzgz, zzfb zzfb) throws IOException {
        zzgu zzgu = (zzgu) zzgz;
        int zza = zza(bArr, i, zzfb);
        int i2 = zzfb.zzru + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzfb);
            zzgu.zzbm(zzfb.zzru);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    static int zzb(byte[] bArr, int i, zzgz<?> zzgz, zzfb zzfb) throws IOException {
        zzhq zzhq = (zzhq) zzgz;
        int zza = zza(bArr, i, zzfb);
        int i2 = zzfb.zzru + zza;
        while (zza < i2) {
            zza = zzb(bArr, zza, zzfb);
            zzhq.zzac(zzfb.zzrv);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    static int zzc(byte[] bArr, int i, zzgz<?> zzgz, zzfb zzfb) throws IOException {
        zzgu zzgu = (zzgu) zzgz;
        int zza = zza(bArr, i, zzfb);
        int i2 = zzfb.zzru + zza;
        while (zza < i2) {
            zzgu.zzbm(zza(bArr, zza));
            zza += 4;
        }
        if (zza == i2) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    static int zzd(byte[] bArr, int i, zzgz<?> zzgz, zzfb zzfb) throws IOException {
        zzhq zzhq = (zzhq) zzgz;
        int zza = zza(bArr, i, zzfb);
        int i2 = zzfb.zzru + zza;
        while (zza < i2) {
            zzhq.zzac(zzb(bArr, zza));
            zza += 8;
        }
        if (zza == i2) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    static int zze(byte[] bArr, int i, zzgz<?> zzgz, zzfb zzfb) throws IOException {
        zzgo zzgo = (zzgo) zzgz;
        int zza = zza(bArr, i, zzfb);
        int i2 = zzfb.zzru + zza;
        while (zza < i2) {
            zzgo.zzu(zzd(bArr, zza));
            zza += 4;
        }
        if (zza == i2) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    static int zzf(byte[] bArr, int i, zzgz<?> zzgz, zzfb zzfb) throws IOException {
        zzgb zzgb = (zzgb) zzgz;
        int zza = zza(bArr, i, zzfb);
        int i2 = zzfb.zzru + zza;
        while (zza < i2) {
            zzgb.zzc(zzc(bArr, zza));
            zza += 8;
        }
        if (zza == i2) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    static int zzg(byte[] bArr, int i, zzgz<?> zzgz, zzfb zzfb) throws IOException {
        zzff zzff = (zzff) zzgz;
        int zza = zza(bArr, i, zzfb);
        int i2 = zzfb.zzru + zza;
        while (zza < i2) {
            zza = zzb(bArr, zza, zzfb);
            zzff.addBoolean(zzfb.zzrv != 0);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    static int zzh(byte[] bArr, int i, zzgz<?> zzgz, zzfb zzfb) throws IOException {
        zzgu zzgu = (zzgu) zzgz;
        int zza = zza(bArr, i, zzfb);
        int i2 = zzfb.zzru + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzfb);
            zzgu.zzbm(zzft.zzav(zzfb.zzru));
        }
        if (zza == i2) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    static int zzi(byte[] bArr, int i, zzgz<?> zzgz, zzfb zzfb) throws IOException {
        zzhq zzhq = (zzhq) zzgz;
        int zza = zza(bArr, i, zzfb);
        int i2 = zzfb.zzru + zza;
        while (zza < i2) {
            zza = zzb(bArr, zza, zzfb);
            zzhq.zzac(zzft.zzr(zzfb.zzrv));
        }
        if (zza == i2) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    static int zza(zzir<?> zzir, int i, byte[] bArr, int i2, int i3, zzgz<?> zzgz, zzfb zzfb) throws IOException {
        int zza = zza((zzir) zzir, bArr, i2, i3, zzfb);
        zzgz.add(zzfb.zzrw);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzfb);
            if (i != zzfb.zzru) {
                break;
            }
            zza = zza((zzir) zzir, bArr, zza2, i3, zzfb);
            zzgz.add(zzfb.zzrw);
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzjm zzjm, zzfb zzfb) throws zzhc {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzb = zzb(bArr, i2, zzfb);
                zzjm.zzb(i, Long.valueOf(zzfb.zzrv));
                return zzb;
            } else if (i4 == 1) {
                zzjm.zzb(i, Long.valueOf(zzb(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zza = zza(bArr, i2, zzfb);
                int i5 = zzfb.zzru;
                if (i5 < 0) {
                    throw zzhc.zzgn();
                } else if (i5 <= bArr.length - zza) {
                    if (i5 == 0) {
                        zzjm.zzb(i, zzfh.zzsd);
                    } else {
                        zzjm.zzb(i, zzfh.zza(bArr, zza, i5));
                    }
                    return zza + i5;
                } else {
                    throw zzhc.zzgm();
                }
            } else if (i4 == 3) {
                zzjm zzih = zzjm.zzih();
                int i6 = (i & -8) | 4;
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zza2 = zza(bArr, i2, zzfb);
                    int i8 = zzfb.zzru;
                    if (i8 == i6) {
                        i7 = i8;
                        i2 = zza2;
                        break;
                    }
                    i7 = i8;
                    i2 = zza(i8, bArr, zza2, i3, zzih, zzfb);
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzhc.zzgs();
                }
                zzjm.zzb(i, zzih);
                return i2;
            } else if (i4 == 5) {
                zzjm.zzb(i, Integer.valueOf(zza(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzhc.zzgp();
            }
        } else {
            throw zzhc.zzgp();
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzfb zzfb) throws zzhc {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                return zzb(bArr, i2, zzfb);
            }
            if (i4 == 1) {
                return i2 + 8;
            }
            if (i4 == 2) {
                return zza(bArr, i2, zzfb) + zzfb.zzru;
            }
            if (i4 == 3) {
                int i5 = (i & -8) | 4;
                int i6 = 0;
                while (i2 < i3) {
                    i2 = zza(bArr, i2, zzfb);
                    i6 = zzfb.zzru;
                    if (i6 == i5) {
                        break;
                    }
                    i2 = zza(i6, bArr, i2, i3, zzfb);
                }
                if (i2 <= i3 && i6 == i5) {
                    return i2;
                }
                throw zzhc.zzgs();
            } else if (i4 == 5) {
                return i2 + 4;
            } else {
                throw zzhc.zzgp();
            }
        } else {
            throw zzhc.zzgp();
        }
    }
}
