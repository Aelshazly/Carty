package okhttp3.internal.tls;

import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, mo24952d2 = {"Lokhttp3/internal/tls/BasicCertificateChainCleaner;", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustRootIndex", "Lokhttp3/internal/tls/TrustRootIndex;", "(Lokhttp3/internal/tls/TrustRootIndex;)V", "clean", "", "Ljava/security/cert/Certificate;", "chain", "hostname", "", "equals", "", "other", "", "hashCode", "", "verifySignature", "toVerify", "Ljava/security/cert/X509Certificate;", "signingCert", "Companion", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: BasicCertificateChainCleaner.kt */
public final class BasicCertificateChainCleaner extends CertificateChainCleaner {
    public static final Companion Companion = new Companion(null);
    private static final int MAX_SIGNERS = 9;
    private final TrustRootIndex trustRootIndex;

    @Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo24952d2 = {"Lokhttp3/internal/tls/BasicCertificateChainCleaner$Companion;", "", "()V", "MAX_SIGNERS", "", "okhttp"}, mo24953k = 1, mo24954mv = {1, 1, 15})
    /* compiled from: BasicCertificateChainCleaner.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    public BasicCertificateChainCleaner(TrustRootIndex trustRootIndex2) {
        Intrinsics.checkParameterIsNotNull(trustRootIndex2, "trustRootIndex");
        this.trustRootIndex = trustRootIndex2;
    }

    public List<Certificate> clean(List<? extends Certificate> chain, String hostname) throws SSLPeerUnverifiedException {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Intrinsics.checkParameterIsNotNull(hostname, "hostname");
        Deque queue = new ArrayDeque(chain);
        List result = new ArrayList();
        Object removeFirst = queue.removeFirst();
        Intrinsics.checkExpressionValueIsNotNull(removeFirst, "queue.removeFirst()");
        result.add(removeFirst);
        boolean foundTrustedCertificate = false;
        int c = 0;
        while (c < 9) {
            Object obj = result.get(result.size() - 1);
            String str = "null cannot be cast to non-null type java.security.cert.X509Certificate";
            if (obj != null) {
                X509Certificate toVerify = (X509Certificate) obj;
                X509Certificate trustedCert = this.trustRootIndex.findByIssuerAndSignature(toVerify);
                if (trustedCert != null) {
                    if (result.size() > 1 || (true ^ Intrinsics.areEqual((Object) toVerify, (Object) trustedCert))) {
                        result.add(trustedCert);
                    }
                    if (verifySignature(trustedCert, trustedCert)) {
                        return result;
                    }
                    foundTrustedCertificate = true;
                } else {
                    Iterator i = queue.iterator();
                    Intrinsics.checkExpressionValueIsNotNull(i, "queue.iterator()");
                    while (i.hasNext()) {
                        Object next = i.next();
                        if (next != null) {
                            X509Certificate signingCert = (X509Certificate) next;
                            if (verifySignature(toVerify, signingCert)) {
                                i.remove();
                                result.add(signingCert);
                            }
                        } else {
                            throw new TypeCastException(str);
                        }
                    }
                    if (foundTrustedCertificate) {
                        return result;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to find a trusted cert that signed ");
                    sb.append(toVerify);
                    throw new SSLPeerUnverifiedException(sb.toString());
                }
                c++;
            } else {
                throw new TypeCastException(str);
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Certificate chain too long: ");
        sb2.append(result);
        throw new SSLPeerUnverifiedException(sb2.toString());
    }

    private final boolean verifySignature(X509Certificate toVerify, X509Certificate signingCert) {
        boolean z = true;
        if (!Intrinsics.areEqual((Object) toVerify.getIssuerDN(), (Object) signingCert.getSubjectDN())) {
            return false;
        }
        try {
            toVerify.verify(signingCert.getPublicKey());
        } catch (GeneralSecurityException e) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return this.trustRootIndex.hashCode();
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof BasicCertificateChainCleaner) || !Intrinsics.areEqual((Object) ((BasicCertificateChainCleaner) other).trustRootIndex, (Object) this.trustRootIndex)) {
            return false;
        }
        return true;
    }
}
