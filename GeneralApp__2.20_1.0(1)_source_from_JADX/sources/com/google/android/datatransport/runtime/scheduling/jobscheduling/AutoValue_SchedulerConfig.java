package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.Map;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final class AutoValue_SchedulerConfig extends SchedulerConfig {
    private final Clock clock;
    private final Map<Priority, ConfigValue> values;

    AutoValue_SchedulerConfig(Clock clock2, Map<Priority, ConfigValue> values2) {
        if (clock2 != null) {
            this.clock = clock2;
            if (values2 != null) {
                this.values = values2;
                return;
            }
            throw new NullPointerException("Null values");
        }
        throw new NullPointerException("Null clock");
    }

    /* access modifiers changed from: 0000 */
    public Clock getClock() {
        return this.clock;
    }

    /* access modifiers changed from: 0000 */
    public Map<Priority, ConfigValue> getValues() {
        return this.values;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SchedulerConfig{clock=");
        sb.append(this.clock);
        sb.append(", values=");
        sb.append(this.values);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof SchedulerConfig)) {
            return false;
        }
        SchedulerConfig that = (SchedulerConfig) o;
        if (!this.clock.equals(that.getClock()) || !this.values.equals(that.getValues())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.clock.hashCode()) * 1000003) ^ this.values.hashCode();
    }
}
