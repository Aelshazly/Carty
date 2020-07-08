package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.Flag;
import java.util.Set;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final class AutoValue_SchedulerConfig_ConfigValue extends ConfigValue {
    private final long delta;
    private final Set<Flag> flags;
    private final long maxAllowedDelay;

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    static final class Builder extends com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder {
        private Long delta;
        private Set<Flag> flags;
        private Long maxAllowedDelay;

        Builder() {
        }

        public com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder setDelta(long delta2) {
            this.delta = Long.valueOf(delta2);
            return this;
        }

        public com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder setMaxAllowedDelay(long maxAllowedDelay2) {
            this.maxAllowedDelay = Long.valueOf(maxAllowedDelay2);
            return this;
        }

        public com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder setFlags(Set<Flag> flags2) {
            if (flags2 != null) {
                this.flags = flags2;
                return this;
            }
            throw new NullPointerException("Null flags");
        }

        public ConfigValue build() {
            String missing = "";
            if (this.delta == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" delta");
                missing = sb.toString();
            }
            if (this.maxAllowedDelay == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" maxAllowedDelay");
                missing = sb2.toString();
            }
            if (this.flags == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" flags");
                missing = sb3.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_SchedulerConfig_ConfigValue autoValue_SchedulerConfig_ConfigValue = new AutoValue_SchedulerConfig_ConfigValue(this.delta.longValue(), this.maxAllowedDelay.longValue(), this.flags);
                return autoValue_SchedulerConfig_ConfigValue;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Missing required properties:");
            sb4.append(missing);
            throw new IllegalStateException(sb4.toString());
        }
    }

    private AutoValue_SchedulerConfig_ConfigValue(long delta2, long maxAllowedDelay2, Set<Flag> flags2) {
        this.delta = delta2;
        this.maxAllowedDelay = maxAllowedDelay2;
        this.flags = flags2;
    }

    /* access modifiers changed from: 0000 */
    public long getDelta() {
        return this.delta;
    }

    /* access modifiers changed from: 0000 */
    public long getMaxAllowedDelay() {
        return this.maxAllowedDelay;
    }

    /* access modifiers changed from: 0000 */
    public Set<Flag> getFlags() {
        return this.flags;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConfigValue{delta=");
        sb.append(this.delta);
        sb.append(", maxAllowedDelay=");
        sb.append(this.maxAllowedDelay);
        sb.append(", flags=");
        sb.append(this.flags);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ConfigValue)) {
            return false;
        }
        ConfigValue that = (ConfigValue) o;
        if (!(this.delta == that.getDelta() && this.maxAllowedDelay == that.getMaxAllowedDelay() && this.flags.equals(that.getFlags()))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        long j = this.delta;
        int h$2 = (h$ ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.maxAllowedDelay;
        return ((h$2 ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.flags.hashCode();
    }
}
