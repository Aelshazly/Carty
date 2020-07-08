package androidx.room;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.IMultiInstanceInvalidationCallback.Stub;
import androidx.room.InvalidationTracker.Observer;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

class MultiInstanceInvalidationClient {
    final Context mAppContext;
    final IMultiInstanceInvalidationCallback mCallback = new Stub() {
        public void onInvalidation(final String[] tables) {
            MultiInstanceInvalidationClient.this.mExecutor.execute(new Runnable() {
                public void run() {
                    MultiInstanceInvalidationClient.this.mInvalidationTracker.notifyObserversByTableNames(tables);
                }
            });
        }
    };
    int mClientId;
    final Executor mExecutor;
    final InvalidationTracker mInvalidationTracker;
    final String mName;
    final Observer mObserver;
    final Runnable mRemoveObserverRunnable = new Runnable() {
        public void run() {
            MultiInstanceInvalidationClient.this.mInvalidationTracker.removeObserver(MultiInstanceInvalidationClient.this.mObserver);
        }
    };
    IMultiInstanceInvalidationService mService;
    final ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            MultiInstanceInvalidationClient.this.mService = IMultiInstanceInvalidationService.Stub.asInterface(service);
            MultiInstanceInvalidationClient.this.mExecutor.execute(MultiInstanceInvalidationClient.this.mSetUpRunnable);
        }

        public void onServiceDisconnected(ComponentName name) {
            MultiInstanceInvalidationClient.this.mExecutor.execute(MultiInstanceInvalidationClient.this.mRemoveObserverRunnable);
            MultiInstanceInvalidationClient.this.mService = null;
        }
    };
    final Runnable mSetUpRunnable = new Runnable() {
        public void run() {
            try {
                IMultiInstanceInvalidationService service = MultiInstanceInvalidationClient.this.mService;
                if (service != null) {
                    MultiInstanceInvalidationClient.this.mClientId = service.registerCallback(MultiInstanceInvalidationClient.this.mCallback, MultiInstanceInvalidationClient.this.mName);
                    MultiInstanceInvalidationClient.this.mInvalidationTracker.addObserver(MultiInstanceInvalidationClient.this.mObserver);
                }
            } catch (RemoteException e) {
                Log.w("ROOM", "Cannot register multi-instance invalidation callback", e);
            }
        }
    };
    final AtomicBoolean mStopped = new AtomicBoolean(false);
    private final Runnable mTearDownRunnable = new Runnable() {
        public void run() {
            MultiInstanceInvalidationClient.this.mInvalidationTracker.removeObserver(MultiInstanceInvalidationClient.this.mObserver);
            try {
                IMultiInstanceInvalidationService service = MultiInstanceInvalidationClient.this.mService;
                if (service != null) {
                    service.unregisterCallback(MultiInstanceInvalidationClient.this.mCallback, MultiInstanceInvalidationClient.this.mClientId);
                }
            } catch (RemoteException e) {
                Log.w("ROOM", "Cannot unregister multi-instance invalidation callback", e);
            }
            MultiInstanceInvalidationClient.this.mAppContext.unbindService(MultiInstanceInvalidationClient.this.mServiceConnection);
        }
    };

    MultiInstanceInvalidationClient(Context context, String name, InvalidationTracker invalidationTracker, Executor executor) {
        this.mAppContext = context.getApplicationContext();
        this.mName = name;
        this.mInvalidationTracker = invalidationTracker;
        this.mExecutor = executor;
        this.mObserver = new Observer((String[]) invalidationTracker.mTableIdLookup.keySet().toArray(new String[0])) {
            public void onInvalidated(Set<String> tables) {
                if (!MultiInstanceInvalidationClient.this.mStopped.get()) {
                    try {
                        IMultiInstanceInvalidationService service = MultiInstanceInvalidationClient.this.mService;
                        if (service != null) {
                            service.broadcastInvalidation(MultiInstanceInvalidationClient.this.mClientId, (String[]) tables.toArray(new String[0]));
                        }
                    } catch (RemoteException e) {
                        Log.w("ROOM", "Cannot broadcast invalidation", e);
                    }
                }
            }

            /* access modifiers changed from: 0000 */
            public boolean isRemote() {
                return true;
            }
        };
        this.mAppContext.bindService(new Intent(this.mAppContext, MultiInstanceInvalidationService.class), this.mServiceConnection, 1);
    }

    /* access modifiers changed from: 0000 */
    public void stop() {
        if (this.mStopped.compareAndSet(false, true)) {
            this.mExecutor.execute(this.mTearDownRunnable);
        }
    }
}
