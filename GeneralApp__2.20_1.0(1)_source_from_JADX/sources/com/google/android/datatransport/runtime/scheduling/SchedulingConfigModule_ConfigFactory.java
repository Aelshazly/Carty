package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.time.Clock;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
public final class SchedulingConfigModule_ConfigFactory implements Factory<SchedulerConfig> {
    private final Provider<Clock> clockProvider;

    public SchedulingConfigModule_ConfigFactory(Provider<Clock> clockProvider2) {
        this.clockProvider = clockProvider2;
    }

    public SchedulerConfig get() {
        return config((Clock) this.clockProvider.get());
    }

    public static SchedulingConfigModule_ConfigFactory create(Provider<Clock> clockProvider2) {
        return new SchedulingConfigModule_ConfigFactory(clockProvider2);
    }

    public static SchedulerConfig config(Clock clock) {
        return (SchedulerConfig) Preconditions.checkNotNull(SchedulingConfigModule.config(clock), "Cannot return null from a non-@Nullable @Provides method");
    }
}
