package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage;
import com.google.firebase.encoders.annotations.Encodable.Ignore;

/* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage */
/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class C0791xfe724d07 extends BinaryImage {
    private final long baseAddress;
    private final String name;
    private final long size;
    private final String uuid;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage$Builder */
    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder {
        private Long baseAddress;
        private String name;
        private Long size;
        private String uuid;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setBaseAddress(long baseAddress2) {
            this.baseAddress = Long.valueOf(baseAddress2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setSize(long size2) {
            this.size = Long.valueOf(size2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setName(String name2) {
            if (name2 != null) {
                this.name = name2;
                return this;
            }
            throw new NullPointerException("Null name");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setUuid(String uuid2) {
            this.uuid = uuid2;
            return this;
        }

        public BinaryImage build() {
            String missing = "";
            if (this.baseAddress == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" baseAddress");
                missing = sb.toString();
            }
            if (this.size == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" size");
                missing = sb2.toString();
            }
            if (this.name == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" name");
                missing = sb3.toString();
            }
            if (missing.isEmpty()) {
                C0791xfe724d07 autoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage = new C0791xfe724d07(this.baseAddress.longValue(), this.size.longValue(), this.name, this.uuid);
                return autoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Missing required properties:");
            sb4.append(missing);
            throw new IllegalStateException(sb4.toString());
        }
    }

    private C0791xfe724d07(long baseAddress2, long size2, String name2, String uuid2) {
        this.baseAddress = baseAddress2;
        this.size = size2;
        this.name = name2;
        this.uuid = uuid2;
    }

    public long getBaseAddress() {
        return this.baseAddress;
    }

    public long getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }

    @Ignore
    public String getUuid() {
        return this.uuid;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BinaryImage{baseAddress=");
        sb.append(this.baseAddress);
        sb.append(", size=");
        sb.append(this.size);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", uuid=");
        sb.append(this.uuid);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof BinaryImage)) {
            return false;
        }
        BinaryImage that = (BinaryImage) o;
        if (this.baseAddress == that.getBaseAddress() && this.size == that.getSize() && this.name.equals(that.getName())) {
            String str = this.uuid;
            if (str != null) {
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        long j = this.baseAddress;
        int h$2 = (h$ ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.size;
        int h$3 = (((h$2 ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.name.hashCode()) * 1000003;
        String str = this.uuid;
        return h$3 ^ (str == null ? 0 : str.hashCode());
    }
}
