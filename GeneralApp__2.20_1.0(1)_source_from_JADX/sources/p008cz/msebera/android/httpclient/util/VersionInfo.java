package p008cz.msebera.android.httpclient.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/* renamed from: cz.msebera.android.httpclient.util.VersionInfo */
public class VersionInfo {
    public static final String PROPERTY_MODULE = "info.module";
    public static final String PROPERTY_RELEASE = "info.release";
    public static final String PROPERTY_TIMESTAMP = "info.timestamp";
    public static final String UNAVAILABLE = "UNAVAILABLE";
    public static final String VERSION_PROPERTY_FILE = "version.properties";
    private final String infoClassloader;
    private final String infoModule;
    private final String infoPackage;
    private final String infoRelease;
    private final String infoTimestamp;

    protected VersionInfo(String pckg, String module, String release, String time, String clsldr) {
        Args.notNull(pckg, "Package identifier");
        this.infoPackage = pckg;
        String str = UNAVAILABLE;
        this.infoModule = module != null ? module : str;
        this.infoRelease = release != null ? release : str;
        this.infoTimestamp = time != null ? time : str;
        if (clsldr != null) {
            str = clsldr;
        }
        this.infoClassloader = str;
    }

    public final String getPackage() {
        return this.infoPackage;
    }

    public final String getModule() {
        return this.infoModule;
    }

    public final String getRelease() {
        return this.infoRelease;
    }

    public final String getTimestamp() {
        return this.infoTimestamp;
    }

    public final String getClassloader() {
        return this.infoClassloader;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.infoPackage.length() + 20 + this.infoModule.length() + this.infoRelease.length() + this.infoTimestamp.length() + this.infoClassloader.length());
        sb.append("VersionInfo(");
        sb.append(this.infoPackage);
        sb.append(':');
        sb.append(this.infoModule);
        String str = this.infoRelease;
        String str2 = UNAVAILABLE;
        if (!str2.equals(str)) {
            sb.append(':');
            sb.append(this.infoRelease);
        }
        if (!str2.equals(this.infoTimestamp)) {
            sb.append(':');
            sb.append(this.infoTimestamp);
        }
        sb.append(')');
        if (!str2.equals(this.infoClassloader)) {
            sb.append('@');
            sb.append(this.infoClassloader);
        }
        return sb.toString();
    }

    public static VersionInfo[] loadVersionInfo(String[] pckgs, ClassLoader clsldr) {
        Args.notNull(pckgs, "Package identifier array");
        List<VersionInfo> vil = new ArrayList<>(pckgs.length);
        for (String pckg : pckgs) {
            VersionInfo vi = loadVersionInfo(pckg, clsldr);
            if (vi != null) {
                vil.add(vi);
            }
        }
        return (VersionInfo[]) vil.toArray(new VersionInfo[vil.size()]);
    }

    public static VersionInfo loadVersionInfo(String pckg, ClassLoader clsldr) {
        InputStream is;
        Args.notNull(pckg, "Package identifier");
        ClassLoader cl = clsldr != null ? clsldr : Thread.currentThread().getContextClassLoader();
        Properties vip = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(pckg.replace('.', '/'));
            sb.append("/");
            sb.append(VERSION_PROPERTY_FILE);
            is = cl.getResourceAsStream(sb.toString());
            if (is != null) {
                Properties props = new Properties();
                props.load(is);
                vip = props;
                is.close();
            }
        } catch (IOException e) {
        } catch (Throwable th) {
            is.close();
            throw th;
        }
        if (vip != null) {
            return fromMap(pckg, vip, cl);
        }
        return null;
    }

    protected static VersionInfo fromMap(String pckg, Map<?, ?> info, ClassLoader clsldr) {
        String clsldrstr;
        Args.notNull(pckg, "Package identifier");
        String module = null;
        String release = null;
        String timestamp = null;
        if (info != null) {
            module = (String) info.get(PROPERTY_MODULE);
            if (module != null && module.length() < 1) {
                module = null;
            }
            release = (String) info.get(PROPERTY_RELEASE);
            if (release != null && (release.length() < 1 || release.equals("${pom.version}"))) {
                release = null;
            }
            timestamp = (String) info.get(PROPERTY_TIMESTAMP);
            if (timestamp != null && (timestamp.length() < 1 || timestamp.equals("${mvn.timestamp}"))) {
                timestamp = null;
            }
        }
        if (clsldr != null) {
            clsldrstr = clsldr.toString();
        } else {
            clsldrstr = null;
        }
        VersionInfo versionInfo = new VersionInfo(pckg, module, release, timestamp, clsldrstr);
        return versionInfo;
    }

    public static String getUserAgent(String name, String pkg, Class<?> cls) {
        VersionInfo vi = loadVersionInfo(pkg, cls.getClassLoader());
        String release = vi != null ? vi.getRelease() : UNAVAILABLE;
        String javaVersion = System.getProperty("java.version");
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("/");
        sb.append(release);
        sb.append(" (Java 1.5 minimum; Java/");
        sb.append(javaVersion);
        sb.append(")");
        return sb.toString();
    }
}
