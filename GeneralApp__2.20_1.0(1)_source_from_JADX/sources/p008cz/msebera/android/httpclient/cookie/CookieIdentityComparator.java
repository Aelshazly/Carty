package p008cz.msebera.android.httpclient.cookie;

import java.io.Serializable;
import java.util.Comparator;

/* renamed from: cz.msebera.android.httpclient.cookie.CookieIdentityComparator */
public class CookieIdentityComparator implements Serializable, Comparator<Cookie> {
    private static final long serialVersionUID = 4466565437490631532L;

    public int compare(Cookie c1, Cookie c2) {
        int res = c1.getName().compareTo(c2.getName());
        if (res == 0) {
            String d1 = c1.getDomain();
            String str = ".local";
            if (d1 == null) {
                d1 = "";
            } else if (d1.indexOf(46) == -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(d1);
                sb.append(str);
                d1 = sb.toString();
            }
            String d2 = c2.getDomain();
            if (d2 == null) {
                d2 = "";
            } else if (d2.indexOf(46) == -1) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(d2);
                sb2.append(str);
                d2 = sb2.toString();
            }
            res = d1.compareToIgnoreCase(d2);
        }
        if (res != 0) {
            return res;
        }
        String p1 = c1.getPath();
        if (p1 == null) {
            p1 = "/";
        }
        String p2 = c2.getPath();
        if (p2 == null) {
            p2 = "/";
        }
        return p1.compareTo(p2);
    }
}
