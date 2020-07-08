package com.navibees.navigatorapp.network;

public interface NetworkResponse<T> {
    void onFailure(Throwable th);

    void onResponse(T t);
}
