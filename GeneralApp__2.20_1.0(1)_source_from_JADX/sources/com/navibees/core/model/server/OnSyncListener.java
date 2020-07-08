package com.navibees.core.model.server;

public interface OnSyncListener {
    void appMetadataSyncCompleted();

    void appMetadataSyncFailed(String str);

    void buildingSyncCompleted();

    void buildingSyncCompleted(String str);

    void buildingSyncFailed(String str);

    void buildingSyncFailed(String str, String str2);
}
