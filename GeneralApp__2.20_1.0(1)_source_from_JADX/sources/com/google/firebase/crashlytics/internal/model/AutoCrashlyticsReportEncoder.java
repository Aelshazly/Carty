package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Organization;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.IOException;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public final class AutoCrashlyticsReportEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 1;
    public static final Configurator CONFIG = new AutoCrashlyticsReportEncoder();

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportCustomAttributeEncoder implements ObjectEncoder<CustomAttribute> {
        static final CrashlyticsReportCustomAttributeEncoder INSTANCE = new CrashlyticsReportCustomAttributeEncoder();

        private CrashlyticsReportCustomAttributeEncoder() {
        }

        public void encode(CustomAttribute value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("key", (Object) value.getKey());
            ctx.add("value", (Object) value.getValue());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportEncoder implements ObjectEncoder<CrashlyticsReport> {
        static final CrashlyticsReportEncoder INSTANCE = new CrashlyticsReportEncoder();

        private CrashlyticsReportEncoder() {
        }

        public void encode(CrashlyticsReport value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("sdkVersion", (Object) value.getSdkVersion());
            ctx.add("gmpAppId", (Object) value.getGmpAppId());
            ctx.add("platform", value.getPlatform());
            ctx.add("installationUuid", (Object) value.getInstallationUuid());
            ctx.add("buildVersion", (Object) value.getBuildVersion());
            ctx.add("displayVersion", (Object) value.getDisplayVersion());
            ctx.add("session", (Object) value.getSession());
            ctx.add("ndkPayload", (Object) value.getNdkPayload());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportFilesPayloadEncoder implements ObjectEncoder<FilesPayload> {
        static final CrashlyticsReportFilesPayloadEncoder INSTANCE = new CrashlyticsReportFilesPayloadEncoder();

        private CrashlyticsReportFilesPayloadEncoder() {
        }

        public void encode(FilesPayload value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("files", (Object) value.getFiles());
            ctx.add("orgId", (Object) value.getOrgId());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportFilesPayloadFileEncoder implements ObjectEncoder<File> {
        static final CrashlyticsReportFilesPayloadFileEncoder INSTANCE = new CrashlyticsReportFilesPayloadFileEncoder();

        private CrashlyticsReportFilesPayloadFileEncoder() {
        }

        public void encode(File value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("filename", (Object) value.getFilename());
            ctx.add("contents", (Object) value.getContents());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionApplicationEncoder implements ObjectEncoder<Application> {
        static final CrashlyticsReportSessionApplicationEncoder INSTANCE = new CrashlyticsReportSessionApplicationEncoder();

        private CrashlyticsReportSessionApplicationEncoder() {
        }

        public void encode(Application value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("identifier", (Object) value.getIdentifier());
            ctx.add(ClientCookie.VERSION_ATTR, (Object) value.getVersion());
            ctx.add("displayVersion", (Object) value.getDisplayVersion());
            ctx.add("organization", (Object) value.getOrganization());
            ctx.add("installationUuid", (Object) value.getInstallationUuid());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionApplicationOrganizationEncoder implements ObjectEncoder<Organization> {
        static final CrashlyticsReportSessionApplicationOrganizationEncoder INSTANCE = new CrashlyticsReportSessionApplicationOrganizationEncoder();

        private CrashlyticsReportSessionApplicationOrganizationEncoder() {
        }

        public void encode(Organization value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("clsId", (Object) value.getClsId());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionDeviceEncoder implements ObjectEncoder<Device> {
        static final CrashlyticsReportSessionDeviceEncoder INSTANCE = new CrashlyticsReportSessionDeviceEncoder();

        private CrashlyticsReportSessionDeviceEncoder() {
        }

        public void encode(Device value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("arch", value.getArch());
            ctx.add("model", (Object) value.getModel());
            ctx.add("cores", value.getCores());
            ctx.add("ram", value.getRam());
            ctx.add("diskSpace", value.getDiskSpace());
            ctx.add("simulator", value.isSimulator());
            ctx.add("state", value.getState());
            ctx.add("manufacturer", (Object) value.getManufacturer());
            ctx.add("modelClass", (Object) value.getModelClass());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionEncoder implements ObjectEncoder<Session> {
        static final CrashlyticsReportSessionEncoder INSTANCE = new CrashlyticsReportSessionEncoder();

        private CrashlyticsReportSessionEncoder() {
        }

        public void encode(Session value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("generator", (Object) value.getGenerator());
            ctx.add("identifier", (Object) value.getIdentifierUtf8Bytes());
            ctx.add("startedAt", value.getStartedAt());
            ctx.add("endedAt", (Object) value.getEndedAt());
            ctx.add("crashed", value.isCrashed());
            ctx.add("app", (Object) value.getApp());
            ctx.add("user", (Object) value.getUser());
            ctx.add("os", (Object) value.getOs());
            ctx.add("device", (Object) value.getDevice());
            ctx.add("events", (Object) value.getEvents());
            ctx.add("generatorType", value.getGeneratorType());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionEventApplicationEncoder implements ObjectEncoder<Event.Application> {
        static final CrashlyticsReportSessionEventApplicationEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationEncoder();

        private CrashlyticsReportSessionEventApplicationEncoder() {
        }

        public void encode(Event.Application value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("execution", (Object) value.getExecution());
            ctx.add("customAttributes", (Object) value.getCustomAttributes());
            ctx.add("background", (Object) value.getBackground());
            ctx.add("uiOrientation", value.getUiOrientation());
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoCrashlyticsReportEncoder$CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder */
    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class C0777x99c932db implements ObjectEncoder<BinaryImage> {
        static final C0777x99c932db INSTANCE = new C0777x99c932db();

        private C0777x99c932db() {
        }

        public void encode(BinaryImage value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("baseAddress", value.getBaseAddress());
            ctx.add("size", value.getSize());
            ctx.add("name", (Object) value.getName());
            ctx.add("uuid", (Object) value.getUuidUtf8Bytes());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionEventApplicationExecutionEncoder implements ObjectEncoder<Execution> {
        static final CrashlyticsReportSessionEventApplicationExecutionEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionEncoder() {
        }

        public void encode(Execution value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("threads", (Object) value.getThreads());
            ctx.add("exception", (Object) value.getException());
            ctx.add("signal", (Object) value.getSignal());
            ctx.add("binaries", (Object) value.getBinaries());
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoCrashlyticsReportEncoder$CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder */
    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class C0778x55689506 implements ObjectEncoder<Exception> {
        static final C0778x55689506 INSTANCE = new C0778x55689506();

        private C0778x55689506() {
        }

        public void encode(Exception value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("type", (Object) value.getType());
            ctx.add("reason", (Object) value.getReason());
            ctx.add("frames", (Object) value.getFrames());
            ctx.add("causedBy", (Object) value.getCausedBy());
            ctx.add("overflowCount", value.getOverflowCount());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionEventApplicationExecutionSignalEncoder implements ObjectEncoder<Signal> {
        static final CrashlyticsReportSessionEventApplicationExecutionSignalEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionSignalEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionSignalEncoder() {
        }

        public void encode(Signal value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("name", (Object) value.getName());
            ctx.add("code", (Object) value.getCode());
            ctx.add("address", value.getAddress());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionEventApplicationExecutionThreadEncoder implements ObjectEncoder<Thread> {
        static final CrashlyticsReportSessionEventApplicationExecutionThreadEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionThreadEncoder() {
        }

        public void encode(Thread value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("name", (Object) value.getName());
            ctx.add("importance", value.getImportance());
            ctx.add("frames", (Object) value.getFrames());
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoCrashlyticsReportEncoder$CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder */
    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class C0779xc3999712 implements ObjectEncoder<Frame> {
        static final C0779xc3999712 INSTANCE = new C0779xc3999712();

        private C0779xc3999712() {
        }

        public void encode(Frame value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("pc", value.getPc());
            ctx.add("symbol", (Object) value.getSymbol());
            ctx.add("file", (Object) value.getFile());
            ctx.add("offset", value.getOffset());
            ctx.add("importance", value.getImportance());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionEventDeviceEncoder implements ObjectEncoder<Event.Device> {
        static final CrashlyticsReportSessionEventDeviceEncoder INSTANCE = new CrashlyticsReportSessionEventDeviceEncoder();

        private CrashlyticsReportSessionEventDeviceEncoder() {
        }

        public void encode(Event.Device value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("batteryLevel", (Object) value.getBatteryLevel());
            ctx.add("batteryVelocity", value.getBatteryVelocity());
            ctx.add("proximityOn", value.isProximityOn());
            ctx.add("orientation", value.getOrientation());
            ctx.add("ramUsed", value.getRamUsed());
            ctx.add("diskUsed", value.getDiskUsed());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionEventEncoder implements ObjectEncoder<Event> {
        static final CrashlyticsReportSessionEventEncoder INSTANCE = new CrashlyticsReportSessionEventEncoder();

        private CrashlyticsReportSessionEventEncoder() {
        }

        public void encode(Event value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("timestamp", value.getTimestamp());
            ctx.add("type", (Object) value.getType());
            ctx.add("app", (Object) value.getApp());
            ctx.add("device", (Object) value.getDevice());
            ctx.add("log", (Object) value.getLog());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionEventLogEncoder implements ObjectEncoder<Log> {
        static final CrashlyticsReportSessionEventLogEncoder INSTANCE = new CrashlyticsReportSessionEventLogEncoder();

        private CrashlyticsReportSessionEventLogEncoder() {
        }

        public void encode(Log value, ObjectEncoderContext ctx) throws IOException {
            ctx.add(Param.CONTENT, (Object) value.getContent());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionOperatingSystemEncoder implements ObjectEncoder<OperatingSystem> {
        static final CrashlyticsReportSessionOperatingSystemEncoder INSTANCE = new CrashlyticsReportSessionOperatingSystemEncoder();

        private CrashlyticsReportSessionOperatingSystemEncoder() {
        }

        public void encode(OperatingSystem value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("platform", value.getPlatform());
            ctx.add(ClientCookie.VERSION_ATTR, (Object) value.getVersion());
            ctx.add("buildVersion", (Object) value.getBuildVersion());
            ctx.add("jailbroken", value.isJailbroken());
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private static final class CrashlyticsReportSessionUserEncoder implements ObjectEncoder<User> {
        static final CrashlyticsReportSessionUserEncoder INSTANCE = new CrashlyticsReportSessionUserEncoder();

        private CrashlyticsReportSessionUserEncoder() {
        }

        public void encode(User value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("identifier", (Object) value.getIdentifier());
        }
    }

    private AutoCrashlyticsReportEncoder() {
    }

    public void configure(EncoderConfig<?> cfg) {
        cfg.registerEncoder(CrashlyticsReport.class, (ObjectEncoder<? super U>) CrashlyticsReportEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport.class, (ObjectEncoder<? super U>) CrashlyticsReportEncoder.INSTANCE);
        cfg.registerEncoder(Session.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEncoder.INSTANCE);
        cfg.registerEncoder(Application.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionApplicationEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Application.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionApplicationEncoder.INSTANCE);
        cfg.registerEncoder(Organization.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionApplicationOrganizationEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Application_Organization.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionApplicationOrganizationEncoder.INSTANCE);
        cfg.registerEncoder(User.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionUserEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_User.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionUserEncoder.INSTANCE);
        cfg.registerEncoder(OperatingSystem.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionOperatingSystemEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_OperatingSystem.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionOperatingSystemEncoder.INSTANCE);
        cfg.registerEncoder(Device.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionDeviceEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Device.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionDeviceEncoder.INSTANCE);
        cfg.registerEncoder(Event.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventEncoder.INSTANCE);
        cfg.registerEncoder(Event.Application.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationEncoder.INSTANCE);
        cfg.registerEncoder(Execution.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionEncoder.INSTANCE);
        cfg.registerEncoder(Thread.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionThreadEncoder.INSTANCE);
        cfg.registerEncoder(C0797x7e3e3ebd.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionThreadEncoder.INSTANCE);
        cfg.registerEncoder(Frame.class, (ObjectEncoder<? super U>) C0779xc3999712.INSTANCE);
        cfg.registerEncoder(C0799xce3d994b.class, (ObjectEncoder<? super U>) C0779xc3999712.INSTANCE);
        cfg.registerEncoder(Exception.class, (ObjectEncoder<? super U>) C0778x55689506.INSTANCE);
        cfg.registerEncoder(C0793xc2f5febc.class, (ObjectEncoder<? super U>) C0778x55689506.INSTANCE);
        cfg.registerEncoder(Signal.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionSignalEncoder.INSTANCE);
        cfg.registerEncoder(C0795x7c929f5b.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionSignalEncoder.INSTANCE);
        cfg.registerEncoder(BinaryImage.class, (ObjectEncoder<? super U>) C0777x99c932db.INSTANCE);
        cfg.registerEncoder(C0791xfe724d07.class, (ObjectEncoder<? super U>) C0777x99c932db.INSTANCE);
        cfg.registerEncoder(CustomAttribute.class, (ObjectEncoder<? super U>) CrashlyticsReportCustomAttributeEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_CustomAttribute.class, (ObjectEncoder<? super U>) CrashlyticsReportCustomAttributeEncoder.INSTANCE);
        cfg.registerEncoder(Event.Device.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventDeviceEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Device.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventDeviceEncoder.INSTANCE);
        cfg.registerEncoder(Log.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventLogEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Log.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventLogEncoder.INSTANCE);
        cfg.registerEncoder(FilesPayload.class, (ObjectEncoder<? super U>) CrashlyticsReportFilesPayloadEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_FilesPayload.class, (ObjectEncoder<? super U>) CrashlyticsReportFilesPayloadEncoder.INSTANCE);
        cfg.registerEncoder(File.class, (ObjectEncoder<? super U>) CrashlyticsReportFilesPayloadFileEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_FilesPayload_File.class, (ObjectEncoder<? super U>) CrashlyticsReportFilesPayloadFileEncoder.INSTANCE);
    }
}
