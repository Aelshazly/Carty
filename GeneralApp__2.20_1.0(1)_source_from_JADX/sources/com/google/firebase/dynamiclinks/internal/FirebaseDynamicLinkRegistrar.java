package com.google.firebase.dynamiclinks.internal;

import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.firebase:firebase-dynamic-links@@19.1.0 */
public final class FirebaseDynamicLinkRegistrar implements ComponentRegistrar {
    public final List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(FirebaseDynamicLinks.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.optional(AnalyticsConnector.class)).factory(zzf.zzs).build()});
    }
}
