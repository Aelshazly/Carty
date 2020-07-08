package androidx.room.util;

import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import androidx.room.RoomDatabase;
import androidx.sqlite.p004db.SupportSQLiteDatabase;
import androidx.sqlite.p004db.SupportSQLiteQuery;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    @Deprecated
    public static Cursor query(RoomDatabase db, SupportSQLiteQuery sqLiteQuery, boolean maybeCopy) {
        return query(db, sqLiteQuery, maybeCopy, null);
    }

    public static Cursor query(RoomDatabase db, SupportSQLiteQuery sqLiteQuery, boolean maybeCopy, CancellationSignal signal) {
        int rowsInWindow;
        Cursor cursor = db.query(sqLiteQuery, signal);
        if (maybeCopy && (cursor instanceof AbstractWindowedCursor)) {
            AbstractWindowedCursor windowedCursor = (AbstractWindowedCursor) cursor;
            int rowsInCursor = windowedCursor.getCount();
            if (windowedCursor.hasWindow()) {
                rowsInWindow = windowedCursor.getWindow().getNumRows();
            } else {
                rowsInWindow = rowsInCursor;
            }
            if (VERSION.SDK_INT < 23 || rowsInWindow < rowsInCursor) {
                return CursorUtil.copyAndClose(windowedCursor);
            }
        }
        return cursor;
    }

    /* JADX INFO: finally extract failed */
    public static void dropFtsSyncTriggers(SupportSQLiteDatabase db) {
        List<String> existingTriggers = new ArrayList<>();
        Cursor cursor = db.query("SELECT name FROM sqlite_master WHERE type = 'trigger'");
        while (cursor.moveToNext()) {
            try {
                existingTriggers.add(cursor.getString(0));
            } catch (Throwable th) {
                cursor.close();
                throw th;
            }
        }
        cursor.close();
        for (String triggerName : existingTriggers) {
            if (triggerName.startsWith("room_fts_content_sync_")) {
                StringBuilder sb = new StringBuilder();
                sb.append("DROP TRIGGER IF EXISTS ");
                sb.append(triggerName);
                db.execSQL(sb.toString());
            }
        }
    }

    public static int readVersion(File databaseFile) throws IOException {
        FileChannel input = null;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            input = new FileInputStream(databaseFile).getChannel();
            input.tryLock(60, 4, true);
            input.position(60);
            if (input.read(buffer) == 4) {
                buffer.rewind();
                return buffer.getInt();
            }
            throw new IOException("Bad database header, unable to read 4 bytes at offset 60");
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    public static CancellationSignal createCancellationSignal() {
        if (VERSION.SDK_INT >= 16) {
            return new CancellationSignal();
        }
        return null;
    }

    private DBUtil() {
    }
}
