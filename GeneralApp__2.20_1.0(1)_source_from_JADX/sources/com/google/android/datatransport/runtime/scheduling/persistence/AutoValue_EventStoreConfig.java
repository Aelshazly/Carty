package com.google.android.datatransport.runtime.scheduling.persistence;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final class AutoValue_EventStoreConfig extends EventStoreConfig {
    private final int criticalSectionEnterTimeoutMs;
    private final long eventCleanUpAge;
    private final int loadBatchSize;
    private final long maxStorageSizeInBytes;

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    static final class Builder extends Builder {
        private Integer criticalSectionEnterTimeoutMs;
        private Long eventCleanUpAge;
        private Integer loadBatchSize;
        private Long maxStorageSizeInBytes;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public Builder setMaxStorageSizeInBytes(long maxStorageSizeInBytes2) {
            this.maxStorageSizeInBytes = Long.valueOf(maxStorageSizeInBytes2);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder setLoadBatchSize(int loadBatchSize2) {
            this.loadBatchSize = Integer.valueOf(loadBatchSize2);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder setCriticalSectionEnterTimeoutMs(int criticalSectionEnterTimeoutMs2) {
            this.criticalSectionEnterTimeoutMs = Integer.valueOf(criticalSectionEnterTimeoutMs2);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder setEventCleanUpAge(long eventCleanUpAge2) {
            this.eventCleanUpAge = Long.valueOf(eventCleanUpAge2);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public EventStoreConfig build() {
            String missing = "";
            if (this.maxStorageSizeInBytes == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" maxStorageSizeInBytes");
                missing = sb.toString();
            }
            if (this.loadBatchSize == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" loadBatchSize");
                missing = sb2.toString();
            }
            if (this.criticalSectionEnterTimeoutMs == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" criticalSectionEnterTimeoutMs");
                missing = sb3.toString();
            }
            if (this.eventCleanUpAge == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(missing);
                sb4.append(" eventCleanUpAge");
                missing = sb4.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_EventStoreConfig autoValue_EventStoreConfig = new AutoValue_EventStoreConfig(this.maxStorageSizeInBytes.longValue(), this.loadBatchSize.intValue(), this.criticalSectionEnterTimeoutMs.intValue(), this.eventCleanUpAge.longValue());
                return autoValue_EventStoreConfig;
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Missing required properties:");
            sb5.append(missing);
            throw new IllegalStateException(sb5.toString());
        }
    }

    private AutoValue_EventStoreConfig(long maxStorageSizeInBytes2, int loadBatchSize2, int criticalSectionEnterTimeoutMs2, long eventCleanUpAge2) {
        this.maxStorageSizeInBytes = maxStorageSizeInBytes2;
        this.loadBatchSize = loadBatchSize2;
        this.criticalSectionEnterTimeoutMs = criticalSectionEnterTimeoutMs2;
        this.eventCleanUpAge = eventCleanUpAge2;
    }

    /* access modifiers changed from: 0000 */
    public long getMaxStorageSizeInBytes() {
        return this.maxStorageSizeInBytes;
    }

    /* access modifiers changed from: 0000 */
    public int getLoadBatchSize() {
        return this.loadBatchSize;
    }

    /* access modifiers changed from: 0000 */
    public int getCriticalSectionEnterTimeoutMs() {
        return this.criticalSectionEnterTimeoutMs;
    }

    /* access modifiers changed from: 0000 */
    public long getEventCleanUpAge() {
        return this.eventCleanUpAge;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EventStoreConfig{maxStorageSizeInBytes=");
        sb.append(this.maxStorageSizeInBytes);
        sb.append(", loadBatchSize=");
        sb.append(this.loadBatchSize);
        sb.append(", criticalSectionEnterTimeoutMs=");
        sb.append(this.criticalSectionEnterTimeoutMs);
        sb.append(", eventCleanUpAge=");
        sb.append(this.eventCleanUpAge);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof EventStoreConfig)) {
            return false;
        }
        EventStoreConfig that = (EventStoreConfig) o;
        if (!(this.maxStorageSizeInBytes == that.getMaxStorageSizeInBytes() && this.loadBatchSize == that.getLoadBatchSize() && this.criticalSectionEnterTimeoutMs == that.getCriticalSectionEnterTimeoutMs() && this.eventCleanUpAge == that.getEventCleanUpAge())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        long j = this.maxStorageSizeInBytes;
        int h$2 = (((((h$ ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.loadBatchSize) * 1000003) ^ this.criticalSectionEnterTimeoutMs) * 1000003;
        long j2 = this.eventCleanUpAge;
        return h$2 ^ ((int) (j2 ^ (j2 >>> 32)));
    }
}
