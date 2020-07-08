package com.navibees.navigatorapp.data;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase.Callback;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.sqlite.p004db.SupportSQLiteDatabase;
import androidx.sqlite.p004db.SupportSQLiteOpenHelper;
import androidx.sqlite.p004db.SupportSQLiteOpenHelper.Configuration;
import com.navibees.navigatorapp.data.dao.ExhibitorDao;
import com.navibees.navigatorapp.data.dao.ExhibitorDao_Impl;
import com.navibees.navigatorapp.data.dao.SpeakersDao;
import com.navibees.navigatorapp.data.dao.SpeakersDao_Impl;
import com.navibees.navigatorapp.data.dao.SponserDao;
import com.navibees.navigatorapp.data.dao.SponserDao_Impl;
import java.util.HashMap;
import java.util.HashSet;

public final class AppDatabase_Impl extends AppDatabase {
    private volatile ExhibitorDao _exhibitorDao;
    private volatile SpeakersDao _speakersDao;
    private volatile SponserDao _sponserDao;

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new Delegate(1) {
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Exhibitor` (`id` INTEGER NOT NULL, `name` TEXT, `titleEn` TEXT, `titleAr` TEXT, `aboutEn` TEXT, `aboutAr` TEXT, `aboutFr` TEXT, `aboutDu` TEXT, `aboutUr` TEXT, `emailID` TEXT, `phone` TEXT, `website` TEXT, `place` TEXT, `boothNumber` TEXT, `navId` TEXT, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Speaker` (`id` INTEGER NOT NULL, `name` TEXT, `title` TEXT, `about` TEXT, `sessionDay` TEXT, `sessionTime` TEXT, `place` TEXT, `emailID` TEXT, `phone` TEXT, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Sponsor` (`id` INTEGER NOT NULL, `name` TEXT, `titleEn` TEXT, `titleAr` TEXT, `phone` TEXT, `website` TEXT, PRIMARY KEY(`id`))");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f6ba37656e0aa59b75e958805493c024')");
            }

            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `Exhibitor`");
                _db.execSQL("DROP TABLE IF EXISTS `Speaker`");
                _db.execSQL("DROP TABLE IF EXISTS `Sponsor`");
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int _size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((Callback) AppDatabase_Impl.this.mCallbacks.get(_i)).onDestructiveMigration(_db);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void onCreate(SupportSQLiteDatabase _db) {
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int _size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((Callback) AppDatabase_Impl.this.mCallbacks.get(_i)).onCreate(_db);
                    }
                }
            }

            public void onOpen(SupportSQLiteDatabase _db) {
                AppDatabase_Impl.this.mDatabase = _db;
                AppDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int _size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((Callback) AppDatabase_Impl.this.mCallbacks.get(_i)).onOpen(_db);
                    }
                }
            }

            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            /* access modifiers changed from: protected */
            public ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                SupportSQLiteDatabase supportSQLiteDatabase = _db;
                HashMap<String, Column> _columnsExhibitor = new HashMap<>(15);
                Column column = new Column("id", "INTEGER", true, 1, null, 1);
                String str = "id";
                _columnsExhibitor.put(str, column);
                Column column2 = new Column("name", "TEXT", false, 0, null, 1);
                String str2 = "name";
                _columnsExhibitor.put(str2, column2);
                Column column3 = new Column("titleEn", "TEXT", false, 0, null, 1);
                String str3 = "titleEn";
                _columnsExhibitor.put(str3, column3);
                Column column4 = new Column("titleAr", "TEXT", false, 0, null, 1);
                String str4 = "titleAr";
                _columnsExhibitor.put(str4, column4);
                Column column5 = new Column("aboutEn", "TEXT", false, 0, null, 1);
                _columnsExhibitor.put("aboutEn", column5);
                Column column6 = new Column("aboutAr", "TEXT", false, 0, null, 1);
                _columnsExhibitor.put("aboutAr", column6);
                Column column7 = new Column("aboutFr", "TEXT", false, 0, null, 1);
                _columnsExhibitor.put("aboutFr", column7);
                Column column8 = new Column("aboutDu", "TEXT", false, 0, null, 1);
                _columnsExhibitor.put("aboutDu", column8);
                Column column9 = new Column("aboutUr", "TEXT", false, 0, null, 1);
                _columnsExhibitor.put("aboutUr", column9);
                Column column10 = new Column("emailID", "TEXT", false, 0, null, 1);
                String str5 = "emailID";
                _columnsExhibitor.put(str5, column10);
                Column column11 = new Column("phone", "TEXT", false, 0, null, 1);
                String str6 = "phone";
                _columnsExhibitor.put(str6, column11);
                Column column12 = new Column("website", "TEXT", false, 0, null, 1);
                String str7 = "website";
                _columnsExhibitor.put(str7, column12);
                Column column13 = new Column("place", "TEXT", false, 0, null, 1);
                String str8 = "place";
                _columnsExhibitor.put(str8, column13);
                Column column14 = new Column("boothNumber", "TEXT", false, 0, null, 1);
                _columnsExhibitor.put("boothNumber", column14);
                Column column15 = new Column("navId", "TEXT", false, 0, null, 1);
                _columnsExhibitor.put("navId", column15);
                HashSet<ForeignKey> _foreignKeysExhibitor = new HashSet<>(0);
                String str9 = "Exhibitor";
                TableInfo _infoExhibitor = new TableInfo(str9, _columnsExhibitor, _foreignKeysExhibitor, new HashSet<>(0));
                TableInfo _existingExhibitor = TableInfo.read(supportSQLiteDatabase, str9);
                String str10 = "\n Found:\n";
                if (!_infoExhibitor.equals(_existingExhibitor)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Exhibitor(com.navibees.navigatorapp.models.Exhibitor).\n Expected:\n");
                    sb.append(_infoExhibitor);
                    sb.append(str10);
                    sb.append(_existingExhibitor);
                    return new ValidationResult(false, sb.toString());
                }
                HashMap hashMap = _columnsExhibitor;
                HashMap hashMap2 = new HashMap(9);
                Column column16 = new Column("id", "INTEGER", true, 1, null, 1);
                hashMap2.put(str, column16);
                Column column17 = new Column("name", "TEXT", false, 0, null, 1);
                hashMap2.put(str2, column17);
                Column column18 = new Column("title", "TEXT", false, 0, null, 1);
                HashSet hashSet = _foreignKeysExhibitor;
                hashMap2.put("title", column18);
                Column column19 = new Column("about", "TEXT", false, 0, null, 1);
                hashMap2.put("about", column19);
                Column column20 = new Column("sessionDay", "TEXT", false, 0, null, 1);
                hashMap2.put("sessionDay", column20);
                Column column21 = new Column("sessionTime", "TEXT", false, 0, null, 1);
                hashMap2.put("sessionTime", column21);
                Column column22 = new Column("place", "TEXT", false, 0, null, 1);
                hashMap2.put(str8, column22);
                Column column23 = new Column("emailID", "TEXT", false, 0, null, 1);
                hashMap2.put(str5, column23);
                Column column24 = new Column("phone", "TEXT", false, 0, null, 1);
                hashMap2.put(str6, column24);
                HashSet<ForeignKey> _foreignKeysSpeaker = new HashSet<>(0);
                String str11 = "Speaker";
                TableInfo _infoSpeaker = new TableInfo(str11, hashMap2, _foreignKeysSpeaker, new HashSet(0));
                TableInfo _existingSpeaker = TableInfo.read(supportSQLiteDatabase, str11);
                if (!_infoSpeaker.equals(_existingSpeaker)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Speaker(com.navibees.navigatorapp.models.Speaker).\n Expected:\n");
                    sb2.append(_infoSpeaker);
                    sb2.append(str10);
                    sb2.append(_existingSpeaker);
                    return new ValidationResult(false, sb2.toString());
                }
                HashMap hashMap3 = hashMap2;
                HashSet hashSet2 = _foreignKeysSpeaker;
                HashMap<String, Column> _columnsSponsor = new HashMap<>(6);
                Column column25 = new Column("id", "INTEGER", true, 1, null, 1);
                _columnsSponsor.put(str, column25);
                Column column26 = new Column("name", "TEXT", false, 0, null, 1);
                _columnsSponsor.put(str2, column26);
                Column column27 = new Column("titleEn", "TEXT", false, 0, null, 1);
                _columnsSponsor.put(str3, column27);
                Column column28 = new Column("titleAr", "TEXT", false, 0, null, 1);
                _columnsSponsor.put(str4, column28);
                Column column29 = new Column("phone", "TEXT", false, 0, null, 1);
                _columnsSponsor.put(str6, column29);
                Column column30 = new Column("website", "TEXT", false, 0, null, 1);
                _columnsSponsor.put(str7, column30);
                String str12 = "Sponsor";
                TableInfo _infoSponsor = new TableInfo(str12, _columnsSponsor, new HashSet<>(0), new HashSet(0));
                TableInfo _existingSponsor = TableInfo.read(supportSQLiteDatabase, str12);
                if (_infoSponsor.equals(_existingSponsor)) {
                    return new ValidationResult(true, null);
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Sponsor(com.navibees.navigatorapp.models.Sponsor).\n Expected:\n");
                sb3.append(_infoSponsor);
                sb3.append(str10);
                sb3.append(_existingSponsor);
                return new ValidationResult(false, sb3.toString());
            }
        }, "f6ba37656e0aa59b75e958805493c024", "9f8efd72e3401d19c52f7bd8457f4629")).build());
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap<>(0), new HashMap(0), "Exhibitor", "Speaker", "Sponsor");
    }

    public void clearAllTables() {
        String str = "VACUUM";
        String str2 = "PRAGMA wal_checkpoint(FULL)";
        super.assertNotMainThread();
        SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            _db.execSQL("DELETE FROM `Exhibitor`");
            _db.execSQL("DELETE FROM `Speaker`");
            _db.execSQL("DELETE FROM `Sponsor`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            _db.query(str2).close();
            if (!_db.inTransaction()) {
                _db.execSQL(str);
            }
        }
    }

    public ExhibitorDao exhibitorDao() {
        ExhibitorDao exhibitorDao;
        if (this._exhibitorDao != null) {
            return this._exhibitorDao;
        }
        synchronized (this) {
            if (this._exhibitorDao == null) {
                this._exhibitorDao = new ExhibitorDao_Impl(this);
            }
            exhibitorDao = this._exhibitorDao;
        }
        return exhibitorDao;
    }

    public SpeakersDao speakersDao() {
        SpeakersDao speakersDao;
        if (this._speakersDao != null) {
            return this._speakersDao;
        }
        synchronized (this) {
            if (this._speakersDao == null) {
                this._speakersDao = new SpeakersDao_Impl(this);
            }
            speakersDao = this._speakersDao;
        }
        return speakersDao;
    }

    public SponserDao sponsorDao() {
        SponserDao sponserDao;
        if (this._sponserDao != null) {
            return this._sponserDao;
        }
        synchronized (this) {
            if (this._sponserDao == null) {
                this._sponserDao = new SponserDao_Impl(this);
            }
            sponserDao = this._sponserDao;
        }
        return sponserDao;
    }
}
