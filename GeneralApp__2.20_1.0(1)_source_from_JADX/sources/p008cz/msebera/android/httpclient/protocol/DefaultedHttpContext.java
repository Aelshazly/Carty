package p008cz.msebera.android.httpclient.protocol;

import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.protocol.DefaultedHttpContext */
public final class DefaultedHttpContext implements HttpContext {
    private final HttpContext defaults;
    private final HttpContext local;

    public DefaultedHttpContext(HttpContext local2, HttpContext defaults2) {
        this.local = (HttpContext) Args.notNull(local2, "HTTP context");
        this.defaults = defaults2;
    }

    public Object getAttribute(String id) {
        Object obj = this.local.getAttribute(id);
        if (obj == null) {
            return this.defaults.getAttribute(id);
        }
        return obj;
    }

    public Object removeAttribute(String id) {
        return this.local.removeAttribute(id);
    }

    public void setAttribute(String id, Object obj) {
        this.local.setAttribute(id, obj);
    }

    public HttpContext getDefaults() {
        return this.defaults;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[local: ");
        buf.append(this.local);
        buf.append("defaults: ");
        buf.append(this.defaults);
        buf.append("]");
        return buf.toString();
    }
}
