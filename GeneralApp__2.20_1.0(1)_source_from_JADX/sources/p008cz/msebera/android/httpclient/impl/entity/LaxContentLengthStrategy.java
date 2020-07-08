package p008cz.msebera.android.httpclient.impl.entity;

import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.HeaderElement;
import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpMessage;
import p008cz.msebera.android.httpclient.ParseException;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import p008cz.msebera.android.httpclient.protocol.HTTP;
import p008cz.msebera.android.httpclient.util.Args;

/* renamed from: cz.msebera.android.httpclient.impl.entity.LaxContentLengthStrategy */
public class LaxContentLengthStrategy implements ContentLengthStrategy {
    public static final LaxContentLengthStrategy INSTANCE = new LaxContentLengthStrategy();
    private final int implicitLen;

    public LaxContentLengthStrategy(int implicitLen2) {
        this.implicitLen = implicitLen2;
    }

    public LaxContentLengthStrategy() {
        this(-1);
    }

    public long determineLength(HttpMessage message) throws HttpException {
        Args.notNull(message, "HTTP message");
        Header transferEncodingHeader = message.getFirstHeader("Transfer-Encoding");
        if (transferEncodingHeader != null) {
            try {
                HeaderElement[] encodings = transferEncodingHeader.getElements();
                int len = encodings.length;
                if (!HTTP.IDENTITY_CODING.equalsIgnoreCase(transferEncodingHeader.getValue()) && len > 0) {
                    if (HTTP.CHUNK_CODING.equalsIgnoreCase(encodings[len - 1].getName())) {
                        return -2;
                    }
                }
                return -1;
            } catch (ParseException px) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid Transfer-Encoding header value: ");
                sb.append(transferEncodingHeader);
                throw new ProtocolException(sb.toString(), px);
            }
        } else {
            String str = "Content-Length";
            if (message.getFirstHeader(str) == null) {
                return (long) this.implicitLen;
            }
            long contentlen = -1;
            Header[] headers = message.getHeaders(str);
            int i = headers.length - 1;
            while (true) {
                if (i < 0) {
                    break;
                }
                try {
                    contentlen = Long.parseLong(headers[i].getValue());
                    break;
                } catch (NumberFormatException e) {
                    i--;
                }
            }
            if (contentlen >= 0) {
                return contentlen;
            }
            return -1;
        }
    }
}
