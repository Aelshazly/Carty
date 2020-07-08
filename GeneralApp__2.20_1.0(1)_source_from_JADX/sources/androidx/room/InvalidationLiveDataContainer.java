package androidx.room;

import androidx.lifecycle.LiveData;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.Callable;

class InvalidationLiveDataContainer {
    private final RoomDatabase mDatabase;
    final Set<LiveData> mLiveDataSet = Collections.newSetFromMap(new IdentityHashMap());

    InvalidationLiveDataContainer(RoomDatabase database) {
        this.mDatabase = database;
    }

    /* access modifiers changed from: 0000 */
    public <T> LiveData<T> create(String[] tableNames, boolean inTransaction, Callable<T> computeFunction) {
        RoomTrackingLiveData roomTrackingLiveData = new RoomTrackingLiveData(this.mDatabase, this, inTransaction, computeFunction, tableNames);
        return roomTrackingLiveData;
    }

    /* access modifiers changed from: 0000 */
    public void onActive(LiveData liveData) {
        this.mLiveDataSet.add(liveData);
    }

    /* access modifiers changed from: 0000 */
    public void onInactive(LiveData liveData) {
        this.mLiveDataSet.remove(liveData);
    }
}
