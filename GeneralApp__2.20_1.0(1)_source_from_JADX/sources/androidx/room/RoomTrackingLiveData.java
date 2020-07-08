package androidx.room;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import androidx.room.InvalidationTracker.Observer;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

class RoomTrackingLiveData<T> extends LiveData<T> {
    final Callable<T> mComputeFunction;
    final AtomicBoolean mComputing = new AtomicBoolean(false);
    private final InvalidationLiveDataContainer mContainer;
    final RoomDatabase mDatabase;
    final boolean mInTransaction;
    final AtomicBoolean mInvalid = new AtomicBoolean(true);
    final Runnable mInvalidationRunnable = new Runnable() {
        public void run() {
            boolean isActive = RoomTrackingLiveData.this.hasActiveObservers();
            if (RoomTrackingLiveData.this.mInvalid.compareAndSet(false, true) && isActive) {
                RoomTrackingLiveData.this.getQueryExecutor().execute(RoomTrackingLiveData.this.mRefreshRunnable);
            }
        }
    };
    final Observer mObserver;
    final Runnable mRefreshRunnable = new Runnable() {
        public void run() {
            if (RoomTrackingLiveData.this.mRegisteredObserver.compareAndSet(false, true)) {
                RoomTrackingLiveData.this.mDatabase.getInvalidationTracker().addWeakObserver(RoomTrackingLiveData.this.mObserver);
            }
            do {
                boolean computed = false;
                if (RoomTrackingLiveData.this.mComputing.compareAndSet(false, true)) {
                    Object obj = null;
                    while (RoomTrackingLiveData.this.mInvalid.compareAndSet(true, false)) {
                        try {
                            computed = true;
                            obj = RoomTrackingLiveData.this.mComputeFunction.call();
                        } catch (Exception e) {
                            throw new RuntimeException("Exception while computing database live data.", e);
                        } catch (Throwable th) {
                            RoomTrackingLiveData.this.mComputing.set(false);
                            throw th;
                        }
                    }
                    if (computed) {
                        RoomTrackingLiveData.this.postValue(obj);
                    }
                    RoomTrackingLiveData.this.mComputing.set(false);
                }
                if (!computed) {
                    return;
                }
            } while (RoomTrackingLiveData.this.mInvalid.get());
        }
    };
    final AtomicBoolean mRegisteredObserver = new AtomicBoolean(false);

    RoomTrackingLiveData(RoomDatabase database, InvalidationLiveDataContainer container, boolean inTransaction, Callable<T> computeFunction, String[] tableNames) {
        this.mDatabase = database;
        this.mInTransaction = inTransaction;
        this.mComputeFunction = computeFunction;
        this.mContainer = container;
        this.mObserver = new Observer(tableNames) {
            public void onInvalidated(Set<String> set) {
                ArchTaskExecutor.getInstance().executeOnMainThread(RoomTrackingLiveData.this.mInvalidationRunnable);
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onActive() {
        super.onActive();
        this.mContainer.onActive(this);
        getQueryExecutor().execute(this.mRefreshRunnable);
    }

    /* access modifiers changed from: protected */
    public void onInactive() {
        super.onInactive();
        this.mContainer.onInactive(this);
    }

    /* access modifiers changed from: 0000 */
    public Executor getQueryExecutor() {
        if (this.mInTransaction) {
            return this.mDatabase.getTransactionExecutor();
        }
        return this.mDatabase.getQueryExecutor();
    }
}
