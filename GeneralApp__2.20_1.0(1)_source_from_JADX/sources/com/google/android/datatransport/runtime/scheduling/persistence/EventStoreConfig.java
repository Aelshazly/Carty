package com.google.android.datatransport.runtime.scheduling.persistence;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
abstract class EventStoreConfig {
    static final EventStoreConfig DEFAULT = builder().setMaxStorageSizeInBytes(MAX_DB_STORAGE_SIZE_IN_BYTES).setLoadBatchSize(200).setCriticalSectionEnterTimeoutMs(10000).setEventCleanUpAge(DURATION_ONE_WEEK_MS).build();
    private static final long DURATION_ONE_WEEK_MS = 604800000;
    private static final int LOAD_BATCH_SIZE = 200;
    private static final int LOCK_TIME_OUT_MS = 10000;
    private static final long MAX_DB_STORAGE_SIZE_IN_BYTES = 10485760;

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract EventStoreConfig build();

        /* access modifiers changed from: 0000 */
        public abstract Builder setCriticalSectionEnterTimeoutMs(int i);

        /* access modifiers changed from: 0000 */
        public abstract Builder setEventCleanUpAge(long j);

        /* access modifiers changed from: 0000 */
        public abstract Builder setLoadBatchSize(int i);

        /* access modifiers changed from: 0000 */
        public abstract Builder setMaxStorageSizeInBytes(long j);

        Builder() {
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract int getCriticalSectionEnterTimeoutMs();

    /* access modifiers changed from: 0000 */
    public abstract long getEventCleanUpAge();

    /* access modifiers changed from: 0000 */
    public abstract int getLoadBatchSize();

    /* access modifiers changed from: 0000 */
    public abstract long getMaxStorageSizeInBytes();

    EventStoreConfig() {
    }

    static Builder builder() {
        return new Builder();
    }

    /* access modifiers changed from: 0000 */
    public Builder toBuilder() {
        return builder().setMaxStorageSizeInBytes(getMaxStorageSizeInBytes()).setLoadBatchSize(getLoadBatchSize()).setCriticalSectionEnterTimeoutMs(getCriticalSectionEnterTimeoutMs()).setEventCleanUpAge(getEventCleanUpAge());
    }
}
