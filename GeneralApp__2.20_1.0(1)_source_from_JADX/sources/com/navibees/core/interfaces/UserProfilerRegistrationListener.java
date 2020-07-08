package com.navibees.core.interfaces;

public interface UserProfilerRegistrationListener {
    void onFailure(int i, Throwable th);

    void onSuccess(int i);
}
