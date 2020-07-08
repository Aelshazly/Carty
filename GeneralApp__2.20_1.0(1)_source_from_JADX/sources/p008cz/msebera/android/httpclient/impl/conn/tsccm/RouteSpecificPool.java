package p008cz.msebera.android.httpclient.impl.conn.tsccm;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import p008cz.msebera.android.httpclient.conn.params.ConnPerRoute;
import p008cz.msebera.android.httpclient.conn.routing.HttpRoute;
import p008cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import p008cz.msebera.android.httpclient.util.Args;
import p008cz.msebera.android.httpclient.util.Asserts;
import p008cz.msebera.android.httpclient.util.LangUtils;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.tsccm.RouteSpecificPool */
public class RouteSpecificPool {
    protected final ConnPerRoute connPerRoute;
    protected final LinkedList<BasicPoolEntry> freeEntries;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    protected final int maxEntries;
    protected int numEntries;
    protected final HttpRoute route;
    protected final Queue<WaitingThread> waitingThreads;

    @Deprecated
    public RouteSpecificPool(HttpRoute route2, int maxEntries2) {
        this.route = route2;
        this.maxEntries = maxEntries2;
        this.connPerRoute = new ConnPerRoute() {
            public int getMaxForRoute(HttpRoute route) {
                return RouteSpecificPool.this.maxEntries;
            }
        };
        this.freeEntries = new LinkedList<>();
        this.waitingThreads = new LinkedList();
        this.numEntries = 0;
    }

    public RouteSpecificPool(HttpRoute route2, ConnPerRoute connPerRoute2) {
        this.route = route2;
        this.connPerRoute = connPerRoute2;
        this.maxEntries = connPerRoute2.getMaxForRoute(route2);
        this.freeEntries = new LinkedList<>();
        this.waitingThreads = new LinkedList();
        this.numEntries = 0;
    }

    public final HttpRoute getRoute() {
        return this.route;
    }

    public final int getMaxEntries() {
        return this.maxEntries;
    }

    public boolean isUnused() {
        return this.numEntries < 1 && this.waitingThreads.isEmpty();
    }

    public int getCapacity() {
        return this.connPerRoute.getMaxForRoute(this.route) - this.numEntries;
    }

    public final int getEntryCount() {
        return this.numEntries;
    }

    public BasicPoolEntry allocEntry(Object state) {
        if (!this.freeEntries.isEmpty()) {
            LinkedList<BasicPoolEntry> linkedList = this.freeEntries;
            ListIterator<BasicPoolEntry> it = linkedList.listIterator(linkedList.size());
            while (it.hasPrevious()) {
                BasicPoolEntry entry = (BasicPoolEntry) it.previous();
                if (entry.getState() != null) {
                    if (LangUtils.equals(state, entry.getState())) {
                    }
                }
                it.remove();
                return entry;
            }
        }
        if (getCapacity() != 0 || this.freeEntries.isEmpty()) {
            return null;
        }
        BasicPoolEntry entry2 = (BasicPoolEntry) this.freeEntries.remove();
        entry2.shutdownEntry();
        try {
            entry2.getConnection().close();
        } catch (IOException ex) {
            this.log.debug("I/O error closing connection", ex);
        }
        return entry2;
    }

    public void freeEntry(BasicPoolEntry entry) {
        int i = this.numEntries;
        if (i < 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("No entry created for this pool. ");
            sb.append(this.route);
            throw new IllegalStateException(sb.toString());
        } else if (i > this.freeEntries.size()) {
            this.freeEntries.add(entry);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("No entry allocated from this pool. ");
            sb2.append(this.route);
            throw new IllegalStateException(sb2.toString());
        }
    }

    public void createdEntry(BasicPoolEntry entry) {
        Args.check(this.route.equals(entry.getPlannedRoute()), "Entry not planned for this pool");
        this.numEntries++;
    }

    public boolean deleteEntry(BasicPoolEntry entry) {
        boolean found = this.freeEntries.remove(entry);
        if (found) {
            this.numEntries--;
        }
        return found;
    }

    public void dropEntry() {
        Asserts.check(this.numEntries > 0, "There is no entry that could be dropped");
        this.numEntries--;
    }

    public void queueThread(WaitingThread wt) {
        Args.notNull(wt, "Waiting thread");
        this.waitingThreads.add(wt);
    }

    public boolean hasThread() {
        return !this.waitingThreads.isEmpty();
    }

    public WaitingThread nextThread() {
        return (WaitingThread) this.waitingThreads.peek();
    }

    public void removeThread(WaitingThread wt) {
        if (wt != null) {
            this.waitingThreads.remove(wt);
        }
    }
}
