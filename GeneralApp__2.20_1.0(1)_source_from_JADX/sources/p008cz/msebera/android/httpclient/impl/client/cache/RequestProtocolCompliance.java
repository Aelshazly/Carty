package p008cz.msebera.android.httpclient.impl.client.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import p008cz.msebera.android.httpclient.HttpRequest;
import p008cz.msebera.android.httpclient.HttpResponse;
import p008cz.msebera.android.httpclient.HttpStatus;
import p008cz.msebera.android.httpclient.HttpVersion;
import p008cz.msebera.android.httpclient.ProtocolVersion;
import p008cz.msebera.android.httpclient.client.ClientProtocolException;
import p008cz.msebera.android.httpclient.client.cache.HeaderConstants;
import p008cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import p008cz.msebera.android.httpclient.entity.AbstractHttpEntity;
import p008cz.msebera.android.httpclient.entity.ContentType;
import p008cz.msebera.android.httpclient.message.BasicHeader;
import p008cz.msebera.android.httpclient.message.BasicHttpResponse;
import p008cz.msebera.android.httpclient.message.BasicStatusLine;
import p008cz.msebera.android.httpclient.protocol.HTTP;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.RequestProtocolCompliance */
class RequestProtocolCompliance {
    private static final List<String> disallowedWithNoCache = Arrays.asList(new String[]{HeaderConstants.CACHE_CONTROL_MIN_FRESH, HeaderConstants.CACHE_CONTROL_MAX_STALE, "max-age"});
    private final boolean weakETagOnPutDeleteAllowed;

