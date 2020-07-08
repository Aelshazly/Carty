package com.loopj.android.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p008cz.msebera.android.httpclient.Header;

public class JsonHttpResponseHandler extends TextHttpResponseHandler {
    private static final String LOG_TAG = "JsonHttpRH";
    /* access modifiers changed from: private */
    public boolean useRFC5179CompatibilityMode = true;

    public JsonHttpResponseHandler() {
        super("UTF-8");
    }

    public JsonHttpResponseHandler(String encoding) {
        super(encoding);
    }

    public JsonHttpResponseHandler(boolean useRFC5179CompatibilityMode2) {
        super("UTF-8");
        this.useRFC5179CompatibilityMode = useRFC5179CompatibilityMode2;
    }

    public JsonHttpResponseHandler(String encoding, boolean useRFC5179CompatibilityMode2) {
        super(encoding);
        this.useRFC5179CompatibilityMode = useRFC5179CompatibilityMode2;
    }

    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        AsyncHttpClient.log.mo22462w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
    }

    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        AsyncHttpClient.log.mo22462w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");
    }

    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        AsyncHttpClient.log.mo22463w(LOG_TAG, "onFailure(int, Header[], Throwable, JSONObject) was not overriden, but callback was received", throwable);
    }

    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        AsyncHttpClient.log.mo22463w(LOG_TAG, "onFailure(int, Header[], Throwable, JSONArray) was not overriden, but callback was received", throwable);
    }

    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        AsyncHttpClient.log.mo22463w(LOG_TAG, "onFailure(int, Header[], String, Throwable) was not overriden, but callback was received", throwable);
    }

    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        AsyncHttpClient.log.mo22462w(LOG_TAG, "onSuccess(int, Header[], String) was not overriden, but callback was received");
    }

    public final void onSuccess(final int statusCode, final Header[] headers, final byte[] responseBytes) {
        if (statusCode != 204) {
            Runnable parser = new Runnable() {
                public void run() {
                    try {
                        final Object jsonResponse = JsonHttpResponseHandler.this.parseResponse(responseBytes);
                        JsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                if (JsonHttpResponseHandler.this.useRFC5179CompatibilityMode || jsonResponse != null) {
                                    Object obj = jsonResponse;
                                    if (obj instanceof JSONObject) {
                                        JsonHttpResponseHandler.this.onSuccess(statusCode, headers, (JSONObject) jsonResponse);
                                    } else if (obj instanceof JSONArray) {
                                        JsonHttpResponseHandler.this.onSuccess(statusCode, headers, (JSONArray) jsonResponse);
                                    } else if (!(obj instanceof String)) {
                                        JsonHttpResponseHandler jsonHttpResponseHandler = JsonHttpResponseHandler.this;
                                        int i = statusCode;
                                        Header[] headerArr = headers;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Unexpected response type ");
                                        sb.append(jsonResponse.getClass().getName());
                                        jsonHttpResponseHandler.onFailure(i, headerArr, (Throwable) new JSONException(sb.toString()), (JSONObject) null);
                                    } else if (JsonHttpResponseHandler.this.useRFC5179CompatibilityMode) {
                                        JsonHttpResponseHandler.this.onFailure(statusCode, headers, (String) jsonResponse, (Throwable) new JSONException("Response cannot be parsed as JSON data"));
                                    } else {
                                        JsonHttpResponseHandler.this.onSuccess(statusCode, headers, (String) jsonResponse);
                                    }
                                } else {
                                    JsonHttpResponseHandler.this.onSuccess(statusCode, headers, (String) null);
                                }
                            }
                        });
                    } catch (JSONException ex) {
                        JsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                JsonHttpResponseHandler.this.onFailure(statusCode, headers, (Throwable) ex, (JSONObject) null);
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
            onSuccess(statusCode, headers, new JSONObject());
        }
    }

    public final void onFailure(int statusCode, Header[] headers, byte[] responseBytes, Throwable throwable) {
        if (responseBytes != null) {
            final byte[] bArr = responseBytes;
            final int i = statusCode;
            final Header[] headerArr = headers;
            final Throwable th = throwable;
            C09542 r0 = new Runnable() {
                public void run() {
                    try {
                        final Object jsonResponse = JsonHttpResponseHandler.this.parseResponse(bArr);
                        JsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                if (JsonHttpResponseHandler.this.useRFC5179CompatibilityMode || jsonResponse != null) {
                                    Object obj = jsonResponse;
                                    if (obj instanceof JSONObject) {
                                        JsonHttpResponseHandler.this.onFailure(i, headerArr, th, (JSONObject) jsonResponse);
                                    } else if (obj instanceof JSONArray) {
                                        JsonHttpResponseHandler.this.onFailure(i, headerArr, th, (JSONArray) jsonResponse);
                                    } else if (obj instanceof String) {
                                        JsonHttpResponseHandler.this.onFailure(i, headerArr, (String) jsonResponse, th);
                                    } else {
                                        JsonHttpResponseHandler jsonHttpResponseHandler = JsonHttpResponseHandler.this;
                                        int i = i;
                                        Header[] headerArr = headerArr;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Unexpected response type ");
                                        sb.append(jsonResponse.getClass().getName());
                                        jsonHttpResponseHandler.onFailure(i, headerArr, (Throwable) new JSONException(sb.toString()), (JSONObject) null);
                                    }
                                } else {
                                    JsonHttpResponseHandler.this.onFailure(i, headerArr, (String) null, th);
                                }
                            }
                        });
                    } catch (JSONException ex) {
                        JsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                JsonHttpResponseHandler.this.onFailure(i, headerArr, (Throwable) ex, (JSONObject) null);
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
            AsyncHttpClient.log.mo22460v(LOG_TAG, "response body is null, calling onFailure(Throwable, JSONObject)");
            onFailure(statusCode, headers, throwable, (JSONObject) null);
        }
    }

    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object parseResponse(byte[] r6) throws org.json.JSONException {
        /*
            r5 = this;
            if (r6 != 0) goto L_0x0004
            r0 = 0
            return r0
        L_0x0004:
            r0 = 0
            java.lang.String r1 = r5.getCharset()
            java.lang.String r1 = getResponseString(r6, r1)
            if (r1 == 0) goto L_0x0071
            java.lang.String r1 = r1.trim()
            boolean r2 = r5.useRFC5179CompatibilityMode
            java.lang.String r3 = "["
            java.lang.String r4 = "{"
            if (r2 == 0) goto L_0x0032
            boolean r2 = r1.startsWith(r4)
            if (r2 != 0) goto L_0x0028
            boolean r2 = r1.startsWith(r3)
            if (r2 == 0) goto L_0x0071
        L_0x0028:
            org.json.JSONTokener r2 = new org.json.JSONTokener
            r2.<init>(r1)
            java.lang.Object r0 = r2.nextValue()
            goto L_0x0071
        L_0x0032:
            boolean r2 = r1.startsWith(r4)
            if (r2 == 0) goto L_0x0041
            java.lang.String r2 = "}"
            boolean r2 = r1.endsWith(r2)
            if (r2 != 0) goto L_0x004f
        L_0x0041:
            boolean r2 = r1.startsWith(r3)
            if (r2 == 0) goto L_0x0059
            java.lang.String r2 = "]"
            boolean r2 = r1.endsWith(r2)
            if (r2 == 0) goto L_0x0059
        L_0x004f:
            org.json.JSONTokener r2 = new org.json.JSONTokener
            r2.<init>(r1)
            java.lang.Object r0 = r2.nextValue()
            goto L_0x0071
        L_0x0059:
            java.lang.String r2 = "\""
            boolean r3 = r1.startsWith(r2)
            if (r3 == 0) goto L_0x0071
            boolean r2 = r1.endsWith(r2)
            if (r2 == 0) goto L_0x0071
            int r2 = r1.length()
            r3 = 1
            int r2 = r2 - r3
            java.lang.String r0 = r1.substring(r3, r2)
        L_0x0071:
            if (r0 != 0) goto L_0x0074
            r0 = r1
        L_0x0074:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loopj.android.http.JsonHttpResponseHandler.parseResponse(byte[]):java.lang.Object");
    }

    public boolean isUseRFC5179CompatibilityMode() {
        return this.useRFC5179CompatibilityMode;
    }

    public void setUseRFC5179CompatibilityMode(boolean useRFC5179CompatibilityMode2) {
        this.useRFC5179CompatibilityMode = useRFC5179CompatibilityMode2;
    }
}
