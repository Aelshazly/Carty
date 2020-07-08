package p008cz.msebera.android.httpclient.client.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p008cz.msebera.android.httpclient.Consts;
import p008cz.msebera.android.httpclient.NameValuePair;
import p008cz.msebera.android.httpclient.conn.util.InetAddressUtils;
import p008cz.msebera.android.httpclient.message.BasicNameValuePair;

/* renamed from: cz.msebera.android.httpclient.client.utils.URIBuilder */
public class URIBuilder {
    private String encodedAuthority;
    private String encodedFragment;
    private String encodedPath;
    private String encodedQuery;
    private String encodedSchemeSpecificPart;
    private String encodedUserInfo;
    private String fragment;
    private String host;
    private String path;
    private int port;
    private String query;
    private List<NameValuePair> queryParams;
    private String scheme;
    private String userInfo;

    public URIBuilder() {
        this.port = -1;
    }

    public URIBuilder(String string) throws URISyntaxException {
        digestURI(new URI(string));
    }

    public URIBuilder(URI uri) {
        digestURI(uri);
    }

    private List<NameValuePair> parseQuery(String query2, Charset charset) {
        if (query2 == null || query2.length() <= 0) {
            return null;
        }
        return URLEncodedUtils.parse(query2, charset);
    }

    public URI build() throws URISyntaxException {
        return new URI(buildString());
    }

    private String buildString() {
        StringBuilder sb = new StringBuilder();
        String str = this.scheme;
        if (str != null) {
            sb.append(str);
            sb.append(':');
        }
        String str2 = this.encodedSchemeSpecificPart;
        if (str2 != null) {
            sb.append(str2);
        } else {
            String str3 = "//";
            if (this.encodedAuthority != null) {
                sb.append(str3);
                sb.append(this.encodedAuthority);
            } else if (this.host != null) {
                sb.append(str3);
                String str4 = this.encodedUserInfo;
                String str5 = "@";
                if (str4 != null) {
                    sb.append(str4);
                    sb.append(str5);
                } else {
                    String str6 = this.userInfo;
                    if (str6 != null) {
                        sb.append(encodeUserInfo(str6));
                        sb.append(str5);
                    }
                }
                if (InetAddressUtils.isIPv6Address(this.host)) {
                    sb.append("[");
                    sb.append(this.host);
                    sb.append("]");
                } else {
                    sb.append(this.host);
                }
                if (this.port >= 0) {
                    sb.append(":");
                    sb.append(this.port);
                }
            }
            String str7 = this.encodedPath;
            if (str7 != null) {
                sb.append(normalizePath(str7));
            } else {
                String str8 = this.path;
                if (str8 != null) {
                    sb.append(encodePath(normalizePath(str8)));
                }
            }
            String str9 = "?";
            if (this.encodedQuery != null) {
                sb.append(str9);
                sb.append(this.encodedQuery);
            } else if (this.queryParams != null) {
                sb.append(str9);
                sb.append(encodeUrlForm(this.queryParams));
            } else if (this.query != null) {
                sb.append(str9);
                sb.append(encodeUric(this.query));
            }
        }
        String str10 = "#";
        if (this.encodedFragment != null) {
            sb.append(str10);
            sb.append(this.encodedFragment);
        } else if (this.fragment != null) {
            sb.append(str10);
            sb.append(encodeUric(this.fragment));
        }
        return sb.toString();
    }

    private void digestURI(URI uri) {
        this.scheme = uri.getScheme();
        this.encodedSchemeSpecificPart = uri.getRawSchemeSpecificPart();
        this.encodedAuthority = uri.getRawAuthority();
        this.host = uri.getHost();
        this.port = uri.getPort();
        this.encodedUserInfo = uri.getRawUserInfo();
        this.userInfo = uri.getUserInfo();
        this.encodedPath = uri.getRawPath();
        this.path = uri.getPath();
        this.encodedQuery = uri.getRawQuery();
        this.queryParams = parseQuery(uri.getRawQuery(), Consts.UTF_8);
        this.encodedFragment = uri.getRawFragment();
        this.fragment = uri.getFragment();
    }

    private String encodeUserInfo(String userInfo2) {
        return URLEncodedUtils.encUserInfo(userInfo2, Consts.UTF_8);
    }

    private String encodePath(String path2) {
        return URLEncodedUtils.encPath(path2, Consts.UTF_8);
    }

    private String encodeUrlForm(List<NameValuePair> params) {
        return URLEncodedUtils.format((Iterable<? extends NameValuePair>) params, Consts.UTF_8);
    }

