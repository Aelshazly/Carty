package okio;

import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 2}, mo24951d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b¨\u0006\u0005"}, mo24952d2 = {"deflate", "Lokio/DeflaterSink;", "Lokio/Sink;", "deflater", "Ljava/util/zip/Deflater;", "jvm"}, mo24953k = 2, mo24954mv = {1, 1, 11})
/* renamed from: okio.-DeflaterSinkExtensions reason: invalid class name */
/* compiled from: DeflaterSink.kt */
public final class DeflaterSinkExtensions {
    public static /* bridge */ /* synthetic */ DeflaterSink deflate$default(Sink $receiver, Deflater deflater, int i, Object obj) {
        if ((i & 1) != 0) {
            deflater = new Deflater();
        }
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(deflater, "deflater");
        return new DeflaterSink($receiver, deflater);
    }

    public static final DeflaterSink deflate(Sink $receiver, Deflater deflater) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(deflater, "deflater");
        return new DeflaterSink($receiver, deflater);
    }
}
