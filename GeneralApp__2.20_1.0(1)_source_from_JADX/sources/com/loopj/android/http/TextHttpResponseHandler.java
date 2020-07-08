package com.loopj.android.http;

import java.io.UnsupportedEncodingException;
import p008cz.msebera.android.httpclient.Header;

public abstract class TextHttpResponseHandler extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "TextHttpRH";

    public abstract void onFailure(int i, Header[] headerArr, String str, Throwable th);

    public abstract void onSuccess(int i, Header[] headerArr, String str);

    public TextHttpResponseHandler() {
        this("UTF-8");
    }

    public TextHttpResponseHandler(String encoding) {
        setCharset(encoding);
    }

    public static String getResponseString(byte[] stringBytes, String charset) {
        String toReturn;
        if (stringBytes == null) {
            toReturn = null;
        } else {
            try {
                toReturn = new String(stringBytes, charset);
            } catch (UnsupportedEncodingException e) {
                AsyncHttpClient.log.mo22450e(LOG_TAG, "Encoding response into string failed", e);
                return null;
            }
        }
        if (toReturn == null || !toReturn.startsWith(AsyncHttpResponseHandler.UTF8_BOM)) {
            return toReturn;
        }
        return toReturn.substring(1);
    }

    public void onSuccess(int statusCode, Header[] headers, byte[] responseBytes) {
        onSuccess(statusCode, headers, getResponseString(responseBytes, getCharset()));
    }

    public void onFailure(int statusCode, Header[] headers, byte[] responseBytes, Throwable throwable) {
        onFailure(statusCode, headers, getResponseString(responseBytes, getCharset()), throwable);
    }
}
