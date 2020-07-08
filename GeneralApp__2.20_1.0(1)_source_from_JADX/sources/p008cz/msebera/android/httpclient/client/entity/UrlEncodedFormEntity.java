package p008cz.msebera.android.httpclient.client.entity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import p008cz.msebera.android.httpclient.NameValuePair;
import p008cz.msebera.android.httpclient.client.utils.URLEncodedUtils;
import p008cz.msebera.android.httpclient.entity.ContentType;
import p008cz.msebera.android.httpclient.entity.StringEntity;
import p008cz.msebera.android.httpclient.protocol.HTTP;

/* renamed from: cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity */
public class UrlEncodedFormEntity extends StringEntity {
    public UrlEncodedFormEntity(List<? extends NameValuePair> parameters, String charset) throws UnsupportedEncodingException {
        String str;
        if (charset != null) {
            str = charset;
        } else {
            str = HTTP.DEF_CONTENT_CHARSET.name();
        }
        super(URLEncodedUtils.format(parameters, str), ContentType.create(URLEncodedUtils.CONTENT_TYPE, charset));
    }

    public UrlEncodedFormEntity(Iterable<? extends NameValuePair> parameters, Charset charset) {
        super(URLEncodedUtils.format(parameters, charset != null ? charset : HTTP.DEF_CONTENT_CHARSET), ContentType.create(URLEncodedUtils.CONTENT_TYPE, charset));
    }

    public UrlEncodedFormEntity(List<? extends NameValuePair> parameters) throws UnsupportedEncodingException {
        this((Iterable<? extends NameValuePair>) parameters, (Charset) null);
    }

    public UrlEncodedFormEntity(Iterable<? extends NameValuePair> parameters) {
        this(parameters, (Charset) null);
    }
}
