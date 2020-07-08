package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.backends.BackendResponse.Status;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final class AutoValue_BackendResponse extends BackendResponse {
    private final long nextRequestWaitMillis;
    private final Status status;

    AutoValue_BackendResponse(Status status2, long nextRequestWaitMillis2) {
        if (status2 != null) {
            this.status = status2;
            this.nextRequestWaitMillis = nextRequestWaitMillis2;
            return;
        }
        throw new NullPointerException("Null status");
    }

    public Status getStatus() {
        return this.status;
    }

    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BackendResponse{status=");
        sb.append(this.status);
        sb.append(", nextRequestWaitMillis=");
        sb.append(this.nextRequestWaitMillis);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof BackendResponse)) {
            return false;
        }
        BackendResponse that = (BackendResponse) o;
        if (!this.status.equals(that.getStatus()) || this.nextRequestWaitMillis != that.getNextRequestWaitMillis()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int h$ = ((1 * 1000003) ^ this.status.hashCode()) * 1000003;
        long j = this.nextRequestWaitMillis;
        return h$ ^ ((int) (j ^ (j >>> 32)));
    }
}
