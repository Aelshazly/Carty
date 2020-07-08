package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
public final class zzkw implements zzku {
    private static final zzbe<Long> zzagx;
    private static final zzbe<Boolean> zzagy;
    private static final zzbe<Boolean> zzagz;
    private static final zzbe<Boolean> zzaha;
    private static final zzbe<Boolean> zzahb;
    private static final zzbe<Boolean> zzahc;
    private static final zzbe<Boolean> zzahd;
    private static final zzbe<Boolean> zzahe;
    private static final zzbe<Boolean> zzahf;
    private static final zzbe<Boolean> zzahg;
    private static final zzbe<Boolean> zzahh;
    private static final zzbe<Boolean> zzahi;
    private static final zzbe<Long> zzahj;
    private static final zzbe<Long> zzahk;

    public final boolean zzjp() {
        return ((Boolean) zzagz.get()).booleanValue();
    }

    public final boolean zzjq() {
        return ((Boolean) zzahd.get()).booleanValue();
    }

    static {
        zzbk zzf = new zzbk(zzbb.getContentProviderUri("com.google.android.gms.vision.sdk")).zzf("vision.sdk:");
        zzagx = zzf.zza("OptionalModule__check_alarm_seconds", 10);
        zzagy = zzf.zza("OptionalModule__enable_barcode_optional_module", false);
        zzagz = zzf.zza("OptionalModule__enable_barcode_optional_module_v25", false);
        zzaha = zzf.zza("OptionalModule__enable_face_optional_module", false);
        zzahb = zzf.zza("OptionalModule__enable_face_optional_module_v25", true);
        zzahc = zzf.zza("OptionalModule__enable_ica_optional_module", false);
        zzahd = zzf.zza("OptionalModule__enable_ica_optional_module_v25", false);
        zzahe = zzf.zza("OptionalModule__enable_ocr_optional_module", false);
        zzahf = zzf.zza("OptionalModule__enable_ocr_optional_module_v25", false);
        zzahg = zzf.zza("OptionalModule__enable_old_download_path", true);
        zzahh = zzf.zza("OptionalModule__enable_optional_module_download_retry", false);
        zzahi = zzf.zza("OptionalModule__enable_progress_listener_for_optional_module_download", false);
        zzahj = zzf.zza("OptionalModule__listener_timeout_in_minutes", 5);
        zzahk = zzf.zza("OptionalModule__max_download_status_pending_count", 5);
    }
}
