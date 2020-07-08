package androidx.room;

import android.content.Context;
import androidx.room.RoomDatabase.Callback;
import androidx.room.RoomDatabase.JournalMode;
import androidx.room.RoomDatabase.MigrationContainer;
import androidx.sqlite.p004db.SupportSQLiteOpenHelper.Factory;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public class DatabaseConfiguration {
    public final boolean allowDestructiveMigrationOnDowngrade;
    public final boolean allowMainThreadQueries;
    public final List<Callback> callbacks;
    public final Context context;
    public final String copyFromAssetPath;
    public final File copyFromFile;
    public final JournalMode journalMode;
    private final Set<Integer> mMigrationNotRequiredFrom;
    public final MigrationContainer migrationContainer;
    public final boolean multiInstanceInvalidation;
    public final String name;
    public final Executor queryExecutor;
    public final boolean requireMigration;
    public final Factory sqliteOpenHelperFactory;
    public final Executor transactionExecutor;

    @Deprecated
    public DatabaseConfiguration(Context context2, String name2, Factory sqliteOpenHelperFactory2, MigrationContainer migrationContainer2, List<Callback> callbacks2, boolean allowMainThreadQueries2, JournalMode journalMode2, Executor queryExecutor2, boolean requireMigration2, Set<Integer> migrationNotRequiredFrom) {
        this(context2, name2, sqliteOpenHelperFactory2, migrationContainer2, callbacks2, allowMainThreadQueries2, journalMode2, queryExecutor2, queryExecutor2, false, requireMigration2, false, migrationNotRequiredFrom, null, null);
    }

    @Deprecated
    public DatabaseConfiguration(Context context2, String name2, Factory sqliteOpenHelperFactory2, MigrationContainer migrationContainer2, List<Callback> callbacks2, boolean allowMainThreadQueries2, JournalMode journalMode2, Executor queryExecutor2, Executor transactionExecutor2, boolean multiInstanceInvalidation2, boolean requireMigration2, boolean allowDestructiveMigrationOnDowngrade2, Set<Integer> migrationNotRequiredFrom) {
        this(context2, name2, sqliteOpenHelperFactory2, migrationContainer2, callbacks2, allowMainThreadQueries2, journalMode2, queryExecutor2, transactionExecutor2, multiInstanceInvalidation2, requireMigration2, allowDestructiveMigrationOnDowngrade2, migrationNotRequiredFrom, null, null);
    }

    public DatabaseConfiguration(Context context2, String name2, Factory sqliteOpenHelperFactory2, MigrationContainer migrationContainer2, List<Callback> callbacks2, boolean allowMainThreadQueries2, JournalMode journalMode2, Executor queryExecutor2, Executor transactionExecutor2, boolean multiInstanceInvalidation2, boolean requireMigration2, boolean allowDestructiveMigrationOnDowngrade2, Set<Integer> migrationNotRequiredFrom, String copyFromAssetPath2, File copyFromFile2) {
        this.sqliteOpenHelperFactory = sqliteOpenHelperFactory2;
        this.context = context2;
        this.name = name2;
        this.migrationContainer = migrationContainer2;
        this.callbacks = callbacks2;
        this.allowMainThreadQueries = allowMainThreadQueries2;
        this.journalMode = journalMode2;
        this.queryExecutor = queryExecutor2;
        this.transactionExecutor = transactionExecutor2;
        this.multiInstanceInvalidation = multiInstanceInvalidation2;
        this.requireMigration = requireMigration2;
        this.allowDestructiveMigrationOnDowngrade = allowDestructiveMigrationOnDowngrade2;
        this.mMigrationNotRequiredFrom = migrationNotRequiredFrom;
        this.copyFromAssetPath = copyFromAssetPath2;
        this.copyFromFile = copyFromFile2;
    }

    @Deprecated
    public boolean isMigrationRequiredFrom(int version) {
        return isMigrationRequired(version, version + 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        if (r3.contains(java.lang.Integer.valueOf(r6)) != false) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isMigrationRequired(int r6, int r7) {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            if (r6 <= r7) goto L_0x0006
            r2 = 1
            goto L_0x0007
        L_0x0006:
            r2 = 0
        L_0x0007:
            if (r2 == 0) goto L_0x000e
            boolean r3 = r5.allowDestructiveMigrationOnDowngrade
            if (r3 == 0) goto L_0x000e
            return r1
        L_0x000e:
            boolean r3 = r5.requireMigration
            if (r3 == 0) goto L_0x0021
            java.util.Set<java.lang.Integer> r3 = r5.mMigrationNotRequiredFrom
            if (r3 == 0) goto L_0x0020
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0021
        L_0x0020:
            goto L_0x0022
        L_0x0021:
            r0 = 0
        L_0x0022:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.DatabaseConfiguration.isMigrationRequired(int, int):boolean");
    }
}
