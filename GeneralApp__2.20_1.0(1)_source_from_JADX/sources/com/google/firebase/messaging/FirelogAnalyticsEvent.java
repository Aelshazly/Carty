package com.google.firebase.messaging;

import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
final class FirelogAnalyticsEvent {
    private final String zza;
    private final Intent zzb;

    /* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
    static final class zza {
        private final FirelogAnalyticsEvent zza;

        zza(FirelogAnalyticsEvent firelogAnalyticsEvent) {
            this.zza = (FirelogAnalyticsEvent) Preconditions.checkNotNull(firelogAnalyticsEvent);
        }

        /* access modifiers changed from: 0000 */
        public final FirelogAnalyticsEvent zza() {
            return this.zza;
        }
    }

    /* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
    static class zzb implements ObjectEncoder<FirelogAnalyticsEvent> {
        zzb() {
        }

        public final /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
            FirelogAnalyticsEvent firelogAnalyticsEvent = (FirelogAnalyticsEvent) obj;
            ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
            Intent zza = firelogAnalyticsEvent.zza();
            objectEncoderContext.add("ttl", zzp.zzf(zza));
            objectEncoderContext.add(NotificationCompat.CATEGORY_EVENT, (Object) firelogAnalyticsEvent.zzb());
            objectEncoderContext.add("instanceId", (Object) zzp.zzc());
            objectEncoderContext.add("priority", zzp.zzm(zza));
            objectEncoderContext.add("packageName", (Object) zzp.zzb());
            objectEncoderContext.add("sdkPlatform", (Object) "ANDROID");
            objectEncoderContext.add("messageType", (Object) zzp.zzk(zza));
            String zzj = zzp.zzj(zza);
            if (zzj != null) {
                objectEncoderContext.add("messageId", (Object) zzj);
            }
            String zzl = zzp.zzl(zza);
            if (zzl != null) {
                objectEncoderContext.add("topic", (Object) zzl);
            }
            String zzg = zzp.zzg(zza);
            if (zzg != null) {
                objectEncoderContext.add("collapseKey", (Object) zzg);
            }
            if (zzp.zzi(zza) != null) {
                objectEncoderContext.add("analyticsLabel", (Object) zzp.zzi(zza));
            }
            if (zzp.zzh(zza) != null) {
                objectEncoderContext.add("composerLabel", (Object) zzp.zzh(zza));
            }
            String zzd = zzp.zzd();
            if (zzd != null) {
                objectEncoderContext.add("projectNumber", (Object) zzd);
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-messaging@@20.1.5 */
    static final class zzc implements ObjectEncoder<zza> {
        zzc() {
        }

        public final /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
            ((ObjectEncoderContext) obj2).add("messaging_client_event", (Object) ((zza) obj).zza());
        }
    }

    FirelogAnalyticsEvent(String str, Intent intent) {
        this.zza = Preconditions.checkNotEmpty(str, "evenType must be non-null");
        this.zzb = (Intent) Preconditions.checkNotNull(intent, "intent must be non-null");
    }

    /* access modifiers changed from: 0000 */
    public final Intent zza() {
        return this.zzb;
    }

    /* access modifiers changed from: 0000 */
    public final String zzb() {
        return this.zza;
    }
}
