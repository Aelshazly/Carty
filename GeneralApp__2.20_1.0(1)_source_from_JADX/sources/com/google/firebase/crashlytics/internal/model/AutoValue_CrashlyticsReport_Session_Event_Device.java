package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_Session_Event_Device extends Device {
    private final Double batteryLevel;
    private final int batteryVelocity;
    private final long diskUsed;
    private final int orientation;
    private final boolean proximityOn;
    private final long ramUsed;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder {
        private Double batteryLevel;
        private Integer batteryVelocity;
        private Long diskUsed;
        private Integer orientation;
        private Boolean proximityOn;
        private Long ramUsed;

        Builder() {
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder setBatteryLevel(Double batteryLevel2) {
            this.batteryLevel = batteryLevel2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder setBatteryVelocity(int batteryVelocity2) {
            this.batteryVelocity = Integer.valueOf(batteryVelocity2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder setProximityOn(boolean proximityOn2) {
            this.proximityOn = Boolean.valueOf(proximityOn2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder setOrientation(int orientation2) {
            this.orientation = Integer.valueOf(orientation2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder setRamUsed(long ramUsed2) {
            this.ramUsed = Long.valueOf(ramUsed2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder setDiskUsed(long diskUsed2) {
            this.diskUsed = Long.valueOf(diskUsed2);
            return this;
        }

        public Device build() {
            String missing = "";
            if (this.batteryVelocity == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" batteryVelocity");
                missing = sb.toString();
            }
            if (this.proximityOn == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" proximityOn");
                missing = sb2.toString();
            }
            if (this.orientation == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" orientation");
                missing = sb3.toString();
            }
            if (this.ramUsed == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(missing);
                sb4.append(" ramUsed");
                missing = sb4.toString();
            }
            if (this.diskUsed == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(missing);
                sb5.append(" diskUsed");
                missing = sb5.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_CrashlyticsReport_Session_Event_Device autoValue_CrashlyticsReport_Session_Event_Device = new AutoValue_CrashlyticsReport_Session_Event_Device(this.batteryLevel, this.batteryVelocity.intValue(), this.proximityOn.booleanValue(), this.orientation.intValue(), this.ramUsed.longValue(), this.diskUsed.longValue());
                return autoValue_CrashlyticsReport_Session_Event_Device;
            }
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Missing required properties:");
            sb6.append(missing);
            throw new IllegalStateException(sb6.toString());
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Device(Double batteryLevel2, int batteryVelocity2, boolean proximityOn2, int orientation2, long ramUsed2, long diskUsed2) {
        this.batteryLevel = batteryLevel2;
        this.batteryVelocity = batteryVelocity2;
        this.proximityOn = proximityOn2;
        this.orientation = orientation2;
        this.ramUsed = ramUsed2;
        this.diskUsed = diskUsed2;
    }

    public Double getBatteryLevel() {
        return this.batteryLevel;
    }

    public int getBatteryVelocity() {
        return this.batteryVelocity;
    }

    public boolean isProximityOn() {
        return this.proximityOn;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public long getRamUsed() {
        return this.ramUsed;
    }

    public long getDiskUsed() {
        return this.diskUsed;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Device{batteryLevel=");
        sb.append(this.batteryLevel);
        sb.append(", batteryVelocity=");
        sb.append(this.batteryVelocity);
        sb.append(", proximityOn=");
        sb.append(this.proximityOn);
        sb.append(", orientation=");
        sb.append(this.orientation);
        sb.append(", ramUsed=");
        sb.append(this.ramUsed);
        sb.append(", diskUsed=");
        sb.append(this.diskUsed);
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004b, code lost:
        if (r8.diskUsed == r1.getDiskUsed()) goto L_0x004f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 1
            if (r9 != r8) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r9 instanceof com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
            r2 = 0
            if (r1 == 0) goto L_0x0050
            r1 = r9
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Device r1 = (com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device) r1
            java.lang.Double r3 = r8.batteryLevel
            if (r3 != 0) goto L_0x0017
            java.lang.Double r3 = r1.getBatteryLevel()
            if (r3 != 0) goto L_0x004e
            goto L_0x0021
        L_0x0017:
            java.lang.Double r4 = r1.getBatteryLevel()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x004e
        L_0x0021:
            int r3 = r8.batteryVelocity
            int r4 = r1.getBatteryVelocity()
            if (r3 != r4) goto L_0x004e
            boolean r3 = r8.proximityOn
            boolean r4 = r1.isProximityOn()
            if (r3 != r4) goto L_0x004e
            int r3 = r8.orientation
            int r4 = r1.getOrientation()
            if (r3 != r4) goto L_0x004e
            long r3 = r8.ramUsed
            long r5 = r1.getRamUsed()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x004e
            long r3 = r8.diskUsed
            long r5 = r1.getDiskUsed()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r0 = 0
        L_0x004f:
            return r0
        L_0x0050:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Device.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        Double d = this.batteryLevel;
        int h$2 = (((((((h$ ^ (d == null ? 0 : d.hashCode())) * 1000003) ^ this.batteryVelocity) * 1000003) ^ (this.proximityOn ? 1231 : 1237)) * 1000003) ^ this.orientation) * 1000003;
        long j = this.ramUsed;
        int h$3 = (h$2 ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        long j2 = this.diskUsed;
        return h$3 ^ ((int) (j2 ^ (j2 >>> 32)));
    }
}
