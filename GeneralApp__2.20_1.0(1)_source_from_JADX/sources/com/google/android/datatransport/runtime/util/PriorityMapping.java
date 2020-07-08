package com.google.android.datatransport.runtime.util;

import android.util.SparseArray;
import com.google.android.datatransport.Priority;
import java.util.EnumMap;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
public final class PriorityMapping {
    private static EnumMap<Priority, Integer> PRIORITY_INT_MAP = new EnumMap<>(Priority.class);
    private static SparseArray<Priority> PRIORITY_MAP = new SparseArray<>();

    static {
        PRIORITY_INT_MAP.put(Priority.DEFAULT, Integer.valueOf(0));
        PRIORITY_INT_MAP.put(Priority.VERY_LOW, Integer.valueOf(1));
        PRIORITY_INT_MAP.put(Priority.HIGHEST, Integer.valueOf(2));
        for (Priority p : PRIORITY_INT_MAP.keySet()) {
            PRIORITY_MAP.append(((Integer) PRIORITY_INT_MAP.get(p)).intValue(), p);
        }
    }

    public static Priority valueOf(int value) {
        Priority priority = (Priority) PRIORITY_MAP.get(value);
        if (priority != null) {
            return priority;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown Priority for value ");
        sb.append(value);
        throw new IllegalArgumentException(sb.toString());
    }

    public static int toInt(Priority priority) {
        Integer integer = (Integer) PRIORITY_INT_MAP.get(priority);
        if (integer != null) {
            return integer.intValue();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("PriorityMapping is missing known Priority value ");
        sb.append(priority);
        throw new IllegalStateException(sb.toString());
    }
}
