package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.network.HttpMethod;
import com.google.firebase.crashlytics.internal.network.HttpRequest;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public abstract class AbstractSpiCall {
    public static final String ACCEPT_JSON_VALUE = "application/json";
    public static final String ANDROID_CLIENT_TYPE = "android";
    public static final String CRASHLYTICS_USER_AGENT = "Crashlytics Android SDK/";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_CLIENT_TYPE = "X-CRASHLYTICS-API-CLIENT-TYPE";
    public static final String HEADER_CLIENT_VERSION = "X-CRASHLYTICS-API-CLIENT-VERSION";
    public static final String HEADER_DEVELOPER_TOKEN = "X-CRASHLYTICS-DEVELOPER-TOKEN";
    public static final String HEADER_GOOGLE_APP_ID = "X-CRASHLYTICS-GOOGLE-APP-ID";
    public static final String HEADER_ORG_ID = "X-CRASHLYTICS-ORG-ID";
    public static final String HEADER_REQUEST_ID = "X-REQUEST-ID";
    public static final String HEADER_USER_AGENT = "User-Agent";
    private static final Pattern PROTOCOL_AND_HOST_PATTERN = Pattern.compile("http(s?)://[^\\/]+", 2);
    private final HttpMethod method;
    private final String protocolAndHostOverride;
    private final HttpRequestFactory requestFactory;
    private final String url;

    public AbstractSpiCall(String protocolAndHostOverride2, String url2, HttpRequestFactory requestFactory2, HttpMethod method2) {
        if (url2 == null) {
            throw new IllegalArgumentException("url must not be null.");
        } else if (requestFactory2 != null) {
            this.protocolAndHostOverride = protocolAndHostOverride2;
            this.url = overrideProtocolAndHost(url2);
            this.requestFactory = requestFactory2;
            this.method = method2;
        } else {
            throw new IllegalArgumentException("requestFactory must not be null.");
        }
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        return this.url;
    }

    /* access modifiers changed from: protected */
    public HttpRequest getHttpRequest() {
        return getHttpRequest(Collections.emptyMap());
    }

    /* access modifiers changed from: protected */
    public HttpRequest getHttpRequest(Map<String, String> queryParams) {
        HttpRequest httpRequest = this.requestFactory.buildHttpRequest(this.method, getUrl(), queryParams);
        StringBuilder sb = new StringBuilder();
        sb.append(CRASHLYTICS_USER_AGENT);
        sb.append(CrashlyticsCore.getVersion());
        return httpRequest.header("User-Agent", sb.toString()).header(HEADER_DEVELOPER_TOKEN, "470fa2b4ae81cd56ecbcda9735803434cec591fa");
    }

    private String overrideProtocolAndHost(String url2) {
        String toReturn = url2;
        if (!CommonUtils.isNullOrEmpty(this.protocolAndHostOverride)) {
            return PROTOCOL_AND_HOST_PATTERN.matcher(url2).replaceFirst(this.protocolAndHostOverride);
        }
        return toReturn;
    }
}
