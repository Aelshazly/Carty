package com.loopj.android.http;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLException;
import p008cz.msebera.android.httpclient.NoHttpResponseException;
import p008cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import p008cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import p008cz.msebera.android.httpclient.protocol.HttpContext;

class RetryHandler implements HttpRequestRetryHandler {
    private static final HashSet<Class<?>> exceptionBlacklist = new HashSet<>();
    private static final HashSet<Class<?>> exceptionWhitelist = new HashSet<>();
    private final int maxRetries;
    private final int retrySleepTimeMS;

    static {
        exceptionWhitelist.add(NoHttpResponseException.class);
        exceptionWhitelist.add(UnknownHostException.class);
        exceptionWhitelist.add(SocketException.class);
        exceptionBlacklist.add(InterruptedIOException.class);
        exceptionBlacklist.add(SSLException.class);
    }

    public RetryHandler(int maxRetries2, int retrySleepTimeMS2) {
        this.maxRetries = maxRetries2;
        this.retrySleepTimeMS = retrySleepTimeMS2;
    }

    static void addClassToWhitelist(Class<?> cls) {
        exceptionWhitelist.add(cls);
    }

    static void addClassToBlacklist(Class<?> cls) {
        exceptionBlacklist.add(cls);
    }

    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        boolean retry = true;
        Boolean b = (Boolean) context.getAttribute("http.request_sent");
        boolean sent = b != null && b.booleanValue();
        if (executionCount > this.maxRetries) {
            retry = false;
        } else if (isInList(exceptionWhitelist, exception)) {
            retry = true;
        } else if (isInList(exceptionBlacklist, exception)) {
            retry = false;
        } else if (!sent) {
            retry = true;
        }
        if (retry && ((HttpUriRequest) context.getAttribute("http.request")) == null) {
            return false;
        }
        if (retry) {
            SystemClock.sleep((long) this.retrySleepTimeMS);
        } else {
            exception.printStackTrace();
        }
        return retry;
    }

    /* access modifiers changed from: protected */
    public boolean isInList(HashSet<Class<?>> list, Throwable error) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((Class) it.next()).isInstance(error)) {
                return true;
            }
        }
        return false;
    }
}
