package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User;
import com.google.firebase.encoders.annotations.Encodable.Ignore;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_Session extends Session {
    private final Application app;
    private final boolean crashed;
    private final Device device;
    private final Long endedAt;
    private final ImmutableList<Event> events;
    private final String generator;
    private final int generatorType;
    private final String identifier;

    /* renamed from: os */
    private final OperatingSystem f78os;
    private final long startedAt;
    private final User user;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder {
        private Application app;
        private Boolean crashed;
        private Device device;
        private Long endedAt;
        private ImmutableList<Event> events;
        private String generator;
        private Integer generatorType;
        private String identifier;

        /* renamed from: os */
        private OperatingSystem f79os;
        private Long startedAt;
        private User user;

        Builder() {
        }

        private Builder(Session source) {
            this.generator = source.getGenerator();
            this.identifier = source.getIdentifier();
            this.startedAt = Long.valueOf(source.getStartedAt());
            this.endedAt = source.getEndedAt();
            this.crashed = Boolean.valueOf(source.isCrashed());
            this.app = source.getApp();
            this.user = source.getUser();
            this.f79os = source.getOs();
            this.device = source.getDevice();
            this.events = source.getEvents();
            this.generatorType = Integer.valueOf(source.getGeneratorType());
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder setGenerator(String generator2) {
            if (generator2 != null) {
                this.generator = generator2;
                return this;
            }
            throw new NullPointerException("Null generator");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder setIdentifier(String identifier2) {
            if (identifier2 != null) {
                this.identifier = identifier2;
                return this;
            }
            throw new NullPointerException("Null identifier");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder setStartedAt(long startedAt2) {
            this.startedAt = Long.valueOf(startedAt2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder setEndedAt(Long endedAt2) {
            this.endedAt = endedAt2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder setCrashed(boolean crashed2) {
            this.crashed = Boolean.valueOf(crashed2);
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder setApp(Application app2) {
            if (app2 != null) {
                this.app = app2;
                return this;
            }
            throw new NullPointerException("Null app");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder setUser(User user2) {
            this.user = user2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder setOs(OperatingSystem os) {
            this.f79os = os;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder setDevice(Device device2) {
            this.device = device2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder setEvents(ImmutableList<Event> events2) {
            this.events = events2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder setGeneratorType(int generatorType2) {
            this.generatorType = Integer.valueOf(generatorType2);
            return this;
        }

        public Session build() {
            String missing = "";
            if (this.generator == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" generator");
                missing = sb.toString();
            }
            if (this.identifier == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" identifier");
                missing = sb2.toString();
            }
            if (this.startedAt == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(missing);
                sb3.append(" startedAt");
                missing = sb3.toString();
            }
            if (this.crashed == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(missing);
                sb4.append(" crashed");
                missing = sb4.toString();
            }
            if (this.app == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(missing);
                sb5.append(" app");
                missing = sb5.toString();
            }
            if (this.generatorType == null) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(missing);
                sb6.append(" generatorType");
                missing = sb6.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_CrashlyticsReport_Session autoValue_CrashlyticsReport_Session = new AutoValue_CrashlyticsReport_Session(this.generator, this.identifier, this.startedAt.longValue(), this.endedAt, this.crashed.booleanValue(), this.app, this.user, this.f79os, this.device, this.events, this.generatorType.intValue());
                return autoValue_CrashlyticsReport_Session;
            }
            StringBuilder sb7 = new StringBuilder();
            sb7.append("Missing required properties:");
            sb7.append(missing);
            throw new IllegalStateException(sb7.toString());
        }
    }

    private AutoValue_CrashlyticsReport_Session(String generator2, String identifier2, long startedAt2, Long endedAt2, boolean crashed2, Application app2, User user2, OperatingSystem os, Device device2, ImmutableList<Event> events2, int generatorType2) {
        this.generator = generator2;
        this.identifier = identifier2;
        this.startedAt = startedAt2;
        this.endedAt = endedAt2;
        this.crashed = crashed2;
        this.app = app2;
        this.user = user2;
        this.f78os = os;
        this.device = device2;
        this.events = events2;
        this.generatorType = generatorType2;
    }

    public String getGenerator() {
        return this.generator;
    }

    @Ignore
    public String getIdentifier() {
        return this.identifier;
    }

    public long getStartedAt() {
        return this.startedAt;
    }

    public Long getEndedAt() {
        return this.endedAt;
    }

    public boolean isCrashed() {
        return this.crashed;
    }

    public Application getApp() {
        return this.app;
    }

    public User getUser() {
        return this.user;
    }

    public OperatingSystem getOs() {
        return this.f78os;
    }

    public Device getDevice() {
        return this.device;
    }

    public ImmutableList<Event> getEvents() {
        return this.events;
    }

    public int getGeneratorType() {
        return this.generatorType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Session{generator=");
        sb.append(this.generator);
        sb.append(", identifier=");
        sb.append(this.identifier);
        sb.append(", startedAt=");
        sb.append(this.startedAt);
        sb.append(", endedAt=");
        sb.append(this.endedAt);
        sb.append(", crashed=");
        sb.append(this.crashed);
        sb.append(", app=");
        sb.append(this.app);
        sb.append(", user=");
        sb.append(this.user);
        sb.append(", os=");
        sb.append(this.f78os);
        sb.append(", device=");
        sb.append(this.device);
        sb.append(", events=");
        sb.append(this.events);
        sb.append(", generatorType=");
        sb.append(this.generatorType);
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b1, code lost:
        if (r8.generatorType == r1.getGeneratorType()) goto L_0x00b5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 1
            if (r9 != r8) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r9 instanceof com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
            r2 = 0
            if (r1 == 0) goto L_0x00b6
            r1 = r9
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session r1 = (com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session) r1
            java.lang.String r3 = r8.generator
            java.lang.String r4 = r1.getGenerator()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00b4
            java.lang.String r3 = r8.identifier
            java.lang.String r4 = r1.getIdentifier()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00b4
            long r3 = r8.startedAt
            long r5 = r1.getStartedAt()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x00b4
            java.lang.Long r3 = r8.endedAt
            if (r3 != 0) goto L_0x0039
            java.lang.Long r3 = r1.getEndedAt()
            if (r3 != 0) goto L_0x00b4
            goto L_0x0043
        L_0x0039:
            java.lang.Long r4 = r1.getEndedAt()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00b4
        L_0x0043:
            boolean r3 = r8.crashed
            boolean r4 = r1.isCrashed()
            if (r3 != r4) goto L_0x00b4
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Application r3 = r8.app
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Application r4 = r1.getApp()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00b4
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$User r3 = r8.user
            if (r3 != 0) goto L_0x0062
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$User r3 = r1.getUser()
            if (r3 != 0) goto L_0x00b4
            goto L_0x006c
        L_0x0062:
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$User r4 = r1.getUser()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00b4
        L_0x006c:
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$OperatingSystem r3 = r8.f78os
            if (r3 != 0) goto L_0x0077
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$OperatingSystem r3 = r1.getOs()
            if (r3 != 0) goto L_0x00b4
            goto L_0x0081
        L_0x0077:
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$OperatingSystem r4 = r1.getOs()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00b4
        L_0x0081:
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Device r3 = r8.device
            if (r3 != 0) goto L_0x008c
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Device r3 = r1.getDevice()
            if (r3 != 0) goto L_0x00b4
            goto L_0x0096
        L_0x008c:
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Device r4 = r1.getDevice()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00b4
        L_0x0096:
            com.google.firebase.crashlytics.internal.model.ImmutableList<com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event> r3 = r8.events
            if (r3 != 0) goto L_0x00a1
            com.google.firebase.crashlytics.internal.model.ImmutableList r3 = r1.getEvents()
            if (r3 != 0) goto L_0x00b4
            goto L_0x00ab
        L_0x00a1:
            com.google.firebase.crashlytics.internal.model.ImmutableList r4 = r1.getEvents()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00b4
        L_0x00ab:
            int r3 = r8.generatorType
            int r4 = r1.getGeneratorType()
            if (r3 != r4) goto L_0x00b4
            goto L_0x00b5
        L_0x00b4:
            r0 = 0
        L_0x00b5:
            return r0
        L_0x00b6:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int h$ = ((((1 * 1000003) ^ this.generator.hashCode()) * 1000003) ^ this.identifier.hashCode()) * 1000003;
        long j = this.startedAt;
        int h$2 = (h$ ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        Long l = this.endedAt;
        int i = 0;
        int h$3 = (((((h$2 ^ (l == null ? 0 : l.hashCode())) * 1000003) ^ (this.crashed ? 1231 : 1237)) * 1000003) ^ this.app.hashCode()) * 1000003;
        User user2 = this.user;
        int h$4 = (h$3 ^ (user2 == null ? 0 : user2.hashCode())) * 1000003;
        OperatingSystem operatingSystem = this.f78os;
        int h$5 = (h$4 ^ (operatingSystem == null ? 0 : operatingSystem.hashCode())) * 1000003;
        Device device2 = this.device;
        int h$6 = (h$5 ^ (device2 == null ? 0 : device2.hashCode())) * 1000003;
        ImmutableList<Event> immutableList = this.events;
        if (immutableList != null) {
            i = immutableList.hashCode();
        }
        return ((h$6 ^ i) * 1000003) ^ this.generatorType;
    }

    public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder toBuilder() {
        return new Builder(this);
    }
}
