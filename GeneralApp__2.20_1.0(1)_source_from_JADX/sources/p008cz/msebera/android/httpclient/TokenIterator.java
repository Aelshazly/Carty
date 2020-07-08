package p008cz.msebera.android.httpclient;

import java.util.Iterator;

/* renamed from: cz.msebera.android.httpclient.TokenIterator */
public interface TokenIterator extends Iterator<Object> {
    boolean hasNext();

    String nextToken();
}
