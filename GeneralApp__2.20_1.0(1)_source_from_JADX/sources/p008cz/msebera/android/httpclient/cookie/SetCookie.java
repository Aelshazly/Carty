package p008cz.msebera.android.httpclient.cookie;

import java.util.Date;

/* renamed from: cz.msebera.android.httpclient.cookie.SetCookie */
public interface SetCookie extends Cookie {
    void setComment(String str);

    void setDomain(String str);

    void setExpiryDate(Date date);

    void setPath(String str);

    void setSecure(boolean z);

    void setValue(String str);

    void setVersion(int i);
}
