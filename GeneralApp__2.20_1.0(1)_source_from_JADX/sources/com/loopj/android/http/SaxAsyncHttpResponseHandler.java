package com.loopj.android.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;

public abstract class SaxAsyncHttpResponseHandler<T extends DefaultHandler> extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "SaxAsyncHttpRH";
    private T handler = null;

    public abstract void onFailure(int i, Header[] headerArr, T t);

    public abstract void onSuccess(int i, Header[] headerArr, T t);

    public SaxAsyncHttpResponseHandler(T t) {
        if (t != null) {
            this.handler = t;
            return;
        }
        throw new Error("null instance of <T extends DefaultHandler> passed to constructor");
    }

    /* access modifiers changed from: protected */
    public byte[] getResponseData(HttpEntity entity) throws IOException {
        String str = "getResponseData exception";
        String str2 = LOG_TAG;
        if (entity != null) {
            InputStream instream = entity.getContent();
            InputStreamReader inputStreamReader = null;
            if (instream != null) {
                try {
                    XMLReader rssReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
                    rssReader.setContentHandler(this.handler);
                    inputStreamReader = new InputStreamReader(instream, getCharset());
                    rssReader.parse(new InputSource(inputStreamReader));
                    AsyncHttpClient.silentCloseInputStream(instream);
                    try {
                        inputStreamReader.close();
                    } catch (IOException e) {
                    }
                } catch (SAXException e2) {
                    AsyncHttpClient.log.mo22450e(str2, str, e2);
                    AsyncHttpClient.silentCloseInputStream(instream);
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                } catch (ParserConfigurationException e3) {
                    AsyncHttpClient.log.mo22450e(str2, str, e3);
                    AsyncHttpClient.silentCloseInputStream(instream);
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                } catch (Throwable th) {
                    AsyncHttpClient.silentCloseInputStream(instream);
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            }
        }
        return null;
    }

    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        onSuccess(statusCode, headers, this.handler);
    }

    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        onFailure(statusCode, headers, this.handler);
    }
}
