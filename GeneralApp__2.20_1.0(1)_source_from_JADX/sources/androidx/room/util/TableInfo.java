package androidx.room.util;

import android.database.Cursor;
import android.os.Build.VERSION;
import androidx.sqlite.p004db.SupportSQLiteDatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TableInfo {
    public static final int CREATED_FROM_DATABASE = 2;
    public static final int CREATED_FROM_ENTITY = 1;
    public static final int CREATED_FROM_UNKNOWN = 0;
    public final Map<String, Column> columns;
    public final Set<ForeignKey> foreignKeys;
    public final Set<Index> indices;
    public final String name;

    public static class Column {
        public final int affinity;
        public final String defaultValue;
        private final int mCreatedFrom;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        @Deprecated
        public Column(String name2, String type2, boolean notNull2, int primaryKeyPosition2) {
            this(name2, type2, notNull2, primaryKeyPosition2, null, 0);
        }

        public Column(String name2, String type2, boolean notNull2, int primaryKeyPosition2, String defaultValue2, int createdFrom) {
            this.name = name2;
            this.type = type2;
            this.notNull = notNull2;
            this.primaryKeyPosition = primaryKeyPosition2;
            this.affinity = findAffinity(type2);
            this.defaultValue = defaultValue2;
            this.mCreatedFrom = createdFrom;
        }

        private static int findAffinity(String type2) {
            if (type2 == null) {
                return 5;
            }
            String uppercaseType = type2.toUpperCase(Locale.US);
            if (uppercaseType.contains("INT")) {
                return 3;
            }
            if (uppercaseType.contains("CHAR") || uppercaseType.contains("CLOB") || uppercaseType.contains("TEXT")) {
                return 2;
            }
            if (uppercaseType.contains("BLOB")) {
                return 5;
            }
            if (uppercaseType.contains("REAL") || uppercaseType.contains("FLOA") || uppercaseType.contains("DOUB")) {
                return 4;
            }
            return 1;
        }

        public boolean equals(Object o) {
            boolean z = true;
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Column column = (Column) o;
            if (VERSION.SDK_INT >= 20) {
                if (this.primaryKeyPosition != column.primaryKeyPosition) {
                    return false;
                }
            } else if (isPrimaryKey() != column.isPrimaryKey()) {
                return false;
            }
            if (!this.name.equals(column.name) || this.notNull != column.notNull) {
                return false;
            }
            if (this.mCreatedFrom == 1 && column.mCreatedFrom == 2) {
                String str = this.defaultValue;
                if (str != null && !str.equals(column.defaultValue)) {
                    return false;
                }
            }
            if (this.mCreatedFrom == 2 && column.mCreatedFrom == 1) {
                String str2 = column.defaultValue;
                if (str2 != null && !str2.equals(this.defaultValue)) {
                    return false;
                }
            }
            int i = this.mCreatedFrom;
            if (i != 0 && i == column.mCreatedFrom) {
                String str3 = this.defaultValue;
                if (str3 == null ? column.defaultValue != null : !str3.equals(column.defaultValue)) {
                    return false;
                }
            }
            if (this.affinity != column.affinity) {
                z = false;
            }
            return z;
        }

        public boolean isPrimaryKey() {
            return this.primaryKeyPosition > 0;
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.affinity) * 31) + (this.notNull ? 1231 : 1237)) * 31) + this.primaryKeyPosition;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Column{name='");
            sb.append(this.name);
            sb.append('\'');
            sb.append(", type='");
            sb.append(this.type);
            sb.append('\'');
            sb.append(", affinity='");
            sb.append(this.affinity);
            sb.append('\'');
            sb.append(", notNull=");
            sb.append(this.notNull);
            sb.append(", primaryKeyPosition=");
            sb.append(this.primaryKeyPosition);
            sb.append(", defaultValue='");
            sb.append(this.defaultValue);
            sb.append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public static class ForeignKey {
        public final List<String> columnNames;
        public final String onDelete;
        public final String onUpdate;
        public final List<String> referenceColumnNames;
        public final String referenceTable;

        public ForeignKey(String referenceTable2, String onDelete2, String onUpdate2, List<String> columnNames2, List<String> referenceColumnNames2) {
            this.referenceTable = referenceTable2;
            this.onDelete = onDelete2;
            this.onUpdate = onUpdate2;
            this.columnNames = Collections.unmodifiableList(columnNames2);
            this.referenceColumnNames = Collections.unmodifiableList(referenceColumnNames2);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ForeignKey that = (ForeignKey) o;
            if (this.referenceTable.equals(that.referenceTable) && this.onDelete.equals(that.onDelete) && this.onUpdate.equals(that.onUpdate) && this.columnNames.equals(that.columnNames)) {
                return this.referenceColumnNames.equals(that.referenceColumnNames);
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.referenceTable.hashCode() * 31) + this.onDelete.hashCode()) * 31) + this.onUpdate.hashCode()) * 31) + this.columnNames.hashCode()) * 31) + this.referenceColumnNames.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ForeignKey{referenceTable='");
            sb.append(this.referenceTable);
            sb.append('\'');
            sb.append(", onDelete='");
            sb.append(this.onDelete);
            sb.append('\'');
            sb.append(", onUpdate='");
            sb.append(this.onUpdate);
            sb.append('\'');
            sb.append(", columnNames=");
            sb.append(this.columnNames);
            sb.append(", referenceColumnNames=");
            sb.append(this.referenceColumnNames);
            sb.append('}');
            return sb.toString();
        }
    }

    static class ForeignKeyWithSequence implements Comparable<ForeignKeyWithSequence> {
        final String mFrom;
        final int mId;
        final int mSequence;
        final String mTo;

        ForeignKeyWithSequence(int id, int sequence, String from, String to) {
            this.mId = id;
            this.mSequence = sequence;
            this.mFrom = from;
            this.mTo = to;
        }

        public int compareTo(ForeignKeyWithSequence o) {
            int idCmp = this.mId - o.mId;
            if (idCmp == 0) {
                return this.mSequence - o.mSequence;
            }
            return idCmp;
        }
    }

    public static class Index {
        public static final String DEFAULT_PREFIX = "index_";
        public final List<String> columns;
        public final String name;
        public final boolean unique;

        public Index(String name2, boolean unique2, List<String> columns2) {
            this.name = name2;
            this.unique = unique2;
            this.columns = columns2;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Index index = (Index) o;
            if (this.unique != index.unique || !this.columns.equals(index.columns)) {
                return false;
            }
            String str = this.name;
            String str2 = DEFAULT_PREFIX;
            if (str.startsWith(str2)) {
                return index.name.startsWith(str2);
            }
            return this.name.equals(index.name);
        }

        public int hashCode() {
            int result;
            String str = this.name;
            String str2 = DEFAULT_PREFIX;
            if (str.startsWith(str2)) {
                result = str2.hashCode();
            } else {
                result = this.name.hashCode();
            }
            return (((result * 31) + (this.unique ? 1 : 0)) * 31) + this.columns.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Index{name='");
            sb.append(this.name);
            sb.append('\'');
            sb.append(", unique=");
            sb.append(this.unique);
            sb.append(", columns=");
            sb.append(this.columns);
            sb.append('}');
            return sb.toString();
        }
    }

    public TableInfo(String name2, Map<String, Column> columns2, Set<ForeignKey> foreignKeys2, Set<Index> indices2) {
        this.name = name2;
        this.columns = Collections.unmodifiableMap(columns2);
        this.foreignKeys = Collections.unmodifiableSet(foreignKeys2);
        this.indices = indices2 == null ? null : Collections.unmodifiableSet(indices2);
    }

    public TableInfo(String name2, Map<String, Column> columns2, Set<ForeignKey> foreignKeys2) {
        this(name2, columns2, foreignKeys2, Collections.emptySet());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableInfo tableInfo = (TableInfo) o;
        String str = this.name;
        if (str == null ? tableInfo.name != null : !str.equals(tableInfo.name)) {
            return false;
        }
        Map<String, Column> map = this.columns;
        if (map == null ? tableInfo.columns != null : !map.equals(tableInfo.columns)) {
            return false;
        }
        Set<ForeignKey> set = this.foreignKeys;
        if (set == null ? tableInfo.foreignKeys != null : !set.equals(tableInfo.foreignKeys)) {
            return false;
        }
        Set<Index> set2 = this.indices;
        if (set2 != null) {
            Set<Index> set3 = tableInfo.indices;
            if (set3 != null) {
                return set2.equals(set3);
            }
        }
        return true;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Map<String, Column> map = this.columns;
        int result = (hashCode + (map != null ? map.hashCode() : 0)) * 31;
        Set<ForeignKey> set = this.foreignKeys;
        if (set != null) {
            i = set.hashCode();
        }
        return result + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TableInfo{name='");
        sb.append(this.name);
        sb.append('\'');
        sb.append(", columns=");
        sb.append(this.columns);
        sb.append(", foreignKeys=");
        sb.append(this.foreignKeys);
        sb.append(", indices=");
        sb.append(this.indices);
        sb.append('}');
        return sb.toString();
    }

    public static TableInfo read(SupportSQLiteDatabase database, String tableName) {
        return new TableInfo(tableName, readColumns(database, tableName), readForeignKeys(database, tableName), readIndices(database, tableName));
    }

    private static Set<ForeignKey> readForeignKeys(SupportSQLiteDatabase database, String tableName) {
        int idColumnIndex;
        Set<ForeignKey> foreignKeys2 = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        sb.append("PRAGMA foreign_key_list(`");
        sb.append(tableName);
        sb.append("`)");
        Cursor cursor = database.query(sb.toString());
        try {
            int idColumnIndex2 = cursor.getColumnIndex("id");
            int seqColumnIndex = cursor.getColumnIndex("seq");
            int tableColumnIndex = cursor.getColumnIndex("table");
            int onDeleteColumnIndex = cursor.getColumnIndex("on_delete");
            int onUpdateColumnIndex = cursor.getColumnIndex("on_update");
            List<ForeignKeyWithSequence> ordered = readForeignKeyFieldMappings(cursor);
            int count = cursor.getCount();
            int position = 0;
            while (position < count) {
                cursor.moveToPosition(position);
                if (cursor.getInt(seqColumnIndex) != 0) {
                    idColumnIndex = idColumnIndex2;
                } else {
                    int id = cursor.getInt(idColumnIndex2);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (ForeignKeyWithSequence key : ordered) {
                        int idColumnIndex3 = idColumnIndex2;
                        if (key.mId == id) {
                            arrayList.add(key.mFrom);
                            arrayList2.add(key.mTo);
                        }
                        String str = tableName;
                        idColumnIndex2 = idColumnIndex3;
                    }
                    idColumnIndex = idColumnIndex2;
                    ForeignKey foreignKey = new ForeignKey(cursor.getString(tableColumnIndex), cursor.getString(onDeleteColumnIndex), cursor.getString(onUpdateColumnIndex), arrayList, arrayList2);
                    foreignKeys2.add(foreignKey);
                }
                position++;
                String str2 = tableName;
                idColumnIndex2 = idColumnIndex;
            }
            return foreignKeys2;
        } finally {
            cursor.close();
        }
    }

    private static List<ForeignKeyWithSequence> readForeignKeyFieldMappings(Cursor cursor) {
        int idColumnIndex = cursor.getColumnIndex("id");
        int seqColumnIndex = cursor.getColumnIndex("seq");
        int fromColumnIndex = cursor.getColumnIndex("from");
        int toColumnIndex = cursor.getColumnIndex("to");
        int count = cursor.getCount();
        List<ForeignKeyWithSequence> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            result.add(new ForeignKeyWithSequence(cursor.getInt(idColumnIndex), cursor.getInt(seqColumnIndex), cursor.getString(fromColumnIndex), cursor.getString(toColumnIndex)));
        }
        Collections.sort(result);
        return result;
    }

    private static Map<String, Column> readColumns(SupportSQLiteDatabase database, String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("PRAGMA table_info(`");
        sb.append(tableName);
        sb.append("`)");
        Cursor cursor = database.query(sb.toString());
        Map<String, Column> columns2 = new HashMap<>();
        try {
            if (cursor.getColumnCount() > 0) {
                int nameIndex = cursor.getColumnIndex("name");
                int typeIndex = cursor.getColumnIndex("type");
                int notNullIndex = cursor.getColumnIndex("notnull");
                int pkIndex = cursor.getColumnIndex("pk");
                int defaultValueIndex = cursor.getColumnIndex("dflt_value");
                while (cursor.moveToNext()) {
                    String name2 = cursor.getString(nameIndex);
                    int nameIndex2 = nameIndex;
                    Column column = r10;
                    Column column2 = new Column(name2, cursor.getString(typeIndex), cursor.getInt(notNullIndex) != 0, cursor.getInt(pkIndex), cursor.getString(defaultValueIndex), 2);
                    columns2.put(name2, column);
                    nameIndex = nameIndex2;
                }
            }
            return columns2;
        } finally {
            cursor.close();
        }
    }

    private static Set<Index> readIndices(SupportSQLiteDatabase database, String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("PRAGMA index_list(`");
        sb.append(tableName);
        sb.append("`)");
        Cursor cursor = database.query(sb.toString());
        try {
            int nameColumnIndex = cursor.getColumnIndex("name");
            int originColumnIndex = cursor.getColumnIndex("origin");
            int uniqueIndex = cursor.getColumnIndex("unique");
            if (!(nameColumnIndex == -1 || originColumnIndex == -1)) {
                if (uniqueIndex != -1) {
                    HashSet<Index> indices2 = new HashSet<>();
                    while (cursor.moveToNext()) {
                        if ("c".equals(cursor.getString(originColumnIndex))) {
                            String name2 = cursor.getString(nameColumnIndex);
                            boolean z = true;
                            if (cursor.getInt(uniqueIndex) != 1) {
                                z = false;
                            }
                            Index index = readIndex(database, name2, z);
                            if (index == null) {
                                cursor.close();
                                return null;
                            }
                            indices2.add(index);
                        }
                    }
                    cursor.close();
                    return indices2;
                }
            }
            return null;
        } finally {
            cursor.close();
        }
    }

    private static Index readIndex(SupportSQLiteDatabase database, String name2, boolean unique) {
        StringBuilder sb = new StringBuilder();
        sb.append("PRAGMA index_xinfo(`");
        sb.append(name2);
        sb.append("`)");
        Cursor cursor = database.query(sb.toString());
        try {
            int seqnoColumnIndex = cursor.getColumnIndex("seqno");
            int cidColumnIndex = cursor.getColumnIndex("cid");
            int nameColumnIndex = cursor.getColumnIndex("name");
            if (!(seqnoColumnIndex == -1 || cidColumnIndex == -1)) {
                if (nameColumnIndex != -1) {
                    TreeMap<Integer, String> results = new TreeMap<>();
                    while (cursor.moveToNext()) {
                        if (cursor.getInt(cidColumnIndex) >= 0) {
                            int seq = cursor.getInt(seqnoColumnIndex);
                            results.put(Integer.valueOf(seq), cursor.getString(nameColumnIndex));
                        }
                    }
                    List<String> columns2 = new ArrayList<>(results.size());
                    columns2.addAll(results.values());
                    Index index = new Index(name2, unique, columns2);
                    cursor.close();
                    return index;
                }
            }
            return null;
        } finally {
            cursor.close();
        }
    }
}
