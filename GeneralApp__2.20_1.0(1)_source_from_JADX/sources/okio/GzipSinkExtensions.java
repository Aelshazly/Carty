package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\b¨\u0006\u0003"}, mo24952d2 = {"gzip", "Lokio/GzipSink;", "Lokio/Sink;", "jvm"}, mo24953k = 2, mo24954mv = {1, 1, 11})
/* renamed from: okio.-GzipSinkExtensions reason: invalid class name */
/* compiled from: GzipSink.kt */
public final class GzipSinkExtensions {
    public static final GzipSink gzip(Sink $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new GzipSink($receiver);
    }
}
