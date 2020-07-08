package okhttp3.internal.connection;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.Handshake;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo24952d2 = {"<anonymous>", "", "Ljava/security/cert/X509Certificate;", "invoke"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* compiled from: RealConnection.kt */
final class RealConnection$connectTls$2 extends Lambda implements Function0<List<? extends X509Certificate>> {
    final /* synthetic */ RealConnection this$0;

    RealConnection$connectTls$2(RealConnection realConnection) {
        this.this$0 = realConnection;
        super(0);
    }

    public final List<X509Certificate> invoke() {
        Handshake access$getHandshake$p = this.this$0.handshake;
        if (access$getHandshake$p == null) {
            Intrinsics.throwNpe();
        }
        Iterable<Certificate> $this$map$iv = access$getHandshake$p.peerCertificates();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Certificate it : $this$map$iv) {
            if (it != null) {
                destination$iv$iv.add((X509Certificate) it);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.security.cert.X509Certificate");
            }
        }
        return (List) destination$iv$iv;
    }
}
