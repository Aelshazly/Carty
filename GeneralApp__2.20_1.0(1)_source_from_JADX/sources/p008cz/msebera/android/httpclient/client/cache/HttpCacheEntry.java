package p008cz.msebera.android.httpclient.client.cache;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.StatusLine;
import p008cz.msebera.android.httpclient.client.utils.DateUtils;
import p008cz.msebera.android.httpclient.message.HeaderGroup;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.client.cache.HttpCacheEntry */
public class HttpCacheEntry implements Serializable {
    private static final long serialVersionUID = -6300496422359477413L;
    private final Date date;
    private final Date requestDate;
    private final Resource resource;
    private final Date responseDate;
    private final HeaderGroup responseHeaders;
    private final StatusLine statusLine;
    private final Map<String, String> variantMap;

    public HttpCacheEntry(Date requestDate2, Date responseDate2, StatusLine statusLine2, Header[] responseHeaders2, Resource resource2, Map<String, String> variantMap2) {
        Args.notNull(requestDate2, "Request date");
        Args.notNull(responseDate2, "Response date");
        Args.notNull(statusLine2, "Status line");
        Args.notNull(responseHeaders2, "Response headers");
        this.requestDate = requestDate2;
        this.responseDate = responseDate2;
        this.statusLine = statusLine2;
        this.responseHeaders = new HeaderGroup();
        this.responseHeaders.setHeaders(responseHeaders2);
        this.resource = resource2;
        this.variantMap = variantMap2 != null ? new HashMap(variantMap2) : null;
        this.date = parseDate();
    }

    public HttpCacheEntry(Date requestDate2, Date responseDate2, StatusLine statusLine2, Header[] responseHeaders2, Resource resource2) {
        this(requestDate2, responseDate2, statusLine2, responseHeaders2, resource2, new HashMap());
    }

    private Date parseDate() {
        Header dateHdr = getFirstHeader("Date");
        if (dateHdr == null) {
            return null;
        }
        return DateUtils.parseDate(dateHdr.getValue());
    }

    public StatusLine getStatusLine() {
        return this.statusLine;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.statusLine.getProtocolVersion();
    }

    public String getReasonPhrase() {
        return this.statusLine.getReasonPhrase();
    }

    public int getStatusCode() {
        return this.statusLine.getStatusCode();
    }

    public Date getRequestDate() {
        return this.requestDate;
    }

    public Date getResponseDate() {
        return this.responseDate;
    }

    public Header[] getAllHeaders() {
        return this.responseHeaders.getAllHeaders();
    }

    public Header getFirstHeader(String name) {
        return this.responseHeaders.getFirstHeader(name);
    }

    public Header[] getHeaders(String name) {
        return this.responseHeaders.getHeaders(name);
    }

    public Date getDate() {
        return this.date;
    }

    public Resource getResource() {
        return this.resource;
    }

    public boolean hasVariants() {
        return getFirstHeader("Vary") != null;
    }

    public Map<String, String> getVariantMap() {
        return Collections.unmodifiableMap(this.variantMap);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[request date=");
        sb.append(this.requestDate);
        sb.append("; response date=");
        sb.append(this.responseDate);
        sb.append("; statusLine=");
        sb.append(this.statusLine);
        sb.append("]");
        return sb.toString();
    }
}
