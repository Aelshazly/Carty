package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.CreationContextFactory_Factory;
import com.google.android.datatransport.runtime.backends.MetadataBackendRegistry_Factory;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler_Factory;
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule_ConfigFactory;
import com.google.android.datatransport.runtime.scheduling.SchedulingModule_WorkSchedulerFactory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_SchemaVersionFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_StoreConfigFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore_Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager_Factory;
import com.google.android.datatransport.runtime.time.TimeModule_EventClockFactory;
import com.google.android.datatransport.runtime.time.TimeModule_UptimeClockFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final class DaggerTransportRuntimeComponent extends TransportRuntimeComponent {
    private Provider<SchedulerConfig> configProvider;
    private Provider creationContextFactoryProvider;
    private Provider<DefaultScheduler> defaultSchedulerProvider;
    private Provider<Executor> executorProvider;
    private Provider metadataBackendRegistryProvider;
    private Provider<SQLiteEventStore> sQLiteEventStoreProvider;
    private Provider schemaManagerProvider;
    private Provider<Context> setApplicationContextProvider;
    private Provider<TransportRuntime> transportRuntimeProvider;
    private Provider<Uploader> uploaderProvider;
    private Provider<WorkInitializer> workInitializerProvider;
    private Provider<WorkScheduler> workSchedulerProvider;

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    private static final class Builder implements Builder {
        private Context setApplicationContext;

        private Builder() {
        }

        public Builder setApplicationContext(Context applicationContext) {
            this.setApplicationContext = (Context) Preconditions.checkNotNull(applicationContext);
            return this;
        }

        public TransportRuntimeComponent build() {
            Preconditions.checkBuilderRequirement(this.setApplicationContext, Context.class);
            return new DaggerTransportRuntimeComponent(this.setApplicationContext);
        }
    }

    private DaggerTransportRuntimeComponent(Context setApplicationContextParam) {
        initialize(setApplicationContextParam);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Context setApplicationContextParam) {
        this.executorProvider = DoubleCheck.provider(ExecutionModule_ExecutorFactory.create());
        this.setApplicationContextProvider = InstanceFactory.create(setApplicationContextParam);
        this.creationContextFactoryProvider = CreationContextFactory_Factory.create(this.setApplicationContextProvider, TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create());
        this.metadataBackendRegistryProvider = DoubleCheck.provider(MetadataBackendRegistry_Factory.create(this.setApplicationContextProvider, this.creationContextFactoryProvider));
        this.schemaManagerProvider = SchemaManager_Factory.create(this.setApplicationContextProvider, EventStoreModule_SchemaVersionFactory.create());
        this.sQLiteEventStoreProvider = DoubleCheck.provider(SQLiteEventStore_Factory.create(TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), EventStoreModule_StoreConfigFactory.create(), this.schemaManagerProvider));
        this.configProvider = SchedulingConfigModule_ConfigFactory.create(TimeModule_EventClockFactory.create());
        this.workSchedulerProvider = SchedulingModule_WorkSchedulerFactory.create(this.setApplicationContextProvider, this.sQLiteEventStoreProvider, this.configProvider, TimeModule_UptimeClockFactory.create());
        Provider<Executor> provider = this.executorProvider;
        Provider provider2 = this.metadataBackendRegistryProvider;
        Provider<WorkScheduler> provider3 = this.workSchedulerProvider;
        Provider<SQLiteEventStore> provider4 = this.sQLiteEventStoreProvider;
        this.defaultSchedulerProvider = DefaultScheduler_Factory.create(provider, provider2, provider3, provider4, provider4);
        Provider<Context> provider5 = this.setApplicationContextProvider;
        Provider provider6 = this.metadataBackendRegistryProvider;
        Provider<SQLiteEventStore> provider7 = this.sQLiteEventStoreProvider;
        this.uploaderProvider = Uploader_Factory.create(provider5, provider6, provider7, this.workSchedulerProvider, this.executorProvider, provider7, TimeModule_EventClockFactory.create());
        Provider<Executor> provider8 = this.executorProvider;
        Provider<SQLiteEventStore> provider9 = this.sQLiteEventStoreProvider;
        this.workInitializerProvider = WorkInitializer_Factory.create(provider8, provider9, this.workSchedulerProvider, provider9);
        this.transportRuntimeProvider = DoubleCheck.provider(TransportRuntime_Factory.create(TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), this.defaultSchedulerProvider, this.uploaderProvider, this.workInitializerProvider));
    }

    /* access modifiers changed from: 0000 */
    public TransportRuntime getTransportRuntime() {
        return (TransportRuntime) this.transportRuntimeProvider.get();
    }

    /* access modifiers changed from: 0000 */
    public EventStore getEventStore() {
        return (EventStore) this.sQLiteEventStoreProvider.get();
    }
}