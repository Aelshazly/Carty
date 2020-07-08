package p008cz.msebera.android.httpclient.impl.client;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: cz.msebera.android.httpclient.impl.client.FutureRequestExecutionMetrics */
public final class FutureRequestExecutionMetrics {
    private final AtomicLong activeConnections = new AtomicLong();
    private final DurationCounter failedConnections = new DurationCounter();
    private final DurationCounter requests = new DurationCounter();
    private final AtomicLong scheduledConnections = new AtomicLong();
    private final DurationCounter successfulConnections = new DurationCounter();
    private final DurationCounter tasks = new DurationCounter();

    /* renamed from: cz.msebera.android.httpclient.impl.client.FutureRequestExecutionMetrics$DurationCounter */
    static class DurationCounter {
        private final AtomicLong count = new AtomicLong(0);
        private final AtomicLong cumulativeDuration = new AtomicLong(0);

        DurationCounter() {
        }

        public void increment(long startTime) {
            this.count.incrementAndGet();
            this.cumulativeDuration.addAndGet(System.currentTimeMillis() - startTime);
        }

        public long count() {
            return this.count.get();
        }

        public long averageDuration() {
            long counter = this.count.get();
            if (counter > 0) {
                return this.cumulativeDuration.get() / counter;
            }
            return 0;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("[count=");
            builder.append(count());
            builder.append(", averageDuration=");
            builder.append(averageDuration());
            builder.append("]");
            return builder.toString();
        }
    }

    FutureRequestExecutionMetrics() {
    }

    /* access modifiers changed from: 0000 */
    public AtomicLong getActiveConnections() {
        return this.activeConnections;
    }

    /* access modifiers changed from: 0000 */
    public AtomicLong getScheduledConnections() {
        return this.scheduledConnections;
    }

    /* access modifiers changed from: 0000 */
    public DurationCounter getSuccessfulConnections() {
        return this.successfulConnections;
    }

    /* access modifiers changed from: 0000 */
    public DurationCounter getFailedConnections() {
        return this.failedConnections;
    }

    /* access modifiers changed from: 0000 */
    public DurationCounter getRequests() {
        return this.requests;
    }

    /* access modifiers changed from: 0000 */
    public DurationCounter getTasks() {
        return this.tasks;
    }

    public long getActiveConnectionCount() {
        return this.activeConnections.get();
    }

    public long getScheduledConnectionCount() {
        return this.scheduledConnections.get();
    }

    public long getSuccessfulConnectionCount() {
        return this.successfulConnections.count();
    }

    public long getSuccessfulConnectionAverageDuration() {
        return this.successfulConnections.averageDuration();
    }

    public long getFailedConnectionCount() {
        return this.failedConnections.count();
    }

    public long getFailedConnectionAverageDuration() {
        return this.failedConnections.averageDuration();
    }

    public long getRequestCount() {
        return this.requests.count();
    }

    public long getRequestAverageDuration() {
        return this.requests.averageDuration();
    }

    public long getTaskCount() {
        return this.tasks.count();
    }

    public long getTaskAverageDuration() {
        return this.tasks.averageDuration();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[activeConnections=");
        builder.append(this.activeConnections);
        builder.append(", scheduledConnections=");
        builder.append(this.scheduledConnections);
        builder.append(", successfulConnections=");
        builder.append(this.successfulConnections);
        builder.append(", failedConnections=");
        builder.append(this.failedConnections);
        builder.append(", requests=");
        builder.append(this.requests);
        builder.append(", tasks=");
        builder.append(this.tasks);
        builder.append("]");
        return builder.toString();
    }
}
