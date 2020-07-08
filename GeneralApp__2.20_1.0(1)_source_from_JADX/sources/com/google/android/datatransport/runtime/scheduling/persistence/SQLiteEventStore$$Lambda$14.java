package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final /* synthetic */ class SQLiteEventStore$$Lambda$14 implements Function {
    private static final SQLiteEventStore$$Lambda$14 instance = new SQLiteEventStore$$Lambda$14();

    private SQLiteEventStore$$Lambda$14() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$clearDb$11((SQLiteDatabase) obj);
    }
}