    private String encodeUric(String fragment2) {
        return URLEncodedUtils.encUric(fragment2, Consts.UTF_8);
    }

    public URIBuilder setScheme(String scheme2) {
        this.scheme = scheme2;
        return this;
    }

    public URIBuilder setUserInfo(String userInfo2) {
        this.userInfo = userInfo2;
        this.encodedSchemeSpecificPart = null;
        this.encodedAuthority = null;
        this.encodedUserInfo = null;
        return this;
    }

    public URIBuilder setUserInfo(String username, String password) {
        StringBuilder sb = new StringBuilder();
        sb.append(username);
        sb.append(':');
        sb.append(password);
        return setUserInfo(sb.toString());
    }

    public URIBuilder setHost(String host2) {
        this.host = host2;
        this.encodedSchemeSpecificPart = null;
        this.encodedAuthority = null;
        return this;
    }

    public URIBuilder setPort(int port2) {
        this.port = port2 < 0 ? -1 : port2;
        this.encodedSchemeSpecificPart = null;
        this.encodedAuthority = null;
        return this;
    }

    public URIBuilder setPath(String path2) {
        this.path = path2;
        this.encodedSchemeSpecificPart = null;
        this.encodedPath = null;
        return this;
    }

    public URIBuilder removeQuery() {
        this.queryParams = null;
        this.query = null;
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        return this;
    }

    @Deprecated
    public URIBuilder setQuery(String query2) {
        this.queryParams = parseQuery(query2, Consts.UTF_8);
        this.query = null;
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        return this;
    }

    public URIBuilder setParameters(List<NameValuePair> nvps) {
        List<NameValuePair> list = this.queryParams;
        if (list == null) {
            this.queryParams = new ArrayList();
        } else {
            list.clear();
        }
        this.queryParams.addAll(nvps);
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.query = null;
        return this;
    }

    public URIBuilder addParameters(List<NameValuePair> nvps) {
        if (this.queryParams == null) {
            this.queryParams = new ArrayList();
        }
        this.queryParams.addAll(nvps);
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.query = null;
        return this;
    }

    public URIBuilder setParameters(NameValuePair... nvps) {
        List<NameValuePair> list = this.queryParams;
        if (list == null) {
            this.queryParams = new ArrayList();
        } else {
            list.clear();
        }
        for (NameValuePair nvp : nvps) {
            this.queryParams.add(nvp);
        }
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.query = null;
        return this;
    }

    public URIBuilder addParameter(String param, String value) {
        if (this.queryParams == null) {
            this.queryParams = new ArrayList();
        }
        this.queryParams.add(new BasicNameValuePair(param, value));
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.query = null;
        return this;
    }

    public URIBuilder setParameter(String param, String value) {
        if (this.queryParams == null) {
            this.queryParams = new ArrayList();
        }
        if (!this.queryParams.isEmpty()) {
            Iterator<NameValuePair> it = this.queryParams.iterator();
            while (it.hasNext()) {
                if (((NameValuePair) it.next()).getName().equals(param)) {
                    it.remove();
                }
            }
        }
        this.queryParams.add(new BasicNameValuePair(param, value));
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.query = null;
        return this;
    }

    public URIBuilder clearParameters() {
        this.queryParams = null;
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        return this;
    }

    public URIBuilder setCustomQuery(String query2) {
        this.query = query2;
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.queryParams = null;
        return this;
    }

    public URIBuilder setFragment(String fragment2) {
        this.fragment = fragment2;
        this.encodedFragment = null;
        return this;
    }

    public boolean isAbsolute() {
        return this.scheme != null;
    }

    public boolean isOpaque() {
        return this.path == null;
    }

    public String getScheme() {
        return this.scheme;
    }

    public String getUserInfo() {
        return this.userInfo;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getPath() {
        return this.path;
    }

    public List<NameValuePair> getQueryParams() {
        List<NameValuePair> list = this.queryParams;
        if (list != null) {
            return new ArrayList(list);
        }
        return new ArrayList();
    }

    public String getFragment() {
        return this.fragment;
    }

    public String toString() {
        return buildString();
    }

    private static String normalizePath(String path2) {
        String s = path2;
        if (s == null) {
            return null;
        }
        int n = 0;
        while (n < s.length() && s.charAt(n) == '/') {
            n++;
        }
        if (n > 1) {
            s = s.substring(n - 1);
        }
        return s;
    }
}
