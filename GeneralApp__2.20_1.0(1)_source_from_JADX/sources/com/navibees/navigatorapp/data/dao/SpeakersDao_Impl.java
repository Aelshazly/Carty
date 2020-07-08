package com.navibees.navigatorapp.data.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p004db.SupportSQLiteStatement;
import com.navibees.navigatorapp.models.Speaker;
import java.util.ArrayList;
import java.util.List;

public final class SpeakersDao_Impl implements SpeakersDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Speaker> __insertionAdapterOfSpeaker;
    private final SharedSQLiteStatement __preparedStmtOfClear;

    public SpeakersDao_Impl(RoomDatabase __db2) {
        this.__db = __db2;
        this.__insertionAdapterOfSpeaker = new EntityInsertionAdapter<Speaker>(__db2) {
            public String createQuery() {
                return "INSERT OR ABORT INTO `Speaker` (`id`,`name`,`title`,`about`,`sessionDay`,`sessionTime`,`place`,`emailID`,`phone`) VALUES (?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement stmt, Speaker value) {
                stmt.bindLong(1, (long) value.f207id);
                if (value.name == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.name);
                }
                if (value.title == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.title);
                }
                if (value.about == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.about);
                }
                if (value.sessionDay == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.sessionDay);
                }
                if (value.sessionTime == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.sessionTime);
                }
                if (value.place == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.place);
                }
                if (value.emailID == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.emailID);
                }
                if (value.phone == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.phone);
                }
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(__db2) {
            public String createQuery() {
                String str = "Delete FROM Speaker";
                return "Delete FROM Speaker";
            }
        };
    }

    public void insertAll(List<Speaker> exhibitors) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSpeaker.insert((Iterable<? extends T>) exhibitors);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void clear() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement _stmt = this.__preparedStmtOfClear.acquire();
        this.__db.beginTransaction();
        try {
            _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfClear.release(_stmt);
        }
    }

    public List<Speaker> getAll() {
        String str = "SELECT * FROM Speaker";
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM Speaker", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement, false, null);
        try {
            int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            int _cursorIndexOfAbout = CursorUtil.getColumnIndexOrThrow(_cursor, "about");
            int _cursorIndexOfSessionDay = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionDay");
            int _cursorIndexOfSessionTime = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionTime");
            int _cursorIndexOfPlace = CursorUtil.getColumnIndexOrThrow(_cursor, "place");
            int _cursorIndexOfEmailID = CursorUtil.getColumnIndexOrThrow(_cursor, "emailID");
            int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
            List<Speaker> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                Speaker _item = new Speaker();
                _item.f207id = _cursor.getInt(_cursorIndexOfId);
                _item.name = _cursor.getString(_cursorIndexOfName);
                _item.title = _cursor.getString(_cursorIndexOfTitle);
                _item.about = _cursor.getString(_cursorIndexOfAbout);
                _item.sessionDay = _cursor.getString(_cursorIndexOfSessionDay);
                _item.sessionTime = _cursor.getString(_cursorIndexOfSessionTime);
                _item.place = _cursor.getString(_cursorIndexOfPlace);
                _item.emailID = _cursor.getString(_cursorIndexOfEmailID);
                _item.phone = _cursor.getString(_cursorIndexOfPhone);
                _result.add(_item);
            }
            return _result;
        } finally {
            _cursor.close();
            _statement.release();
        }
    }
}
