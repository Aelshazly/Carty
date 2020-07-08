package com.google.firebase.crashlytics.internal.network;

import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Response;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class HttpResponse {
    private String body;
    private int code;
    private Headers headers;

    HttpResponse(int code2, String body2, Headers headers2) {
        this.code = code2;
        this.body = body2;
        this.headers = headers2;
    }

    static HttpResponse create(Response response) throws IOException {
        return new HttpResponse(response.code(), response.body() == null ? null : response.body().string(), response.headers());
    }

    public int code() {
        return this.code;
    }

    public String body() {
        return this.body;
    }

    public String header(String name) {
        return this.headers.get(name);
    }
}
