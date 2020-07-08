package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

final class BarcodeValue {
    private final Map<Integer, Integer> values = new HashMap();

    BarcodeValue() {
    }

    /* access modifiers changed from: 0000 */
    public void setValue(int value) {
        Integer num = (Integer) this.values.get(Integer.valueOf(value));
        Integer confidence = num;
        if (num == null) {
            confidence = Integer.valueOf(0);
        }
        this.values.put(Integer.valueOf(value), Integer.valueOf(confidence.intValue() + 1));
    }

    /* access modifiers changed from: 0000 */
    public int[] getValue() {
        int maxConfidence = -1;
        Collection<Integer> result = new ArrayList<>();
        for (Entry entry : this.values.entrySet()) {
            Entry entry2 = entry;
            if (((Integer) entry.getValue()).intValue() > maxConfidence) {
                maxConfidence = ((Integer) entry2.getValue()).intValue();
                result.clear();
                result.add(entry2.getKey());
            } else if (((Integer) entry2.getValue()).intValue() == maxConfidence) {
                result.add(entry2.getKey());
            }
        }
        return PDF417Common.toIntArray(result);
    }

    public Integer getConfidence(int value) {
        return (Integer) this.values.get(Integer.valueOf(value));
    }
}
