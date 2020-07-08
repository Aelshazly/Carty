package com.navibees.navigatorapp.utils;

import android.net.Uri;
import android.net.Uri.Builder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.internal.cache.DiskLruCache;
import org.json.JSONException;
import org.json.JSONObject;
import p008cz.msebera.android.httpclient.Header;
import p008cz.msebera.android.httpclient.entity.StringEntity;
import p008cz.msebera.android.httpclient.message.BasicHeader;

public class ShareUtility {
    public static String generateQueryParameters(Map<String, String> customParameters) throws UnsupportedEncodingException {
        StringBuilder queryParameters = new StringBuilder();
        if (!customParameters.isEmpty()) {
            for (Entry<String, String> parameter : customParameters.entrySet()) {
                queryParameters.append(String.format("&%1s=%2s", new Object[]{parameter.getKey(), ((String) parameter.getValue()).trim()}));
            }
        }
        queryParameters.deleteCharAt(0);
        return queryParameters.toString().replace(" ", "");
    }

    public static Uri buildFirebaseDynamicDeepLink(Uri deepLink, String appCode, String androidPackageName, int minVersion, boolean isAd, String iOSPackageName, String iOSAppStoreID) {
        Builder scheme = new Builder().scheme("https");
        StringBuilder sb = new StringBuilder();
        sb.append(appCode);
        sb.append(".app.goo.gl");
        Builder builder = scheme.authority(sb.toString()).path("/").appendQueryParameter("link", deepLink.toString()).appendQueryParameter("apn", androidPackageName);
        if (isAd) {
            builder.appendQueryParameter("ad", DiskLruCache.VERSION_1);
        }
        if (minVersion > 0) {
            builder.appendQueryParameter("amv", Integer.toString(minVersion));
        }
        builder.appendQueryParameter("ibi", iOSPackageName);
        builder.appendQueryParameter("isi", iOSAppStoreID);
        return builder.build();
    }

    public static void generateShortLink(final String longDynamicLink, String apiKey, final ShortLinkCallback callback) throws JSONException {
        StringEntity entity;
        StringBuilder sb = new StringBuilder();
        sb.append("https://firebasedynamiclinks.googleapis.com/v1/shortLinks?key=");
        sb.append(apiKey);
        String url = sb.toString();
        JSONObject body = new JSONObject();
        body.put("longDynamicLink", longDynamicLink);
        StringEntity entity2 = null;
        try {
            entity2 = new StringEntity(body.toString());
            entity2.setContentType((Header) new BasicHeader("Content-Type", "application/json"));
            entity = entity2;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            entity = entity2;
        }
        String str = url;
        StringEntity stringEntity = entity;
        new AsyncHttpClient().post(null, str, stringEntity, "application/json", new AsyncHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    callback.onSuccess(new JSONObject(new String(responseBody)).optString("shortLink"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFail(longDynamicLink);
                }
            }

            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (responseBody != null) {
                    callback.onFail(new String(responseBody));
                } else {
                    callback.onFail("");
                }
            }
        });
    }

    public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        String[] pairs;
        Map<String, String> query_pairs = new LinkedHashMap<>();
        for (String pair : url.getQuery().split("&")) {
            int idx = pair.indexOf("=");
            String str = "UTF-8";
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), str), URLDecoder.decode(pair.substring(idx + 1), str));
        }
        return query_pairs;
    }
}
