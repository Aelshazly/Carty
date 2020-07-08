package p008cz.msebera.android.httpclient.impl.client;

import java.io.IOException;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.StatusLine;
import p008cz.msebera.android.httpclient.client.HttpResponseException;
import p008cz.msebera.android.httpclient.client.ResponseHandler;
import p008cz.msebera.android.httpclient.util.EntityUtils;

/* renamed from: cz.msebera.android.httpclient.impl.client.BasicResponseHandler */
public class BasicResponseHandler implements ResponseHandler<String> {
    public String handleResponse(HttpResponse response) throws HttpResponseException, IOException {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            EntityUtils.consume(entity);
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        } else if (entity == null) {
            return null;
        } else {
            return EntityUtils.toString(entity);
        }
    }
}
