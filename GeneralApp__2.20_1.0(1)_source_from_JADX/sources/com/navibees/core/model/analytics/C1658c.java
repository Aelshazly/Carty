package com.navibees.core.model.analytics;

/* renamed from: com.navibees.core.model.analytics.c */
/* compiled from: RegionEventState */
public enum C1658c {
    ENTERED,
    VISITED;

    /* renamed from: com.navibees.core.model.analytics.c$a */
    /* compiled from: RegionEventState */
    static /* synthetic */ class C1659a {

        /* renamed from: a */
        static final /* synthetic */ int[] f1306a = null;

        static {
            f1306a = new int[C1658c.values().length];
            try {
                f1306a[C1658c.VISITED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1306a[C1658c.ENTERED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* renamed from: a */
    public String mo29046a() {
        int i = C1659a.f1306a[ordinal()];
        if (i != 1) {
            return i != 2 ? "" : "entered";
        }
        return "visited";
    }

    /* renamed from: a */
    public static C1658c m950a(String str) {
        if (str.equalsIgnoreCase("visited")) {
            return VISITED;
        }
        if (str.equalsIgnoreCase("entered")) {
            return ENTERED;
        }
        return ENTERED;
    }
}
