package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager.Migration;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
final /* synthetic */ class SchemaManager$$Lambda$2 implements Migration {
    private static final SchemaManager$$Lambda$2 instance = new SchemaManager$$Lambda$2();

    private SchemaManager$$Lambda$2() {
    }

    public static Migration lambdaFactory$() {
        return instance;
    }

    public void upgrade(SQLiteDatabase sQLiteDatabase) {
        SchemaManager.lambda$static$1(sQLiteDatabase);
    }
}