    /* renamed from: cz.msebera.android.httpclient.impl.client.cache.RequestProtocolCompliance$1 */
    static /* synthetic */ class C12851 {

        /* renamed from: $SwitchMap$cz$msebera$android$httpclient$impl$client$cache$RequestProtocolError */
        static final /* synthetic */ int[] f235x3102a434 = new int[RequestProtocolError.values().length];

        static {
            try {
                f235x3102a434[RequestProtocolError.BODY_BUT_NO_LENGTH_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f235x3102a434[RequestProtocolError.WEAK_ETAG_AND_RANGE_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f235x3102a434[RequestProtocolError.WEAK_ETAG_ON_PUTDELETE_METHOD_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f235x3102a434[RequestProtocolError.NO_CACHE_DIRECTIVE_WITH_FIELD_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public RequestProtocolCompliance() {
        this.weakETagOnPutDeleteAllowed = false;
    }

    public RequestProtocolCompliance(boolean weakETagOnPutDeleteAllowed2) {
        this.weakETagOnPutDeleteAllowed = weakETagOnPutDeleteAllowed2;
    }

    public List<RequestProtocolError> requestIsFatallyNonCompliant(HttpRequest request) {
        List<RequestProtocolError> theErrors = new ArrayList<>();
        RequestProtocolError anError = requestHasWeakETagAndRange(request);
        if (anError != null) {
            theErrors.add(anError);
        }
        if (!this.weakETagOnPutDeleteAllowed) {
            RequestProtocolError anError2 = requestHasWeekETagForPUTOrDELETEIfMatch(request);
            if (anError2 != null) {
                theErrors.add(anError2);
            }
        }
        RequestProtocolError anError3 = requestContainsNoCacheDirectiveWithFieldName(request);
        if (anError3 != null) {
            theErrors.add(anError3);
        }
        return theErrors;
    }

    public void makeRequestCompliant(HttpRequestWrapper request) throws ClientProtocolException {
        if (requestMustNotHaveEntity(request)) {
            ((HttpEntityEnclosingRequest) request).setEntity(null);
        }
        verifyRequestWithExpectContinueFlagHas100continueHeader(request);
        verifyOPTIONSRequestWithBodyHasContentType(request);
        decrementOPTIONSMaxForwardsIfGreaterThen0(request);
        stripOtherFreshnessDirectivesWithNoCache(request);
        if (requestVersionIsTooLow(request) || requestMinorVersionIsTooHighMajorVersionsMatch(request)) {
            request.setProtocolVersion(HttpVersion.HTTP_1_1);
        }
    }

    private void stripOtherFreshnessDirectivesWithNoCache(HttpRequest request) {
        HeaderElement[] elements;
        List<HeaderElement> outElts = new ArrayList<>();
        String str = "Cache-Control";
        Header[] headers = request.getHeaders(str);
        int length = headers.length;
        boolean shouldStrip = false;
        int i = 0;
        while (i < length) {
            boolean shouldStrip2 = shouldStrip;
            for (HeaderElement elt : headers[i].getElements()) {
                if (!disallowedWithNoCache.contains(elt.getName())) {
                    outElts.add(elt);
                }
                if (HeaderConstants.CACHE_CONTROL_NO_CACHE.equals(elt.getName())) {
                    shouldStrip2 = true;
                }
            }
            i++;
            shouldStrip = shouldStrip2;
        }
        if (shouldStrip) {
            request.removeHeaders(str);
            request.setHeader(str, buildHeaderFromElements(outElts));
        }
    }

    private String buildHeaderFromElements(List<HeaderElement> outElts) {
        StringBuilder newHdr = new StringBuilder("");
        boolean first = true;
        for (HeaderElement elt : outElts) {
            if (!first) {
                newHdr.append(",");
            } else {
                first = false;
            }
            newHdr.append(elt.toString());
        }
        return newHdr.toString();
    }

    private boolean requestMustNotHaveEntity(HttpRequest request) {
        return "TRACE".equals(request.getRequestLine().getMethod()) && (request instanceof HttpEntityEnclosingRequest);
    }

    private void decrementOPTIONSMaxForwardsIfGreaterThen0(HttpRequest request) {
        if ("OPTIONS".equals(request.getRequestLine().getMethod())) {
            String str = "Max-Forwards";
            Header maxForwards = request.getFirstHeader(str);
            if (maxForwards != null) {
                request.removeHeaders(str);
                request.setHeader(str, Integer.toString(Integer.parseInt(maxForwards.getValue()) - 1));
            }
        }
    }

    private void verifyOPTIONSRequestWithBodyHasContentType(HttpRequest request) {
        if ("OPTIONS".equals(request.getRequestLine().getMethod()) && (request instanceof HttpEntityEnclosingRequest)) {
            addContentTypeHeaderIfMissing((HttpEntityEnclosingRequest) request);
        }
    }

    private void addContentTypeHeaderIfMissing(HttpEntityEnclosingRequest request) {
        if (request.getEntity().getContentType() == null) {
            ((AbstractHttpEntity) request.getEntity()).setContentType(ContentType.APPLICATION_OCTET_STREAM.getMimeType());
        }
    }

    private void verifyRequestWithExpectContinueFlagHas100continueHeader(HttpRequest request) {
        if (!(request instanceof HttpEntityEnclosingRequest)) {
            remove100ContinueHeaderIfExists(request);
        } else if (!((HttpEntityEnclosingRequest) request).expectContinue() || ((HttpEntityEnclosingRequest) request).getEntity() == null) {
            remove100ContinueHeaderIfExists(request);
        } else {
            add100ContinueHeaderIfMissing(request);
        }
    }

    private void remove100ContinueHeaderIfExists(HttpRequest request) {
        HeaderElement[] elements;
        String str = "Expect";
        Header[] expectHeaders = request.getHeaders(str);
        List<HeaderElement> expectElementsThatAreNot100Continue = new ArrayList<>();
        int length = expectHeaders.length;
        boolean hasHeader = false;
        int i = 0;
        while (i < length) {
            Header h = expectHeaders[i];
            boolean hasHeader2 = hasHeader;
            for (HeaderElement elt : h.getElements()) {
                if (!HTTP.EXPECT_CONTINUE.equalsIgnoreCase(elt.getName())) {
                    expectElementsThatAreNot100Continue.add(elt);
                } else {
                    hasHeader2 = true;
                }
            }
            if (hasHeader2) {
                request.removeHeader(h);
                for (HeaderElement elt2 : expectElementsThatAreNot100Continue) {
                    request.addHeader(new BasicHeader(str, elt2.getName()));
                }
                return;
            }
            expectElementsThatAreNot100Continue = new ArrayList<>();
            i++;
            hasHeader = hasHeader2;
        }
    }

    private void add100ContinueHeaderIfMissing(HttpRequest request) {
        String str;
        String str2 = "Expect";
        Header[] headers = request.getHeaders(str2);
        int length = headers.length;
        boolean hasHeader = false;
        int i = 0;
        while (true) {
            str = HTTP.EXPECT_CONTINUE;
            if (i >= length) {
                break;
            }
            boolean hasHeader2 = hasHeader;
            for (HeaderElement elt : headers[i].getElements()) {
                if (str.equalsIgnoreCase(elt.getName())) {
                    hasHeader2 = true;
                }
            }
            i++;
            hasHeader = hasHeader2;
        }
        if (!hasHeader) {
            request.addHeader(str2, str);
        }
    }

    /* access modifiers changed from: protected */
    public boolean requestMinorVersionIsTooHighMajorVersionsMatch(HttpRequest request) {
        ProtocolVersion requestProtocol = request.getProtocolVersion();
        if (requestProtocol.getMajor() == HttpVersion.HTTP_1_1.getMajor() && requestProtocol.getMinor() > HttpVersion.HTTP_1_1.getMinor()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean requestVersionIsTooLow(HttpRequest request) {
        return request.getProtocolVersion().compareToVersion(HttpVersion.HTTP_1_1) < 0;
    }

    public HttpResponse getErrorForRequest(RequestProtocolError errorCheck) {
        int i = C12851.f235x3102a434[errorCheck.ordinal()];
        if (i == 1) {
            return new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_LENGTH_REQUIRED, ""));
        }
        if (i == 2) {
            return new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_BAD_REQUEST, "Weak eTag not compatible with byte range"));
        }
        if (i == 3) {
            return new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_BAD_REQUEST, "Weak eTag not compatible with PUT or DELETE requests"));
        }
        if (i == 4) {
            return new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_BAD_REQUEST, "No-Cache directive MUST NOT include a field name"));
        }
        throw new IllegalStateException("The request was compliant, therefore no error can be generated for it.");
    }

    private RequestProtocolError requestHasWeakETagAndRange(HttpRequest request) {
        if (!"GET".equals(request.getRequestLine().getMethod()) || request.getFirstHeader("Range") == null) {
            return null;
        }
        Header ifRange = request.getFirstHeader("If-Range");
        if (ifRange != null && ifRange.getValue().startsWith("W/")) {
            return RequestProtocolError.WEAK_ETAG_AND_RANGE_ERROR;
        }
        return null;
    }

    private RequestProtocolError requestHasWeekETagForPUTOrDELETEIfMatch(HttpRequest request) {
        String method = request.getRequestLine().getMethod();
        if (!"PUT".equals(method) && !"DELETE".equals(method)) {
            return null;
        }
        Header ifMatch = request.getFirstHeader("If-Match");
        String str = "W/";
        if (ifMatch == null) {
            Header ifNoneMatch = request.getFirstHeader("If-None-Match");
            if (ifNoneMatch != null && ifNoneMatch.getValue().startsWith(str)) {
                return RequestProtocolError.WEAK_ETAG_ON_PUTDELETE_METHOD_ERROR;
            }
        } else if (ifMatch.getValue().startsWith(str)) {
            return RequestProtocolError.WEAK_ETAG_ON_PUTDELETE_METHOD_ERROR;
        }
        return null;
    }

    private RequestProtocolError requestContainsNoCacheDirectiveWithFieldName(HttpRequest request) {
        HeaderElement[] elements;
        for (Header h : request.getHeaders("Cache-Control")) {
            for (HeaderElement elt : h.getElements()) {
                if (HeaderConstants.CACHE_CONTROL_NO_CACHE.equalsIgnoreCase(elt.getName()) && elt.getValue() != null) {
                    return RequestProtocolError.NO_CACHE_DIRECTIVE_WITH_FIELD_NAME;
                }
            }
        }
        return null;
    }
}
