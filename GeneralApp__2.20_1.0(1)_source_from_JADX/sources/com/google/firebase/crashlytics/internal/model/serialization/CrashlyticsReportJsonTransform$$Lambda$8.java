package com.google.firebase.crashlytics.internal.model.serialization;

import android.util.JsonReader;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final /* synthetic */ class CrashlyticsReportJsonTransform$$Lambda$8 implements ObjectParser {
    private static final CrashlyticsReportJsonTransform$$Lambda$8 instance = new CrashlyticsReportJsonTransform$$Lambda$8();

    private CrashlyticsReportJsonTransform$$Lambda$8() {
    }

    public static ObjectParser lambdaFactory$() {
        return instance;
    }

    public Object parse(JsonReader jsonReader) {
        return CrashlyticsReportJsonTransform.parseEventFrame(jsonReader);
    }
}
