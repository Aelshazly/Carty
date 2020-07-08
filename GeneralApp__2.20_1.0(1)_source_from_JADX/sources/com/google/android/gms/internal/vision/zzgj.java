package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzgj {
    private static final zzgf<?> zztl = new zzgh();
    private static final zzgf<?> zztm = zzfp();

    private static zzgf<?> zzfp() {
        try {
            return (zzgf) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    static zzgf<?> zzfq() {
        return zztl;
    }

    static zzgf<?> zzfr() {
        zzgf<?> zzgf = zztm;
        if (zzgf != null) {
            return zzgf;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
