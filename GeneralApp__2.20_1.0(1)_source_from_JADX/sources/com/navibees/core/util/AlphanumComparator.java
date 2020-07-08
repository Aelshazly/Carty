package com.navibees.core.util;

import java.util.Comparator;

public class AlphanumComparator implements Comparator {
    /* renamed from: a */
    private final String m1164a(String str, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        char charAt = str.charAt(i2);
        sb.append(charAt);
        int i3 = i2 + 1;
        if (m1165a(charAt)) {
            while (i3 < i) {
                char charAt2 = str.charAt(i3);
                if (!m1165a(charAt2)) {
                    break;
                }
                sb.append(charAt2);
                i3++;
            }
        } else {
            while (i3 < i) {
                char charAt3 = str.charAt(i3);
                if (m1165a(charAt3)) {
                    break;
                }
                sb.append(charAt3);
                i3++;
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    private final boolean m1165a(char c) {
        return (c >= '0' && c <= '9') || (c >= 1632 && c <= 1641);
    }

    public int compare(Object obj, Object obj2) {
        int i;
        if (!(obj instanceof String) || !(obj2 instanceof String)) {
            return 0;
        }
        String str = (String) obj;
        String str2 = (String) obj2;
        int length = str.length();
        int length2 = str2.length();
        int i2 = 0;
        int i3 = 0;
        while (i2 < length && i3 < length2) {
            String a = m1164a(str, length, i2);
            i2 += a.length();
            String a2 = m1164a(str2, length2, i3);
            i3 += a2.length();
            if (!m1165a(a.charAt(0)) || !m1165a(a2.charAt(0))) {
                i = a.compareTo(a2);
                continue;
            } else {
                int length3 = a.length();
                i = length3 - a2.length();
                if (i == 0) {
                    int i4 = i;
                    for (int i5 = 0; i5 < length3; i5++) {
                        i4 = a.charAt(i5) - a2.charAt(i5);
                        if (i4 != 0) {
                            return i4;
                        }
                    }
                    i = i4;
                    continue;
                } else {
                    continue;
                }
            }
            if (i != 0) {
                return i;
            }
        }
        return length - length2;
    }
}
