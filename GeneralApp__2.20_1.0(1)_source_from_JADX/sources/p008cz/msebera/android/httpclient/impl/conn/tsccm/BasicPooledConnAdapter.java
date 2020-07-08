package p008cz.msebera.android.httpclient.impl.conn.tsccm;

import p008cz.msebera.android.httpclient.conn.ClientConnectionManager;
import p008cz.msebera.android.httpclient.impl.conn.AbstractPoolEntry;
import p008cz.msebera.android.httpclient.impl.conn.AbstractPooledConnAdapter;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.conn.tsccm.BasicPooledConnAdapter */
public class BasicPooledConnAdapter extends AbstractPooledConnAdapter {
    protected BasicPooledConnAdapter(ThreadSafeClientConnManager tsccm, AbstractPoolEntry entry) {
        super(tsccm, entry);
        markReusable();
    }

    /* access modifiers changed from: protected */
    public ClientConnectionManager getManager() {
        return super.getManager();
    }

    /* access modifiers changed from: protected */
    public AbstractPoolEntry getPoolEntry() {
        return super.getPoolEntry();
    }

    /* access modifiers changed from: protected */
    public void detach() {
        super.detach();
    }
}
