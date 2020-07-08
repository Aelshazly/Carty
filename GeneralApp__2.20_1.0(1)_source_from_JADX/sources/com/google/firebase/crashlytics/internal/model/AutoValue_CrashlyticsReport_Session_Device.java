package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_Session_Device extends Device {
    private final int arch;
    private final int cores;
    private final long diskSpace;
    private final String manufacturer;
    private final String model;
    private final String modelClass;
    private final long ram;
    private final boolean simulator;
    private final int state;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder {
        private Integer arch;
        private Integer cores;
        private Long diskSpace;
        private String manufacturer;
        private String model;
        private String modelClass;
        private Long ram;
        private Boolean simulator;
        private Integer state;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder setArch(int arch2) {
            this.arch = Integer.valueOf(arch2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder setModel(String model2) {
            if (model2 != null) {
                this.model = model2;
                return this;
            }
            throw new NullPointerException("Null model");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder setCores(int cores2) {
            this.cores = Integer.valueOf(cores2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder setRam(long ram2) {
            this.ram = Long.valueOf(ram2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder setDiskSpace(long diskSpace2) {
            this.diskSpace = Long.valueOf(diskSpace2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder setSimulator(boolean simulator2) {
            this.simulator = Boolean.valueOf(simulator2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder setState(int state2) {
            this.state = Integer.valueOf(state2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder setManufacturer(String manufacturer2) {
            if (manufacturer2 != null) {
                this.manufacturer = manufacturer2;
                return this;
            }
            throw new NullPointerException("Null manufacturer");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder setModelClass(String modelClass2) {
            if (modelClass2 != null) {
                this.modelClass = modelClass2;
                return this;
            }
            throw new NullPointerException("Null modelClass");
        }

        public Device build() {
            String missing = "";
            if (this.arch == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" arch");
                missing = sb.toString();
            }
            if (this.model == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" model");
                missing = sb2.toString();
            }
            if (this.cores == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" cores");
                missing = sb3.toString();
            }
            if (this.ram == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(missing);
                sb4.append(" ram");
                missing = sb4.toString();
            }
            if (this.diskSpace == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(missing);
                sb5.append(" diskSpace");
                missing = sb5.toString();
            }
            if (this.simulator == null) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(missing);
                sb6.append(" simulator");
                missing = sb6.toString();
            }
            if (this.state == null) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append(missing);
                sb7.append(" state");
                missing = sb7.toString();
            }
            if (this.manufacturer == null) {
                StringBuilder sb8 = new StringBuilder();
                sb8.append(missing);
                sb8.append(" manufacturer");
                missing = sb8.toString();
            }
            if (this.modelClass == null) {
                StringBuilder sb9 = new StringBuilder();
                sb9.append(missing);
                sb9.append(" modelClass");
                missing = sb9.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_CrashlyticsReport_Session_Device autoValue_CrashlyticsReport_Session_Device = new AutoValue_CrashlyticsReport_Session_Device(this.arch.intValue(), this.model, this.cores.intValue(), this.ram.longValue(), this.diskSpace.longValue(), this.simulator.booleanValue(), this.state.intValue(), this.manufacturer, this.modelClass);
                return autoValue_CrashlyticsReport_Session_Device;
            }
            StringBuilder sb10 = new StringBuilder();
            sb10.append("Missing required properties:");
            sb10.append(missing);
            throw new IllegalStateException(sb10.toString());
        }
    }

    private AutoValue_CrashlyticsReport_Session_Device(int arch2, String model2, int cores2, long ram2, long diskSpace2, boolean simulator2, int state2, String manufacturer2, String modelClass2) {
        this.arch = arch2;
        this.model = model2;
        this.cores = cores2;
        this.ram = ram2;
        this.diskSpace = diskSpace2;
        this.simulator = simulator2;
        this.state = state2;
        this.manufacturer = manufacturer2;
        this.modelClass = modelClass2;
    }

    public int getArch() {
        return this.arch;
    }

    public String getModel() {
        return this.model;
    }

    public int getCores() {
        return this.cores;
    }

    public long getRam() {
        return this.ram;
    }

    public long getDiskSpace() {
        return this.diskSpace;
    }

    public boolean isSimulator() {
        return this.simulator;
    }

    public int getState() {
        return this.state;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModelClass() {
        return this.modelClass;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Device{arch=");
        sb.append(this.arch);
        sb.append(", model=");
        sb.append(this.model);
        sb.append(", cores=");
        sb.append(this.cores);
        sb.append(", ram=");
        sb.append(this.ram);
        sb.append(", diskSpace=");
        sb.append(this.diskSpace);
        sb.append(", simulator=");
        sb.append(this.simulator);
        sb.append(", state=");
        sb.append(this.state);
        sb.append(", manufacturer=");
        sb.append(this.manufacturer);
        sb.append(", modelClass=");
        sb.append(this.modelClass);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof Device)) {
            return false;
        }
        Device that = (Device) o;
        if (!(this.arch == that.getArch() && this.model.equals(that.getModel()) && this.cores == that.getCores() && this.ram == that.getRam() && this.diskSpace == that.getDiskSpace() && this.simulator == that.isSimulator() && this.state == that.getState() && this.manufacturer.equals(that.getManufacturer()) && this.modelClass.equals(that.getModelClass()))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int h$ = ((((((1 * 1000003) ^ this.arch) * 1000003) ^ this.model.hashCode()) * 1000003) ^ this.cores) * 1000003;
        long j = this.ram;
        int h$2 = (h$ ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.diskSpace;
        return ((((((((h$2 ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ (this.simulator ? 1231 : 1237)) * 1000003) ^ this.state) * 1000003) ^ this.manufacturer.hashCode()) * 1000003) ^ this.modelClass.hashCode();
    }
}
