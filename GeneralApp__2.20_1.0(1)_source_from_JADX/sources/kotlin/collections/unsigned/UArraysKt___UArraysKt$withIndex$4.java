package kotlin.collections.unsigned;

import kotlin.Metadata;
import kotlin.UShortArray;
import kotlin.collections.UShortIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo24950bv = {1, 0, 3}, mo24951d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo24952d2 = {"<anonymous>", "Lkotlin/collections/UShortIterator;", "invoke"}, mo24953k = 3, mo24954mv = {1, 1, 15})
/* compiled from: _UArrays.kt */
final class UArraysKt___UArraysKt$withIndex$4 extends Lambda implements Function0<UShortIterator> {
    final /* synthetic */ short[] $this_withIndex;

    UArraysKt___UArraysKt$withIndex$4(short[] sArr) {
        this.$this_withIndex = sArr;
        super(0);
    }

    public final UShortIterator invoke() {
        return UShortArray.m1570iteratorimpl(this.$this_withIndex);
    }
}
