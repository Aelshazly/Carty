package p008cz.msebera.android.httpclient.client.methods;

import p008cz.msebera.android.httpclient.concurrent.Cancellable;

/* renamed from: cz.msebera.android.httpclient.client.methods.HttpExecutionAware */
public interface HttpExecutionAware {
    boolean isAborted();

    void setCancellable(Cancellable cancellable);
}
