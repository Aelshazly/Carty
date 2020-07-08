package com.jhlabs.map.proj;

public class OtherProjections {
    public Object createPlugin() {
        return new Object[]{new SimpleConicProjection(5), new SimpleConicProjection(1), new SimpleConicProjection(2), new SimpleConicProjection(3), new SimpleConicProjection(4), new SimpleConicProjection(6), new MolleweideProjection(1), new MolleweideProjection(2)};
    }
}
