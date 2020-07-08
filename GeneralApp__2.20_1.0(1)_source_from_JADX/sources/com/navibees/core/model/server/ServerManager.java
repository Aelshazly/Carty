package com.navibees.core.model.server;

import android.app.Application;
import com.navibees.core.interfaces.UserProfilerLoginListener;
import com.navibees.core.interfaces.UserProfilerRegistrationListener;
import java.security.InvalidParameterException;
import org.json.JSONException;

public interface ServerManager {
    void loginUser(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, UserProfilerLoginListener userProfilerLoginListener) throws InvalidParameterException, JSONException;

    void registerPNSDeviceIdentifier(Application application, String str);

    void registerUser(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, UserProfilerRegistrationListener userProfilerRegistrationListener) throws InvalidParameterException, JSONException;

    void startSync(Application application, String str, String str2);
}
