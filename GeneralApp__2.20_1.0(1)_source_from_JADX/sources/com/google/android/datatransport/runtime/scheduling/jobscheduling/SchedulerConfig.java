package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
public abstract class SchedulerConfig {
    private static final long ONE_SECOND = 1000;
    private static final long THIRTY_SECONDS = 30000;
    private static final long TWENTY_FOUR_HOURS = 86400000;

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    public static class Builder {
        private Clock clock;
        private Map<Priority, ConfigValue> values = new HashMap();

        public Builder setClock(Clock clock2) {
            this.clock = clock2;
            return this;
        }

        public Builder addConfig(Priority priority, ConfigValue value) {
            this.values.put(priority, value);
            return this;
        }

        public SchedulerConfig build() {
            if (this.clock == null) {
                throw new NullPointerException("missing required property: clock");
            } else if (this.values.keySet().size() >= Priority.values().length) {
                Map<Priority, ConfigValue> values2 = this.values;
                this.values = new HashMap();
                return SchedulerConfig.create(this.clock, values2);
            } else {
                throw new IllegalStateException("Not all priorities have been configured");
            }
        }
    }

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    public static abstract class ConfigValue {

        /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
        public static abstract class Builder {
            public abstract ConfigValue build();

            public abstract Builder setDelta(long j);

            public abstract Builder setFlags(Set<Flag> set);

            public abstract Builder setMaxAllowedDelay(long j);
        }

        /* access modifiers changed from: 0000 */
        public abstract long getDelta();

        /* access modifiers changed from: 0000 */
        public abstract Set<Flag> getFlags();

        /* access modifiers changed from: 0000 */
        public abstract long getMaxAllowedDelay();

        public static Builder builder() {
            return new Builder().setFlags(Collections.emptySet());
        }
    }

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    public enum Flag {
        NETWORK_UNMETERED,
        DEVICE_IDLE,
        DEVICE_CHARGING
    }

    /* access modifiers changed from: 0000 */
    public abstract Clock getClock();

    /* access modifiers changed from: 0000 */
    public abstract Map<Priority, ConfigValue> getValues();

    public static SchedulerConfig getDefault(Clock clock) {
        return builder().addConfig(Priority.DEFAULT, ConfigValue.builder().setDelta(THIRTY_SECONDS).setMaxAllowedDelay(TWENTY_FOUR_HOURS).build()).addConfig(Priority.HIGHEST, ConfigValue.builder().setDelta(ONE_SECOND).setMaxAllowedDelay(TWENTY_FOUR_HOURS).build()).addConfig(Priority.VERY_LOW, ConfigValue.builder().setDelta(TWENTY_FOUR_HOURS).setMaxAllowedDelay(TWENTY_FOUR_HOURS).setFlags(immutableSetOf(Flag.NETWORK_UNMETERED, Flag.DEVICE_IDLE)).build()).setClock(clock).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    static SchedulerConfig create(Clock clock, Map<Priority, ConfigValue> values) {
        return new AutoValue_SchedulerConfig(clock, values);
    }

    public long getScheduleDelay(Priority priority, long minTimestamp, int attemptNumber) {
        ConfigValue config = (ConfigValue) getValues().get(priority);
        return Math.min(Math.max(((long) Math.pow(2.0d, (double) (attemptNumber - 1))) * config.getDelta(), minTimestamp - getClock().getTime()), config.getMaxAllowedDelay());
    }

    public android.app.job.JobInfo.Builder configureJob(android.app.job.JobInfo.Builder builder, Priority priority, long minimumTimestamp, int attemptNumber) {
        builder.setMinimumLatency(getScheduleDelay(priority, minimumTimestamp, attemptNumber));
        populateFlags(builder, ((ConfigValue) getValues().get(priority)).getFlags());
        return builder;
    }

    private void populateFlags(android.app.job.JobInfo.Builder builder, Set<Flag> flags) {
        if (flags.contains(Flag.NETWORK_UNMETERED)) {
            builder.setRequiredNetworkType(2);
        } else {
            builder.setRequiredNetworkType(1);
        }
        if (flags.contains(Flag.DEVICE_CHARGING)) {
            builder.setRequiresCharging(true);
        }
        if (flags.contains(Flag.DEVICE_IDLE)) {
            builder.setRequiresDeviceIdle(true);
        }
    }

    public Set<Flag> getFlags(Priority priority) {
        return ((ConfigValue) getValues().get(priority)).getFlags();
    }

    private static <T> Set<T> immutableSetOf(T... values) {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(values)));
    }
}
