package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.Logger;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public final class ExecutorUtils {
    private static final long DEFAULT_TERMINATION_TIMEOUT = 2;

    private ExecutorUtils() {
    }

    public static ExecutorService buildSingleThreadExecutorService(String name) {
        ExecutorService executor = Executors.newSingleThreadExecutor(getNamedThreadFactory(name));
        addDelayedShutdownHook(name, executor);
        return executor;
    }

    public static ScheduledExecutorService buildSingleThreadScheduledExecutorService(String name) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(getNamedThreadFactory(name));
        addDelayedShutdownHook(name, executor);
        return executor;
    }

    public static final ThreadFactory getNamedThreadFactory(final String threadNameTemplate) {
        final AtomicLong count = new AtomicLong(1);
        return new ThreadFactory() {
            public Thread newThread(final Runnable runnable) {
                Thread thread = Executors.defaultThreadFactory().newThread(new BackgroundPriorityRunnable() {
                    public void onRun() {
                        runnable.run();
                    }
                });
                StringBuilder sb = new StringBuilder();
                sb.append(threadNameTemplate);
                sb.append(count.getAndIncrement());
                thread.setName(sb.toString());
                return thread;
            }
        };
    }

    private static final void addDelayedShutdownHook(String serviceName, ExecutorService service) {
        addDelayedShutdownHook(serviceName, service, 2, TimeUnit.SECONDS);
    }

    public static final void addDelayedShutdownHook(String serviceName, ExecutorService service, long terminationTimeout, TimeUnit timeUnit) {
        Runtime runtime = Runtime.getRuntime();
        final String str = serviceName;
        final ExecutorService executorService = service;
        final long j = terminationTimeout;
        final TimeUnit timeUnit2 = timeUnit;
        C07672 r2 = new BackgroundPriorityRunnable() {
            public void onRun() {
                try {
                    Logger logger = Logger.getLogger();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Executing shutdown hook for ");
                    sb.append(str);
                    logger.mo19679d(sb.toString());
                    executorService.shutdown();
                    if (!executorService.awaitTermination(j, timeUnit2)) {
                        Logger logger2 = Logger.getLogger();
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(" did not shut down in the allocated time. Requesting immediate shutdown.");
                        logger2.mo19679d(sb2.toString());
                        executorService.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    Logger.getLogger().mo19679d(String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", new Object[]{str}));
                    executorService.shutdownNow();
                }
            }
        };
        StringBuilder sb = new StringBuilder();
        sb.append("Crashlytics Shutdown Hook for ");
        sb.append(serviceName);
        runtime.addShutdownHook(new Thread(r2, sb.toString()));
    }
}
