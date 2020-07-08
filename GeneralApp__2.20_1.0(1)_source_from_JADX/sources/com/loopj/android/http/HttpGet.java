package com.loopj.android.http;

import java.net.URI;
import p008cz.msebera.android.httpclient.client.methods.HttpEntityEnclosingRequestBase;

public final class HttpGet extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "GET";

    public HttpGet() {
    }

    public HttpGet(URI uri) {
        setURI(uri);
    }

    public HttpGet(String uri) {
        setURI(URI.create(uri));
    }

    public String getMethod() {
        return "GET";
    }
}
