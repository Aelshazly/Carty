package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzim {
    private static final zzik zzzr = zzhn();
    private static final zzik zzzs = new zzij();

    static zzik zzhl() {
        return zzzr;
    }

    static zzik zzhm() {
        return zzzs;
    }

    private static zzik zzhn() {
        try {
            return (zzik) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
