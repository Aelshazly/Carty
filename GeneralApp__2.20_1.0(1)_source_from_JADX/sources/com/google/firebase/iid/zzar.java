package com.google.firebase.iid;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
final /* synthetic */ class zzar implements ComponentFactory {
    static final ComponentFactory zza = new zzar();

    private zzar() {
    }

    public final Object create(ComponentContainer componentContainer) {
        return new zza((FirebaseInstanceId) componentContainer.get(FirebaseInstanceId.class));
    }
}
