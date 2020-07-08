package com.google.firebase.crashlytics.internal.report.network;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.firebase.crashlytics.internal.common.ResponseParser;
import com.google.firebase.crashlytics.internal.network.HttpMethod;
import com.google.firebase.crashlytics.internal.network.HttpRequest;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.network.HttpResponse;
import com.google.firebase.crashlytics.internal.report.model.CreateReportRequest;
import com.google.firebase.crashlytics.internal.report.model.Report;
import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class DefaultCreateReportSpiCall extends AbstractSpiCall implements CreateReportSpiCall {
    static final String FILE_CONTENT_TYPE = "application/octet-stream";
    static final String FILE_PARAM = "report[file]";
    static final String IDENTIFIER_PARAM = "report[identifier]";
    static final String MULTI_FILE_PARAM = "report[file";
    private final String version;

    public DefaultCreateReportSpiCall(String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, String version2) {
        this(protocolAndHostOverride, url, requestFactory, HttpMethod.POST, version2);
    }

    DefaultCreateReportSpiCall(String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, HttpMethod method, String version2) {
        super(protocolAndHostOverride, url, requestFactory, method);
        this.version = version2;
    }

    public boolean invoke(CreateReportRequest requestData, boolean dataCollectionToken) {
        if (dataCollectionToken) {
            HttpRequest httpRequest = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), requestData), requestData.report);
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Sending report to: ");
            sb.append(getUrl());
            logger.mo19679d(sb.toString());
            try {
                HttpResponse httpResponse = httpRequest.execute();
                int statusCode = httpResponse.code();
                Logger logger2 = Logger.getLogger();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Create report request ID: ");
                sb2.append(httpResponse.header(AbstractSpiCall.HEADER_REQUEST_ID));
                logger2.mo19679d(sb2.toString());
                Logger logger3 = Logger.getLogger();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Result was: ");
                sb3.append(statusCode);
                logger3.mo19679d(sb3.toString());
                return ResponseParser.parse(statusCode) == 0;
            } catch (IOException ioe) {
                Logger.getLogger().mo19682e("Create report HTTP request failed.", ioe);
                throw new RuntimeException(ioe);
            }
        } else {
            throw new RuntimeException("An invalid data collection token was used.");
        }
    }

    private HttpRequest applyHeadersTo(HttpRequest request, CreateReportRequest requestData) {
        HttpRequest request2 = request.header(AbstractSpiCall.HEADER_GOOGLE_APP_ID, requestData.googleAppId).header(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).header(AbstractSpiCall.HEADER_CLIENT_VERSION, this.version);
        for (Entry<String, String> entry : requestData.report.getCustomHeaders().entrySet()) {
            request2 = request2.header(entry);
        }
        return request2;
    }

    private HttpRequest applyMultipartDataTo(HttpRequest request, Report report) {
        File[] files;
        HttpRequest request2 = request.part(IDENTIFIER_PARAM, report.getIdentifier());
        String str = "application/octet-stream";
        String str2 = " to report ";
        if (report.getFiles().length == 1) {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Adding single file ");
            sb.append(report.getFileName());
            sb.append(str2);
            sb.append(report.getIdentifier());
            logger.mo19679d(sb.toString());
            return request2.part(FILE_PARAM, report.getFileName(), str, report.getFile());
        }
        int i = 0;
        for (File file : report.getFiles()) {
            Logger logger2 = Logger.getLogger();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Adding file ");
            sb2.append(file.getName());
            sb2.append(str2);
            sb2.append(report.getIdentifier());
            logger2.mo19679d(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(MULTI_FILE_PARAM);
            sb3.append(i);
            sb3.append("]");
            request2 = request2.part(sb3.toString(), file.getName(), str, file);
            i++;
        }
        return request2;
    }
}
