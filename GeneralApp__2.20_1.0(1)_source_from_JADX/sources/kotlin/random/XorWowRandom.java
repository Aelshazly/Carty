package kotlin.random;

import kotlin.Metadata;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B7\b\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u000e\u0010\u000b\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo24952d2 = {"Lkotlin/random/XorWowRandom;", "Lkotlin/random/Random;", "seed1", "", "seed2", "(II)V", "x", "y", "z", "w", "v", "addend", "(IIIIII)V", "nextBits", "bitCount", "nextInt", "kotlin-stdlib"}, mo24953k = 1, mo24954mv = {1, 1, 15})
/* compiled from: XorWowRandom.kt */
public final class XorWowRandom extends Random {
    private int addend;

    /* renamed from: v */
    private int f252v;

    /* renamed from: w */
    private int f253w;

    /* renamed from: x */
    private int f254x;

    /* renamed from: y */
    private int f255y;

    /* renamed from: z */
    private int f256z;

    public XorWowRandom(int x, int y, int z, int w, int v, int addend2) {
        this.f254x = x;
        this.f255y = y;
        this.f256z = z;
        this.f253w = w;
        this.f252v = v;
        this.addend = addend2;
        if (((((this.f254x | this.f255y) | this.f256z) | this.f253w) | this.f252v) != 0) {
            for (int i = 0; i < 64; i++) {
                int i2 = i;
                nextInt();
            }
            return;
        }
        throw new IllegalArgumentException("Initial state must have at least one non-zero element.".toString());
    }

    public XorWowRandom(int seed1, int seed2) {
        int i = seed1;
        int i2 = seed2;
        this(i, i2, 0, 0, ~seed1, (seed1 << 10) ^ (seed2 >>> 4));
    }

    public int nextInt() {
        int t = this.f254x;
        int t2 = t ^ (t >>> 2);
        this.f254x = this.f255y;
        this.f255y = this.f256z;
        this.f256z = this.f253w;
        int v0 = this.f252v;
        this.f253w = v0;
        int t3 = (((t2 << 1) ^ t2) ^ v0) ^ (v0 << 4);
        this.f252v = t3;
        this.addend += 362437;
        return this.addend + t3;
    }

    public int nextBits(int bitCount) {
        return RandomKt.takeUpperBits(nextInt(), bitCount);
    }
}
