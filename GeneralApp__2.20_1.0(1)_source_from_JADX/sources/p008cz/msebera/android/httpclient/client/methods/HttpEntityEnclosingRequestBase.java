package p008cz.msebera.android.httpclient.client.methods;

import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpEntity;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.client.utils.CloneUtils;
import p008cz.msebera.android.httpclient.protocol.HTTP;

/* renamed from: cz.msebera.android.httpclient.client.methods.HttpEntityEnclosingRequestBase */
public abstract class HttpEntityEnclosingRequestBase extends HttpRequestBase implements HttpEntityEnclosingRequest {
    private HttpEntity entity;

    public HttpEntity getEntity() {
        return this.entity;
    }

    public void setEntity(HttpEntity entity2) {
        this.entity = entity2;
    }

    public boolean expectContinue() {
        Header expect = getFirstHeader("Expect");
        if (expect != null) {
            if (HTTP.EXPECT_CONTINUE.equalsIgnoreCase(expect.getValue())) {
                return true;
            }
        }
        return false;
    }

    public Object clone() throws CloneNotSupportedException {
        HttpEntityEnclosingRequestBase clone = (HttpEntityEnclosingRequestBase) super.clone();
        HttpEntity httpEntity = this.entity;
        if (httpEntity != null) {
            clone.entity = (HttpEntity) CloneUtils.cloneObject(httpEntity);
        }
        return clone;
    }
}
