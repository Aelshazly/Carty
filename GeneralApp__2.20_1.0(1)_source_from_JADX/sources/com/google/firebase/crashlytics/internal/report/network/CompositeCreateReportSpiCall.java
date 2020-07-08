package com.google.firebase.crashlytics.internal.report.network;

import com.google.firebase.crashlytics.internal.report.model.CreateReportRequest;
import com.google.firebase.crashlytics.internal.report.model.Report.Type;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class CompositeCreateReportSpiCall implements CreateReportSpiCall {
    private final DefaultCreateReportSpiCall javaReportSpiCall;
    private final NativeCreateReportSpiCall nativeReportSpiCall;

    /* renamed from: com.google.firebase.crashlytics.internal.report.network.CompositeCreateReportSpiCall$1 */
    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static /* synthetic */ class C08121 {

        /* renamed from: $SwitchMap$com$google$firebase$crashlytics$internal$report$model$Report$Type */
        static final /* synthetic */ int[] f82xc221d5a6 = new int[Type.values().length];

        static {
            try {
                f82xc221d5a6[Type.JAVA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f82xc221d5a6[Type.NATIVE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public CompositeCreateReportSpiCall(DefaultCreateReportSpiCall javaReportSpiCall2, NativeCreateReportSpiCall nativeReportSpiCall2) {
        this.javaReportSpiCall = javaReportSpiCall2;
        this.nativeReportSpiCall = nativeReportSpiCall2;
    }

    public boolean invoke(CreateReportRequest requestData, boolean dataCollectionToken) {
        int i = C08121.f82xc221d5a6[requestData.report.getType().ordinal()];
        if (i == 1) {
            this.javaReportSpiCall.invoke(requestData, dataCollectionToken);
            return true;
        } else if (i != 2) {
            return false;
        } else {
            this.nativeReportSpiCall.invoke(requestData, dataCollectionToken);
            return true;
        }
    }
}
