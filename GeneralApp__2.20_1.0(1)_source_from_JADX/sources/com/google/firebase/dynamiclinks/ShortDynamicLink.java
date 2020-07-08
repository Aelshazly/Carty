package com.google.firebase.dynamiclinks;

import android.net.Uri;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
public interface ShortDynamicLink {

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
    public @interface Suffix {
        public static final int SHORT = 2;
        public static final int UNGUESSABLE = 1;
    }

    /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
    public interface Warning {
        @Deprecated
        String getCode();

        String getMessage();
    }

    Uri getPreviewLink();

    Uri getShortLink();

    List<? extends Warning> getWarnings();
}
