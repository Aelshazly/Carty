package p008cz.msebera.android.httpclient.message;

import java.util.NoSuchElementException;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderIterator;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.message.BasicHeaderIterator */
public class BasicHeaderIterator implements HeaderIterator {
    protected final Header[] allHeaders;
    protected int currentIndex = findNext(-1);
    protected String headerName;

    public BasicHeaderIterator(Header[] headers, String name) {
        this.allHeaders = (Header[]) Args.notNull(headers, "Header array");
        this.headerName = name;
    }

    /* access modifiers changed from: protected */
    public int findNext(int pos) {
        int from = pos;
        int i = -1;
        if (from < -1) {
            return -1;
        }
        int to = this.allHeaders.length - 1;
        boolean found = false;
        while (!found && from < to) {
            from++;
            found = filterHeader(from);
        }
        if (found) {
            i = from;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public boolean filterHeader(int index) {
        String str = this.headerName;
        return str == null || str.equalsIgnoreCase(this.allHeaders[index].getName());
    }

    public boolean hasNext() {
        return this.currentIndex >= 0;
    }

    public Header nextHeader() throws NoSuchElementException {
        int current = this.currentIndex;
        if (current >= 0) {
            this.currentIndex = findNext(current);
            return this.allHeaders[current];
        }
        throw new NoSuchElementException("Iteration already finished.");
    }

    public final Object next() throws NoSuchElementException {
        return nextHeader();
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Removing headers is not supported.");
    }
}
