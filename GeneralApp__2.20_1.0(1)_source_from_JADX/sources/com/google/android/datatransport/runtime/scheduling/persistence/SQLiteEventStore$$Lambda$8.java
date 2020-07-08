package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final /* synthetic */ class SQLiteEventStore$$Lambda$8 implements Function {
    private static final SQLiteEventStore$$Lambda$8 instance = new SQLiteEventStore$$Lambda$8();

    private SQLiteEventStore$$Lambda$8() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$getNextCallTime$4((Cursor) obj);
    }
}
