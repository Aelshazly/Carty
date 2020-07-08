package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import p008cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import p008cz.msebera.android.httpclient.cookie.CookieSpec;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.AbstractCookieSpec */
public abstract class AbstractCookieSpec implements CookieSpec {
    private final Map<String, CookieAttributeHandler> attribHandlerMap = new HashMap(10);

    public void registerAttribHandler(String name, CookieAttributeHandler handler) {
        Args.notNull(name, "Attribute name");
        Args.notNull(handler, "Attribute handler");
        this.attribHandlerMap.put(name, handler);
    }

    /* access modifiers changed from: protected */
    public CookieAttributeHandler findAttribHandler(String name) {
        return (CookieAttributeHandler) this.attribHandlerMap.get(name);
    }

    /* access modifiers changed from: protected */
    public CookieAttributeHandler getAttribHandler(String name) {
        CookieAttributeHandler handler = findAttribHandler(name);
        if (handler != null) {
            return handler;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Handler not registered for ");
        sb.append(name);
        sb.append(" attribute.");
        throw new IllegalStateException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public Collection<CookieAttributeHandler> getAttribHandlers() {
        return this.attribHandlerMap.values();
    }
}
