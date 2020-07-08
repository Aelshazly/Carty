package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri.Builder;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.util.PriorityMapping;

/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
public class AlarmManagerScheduler implements WorkScheduler {
    static final String ATTEMPT_NUMBER = "attemptNumber";
    static final String BACKEND_NAME = "backendName";
    static final String EVENT_PRIORITY = "priority";
    static final String EXTRAS = "extras";
    private static final String LOG_TAG = "AlarmManagerScheduler";
    private AlarmManager alarmManager;
    private final Clock clock;
    private final SchedulerConfig config;
    private final Context context;
    private final EventStore eventStore;

    public AlarmManagerScheduler(Context applicationContext, EventStore eventStore2, Clock clock2, SchedulerConfig config2) {
        this(applicationContext, eventStore2, (AlarmManager) applicationContext.getSystemService(NotificationCompat.CATEGORY_ALARM), clock2, config2);
    }

    AlarmManagerScheduler(Context applicationContext, EventStore eventStore2, AlarmManager alarmManager2, Clock clock2, SchedulerConfig config2) {
        this.context = applicationContext;
        this.eventStore = eventStore2;
        this.alarmManager = alarmManager2;
        this.clock = clock2;
        this.config = config2;
    }

    /* access modifiers changed from: 0000 */
    public boolean isJobServiceOn(Intent intent) {
        return PendingIntent.getBroadcast(this.context, 0, intent, 536870912) != null;
    }

    public void schedule(TransportContext transportContext, int attemptNumber) {
        Builder intentDataBuilder = new Builder();
        intentDataBuilder.appendQueryParameter(BACKEND_NAME, transportContext.getBackendName());
        intentDataBuilder.appendQueryParameter(EVENT_PRIORITY, String.valueOf(PriorityMapping.toInt(transportContext.getPriority())));
        if (transportContext.getExtras() != null) {
            intentDataBuilder.appendQueryParameter(EXTRAS, Base64.encodeToString(transportContext.getExtras(), 0));
        }
        Intent intent = new Intent(this.context, AlarmManagerSchedulerBroadcastReceiver.class);
        intent.setData(intentDataBuilder.build());
        intent.putExtra(ATTEMPT_NUMBER, attemptNumber);
        boolean isJobServiceOn = isJobServiceOn(intent);
        String str = LOG_TAG;
        if (isJobServiceOn) {
            Logging.m14d(str, "Upload for context %s is already scheduled. Returning...", (Object) transportContext);
            return;
        }
        long backendTime = this.eventStore.getNextCallTime(transportContext);
        long scheduleDelay = this.config.getScheduleDelay(transportContext.getPriority(), backendTime, attemptNumber);
        Logging.m16d(str, "Scheduling upload for context %s in %dms(Backend next call timestamp %d). Attempt %d", transportContext, Long.valueOf(scheduleDelay), Long.valueOf(backendTime), Integer.valueOf(attemptNumber));
        this.alarmManager.set(3, this.clock.getTime() + scheduleDelay, PendingIntent.getBroadcast(this.context, 0, intent, 0));
    }
}
