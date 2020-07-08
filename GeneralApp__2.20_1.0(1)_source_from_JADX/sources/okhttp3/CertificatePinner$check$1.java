package okhttp3;

import java.security.cert.X509Certificate;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo24952d2 = {"<anonymous>", "", "Ljava/security/cert/X509Certificate;", "invoke"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* compiled from: CertificatePinner.kt */
final class CertificatePinner$check$1 extends Lambda implements Function0<List<? extends X509Certificate>> {
    final /* synthetic */ String $hostname;
    final /* synthetic */ List $peerCertificates;
    final /* synthetic */ CertificatePinner this$0;

    CertificatePinner$check$1(CertificatePinner certificatePinner, List list, String str) {
        this.this$0 = certificatePinner;
        this.$peerCertificates = list;
        this.$hostname = str;
        super(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0012, code lost:
        if (r0 != null) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.security.cert.X509Certificate> invoke() {
        /*
            r10 = this;
            okhttp3.CertificatePinner r0 = r10.this$0
            okhttp3.internal.tls.CertificateChainCleaner r0 = r0.getCertificateChainCleaner$okhttp()
            if (r0 == 0) goto L_0x0015
            java.util.List r1 = r10.$peerCertificates
            java.lang.String r2 = r10.$hostname
            java.util.List r0 = r0.clean(r1, r2)
            if (r0 == 0) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            java.util.List r0 = r10.$peerCertificates
        L_0x0017:
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            r1 = 0
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 10
            int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r3)
            r2.<init>(r3)
            java.util.Collection r2 = (java.util.Collection) r2
            r3 = r0
            r4 = 0
            java.util.Iterator r5 = r3.iterator()
        L_0x002d:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x004b
            java.lang.Object r6 = r5.next()
            r7 = r6
            java.security.cert.Certificate r7 = (java.security.cert.Certificate) r7
            r8 = 0
            if (r7 == 0) goto L_0x0043
            java.security.cert.X509Certificate r7 = (java.security.cert.X509Certificate) r7
            r2.add(r7)
            goto L_0x002d
        L_0x0043:
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
            java.lang.String r9 = "null cannot be cast to non-null type java.security.cert.X509Certificate"
            r5.<init>(r9)
            throw r5
        L_0x004b:
            r0 = r2
            java.util.List r0 = (java.util.List) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.CertificatePinner$check$1.invoke():java.util.List");
    }
}
