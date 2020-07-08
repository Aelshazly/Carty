package p008cz.msebera.android.httpclient.protocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.protocol.BasicHttpContext */
public class BasicHttpContext implements HttpContext {
    private final Map<String, Object> map;
    private final HttpContext parentContext;

    public BasicHttpContext() {
        this(null);
    }

    public BasicHttpContext(HttpContext parentContext2) {
        this.map = new ConcurrentHashMap();
        this.parentContext = parentContext2;
    }

    public Object getAttribute(String id) {
        Args.notNull(id, "Id");
        Object obj = this.map.get(id);
        if (obj != null) {
            return obj;
        }
        HttpContext httpContext = this.parentContext;
        if (httpContext != null) {
            return httpContext.getAttribute(id);
        }
        return obj;
    }

    public void setAttribute(String id, Object obj) {
        Args.notNull(id, "Id");
        if (obj != null) {
            this.map.put(id, obj);
        } else {
            this.map.remove(id);
        }
    }

    public Object removeAttribute(String id) {
        Args.notNull(id, "Id");
        return this.map.remove(id);
    }

    public void clear() {
        this.map.clear();
    }

    public String toString() {
        return this.map.toString();
    }
}
