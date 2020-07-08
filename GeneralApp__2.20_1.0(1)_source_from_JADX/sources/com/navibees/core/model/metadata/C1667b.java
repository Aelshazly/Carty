package com.navibees.core.model.metadata;

import com.navibees.core.model.metadata.json.ActivityGroup;
import com.navibees.core.model.metadata.json.ApplicationConfiguration;
import com.navibees.core.model.metadata.json.BeaconNodeConfiguration;
import com.navibees.core.model.metadata.json.Facility;
import com.navibees.core.model.metadata.json.Floor;
import com.navibees.core.model.metadata.json.IndoorLocationRestriction;
import com.navibees.core.model.metadata.json.MonitoredRegion;
import com.navibees.core.model.metadata.json.OutdoorRegion;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.POICategory;
import com.navibees.core.model.metadata.json.Portal;
import java.util.List;

/* renamed from: com.navibees.core.model.metadata.b */
/* compiled from: DataChangeListener */
public interface C1667b {
    /* renamed from: a */
    void mo29099a(ApplicationConfiguration applicationConfiguration, long j);

    /* renamed from: a */
    void mo29100a(List<MonitoredRegion> list, long j);

    /* renamed from: a */
    void mo29101a(List<IndoorLocationRestriction> list, long j, String str);

    /* renamed from: b */
    void mo29102b(List<Portal> list, long j);

    /* renamed from: c */
    void mo29103c(List<BeaconNodeConfiguration> list, long j);

    /* renamed from: d */
    void mo29104d(List<? extends POI> list, long j);

    /* renamed from: e */
    void mo29105e(List<? extends Facility> list, long j);

    /* renamed from: f */
    void mo29106f(List<ActivityGroup> list, long j);

    /* renamed from: g */
    void mo29107g(List<? extends Floor> list, long j);

    /* renamed from: h */
    void mo29108h(List<POICategory> list, long j);

    /* renamed from: i */
    void mo29109i(List<OutdoorRegion> list, long j);
}
