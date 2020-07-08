package com.google.firebase.dynamiclinks;

import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.dynamiclinks.internal.zze;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
public final class DynamicLink {
    private final Bundle zze;

    /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
    public static final class AndroidParameters {
        final Bundle zzf;

        /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
        public static final class Builder {
            private final Bundle zzf;

            public Builder() {
                if (FirebaseApp.getInstance() != null) {
                    this.zzf = new Bundle();
                    this.zzf.putString("apn", FirebaseApp.getInstance().getApplicationContext().getPackageName());
                    return;
                }
                throw new IllegalStateException("FirebaseApp not initialized.");
            }

            public Builder(String str) {
                this.zzf = new Bundle();
                this.zzf.putString("apn", str);
            }

            public final Builder setFallbackUrl(Uri uri) {
                this.zzf.putParcelable("afl", uri);
                return this;
            }

            public final Uri getFallbackUrl() {
                Uri uri = (Uri) this.zzf.getParcelable("afl");
                if (uri == null) {
                    return Uri.EMPTY;
                }
                return uri;
            }

            public final Builder setMinimumVersion(int i) {
                this.zzf.putInt("amv", i);
                return this;
            }

            public final int getMinimumVersion() {
                return this.zzf.getInt("amv");
            }

            public final AndroidParameters build() {
                return new AndroidParameters(this.zzf);
            }
        }

        private AndroidParameters(Bundle bundle) {
            this.zzf = bundle;
        }
    }

    /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
    public static final class Builder {
        private final Bundle zze = new Bundle();
        private final zze zzg;
        private final Bundle zzh;

        public Builder(zze zze2) {
            this.zzg = zze2;
            if (FirebaseApp.getInstance() != null) {
                this.zze.putString("apiKey", FirebaseApp.getInstance().getOptions().getApiKey());
            }
            this.zzh = new Bundle();
            this.zze.putBundle("parameters", this.zzh);
        }

        public final Builder setLongLink(Uri uri) {
            this.zze.putParcelable("dynamicLink", uri);
            return this;
        }

        public final Uri getLongLink() {
            Uri uri = (Uri) this.zzh.getParcelable("dynamicLink");
            if (uri == null) {
                return Uri.EMPTY;
            }
            return uri;
        }

        public final Builder setLink(Uri uri) {
            this.zzh.putParcelable("link", uri);
            return this;
        }

        public final Uri getLink() {
            Uri uri = (Uri) this.zzh.getParcelable("link");
            if (uri == null) {
                return Uri.EMPTY;
            }
            return uri;
        }

