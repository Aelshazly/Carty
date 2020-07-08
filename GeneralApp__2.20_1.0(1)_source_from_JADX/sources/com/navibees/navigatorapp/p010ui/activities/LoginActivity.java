package com.navibees.navigatorapp.p010ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.navibees.core.NaviBeesApplication;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.model.server.OnSyncListener;
import com.navibees.core.util.NaviBeesUtils;
import com.navibees.navigatorapp.C1170R;
import com.navibees.navigatorapp.data.Prefs;
import com.navibees.navigatorapp.models.OperationApplication;
import com.navibees.navigatorapp.models.Venue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import p008cz.msebera.android.httpclient.Header;

/* renamed from: com.navibees.navigatorapp.ui.activities.LoginActivity */
public class LoginActivity extends AppCompatActivity {
    private AsyncHttpClient client;
    /* access modifiers changed from: private */
    public Venue currentVenue;
    private LinearLayout loginLayout;
    /* access modifiers changed from: private */
    public OperationApplication operationApplication;
    private EditText password;
    /* access modifiers changed from: private */
    public ProgressDialog progressDialog;
    private RelativeLayout singleVenueLayout;
    /* access modifiers changed from: private */
    public boolean testing = false;
    private EditText username;
    private TextView venueNameTxt;
    private ListView venuesList;
    private LinearLayout venuesListLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(512, 512);
        setContentView((int) C1170R.layout.activity_login);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setMessage("Please wait..");
        this.progressDialog.setCancelable(false);
        this.client = new AsyncHttpClient();
        this.username = (EditText) findViewById(C1170R.C1173id.ed_username);
        this.password = (EditText) findViewById(C1170R.C1173id.ed_password);
        getVenuesData();
        this.loginLayout = (LinearLayout) findViewById(C1170R.C1173id.loginLayout);
        this.venuesListLayout = (LinearLayout) findViewById(C1170R.C1173id.VenuesListLayout);
        this.singleVenueLayout = (RelativeLayout) findViewById(C1170R.C1173id.singleVenueLayout);
        this.venueNameTxt = (TextView) findViewById(C1170R.C1173id.venueNameTxt);
        this.venuesList = (ListView) findViewById(C1170R.C1173id.VenuesList);
        ((Button) findViewById(C1170R.C1173id.bt_login)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.authenticateUser();
            }
        });
        ((Button) findViewById(C1170R.C1173id.bt_logout)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.showLogin();
                if (NaviBeesManager.getInstance(LoginActivity.this.getApplication()).getNaviBeesBeaconManager() != null) {
                    NaviBeesManager.getInstance(LoginActivity.this.getApplication()).getNaviBeesBeaconManager().unbindService();
                }
                Prefs.getInstance(LoginActivity.this.getApplicationContext()).clearPref();
            }
        });
        ((Button) findViewById(C1170R.C1173id.bt_continue)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Prefs.getInstance(LoginActivity.this.getApplicationContext()).getOperationUserIsAdmin()) {
                    LoginActivity.this.showVenuesList();
                    return;
                }
                LoginActivity loginActivity = LoginActivity.this;
                if (loginActivity.isMetadataDownloaded(Prefs.getInstance(loginActivity.getApplicationContext()).getOperationVenueId())) {
                    LoginActivity.this.goToActivity();
                } else {
                    Toast.makeText(LoginActivity.this, "Initialize failed", 0).show();
                }
            }
        });
        if (!Prefs.getInstance(getApplicationContext()).getOperationUsername().isEmpty()) {
            checkDynamicLink();
            if (Prefs.getInstance(getApplicationContext()).getOperationUserIsAdmin()) {
                showVenuesList();
            } else {
                showLoggedInLayout(Prefs.getInstance(getApplicationContext()).getOperationVenueName());
            }
        }
    }

    private void getVenuesData() {
        this.progressDialog.show();
        if (Prefs.getInstance(getApplicationContext()).getOperationData() != null) {
            try {
                this.progressDialog.dismiss();
                this.operationApplication = new OperationApplication(Prefs.getInstance(getApplicationContext()).getOperationData());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (NaviBeesUtils.isInternetConnectionOnline(this)) {
            this.client.get("http://cms2.navibees.com/OperationsApp/Operations_App.json", new AsyncHttpResponseHandler() {
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200) {
                        LoginActivity.this.progressDialog.dismiss();
                        String response = new String(responseBody);
                        Prefs.getInstance(LoginActivity.this.getApplicationContext()).saveOperationData(response);
                        try {
                            LoginActivity.this.operationApplication = new OperationApplication(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    LoginActivity.this.progressDialog.dismiss();
                    LoginActivity loginActivity = LoginActivity.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error: ");
                    sb.append(error.getMessage());
                    Toast.makeText(loginActivity, sb.toString(), 0).show();
                }
            });
        } else if (Prefs.getInstance(getApplicationContext()).getOperationData() != null) {
            try {
                this.progressDialog.dismiss();
                this.operationApplication = new OperationApplication(Prefs.getInstance(getApplicationContext()).getOperationData());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else {
            this.progressDialog.dismiss();
            Toast.makeText(this, "Error: Failed to load data", 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void authenticateUser() {
        if (this.username.getText().toString().isEmpty() || this.password.getText().toString().isEmpty()) {
            this.progressDialog.dismiss();
            Toast.makeText(this, "Enter username and password", 0).show();
            return;
        }
        OperationApplication operationApplication2 = this.operationApplication;
        if (operationApplication2 != null) {
            if (operationApplication2.adminUsername.equals(this.username.getText().toString()) && this.operationApplication.adminPassword.equals(this.password.getText().toString())) {
                Prefs.getInstance(getApplicationContext()).saveOperationUsername(this.username.getText().toString());
                Prefs.getInstance(getApplicationContext()).saveOperationUserType(true);
                showVenuesList();
            } else if (checkUserCredentials(this.username.getText().toString(), this.password.getText().toString())) {
                Prefs.getInstance(getApplicationContext()).saveOperationUsername(this.username.getText().toString());
                Prefs.getInstance(getApplicationContext()).saveOperationUserType(false);
                showLoggedInLayout(Prefs.getInstance(getApplicationContext()).getOperationVenueName());
            } else {
                Toast.makeText(this, "Error: Invalid username or password", 0).show();
            }
        } else if (!NaviBeesUtils.isInternetConnectionOnline(this)) {
            Toast.makeText(this, "Error: No internet connection", 0).show();
        } else {
            Toast.makeText(this, "Error: Failed to load data", 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void showVenuesList() {
        this.loginLayout.setVisibility(8);
        this.venuesListLayout.setVisibility(0);
        this.singleVenueLayout.setVisibility(8);
        final ArrayList arrayList = new ArrayList();
        OperationApplication operationApplication2 = this.operationApplication;
        if (operationApplication2 != null) {
            for (Venue venue : operationApplication2.venues) {
                arrayList.add(venue.venue);
            }
            final ArrayList arrayList2 = arrayList;
            C12325 r1 = new ArrayAdapter<String>(this, 17367043, arrayList) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView textView = (TextView) view.findViewById(16908308);
                    textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    LoginActivity loginActivity = LoginActivity.this;
                    if (loginActivity.isMetadataDownloaded(((Venue) loginActivity.operationApplication.venues.get(position)).appId)) {
                        Prefs.getInstance(LoginActivity.this.getApplicationContext()).saveOperationVenue(((Venue) LoginActivity.this.operationApplication.venues.get(position)).venue, ((Venue) LoginActivity.this.operationApplication.venues.get(position)).appId);
                    }
                    if (Prefs.getInstance(LoginActivity.this.getApplicationContext()).getOperationVenueName().equals(arrayList2.get(position))) {
                        textView.setTextColor(LoginActivity.this.getResources().getColor(C1170R.C1171color.GreenLight));
                    }
                    return view;
                }
            };
            this.venuesList.setAdapter(r1);
        }
        this.venuesList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                Builder alertDialog = new Builder(LoginActivity.this);
                StringBuilder sb = new StringBuilder();
                sb.append("Select ");
                sb.append((String) arrayList.get(i));
                sb.append(" ?");
                alertDialog.setTitle((CharSequence) sb.toString());
                alertDialog.setMessage((CharSequence) "Press OK to continue");
                alertDialog.setPositiveButton((CharSequence) "OK", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (LoginActivity.this.testing) {
                            LoginActivity.this.goToActivity();
                        } else {
                            LoginActivity.this.showLoggedInLayout((String) arrayList.get(i));
                        }
                    }
                });
                alertDialog.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.setCancelable(false);
                alertDialog.create().show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showLoggedInLayout(String venueName) {
        this.loginLayout.setVisibility(8);
        this.venuesListLayout.setVisibility(8);
        this.singleVenueLayout.setVisibility(0);
        this.venueNameTxt.setText(venueName);
        if (!Prefs.getInstance(getApplicationContext()).getOperationVenueName().equals(venueName) && !Prefs.getInstance(getApplicationContext()).getOperationVenueName().isEmpty()) {
            NaviBeesManager.getInstance(getApplication()).deInitialize(this);
        }
        loadMetaData(venueName);
    }

    private boolean checkUserCredentials(String username2, String password2) {
        for (Venue venue : this.operationApplication.venues) {
            if (venue.userName.equals(username2) && venue.password.equals(password2)) {
                Prefs.getInstance(getApplicationContext()).saveOperationVenue(venue.venue, venue.appId);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void showLogin() {
        this.loginLayout.setVisibility(0);
        this.venuesListLayout.setVisibility(8);
        this.singleVenueLayout.setVisibility(8);
    }

    private void loadMetaData(String venueName) {
        this.progressDialog.show();
        this.progressDialog.setMessage("Loading app metadata");
        for (Venue venue : this.operationApplication.venues) {
            if (venue.venue.equals(venueName)) {
                this.currentVenue = venue;
            }
        }
        final NaviBeesApplication naviBeesApplication = (NaviBeesApplication) getApplication();
        naviBeesApplication.syncData(this.currentVenue.appId, this.currentVenue.authenticationToken, new OnSyncListener() {
            public void buildingSyncCompleted() {
                NaviBeesApplication naviBeesApplication = naviBeesApplication;
                naviBeesApplication.syncInProgress = false;
                naviBeesApplication.initialize();
                LoginActivity.this.progressDialog.setMessage("Please wait..");
                LoginActivity.this.progressDialog.dismiss();
                Prefs.getInstance(LoginActivity.this.getApplicationContext()).saveOperationVenue(LoginActivity.this.currentVenue.venue, LoginActivity.this.currentVenue.appId);
                LoginActivity.this.goToDemoActivity("Metadata successfully loaded");
            }

            public void buildingSyncFailed(String error) {
                naviBeesApplication.syncInProgress = false;
                Toast.makeText(LoginActivity.this, error, 0).show();
            }

            public void appMetadataSyncCompleted() {
                naviBeesApplication.syncInProgress = false;
                LoginActivity.this.progressDialog.setMessage("Loading buildings metadata");
            }

            public void appMetadataSyncFailed(String error) {
                naviBeesApplication.syncInProgress = false;
                Toast.makeText(LoginActivity.this, error, 0).show();
                LoginActivity.this.progressDialog.setMessage("Please wait..");
                LoginActivity.this.progressDialog.dismiss();
                if (NaviBeesManager.getInstance(LoginActivity.this.getApplication()).getMetaDataManager().navibeesApp != null && NaviBeesManager.getInstance(LoginActivity.this.getApplication()).getMetaDataManager().navibeesApp.f1338id.equals(LoginActivity.this.currentVenue.appId)) {
                    LoginActivity.this.goToDemoActivity("Local Metadata loaded");
                }
            }

            public void buildingSyncCompleted(String buildingId) {
                naviBeesApplication.syncInProgress = false;
            }

            public void buildingSyncFailed(String buildingId, String error) {
                naviBeesApplication.syncInProgress = false;
                LoginActivity.this.progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, error, 0).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void goToDemoActivity(String title) {
        Builder alertDialog = new Builder(this);
        alertDialog.setTitle((CharSequence) title);
        alertDialog.setMessage((CharSequence) "Press OK to continue");
        alertDialog.setPositiveButton((CharSequence) "OK", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                LoginActivity.this.goToActivity();
            }
        });
        alertDialog.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.create().show();
    }

    public void goToActivity() {
        startActivity(new Intent(this, SplashActivity.class));
    }

    /* access modifiers changed from: private */
    public boolean isMetadataDownloaded(String appId) {
        if (NaviBeesManager.getInstance(getApplication()).getMetaDataManager().navibeesApp != null) {
            return NaviBeesManager.getInstance(getApplication()).getMetaDataManager().navibeesApp.f1338id.equals(appId);
        }
        return false;
    }

    private void checkDynamicLink() {
        if (getIntent().getData() != null) {
            FirebaseDynamicLinks.getInstance().getDynamicLink(getIntent()).addOnSuccessListener((Activity) this, (OnSuccessListener<? super TResult>) new OnSuccessListener<PendingDynamicLinkData>() {
                public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                    Uri deepLink = null;
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.getLink();
                    }
                    if (deepLink != null) {
                        LoginActivity.this.processReceivedDynamicLink(deepLink.toString());
                    }
                }
            }).addOnFailureListener((Activity) this, (OnFailureListener) new OnFailureListener() {
                public void onFailure(Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void processReceivedDynamicLink(String link) {
        if (link != null) {
            String deeplink = link;
            String link2 = link.split("\\?")[1];
            System.out.println(link2);
            String[] paramsArr = link2.split("&");
            Map<String, String> customParameters = new HashMap<>();
            for (String s : paramsArr) {
                String[] arr = s.split("=");
                customParameters.put(arr[0], arr[1]);
            }
            String venue = (String) customParameters.get("venue");
            if (venue != null && venue.equalsIgnoreCase(NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue().f1342id)) {
                Intent intent = new Intent(this, MapViewActivity.class);
                intent.putExtra("deeplink", deeplink);
                startActivity(intent);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getVenuesData();
    }
}
