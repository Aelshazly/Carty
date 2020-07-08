package p008cz.msebera.android.httpclient;

import java.util.Iterator;

/* renamed from: cz.msebera.android.httpclient.HeaderElementIterator */
public interface HeaderElementIterator extends Iterator<Object> {
    boolean hasNext();

    HeaderElement nextElement();
}
