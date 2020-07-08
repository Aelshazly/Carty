package com.google.firebase.crashlytics.internal.network;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class HttpRequest {
    private static final OkHttpClient CLIENT = new OkHttpClient().newBuilder().callTimeout(10000, TimeUnit.MILLISECONDS).build();
    private static final int DEFAULT_TIMEOUT_MS = 10000;
    private Builder bodyBuilder = null;
    private final Map<String, String> headers;
    private final HttpMethod method;
    private final Map<String, String> queryParams;
    private final String url;

    public HttpRequest(HttpMethod method2, String url2, Map<String, String> queryParams2) {
        this.method = method2;
        this.url = url2;
        this.queryParams = queryParams2;
        this.headers = new HashMap();
    }

    public HttpRequest header(String name, String value) {
        this.headers.put(name, value);
        return this;
    }

    public HttpRequest header(Entry<String, String> entry) {
        return header((String) entry.getKey(), (String) entry.getValue());
    }

    private Builder getOrCreateBodyBuilder() {
        if (this.bodyBuilder == null) {
            this.bodyBuilder = new Builder().setType(MultipartBody.FORM);
        }
        return this.bodyBuilder;
    }

    public HttpRequest part(String name, String value) {
        this.bodyBuilder = getOrCreateBodyBuilder().addFormDataPart(name, value);
        return this;
    }

    public HttpRequest part(String name, String filename, String contentType, File part) {
        this.bodyBuilder = getOrCreateBodyBuilder().addFormDataPart(name, filename, RequestBody.create(MediaType.parse(contentType), part));
        return this;
    }

    public String method() {
        return this.method.name();
    }

    private Request build() {
        Request.Builder builder = new Request.Builder().cacheControl(new CacheControl.Builder().noCache().build());
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.url).newBuilder();
        for (Entry<String, String> entry : this.queryParams.entrySet()) {
            urlBuilder = urlBuilder.addEncodedQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        Request.Builder builder2 = builder.url(urlBuilder.build());
        for (Entry<String, String> entry2 : this.headers.entrySet()) {
            builder2 = builder2.header((String) entry2.getKey(), (String) entry2.getValue());
        }
        Builder builder3 = this.bodyBuilder;
        return builder2.method(this.method.name(), builder3 == null ? null : builder3.build()).build();
    }

    public HttpResponse execute() throws IOException {
        return HttpResponse.create(CLIENT.newCall(build()).execute());
    }
}
