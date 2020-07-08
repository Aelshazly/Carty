package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzhx {
    private static final zzhv zzyw = zzhh();
    private static final zzhv zzyx = new zzhy();

    static zzhv zzhf() {
        return zzyw;
    }

    static zzhv zzhg() {
        return zzyx;
    }

    private static zzhv zzhh() {
        try {
            return (zzhv) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
