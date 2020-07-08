package com.google.firebase.crashlytics.internal.model.serialization;

import android.util.JsonReader;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final /* synthetic */ class CrashlyticsReportJsonTransform$$Lambda$6 implements ObjectParser {
    private static final CrashlyticsReportJsonTransform$$Lambda$6 instance = new CrashlyticsReportJsonTransform$$Lambda$6();

    private CrashlyticsReportJsonTransform$$Lambda$6() {
    }

    public static ObjectParser lambdaFactory$() {
        return instance;
    }

    public Object parse(JsonReader jsonReader) {
        return CrashlyticsReportJsonTransform.parseEventThread(jsonReader);
    }
}
