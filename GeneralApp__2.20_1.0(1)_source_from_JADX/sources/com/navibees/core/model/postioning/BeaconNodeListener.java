package com.navibees.core.model.postioning;

import com.navibees.core.model.license.NaviBeesLicenseExpireException;
import com.navibees.core.model.license.NaviBeesLicenseNotAuthorizedException;
import com.navibees.core.model.metadata.BeaconNode;
import java.util.List;

public interface BeaconNodeListener {
    void beaconNodeCallback(List<BeaconNode> list) throws NaviBeesLicenseNotAuthorizedException, NaviBeesLicenseExpireException;
}
