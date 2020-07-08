package com.google.android.gms.internal.vision;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzjp {
    private static final Logger logger = Logger.getLogger(zzjp.class.getName());
    private static final boolean zzaav = zzk(Long.TYPE);
    private static final boolean zzaaw = zzk(Integer.TYPE);
    private static final zzd zzaax;
    private static final boolean zzaay = zzin();
    private static final long zzaaz = ((long) zzi(byte[].class));
    private static final long zzaba;
    private static final long zzabb;
    private static final long zzabc;
    private static final long zzabd;
    private static final long zzabe;
    private static final long zzabf;
    private static final long zzabg;
    private static final long zzabh;
    private static final long zzabi;
    private static final long zzabj;
    private static final long zzabk = ((long) zzi(Object[].class));
    private static final long zzabl = ((long) zzj(Object[].class));
    private static final long zzabm;
    private static final int zzabn = ((int) (zzaaz & 7));
    static final boolean zzabo = (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN);
    private static final Class<?> zzrs = zzfa.zzds();
    private static final boolean zzsx = zzim();
    private static final Unsafe zzyz = zzil();

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzy(Object obj, long j) {
            if (zzjp.zzabo) {
                return zzjp.zzq(obj, j);
            }
            return zzjp.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzjp.zzabo) {
                zzjp.zza(obj, j, b);
            } else {
                zzjp.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzjp.zzabo) {
                return zzjp.zzs(obj, j);
            }
            return zzjp.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzjp.zzabo) {
                zzjp.zzb(obj, j, z);
            } else {
                zzjp.zzc(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzy(Object obj, long j) {
            return this.zzabr.getByte(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzabr.putByte(obj, j, b);
        }

        public final boolean zzm(Object obj, long j) {
            return this.zzabr.getBoolean(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzabr.putBoolean(obj, j, z);
        }

        public final float zzn(Object obj, long j) {
            return this.zzabr.getFloat(obj, j);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzabr.putFloat(obj, j, f);
        }

        public final double zzo(Object obj, long j) {
            return this.zzabr.getDouble(obj, j);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzabr.putDouble(obj, j, d);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzy(Object obj, long j) {
            if (zzjp.zzabo) {
                return zzjp.zzq(obj, j);
            }
            return zzjp.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzjp.zzabo) {
                zzjp.zza(obj, j, b);
            } else {
                zzjp.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzjp.zzabo) {
                return zzjp.zzs(obj, j);
            }
            return zzjp.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzjp.zzabo) {
                zzjp.zzb(obj, j, z);
            } else {
                zzjp.zzc(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
    static abstract class zzd {
        Unsafe zzabr;

        zzd(Unsafe unsafe) {
            this.zzabr = unsafe;
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);

        public final int zzk(Object obj, long j) {
            return this.zzabr.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zzabr.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zzabr.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzabr.putLong(obj, j, j2);
        }
    }

    private zzjp() {
    }

    static boolean zzij() {
        return zzsx;
    }

    static boolean zzik() {
        return zzaay;
    }

    static <T> T zzh(Class<T> cls) {
        try {
            return zzyz.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzi(Class<?> cls) {
        if (zzsx) {
            return zzaax.zzabr.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzj(Class<?> cls) {
        if (zzsx) {
            return zzaax.zzabr.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzk(Object obj, long j) {
        return zzaax.zzk(obj, j);
    }

    static void zzb(Object obj, long j, int i) {
        zzaax.zzb(obj, j, i);
    }

    static long zzl(Object obj, long j) {
        return zzaax.zzl(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzaax.zza(obj, j, j2);
    }

    static boolean zzm(Object obj, long j) {
        return zzaax.zzm(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzaax.zza(obj, j, z);
    }

    static float zzn(Object obj, long j) {
        return zzaax.zzn(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzaax.zza(obj, j, f);
    }

    static double zzo(Object obj, long j) {
        return zzaax.zzo(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzaax.zza(obj, j, d);
    }

    static Object zzp(Object obj, long j) {
        return zzaax.zzabr.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzaax.zzabr.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzaax.zzy(bArr, zzaaz + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzaax.zze(bArr, zzaaz + j, b);
    }

    static Unsafe zzil() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzjr());
        } catch (Throwable th) {
            return null;
        }
    }

    private static boolean zzim() {
        Unsafe unsafe = zzyz;
        if (unsafe == null) {
            return false;
        }
        try {
            Class cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzfa.zzdr()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzin() {
        String str = "copyMemory";
        String str2 = "getLong";
        Unsafe unsafe = zzyz;
        if (unsafe == null) {
            return false;
        }
        try {
            Class cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod(str2, new Class[]{Object.class, Long.TYPE});
            if (zzio() == null) {
                return false;
            }
            if (zzfa.zzdr()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod(str2, new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod(str, new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod(str, new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzk(Class<?> cls) {
        Class<byte[]> cls2 = byte[].class;
        if (!zzfa.zzdr()) {
            return false;
        }
        try {
            Class<?> cls3 = zzrs;
            cls3.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls3.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls3.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static Field zzio() {
        if (zzfa.zzdr()) {
            Field zzb2 = zzb(Buffer.class, "effectiveDirectAddress");
            if (zzb2 != null) {
                return zzb2;
            }
        }
        Field zzb3 = zzb(Buffer.class, "address");
        if (zzb3 == null || zzb3.getType() != Long.TYPE) {
            return null;
        }
        return zzb3;
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable th) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (zzk(obj, -4 & j) >>> ((int) (((~j) & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static byte zzr(Object obj, long j) {
        return (byte) (zzk(obj, -4 & j) >>> ((int) ((j & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = ((~((int) j)) & 3) << 3;
        int i2 = (255 & b) << i;
        zzb(obj, j2, i2 | (zzk(obj, j2) & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, z ? (byte) 1 : 0);
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, z ? (byte) 1 : 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00f5  */
    static {
        /*
            java.lang.Class<double[]> r0 = double[].class
            java.lang.Class<float[]> r1 = float[].class
            java.lang.Class<long[]> r2 = long[].class
            java.lang.Class<int[]> r3 = int[].class
            java.lang.Class<boolean[]> r4 = boolean[].class
            java.lang.Class<com.google.android.gms.internal.vision.zzjp> r5 = com.google.android.gms.internal.vision.zzjp.class
            java.lang.String r5 = r5.getName()
            java.util.logging.Logger r5 = java.util.logging.Logger.getLogger(r5)
            logger = r5
            sun.misc.Unsafe r5 = zzil()
            zzyz = r5
            java.lang.Class r5 = com.google.android.gms.internal.vision.zzfa.zzds()
            zzrs = r5
            java.lang.Class r5 = java.lang.Long.TYPE
            boolean r5 = zzk(r5)
            zzaav = r5
            java.lang.Class r5 = java.lang.Integer.TYPE
            boolean r5 = zzk(r5)
            zzaaw = r5
            sun.misc.Unsafe r5 = zzyz
            r6 = 0
            if (r5 != 0) goto L_0x0038
            goto L_0x005e
        L_0x0038:
            boolean r5 = com.google.android.gms.internal.vision.zzfa.zzdr()
            if (r5 == 0) goto L_0x0057
            boolean r5 = zzaav
            if (r5 == 0) goto L_0x004a
            com.google.android.gms.internal.vision.zzjp$zzc r6 = new com.google.android.gms.internal.vision.zzjp$zzc
            sun.misc.Unsafe r5 = zzyz
            r6.<init>(r5)
            goto L_0x005e
        L_0x004a:
            boolean r5 = zzaaw
            if (r5 == 0) goto L_0x0056
            com.google.android.gms.internal.vision.zzjp$zza r6 = new com.google.android.gms.internal.vision.zzjp$zza
            sun.misc.Unsafe r5 = zzyz
            r6.<init>(r5)
            goto L_0x005e
        L_0x0056:
            goto L_0x005e
        L_0x0057:
            com.google.android.gms.internal.vision.zzjp$zzb r6 = new com.google.android.gms.internal.vision.zzjp$zzb
            sun.misc.Unsafe r5 = zzyz
            r6.<init>(r5)
        L_0x005e:
            zzaax = r6
            boolean r5 = zzin()
            zzaay = r5
            boolean r5 = zzim()
            zzsx = r5
            java.lang.Class<byte[]> r5 = byte[].class
            int r5 = zzi(r5)
            long r5 = (long) r5
            zzaaz = r5
            int r5 = zzi(r4)
            long r5 = (long) r5
            zzaba = r5
            int r4 = zzj(r4)
            long r4 = (long) r4
            zzabb = r4
            int r4 = zzi(r3)
            long r4 = (long) r4
            zzabc = r4
            int r3 = zzj(r3)
            long r3 = (long) r3
            zzabd = r3
            int r3 = zzi(r2)
            long r3 = (long) r3
            zzabe = r3
            int r2 = zzj(r2)
            long r2 = (long) r2
            zzabf = r2
            int r2 = zzi(r1)
            long r2 = (long) r2
            zzabg = r2
            int r1 = zzj(r1)
            long r1 = (long) r1
            zzabh = r1
            int r1 = zzi(r0)
            long r1 = (long) r1
            zzabi = r1
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzabj = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzabk = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzabl = r0
            java.lang.reflect.Field r0 = zzio()
            if (r0 == 0) goto L_0x00df
            com.google.android.gms.internal.vision.zzjp$zzd r1 = zzaax
            if (r1 != 0) goto L_0x00d8
            goto L_0x00df
        L_0x00d8:
            sun.misc.Unsafe r1 = r1.zzabr
            long r0 = r1.objectFieldOffset(r0)
            goto L_0x00e1
        L_0x00df:
            r0 = -1
        L_0x00e1:
            zzabm = r0
            long r0 = zzaaz
            r2 = 7
            long r0 = r0 & r2
            int r1 = (int) r0
            zzabn = r1
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x00f5
            r0 = 1
            goto L_0x00f6
        L_0x00f5:
            r0 = 0
        L_0x00f6:
            zzabo = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjp.<clinit>():void");
    }
}
