package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public enum zzhe {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, Integer.valueOf(0)),
    LONG(Long.TYPE, Long.class, Long.valueOf(0)),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.valueOf(false)),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzfh.class, zzfh.class, zzfh.zzsd),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);
    
    private final Class<?> zzxy;
    private final Class<?> zzxz;
    private final Object zzya;

    private zzhe(Class<?> cls, Class<?> cls2, Object obj) {
        this.zzxy = cls;
        this.zzxz = cls2;
        this.zzya = obj;
    }

    public final Class<?> zzgv() {
        return this.zzxz;
    }
}
