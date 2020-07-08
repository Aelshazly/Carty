package p008cz.msebera.android.httpclient.impl.entity;

import p008cz.msebera.android.httpclient.HttpException;
import p008cz.msebera.android.httpclient.HttpMessage;
import p008cz.msebera.android.httpclient.ProtocolException;
import p008cz.msebera.android.httpclient.entity.ContentLengthStrategy;

/* renamed from: cz.msebera.android.httpclient.impl.entity.DisallowIdentityContentLengthStrategy */
public class DisallowIdentityContentLengthStrategy implements ContentLengthStrategy {
    public static final DisallowIdentityContentLengthStrategy INSTANCE = new DisallowIdentityContentLengthStrategy(new LaxContentLengthStrategy(0));
    private final ContentLengthStrategy contentLengthStrategy;

    public DisallowIdentityContentLengthStrategy(ContentLengthStrategy contentLengthStrategy2) {
        this.contentLengthStrategy = contentLengthStrategy2;
    }

    public long determineLength(HttpMessage message) throws HttpException {
        long result = this.contentLengthStrategy.determineLength(message);
        if (result != -1) {
            return result;
        }
        throw new ProtocolException("Identity transfer encoding cannot be used");
    }
}
