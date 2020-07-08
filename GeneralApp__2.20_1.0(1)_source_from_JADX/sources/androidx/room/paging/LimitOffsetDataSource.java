package androidx.room.paging;

import android.database.Cursor;
import androidx.paging.PositionalDataSource;
import androidx.paging.PositionalDataSource.LoadInitialCallback;
import androidx.paging.PositionalDataSource.LoadInitialParams;
import androidx.paging.PositionalDataSource.LoadRangeCallback;
import androidx.paging.PositionalDataSource.LoadRangeParams;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.p004db.SupportSQLiteQuery;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class LimitOffsetDataSource<T> extends PositionalDataSource<T> {
    private final String mCountQuery;
    private final RoomDatabase mDb;
    private final boolean mInTransaction;
    private final String mLimitOffsetQuery;
    private final Observer mObserver;
    private final RoomSQLiteQuery mSourceQuery;

    /* access modifiers changed from: protected */
    public abstract List<T> convertRows(Cursor cursor);

    protected LimitOffsetDataSource(RoomDatabase db, SupportSQLiteQuery query, boolean inTransaction, String... tables) {
        this(db, RoomSQLiteQuery.copyFrom(query), inTransaction, tables);
    }

    protected LimitOffsetDataSource(RoomDatabase db, RoomSQLiteQuery query, boolean inTransaction, String... tables) {
        this.mDb = db;
        this.mSourceQuery = query;
        this.mInTransaction = inTransaction;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*) FROM ( ");
        sb.append(this.mSourceQuery.getSql());
        sb.append(" )");
        this.mCountQuery = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT * FROM ( ");
        sb2.append(this.mSourceQuery.getSql());
        sb2.append(" ) LIMIT ? OFFSET ?");
        this.mLimitOffsetQuery = sb2.toString();
        this.mObserver = new Observer(tables) {
            public void onInvalidated(Set<String> set) {
                LimitOffsetDataSource.this.invalidate();
            }
        };
        db.getInvalidationTracker().addWeakObserver(this.mObserver);
    }

    public int countItems() {
        RoomSQLiteQuery sqLiteQuery = RoomSQLiteQuery.acquire(this.mCountQuery, this.mSourceQuery.getArgCount());
        sqLiteQuery.copyArgumentsFrom(this.mSourceQuery);
        Cursor cursor = this.mDb.query(sqLiteQuery);
        try {
            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            }
            cursor.close();
            sqLiteQuery.release();
            return 0;
        } finally {
            cursor.close();
            sqLiteQuery.release();
        }
    }

    public boolean isInvalid() {
        this.mDb.getInvalidationTracker().refreshVersionsSync();
        return LimitOffsetDataSource.super.isInvalid();
    }

    /* JADX INFO: finally extract failed */
    public void loadInitial(LoadInitialParams params, LoadInitialCallback<T> callback) {
        List<T> list = Collections.emptyList();
        int firstLoadPosition = 0;
        RoomSQLiteQuery sqLiteQuery = null;
        Cursor cursor = null;
        this.mDb.beginTransaction();
        try {
            int totalCount = countItems();
            if (totalCount != 0) {
                firstLoadPosition = computeInitialLoadPosition(params, totalCount);
                sqLiteQuery = getSQLiteQuery(firstLoadPosition, computeInitialLoadSize(params, firstLoadPosition, totalCount));
                cursor = this.mDb.query(sqLiteQuery);
                List<T> rows = convertRows(cursor);
                this.mDb.setTransactionSuccessful();
                list = rows;
            }
            if (cursor != null) {
                cursor.close();
            }
            this.mDb.endTransaction();
            if (sqLiteQuery != null) {
                sqLiteQuery.release();
            }
            callback.onResult(list, firstLoadPosition, totalCount);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            this.mDb.endTransaction();
            if (sqLiteQuery != null) {
                sqLiteQuery.release();
            }
            throw th;
        }
    }

    public void loadRange(LoadRangeParams params, LoadRangeCallback<T> callback) {
        callback.onResult(loadRange(params.startPosition, params.loadSize));
    }

    public List<T> loadRange(int startPosition, int loadCount) {
        RoomSQLiteQuery sqLiteQuery = getSQLiteQuery(startPosition, loadCount);
        if (this.mInTransaction) {
            this.mDb.beginTransaction();
            Cursor cursor = null;
            try {
                cursor = this.mDb.query(sqLiteQuery);
                List<T> rows = convertRows(cursor);
                this.mDb.setTransactionSuccessful();
                return rows;
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                this.mDb.endTransaction();
                sqLiteQuery.release();
            }
        } else {
            Cursor cursor2 = this.mDb.query(sqLiteQuery);
            try {
                return convertRows(cursor2);
            } finally {
                cursor2.close();
                sqLiteQuery.release();
            }
        }
    }

    private RoomSQLiteQuery getSQLiteQuery(int startPosition, int loadCount) {
        RoomSQLiteQuery sqLiteQuery = RoomSQLiteQuery.acquire(this.mLimitOffsetQuery, this.mSourceQuery.getArgCount() + 2);
        sqLiteQuery.copyArgumentsFrom(this.mSourceQuery);
        sqLiteQuery.bindLong(sqLiteQuery.getArgCount() - 1, (long) loadCount);
        sqLiteQuery.bindLong(sqLiteQuery.getArgCount(), (long) startPosition);
        return sqLiteQuery;
    }
}
