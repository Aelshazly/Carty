package com.loopj.android.http;

import android.os.Looper;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.StatusLine;
import p008cz.msebera.android.httpclient.client.HttpResponseException;

public abstract class BinaryHttpResponseHandler extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "BinaryHttpRH";
    private String[] mAllowedContentTypes = {"application/octet-stream", "image/jpeg", "image/png", "image/gif"};

    public abstract void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th);

    public abstract void onSuccess(int i, Header[] headerArr, byte[] bArr);

    public BinaryHttpResponseHandler() {
    }

    public BinaryHttpResponseHandler(String[] allowedContentTypes) {
        if (allowedContentTypes != null) {
            this.mAllowedContentTypes = allowedContentTypes;
        } else {
            AsyncHttpClient.log.mo22449e(LOG_TAG, "Constructor passed allowedContentTypes was null !");
        }
    }

    public BinaryHttpResponseHandler(String[] allowedContentTypes, Looper looper) {
        super(looper);
        if (allowedContentTypes != null) {
            this.mAllowedContentTypes = allowedContentTypes;
        } else {
            AsyncHttpClient.log.mo22449e(LOG_TAG, "Constructor passed allowedContentTypes was null !");
        }
    }

    public String[] getAllowedContentTypes() {
        return this.mAllowedContentTypes;
    }

    public final void sendResponseMessage(HttpResponse response) throws IOException {
        String[] allowedContentTypes;
        StatusLine status = response.getStatusLine();
        Header[] contentTypeHeaders = response.getHeaders("Content-Type");
        if (contentTypeHeaders.length != 1) {
            sendFailureMessage(status.getStatusCode(), response.getAllHeaders(), null, new HttpResponseException(status.getStatusCode(), "None, or more than one, Content-Type Header found!"));
            return;
        }
        Header contentTypeHeader = contentTypeHeaders[0];
        boolean foundAllowedContentType = false;
        for (String anAllowedContentType : getAllowedContentTypes()) {
            try {
                if (Pattern.matches(anAllowedContentType, contentTypeHeader.getValue())) {
                    foundAllowedContentType = true;
                }
            } catch (PatternSyntaxException e) {
                LogInterface logInterface = AsyncHttpClient.log;
                StringBuilder sb = new StringBuilder();
                sb.append("Given pattern is not valid: ");
                sb.append(anAllowedContentType);
                logInterface.mo22450e(LOG_TAG, sb.toString(), e);
            }
        }
        if (!foundAllowedContentType) {
            int statusCode = status.getStatusCode();
            Header[] allHeaders = response.getAllHeaders();
            int statusCode2 = status.getStatusCode();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Content-Type (");
            sb2.append(contentTypeHeader.getValue());
            sb2.append(") not allowed!");
            sendFailureMessage(statusCode, allHeaders, null, new HttpResponseException(statusCode2, sb2.toString()));
            return;
        }
        super.sendResponseMessage(response);
    }
}
