package p008cz.msebera.android.httpclient.impl.cookie;

import java.util.Date;
import p008cz.msebera.android.httpclient.cookie.SetCookie2;

/* renamed from: cz.msebera.android.httpclient.impl.cookie.BasicClientCookie2 */
public class BasicClientCookie2 extends BasicClientCookie implements SetCookie2 {
    private static final long serialVersionUID = -7744598295706617057L;
    private String commentURL;
    private boolean discard;
    private int[] ports;

    public BasicClientCookie2(String name, String value) {
        super(name, value);
    }

    public int[] getPorts() {
        return this.ports;
    }

    public void setPorts(int[] ports2) {
        this.ports = ports2;
    }

    public String getCommentURL() {
        return this.commentURL;
    }

    public void setCommentURL(String commentURL2) {
        this.commentURL = commentURL2;
    }

    public void setDiscard(boolean discard2) {
        this.discard = discard2;
    }

    public boolean isPersistent() {
        return !this.discard && super.isPersistent();
    }

    public boolean isExpired(Date date) {
        return this.discard || super.isExpired(date);
    }

    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie2 clone = (BasicClientCookie2) super.clone();
        int[] iArr = this.ports;
        if (iArr != null) {
            clone.ports = (int[]) iArr.clone();
        }
        return clone;
    }
}
