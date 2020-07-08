package com.google.firebase.crashlytics.internal.model.serialization;

import android.util.Base64;
import android.util.JsonReader;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.firebase.crashlytics.internal.model.AutoCrashlyticsReportEncoder;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application;
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
import com.google.firebase.crashlytics.internal.model.ImmutableList;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import p008cz.msebera.android.httpclient.cookie.ClientCookie;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class CrashlyticsReportJsonTransform {
    private static final DataEncoder CRASHLYTICS_REPORT_JSON_ENCODER = new JsonDataEncoderBuilder().configureWith(AutoCrashlyticsReportEncoder.CONFIG).ignoreNullValues(true).build();

    /* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
    private interface ObjectParser<T> {
        T parse(JsonReader jsonReader) throws IOException;
    }

    public String reportToJson(CrashlyticsReport report) {
        return CRASHLYTICS_REPORT_JSON_ENCODER.encode(report);
    }

    public String eventToJson(Event event) {
        return CRASHLYTICS_REPORT_JSON_ENCODER.encode(event);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        $closeResource(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0019, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.crashlytics.internal.model.CrashlyticsReport reportFromJson(java.lang.String r4) throws java.io.IOException {
        /*
            r3 = this;
            android.util.JsonReader r0 = new android.util.JsonReader     // Catch:{ IllegalStateException -> 0x001a }
            java.io.StringReader r1 = new java.io.StringReader     // Catch:{ IllegalStateException -> 0x001a }
            r1.<init>(r4)     // Catch:{ IllegalStateException -> 0x001a }
            r0.<init>(r1)     // Catch:{ IllegalStateException -> 0x001a }
            r1 = 0
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport r2 = parseReport(r0)     // Catch:{ all -> 0x0013 }
            $closeResource(r1, r0)     // Catch:{ IllegalStateException -> 0x001a }
            return r2
        L_0x0013:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0015 }
        L_0x0015:
            r2 = move-exception
            $closeResource(r1, r0)     // Catch:{ IllegalStateException -> 0x001a }
            throw r2     // Catch:{ IllegalStateException -> 0x001a }
        L_0x001a:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform.reportFromJson(java.lang.String):com.google.firebase.crashlytics.internal.model.CrashlyticsReport");
    }

    private static /* synthetic */ void $closeResource(Throwable x0, AutoCloseable x1) {
        if (x0 != null) {
            try {
                x1.close();
            } catch (Throwable th) {
            }
        } else {
            x1.close();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        $closeResource(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0019, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event eventFromJson(java.lang.String r4) throws java.io.IOException {
        /*
            r3 = this;
            android.util.JsonReader r0 = new android.util.JsonReader     // Catch:{ IllegalStateException -> 0x001a }
            java.io.StringReader r1 = new java.io.StringReader     // Catch:{ IllegalStateException -> 0x001a }
            r1.<init>(r4)     // Catch:{ IllegalStateException -> 0x001a }
            r0.<init>(r1)     // Catch:{ IllegalStateException -> 0x001a }
            r1 = 0
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event r2 = parseEvent(r0)     // Catch:{ all -> 0x0013 }
            $closeResource(r1, r0)     // Catch:{ IllegalStateException -> 0x001a }
            return r2
        L_0x0013:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0015 }
        L_0x0015:
            r2 = move-exception
            $closeResource(r1, r0)     // Catch:{ IllegalStateException -> 0x001a }
            throw r2     // Catch:{ IllegalStateException -> 0x001a }
        L_0x001a:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform.eventFromJson(java.lang.String):com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event");
    }

    private static CrashlyticsReport parseReport(JsonReader jsonReader) throws IOException {
        Builder builder = CrashlyticsReport.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case -2118372775:
                    if (name.equals("ndkPayload")) {
                        c = 7;
                        break;
                    }
                    break;
                case -1962630338:
                    if (name.equals("sdkVersion")) {
                        c = 0;
                        break;
                    }
                    break;
                case -911706486:
                    if (name.equals("buildVersion")) {
                        c = 4;
                        break;
                    }
                    break;
                case 344431858:
                    if (name.equals("gmpAppId")) {
                        c = 1;
                        break;
                    }
                    break;
                case 719853845:
                    if (name.equals("installationUuid")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1874684019:
                    if (name.equals("platform")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1975623094:
                    if (name.equals("displayVersion")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1984987798:
                    if (name.equals("session")) {
                        c = 6;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    builder.setSdkVersion(jsonReader.nextString());
                    break;
                case 1:
                    builder.setGmpAppId(jsonReader.nextString());
                    break;
                case 2:
                    builder.setPlatform(jsonReader.nextInt());
                    break;
                case 3:
                    builder.setInstallationUuid(jsonReader.nextString());
                    break;
                case 4:
                    builder.setBuildVersion(jsonReader.nextString());
                    break;
                case 5:
                    builder.setDisplayVersion(jsonReader.nextString());
                    break;
                case 6:
                    builder.setSession(parseSession(jsonReader));
                    break;
                case 7:
                    builder.setNdkPayload(parseNdkPayload(jsonReader));
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static Session parseSession(JsonReader jsonReader) throws IOException {
        Session.Builder builder = Session.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case -2128794476:
                    if (name.equals("startedAt")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1618432855:
                    if (name.equals("identifier")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1606742899:
                    if (name.equals("endedAt")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1335157162:
                    if (name.equals("device")) {
                        c = 8;
                        break;
                    }
                    break;
                case -1291329255:
                    if (name.equals("events")) {
                        c = 9;
                        break;
                    }
                    break;
                case 3556:
                    if (name.equals("os")) {
                        c = 7;
                        break;
                    }
                    break;
                case 96801:
                    if (name.equals("app")) {
                        c = 6;
                        break;
                    }
                    break;
                case 3599307:
                    if (name.equals("user")) {
                        c = 5;
                        break;
                    }
                    break;
                case 286956243:
                    if (name.equals("generator")) {
                        c = 0;
                        break;
                    }
                    break;
                case 1025385094:
                    if (name.equals("crashed")) {
                        c = 4;
                        break;
                    }
                    break;
                case 2047016109:
                    if (name.equals("generatorType")) {
                        c = 10;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    builder.setGenerator(jsonReader.nextString());
                    break;
                case 1:
                    builder.setIdentifierFromUtf8Bytes(Base64.decode(jsonReader.nextString(), 2));
                    break;
                case 2:
                    builder.setStartedAt(jsonReader.nextLong());
                    break;
                case 3:
                    builder.setEndedAt(Long.valueOf(jsonReader.nextLong()));
                    break;
                case 4:
                    builder.setCrashed(jsonReader.nextBoolean());
                    break;
                case 5:
                    builder.setUser(parseUser(jsonReader));
                    break;
                case 6:
                    builder.setApp(parseApp(jsonReader));
                    break;
                case 7:
                    builder.setOs(parseOs(jsonReader));
                    break;
                case 8:
                    builder.setDevice(parseDevice(jsonReader));
                    break;
                case 9:
                    builder.setEvents(parseArray(jsonReader, CrashlyticsReportJsonTransform$$Lambda$1.lambdaFactory$()));
                    break;
                case 10:
                    builder.setGeneratorType(jsonReader.nextInt());
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static FilesPayload parseNdkPayload(JsonReader jsonReader) throws IOException {
        FilesPayload.Builder builder = FilesPayload.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            int hashCode = name.hashCode();
            if (hashCode != 97434231) {
                if (hashCode == 106008351 && name.equals("orgId")) {
                    c = 1;
                }
            } else if (name.equals("files")) {
                c = 0;
            }
            if (c == 0) {
                builder.setFiles(parseArray(jsonReader, CrashlyticsReportJsonTransform$$Lambda$4.lambdaFactory$()));
            } else if (c != 1) {
                jsonReader.skipValue();
            } else {
                builder.setOrgId(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    /* access modifiers changed from: private */
    public static File parseFile(JsonReader jsonReader) throws IOException {
        File.Builder builder = File.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            int hashCode = name.hashCode();
            if (hashCode != -734768633) {
                if (hashCode == -567321830 && name.equals("contents")) {
                    c = 1;
                }
            } else if (name.equals("filename")) {
                c = 0;
            }
            if (c == 0) {
                builder.setFilename(jsonReader.nextString());
            } else if (c != 1) {
                jsonReader.skipValue();
            } else {
                builder.setContents(Base64.decode(jsonReader.nextString(), 2));
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static User parseUser(JsonReader jsonReader) throws IOException {
        User.Builder builder = User.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            if (name.hashCode() == -1618432855 && name.equals("identifier")) {
                c = 0;
            }
            if (c != 0) {
                jsonReader.skipValue();
            } else {
                builder.setIdentifier(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static Application parseApp(JsonReader jsonReader) throws IOException {
        Application.Builder builder = Application.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case -1618432855:
                    if (name.equals("identifier")) {
                        c = 0;
                        break;
                    }
                    break;
                case 351608024:
                    if (name.equals(ClientCookie.VERSION_ATTR)) {
                        c = 1;
                        break;
                    }
                    break;
                case 719853845:
                    if (name.equals("installationUuid")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1975623094:
                    if (name.equals("displayVersion")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                builder.setIdentifier(jsonReader.nextString());
            } else if (c == 1) {
                builder.setVersion(jsonReader.nextString());
            } else if (c == 2) {
                builder.setDisplayVersion(jsonReader.nextString());
            } else if (c != 3) {
                jsonReader.skipValue();
            } else {
                builder.setInstallationUuid(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static OperatingSystem parseOs(JsonReader jsonReader) throws IOException {
        OperatingSystem.Builder builder = OperatingSystem.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case -911706486:
                    if (name.equals("buildVersion")) {
                        c = 2;
                        break;
                    }
                    break;
                case -293026577:
                    if (name.equals("jailbroken")) {
                        c = 3;
                        break;
                    }
                    break;
                case 351608024:
                    if (name.equals(ClientCookie.VERSION_ATTR)) {
                        c = 1;
                        break;
                    }
                    break;
                case 1874684019:
                    if (name.equals("platform")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                builder.setPlatform(jsonReader.nextInt());
            } else if (c == 1) {
                builder.setVersion(jsonReader.nextString());
            } else if (c == 2) {
                builder.setBuildVersion(jsonReader.nextString());
            } else if (c != 3) {
                jsonReader.skipValue();
            } else {
                builder.setJailbroken(jsonReader.nextBoolean());
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static Device parseDevice(JsonReader jsonReader) throws IOException {
        Device.Builder builder = Device.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case -1981332476:
                    if (name.equals("simulator")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1969347631:
                    if (name.equals("manufacturer")) {
                        c = 7;
                        break;
                    }
                    break;
                case 112670:
                    if (name.equals("ram")) {
                        c = 3;
                        break;
                    }
                    break;
                case 3002454:
                    if (name.equals("arch")) {
                        c = 0;
                        break;
                    }
                    break;
                case 81784169:
                    if (name.equals("diskSpace")) {
                        c = 4;
                        break;
                    }
                    break;
                case 94848180:
                    if (name.equals("cores")) {
                        c = 2;
                        break;
                    }
                    break;
                case 104069929:
                    if (name.equals("model")) {
                        c = 1;
                        break;
                    }
                    break;
                case 109757585:
                    if (name.equals("state")) {
                        c = 6;
                        break;
                    }
                    break;
                case 2078953423:
                    if (name.equals("modelClass")) {
                        c = 8;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    builder.setArch(jsonReader.nextInt());
                    break;
                case 1:
                    builder.setModel(jsonReader.nextString());
                    break;
                case 2:
                    builder.setCores(jsonReader.nextInt());
                    break;
                case 3:
                    builder.setRam(jsonReader.nextLong());
                    break;
                case 4:
                    builder.setDiskSpace(jsonReader.nextLong());
                    break;
                case 5:
                    builder.setSimulator(jsonReader.nextBoolean());
                    break;
                case 6:
                    builder.setState(jsonReader.nextInt());
                    break;
                case 7:
                    builder.setManufacturer(jsonReader.nextString());
                    break;
                case 8:
                    builder.setModelClass(jsonReader.nextString());
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    /* access modifiers changed from: private */
    public static Event parseEvent(JsonReader jsonReader) throws IOException {
        Event.Builder builder = Event.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case -1335157162:
                    if (name.equals("device")) {
                        c = 3;
                        break;
                    }
                    break;
                case 96801:
                    if (name.equals("app")) {
                        c = 2;
                        break;
                    }
                    break;
                case 107332:
                    if (name.equals("log")) {
                        c = 4;
                        break;
                    }
                    break;
                case 3575610:
                    if (name.equals("type")) {
                        c = 1;
                        break;
                    }
                    break;
                case 55126294:
                    if (name.equals("timestamp")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                builder.setTimestamp(jsonReader.nextLong());
            } else if (c == 1) {
                builder.setType(jsonReader.nextString());
            } else if (c == 2) {
                builder.setApp(parseEventApp(jsonReader));
            } else if (c == 3) {
                builder.setDevice(parseEventDevice(jsonReader));
            } else if (c != 4) {
                jsonReader.skipValue();
            } else {
                builder.setLog(parseEventLog(jsonReader));
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static Event.Application parseEventApp(JsonReader jsonReader) throws IOException {
        Event.Application.Builder builder = Event.Application.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case -1332194002:
                    if (name.equals("background")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1090974952:
                    if (name.equals("execution")) {
                        c = 2;
                        break;
                    }
                    break;
                case 555169704:
                    if (name.equals("customAttributes")) {
                        c = 3;
                        break;
                    }
                    break;
                case 928737948:
                    if (name.equals("uiOrientation")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                builder.setBackground(Boolean.valueOf(jsonReader.nextBoolean()));
            } else if (c == 1) {
                builder.setUiOrientation(jsonReader.nextInt());
            } else if (c == 2) {
                builder.setExecution(parseEventExecution(jsonReader));
            } else if (c != 3) {
                jsonReader.skipValue();
            } else {
                builder.setCustomAttributes(parseArray(jsonReader, CrashlyticsReportJsonTransform$$Lambda$5.lambdaFactory$()));
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static Execution parseEventExecution(JsonReader jsonReader) throws IOException {
        Execution.Builder builder = Execution.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case -1337936983:
                    if (name.equals("threads")) {
                        c = 0;
                        break;
                    }
                    break;
                case -902467928:
                    if (name.equals("signal")) {
                        c = 2;
                        break;
                    }
                    break;
                case 937615455:
                    if (name.equals("binaries")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1481625679:
                    if (name.equals("exception")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                builder.setThreads(parseArray(jsonReader, CrashlyticsReportJsonTransform$$Lambda$6.lambdaFactory$()));
            } else if (c == 1) {
                builder.setException(parseEventExecutionException(jsonReader));
            } else if (c == 2) {
                builder.setSignal(parseEventSignal(jsonReader));
            } else if (c != 3) {
                jsonReader.skipValue();
            } else {
                builder.setBinaries(parseArray(jsonReader, CrashlyticsReportJsonTransform$$Lambda$7.lambdaFactory$()));
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static Exception parseEventExecutionException(JsonReader jsonReader) throws IOException {
        Exception.Builder builder = Exception.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case -1266514778:
                    if (name.equals("frames")) {
                        c = 1;
                        break;
                    }
                    break;
                case -934964668:
                    if (name.equals("reason")) {
                        c = 4;
                        break;
                    }
                    break;
                case 3575610:
                    if (name.equals("type")) {
                        c = 3;
                        break;
                    }
                    break;
                case 91997906:
                    if (name.equals("causedBy")) {
                        c = 0;
                        break;
                    }
                    break;
                case 581754413:
                    if (name.equals("overflowCount")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                builder.setCausedBy(parseEventExecutionException(jsonReader));
            } else if (c == 1) {
                builder.setFrames(parseArray(jsonReader, CrashlyticsReportJsonTransform$$Lambda$8.lambdaFactory$()));
            } else if (c == 2) {
                builder.setOverflowCount(jsonReader.nextInt());
            } else if (c == 3) {
                builder.setType(jsonReader.nextString());
            } else if (c != 4) {
                jsonReader.skipValue();
            } else {
                builder.setReason(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static Signal parseEventSignal(JsonReader jsonReader) throws IOException {
        Signal.Builder builder = Signal.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            int hashCode = name.hashCode();
            if (hashCode != -1147692044) {
                if (hashCode != 3059181) {
                    if (hashCode == 3373707 && name.equals("name")) {
                        c = 0;
                    }
                } else if (name.equals("code")) {
                    c = 1;
                }
            } else if (name.equals("address")) {
                c = 2;
            }
            if (c == 0) {
                builder.setName(jsonReader.nextString());
            } else if (c == 1) {
                builder.setCode(jsonReader.nextString());
            } else if (c != 2) {
                jsonReader.skipValue();
            } else {
                builder.setAddress(jsonReader.nextLong());
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    /* access modifiers changed from: private */
    public static BinaryImage parseEventBinaryImage(JsonReader jsonReader) throws IOException {
        BinaryImage.Builder builder = BinaryImage.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case 3373707:
                    if (name.equals("name")) {
                        c = 0;
                        break;
                    }
                    break;
                case 3530753:
                    if (name.equals("size")) {
                        c = 2;
                        break;
                    }
                    break;
                case 3601339:
                    if (name.equals("uuid")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1153765347:
                    if (name.equals("baseAddress")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                builder.setName(jsonReader.nextString());
            } else if (c == 1) {
                builder.setBaseAddress(jsonReader.nextLong());
            } else if (c == 2) {
                builder.setSize(jsonReader.nextLong());
            } else if (c != 3) {
                jsonReader.skipValue();
            } else {
                builder.setUuidFromUtf8Bytes(Base64.decode(jsonReader.nextString(), 2));
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    /* access modifiers changed from: private */
    public static Thread parseEventThread(JsonReader jsonReader) throws IOException {
        Thread.Builder builder = Thread.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            int hashCode = name.hashCode();
            if (hashCode != -1266514778) {
                if (hashCode != 3373707) {
                    if (hashCode == 2125650548 && name.equals("importance")) {
                        c = 0;
                    }
                } else if (name.equals("name")) {
                    c = 1;
                }
            } else if (name.equals("frames")) {
                c = 2;
            }
            if (c == 0) {
                builder.setImportance(jsonReader.nextInt());
            } else if (c == 1) {
                builder.setName(jsonReader.nextString());
            } else if (c != 2) {
                jsonReader.skipValue();
            } else {
                builder.setFrames(parseArray(jsonReader, CrashlyticsReportJsonTransform$$Lambda$9.lambdaFactory$()));
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    /* access modifiers changed from: private */
    public static Frame parseEventFrame(JsonReader jsonReader) throws IOException {
        Frame.Builder builder = Frame.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case -1019779949:
                    if (name.equals("offset")) {
                        c = 2;
                        break;
                    }
                    break;
                case -887523944:
                    if (name.equals("symbol")) {
                        c = 4;
                        break;
                    }
                    break;
                case 3571:
                    if (name.equals("pc")) {
                        c = 3;
                        break;
                    }
                    break;
                case 3143036:
                    if (name.equals("file")) {
                        c = 1;
                        break;
                    }
                    break;
                case 2125650548:
                    if (name.equals("importance")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                builder.setImportance(jsonReader.nextInt());
            } else if (c == 1) {
                builder.setFile(jsonReader.nextString());
            } else if (c == 2) {
                builder.setOffset(jsonReader.nextLong());
            } else if (c == 3) {
                builder.setPc(jsonReader.nextLong());
            } else if (c != 4) {
                jsonReader.skipValue();
            } else {
                builder.setSymbol(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static Event.Device parseEventDevice(JsonReader jsonReader) throws IOException {
        Event.Device.Builder builder = Event.Device.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case -1708606089:
                    if (name.equals("batteryLevel")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1455558134:
                    if (name.equals("batteryVelocity")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1439500848:
                    if (name.equals("orientation")) {
                        c = 4;
                        break;
                    }
                    break;
                case 279795450:
                    if (name.equals("diskUsed")) {
                        c = 2;
                        break;
                    }
                    break;
                case 976541947:
                    if (name.equals("ramUsed")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1516795582:
                    if (name.equals("proximityOn")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                builder.setBatteryLevel(Double.valueOf(jsonReader.nextDouble()));
            } else if (c == 1) {
                builder.setBatteryVelocity(jsonReader.nextInt());
            } else if (c == 2) {
                builder.setDiskUsed(jsonReader.nextLong());
            } else if (c == 3) {
                builder.setProximityOn(jsonReader.nextBoolean());
            } else if (c == 4) {
                builder.setOrientation(jsonReader.nextInt());
            } else if (c != 5) {
                jsonReader.skipValue();
            } else {
                builder.setRamUsed(jsonReader.nextLong());
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static Log parseEventLog(JsonReader jsonReader) throws IOException {
        Log.Builder builder = Log.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            if (name.hashCode() == 951530617 && name.equals(Param.CONTENT)) {
                c = 0;
            }
            if (c != 0) {
                jsonReader.skipValue();
            } else {
                builder.setContent(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    /* access modifiers changed from: private */
    public static CustomAttribute parseCustomAttribute(JsonReader jsonReader) throws IOException {
        CustomAttribute.Builder builder = CustomAttribute.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            char c = 65535;
            int hashCode = name.hashCode();
            if (hashCode != 106079) {
                if (hashCode == 111972721 && name.equals("value")) {
                    c = 1;
                }
            } else if (name.equals("key")) {
                c = 0;
            }
            if (c == 0) {
                builder.setKey(jsonReader.nextString());
            } else if (c != 1) {
                jsonReader.skipValue();
            } else {
                builder.setValue(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    private static <T> ImmutableList<T> parseArray(JsonReader jsonReader, ObjectParser<T> objectParser) throws IOException {
        List<T> objects = new ArrayList<>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            objects.add(objectParser.parse(jsonReader));
        }
        jsonReader.endArray();
        return ImmutableList.from(objects);
    }
}