        @Deprecated
        public final Builder setDynamicLinkDomain(String str) {
            if (str.matches("(https:\\/\\/)?[a-z0-9]{3,}\\.app\\.goo\\.gl$") || str.matches("(https:\\/\\/)?[a-z0-9]{3,}\\.page\\.link$")) {
                this.zze.putString(ClientCookie.DOMAIN_ATTR, str);
                Bundle bundle = this.zze;
                String valueOf = String.valueOf("https://");
                String valueOf2 = String.valueOf(str);
                bundle.putString("domainUriPrefix", valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
                return this;
            }
            throw new IllegalArgumentException("Use setDomainUriPrefix() instead, setDynamicLinkDomain() is only applicable for *.page.link and *.app.goo.gl domains.");
        }

        public final Builder setDomainUriPrefix(String str) {
            if (str.matches("(https:\\/\\/)?[a-z0-9]{3,}\\.app\\.goo\\.gl$") || str.matches("(https:\\/\\/)?[a-z0-9]{3,}\\.page\\.link$")) {
                this.zze.putString(ClientCookie.DOMAIN_ATTR, str.replace("https://", ""));
            }
            this.zze.putString("domainUriPrefix", str);
            return this;
        }

        public final String getDomainUriPrefix() {
            return this.zze.getString("domainUriPrefix", "");
        }

        public final Builder setAndroidParameters(AndroidParameters androidParameters) {
            this.zzh.putAll(androidParameters.zzf);
            return this;
        }

        public final Builder setIosParameters(IosParameters iosParameters) {
            this.zzh.putAll(iosParameters.zzf);
            return this;
        }

        public final Builder setGoogleAnalyticsParameters(GoogleAnalyticsParameters googleAnalyticsParameters) {
            this.zzh.putAll(googleAnalyticsParameters.zzf);
            return this;
        }

        public final Builder setItunesConnectAnalyticsParameters(ItunesConnectAnalyticsParameters itunesConnectAnalyticsParameters) {
            this.zzh.putAll(itunesConnectAnalyticsParameters.zzf);
            return this;
        }

        public final Builder setSocialMetaTagParameters(SocialMetaTagParameters socialMetaTagParameters) {
            this.zzh.putAll(socialMetaTagParameters.zzf);
            return this;
        }

        public final Builder setNavigationInfoParameters(NavigationInfoParameters navigationInfoParameters) {
            this.zzh.putAll(navigationInfoParameters.zzf);
            return this;
        }

        public final DynamicLink buildDynamicLink() {
            zze.zzb(this.zze);
            return new DynamicLink(this.zze);
        }

        public final Task<ShortDynamicLink> buildShortDynamicLink() {
            zzb();
            return this.zzg.zza(this.zze);
        }

        public final Task<ShortDynamicLink> buildShortDynamicLink(int i) {
            zzb();
            this.zze.putInt("suffix", i);
            return this.zzg.zza(this.zze);
        }

        private final void zzb() {
            if (this.zze.getString("apiKey") == null) {
                throw new IllegalArgumentException("Missing API key. Set with setApiKey().");
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
    public static final class GoogleAnalyticsParameters {
        Bundle zzf;

        /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
        public static final class Builder {
            private final Bundle zzf = new Bundle();

            public Builder() {
            }

            public Builder(String str, String str2, String str3) {
                this.zzf.putString("utm_source", str);
                this.zzf.putString("utm_medium", str2);
                this.zzf.putString("utm_campaign", str3);
            }

            public final Builder setSource(String str) {
                this.zzf.putString("utm_source", str);
                return this;
            }

            public final String getSource() {
                return this.zzf.getString("utm_source", "");
            }

            public final Builder setMedium(String str) {
                this.zzf.putString("utm_medium", str);
                return this;
            }

            public final String getMedium() {
                return this.zzf.getString("utm_medium", "");
            }

            public final Builder setCampaign(String str) {
                this.zzf.putString("utm_campaign", str);
                return this;
            }

            public final String getCampaign() {
                return this.zzf.getString("utm_campaign", "");
            }

            public final Builder setTerm(String str) {
                this.zzf.putString("utm_term", str);
                return this;
            }

            public final String getTerm() {
                return this.zzf.getString("utm_term", "");
            }

            public final Builder setContent(String str) {
                this.zzf.putString("utm_content", str);
                return this;
            }

            public final String getContent() {
                return this.zzf.getString("utm_content", "");
            }

            public final GoogleAnalyticsParameters build() {
                return new GoogleAnalyticsParameters(this.zzf);
            }
        }

        private GoogleAnalyticsParameters(Bundle bundle) {
            this.zzf = bundle;
        }
    }

    /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
    public static final class IosParameters {
        final Bundle zzf;

        /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
        public static final class Builder {
            private final Bundle zzf = new Bundle();

            public Builder(String str) {
                this.zzf.putString("ibi", str);
            }

            public final Builder setFallbackUrl(Uri uri) {
                this.zzf.putParcelable("ifl", uri);
                return this;
            }

            public final Builder setCustomScheme(String str) {
                this.zzf.putString("ius", str);
                return this;
            }

            public final String getCustomScheme() {
                return this.zzf.getString("ius", "");
            }

            public final Builder setIpadFallbackUrl(Uri uri) {
                this.zzf.putParcelable("ipfl", uri);
                return this;
            }

            public final Uri getIpadFallbackUrl() {
                Uri uri = (Uri) this.zzf.getParcelable("ipfl");
                if (uri == null) {
                    return Uri.EMPTY;
                }
                return uri;
            }

            public final Builder setIpadBundleId(String str) {
                this.zzf.putString("ipbi", str);
                return this;
            }

            public final String getIpadBundleId() {
                return this.zzf.getString("ipbi", "");
            }

            public final Builder setAppStoreId(String str) {
                this.zzf.putString("isi", str);
                return this;
            }

            public final String getAppStoreId() {
                return this.zzf.getString("isi", "");
            }

            public final Builder setMinimumVersion(String str) {
                this.zzf.putString("imv", str);
                return this;
            }

            public final String getMinimumVersion() {
                return this.zzf.getString("imv", "");
            }

            public final IosParameters build() {
                return new IosParameters(this.zzf);
            }
        }

        private IosParameters(Bundle bundle) {
            this.zzf = bundle;
        }
    }

    /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
    public static final class ItunesConnectAnalyticsParameters {
        final Bundle zzf;

        /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
        public static final class Builder {
            private final Bundle zzf = new Bundle();

            public final Builder setProviderToken(String str) {
                this.zzf.putString("pt", str);
                return this;
            }

            public final String getProviderToken() {
                return this.zzf.getString("pt", "");
            }

            public final Builder setAffiliateToken(String str) {
                this.zzf.putString("at", str);
                return this;
            }

            public final String getAffiliateToken() {
                return this.zzf.getString("at", "");
            }

            public final Builder setCampaignToken(String str) {
                this.zzf.putString("ct", str);
                return this;
            }

            public final String getCampaignToken() {
                return this.zzf.getString("ct", "");
            }

            public final ItunesConnectAnalyticsParameters build() {
                return new ItunesConnectAnalyticsParameters(this.zzf);
            }
        }

        private ItunesConnectAnalyticsParameters(Bundle bundle) {
            this.zzf = bundle;
        }
    }

    /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
    public static final class NavigationInfoParameters {
        final Bundle zzf;

        /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
        public static final class Builder {
            private final Bundle zzf = new Bundle();

            public final Builder setForcedRedirectEnabled(boolean z) {
                this.zzf.putInt("efr", z ? 1 : 0);
                return this;
            }

            public final boolean getForcedRedirectEnabled() {
                return this.zzf.getInt("efr") == 1;
            }

            public final NavigationInfoParameters build() {
                return new NavigationInfoParameters(this.zzf);
            }
        }

        private NavigationInfoParameters(Bundle bundle) {
            this.zzf = bundle;
        }
    }

    /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
    public static final class SocialMetaTagParameters {
        final Bundle zzf;

        /* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
        public static final class Builder {
            private final Bundle zzf = new Bundle();

            public final Builder setTitle(String str) {
                this.zzf.putString("st", str);
                return this;
            }

            public final String getTitle() {
                return this.zzf.getString("st", "");
            }

            public final Builder setDescription(String str) {
                this.zzf.putString("sd", str);
                return this;
            }

            public final String getDescription() {
                return this.zzf.getString("sd", "");
            }

            public final Builder setImageUrl(Uri uri) {
                this.zzf.putParcelable("si", uri);
                return this;
            }

            public final Uri getImageUrl() {
                Uri uri = (Uri) this.zzf.getParcelable("si");
                if (uri == null) {
                    return Uri.EMPTY;
                }
                return uri;
            }

            public final SocialMetaTagParameters build() {
                return new SocialMetaTagParameters(this.zzf);
            }
        }

        private SocialMetaTagParameters(Bundle bundle) {
            this.zzf = bundle;
        }
    }

    DynamicLink(Bundle bundle) {
        this.zze = bundle;
    }

    public final Uri getUri() {
        Bundle bundle = this.zze;
        zze.zzb(bundle);
        Uri uri = (Uri) bundle.getParcelable("dynamicLink");
        if (uri != null) {
            return uri;
        }
        android.net.Uri.Builder builder = new android.net.Uri.Builder();
        Uri parse = Uri.parse(bundle.getString("domainUriPrefix"));
        builder.scheme(parse.getScheme());
        builder.authority(parse.getAuthority());
        builder.path(parse.getPath());
        Bundle bundle2 = bundle.getBundle("parameters");
        for (String str : bundle2.keySet()) {
            Object obj = bundle2.get(str);
            if (obj != null) {
                builder.appendQueryParameter(str, obj.toString());
            }
        }
        return builder.build();
    }
}
