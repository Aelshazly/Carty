package com.navibees.core.model.postioning;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RSSIMAP implements Serializable {
    public Map<Integer, Double> map = new HashMap();
}
