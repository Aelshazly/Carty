package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final /* synthetic */ class SQLiteEventStore$$Lambda$17 implements Producer {
    private final SQLiteDatabase arg$1;

    private SQLiteEventStore$$Lambda$17(SQLiteDatabase sQLiteDatabase) {
        this.arg$1 = sQLiteDatabase;
    }

    public static Producer lambdaFactory$(SQLiteDatabase sQLiteDatabase) {
        return new SQLiteEventStore$$Lambda$17(sQLiteDatabase);
    }

    public Object produce() {
        return this.arg$1.beginTransaction();
    }
}
