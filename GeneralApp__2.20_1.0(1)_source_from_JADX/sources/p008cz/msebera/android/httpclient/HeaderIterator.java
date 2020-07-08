package p008cz.msebera.android.httpclient;

import java.util.Iterator;

/* renamed from: cz.msebera.android.httpclient.HeaderIterator */
public interface HeaderIterator extends Iterator<Object> {
    boolean hasNext();

    Header nextHeader();
}
