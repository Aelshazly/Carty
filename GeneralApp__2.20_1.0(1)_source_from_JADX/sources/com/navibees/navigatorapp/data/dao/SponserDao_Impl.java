package com.navibees.navigatorapp.data.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p004db.SupportSQLiteStatement;
import com.navibees.navigatorapp.models.Sponsor;
import java.util.ArrayList;
import java.util.List;

public final class SponserDao_Impl implements SponserDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Sponsor> __insertionAdapterOfSponsor;
    private final SharedSQLiteStatement __preparedStmtOfClear;

    public SponserDao_Impl(RoomDatabase __db2) {
        this.__db = __db2;
        this.__insertionAdapterOfSponsor = new EntityInsertionAdapter<Sponsor>(__db2) {
            public String createQuery() {
                return "INSERT OR ABORT INTO `Sponsor` (`id`,`name`,`titleEn`,`titleAr`,`phone`,`website`) VALUES (?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement stmt, Sponsor value) {
                stmt.bindLong(1, (long) value.f208id);
                if (value.name == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.name);
                }
                if (value.titleEn == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.titleEn);
                }
                if (value.titleAr == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.titleAr);
                }
                if (value.phone == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.phone);
                }
                if (value.website == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.website);
                }
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(__db2) {
            public String createQuery() {
                String str = "Delete FROM Sponsor";
                return "Delete FROM Sponsor";
            }
        };
    }

    public void insertAll(List<Sponsor> exhibitors) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSponsor.insert((Iterable<? extends T>) exhibitors);
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

    public List<Sponsor> getAll() {
        String str = "SELECT * FROM Sponsor";
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM Sponsor", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement, false, null);
        try {
            int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
            int _cursorIndexOfTitleAr = CursorUtil.getColumnIndexOrThrow(_cursor, "titleAr");
            int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
            int _cursorIndexOfWebsite = CursorUtil.getColumnIndexOrThrow(_cursor, "website");
            List<Sponsor> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                Sponsor _item = new Sponsor();
                _item.f208id = _cursor.getInt(_cursorIndexOfId);
                _item.name = _cursor.getString(_cursorIndexOfName);
                _item.titleEn = _cursor.getString(_cursorIndexOfTitleEn);
                _item.titleAr = _cursor.getString(_cursorIndexOfTitleAr);
                _item.phone = _cursor.getString(_cursorIndexOfPhone);
                _item.website = _cursor.getString(_cursorIndexOfWebsite);
                _result.add(_item);
            }
            return _result;
        } finally {
            _cursor.close();
            _statement.release();
        }
    }
}
