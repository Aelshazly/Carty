package com.google.firebase.installations;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.installations.FirebaseInstallationsException.Status;
import com.google.firebase.installations.local.IidStore;
import com.google.firebase.installations.local.PersistedInstallation;
import com.google.firebase.installations.local.PersistedInstallationEntry;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import com.google.firebase.installations.remote.InstallationResponse;
import com.google.firebase.installations.remote.InstallationResponse.ResponseCode;
import com.google.firebase.installations.remote.TokenResult;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
public class FirebaseInstallations implements FirebaseInstallationsApi {
    private static final String CHIME_FIREBASE_APP_NAME = "CHIME_ANDROID_SDK";
    private static final int CORE_POOL_SIZE = 0;
    private static final long KEEP_ALIVE_TIME_IN_SECONDS = 30;
    private static final String LOCKFILE_NAME_GENERATE_FID = "generatefid.lock";
    private static final int MAXIMUM_POOL_SIZE = 1;
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, String.format("firebase-installations-executor-%d", new Object[]{Integer.valueOf(this.mCount.getAndIncrement())}));
        }
    };
    private static final Object lockGenerateFid = new Object();
    private final ExecutorService backgroundExecutor;
    private final RandomFidGenerator fidGenerator;
    private final FirebaseApp firebaseApp;
    private final IidStore iidStore;
    private final List<StateListener> listeners;
    private final Object lock;
    private final ExecutorService networkExecutor;
    private final PersistedInstallation persistedInstallation;
    private final FirebaseInstallationServiceClient serviceClient;
    private final Utils utils;

    /* renamed from: com.google.firebase.installations.FirebaseInstallations$2 */
    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    static /* synthetic */ class C08172 {

        /* renamed from: $SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode */
        static final /* synthetic */ int[] f83xc38d2559 = new int[ResponseCode.values().length];

        /* renamed from: $SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode */
        static final /* synthetic */ int[] f84xe5baa01a = new int[TokenResult.ResponseCode.values().length];

        static {
            try {
                f84xe5baa01a[TokenResult.ResponseCode.OK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f84xe5baa01a[TokenResult.ResponseCode.BAD_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f84xe5baa01a[TokenResult.ResponseCode.AUTH_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f83xc38d2559[ResponseCode.OK.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f83xc38d2559[ResponseCode.BAD_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    FirebaseInstallations(FirebaseApp firebaseApp2, UserAgentPublisher publisher, HeartBeatInfo heartbeatInfo) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, KEEP_ALIVE_TIME_IN_SECONDS, TimeUnit.SECONDS, new LinkedBlockingQueue(), THREAD_FACTORY);
        this(threadPoolExecutor, firebaseApp2, new FirebaseInstallationServiceClient(firebaseApp2.getApplicationContext(), publisher, heartbeatInfo), new PersistedInstallation(firebaseApp2), new Utils(), new IidStore(firebaseApp2), new RandomFidGenerator());
    }

    FirebaseInstallations(ExecutorService backgroundExecutor2, FirebaseApp firebaseApp2, FirebaseInstallationServiceClient serviceClient2, PersistedInstallation persistedInstallation2, Utils utils2, IidStore iidStore2, RandomFidGenerator fidGenerator2) {
        this.lock = new Object();
        this.listeners = new ArrayList();
        this.firebaseApp = firebaseApp2;
        this.serviceClient = serviceClient2;
        this.persistedInstallation = persistedInstallation2;
        this.utils = utils2;
        this.iidStore = iidStore2;
        this.fidGenerator = fidGenerator2;
        this.backgroundExecutor = backgroundExecutor2;
        ThreadPoolExecutor threadPoolExecutor = r8;
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(0, 1, KEEP_ALIVE_TIME_IN_SECONDS, TimeUnit.SECONDS, new LinkedBlockingQueue(), THREAD_FACTORY);
        this.networkExecutor = threadPoolExecutor;
    }

    private void preConditionChecks() {
        Preconditions.checkNotEmpty(getApplicationId());
        Preconditions.checkNotEmpty(getProjectIdentifier());
        Preconditions.checkNotEmpty(getApiKey());
    }

    /* access modifiers changed from: 0000 */
    public String getProjectIdentifier() {
        if (TextUtils.isEmpty(this.firebaseApp.getOptions().getProjectId())) {
            return this.firebaseApp.getOptions().getGcmSenderId();
        }
        return this.firebaseApp.getOptions().getProjectId();
    }

    public static FirebaseInstallations getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static FirebaseInstallations getInstance(FirebaseApp app) {
        Preconditions.checkArgument(app != null, "Null is not a valid value of FirebaseApp.");
        return (FirebaseInstallations) app.get(FirebaseInstallationsApi.class);
    }

    /* access modifiers changed from: 0000 */
    public String getApplicationId() {
        return this.firebaseApp.getOptions().getApplicationId();
    }

    /* access modifiers changed from: 0000 */
    public String getApiKey() {
        return this.firebaseApp.getOptions().getApiKey();
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return this.firebaseApp.getName();
    }

    public Task<String> getId() {
        preConditionChecks();
        Task<String> task = addGetIdListener();
        this.backgroundExecutor.execute(FirebaseInstallations$$Lambda$1.lambdaFactory$(this));
        return task;
    }

    public Task<InstallationTokenResult> getToken(boolean forceRefresh) {
        preConditionChecks();
        Task<InstallationTokenResult> task = addGetAuthTokenListener();
        if (forceRefresh) {
            this.backgroundExecutor.execute(FirebaseInstallations$$Lambda$2.lambdaFactory$(this));
        } else {
            this.backgroundExecutor.execute(FirebaseInstallations$$Lambda$3.lambdaFactory$(this));
        }
        return task;
    }

    public Task<Void> delete() {
        return Tasks.call(this.backgroundExecutor, FirebaseInstallations$$Lambda$4.lambdaFactory$(this));
    }

    private Task<String> addGetIdListener() {
        TaskCompletionSource<String> taskCompletionSource = new TaskCompletionSource<>();
        StateListener l = new GetIdListener(taskCompletionSource);
        synchronized (this.lock) {
            this.listeners.add(l);
        }
        return taskCompletionSource.getTask();
    }

    private Task<InstallationTokenResult> addGetAuthTokenListener() {
        TaskCompletionSource<InstallationTokenResult> taskCompletionSource = new TaskCompletionSource<>();
        StateListener l = new GetAuthTokenListener(this.utils, taskCompletionSource);
        synchronized (this.lock) {
            this.listeners.add(l);
        }
        return taskCompletionSource.getTask();
    }

    private void triggerOnStateReached(PersistedInstallationEntry persistedInstallationEntry) {
        synchronized (this.lock) {
            Iterator<StateListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                if (((StateListener) it.next()).onStateReached(persistedInstallationEntry)) {
                    it.remove();
                }
            }
        }
    }

    private void triggerOnException(PersistedInstallationEntry prefs, Exception exception) {
        synchronized (this.lock) {
            Iterator<StateListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                if (((StateListener) it.next()).onException(prefs, exception)) {
                    it.remove();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final void doGetId() {
        doRegistrationInternal(false);
    }

    /* access modifiers changed from: private */
    public final void doGetAuthTokenWithoutForceRefresh() {
        doRegistrationInternal(false);
    }

    /* access modifiers changed from: private */
    public final void doGetAuthTokenForceRefresh() {
        doRegistrationInternal(true);
    }

    private final void doRegistrationInternal(boolean forceRefresh) {
        PersistedInstallationEntry prefs = getPrefsWithGeneratedIdMultiProcessSafe();
        if (forceRefresh) {
            prefs = prefs.withClearedAuthToken();
        }
        triggerOnStateReached(prefs);
        this.networkExecutor.execute(FirebaseInstallations$$Lambda$5.lambdaFactory$(this, forceRefresh));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doNetworkCall(boolean r4) {
        /*
            r3 = this;
            com.google.firebase.installations.local.PersistedInstallationEntry r0 = r3.getPrefsWithGeneratedIdMultiProcessSafe()
            boolean r1 = r0.isErrored()     // Catch:{ IOException -> 0x0054 }
            if (r1 != 0) goto L_0x0023
            boolean r1 = r0.isUnregistered()     // Catch:{ IOException -> 0x0054 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0023
        L_0x0011:
            if (r4 != 0) goto L_0x001d
            com.google.firebase.installations.Utils r1 = r3.utils     // Catch:{ IOException -> 0x0054 }
            boolean r1 = r1.isAuthTokenExpired(r0)     // Catch:{ IOException -> 0x0054 }
            if (r1 == 0) goto L_0x001c
            goto L_0x001d
        L_0x001c:
            return
        L_0x001d:
            com.google.firebase.installations.local.PersistedInstallationEntry r1 = r3.fetchAuthTokenFromServer(r0)     // Catch:{ IOException -> 0x0054 }
            r0 = r1
            goto L_0x0028
        L_0x0023:
            com.google.firebase.installations.local.PersistedInstallationEntry r1 = r3.registerFidWithServer(r0)     // Catch:{ IOException -> 0x0054 }
            r0 = r1
        L_0x0028:
            com.google.firebase.installations.local.PersistedInstallation r1 = r3.persistedInstallation
            r1.insertOrUpdatePersistedInstallationEntry(r0)
            boolean r1 = r0.isErrored()
            if (r1 == 0) goto L_0x003f
            com.google.firebase.installations.FirebaseInstallationsException r1 = new com.google.firebase.installations.FirebaseInstallationsException
            com.google.firebase.installations.FirebaseInstallationsException$Status r2 = com.google.firebase.installations.FirebaseInstallationsException.Status.BAD_CONFIG
            r1.<init>(r2)
            r3.triggerOnException(r0, r1)
            goto L_0x0053
        L_0x003f:
            boolean r1 = r0.isNotGenerated()
            if (r1 == 0) goto L_0x0050
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "cleared fid due to auth error"
            r1.<init>(r2)
            r3.triggerOnException(r0, r1)
            goto L_0x0053
        L_0x0050:
            r3.triggerOnStateReached(r0)
        L_0x0053:
            return
        L_0x0054:
            r1 = move-exception
            r3.triggerOnException(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.installations.FirebaseInstallations.doNetworkCall(boolean):void");
    }

    private PersistedInstallationEntry getPrefsWithGeneratedIdMultiProcessSafe() {
        PersistedInstallationEntry prefs;
        synchronized (lockGenerateFid) {
            CrossProcessLock lock2 = CrossProcessLock.acquire(this.firebaseApp.getApplicationContext(), LOCKFILE_NAME_GENERATE_FID);
            try {
                prefs = this.persistedInstallation.readPersistedInstallationEntryValue();
                if (prefs.isNotGenerated()) {
                    prefs = this.persistedInstallation.insertOrUpdatePersistedInstallationEntry(prefs.withUnregisteredFid(readExistingIidOrCreateFid(prefs)));
                }
                if (lock2 != null) {
                    lock2.releaseAndClose();
                }
            } catch (Throwable th) {
                if (lock2 != null) {
                    lock2.releaseAndClose();
                }
                throw th;
            }
        }
        return prefs;
    }

    private String readExistingIidOrCreateFid(PersistedInstallationEntry prefs) {
        if ((!this.firebaseApp.getName().equals(CHIME_FIREBASE_APP_NAME) && !this.firebaseApp.isDefaultApp()) || !prefs.shouldAttemptMigration()) {
            return this.fidGenerator.createRandomFid();
        }
        String fid = this.iidStore.readIid();
        if (TextUtils.isEmpty(fid)) {
            fid = this.fidGenerator.createRandomFid();
        }
        return fid;
    }

    private PersistedInstallationEntry registerFidWithServer(PersistedInstallationEntry prefs) throws IOException {
        String iidToken = null;
        if (prefs.getFirebaseInstallationId().length() == 11) {
            iidToken = this.iidStore.readToken();
        }
        InstallationResponse response = this.serviceClient.createFirebaseInstallation(getApiKey(), prefs.getFirebaseInstallationId(), getProjectIdentifier(), getApplicationId(), iidToken);
        int i = C08172.f83xc38d2559[response.getResponseCode().ordinal()];
        if (i == 1) {
            return prefs.withRegisteredFid(response.getFid(), response.getRefreshToken(), this.utils.currentTimeInSecs(), response.getAuthToken().getToken(), response.getAuthToken().getTokenExpirationTimestamp());
        } else if (i == 2) {
            return prefs.withFisError("BAD CONFIG");
        } else {
            throw new IOException();
        }
    }

    private PersistedInstallationEntry fetchAuthTokenFromServer(PersistedInstallationEntry prefs) throws IOException {
        TokenResult tokenResult = this.serviceClient.generateAuthToken(getApiKey(), prefs.getFirebaseInstallationId(), getProjectIdentifier(), prefs.getRefreshToken());
        int i = C08172.f84xe5baa01a[tokenResult.getResponseCode().ordinal()];
        if (i == 1) {
            return prefs.withAuthToken(tokenResult.getToken(), tokenResult.getTokenExpirationTimestamp(), this.utils.currentTimeInSecs());
        } else if (i == 2) {
            return prefs.withFisError("BAD CONFIG");
        } else {
            if (i == 3) {
                return prefs.withNoGeneratedFid();
            }
            throw new IOException();
        }
    }

    /* access modifiers changed from: private */
    public Void deleteFirebaseInstallationId() throws FirebaseInstallationsException, IOException {
        PersistedInstallationEntry entry = this.persistedInstallation.readPersistedInstallationEntryValue();
        if (entry.isRegistered()) {
            try {
                this.serviceClient.deleteFirebaseInstallation(getApiKey(), entry.getFirebaseInstallationId(), getProjectIdentifier(), entry.getRefreshToken());
            } catch (FirebaseException e) {
                throw new FirebaseInstallationsException("Failed to delete a Firebase Installation.", Status.BAD_CONFIG);
            }
        }
        this.persistedInstallation.insertOrUpdatePersistedInstallationEntry(entry.withNoGeneratedFid());
        return null;
    }
}
