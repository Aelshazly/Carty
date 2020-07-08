package com.navibees.navigatorapp.data.dao;

import com.navibees.navigatorapp.models.Exhibitor;
import java.util.List;

public interface ExhibitorDao {
    void clear();

    List<Exhibitor> getAll();

    void insertAll(List<Exhibitor> list);
}
