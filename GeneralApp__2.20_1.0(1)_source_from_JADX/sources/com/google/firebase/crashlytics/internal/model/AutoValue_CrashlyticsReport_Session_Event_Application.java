package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
final class AutoValue_CrashlyticsReport_Session_Event_Application extends Application {
    private final Boolean background;
    private final ImmutableList<CustomAttribute> customAttributes;
    private final Execution execution;
    private final int uiOrientation;

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    static final class Builder extends com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder {
        private Boolean background;
        private ImmutableList<CustomAttribute> customAttributes;
        private Execution execution;
        private Integer uiOrientation;

        Builder() {
        }

        private Builder(Application source) {
            this.execution = source.getExecution();
            this.customAttributes = source.getCustomAttributes();
            this.background = source.getBackground();
            this.uiOrientation = Integer.valueOf(source.getUiOrientation());
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder setExecution(Execution execution2) {
            if (execution2 != null) {
                this.execution = execution2;
                return this;
            }
            throw new NullPointerException("Null execution");
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder setCustomAttributes(ImmutableList<CustomAttribute> customAttributes2) {
            this.customAttributes = customAttributes2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder setBackground(Boolean background2) {
            this.background = background2;
            return this;
        }

        public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder setUiOrientation(int uiOrientation2) {
            this.uiOrientation = Integer.valueOf(uiOrientation2);
            return this;
        }

        public Application build() {
            String missing = "";
            if (this.execution == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(missing);
                sb.append(" execution");
                missing = sb.toString();
            }
            if (this.uiOrientation == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(missing);
                sb2.append(" uiOrientation");
                missing = sb2.toString();
            }
            if (missing.isEmpty()) {
                AutoValue_CrashlyticsReport_Session_Event_Application autoValue_CrashlyticsReport_Session_Event_Application = new AutoValue_CrashlyticsReport_Session_Event_Application(this.execution, this.customAttributes, this.background, this.uiOrientation.intValue());
                return autoValue_CrashlyticsReport_Session_Event_Application;
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Missing required properties:");
            sb3.append(missing);
            throw new IllegalStateException(sb3.toString());
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application(Execution execution2, ImmutableList<CustomAttribute> customAttributes2, Boolean background2, int uiOrientation2) {
        this.execution = execution2;
        this.customAttributes = customAttributes2;
        this.background = background2;
        this.uiOrientation = uiOrientation2;
    }

    public Execution getExecution() {
        return this.execution;
    }

    public ImmutableList<CustomAttribute> getCustomAttributes() {
        return this.customAttributes;
    }

    public Boolean getBackground() {
        return this.background;
    }

    public int getUiOrientation() {
        return this.uiOrientation;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Application{execution=");
        sb.append(this.execution);
        sb.append(", customAttributes=");
        sb.append(this.customAttributes);
        sb.append(", background=");
        sb.append(this.background);
        sb.append(", uiOrientation=");
        sb.append(this.uiOrientation);
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
        if (r5.uiOrientation == r1.getUiOrientation()) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r6 instanceof com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
            r2 = 0
            if (r1 == 0) goto L_0x004d
            r1 = r6
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application r1 = (com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application) r1
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$Execution r3 = r5.execution
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event$Application$Execution r4 = r1.getExecution()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x004b
            com.google.firebase.crashlytics.internal.model.ImmutableList<com.google.firebase.crashlytics.internal.model.CrashlyticsReport$CustomAttribute> r3 = r5.customAttributes
            if (r3 != 0) goto L_0x0023
            com.google.firebase.crashlytics.internal.model.ImmutableList r3 = r1.getCustomAttributes()
            if (r3 != 0) goto L_0x004b
            goto L_0x002d
        L_0x0023:
            com.google.firebase.crashlytics.internal.model.ImmutableList r4 = r1.getCustomAttributes()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x004b
        L_0x002d:
            java.lang.Boolean r3 = r5.background
            if (r3 != 0) goto L_0x0038
            java.lang.Boolean r3 = r1.getBackground()
            if (r3 != 0) goto L_0x004b
            goto L_0x0042
        L_0x0038:
            java.lang.Boolean r4 = r1.getBackground()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x004b
        L_0x0042:
            int r3 = r5.uiOrientation
            int r4 = r1.getUiOrientation()
            if (r3 != r4) goto L_0x004b
            goto L_0x004c
        L_0x004b:
            r0 = 0
        L_0x004c:
            return r0
        L_0x004d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int h$ = ((1 * 1000003) ^ this.execution.hashCode()) * 1000003;
        ImmutableList<CustomAttribute> immutableList = this.customAttributes;
        int i = 0;
        int h$2 = (h$ ^ (immutableList == null ? 0 : immutableList.hashCode())) * 1000003;
        Boolean bool = this.background;
        if (bool != null) {
            i = bool.hashCode();
        }
        return ((h$2 ^ i) * 1000003) ^ this.uiOrientation;
    }

    public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder toBuilder() {
        return new Builder(this);
    }
}
