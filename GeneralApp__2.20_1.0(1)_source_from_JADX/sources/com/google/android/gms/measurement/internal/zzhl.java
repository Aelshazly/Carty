package com.google.android.gms.measurement.internal;

import com.google.firebase.analytics.FirebaseAnalytics.Event;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.3.0 */
public class zzhl {
    public static final String[] zza = {"app_background", "app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "ga_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "ad_exposure", "adunit_exposure", "ad_query", "ad_activeview", "ad_impression", "ad_click", "ad_reward", "screen_view", "ga_extra_parameter", "session_start_with_rollout", "firebase_campaign"};
    public static final String[] zzb = {"_ab", "_cd", "_ae", "_ui", "_ug", "_in", "_au", "_cmp", "_err", "_f", "_v", "_iap", "_nd", "_nf", "_no", "_nr", "_ou", "_s", "_e", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", "_ar", "_vs", "_ep", "_ssr", "_cmp"};
    public static final String[] zzc = {Event.PURCHASE, Event.REFUND, Event.ADD_PAYMENT_INFO, Event.ADD_SHIPPING_INFO, Event.ADD_TO_CART, Event.ADD_TO_WISHLIST, Event.BEGIN_CHECKOUT, Event.REMOVE_FROM_CART, Event.SELECT_ITEM, Event.SELECT_PROMOTION, Event.VIEW_CART, Event.VIEW_ITEM, Event.VIEW_ITEM_LIST, Event.VIEW_PROMOTION, Event.ECOMMERCE_PURCHASE, Event.PURCHASE_REFUND, Event.SET_CHECKOUT_OPTION, Event.CHECKOUT_PROGRESS, Event.SELECT_CONTENT, Event.VIEW_SEARCH_RESULTS};

    protected zzhl() {
    }

    public static String zza(String str) {
        return zziw.zza(str, zzb, zza);
    }

    public static String zzb(String str) {
        return zziw.zza(str, zza, zzb);
    }
}
