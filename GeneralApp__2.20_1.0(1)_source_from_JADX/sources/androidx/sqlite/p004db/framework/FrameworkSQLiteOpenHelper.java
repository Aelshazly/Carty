package androidx.sqlite.p004db.framework;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.sqlite.p004db.SupportSQLiteDatabase;
import androidx.sqlite.p004db.SupportSQLiteOpenHelper;
import androidx.sqlite.p004db.SupportSQLiteOpenHelper.Callback;

/* renamed from: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper */
class FrameworkSQLiteOpenHelper implements SupportSQLiteOpenHelper {
    private final OpenHelper mDelegate;

    /* renamed from: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper */
    static class OpenHelper extends SQLiteOpenHelper {
        final Callback mCallback;
        final FrameworkSQLiteDatabase[] mDbRef;
        private boolean mMigrated;

        OpenHelper(Context context, String name, final FrameworkSQLiteDatabase[] dbRef, final Callback callback) {
            super(context, name, null, callback.version, new DatabaseErrorHandler() {
                public void onCorruption(SQLiteDatabase dbObj) {
                    Callback.this.onCorruption(OpenHelper.getWrappedDb(dbRef, dbObj));
                }
            });
            this.mCallback = callback;
            this.mDbRef = dbRef;
        }

        /* access modifiers changed from: 0000 */
        public synchronized SupportSQLiteDatabase getWritableSupportDatabase() {
            this.mMigrated = false;
            SQLiteDatabase db = super.getWritableDatabase();
            if (this.mMigrated) {
                close();
                return getWritableSupportDatabase();
            }
            return getWrappedDb(db);
        }

        /* access modifiers changed from: 0000 */
        public synchronized SupportSQLiteDatabase getReadableSupportDatabase() {
            this.mMigrated = false;
            SQLiteDatabase db = super.getReadableDatabase();
            if (this.mMigrated) {
                close();
                return getReadableSupportDatabase();
            }
            return getWrappedDb(db);
        }

        /* access modifiers changed from: 0000 */
        public FrameworkSQLiteDatabase getWrappedDb(SQLiteDatabase sqLiteDatabase) {
            return getWrappedDb(this.mDbRef, sqLiteDatabase);
        }

        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            this.mCallback.onCreate(getWrappedDb(sqLiteDatabase));
        }

        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            this.mMigrated = true;
            this.mCallback.onUpgrade(getWrappedDb(sqLiteDatabase), oldVersion, newVersion);
        }

        public void onConfigure(SQLiteDatabase db) {
            this.mCallback.onConfigure(getWrappedDb(db));
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            this.mMigrated = true;
            this.mCallback.onDowngrade(getWrappedDb(db), oldVersion, newVersion);
        }

        public void onOpen(SQLiteDatabase db) {
            if (!this.mMigrated) {
                this.mCallback.onOpen(getWrappedDb(db));
            }
        }

        public synchronized void close() {
            super.close();
            this.mDbRef[0] = null;
        }

        static FrameworkSQLiteDatabase getWrappedDb(FrameworkSQLiteDatabase[] refHolder, SQLiteDatabase sqLiteDatabase) {
            FrameworkSQLiteDatabase dbRef = refHolder[0];
            if (dbRef == null || !dbRef.isDelegate(sqLiteDatabase)) {
                refHolder[0] = new FrameworkSQLiteDatabase(sqLiteDatabase);
            }
            return refHolder[0];
        }
    }

    FrameworkSQLiteOpenHelper(Context context, String name, Callback callback) {
        this.mDelegate = createDelegate(context, name, callback);
    }

    private OpenHelper createDelegate(Context context, String name, Callback callback) {
        return new OpenHelper(context, name, new FrameworkSQLiteDatabase[1], callback);
    }

    public String getDatabaseName() {
        return this.mDelegate.getDatabaseName();
    }

    public void setWriteAheadLoggingEnabled(boolean enabled) {
        this.mDelegate.setWriteAheadLoggingEnabled(enabled);
    }

    public SupportSQLiteDatabase getWritableDatabase() {
        return this.mDelegate.getWritableSupportDatabase();
    }

    public SupportSQLiteDatabase getReadableDatabase() {
        return this.mDelegate.getReadableSupportDatabase();
    }

    public void close() {
        this.mDelegate.close();
    }
}
