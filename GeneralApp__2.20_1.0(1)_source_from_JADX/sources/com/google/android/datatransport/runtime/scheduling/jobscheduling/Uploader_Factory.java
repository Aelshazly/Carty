package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
public final class Uploader_Factory implements Factory<Uploader> {
    private final Provider<BackendRegistry> backendRegistryProvider;
    private final Provider<Clock> clockProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventStore> eventStoreProvider;
    private final Provider<Executor> executorProvider;
    private final Provider<SynchronizationGuard> guardProvider;
    private final Provider<WorkScheduler> workSchedulerProvider;

    public Uploader_Factory(Provider<Context> contextProvider2, Provider<BackendRegistry> backendRegistryProvider2, Provider<EventStore> eventStoreProvider2, Provider<WorkScheduler> workSchedulerProvider2, Provider<Executor> executorProvider2, Provider<SynchronizationGuard> guardProvider2, Provider<Clock> clockProvider2) {
        this.contextProvider = contextProvider2;
        this.backendRegistryProvider = backendRegistryProvider2;
        this.eventStoreProvider = eventStoreProvider2;
        this.workSchedulerProvider = workSchedulerProvider2;
        this.executorProvider = executorProvider2;
        this.guardProvider = guardProvider2;
        this.clockProvider = clockProvider2;
    }

    public Uploader get() {
        Uploader uploader = new Uploader((Context) this.contextProvider.get(), (BackendRegistry) this.backendRegistryProvider.get(), (EventStore) this.eventStoreProvider.get(), (WorkScheduler) this.workSchedulerProvider.get(), (Executor) this.executorProvider.get(), (SynchronizationGuard) this.guardProvider.get(), (Clock) this.clockProvider.get());
        return uploader;
    }

    public static Uploader_Factory create(Provider<Context> contextProvider2, Provider<BackendRegistry> backendRegistryProvider2, Provider<EventStore> eventStoreProvider2, Provider<WorkScheduler> workSchedulerProvider2, Provider<Executor> executorProvider2, Provider<SynchronizationGuard> guardProvider2, Provider<Clock> clockProvider2) {
        Uploader_Factory uploader_Factory = new Uploader_Factory(contextProvider2, backendRegistryProvider2, eventStoreProvider2, workSchedulerProvider2, executorProvider2, guardProvider2, clockProvider2);
        return uploader_Factory;
    }

    public static Uploader newInstance(Context context, BackendRegistry backendRegistry, EventStore eventStore, WorkScheduler workScheduler, Executor executor, SynchronizationGuard guard, Clock clock) {
        Uploader uploader = new Uploader(context, backendRegistry, eventStore, workScheduler, executor, guard, clock);
        return uploader;
    }
}
