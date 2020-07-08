package p008cz.msebera.android.httpclient.impl.client.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import okhttp3.internal.cache.DiskLruCache;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import p008cz.msebera.android.httpclient.client.cache.Resource;
import p008cz.msebera.android.httpclient.client.cache.ResourceFactory;
import p008cz.msebera.android.httpclient.client.utils.DateUtils;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.CacheEntryUpdater */
class CacheEntryUpdater {
    private final ResourceFactory resourceFactory;

    CacheEntryUpdater() {
        this(new HeapResourceFactory());
    }

    CacheEntryUpdater(ResourceFactory resourceFactory2) {
        this.resourceFactory = resourceFactory2;
    }

    public HttpCacheEntry updateCacheEntry(String requestId, HttpCacheEntry entry, Date requestDate, Date responseDate, HttpResponse response) throws IOException {
        Args.check(response.getStatusLine().getStatusCode() == 304, "Response must have 304 status code");
        Header[] mergedHeaders = mergeHeaders(entry, response);
        Resource resource = null;
        if (entry.getResource() != null) {
            resource = this.resourceFactory.copy(requestId, entry.getResource());
        }
        HttpCacheEntry httpCacheEntry = new HttpCacheEntry(requestDate, responseDate, entry.getStatusLine(), mergedHeaders, resource);
        return httpCacheEntry;
    }

    /* access modifiers changed from: protected */
    public Header[] mergeHeaders(HttpCacheEntry entry, HttpResponse response) {
        if (entryAndResponseHaveDateHeader(entry, response) && entryDateHeaderNewerThenResponse(entry, response)) {
            return entry.getAllHeaders();
        }
        List<Header> cacheEntryHeaderList = new ArrayList<>(Arrays.asList(entry.getAllHeaders()));
        removeCacheHeadersThatMatchResponse(cacheEntryHeaderList, response);
        removeCacheEntry1xxWarnings(cacheEntryHeaderList, entry);
        cacheEntryHeaderList.addAll(Arrays.asList(response.getAllHeaders()));
        return (Header[]) cacheEntryHeaderList.toArray(new Header[cacheEntryHeaderList.size()]);
    }

    private void removeCacheHeadersThatMatchResponse(List<Header> cacheEntryHeaderList, HttpResponse response) {
        Header[] allHeaders;
        for (Header responseHeader : response.getAllHeaders()) {
            ListIterator<Header> cacheEntryHeaderListIter = cacheEntryHeaderList.listIterator();
            while (cacheEntryHeaderListIter.hasNext()) {
                if (((Header) cacheEntryHeaderListIter.next()).getName().equals(responseHeader.getName())) {
                    cacheEntryHeaderListIter.remove();
                }
            }
        }
    }

    private void removeCacheEntry1xxWarnings(List<Header> cacheEntryHeaderList, HttpCacheEntry entry) {
        ListIterator<Header> cacheEntryHeaderListIter = cacheEntryHeaderList.listIterator();
        while (cacheEntryHeaderListIter.hasNext()) {
            String str = "Warning";
            if (str.equals(((Header) cacheEntryHeaderListIter.next()).getName())) {
                for (Header cacheEntryWarning : entry.getHeaders(str)) {
                    if (cacheEntryWarning.getValue().startsWith(DiskLruCache.VERSION_1)) {
                        cacheEntryHeaderListIter.remove();
                    }
                }
            }
        }
    }

    private boolean entryDateHeaderNewerThenResponse(HttpCacheEntry entry, HttpResponse response) {
        String str = "Date";
        Date entryDate = DateUtils.parseDate(entry.getFirstHeader(str).getValue());
        Date responseDate = DateUtils.parseDate(response.getFirstHeader(str).getValue());
        if (entryDate == null || responseDate == null || !entryDate.after(responseDate)) {
            return false;
        }
        return true;
    }

    private boolean entryAndResponseHaveDateHeader(HttpCacheEntry entry, HttpResponse response) {
        String str = "Date";
        if (entry.getFirstHeader(str) == null || response.getFirstHeader(str) == null) {
            return false;
        }
        return true;
    }
}
