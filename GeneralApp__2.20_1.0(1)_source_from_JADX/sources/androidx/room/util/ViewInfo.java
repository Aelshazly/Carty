package androidx.room.util;

import android.database.Cursor;
import androidx.sqlite.p004db.SupportSQLiteDatabase;

public class ViewInfo {
    public final String name;
    public final String sql;

    public ViewInfo(String name2, String sql2) {
        this.name = name2;
        this.sql = sql2;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ViewInfo viewInfo = (ViewInfo) o;
        String str = this.name;
        if (str == null ? viewInfo.name == null : str.equals(viewInfo.name)) {
            String str2 = this.sql;
            if (str2 == null) {
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.sql;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ViewInfo{name='");
        sb.append(this.name);
        sb.append('\'');
        sb.append(", sql='");
        sb.append(this.sql);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static ViewInfo read(SupportSQLiteDatabase database, String viewName) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT name, sql FROM sqlite_master WHERE type = 'view' AND name = '");
        sb.append(viewName);
        sb.append("'");
        Cursor cursor = database.query(sb.toString());
        try {
            if (cursor.moveToFirst()) {
                return new ViewInfo(cursor.getString(0), cursor.getString(1));
            }
            ViewInfo viewInfo = new ViewInfo(viewName, null);
            cursor.close();
            return viewInfo;
        } finally {
            cursor.close();
        }
    }
}
