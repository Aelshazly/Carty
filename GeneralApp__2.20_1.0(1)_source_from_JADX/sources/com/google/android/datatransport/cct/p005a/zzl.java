package com.google.android.datatransport.cct.p005a;

/* renamed from: com.google.android.datatransport.cct.a.zzl */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
final class zzl extends zzx {
    private final long zza;

    zzl(long j) {
        this.zza = j;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzx)) {
            return false;
        }
        if (this.zza != ((zzx) obj).zza()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long j = this.zza;
        return 1000003 ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LogResponse{nextRequestWaitMillis=");
        sb.append(this.zza);
        sb.append("}");
        return sb.toString();
    }

    public long zza() {
        return this.zza;
    }
}
