package com.google.firebase.crashlytics.internal.report.network;

import com.google.firebase.crashlytics.internal.report.model.CreateReportRequest;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public interface CreateReportSpiCall {
    boolean invoke(CreateReportRequest createReportRequest, boolean z);
}
