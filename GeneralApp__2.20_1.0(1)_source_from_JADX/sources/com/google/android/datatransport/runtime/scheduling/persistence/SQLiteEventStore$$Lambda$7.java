package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final /* synthetic */ class SQLiteEventStore$$Lambda$7 implements Function {
    private final String arg$1;

    private SQLiteEventStore$$Lambda$7(String str) {
        this.arg$1 = str;
    }

    public static Function lambdaFactory$(String str) {
        return new SQLiteEventStore$$Lambda$7(str);
    }

    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$recordFailure$3(this.arg$1, (SQLiteDatabase) obj);
    }
}
