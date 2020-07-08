package com.navibees.core.interfaces;

public interface UserProfilerLoginListener {
    void onFailure(int i, Throwable th);

    void onSuccess(int i);
}
