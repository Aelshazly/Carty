package androidx.room;

import androidx.sqlite.p004db.SupportSQLiteOpenHelper;
import androidx.sqlite.p004db.SupportSQLiteOpenHelper.Configuration;
import androidx.sqlite.p004db.SupportSQLiteOpenHelper.Factory;
import java.io.File;

class SQLiteCopyOpenHelperFactory implements Factory {
    private final String mCopyFromAssetPath;
    private final File mCopyFromFile;
    private final Factory mDelegate;

    SQLiteCopyOpenHelperFactory(String copyFromAssetPath, File copyFromFile, Factory factory) {
        this.mCopyFromAssetPath = copyFromAssetPath;
        this.mCopyFromFile = copyFromFile;
        this.mDelegate = factory;
    }

    public SupportSQLiteOpenHelper create(Configuration configuration) {
        SQLiteCopyOpenHelper sQLiteCopyOpenHelper = new SQLiteCopyOpenHelper(configuration.context, this.mCopyFromAssetPath, this.mCopyFromFile, configuration.callback.version, this.mDelegate.create(configuration));
        return sQLiteCopyOpenHelper;
    }
}
