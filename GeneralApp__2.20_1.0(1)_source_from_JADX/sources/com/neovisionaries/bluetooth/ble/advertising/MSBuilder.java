package com.neovisionaries.bluetooth.ble.advertising;

import java.util.ArrayList;
import java.util.List;

public class MSBuilder implements ADManufacturerSpecificBuilder {
    private final List<ADManufacturerSpecificBuilder> mBuilders = new ArrayList();

    public MSBuilder() {
    }

    public MSBuilder(ADManufacturerSpecificBuilder... builders) {
        for (ADManufacturerSpecificBuilder builder : builders) {
            this.mBuilders.add(builder);
        }
    }

    public ADManufacturerSpecific build(int length, int type, byte[] data, int companyId) {
        for (ADManufacturerSpecificBuilder builder : this.mBuilders) {
            ADManufacturerSpecific structure = builder.build(length, type, data, companyId);
            if (structure != null) {
                return structure;
            }
        }
        return null;
    }

    public void addBuilder(ADManufacturerSpecificBuilder builder) {
        if (builder != null) {
            this.mBuilders.add(builder);
        }
    }

    public void removeBuilder(ADManufacturerSpecificBuilder builder) {
        if (builder != null) {
            this.mBuilders.remove(builder);
        }
    }
}
