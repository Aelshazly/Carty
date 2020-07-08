package com.navibees.navigatorapp.data.dao;

import com.navibees.navigatorapp.models.Speaker;
import java.util.List;

public interface SpeakersDao {
    void clear();

    List<Speaker> getAll();

    void insertAll(List<Speaker> list);
}
