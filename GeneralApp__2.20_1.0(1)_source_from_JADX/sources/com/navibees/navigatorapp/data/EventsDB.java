package com.navibees.navigatorapp.data;

import android.content.Context;
import androidx.room.Room;

public class EventsDB {
    private static EventsDB instance;

    /* renamed from: db */
    public AppDatabase f204db;

    private EventsDB(Context context) {
        this.f204db = (AppDatabase) Room.databaseBuilder(context, AppDatabase.class, "EventsDB").allowMainThreadQueries().build();
    }

    public static EventsDB getInstance(Context context) {
        if (instance == null) {
            instance = new EventsDB(context);
        }
        return instance;
    }
}
