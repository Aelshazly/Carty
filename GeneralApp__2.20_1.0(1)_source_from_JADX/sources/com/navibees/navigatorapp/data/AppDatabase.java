package com.navibees.navigatorapp.data;

import androidx.room.RoomDatabase;
import com.navibees.navigatorapp.data.dao.ExhibitorDao;
import com.navibees.navigatorapp.data.dao.SpeakersDao;
import com.navibees.navigatorapp.data.dao.SponserDao;

public abstract class AppDatabase extends RoomDatabase {
    public abstract ExhibitorDao exhibitorDao();

    public abstract SpeakersDao speakersDao();

    public abstract SponserDao sponsorDao();
}
