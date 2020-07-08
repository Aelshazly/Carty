package com.navibees.core.model.metadata.json;

public enum RegionType {
    BACKGROUND,
    FOREGROUND,
    ALL;

    /* renamed from: com.navibees.core.model.metadata.json.RegionType$1 */
    static /* synthetic */ class C16821 {
        static final /* synthetic */ int[] $SwitchMap$com$navibees$core$model$metadata$json$RegionType = null;

        static {
            $SwitchMap$com$navibees$core$model$metadata$json$RegionType = new int[RegionType.values().length];
            try {
                $SwitchMap$com$navibees$core$model$metadata$json$RegionType[RegionType.FOREGROUND.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$navibees$core$model$metadata$json$RegionType[RegionType.BACKGROUND.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static RegionType getRegion(String str) {
        if (str.equalsIgnoreCase("BACKGROUND")) {
            return BACKGROUND;
        }
        if (str.equalsIgnoreCase("FOREGROUND")) {
            return FOREGROUND;
        }
        return ALL;
    }

    public String getString() {
        int i = C16821.$SwitchMap$com$navibees$core$model$metadata$json$RegionType[ordinal()];
        if (i != 1) {
            return i != 2 ? "ALL" : "BACKGROUND";
        }
        return "FOREGROUND";
    }
}
