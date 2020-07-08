package com.google.firebase.crashlytics.internal.send;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.Destination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class DataTransportCrashlyticsReportSender {
    private static final String CRASHLYTICS_API_KEY = mergeStrings("AzSBpY4F0rHiHFdinTvM", "IayrSTFL9eJ69YeSUO2");
    private static final String CRASHLYTICS_ENDPOINT = mergeStrings("hts/cahyiseot-agolai.o/1frlglgc/aclg", "tp:/rsltcrprsp.ogepscmv/ieo/eaybtho");
    private static final String CRASHLYTICS_TRANSPORT_NAME = "FIREBASE_CRASHLYTICS_REPORT";
    private static final Transformer<CrashlyticsReport, byte[]> DEFAULT_TRANSFORM = DataTransportCrashlyticsReportSender$$Lambda$2.lambdaFactory$();
    private static final int MAX_DATATRANSPORT_BYTES = 851968;
    private static final CrashlyticsReportJsonTransform TRANSFORM = new CrashlyticsReportJsonTransform();
    private final Transport<CrashlyticsReport> transport;
    private final Transformer<CrashlyticsReport, byte[]> transportTransform;

    public static DataTransportCrashlyticsReportSender create(Context context) {
        TransportRuntime.initialize(context);
        Encoding of = Encoding.m11of("json");
        Transformer<CrashlyticsReport, byte[]> transformer = DEFAULT_TRANSFORM;
        return new DataTransportCrashlyticsReportSender(TransportRuntime.getInstance().newFactory((Destination) new CCTDestination(CRASHLYTICS_ENDPOINT, CRASHLYTICS_API_KEY)).getTransport(CRASHLYTICS_TRANSPORT_NAME, CrashlyticsReport.class, of, transformer), DEFAULT_TRANSFORM);
    }

    DataTransportCrashlyticsReportSender(Transport<CrashlyticsReport> transport2, Transformer<CrashlyticsReport, byte[]> transportTransform2) {
        this.transport = transport2;
        this.transportTransform = transportTransform2;
    }

    public Task<CrashlyticsReportWithSessionId> sendReport(CrashlyticsReportWithSessionId reportWithSessionId) {
        CrashlyticsReport report = reportWithSessionId.getReport();
        int reportSize = ((byte[]) this.transportTransform.apply(report)).length;
        if (reportSize > MAX_DATATRANSPORT_BYTES) {
            Logger.getLogger().mo19679d(String.format("Report is too large to be sent via DataTransport. Maximum size is %d bytes. Report size is %d bytes. Removing report.", new Object[]{Integer.valueOf(MAX_DATATRANSPORT_BYTES), Integer.valueOf(reportSize)}));
            return Tasks.forResult(reportWithSessionId);
        }
        TaskCompletionSource<CrashlyticsReportWithSessionId> tcs = new TaskCompletionSource<>();
        this.transport.schedule(Event.ofUrgent(report), DataTransportCrashlyticsReportSender$$Lambda$1.lambdaFactory$(tcs, reportWithSessionId));
        return tcs.getTask();
    }

    static /* synthetic */ void lambda$sendReport$1(TaskCompletionSource tcs, CrashlyticsReportWithSessionId reportWithSessionId, Exception error) {
        if (error != null) {
            tcs.trySetException(error);
        } else {
            tcs.trySetResult(reportWithSessionId);
        }
    }

    private static String mergeStrings(String part1, String part2) {
        int sizeDiff = part1.length() - part2.length();
        if (sizeDiff < 0 || sizeDiff > 1) {
            throw new IllegalArgumentException("Invalid input received");
        }
        StringBuilder url = new StringBuilder(part1.length() + part2.length());
        for (int i = 0; i < part1.length(); i++) {
            url.append(part1.charAt(i));
            if (part2.length() > i) {
                url.append(part2.charAt(i));
            }
        }
        return url.toString();
    }
}
