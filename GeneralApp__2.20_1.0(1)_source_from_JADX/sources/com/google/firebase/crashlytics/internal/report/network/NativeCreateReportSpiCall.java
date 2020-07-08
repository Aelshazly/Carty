package com.google.firebase.crashlytics.internal.report.network;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.common.ResponseParser;
import com.google.firebase.crashlytics.internal.network.HttpMethod;
import com.google.firebase.crashlytics.internal.network.HttpRequest;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.report.model.CreateReportRequest;
import com.google.firebase.crashlytics.internal.report.model.Report;
import java.io.File;
import java.io.IOException;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class NativeCreateReportSpiCall extends AbstractSpiCall implements CreateReportSpiCall {
    private static final String APP_META_FILE_MULTIPART_PARAM = "app_meta_file";
    private static final String BINARY_IMAGES_FILE_MULTIPART_PARAM = "binary_images_file";
    private static final String DEVICE_META_FILE_MULTIPART_PARAM = "device_meta_file";
    private static final String GZIP_FILE_CONTENT_TYPE = "application/octet-stream";
    private static final String KEYS_FILE_MULTIPART_PARAM = "keys_file";
    private static final String LOGS_FILE_MULTIPART_PARAM = "logs_file";
    private static final String METADATA_FILE_MULTIPART_PARAM = "crash_meta_file";
    private static final String MINIDUMP_FILE_MULTIPART_PARAM = "minidump_file";
    static final String ORGANIZATION_IDENTIFIER_PARAM = "org_id";
    private static final String OS_META_FILE_MULTIPART_PARAM = "os_meta_file";
    private static final String REPORT_IDENTIFIER_PARAM = "report_id";
    private static final String SESSION_META_FILE_MULTIPART_PARAM = "session_meta_file";
    private static final String USER_META_FILE_MULTIPART_PARAM = "user_meta_file";
    private final String version;

    public NativeCreateReportSpiCall(String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, String version2) {
        super(protocolAndHostOverride, url, requestFactory, HttpMethod.POST);
        this.version = version2;
    }

    public boolean invoke(CreateReportRequest requestData, boolean dataCollectionToken) {
        if (dataCollectionToken) {
            HttpRequest httpRequest = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), requestData.googleAppId), requestData.organizationId, requestData.report);
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Sending report to: ");
            sb.append(getUrl());
            logger.mo19679d(sb.toString());
            try {
                int statusCode = httpRequest.execute().code();
                Logger logger2 = Logger.getLogger();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Result was: ");
                sb2.append(statusCode);
                logger2.mo19679d(sb2.toString());
                return ResponseParser.parse(statusCode) == 0;
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        } else {
            throw new RuntimeException("An invalid data collection token was used.");
        }
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, String googleAppId) {
        StringBuilder sb = new StringBuilder();
        sb.append(AbstractSpiCall.CRASHLYTICS_USER_AGENT);
        sb.append(CrashlyticsCore.getVersion());
        httpRequest.header("User-Agent", sb.toString()).header(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).header(AbstractSpiCall.HEADER_CLIENT_VERSION, this.version).header(AbstractSpiCall.HEADER_GOOGLE_APP_ID, googleAppId);
        return httpRequest;
    }

    private HttpRequest applyMultipartDataTo(HttpRequest httpRequest, String organizationId, Report report) {
        File[] files;
        if (organizationId != null) {
            httpRequest.part("org_id", organizationId);
        }
        httpRequest.part(REPORT_IDENTIFIER_PARAM, report.getIdentifier());
        for (File f : report.getFiles()) {
            String str = "application/octet-stream";
            if (f.getName().equals("minidump")) {
                httpRequest.part(MINIDUMP_FILE_MULTIPART_PARAM, f.getName(), str, f);
            } else if (f.getName().equals("metadata")) {
                httpRequest.part(METADATA_FILE_MULTIPART_PARAM, f.getName(), str, f);
            } else if (f.getName().equals("binaryImages")) {
                httpRequest.part(BINARY_IMAGES_FILE_MULTIPART_PARAM, f.getName(), str, f);
            } else if (f.getName().equals("session")) {
                httpRequest.part(SESSION_META_FILE_MULTIPART_PARAM, f.getName(), str, f);
            } else if (f.getName().equals("app")) {
                httpRequest.part(APP_META_FILE_MULTIPART_PARAM, f.getName(), str, f);
            } else if (f.getName().equals("device")) {
                httpRequest.part(DEVICE_META_FILE_MULTIPART_PARAM, f.getName(), str, f);
            } else if (f.getName().equals("os")) {
                httpRequest.part(OS_META_FILE_MULTIPART_PARAM, f.getName(), str, f);
            } else if (f.getName().equals("user")) {
                httpRequest.part(USER_META_FILE_MULTIPART_PARAM, f.getName(), str, f);
            } else if (f.getName().equals("logs")) {
                httpRequest.part(LOGS_FILE_MULTIPART_PARAM, f.getName(), str, f);
            } else if (f.getName().equals("keys")) {
                httpRequest.part(KEYS_FILE_MULTIPART_PARAM, f.getName(), str, f);
            }
        }
        return httpRequest;
    }
}
