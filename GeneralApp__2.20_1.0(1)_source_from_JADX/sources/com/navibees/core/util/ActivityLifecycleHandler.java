package com.navibees.core.util;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

public class ActivityLifecycleHandler implements ActivityLifecycleCallbacks {

    /* renamed from: a */
    private LifecycleListener f1603a;

    /* renamed from: b */
    private int f1604b;

    /* renamed from: c */
    private int f1605c;

    /* renamed from: d */
    private boolean f1606d;

    public interface LifecycleListener {
        void onApplicationPaused();

        void onApplicationResumed();

        void onApplicationStarted();

        void onApplicationStopped();
    }

    public ActivityLifecycleHandler(LifecycleListener lifecycleListener) {
        this.f1603a = lifecycleListener;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
        this.f1606d = true;
        this.f1605c--;
    }

    public void onActivityResumed(Activity activity) {
        if (this.f1605c == 0 && !this.f1606d) {
            LifecycleListener lifecycleListener = this.f1603a;
            if (lifecycleListener != null) {
                lifecycleListener.onApplicationResumed();
            }
        }
        this.f1606d = false;
        this.f1605c++;
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        if (this.f1604b == 0) {
            LifecycleListener lifecycleListener = this.f1603a;
            if (lifecycleListener != null) {
                lifecycleListener.onApplicationStarted();
            }
        }
        this.f1604b++;
    }

    public void onActivityStopped(Activity activity) {
        if (this.f1604b == 1) {
            LifecycleListener lifecycleListener = this.f1603a;
            if (lifecycleListener != null) {
                if (this.f1606d && this.f1605c == 0) {
                    lifecycleListener.onApplicationPaused();
                }
                this.f1603a.onApplicationStopped();
            }
        }
        this.f1606d = false;
        this.f1604b--;
    }
}
