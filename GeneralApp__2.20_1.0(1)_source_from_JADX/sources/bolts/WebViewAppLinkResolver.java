package bolts;

import android.content.Context;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import bolts.AppLink.Target;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p008cz.msebera.android.httpclient.HttpHeaders;

public class WebViewAppLinkResolver implements AppLinkResolver {
    private static final String KEY_AL_VALUE = "value";
    private static final String KEY_ANDROID = "android";
    private static final String KEY_APP_NAME = "app_name";
    private static final String KEY_CLASS = "class";
    private static final String KEY_PACKAGE = "package";
    private static final String KEY_SHOULD_FALLBACK = "should_fallback";
    private static final String KEY_URL = "url";
    private static final String KEY_WEB = "web";
    private static final String KEY_WEB_URL = "url";
    private static final String META_TAG_PREFIX = "al";
    private static final String PREFER_HEADER = "Prefer-Html-Meta-Tags";
    private static final String TAG_EXTRACTION_JAVASCRIPT = "javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())";
    /* access modifiers changed from: private */
    public final Context context;

    public WebViewAppLinkResolver(Context context2) {
        this.context = context2;
    }

    public Task<AppLink> getAppLinkFromUrlInBackground(final Uri url) {
        final Capture<String> content = new Capture<>();
        final Capture<String> contentType = new Capture<>();
        return Task.callInBackground(new Callable<Void>() {
            public Void call() throws Exception {
                URL currentURL = new URL(url.toString());
                URLConnection connection = null;
                while (currentURL != null) {
                    connection = currentURL.openConnection();
                    if (connection instanceof HttpURLConnection) {
                        ((HttpURLConnection) connection).setInstanceFollowRedirects(true);
                    }
                    connection.setRequestProperty(WebViewAppLinkResolver.PREFER_HEADER, WebViewAppLinkResolver.META_TAG_PREFIX);
                    connection.connect();
                    if (connection instanceof HttpURLConnection) {
                        HttpURLConnection httpConnection = (HttpURLConnection) connection;
                        if (httpConnection.getResponseCode() < 300 || httpConnection.getResponseCode() >= 400) {
                            currentURL = null;
                        } else {
                            currentURL = new URL(httpConnection.getHeaderField(HttpHeaders.LOCATION));
                            httpConnection.disconnect();
                        }
                    } else {
                        currentURL = null;
                    }
                }
                try {
                    content.set(WebViewAppLinkResolver.readFromConnection(connection));
                    contentType.set(connection.getContentType());
                    return null;
                } finally {
                    if (connection instanceof HttpURLConnection) {
                        ((HttpURLConnection) connection).disconnect();
                    }
                }
            }
        }).onSuccessTask((Continuation<TResult, Task<TContinuationResult>>) new Continuation<Void, Task<JSONArray>>() {
            public Task<JSONArray> then(Task<Void> task) throws Exception {
                String inferredContentType;
                final TaskCompletionSource<JSONArray> tcs = new TaskCompletionSource<>();
                WebView webView = new WebView(WebViewAppLinkResolver.this.context);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setNetworkAvailable(false);
                webView.setWebViewClient(new WebViewClient() {
                    private boolean loaded = false;

                    private void runJavaScript(WebView view) {
                        if (!this.loaded) {
                            this.loaded = true;
                            view.loadUrl(WebViewAppLinkResolver.TAG_EXTRACTION_JAVASCRIPT);
                        }
                    }

                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        runJavaScript(view);
                    }

                    public void onLoadResource(WebView view, String url) {
                        super.onLoadResource(view, url);
                        runJavaScript(view);
                    }
                });
                webView.addJavascriptInterface(new Object() {
                    @JavascriptInterface
                    public void setValue(String value) {
                        try {
                            tcs.trySetResult(new JSONArray(value));
                        } catch (JSONException e) {
                            tcs.trySetError(e);
                        }
                    }
                }, "boltsWebViewAppLinkResolverResult");
                if (contentType.get() != null) {
                    inferredContentType = ((String) contentType.get()).split(";")[0];
                } else {
                    inferredContentType = null;
                }
                webView.loadDataWithBaseURL(url.toString(), (String) content.get(), inferredContentType, null, null);
                return tcs.getTask();
            }
        }, Task.UI_THREAD_EXECUTOR).onSuccess(new Continuation<JSONArray, AppLink>() {
            public AppLink then(Task<JSONArray> task) throws Exception {
                return WebViewAppLinkResolver.makeAppLinkFromAlData(WebViewAppLinkResolver.parseAlData((JSONArray) task.getResult()), url);
            }
        });
    }

    /* access modifiers changed from: private */
    public static Map<String, Object> parseAlData(JSONArray dataArray) throws JSONException {
        Map<String, Object> al = new HashMap<>();
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject tag = dataArray.getJSONObject(i);
            String[] nameComponents = tag.getString("property").split(":");
            if (nameComponents[0].equals(META_TAG_PREFIX)) {
                Map<String, Object> root = al;
                int j = 1;
                while (true) {
                    Map<String, Object> child = null;
                    if (j >= nameComponents.length) {
                        break;
                    }
                    List list = (List) root.get(nameComponents[j]);
                    if (list == null) {
                        list = new ArrayList();
                        root.put(nameComponents[j], list);
                    }
                    if (list.size() > 0) {
                        child = (Map) list.get(list.size() - 1);
                    }
                    if (child == null || j == nameComponents.length - 1) {
                        child = new HashMap<>();
                        list.add(child);
                    }
                    root = child;
                    j++;
                }
                String str = Param.CONTENT;
                if (tag.has(str)) {
                    String str2 = "value";
                    if (tag.isNull(str)) {
                        root.put(str2, null);
                    } else {
                        root.put(str2, tag.getString(str));
                    }
                }
            }
        }
        return al;
    }

    private static List<Map<String, Object>> getAlList(Map<String, Object> map, String key) {
        List<Map<String, Object>> result = (List) map.get(key);
        if (result == null) {
            return Collections.emptyList();
        }
        return result;
    }

    /* access modifiers changed from: private */
    public static AppLink makeAppLinkFromAlData(Map<String, Object> appLinkDict, Uri destination) {
        String str;
        String str2;
        Map<String, Object> map = appLinkDict;
        List<Target> targets = new ArrayList<>();
        List list = (List) map.get("android");
        if (list == null) {
            list = Collections.emptyList();
        }
        Iterator i$ = list.iterator();
        while (true) {
            boolean hasNext = i$.hasNext();
            str = ImagesContract.URL;
            str2 = "value";
            if (!hasNext) {
                break;
            }
            Map<String, Object> platformMap = (Map) i$.next();
            List<Map<String, Object>> urls = getAlList(platformMap, str);
            List<Map<String, Object>> packages = getAlList(platformMap, KEY_PACKAGE);
            List<Map<String, Object>> classes = getAlList(platformMap, KEY_CLASS);
            List<Map<String, Object>> appNames = getAlList(platformMap, KEY_APP_NAME);
            int maxCount = Math.max(urls.size(), Math.max(packages.size(), Math.max(classes.size(), appNames.size())));
            int i = 0;
            while (i < maxCount) {
                List list2 = list;
                Iterator i$2 = i$;
                targets.add(new Target((String) (packages.size() > i ? ((Map) packages.get(i)).get(str2) : null), (String) (classes.size() > i ? ((Map) classes.get(i)).get(str2) : null), tryCreateUrl((String) (urls.size() > i ? ((Map) urls.get(i)).get(str2) : null)), (String) (appNames.size() > i ? ((Map) appNames.get(i)).get(str2) : null)));
                i++;
                i$ = i$2;
                list = list2;
            }
            List list3 = list;
            Iterator it = i$;
        }
        List list4 = list;
        Iterator it2 = i$;
        Uri webUrl = destination;
        List<Map<String, Object>> webMapList = (List) map.get(KEY_WEB);
        if (webMapList != null && webMapList.size() > 0) {
            Map<String, Object> webMap = (Map) webMapList.get(0);
            List<Map<String, Object>> urls2 = (List) webMap.get(str);
            List<Map<String, Object>> shouldFallbacks = (List) webMap.get(KEY_SHOULD_FALLBACK);
            if (shouldFallbacks != null && shouldFallbacks.size() > 0) {
                if (Arrays.asList(new String[]{"no", "false", "0"}).contains(((String) ((Map) shouldFallbacks.get(0)).get(str2)).toLowerCase())) {
                    webUrl = null;
                }
            }
            if (!(webUrl == null || urls2 == null || urls2.size() <= 0)) {
                webUrl = tryCreateUrl((String) ((Map) urls2.get(0)).get(str2));
            }
        }
        return new AppLink(destination, targets, webUrl);
    }

    private static Uri tryCreateUrl(String urlString) {
        if (urlString == null) {
            return null;
        }
        return Uri.parse(urlString);
    }

    /* access modifiers changed from: private */
    public static String readFromConnection(URLConnection connection) throws IOException {
        InputStream stream;
        String str = "charset=";
        if (connection instanceof HttpURLConnection) {
            try {
                stream = connection.getInputStream();
            } catch (Exception e) {
                stream = ((HttpURLConnection) connection).getErrorStream();
            }
        } else {
            stream = connection.getInputStream();
        }
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int read = stream.read(buffer);
                int read2 = read;
                if (read == -1) {
                    break;
                }
                output.write(buffer, 0, read2);
            }
            String charset = connection.getContentEncoding();
            if (charset == null) {
                String[] arr$ = connection.getContentType().split(";");
                int len$ = arr$.length;
                int i$ = 0;
                while (true) {
                    if (i$ >= len$) {
                        break;
                    }
                    String part = arr$[i$].trim();
                    if (part.startsWith(str)) {
                        charset = part.substring(str.length());
                        break;
                    }
                    i$++;
                }
                if (charset == null) {
                    charset = "UTF-8";
                }
            }
            return new String(output.toByteArray(), charset);
        } finally {
            stream.close();
        }
    }
}
