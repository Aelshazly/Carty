package p008cz.msebera.android.httpclient.impl.client;

import p008cz.msebera.android.httpclient.client.methods.HttpPost;

/* renamed from: cz.msebera.android.httpclient.impl.client.LaxRedirectStrategy */
public class LaxRedirectStrategy extends DefaultRedirectStrategy {
    private static final String[] REDIRECT_METHODS = {"GET", HttpPost.METHOD_NAME, "HEAD"};

    /* access modifiers changed from: protected */
    public boolean isRedirectable(String method) {
        for (String m : REDIRECT_METHODS) {
            if (m.equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }
}
