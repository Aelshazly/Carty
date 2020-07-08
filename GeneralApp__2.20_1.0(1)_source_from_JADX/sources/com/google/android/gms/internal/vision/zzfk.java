package com.google.android.gms.internal.vision;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.2 */
final class zzfk extends zzfm {
    private final int limit = this.zzsg.size();
    private int position = 0;
    private final /* synthetic */ zzfh zzsg;

    zzfk(zzfh zzfh) {
        this.zzsg = zzfh;
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final byte nextByte() {
        int i = this.position;
        if (i < this.limit) {
            this.position = i + 1;
            return this.zzsg.zzap(i);
        }
        throw new NoSuchElementException();
    }
}
