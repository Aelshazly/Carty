package androidx.room;

import android.content.Context;
import androidx.room.RoomDatabase.Builder;

public class Room {
    private static final String CURSOR_CONV_SUFFIX = "_CursorConverter";
    static final String LOG_TAG = "ROOM";
    public static final String MASTER_TABLE_NAME = "room_master_table";

    public static <T extends RoomDatabase> Builder<T> databaseBuilder(Context context, Class<T> klass, String name) {
        if (name != null && name.trim().length() != 0) {
            return new Builder<>(context, klass, name);
        }
        throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
    }

    public static <T extends RoomDatabase> Builder<T> inMemoryDatabaseBuilder(Context context, Class<T> klass) {
        return new Builder<>(context, klass, null);
    }

    static <T, C> T getGeneratedImplementation(Class<C> klass, String suffix) {
        String postPackageName;
        String str;
        String fullPackage = klass.getPackage().getName();
        String name = klass.getCanonicalName();
        if (fullPackage.isEmpty()) {
            postPackageName = name;
        } else {
            postPackageName = name.substring(fullPackage.length() + 1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(postPackageName.replace('.', '_'));
        sb.append(suffix);
        String implName = sb.toString();
        try {
            if (fullPackage.isEmpty()) {
                str = implName;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(fullPackage);
                sb2.append(".");
                sb2.append(implName);
                str = sb2.toString();
            }
            return Class.forName(str).newInstance();
        } catch (ClassNotFoundException e) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("cannot find implementation for ");
            sb3.append(klass.getCanonicalName());
            sb3.append(". ");
            sb3.append(implName);
            sb3.append(" does not exist");
            throw new RuntimeException(sb3.toString());
        } catch (IllegalAccessException e2) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Cannot access the constructor");
            sb4.append(klass.getCanonicalName());
            throw new RuntimeException(sb4.toString());
        } catch (InstantiationException e3) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Failed to create an instance of ");
            sb5.append(klass.getCanonicalName());
            throw new RuntimeException(sb5.toString());
        }
    }
}
