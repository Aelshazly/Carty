package com.navibees.navigatorapp.data.dao;

import com.navibees.navigatorapp.models.Sponsor;
import java.util.List;

public interface SponserDao {
    void clear();

    List<Sponsor> getAll();

    void insertAll(List<Sponsor> list);
}
