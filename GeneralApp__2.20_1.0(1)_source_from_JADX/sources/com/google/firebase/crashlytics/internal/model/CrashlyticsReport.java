package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.encoders.annotations.Encodable;
import com.google.firebase.encoders.annotations.Encodable.Field;
import com.google.firebase.encoders.annotations.Encodable.Ignore;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.Charset;

@Encodable
/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public abstract class CrashlyticsReport {
    /* access modifiers changed from: private */
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public @interface Architecture {
        public static final int ARM64 = 9;
        public static final int ARMV6 = 5;
        public static final int ARMV7 = 6;
        public static final int UNKNOWN = 7;
        public static final int X86_32 = 0;
        public static final int X86_64 = 1;
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public static abstract class Builder {
        public abstract CrashlyticsReport build();

        public abstract Builder setBuildVersion(String str);

        public abstract Builder setDisplayVersion(String str);

        public abstract Builder setGmpAppId(String str);

        public abstract Builder setInstallationUuid(String str);

        public abstract Builder setNdkPayload(FilesPayload filesPayload);

        public abstract Builder setPlatform(int i);

        public abstract Builder setSdkVersion(String str);

        public abstract Builder setSession(Session session);
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public static abstract class CustomAttribute {

        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
        public static abstract class Builder {
            public abstract CustomAttribute build();

            public abstract Builder setKey(String str);

            public abstract Builder setValue(String str);
        }

        public abstract String getKey();

        public abstract String getValue();

        public static Builder builder() {
            return new Builder();
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public static abstract class FilesPayload {

        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
        public static abstract class Builder {
            public abstract FilesPayload build();

            public abstract Builder setFiles(ImmutableList<File> immutableList);

            public abstract Builder setOrgId(String str);
        }

        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
        public static abstract class File {

            /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
            public static abstract class Builder {
                public abstract File build();

                public abstract Builder setContents(byte[] bArr);

                public abstract Builder setFilename(String str);
            }

            public abstract byte[] getContents();

            public abstract String getFilename();

            public static Builder builder() {
                return new Builder();
            }
        }

        public abstract ImmutableList<File> getFiles();

        public abstract String getOrgId();

        /* access modifiers changed from: 0000 */
        public abstract Builder toBuilder();

        public static Builder builder() {
            return new Builder();
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public static abstract class Session {

        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
        public static abstract class Application {

            /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
            public static abstract class Builder {
                public abstract Application build();

                public abstract Builder setDisplayVersion(String str);

                public abstract Builder setIdentifier(String str);

                public abstract Builder setInstallationUuid(String str);

                public abstract Builder setOrganization(Organization organization);

                public abstract Builder setVersion(String str);
            }

            /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
            public static abstract class Organization {

                /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                public static abstract class Builder {
                    public abstract Organization build();

                    public abstract Builder setClsId(String str);
                }

                public abstract String getClsId();

                /* access modifiers changed from: protected */
                public abstract Builder toBuilder();

                public static Builder builder() {
                    return new Builder();
                }
            }

            public abstract String getDisplayVersion();

            public abstract String getIdentifier();

            public abstract String getInstallationUuid();

            public abstract Organization getOrganization();

            public abstract String getVersion();

            /* access modifiers changed from: protected */
            public abstract Builder toBuilder();

            public static Builder builder() {
                return new Builder();
            }

            /* access modifiers changed from: 0000 */
            public Application withOrganizationId(String organizationId) {
                Organization organization = getOrganization();
                return toBuilder().setOrganization((organization != null ? organization.toBuilder() : Organization.builder()).setClsId(organizationId).build()).build();
            }
        }

        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
        public static abstract class Builder {
            public abstract Session build();

            public abstract Builder setApp(Application application);

            public abstract Builder setCrashed(boolean z);

            public abstract Builder setDevice(Device device);

            public abstract Builder setEndedAt(Long l);

            public abstract Builder setEvents(ImmutableList<Event> immutableList);

            public abstract Builder setGenerator(String str);

            public abstract Builder setGeneratorType(int i);

            public abstract Builder setIdentifier(String str);

            public abstract Builder setOs(OperatingSystem operatingSystem);

            public abstract Builder setStartedAt(long j);

            public abstract Builder setUser(User user);

            public Builder setIdentifierFromUtf8Bytes(byte[] utf8Bytes) {
                return setIdentifier(new String(utf8Bytes, CrashlyticsReport.UTF_8));
            }
        }

        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
        public static abstract class Device {

            /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
            public static abstract class Builder {
                public abstract Device build();

                public abstract Builder setArch(int i);

                public abstract Builder setCores(int i);

                public abstract Builder setDiskSpace(long j);

                public abstract Builder setManufacturer(String str);

                public abstract Builder setModel(String str);

                public abstract Builder setModelClass(String str);

                public abstract Builder setRam(long j);

                public abstract Builder setSimulator(boolean z);

                public abstract Builder setState(int i);
            }

            public abstract int getArch();

            public abstract int getCores();

            public abstract long getDiskSpace();

            public abstract String getManufacturer();

            public abstract String getModel();

            public abstract String getModelClass();

            public abstract long getRam();

            public abstract int getState();

            public abstract boolean isSimulator();

            public static Builder builder() {
                return new Builder();
            }
        }

        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
        public static abstract class Event {

            /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
            public static abstract class Application {

                /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                public static abstract class Builder {
                    public abstract Application build();

                    public abstract Builder setBackground(Boolean bool);

                    public abstract Builder setCustomAttributes(ImmutableList<CustomAttribute> immutableList);

                    public abstract Builder setExecution(Execution execution);

                    public abstract Builder setUiOrientation(int i);
                }

                /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                public static abstract class Execution {

                    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                    public static abstract class BinaryImage {

                        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                        public static abstract class Builder {
                            public abstract BinaryImage build();

                            public abstract Builder setBaseAddress(long j);

                            public abstract Builder setName(String str);

                            public abstract Builder setSize(long j);

                            public abstract Builder setUuid(String str);

                            public Builder setUuidFromUtf8Bytes(byte[] utf8Bytes) {
                                return setUuid(new String(utf8Bytes, CrashlyticsReport.UTF_8));
                            }
                        }

                        public abstract long getBaseAddress();

                        public abstract String getName();

                        public abstract long getSize();

                        @Ignore
                        public abstract String getUuid();

                        public static Builder builder() {
                            return new Builder();
                        }

                        @Field(name = "uuid")
                        public byte[] getUuidUtf8Bytes() {
                            String uuid = getUuid();
                            if (uuid != null) {
                                return uuid.getBytes(CrashlyticsReport.UTF_8);
                            }
                            return null;
                        }
                    }

                    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                    public static abstract class Builder {
                        public abstract Execution build();

                        public abstract Builder setBinaries(ImmutableList<BinaryImage> immutableList);

                        public abstract Builder setException(Exception exception);

                        public abstract Builder setSignal(Signal signal);

                        public abstract Builder setThreads(ImmutableList<Thread> immutableList);
                    }

                    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                    public static abstract class Exception {

                        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                        public static abstract class Builder {
                            public abstract Exception build();

                            public abstract Builder setCausedBy(Exception exception);

                            public abstract Builder setFrames(ImmutableList<Frame> immutableList);

                            public abstract Builder setOverflowCount(int i);

                            public abstract Builder setReason(String str);

                            public abstract Builder setType(String str);
                        }

                        public abstract Exception getCausedBy();

                        public abstract ImmutableList<Frame> getFrames();

                        public abstract int getOverflowCount();

                        public abstract String getReason();

                        public abstract String getType();

                        public static Builder builder() {
                            return new Builder();
                        }
                    }

                    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                    public static abstract class Signal {

                        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                        public static abstract class Builder {
                            public abstract Signal build();

                            public abstract Builder setAddress(long j);

                            public abstract Builder setCode(String str);

                            public abstract Builder setName(String str);
                        }

                        public abstract long getAddress();

                        public abstract String getCode();

                        public abstract String getName();

                        public static Builder builder() {
                            return new Builder();
                        }
                    }

                    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                    public static abstract class Thread {

                        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                        public static abstract class Builder {
                            public abstract Thread build();

                            public abstract Builder setFrames(ImmutableList<Frame> immutableList);

                            public abstract Builder setImportance(int i);

                            public abstract Builder setName(String str);
                        }

                        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                        public static abstract class Frame {

                            /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                            public static abstract class Builder {
                                public abstract Frame build();

                                public abstract Builder setFile(String str);

                                public abstract Builder setImportance(int i);

                                public abstract Builder setOffset(long j);

                                public abstract Builder setPc(long j);

                                public abstract Builder setSymbol(String str);
                            }

                            public abstract String getFile();

                            public abstract int getImportance();

                            public abstract long getOffset();

                            public abstract long getPc();

                            public abstract String getSymbol();

                            public static Builder builder() {
                                return new Builder();
                            }
                        }

                        public abstract ImmutableList<Frame> getFrames();

                        public abstract int getImportance();

                        public abstract String getName();

                        public static Builder builder() {
                            return new Builder();
                        }
                    }

                    public abstract ImmutableList<BinaryImage> getBinaries();

                    public abstract Exception getException();

                    public abstract Signal getSignal();

                    public abstract ImmutableList<Thread> getThreads();

                    public static Builder builder() {
                        return new Builder();
                    }
                }

                public abstract Boolean getBackground();

                public abstract ImmutableList<CustomAttribute> getCustomAttributes();

                public abstract Execution getExecution();

                public abstract int getUiOrientation();

                public abstract Builder toBuilder();

                public static Builder builder() {
                    return new Builder();
                }
            }

            /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
            public static abstract class Builder {
                public abstract Event build();

                public abstract Builder setApp(Application application);

                public abstract Builder setDevice(Device device);

                public abstract Builder setLog(Log log);

                public abstract Builder setTimestamp(long j);

                public abstract Builder setType(String str);
            }

            /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
            public static abstract class Device {

                /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                public static abstract class Builder {
                    public abstract Device build();

                    public abstract Builder setBatteryLevel(Double d);

                    public abstract Builder setBatteryVelocity(int i);

                    public abstract Builder setDiskUsed(long j);

                    public abstract Builder setOrientation(int i);

                    public abstract Builder setProximityOn(boolean z);

                    public abstract Builder setRamUsed(long j);
                }

                public abstract Double getBatteryLevel();

                public abstract int getBatteryVelocity();

                public abstract long getDiskUsed();

                public abstract int getOrientation();

                public abstract long getRamUsed();

                public abstract boolean isProximityOn();

                public static Builder builder() {
                    return new Builder();
                }
            }

            /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
            public static abstract class Log {

                /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
                public static abstract class Builder {
                    public abstract Log build();

                    public abstract Builder setContent(String str);
                }

                public abstract String getContent();

                public static Builder builder() {
                    return new Builder();
                }
            }

            public abstract Application getApp();

            public abstract Device getDevice();

            public abstract Log getLog();

            public abstract long getTimestamp();

            public abstract String getType();

            public abstract Builder toBuilder();

            public static Builder builder() {
                return new Builder();
            }
        }

        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
        public static abstract class OperatingSystem {

            /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
            public static abstract class Builder {
                public abstract OperatingSystem build();

                public abstract Builder setBuildVersion(String str);

                public abstract Builder setJailbroken(boolean z);

                public abstract Builder setPlatform(int i);

                public abstract Builder setVersion(String str);
            }

            public abstract String getBuildVersion();

            public abstract int getPlatform();

            public abstract String getVersion();

            public abstract boolean isJailbroken();

            public static Builder builder() {
                return new Builder();
            }
        }

        /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
        public static abstract class User {

            /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
            public static abstract class Builder {
                public abstract User build();

                public abstract Builder setIdentifier(String str);
            }

            public abstract String getIdentifier();

            public static Builder builder() {
                return new Builder();
            }
        }

        public abstract Application getApp();

        public abstract Device getDevice();

        public abstract Long getEndedAt();

        public abstract ImmutableList<Event> getEvents();

        public abstract String getGenerator();

        public abstract int getGeneratorType();

        @Ignore
        public abstract String getIdentifier();

        public abstract OperatingSystem getOs();

        public abstract long getStartedAt();

        public abstract User getUser();

        public abstract boolean isCrashed();

        public abstract Builder toBuilder();

        public static Builder builder() {
            return new Builder().setCrashed(false);
        }

        @Field(name = "identifier")
        public byte[] getIdentifierUtf8Bytes() {
            return getIdentifier().getBytes(CrashlyticsReport.UTF_8);
        }

        /* access modifiers changed from: 0000 */
        public Session withEvents(ImmutableList<Event> events) {
            return toBuilder().setEvents(events).build();
        }

        /* access modifiers changed from: 0000 */
        public Session withOrganizationId(String organizationId) {
            return toBuilder().setApp(getApp().withOrganizationId(organizationId)).build();
        }

        /* access modifiers changed from: 0000 */
        public Session withSessionEndFields(long timestamp, boolean isCrashed, String userId) {
            Builder builder = toBuilder();
            builder.setEndedAt(Long.valueOf(timestamp));
            builder.setCrashed(isCrashed);
            if (userId != null) {
                builder.setUser(User.builder().setIdentifier(userId).build()).build();
            }
            return builder.build();
        }
    }

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    public enum Type {
        INCOMPLETE,
        JAVA,
        NATIVE
    }

    public abstract String getBuildVersion();

    public abstract String getDisplayVersion();

    public abstract String getGmpAppId();

    public abstract String getInstallationUuid();

    public abstract FilesPayload getNdkPayload();

    public abstract int getPlatform();

    public abstract String getSdkVersion();

    public abstract Session getSession();

    /* access modifiers changed from: protected */
    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder();
    }

    @Ignore
    public Type getType() {
        if (getSession() != null) {
            return Type.JAVA;
        }
        if (getNdkPayload() != null) {
            return Type.NATIVE;
        }
        return Type.INCOMPLETE;
    }

    public CrashlyticsReport withEvents(ImmutableList<Event> events) {
        if (getSession() != null) {
            return toBuilder().setSession(getSession().withEvents(events)).build();
        }
        throw new IllegalStateException("Reports without sessions cannot have events added to them.");
    }

    public CrashlyticsReport withOrganizationId(String organizationId) {
        Builder builder = toBuilder();
        FilesPayload ndkPayload = getNdkPayload();
        if (ndkPayload != null) {
            builder.setNdkPayload(ndkPayload.toBuilder().setOrgId(organizationId).build());
        }
        Session session = getSession();
        if (session != null) {
            builder.setSession(session.withOrganizationId(organizationId));
        }
        return builder.build();
    }

    public CrashlyticsReport withNdkPayload(FilesPayload filesPayload) {
        return toBuilder().setSession(null).setNdkPayload(filesPayload).build();
    }

    public CrashlyticsReport withSessionEndFields(long endedAt, boolean isCrashed, String userId) {
        Builder builder = toBuilder();
        if (getSession() != null) {
            builder.setSession(getSession().withSessionEndFields(endedAt, isCrashed, userId));
        }
        return builder.build();
    }
}
