package com.google.firebase.crashlytics.internal.persistence;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event;
import com.google.firebase.crashlytics.internal.model.ImmutableList;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public class CrashlyticsReportPersistence {
    private static final String EVENT_COUNTER_FORMAT = "%010d";
    private static final int EVENT_COUNTER_WIDTH = 10;
    private static final FilenameFilter EVENT_FILE_FILTER = CrashlyticsReportPersistence$$Lambda$6.lambdaFactory$();
    private static final String EVENT_FILE_NAME_PREFIX = "event";
    private static final int EVENT_NAME_LENGTH = ("event".length() + 10);
    private static final Comparator<? super File> LATEST_SESSION_ID_FIRST_COMPARATOR = CrashlyticsReportPersistence$$Lambda$5.lambdaFactory$();
    private static final int MAX_OPEN_SESSIONS = 8;
    private static final String NATIVE_REPORTS_DIRECTORY = "native-reports";
    private static final String NORMAL_EVENT_SUFFIX = "";
    private static final String OPEN_SESSIONS_DIRECTORY_NAME = "sessions";
    private static final String PRIORITY_EVENT_SUFFIX = "_";
    private static final String PRIORITY_REPORTS_DIRECTORY = "priority-reports";
    private static final String REPORTS_DIRECTORY = "reports";
    private static final String REPORT_FILE_NAME = "report";
    private static final CrashlyticsReportJsonTransform TRANSFORM = new CrashlyticsReportJsonTransform();
    private static final String USER_FILE_NAME = "user";
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final String WORKING_DIRECTORY_NAME = "report-persistence";
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    private final File nativeReportsDirectory;
    private final File openSessionsDirectory;
    private final File priorityReportsDirectory;
    private final File reportsDirectory;
    private final SettingsDataProvider settingsDataProvider;

    public CrashlyticsReportPersistence(File rootDirectory, SettingsDataProvider settingsDataProvider2) {
        File workingDirectory = new File(rootDirectory, WORKING_DIRECTORY_NAME);
        this.openSessionsDirectory = new File(workingDirectory, OPEN_SESSIONS_DIRECTORY_NAME);
        this.priorityReportsDirectory = new File(workingDirectory, PRIORITY_REPORTS_DIRECTORY);
        this.reportsDirectory = new File(workingDirectory, REPORTS_DIRECTORY);
        this.nativeReportsDirectory = new File(workingDirectory, NATIVE_REPORTS_DIRECTORY);
        this.settingsDataProvider = settingsDataProvider2;
    }

    public void persistReport(CrashlyticsReport report) {
        Session session = report.getSession();
        if (session == null) {
            Logger.getLogger().mo19679d("Could not get session for report");
            return;
        }
        String sessionId = session.getIdentifier();
        try {
            File sessionDirectory = prepareDirectory(getSessionDirectoryById(sessionId));
            writeTextFile(new File(sessionDirectory, REPORT_FILE_NAME), TRANSFORM.reportToJson(report));
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Could not persist report for session ");
            sb.append(sessionId);
            logger.mo19680d(sb.toString(), e);
        }
    }

    public void persistEvent(Event event, String sessionId) {
        persistEvent(event, sessionId, false);
    }

    public void persistEvent(Event event, String sessionId, boolean isHighPriority) {
        int maxEventsToKeep = this.settingsDataProvider.getSettings().getSessionData().maxCustomExceptionEvents;
        File sessionDirectory = getSessionDirectoryById(sessionId);
        try {
            writeTextFile(new File(sessionDirectory, generateEventFilename(this.eventCounter.getAndIncrement(), isHighPriority)), TRANSFORM.eventToJson(event));
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Could not persist event for session ");
            sb.append(sessionId);
            logger.mo19680d(sb.toString(), e);
        }
        trimEvents(sessionDirectory, maxEventsToKeep);
    }

    public void persistUserIdForSession(String userId, String sessionId) {
        try {
            writeTextFile(new File(getSessionDirectoryById(sessionId), USER_FILE_NAME), userId);
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Could not persist user ID for session ");
            sb.append(sessionId);
            logger.mo19680d(sb.toString(), e);
        }
    }

    public void deleteAllReports() {
        for (File reportFile : getAllFinalizedReportFiles()) {
            reportFile.delete();
        }
    }

    public void deleteFinalizedReport(String sessionId) {
        FilenameFilter filter = CrashlyticsReportPersistence$$Lambda$1.lambdaFactory$(sessionId);
        for (File reportFile : combineReportFiles(getFilesInDirectory(this.priorityReportsDirectory, filter), getFilesInDirectory(this.nativeReportsDirectory, filter), getFilesInDirectory(this.reportsDirectory, filter))) {
            reportFile.delete();
        }
    }

    public void finalizeReports(String currentSessionId, long sessionEndTime) {
        for (File sessionDirectory : capAndGetOpenSessions(currentSessionId)) {
            synthesizeReport(sessionDirectory, sessionEndTime);
            recursiveDelete(sessionDirectory);
        }
        capFinalizedReports();
    }

    public void finalizeSessionWithNativeEvent(String previousSessionId, FilesPayload ndkPayload) {
        synthesizeNativeReportFile(new File(getSessionDirectoryById(previousSessionId), REPORT_FILE_NAME), this.nativeReportsDirectory, ndkPayload, previousSessionId);
    }

    public List<CrashlyticsReportWithSessionId> loadFinalizedReports() {
        List<File> allReportFiles = getAllFinalizedReportFiles();
        ArrayList<CrashlyticsReportWithSessionId> allReports = new ArrayList<>();
        allReports.ensureCapacity(allReportFiles.size());
        for (File reportFile : getAllFinalizedReportFiles()) {
            try {
                allReports.add(CrashlyticsReportWithSessionId.create(TRANSFORM.reportFromJson(readTextFile(reportFile)), reportFile.getName()));
            } catch (IOException e) {
                Logger logger = Logger.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("Could not load report file ");
                sb.append(reportFile);
                sb.append("; deleting");
                logger.mo19680d(sb.toString(), e);
                reportFile.delete();
            }
        }
        return allReports;
    }

    private List<File> capAndGetOpenSessions(String currentSessionId) {
        List<File> openSessionDirectories = getFilesInDirectory(this.openSessionsDirectory, CrashlyticsReportPersistence$$Lambda$2.lambdaFactory$(currentSessionId));
        Collections.sort(openSessionDirectories, LATEST_SESSION_ID_FIRST_COMPARATOR);
        if (openSessionDirectories.size() <= 8) {
            return openSessionDirectories;
        }
        for (File openSessionDirectory : openSessionDirectories.subList(8, openSessionDirectories.size())) {
            recursiveDelete(openSessionDirectory);
        }
        return openSessionDirectories.subList(0, 8);
    }

    static /* synthetic */ boolean lambda$capAndGetOpenSessions$3(String currentSessionId, File f) {
        return f.isDirectory() && !f.getName().equals(currentSessionId);
    }

    private void capFinalizedReports() {
        int maxReportsToKeep = this.settingsDataProvider.getSettings().getSessionData().maxCompleteSessionsCount;
        List<File> finalizedReportFiles = getAllFinalizedReportFiles();
        int fileCount = finalizedReportFiles.size();
        if (fileCount > maxReportsToKeep) {
            for (File reportFile : finalizedReportFiles.subList(maxReportsToKeep, fileCount)) {
                reportFile.delete();
            }
        }
    }

    private List<File> getAllFinalizedReportFiles() {
        return sortAndCombineReportFiles(combineReportFiles(getAllFilesInDirectory(this.priorityReportsDirectory), getAllFilesInDirectory(this.nativeReportsDirectory)), getAllFilesInDirectory(this.reportsDirectory));
    }

    private File getSessionDirectoryById(String sessionId) {
        return new File(this.openSessionsDirectory, sessionId);
    }

    private void synthesizeReport(File sessionDirectory, long sessionEndTime) {
        String userId;
        List<File> eventFiles = getFilesInDirectory(sessionDirectory, EVENT_FILE_FILTER);
        if (!eventFiles.isEmpty()) {
            Collections.sort(eventFiles);
            ArrayList arrayList = new ArrayList();
            boolean isHighPriorityReport = false;
            for (File eventFile : eventFiles) {
                try {
                    arrayList.add(TRANSFORM.eventFromJson(readTextFile(eventFile)));
                    isHighPriorityReport = isHighPriorityReport || isHighPriorityEventFile(eventFile.getName());
                } catch (IOException e) {
                    Logger logger = Logger.getLogger();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Could not add event to report for ");
                    sb.append(eventFile);
                    logger.mo19680d(sb.toString(), e);
                }
            }
            try {
                userId = readTextFile(new File(sessionDirectory, USER_FILE_NAME));
            } catch (IOException e2) {
                Logger logger2 = Logger.getLogger();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Could not read user ID file in ");
                sb2.append(sessionDirectory.getName());
                logger2.mo19680d(sb2.toString(), e2);
                userId = null;
            }
            synthesizeReportFile(new File(sessionDirectory, REPORT_FILE_NAME), isHighPriorityReport ? this.priorityReportsDirectory : this.reportsDirectory, arrayList, sessionEndTime, isHighPriorityReport, userId);
        }
    }

    private static void synthesizeNativeReportFile(File reportFile, File outputDirectory, FilesPayload ndkPayload, String previousSessionId) {
        try {
            writeTextFile(new File(prepareDirectory(outputDirectory), previousSessionId), TRANSFORM.reportToJson(TRANSFORM.reportFromJson(readTextFile(reportFile)).withNdkPayload(ndkPayload)));
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Could not synthesize final native report file for ");
            sb.append(reportFile);
            logger.mo19680d(sb.toString(), e);
        }
    }

    private static void synthesizeReportFile(File reportFile, File outputDirectory, List<Event> events, long sessionEndTime, boolean isCrashed, String userId) {
        try {
            CrashlyticsReport report = TRANSFORM.reportFromJson(readTextFile(reportFile)).withSessionEndFields(sessionEndTime, isCrashed, userId).withEvents(ImmutableList.from(events));
            Session session = report.getSession();
            if (session != null) {
                writeTextFile(new File(prepareDirectory(outputDirectory), session.getIdentifier()), TRANSFORM.reportToJson(report));
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Could not synthesize final report file for ");
            sb.append(reportFile);
            logger.mo19680d(sb.toString(), e);
        }
    }

    private static List<File> sortAndCombineReportFiles(List<File>... reports) {
        for (List<File> reportList : reports) {
            Collections.sort(reportList, LATEST_SESSION_ID_FIRST_COMPARATOR);
        }
        return combineReportFiles(reports);
    }

    private static List<File> combineReportFiles(List<File>... reports) {
        ArrayList<File> allReportsFiles = new ArrayList<>();
        int totalReports = 0;
        for (List<File> reportList : reports) {
            totalReports += reportList.size();
        }
        allReportsFiles.ensureCapacity(totalReports);
        for (List<File> reportList2 : reports) {
            allReportsFiles.addAll(reportList2);
        }
        return allReportsFiles;
    }

    private static boolean isHighPriorityEventFile(String fileName) {
        return fileName.startsWith("event") && fileName.endsWith(PRIORITY_EVENT_SUFFIX);
    }

    /* access modifiers changed from: private */
    public static boolean isNormalPriorityEventFile(File dir, String name) {
        return name.startsWith("event") && !name.endsWith(PRIORITY_EVENT_SUFFIX);
    }

    private static String generateEventFilename(int eventNumber, boolean isHighPriority) {
        String paddedEventNumber = String.format(Locale.US, EVENT_COUNTER_FORMAT, new Object[]{Integer.valueOf(eventNumber)});
        String prioritySuffix = isHighPriority ? PRIORITY_EVENT_SUFFIX : "";
        StringBuilder sb = new StringBuilder();
        sb.append("event");
        sb.append(paddedEventNumber);
        sb.append(prioritySuffix);
        return sb.toString();
    }

    private static int trimEvents(File sessionDirectory, int maximum) {
        List<File> normalPriorityEventFiles = getFilesInDirectory(sessionDirectory, CrashlyticsReportPersistence$$Lambda$3.lambdaFactory$());
        Collections.sort(normalPriorityEventFiles, CrashlyticsReportPersistence$$Lambda$4.lambdaFactory$());
        return capFilesCount(normalPriorityEventFiles, maximum);
    }

    private static String getEventNameWithoutPriority(String eventFileName) {
        return eventFileName.substring(0, EVENT_NAME_LENGTH);
    }

    /* access modifiers changed from: private */
    public static int oldestEventFileFirst(File f1, File f2) {
        return getEventNameWithoutPriority(f1.getName()).compareTo(getEventNameWithoutPriority(f2.getName()));
    }

    private static List<File> getAllFilesInDirectory(File directory) {
        return getFilesInDirectory(directory, (FileFilter) null);
    }

    private static List<File> getFilesInDirectory(File directory, FilenameFilter filter) {
        if (!directory.isDirectory()) {
            return Collections.emptyList();
        }
        File[] files = filter == null ? directory.listFiles() : directory.listFiles(filter);
        return files != null ? Arrays.asList(files) : Collections.emptyList();
    }

    private static List<File> getFilesInDirectory(File directory, FileFilter filter) {
        if (!directory.isDirectory()) {
            return Collections.emptyList();
        }
        File[] files = filter == null ? directory.listFiles() : directory.listFiles(filter);
        return files != null ? Arrays.asList(files) : Collections.emptyList();
    }

    private static File prepareDirectory(File directory) throws IOException {
        if (makeDirectory(directory)) {
            return directory;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Could not create directory ");
        sb.append(directory);
        throw new IOException(sb.toString());
    }

    private static boolean makeDirectory(File directory) {
        return directory.exists() || directory.mkdirs();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0016, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        $closeResource(r1, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void writeTextFile(java.io.File r3, java.lang.String r4) throws java.io.IOException {
        /*
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter
            java.io.FileOutputStream r1 = new java.io.FileOutputStream
            r1.<init>(r3)
            java.nio.charset.Charset r2 = UTF_8
            r0.<init>(r1, r2)
            r0.write(r4)     // Catch:{ all -> 0x0014 }
            r1 = 0
            $closeResource(r1, r0)
            return
        L_0x0014:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0016 }
        L_0x0016:
            r2 = move-exception
            $closeResource(r1, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.persistence.CrashlyticsReportPersistence.writeTextFile(java.io.File, java.lang.String):void");
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

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        $closeResource(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String readTextFile(java.io.File r7) throws java.io.IOException {
        /*
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r0]
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.io.FileInputStream r2 = new java.io.FileInputStream
            r2.<init>(r7)
        L_0x000e:
            int r3 = r2.read(r0)     // Catch:{ all -> 0x002a }
            r4 = r3
            if (r3 <= 0) goto L_0x001a
            r3 = 0
            r1.write(r0, r3, r4)     // Catch:{ all -> 0x002a }
            goto L_0x000e
        L_0x001a:
            java.lang.String r3 = new java.lang.String     // Catch:{ all -> 0x002a }
            byte[] r5 = r1.toByteArray()     // Catch:{ all -> 0x002a }
            java.nio.charset.Charset r6 = UTF_8     // Catch:{ all -> 0x002a }
            r3.<init>(r5, r6)     // Catch:{ all -> 0x002a }
            r5 = 0
            $closeResource(r5, r2)
            return r3
        L_0x002a:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x002c }
        L_0x002c:
            r4 = move-exception
            $closeResource(r3, r2)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.persistence.CrashlyticsReportPersistence.readTextFile(java.io.File):java.lang.String");
    }

    private static int capFilesCount(List<File> files, int maximum) {
        int numRetained = files.size();
        for (File f : files) {
            if (numRetained <= maximum) {
                return numRetained;
            }
            recursiveDelete(f);
            numRetained--;
        }
        return numRetained;
    }

    private static void recursiveDelete(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    recursiveDelete(f);
                }
            }
            file.delete();
        }
    }
}
