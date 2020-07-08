package p008cz.msebera.android.httpclient.impl.client.cache;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Formatter;
import java.util.Locale;

/* renamed from: cz.msebera.android.httpclient.impl.client.cache.BasicIdGenerator */
class BasicIdGenerator {
    private long count;
    private final String hostname;
    private final SecureRandom rnd;

    public BasicIdGenerator() {
        String hostname2;
        try {
            hostname2 = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            hostname2 = "localhost";
        }
        this.hostname = hostname2;
        try {
            this.rnd = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException ex) {
            throw new Error(ex);
        }
    }

    public synchronized void generate(StringBuilder buffer) {
        this.count++;
        int rndnum = this.rnd.nextInt();
        buffer.append(System.currentTimeMillis());
        buffer.append('.');
        Formatter formatter = new Formatter(buffer, Locale.US);
        formatter.format("%1$016x-%2$08x", new Object[]{Long.valueOf(this.count), Integer.valueOf(rndnum)});
        formatter.close();
        buffer.append('.');
        buffer.append(this.hostname);
    }

    public String generate() {
        StringBuilder buffer = new StringBuilder();
        generate(buffer);
        return buffer.toString();
    }
}
