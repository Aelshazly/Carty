package p008cz.msebera.android.httpclient.entity;

import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpMessage;

/* renamed from: cz.msebera.android.httpclient.entity.ContentLengthStrategy */
public interface ContentLengthStrategy {
    public static final int CHUNKED = -2;
    public static final int IDENTITY = -1;

    long determineLength(HttpMessage httpMessage) throws HttpException;
}
