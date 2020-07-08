package p008cz.msebera.android.httpclient.impl.cookie;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;
import p008cz.msebera.android.httpclient.cookie.SetCookie;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.BasicClientCookie */
public class BasicClientCookie implements SetCookie, ClientCookie, Cloneable, Serializable {
    private static final long serialVersionUID = -3869795591041535538L;
    private Map<String, String> attribs = new HashMap();
    private String cookieComment;
    private String cookieDomain;
    private Date cookieExpiryDate;
    private String cookiePath;
    private int cookieVersion;
    private boolean isSecure;
    private final String name;
    private String value;

    public BasicClientCookie(String name2, String value2) {
        Args.notNull(name2, "Name");
        this.name = name2;
        this.value = value2;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value2) {
        this.value = value2;
    }

    public String getComment() {
        return this.cookieComment;
    }

    public void setComment(String comment) {
        this.cookieComment = comment;
    }

    public String getCommentURL() {
        return null;
    }

    public Date getExpiryDate() {
        return this.cookieExpiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.cookieExpiryDate = expiryDate;
    }

    public boolean isPersistent() {
        return this.cookieExpiryDate != null;
    }

    public String getDomain() {
        return this.cookieDomain;
    }

    public void setDomain(String domain) {
        if (domain != null) {
            this.cookieDomain = domain.toLowerCase(Locale.ENGLISH);
        } else {
            this.cookieDomain = null;
        }
    }

    public String getPath() {
        return this.cookiePath;
    }

    public void setPath(String path) {
        this.cookiePath = path;
    }

    public boolean isSecure() {
        return this.isSecure;
    }

    public void setSecure(boolean secure) {
        this.isSecure = secure;
    }

    public int[] getPorts() {
        return null;
    }

    public int getVersion() {
        return this.cookieVersion;
    }

    public void setVersion(int version) {
        this.cookieVersion = version;
    }

    public boolean isExpired(Date date) {
        Args.notNull(date, "Date");
        Date date2 = this.cookieExpiryDate;
        return date2 != null && date2.getTime() <= date.getTime();
    }

    public void setAttribute(String name2, String value2) {
        this.attribs.put(name2, value2);
    }

    public String getAttribute(String name2) {
        return (String) this.attribs.get(name2);
    }

    public boolean containsAttribute(String name2) {
        return this.attribs.get(name2) != null;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie clone = (BasicClientCookie) super.clone();
        clone.attribs = new HashMap(this.attribs);
        return clone;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[version: ");
        buffer.append(Integer.toString(this.cookieVersion));
        String str = "]";
        buffer.append(str);
        buffer.append("[name: ");
        buffer.append(this.name);
        buffer.append(str);
        buffer.append("[value: ");
        buffer.append(this.value);
        buffer.append(str);
        buffer.append("[domain: ");
        buffer.append(this.cookieDomain);
        buffer.append(str);
        buffer.append("[path: ");
        buffer.append(this.cookiePath);
        buffer.append(str);
        buffer.append("[expiry: ");
        buffer.append(this.cookieExpiryDate);
        buffer.append(str);
        return buffer.toString();
    }
}
