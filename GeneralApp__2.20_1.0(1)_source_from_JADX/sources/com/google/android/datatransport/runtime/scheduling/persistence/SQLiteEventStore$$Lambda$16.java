package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import java.util.Map;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final /* synthetic */ class SQLiteEventStore$$Lambda$16 implements Function {
    private final Map arg$1;

    private SQLiteEventStore$$Lambda$16(Map map) {
        this.arg$1 = map;
    }

    public static Function lambdaFactory$(Map map) {
        return new SQLiteEventStore$$Lambda$16(map);
    }

    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$loadMetadata$13(this.arg$1, (Cursor) obj);
    }
}
