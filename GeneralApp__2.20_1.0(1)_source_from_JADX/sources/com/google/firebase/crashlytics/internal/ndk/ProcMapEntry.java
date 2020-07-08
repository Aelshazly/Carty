package com.google.firebase.crashlytics.internal.ndk;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
class ProcMapEntry {
    public final long address;
    public final String path;
    public final String perms;
    public final long size;

    public ProcMapEntry(long address2, long size2, String perms2, String path2) {
        this.address = address2;
        this.size = size2;
        this.perms = perms2;
        this.path = path2;
    }
}
