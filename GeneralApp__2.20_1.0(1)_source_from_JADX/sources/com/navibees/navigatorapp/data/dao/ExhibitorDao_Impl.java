package com.navibees.navigatorapp.data.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p004db.SupportSQLiteStatement;
import com.navibees.navigatorapp.models.Exhibitor;
import java.util.ArrayList;
import java.util.List;

public final class ExhibitorDao_Impl implements ExhibitorDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Exhibitor> __insertionAdapterOfExhibitor;
    private final SharedSQLiteStatement __preparedStmtOfClear;

    public ExhibitorDao_Impl(RoomDatabase __db2) {
        this.__db = __db2;
        this.__insertionAdapterOfExhibitor = new EntityInsertionAdapter<Exhibitor>(__db2) {
            public String createQuery() {
                return "INSERT OR ABORT INTO `Exhibitor` (`id`,`name`,`titleEn`,`titleAr`,`aboutEn`,`aboutAr`,`aboutFr`,`aboutDu`,`aboutUr`,`emailID`,`phone`,`website`,`place`,`boothNumber`,`navId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement stmt, Exhibitor value) {
                stmt.bindLong(1, (long) value.f205id);
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
                if (value.aboutEn == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.aboutEn);
                }
                if (value.aboutAr == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.aboutAr);
                }
                if (value.aboutFr == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.aboutFr);
                }
                if (value.aboutDu == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.aboutDu);
                }
                if (value.aboutUr == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.aboutUr);
                }
                if (value.emailID == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.emailID);
                }
                if (value.phone == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.phone);
                }
                if (value.website == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.website);
                }
                if (value.place == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindString(13, value.place);
                }
                if (value.boothNumber == null) {
                    stmt.bindNull(14);
                } else {
                    stmt.bindString(14, value.boothNumber);
                }
                if (value.navId == null) {
                    stmt.bindNull(15);
                } else {
                    stmt.bindString(15, value.navId);
                }
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(__db2) {
            public String createQuery() {
                String str = "Delete FROM Exhibitor";
                return "Delete FROM Exhibitor";
            }
        };
    }

    public void insertAll(List<Exhibitor> exhibitors) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfExhibitor.insert((Iterable<? extends T>) exhibitors);
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

    public List<Exhibitor> getAll() {
        RoomSQLiteQuery _statement;
        int _cursorIndexOfBoothNumber;
        String _sql = "SELECT * FROM Exhibitor";
        RoomSQLiteQuery _statement2 = RoomSQLiteQuery.acquire("SELECT * FROM Exhibitor", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement2, false, null);
        try {
            int _cursorIndexOfNavId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
            int _cursorIndexOfTitleAr = CursorUtil.getColumnIndexOrThrow(_cursor, "titleAr");
            int _cursorIndexOfAboutEn = CursorUtil.getColumnIndexOrThrow(_cursor, "aboutEn");
            int _cursorIndexOfAboutAr = CursorUtil.getColumnIndexOrThrow(_cursor, "aboutAr");
            int _cursorIndexOfAboutFr = CursorUtil.getColumnIndexOrThrow(_cursor, "aboutFr");
            int _cursorIndexOfAboutDu = CursorUtil.getColumnIndexOrThrow(_cursor, "aboutDu");
            int _cursorIndexOfAboutUr = CursorUtil.getColumnIndexOrThrow(_cursor, "aboutUr");
            int _cursorIndexOfEmailID = CursorUtil.getColumnIndexOrThrow(_cursor, "emailID");
            int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
            int _cursorIndexOfWebsite = CursorUtil.getColumnIndexOrThrow(_cursor, "website");
            int _cursorIndexOfPlace = CursorUtil.getColumnIndexOrThrow(_cursor, "place");
            String str = _sql;
            try {
                _cursorIndexOfBoothNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "boothNumber");
                _statement = _statement2;
            } catch (Throwable th) {
                th = th;
                _statement = _statement2;
                _cursor.close();
                _statement.release();
                throw th;
            }
            try {
                int _cursorIndexOfPlace2 = CursorUtil.getColumnIndexOrThrow(_cursor, "navId");
                int _cursorIndexOfId = _cursorIndexOfBoothNumber;
                List<Exhibitor> arrayList = new ArrayList<>(_cursor.getCount());
                while (_cursor.moveToNext()) {
                    Exhibitor _item = new Exhibitor();
                    ArrayList arrayList2 = arrayList;
                    _item.f205id = _cursor.getInt(_cursorIndexOfNavId);
                    _item.name = _cursor.getString(_cursorIndexOfName);
                    _item.titleEn = _cursor.getString(_cursorIndexOfTitleEn);
                    _item.titleAr = _cursor.getString(_cursorIndexOfTitleAr);
                    _item.aboutEn = _cursor.getString(_cursorIndexOfAboutEn);
                    _item.aboutAr = _cursor.getString(_cursorIndexOfAboutAr);
                    _item.aboutFr = _cursor.getString(_cursorIndexOfAboutFr);
                    _item.aboutDu = _cursor.getString(_cursorIndexOfAboutDu);
                    _item.aboutUr = _cursor.getString(_cursorIndexOfAboutUr);
                    _item.emailID = _cursor.getString(_cursorIndexOfEmailID);
                    _item.phone = _cursor.getString(_cursorIndexOfPhone);
                    _item.website = _cursor.getString(_cursorIndexOfWebsite);
                    _item.place = _cursor.getString(_cursorIndexOfPlace);
                    int _cursorIndexOfBoothNumber2 = _cursorIndexOfId;
                    int _cursorIndexOfBoothNumber3 = _cursorIndexOfNavId;
                    _item.boothNumber = _cursor.getString(_cursorIndexOfBoothNumber2);
                    int _cursorIndexOfNavId2 = _cursorIndexOfPlace2;
                    int _cursorIndexOfNavId3 = _cursorIndexOfPlace;
                    _item.navId = _cursor.getString(_cursorIndexOfNavId2);
                    ArrayList arrayList3 = arrayList2;
                    arrayList3.add(_item);
                    int i = _cursorIndexOfNavId3;
                    _cursorIndexOfPlace2 = _cursorIndexOfNavId2;
                    _cursorIndexOfNavId = _cursorIndexOfBoothNumber3;
                    _cursorIndexOfId = _cursorIndexOfBoothNumber2;
                    arrayList = arrayList3;
                    _cursorIndexOfPlace = i;
                }
                int i2 = _cursorIndexOfId;
                int _cursorIndexOfBoothNumber4 = _cursorIndexOfNavId;
                int _cursorIndexOfId2 = _cursorIndexOfPlace2;
                int _cursorIndexOfNavId4 = _cursorIndexOfPlace;
                List<Exhibitor> _result = arrayList;
                _cursor.close();
                _statement.release();
                return _result;
            } catch (Throwable th2) {
                th = th2;
                _cursor.close();
                _statement.release();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            String str2 = _sql;
            _statement = _statement2;
            _cursor.close();
            _statement.release();
            throw th;
        }
    }
}
