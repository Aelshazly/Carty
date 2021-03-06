package com.loopj.android.http;

import p008cz.msebera.android.httpclient.Header;

public abstract class BaseJsonHttpResponseHandler<JSON_TYPE> extends TextHttpResponseHandler {
    private static final String LOG_TAG = "BaseJsonHttpRH";

    public abstract void onFailure(int i, Header[] headerArr, Throwable th, String str, JSON_TYPE json_type);

    public abstract void onSuccess(int i, Header[] headerArr, String str, JSON_TYPE json_type);

    /* access modifiers changed from: protected */
    public abstract JSON_TYPE parseResponse(String str, boolean z) throws Throwable;

    public BaseJsonHttpResponseHandler() {
        this("UTF-8");
    }

    public BaseJsonHttpResponseHandler(String encoding) {
        super(encoding);
    }

    public final void onSuccess(final int statusCode, final Header[] headers, final String responseString) {
        if (statusCode != 204) {
            Runnable parser = new Runnable() {
                public void run() {
                    try {
                        final JSON_TYPE jsonResponse = BaseJsonHttpResponseHandler.this.parseResponse(responseString, false);
                        BaseJsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                BaseJsonHttpResponseHandler.this.onSuccess(statusCode, headers, responseString, jsonResponse);
                            }
                        });
                    } catch (Throwable t) {
                        AsyncHttpClient.log.mo22448d(BaseJsonHttpResponseHandler.LOG_TAG, "parseResponse thrown an problem", t);
                        BaseJsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                BaseJsonHttpResponseHandler.this.onFailure(statusCode, headers, t, responseString, null);
                            }
                        });
                    }
                }
            };
            if (getUseSynchronousMode() || getUsePoolThread()) {
                parser.run();
            } else {
                new Thread(parser).start();
            }
        } else {
            onSuccess(statusCode, headers, null, null);
        }
    }

    public final void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        if (responseString != null) {
            final String str = responseString;
            final int i = statusCode;
            final Header[] headerArr = headers;
            final Throwable th = throwable;
            C09482 r0 = new Runnable() {
                public void run() {
                    try {
                        final JSON_TYPE jsonResponse = BaseJsonHttpResponseHandler.this.parseResponse(str, true);
                        BaseJsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                BaseJsonHttpResponseHandler.this.onFailure(i, headerArr, th, str, jsonResponse);
                            }
                        });
                    } catch (Throwable t) {
                        AsyncHttpClient.log.mo22448d(BaseJsonHttpResponseHandler.LOG_TAG, "parseResponse thrown an problem", t);
                        BaseJsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                BaseJsonHttpResponseHandler.this.onFailure(i, headerArr, th, str, null);
                            }
                        });
                    }
                }
            };
            if (getUseSynchronousMode() || getUsePoolThread()) {
                r0.run();
            } else {
                new Thread(r0).start();
            }
        } else {
            onFailure(statusCode, headers, throwable, null, null);
        }
    }
}
