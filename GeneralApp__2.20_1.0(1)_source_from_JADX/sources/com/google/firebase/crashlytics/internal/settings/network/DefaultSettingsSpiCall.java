package com.google.firebase.crashlytics.internal.settings.network;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.network.HttpMethod;
import com.google.firebase.crashlytics.internal.network.HttpRequest;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.network.HttpResponse;
import com.google.firebase.crashlytics.internal.settings.model.SettingsRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    static final String BUILD_VERSION_PARAM = "build_version";
    static final String DISPLAY_VERSION_PARAM = "display_version";
    static final String HEADER_DEVICE_MODEL = "X-CRASHLYTICS-DEVICE-MODEL";
    static final String HEADER_INSTALLATION_ID = "X-CRASHLYTICS-INSTALLATION-ID";
    static final String HEADER_OS_BUILD_VERSION = "X-CRASHLYTICS-OS-BUILD-VERSION";
    static final String HEADER_OS_DISPLAY_VERSION = "X-CRASHLYTICS-OS-DISPLAY-VERSION";
    static final String INSTANCE_PARAM = "instance";
    static final String SOURCE_PARAM = "source";
    private Logger logger;

    public DefaultSettingsSpiCall(String protocolAndHostOverride, String url, HttpRequestFactory requestFactory) {
        this(protocolAndHostOverride, url, requestFactory, HttpMethod.GET, Logger.getLogger());
    }

    DefaultSettingsSpiCall(String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, HttpMethod method, Logger logger2) {
        super(protocolAndHostOverride, url, requestFactory, method);
        this.logger = logger2;
    }

    public JSONObject invoke(SettingsRequest requestData, boolean dataCollectionToken) {
        if (dataCollectionToken) {
            try {
                Map<String, String> queryParams = getQueryParamsFor(requestData);
                HttpRequest httpRequest = applyHeadersTo(getHttpRequest(queryParams), requestData);
                Logger logger2 = this.logger;
                StringBuilder sb = new StringBuilder();
                sb.append("Requesting settings from ");
                sb.append(getUrl());
                logger2.mo19679d(sb.toString());
                Logger logger3 = this.logger;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Settings query params were: ");
                sb2.append(queryParams);
                logger3.mo19679d(sb2.toString());
                HttpResponse httpResponse = httpRequest.execute();
                Logger logger4 = this.logger;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Settings request ID: ");
                sb3.append(httpResponse.header(AbstractSpiCall.HEADER_REQUEST_ID));
                logger4.mo19679d(sb3.toString());
                return handleResponse(httpResponse);
            } catch (IOException e) {
                this.logger.mo19682e("Settings request failed.", e);
                return null;
            }
        } else {
            throw new RuntimeException("An invalid data collection token was used.");
        }
    }

    /* access modifiers changed from: 0000 */
    public JSONObject handleResponse(HttpResponse httpResponse) {
        int statusCode = httpResponse.code();
        Logger logger2 = this.logger;
        StringBuilder sb = new StringBuilder();
        sb.append("Settings result was: ");
        sb.append(statusCode);
        logger2.mo19679d(sb.toString());
        if (requestWasSuccessful(statusCode)) {
            return getJsonObjectFrom(httpResponse.body());
        }
        Logger logger3 = this.logger;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Failed to retrieve settings from ");
        sb2.append(getUrl());
        logger3.mo19681e(sb2.toString());
        return null;
    }

    /* access modifiers changed from: 0000 */
    public boolean requestWasSuccessful(int httpStatusCode) {
        return httpStatusCode == 200 || httpStatusCode == 201 || httpStatusCode == 202 || httpStatusCode == 203;
    }

    private JSONObject getJsonObjectFrom(String httpRequestBody) {
        try {
            return new JSONObject(httpRequestBody);
        } catch (Exception e) {
            Logger logger2 = this.logger;
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to parse settings JSON from ");
            sb.append(getUrl());
            logger2.mo19680d(sb.toString(), e);
            Logger logger3 = this.logger;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Settings response ");
            sb2.append(httpRequestBody);
            logger3.mo19679d(sb2.toString());
            return null;
        }
    }

    private Map<String, String> getQueryParamsFor(SettingsRequest requestData) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(BUILD_VERSION_PARAM, requestData.buildVersion);
        queryParams.put(DISPLAY_VERSION_PARAM, requestData.displayVersion);
        queryParams.put("source", Integer.toString(requestData.source));
        String instanceId = requestData.instanceId;
        if (!CommonUtils.isNullOrEmpty(instanceId)) {
            queryParams.put(INSTANCE_PARAM, instanceId);
        }
        return queryParams;
    }

    private HttpRequest applyHeadersTo(HttpRequest request, SettingsRequest requestData) {
        applyNonNullHeader(request, AbstractSpiCall.HEADER_GOOGLE_APP_ID, requestData.googleAppId);
        applyNonNullHeader(request, AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE);
        applyNonNullHeader(request, AbstractSpiCall.HEADER_CLIENT_VERSION, CrashlyticsCore.getVersion());
        applyNonNullHeader(request, "Accept", "application/json");
        applyNonNullHeader(request, HEADER_DEVICE_MODEL, requestData.deviceModel);
        applyNonNullHeader(request, HEADER_OS_BUILD_VERSION, requestData.osBuildVersion);
        applyNonNullHeader(request, HEADER_OS_DISPLAY_VERSION, requestData.osDisplayVersion);
        applyNonNullHeader(request, HEADER_INSTALLATION_ID, requestData.installIdProvider.getCrashlyticsInstallId());
        return request;
    }

    private void applyNonNullHeader(HttpRequest request, String key, String value) {
        if (value != null) {
            request.header(key, value);
        }
    }
}
