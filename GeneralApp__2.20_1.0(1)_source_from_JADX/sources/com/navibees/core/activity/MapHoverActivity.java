package com.navibees.core.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HeaderViewListAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.core.widget.NestedScrollView.OnScrollChangeListener;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.navibees.core.NaviBeesConstants;
import com.navibees.core.NaviBeesManager;
import com.navibees.core.activity.CirclePickerView.CirclePickerListener;
import com.navibees.core.activity.CirclePickerView.OnHideFinishedListener;
import com.navibees.core.interfaces.C1643b;
import com.navibees.core.interfaces.MapHoverInterface;
import com.navibees.core.interfaces.RouteInterface;
import com.navibees.core.model.metadata.json.ApplicationConfiguration;
import com.navibees.core.model.metadata.json.Building;
import com.navibees.core.model.metadata.json.Contact;
import com.navibees.core.model.metadata.json.Facility;
import com.navibees.core.model.metadata.json.Floor;
import com.navibees.core.model.metadata.json.Image;
import com.navibees.core.model.metadata.json.IndoorLocation;
import com.navibees.core.model.metadata.json.POI;
import com.navibees.core.model.metadata.json.POICategory;
import com.navibees.core.model.metadata.json.RouteSegment;
import com.navibees.core.model.metadata.json.Text;
import com.navibees.core.model.metadata.json.Venue;
import com.navibees.core.model.tts.TTSManager;
import com.navibees.core.util.ComparatorType;
import com.navibees.core.util.Log;
import com.navibees.core.util.NaviBeesUtils;
import com.navibees.sdk.C1266R;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class MapHoverActivity extends NaviBeesActivity implements MapHoverInterface, CirclePickerListener, C1643b, OnCompletionListener {
    public static boolean enableRoutingWhenOutOfVenue = false;
    public static boolean isClickedOnSearchStartPoint = false;
    public static Context mContext;
    public static Object selectedOrigin;

    /* renamed from: A */
    private TextView f976A;

    /* renamed from: B */
    private TextView f977B;

    /* renamed from: C */
    private TextView f978C;

    /* renamed from: D */
    private TextView f979D;

    /* renamed from: E */
    private TextView f980E;

    /* renamed from: F */
    private ListView f981F;

    /* renamed from: G */
    private View f982G;
    /* access modifiers changed from: private */

    /* renamed from: H */
    public View f983H;
    /* access modifiers changed from: private */

    /* renamed from: I */
    public TextView f984I;
    /* access modifiers changed from: private */

    /* renamed from: J */
    public TextView f985J;
    /* access modifiers changed from: private */

    /* renamed from: K */
    public View f986K;

    /* renamed from: L */
    private RouteInterface f987L;

    /* renamed from: M */
    private TTSManager f988M;
    /* access modifiers changed from: private */

    /* renamed from: N */
    public NumberFormat f989N = new DecimalFormat("#0.000000");

    /* renamed from: O */
    private float f990O;
    /* access modifiers changed from: private */

    /* renamed from: P */
    public float f991P;

    /* renamed from: Q */
    private BottomSheetCallback f992Q = new BottomSheetCallback() {
        public void onSlide(@NonNull View view, float f) {
            if (MapHoverActivity.this.f1022v.getState() == 1 && view.getHeight() == ((int) MapHoverActivity.this.f1018r)) {
                MapHoverActivity.this.f1003c.setExpanded(false);
            }
        }

        public void onStateChanged(@NonNull View view, int i) {
            if (i == 5) {
                float y = MapHoverActivity.this.f1020t.getY();
                if (MapHoverActivity.this.f1024x) {
                    y = MapHoverActivity.this.f1020t.getY() + MapHoverActivity.this.f991P;
                }
                MapHoverActivity.this.f1024x = false;
                float[] fArr = {y};
                String str = "y";
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(MapHoverActivity.this.f1020t, str, fArr);
                ImageView imageView = MapHoverActivity.this.startNavigationIV;
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, str, new float[]{imageView.getY() + MapHoverActivity.this.f991P});
                ImageView imageView2 = MapHoverActivity.this.clearRouteIV;
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageView2, str, new float[]{imageView2.getY() + MapHoverActivity.this.f991P});
                final float y2 = MapHoverActivity.this.startNavigationIV.getY();
                AnimatorSet animatorSet = new AnimatorSet();
                if (MapHoverActivity.this.startNavigationIV.getVisibility() == 0) {
                    animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
                } else if (MapHoverActivity.this.clearRouteIV.getVisibility() == 0) {
                    animatorSet.playTogether(new Animator[]{ofFloat, ofFloat3});
                }
                animatorSet.setDuration(300);
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        if (MapHoverActivity.this.startNavigationIV.getVisibility() == 0) {
                            MapHoverActivity.this.startNavigationIV.setVisibility(4);
                            MapHoverActivity.this.startNavigationIV.setY(y2);
                        } else if (MapHoverActivity.this.clearRouteIV.getVisibility() == 0) {
                            MapHoverActivity.this.clearRouteIV.setVisibility(4);
                            MapHoverActivity.this.clearRouteIV.setY(y2);
                        }
                        super.onAnimationEnd(animator);
                    }
                });
                animatorSet.start();
                MapHoverActivity.this.f983H.setVisibility(4);
                MapHoverActivity mapHoverActivity = MapHoverActivity.this;
                mapHoverActivity.currentlySelectedPOI = null;
                mapHoverActivity.m692c();
                MapHoverActivity.this.f1003c.setExpanded(true);
                MapHoverActivity.this.showNavigationInstructionsBar(false);
                MapHoverActivity.this.m693c(true);
                MapHoverActivity.this.onClearSearchViewClick();
                MapHoverActivity.this.m721o();
            } else if (i == 3) {
                view.getLayoutParams().height = (int) MapHoverActivity.this.f1018r;
                MapHoverActivity.this.f1023w.getLayoutParams().height = (int) MapHoverActivity.this.f1018r;
            } else if (i == 4 && MapHoverActivity.this.clearRouteIV.getVisibility() != 0) {
                MapHoverActivity.this.f1003c.setExpanded(true);
            }
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: R */
    public boolean f993R = true;
    /* access modifiers changed from: private */

    /* renamed from: S */
    public boolean f994S = false;

    /* renamed from: T */
    private String[] f995T = {"Go straight", "Turn gentle right", "Turn gentle left", "Turn right", "Turn left", "Turn sharp right", "Turn sharp left", "Make right U-turn", "Make left U-turn", "Start", "You have arrived", "Go up 1", "Go up 2", "Go up 3", "Go up 4", "Go up 5", "Go up G", "Go down 1", "Go down 2", "Go down 3", "Go down 4", "Go down 5", "Go down G", "Use transportation mode", "Change Buildings", "Stop", "go straight", "turn gentle right", "turn gentle left", "turn right", "turn left", "turn sharp right", "turn sharp left", "make right U-turn", "make left U-turn", "start", "you have arrived", "go up 1", "go up 2", "go up 3", "go up 4", "go up 5", "go up G", "go down 1", "go down 2", "go down 3", "go down 4", "go down 5", "go down G", "use transportation mode", "change Buildings", "stop", "a few seconds", "about a minute", "about 2 minutes", "about 3 minutes", "about 4 minutes", "about 5 minutes", "for", "then", "and", "near", "using", "the stairway", "the escalator", "the lift"};

    /* renamed from: U */
    private MediaPlayer f996U;

    /* renamed from: V */
    private int[] f997V;

    /* renamed from: W */
    private int f998W = 0;

    /* renamed from: X */
    private int f999X = 0;

    /* renamed from: Y */
    private int f1000Y = 0;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public DrawerLayout f1001a;

    /* renamed from: b */
    private NavigationView f1002b;
    public Button btnCont;
    public Button btnSteps;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public AppBarLayout f1003c;
    protected ImageView clearRouteIV;
    public View clearSearchView;
    public View clearStartSearchView;
    protected ImageView compassImage;
    protected Facility currentlySelectedFacility;
    protected POI currentlySelectedPOI;

    /* renamed from: d */
    private Toolbar f1004d;
    public IndoorLocation destinationLocation;
    protected String destinationLocationName;

    /* renamed from: e */
    private boolean f1005e = true;

    /* renamed from: f */
    private double f1006f = 5.0d;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Button f1007g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public CirclePickerView f1008h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public Button f1009i;
    public boolean isCompassActive = true;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public CirclePickerView f1010j;

    /* renamed from: k */
    private FrameLayout f1011k;

    /* renamed from: l */
    private TextView f1012l;

    /* renamed from: m */
    private ImageView f1013m;
    protected LocalBroadcastManager mLocalBroadcastManager;
    protected ImageView myLocationIV;

    /* renamed from: n */
    private Object f1014n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public boolean f1015o = false;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public int f1016p = 0;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public float f1017q;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public float f1018r;

    /* renamed from: s */
    private ImageView f1019s;
    protected ImageView savedLocationButton;
    public EditText searchEditText;
    public EditText searchStartEditText;
    protected boolean showBottomSheet = true;
    public boolean showSavedLocationButton = true;
    /* access modifiers changed from: protected */
    public ImageView startNavigationIV;
    /* access modifiers changed from: private */

    /* renamed from: t */
    public View f1020t;
    /* access modifiers changed from: private */

    /* renamed from: u */
    public View f1021u;
    /* access modifiers changed from: private */

    /* renamed from: v */
    public BottomSheetBehavior f1022v;
    /* access modifiers changed from: private */

    /* renamed from: w */
    public NestedScrollView f1023w;
    /* access modifiers changed from: private */

    /* renamed from: x */
    public boolean f1024x = false;

    /* renamed from: y */
    private TextView f1025y;
    /* access modifiers changed from: private */

    /* renamed from: z */
    public TextView f1026z;

    public MapHoverActivity() {
    }

    /* renamed from: a */
    private void m666a(MenuItem menuItem) {
    }

    /* renamed from: i */
    private int m709i() {
        return -1;
    }

    /* renamed from: l */
    private boolean m716l() {
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: m */
    public boolean m718m() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void addHeaderRunTime(NavigationView navigationView) {
        navigationView.getHeaderView(0).setBackgroundResource(m712j());
    }

    /* access modifiers changed from: protected */
    public abstract void clearDisplayedRoute();

    /* access modifiers changed from: protected */
    public abstract void clearDisplayedRoutingInstructions();

    /* access modifiers changed from: protected */
    public void closeNavDrawer() {
        DrawerLayout drawerLayout = this.f1001a;
        if (drawerLayout != null) {
            drawerLayout.closeDrawer((int) GravityCompat.START);
        }
    }

    public void collapsedBottomSheet() {
        if (this.f1022v.getState() == 3) {
            this.f1022v.setState(4);
        }
    }

    public void compassTrackingChanged(final boolean z) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (z) {
                    MapHoverActivity.this.m688b(true);
                } else {
                    MapHoverActivity.this.m688b(false);
                }
            }
        });
    }

    public void didSelectCell(String str, CirclePickerView circlePickerView) {
        if (this.f1008h == circlePickerView) {
            Building currentBuilding = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentBuilding();
            if (currentBuilding != null) {
                onFloorSelected((Floor) currentBuilding.floorsDictionary.get(str));
                m703f();
            }
        } else if (this.f1010j == circlePickerView) {
            Building building = (Building) NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getBuildings().get(str);
            m701e();
            m703f();
            if (building != null) {
                presentedBuildingChanged(building);
                onBuildingSelected(building);
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean getIsMapLoaded();

    /* access modifiers changed from: protected */
    public View getPOIDescriptionView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public Class<? extends RouteToActivity> getRouteToActivity() {
        return RouteToActivity.class;
    }

    /* access modifiers changed from: protected */
    public ComparatorType getRouteToCategorySortType() {
        return ComparatorType.BY_ID;
    }

    /* access modifiers changed from: protected */
    public ComparatorType getRouteToFacilitySortType() {
        return ComparatorType.BY_NAME;
    }

    /* access modifiers changed from: protected */
    public ComparatorType getRouteToPOISortType() {
        return ComparatorType.BY_NAME;
    }

    /* access modifiers changed from: protected */
    public Toolbar getToolbar() {
        return this.f1004d;
    }

    /* access modifiers changed from: protected */
    public void gotoActivitiesActivity() {
        startActivity(new Intent(this, ActivitiesActivity.class));
    }

    public void gotoRouteToActivity(String str, String str2) {
        m676a(str, str2);
    }

    /* access modifiers changed from: protected */
    public void hideNavigateButton(boolean z) {
        this.f993R = z;
    }

    /* access modifiers changed from: protected */
    public void initiateRouting(POI poi, MapHoverInterface mapHoverInterface) {
        this.currentlySelectedFacility = null;
    }

    /* access modifiers changed from: protected */
    public boolean isNavDrawerOpen() {
        DrawerLayout drawerLayout = this.f1001a;
        return drawerLayout != null && drawerLayout.isDrawerOpen((int) GravityCompat.START);
    }

    public boolean isNavigationInstructionsBarVisible() {
        return this.f1011k.getVisibility() == 0;
    }

    public void locationTrackingChanged(final boolean z) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (z) {
                    MapHoverActivity.this.myLocationIV.setImageResource(C1266R.C1268drawable.com_navibees_sdk_current_location_on);
                } else {
                    MapHoverActivity.this.myLocationIV.setImageResource(C1266R.C1268drawable.com_navibees_sdk_current_location_off);
                }
            }
        });
    }

    public void notifyMapIsLoaded() {
        NaviBeesConstants.CAMERA_MOVING = true;
        m714k();
        if (this.f1014n == null) {
            Intent intent = getIntent();
            String str = NaviBeesConstants.ROUTE_TO_POI_KEY;
            if (intent.getParcelableExtra(str) != null) {
                this.f1014n = getIntent().getParcelableExtra(str);
            } else {
                Intent intent2 = getIntent();
                String str2 = NaviBeesConstants.ROUTE_TO_FACILITY_KEY;
                if (intent2.getParcelableExtra(str2) != null) {
                    this.f1014n = getIntent().getParcelableExtra(str2);
                } else {
                    Intent intent3 = getIntent();
                    String str3 = NaviBeesConstants.ROUTE_TO_LOCATION_KEY;
                    if (intent3.getParcelableExtra(str3) != null) {
                        this.f1014n = getIntent().getParcelableExtra(str3);
                        this.destinationLocationName = getIntent().getStringExtra(NaviBeesConstants.ROUTE_TO_LOCATION_NAME_KEY);
                    }
                }
            }
        }
        if (this.f1014n == null) {
            ApplicationConfiguration configuration = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getConfiguration();
            if (configuration != null && configuration.trackingEnabledByDefault) {
                final Venue currentVenue = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue();
                if (!(currentVenue == null || currentVenue.initialLocation == null)) {
                    new Thread(new Runnable() {
                        public void run() {
                            MapHoverActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    try {
                                        if (NaviBeesConstants.CAMERA_MOVING) {
                                            MapHoverActivity.this.zoomToUTMLocation(currentVenue.initialLocation);
                                        } else {
                                            MapHoverActivity.this.onMyLocationClick(false);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    new Handler().postDelayed(new Runnable() {
                                        public void run() {
                                            NaviBeesConstants.CAMERA_MOVING = false;
                                        }
                                    }, 1000);
                                }
                            });
                        }
                    }).start();
                }
            }
        }
        m706g();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        POI poi;
        m714k();
        if (i != 0) {
            if (i != 2) {
                if (i != 5) {
                    if (i == 6 && i2 == 0) {
                        Toast.makeText(getApplicationContext(), getResources().getString(C1266R.string.user_refuse_enable_location_services), 0).show();
                    }
                    super.onActivityResult(i, i2, intent);
                } else if (i2 == 0) {
                    NaviBeesUtils.disableTTS(getApplicationContext());
                    TTSManager tTSManager = this.f988M;
                    if (tTSManager != null) {
                        tTSManager.shutDown();
                        this.f988M = null;
                    }
                    m724p();
                }
            } else if (i2 == -1) {
                String stringExtra = intent.getStringExtra(NaviBeesConstants.SELECTED_ACTIVITY_POI_ID);
                Building building = (Building) NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getBuildings().get(intent.getStringExtra(NaviBeesConstants.SELECTED_ATIVITY_BUILDING_ID));
                if (building != null) {
                    Iterator it = building.pois.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            poi = null;
                            break;
                        }
                        poi = (POI) it.next();
                        if (poi.f1339id.equals(stringExtra)) {
                            break;
                        }
                    }
                    if (poi != null) {
                        this.f1014n = poi;
                    }
                }
            }
        } else if (i2 == -1) {
            String stringExtra2 = intent.getStringExtra(NaviBeesConstants.SELECTED_PLACE_BUILDING_KEY);
            String stringExtra3 = intent.getStringExtra(NaviBeesConstants.SELECTED_PLACE_ID_KEY);
            int intExtra = intent.getIntExtra(NaviBeesConstants.SELECTED_PLACE_TYPE_KEY, -1);
            if (!(stringExtra2 == null || stringExtra3 == null || intExtra == -1)) {
                Building building2 = (Building) NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getBuildings().get(stringExtra2);
                if (intExtra == 1) {
                    POI poi2 = (POI) building2.poisDictionary.get(stringExtra3);
                    if (poi2 != null) {
                        if (isClickedOnSearchStartPoint) {
                            zoomToPOI(poi2);
                            selectedOrigin = poi2;
                            if (poi2.name != null) {
                                this.searchStartEditText.setText(poi2.getDescProperty());
                            }
                            this.clearStartSearchView.setVisibility(0);
                        } else {
                            this.f1014n = poi2;
                            onPOISelected(poi2);
                        }
                    }
                } else if (intExtra == 2) {
                    Facility facility = (Facility) building2.facilitiesDictionary.get(stringExtra3);
                    if (facility != null) {
                        if (isClickedOnSearchStartPoint) {
                            selectedOrigin = facility;
                            this.clearStartSearchView.setVisibility(0);
                            this.searchStartEditText.setText(facility.name.getText());
                            this.clearStartSearchView.setVisibility(0);
                        } else {
                            this.f1014n = facility;
                        }
                    }
                }
            }
        }
    }

    public void onBackPressed() {
        if (!m718m() || !isNavDrawerOpen()) {
            View view = this.clearSearchView;
            if (view == null) {
                super.onBackPressed();
                return;
            }
            if (view.getVisibility() == 0) {
                this.clearSearchView.callOnClick();
            }
            super.onBackPressed();
        } else {
            closeNavDrawer();
        }
    }

    /* access modifiers changed from: protected */
    public abstract void onBuildingButtonClick();

    /* access modifiers changed from: protected */
    public abstract void onBuildingSelected(Building building);

    /* access modifiers changed from: protected */
    public abstract void onClearRouteClick();

    /* access modifiers changed from: protected */
    public abstract void onClearSearchViewClick();

    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.reset();
        this.f998W++;
        startPlayingTracks();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1266R.layout.activity_map_hover);
        this.f1002b = (NavigationView) findViewById(C1266R.C1269id.navigation_view);
        this.f1001a = (DrawerLayout) findViewById(C1266R.C1269id.drawer_layout);
        this.btnCont = (Button) findViewById(C1266R.C1269id.btnCont);
        this.btnSteps = (Button) findViewById(C1266R.C1269id.btnSteps);
        if (m718m()) {
            m684b(this.f1002b);
        } else {
            this.f1001a.setDrawerLockMode(1);
        }
        this.compassImage = (ImageView) findViewById(C1266R.C1269id.compass_Image);
        this.compassImage.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MapHoverActivity mapHoverActivity = MapHoverActivity.this;
                if (mapHoverActivity.isCompassActive) {
                    mapHoverActivity.m688b(false);
                } else {
                    mapHoverActivity.m688b(true);
                }
                MapHoverActivity mapHoverActivity2 = MapHoverActivity.this;
                mapHoverActivity2.setIsCompassActive(mapHoverActivity2.isCompassActive);
            }
        });
        this.myLocationIV = (ImageView) findViewById(C1266R.C1269id.my_location_fab);
        this.myLocationIV.setEnabled(false);
        this.savedLocationButton = (ImageView) findViewById(C1266R.C1269id.saved_location_button);
        this.savedLocationButton.setEnabled(false);
        this.f1007g = (Button) findViewById(C1266R.C1269id.floor_action_button);
        this.f1007g.setEnabled(false);
        String str = "?";
        m667a(this.f1007g, str, getResources().getString(C1266R.string.floor_button_label));
        this.f1009i = (Button) findViewById(C1266R.C1269id.building_action_button);
        this.f1009i.setEnabled(false);
        m667a(this.f1009i, str, getResources().getString(C1266R.string.building_button_label));
        this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        m682b();
        m714k();
        if (NaviBeesManager.getInstance(getApplication()).isInitialized()) {
            try {
                m728r();
            } catch (Exception e) {
                e.printStackTrace();
            }
            m734u();
            m732t();
            m730s();
            this.f988M = NaviBeesManager.getInstance(getApplication()).getTTSManager(this);
        }
    }

    public void onDone(String str) {
    }

    public void onError(String str) {
    }

    /* access modifiers changed from: protected */
    public abstract void onFloorButtonClick();

    /* access modifiers changed from: protected */
    public abstract void onFloorSelected(Floor floor);

    /* access modifiers changed from: protected */
    public abstract void onMyLocationClick(boolean z);

    public void onNavigationInstructionComputed(double d, double d2, ArrayList<RouteSegment> arrayList) {
        RouteInterface routeInterface = this.f987L;
        if (routeInterface != null) {
            routeInterface.onRouteComputed(d, d2, arrayList);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (m718m()) {
            this.f1001a.openDrawer((int) GravityCompat.START);
        } else if (m716l()) {
            onBackPressed();
        }
        return true;
    }

    public void onPOISelected(POI poi) {
        if (this.clearRouteIV.getVisibility() != 0) {
            this.currentlySelectedPOI = poi;
            runOnUiThread(new Runnable() {
                public void run() {
                    MapHoverActivity.this.f983H.setVisibility(4);
                }
            });
            initiateRouting(poi, (MapHoverInterface) this);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onPlaceSelected(POI poi, IndoorLocation indoorLocation) {
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onResume() {
        /*
            r3 = this;
            super.onResume()
            boolean r0 = r3.getIsMapLoaded()
            if (r0 == 0) goto L_0x000c
            r3.m706g()
        L_0x000c:
            android.content.Context r0 = r3.getApplicationContext()
            mContext = r0
            com.navibees.core.model.tts.TTSManager r0 = r3.f988M
            if (r0 != 0) goto L_0x0032
            android.app.Application r0 = r3.getApplication()
            com.navibees.core.NaviBeesManager r0 = com.navibees.core.NaviBeesManager.getInstance(r0)
            boolean r0 = r0.isInitialized()
            if (r0 == 0) goto L_0x0032
            android.app.Application r0 = r3.getApplication()
            com.navibees.core.NaviBeesManager r0 = com.navibees.core.NaviBeesManager.getInstance(r0)
            com.navibees.core.model.tts.TTSManager r0 = r0.getTTSManager(r3)
            r3.f988M = r0
        L_0x0032:
            boolean r0 = r3.showSavedLocationButton
            if (r0 != 0) goto L_0x003d
            android.widget.ImageView r0 = r3.savedLocationButton
            r1 = 8
            r0.setVisibility(r1)
        L_0x003d:
            android.app.Application r0 = r3.getApplication()
            com.navibees.core.NaviBeesManager r0 = com.navibees.core.NaviBeesManager.getInstance(r0)
            com.navibees.core.model.metadata.MetaDataManager r0 = r0.getMetaDataManager()
            java.util.HashMap r0 = r0.getVenueProperties()
            if (r0 == 0) goto L_0x007f
            java.lang.String r1 = "Enable_Green_Marker_Navigation"
            java.lang.Object r2 = r0.get(r1)
            if (r2 == 0) goto L_0x0065
            java.lang.Object r1 = r0.get(r1)
            java.lang.String r2 = "0"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0065
            r1 = 0
            goto L_0x0066
        L_0x0065:
            r1 = 1
        L_0x0066:
            r3.f1005e = r1
            java.lang.String r1 = "Green_Marker_Path_Deviation_Limit"
            java.lang.Object r2 = r0.get(r1)
            if (r2 == 0) goto L_0x007b
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            double r0 = java.lang.Double.parseDouble(r0)
            goto L_0x007d
        L_0x007b:
            r0 = 4617315517961601024(0x4014000000000000, double:5.0)
        L_0x007d:
            r3.f1006f = r0
        L_0x007f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navibees.core.activity.MapHoverActivity.onResume():void");
    }

    public void onRouteFindCallback(POI poi, double d, double d2, Floor floor, boolean z) {
        if (d != -1.0d) {
            this.currentlySelectedPOI = poi;
            this.f1016p = (int) Math.ceil(d);
            m678a(true);
            m665a(d, (int) d2, floor.name.getText(), z);
            if (this.f1015o) {
                this.f1015o = false;
                runOnUiThread(new Runnable() {
                    public void run() {
                        MapHoverActivity.this.onStartNavigationClick();
                    }
                });
            }
        } else {
            m678a(false);
            if (!(poi == null || floor == null)) {
                this.currentlySelectedPOI = poi;
                m665a(d, (int) d2, floor.name.getText(), z);
            }
        }
        m714k();
    }

    public void onRouteFindCallbackForPosition(double d, double d2, boolean z) {
        this.currentlySelectedPOI = null;
        if (!(d == -1.0d || this.destinationLocation == null)) {
            this.f1016p = (int) Math.ceil(d);
            if (d == 0.0d) {
                m678a(false);
            } else {
                m678a(true);
            }
            SimpleArrayMap buildings = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getBuildings();
            m665a(d, (int) d2, (buildings == null || buildings.get(this.destinationLocation.buildingId) == null) ? "" : ((Floor) ((Building) buildings.get(this.destinationLocation.buildingId)).floors.get(this.destinationLocation.floor)).name.getText(), z);
        }
        m714k();
    }

    public void onSaveLocationClicked(IndoorLocation indoorLocation) {
        Log.m1173i("saveLoc:save", indoorLocation.toJson().toString());
        NaviBeesManager.getInstance(getApplication()).saveLocation(this, indoorLocation);
        if (this.showSavedLocationButton) {
            this.savedLocationButton.setVisibility(0);
        }
    }

    public void onStart(String str) {
    }

    /* access modifiers changed from: protected */
    public void onStartNavigationClick() {
        if (m718m()) {
            this.f1001a.setDrawerLockMode(1);
        }
        this.f1003c.setExpanded(false);
        this.startNavigationIV.setVisibility(4);
        this.clearRouteIV.setVisibility(0);
        this.f983H.setVisibility(0);
        collapsedBottomSheet();
        showNavigationInstructionsBar(true);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        TTSManager tTSManager = this.f988M;
        if (tTSManager != null) {
            tTSManager.shutDown();
            this.f988M = null;
        }
        m724p();
    }

    public void presentedBuildingChanged(Building building) {
        Building currentBuilding = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentBuilding();
        NaviBeesManager.getInstance(getApplication()).getMetaDataManager().setCurrentBuilding(building.f1329id);
        Text text = building.name;
        m667a(this.f1009i, text != null ? text.getText() : "B", getResources().getString(C1266R.string.building_button_label));
        this.f1010j.mo28767a(building.f1329id);
        if (currentBuilding == null || !currentBuilding.f1329id.equals(building.f1329id)) {
            m703f();
            m736v();
        }
    }

    public void presentedFloorChanged(final Floor floor) {
        runOnUiThread(new Runnable() {
            public void run() {
                MapHoverActivity mapHoverActivity = MapHoverActivity.this;
                mapHoverActivity.m667a(mapHoverActivity.f1007g, floor.name.getText(), MapHoverActivity.this.getResources().getString(C1266R.string.floor_button_label));
                MapHoverActivity.this.f1008h.mo28767a(floor.f1331id);
            }
        });
    }

    public void removeRouteListener() {
        this.f987L = null;
    }

    /* access modifiers changed from: protected */
    public abstract void setIsCompassActive(boolean z);

    public void setRouteListener(RouteInterface routeInterface) {
        this.f987L = routeInterface;
    }

    /* access modifiers changed from: protected */
    public abstract void showDestinationMarker(IndoorLocation indoorLocation);

    /* access modifiers changed from: protected */
    public abstract void showDestinationMarker(POI poi);

    public void showNavigationInstructionsBar(boolean z) {
        if (!z) {
            this.f1011k.setVisibility(4);
        } else if (!enableRoutingWhenOutOfVenue) {
            this.f1011k.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void showRealLocation(boolean z);

    public void showStartPointSearchBar(boolean z) {
        if (z) {
            collapsedBottomSheet();
            isClickedOnSearchStartPoint = true;
            findViewById(C1266R.C1269id.search_start_container).setVisibility(0);
            findViewById(C1266R.C1269id.start_point_bottom_line).setVisibility(0);
            return;
        }
        isClickedOnSearchStartPoint = false;
        findViewById(C1266R.C1269id.search_start_container).setVisibility(8);
        findViewById(C1266R.C1269id.start_point_bottom_line).setVisibility(8);
    }

    public void startPlayingTracks() {
        if (this.f996U != null) {
            int[] iArr = this.f997V;
            if (iArr.length > 0 && this.f998W < iArr.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("android.resource://");
                sb.append(getPackageName());
                sb.append("/");
                sb.append(this.f997V[this.f998W]);
                try {
                    this.f996U.setDataSource(this, Uri.parse(sb.toString()));
                    this.f996U.prepare();
                    this.f996U.start();
                    this.f996U.setOnCompletionListener(this);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
        this.f996U.release();
        this.f996U = null;
    }

    /* access modifiers changed from: protected */
    public void stopAutomaticMode() {
        trackingLocationAndCompassChanged(false);
    }

    public void trackingLocationAndCompassChanged(final boolean z) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (z) {
                    MapHoverActivity.this.myLocationIV.setImageResource(C1266R.C1268drawable.com_navibees_sdk_current_location_on);
                    MapHoverActivity.this.m688b(true);
                    return;
                }
                MapHoverActivity.this.myLocationIV.setImageResource(C1266R.C1268drawable.com_navibees_sdk_current_location_off);
                MapHoverActivity.this.m688b(false);
            }
        });
    }

    public void updateBuildingPickerState(boolean z) {
        if (!z) {
            m701e();
        }
    }

    public void updateCompass(float f) {
        ImageView imageView = this.compassImage;
        if (imageView != null) {
            imageView.setScaleType(ScaleType.MATRIX);
            float f2 = -f;
            while (f2 < 0.0f) {
                f2 += 360.0f;
            }
            while (f2 > 360.0f) {
                f2 -= 360.0f;
            }
            Matrix matrix = new Matrix();
            matrix.postRotate(f2, ((float) this.compassImage.getDrawable().getIntrinsicWidth()) * 0.5f, ((float) this.compassImage.getDrawable().getIntrinsicHeight()) * 0.5f);
            this.compassImage.setImageMatrix(matrix);
        }
        if (((double) f) < 0.0d) {
            f += 360.0f;
        }
        this.f987L.onHeadingUpdate((double) f);
    }

    public void updateDistanceToRoute(double d) {
        int routeRecalculationDistance = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getRouteRecalculationDistance();
        if (d <= this.f1006f) {
            showRealLocation(false);
        } else if (!this.f1005e || this.f1000Y < 2) {
            this.f1000Y++;
        } else {
            this.f1000Y = 0;
            showRealLocation(true);
        }
        if (d > ((double) routeRecalculationDistance)) {
            this.f999X++;
        } else {
            this.f999X = 0;
        }
        if (this.f999X >= 3) {
            this.f999X = 0;
            runOnUiThread(new Runnable() {
                public void run() {
                    if (!MapHoverActivity.enableRoutingWhenOutOfVenue) {
                        MapHoverActivity.this.clearRouteIV.callOnClick();
                    }
                    MapHoverActivity mapHoverActivity = MapHoverActivity.this;
                    if (mapHoverActivity.currentlySelectedFacility != null) {
                        mapHoverActivity.f1015o = true;
                        MapHoverActivity.this.onClearSearchViewClick();
                        MapHoverActivity mapHoverActivity2 = MapHoverActivity.this;
                        mapHoverActivity2.initiateRouting((Object) mapHoverActivity2.currentlySelectedFacility, (MapHoverInterface) mapHoverActivity2);
                    }
                }
            });
        }
    }

    public void updateFloorPickerState(boolean z) {
        if (!z) {
            m703f();
        }
    }

    public void updateNavigationMessage(String str, String str2) {
        RouteInterface routeInterface = this.f987L;
        if (routeInterface != null) {
            routeInterface.onNavigationInstructionComputed(str, str2);
        }
        int d = m695d(str2);
        if (d != -1) {
            this.f1013m.setImageResource(d);
        }
        if (str != null) {
            showNavigationInstructionsBar(true);
            m693c(false);
            this.f1012l.setText(str);
            TTSManager tTSManager = this.f988M;
            if (tTSManager != null) {
                tTSManager.setTtsListener(this);
            }
            this.f988M.speak(str);
        }
    }

    public void updateNavigationProgress(double d, double d2) {
        RouteInterface routeInterface = this.f987L;
        if (routeInterface != null) {
            routeInterface.onRouteProgress(d, d2);
        }
        final double d3 = d2;
        final double d4 = d;
        C157419 r1 = new Runnable() {
            public void run() {
                if (MapHoverActivity.this.f983H.getVisibility() == 0) {
                    MapHoverActivity mapHoverActivity = MapHoverActivity.this;
                    if ((mapHoverActivity.currentlySelectedPOI != null || mapHoverActivity.destinationLocation != null) && ((int) d3) != -1) {
                        MapHoverActivity.this.f984I.setText(MapHoverActivity.this.m679b((int) d4));
                        TextView u = MapHoverActivity.this.f985J;
                        StringBuilder sb = new StringBuilder();
                        sb.append(MapHoverActivity.this.m661a((int) Math.ceil(d3)));
                        sb.append(" ~ ");
                        sb.append(MapHoverActivity.this.f1026z.getText().toString());
                        u.setText(sb.toString());
                        boolean z = false;
                        double v = ((double) (MapHoverActivity.this.f1016p - ((int) Math.ceil(d3)) < 0 ? 0 : MapHoverActivity.this.f1016p - ((int) Math.ceil(d3)))) / ((double) MapHoverActivity.this.f1016p);
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        MapHoverActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                        MapHoverActivity.this.f986K.getLayoutParams().width = (int) (v * ((double) displayMetrics.widthPixels));
                        MapHoverActivity.this.f986K.requestLayout();
                        if (((double) NaviBeesManager.getInstance(MapHoverActivity.this.getApplication()).getMetaDataManager().getEndAlertDistance()) >= d3) {
                            z = true;
                        }
                        if (z && (!MapHoverActivity.this.f994S)) {
                            MapHoverActivity mapHoverActivity2 = MapHoverActivity.this;
                            mapHoverActivity2.m687b(mapHoverActivity2.getString(C1266R.string.dialog_title), MapHoverActivity.this.getString(C1266R.string.dialog_arrived_message));
                            MapHoverActivity.this.f994S = true;
                        }
                    }
                }
            }
        };
        runOnUiThread(r1);
    }

    /* access modifiers changed from: protected */
    public abstract void zoomToLongLatLocation(IndoorLocation indoorLocation);

    /* access modifiers changed from: protected */
    public abstract void zoomToPOI(POI poi);

    /* access modifiers changed from: protected */
    public abstract void zoomToUTMLocation(IndoorLocation indoorLocation);

    /* renamed from: f */
    private void m703f() {
        CirclePickerView circlePickerView = this.f1008h;
        if (!circlePickerView.f967f) {
            circlePickerView.mo28766a((OnHideFinishedListener) new OnHideFinishedListener() {
                /* renamed from: a */
                public void mo28779a() {
                    MapHoverActivity.this.f1007g.setVisibility(0);
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) MapHoverActivity.this.savedLocationButton.getLayoutParams();
                    marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin - 200);
                    MapHoverActivity mapHoverActivity = MapHoverActivity.this;
                    if (mapHoverActivity.showSavedLocationButton) {
                        mapHoverActivity.savedLocationButton.setLayoutParams(marginLayoutParams);
                    }
                }
            });
        }
    }

    /* renamed from: g */
    private boolean m706g() {
        Object obj = this.f1014n;
        boolean z = false;
        if (obj != null) {
            if (obj instanceof POI) {
                stopAutomaticMode();
                zoomToPOI((POI) this.f1014n);
                initiateRouting(this.f1014n, (MapHoverInterface) this);
                z = true;
            } else if (obj instanceof Facility) {
                initiateRouting(obj, (MapHoverInterface) this);
                z = true;
            } else if (obj instanceof IndoorLocation) {
                stopAutomaticMode();
                zoomToLongLatLocation((IndoorLocation) this.f1014n);
                initiateRouting((IndoorLocation) this.f1014n, this.destinationLocationName, this);
                z = true;
            }
            this.f1014n = null;
        }
        return z;
    }

    /* renamed from: h */
    private float m707h() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float f = displayMetrics.density;
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return f;
    }

    /* renamed from: j */
    private int m712j() {
        return C1266R.C1268drawable.drawer_header_background_navibees;
    }

    /* renamed from: k */
    private void m714k() {
        runOnUiThread(new Runnable() {
            public void run() {
                View currentFocus = MapHoverActivity.this.getCurrentFocus();
                if (currentFocus != null) {
                    ((InputMethodManager) MapHoverActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: n */
    public void m720n() {
        if (!this.f1024x) {
            this.f1024x = true;
            this.f991P = (float) (((int) this.f1017q) + this.startNavigationIV.getHeight());
            this.f990O = this.f1020t.getTranslationY();
            this.f1020t.setTranslationY(this.f990O - this.f991P);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: o */
    public void m721o() {
        this.f1021u.getLayoutParams().height = (int) this.f1017q;
        this.f1023w.getLayoutParams().height = (int) this.f1017q;
    }

    /* access modifiers changed from: private */
    /* renamed from: p */
    public void m724p() {
        MediaPlayer mediaPlayer = this.f996U;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.f996U.release();
            this.f996U = null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: q */
    public void m726q() {
        TTSManager tTSManager = this.f988M;
        if (tTSManager != null) {
            tTSManager.resetTTS();
        }
    }

    /* renamed from: r */
    private void m728r() {
        int i;
        String str;
        this.myLocationIV.setEnabled(true);
        ApplicationConfiguration configuration = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getConfiguration();
        if (configuration != null) {
            if (configuration.trackingEnabledByDefault) {
                trackingLocationAndCompassChanged(true);
            } else {
                trackingLocationAndCompassChanged(false);
            }
        }
        this.myLocationIV.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MapHoverActivity.this.mo28850a();
                MapHoverActivity.this.onMyLocationClick(true);
            }
        });
        this.savedLocationButton.setEnabled(true);
        m693c(true);
        this.savedLocationButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                IndoorLocation savedLocation = NaviBeesManager.getInstance(MapHoverActivity.this.getApplication()).getSavedLocation(MapHoverActivity.this.getApplicationContext());
                MapHoverActivity.this.zoomToLongLatLocation(savedLocation);
                MapHoverActivity mapHoverActivity = MapHoverActivity.this;
                mapHoverActivity.initiateRouting(savedLocation, mapHoverActivity.getString(C1266R.string.saved_location_name), MapHoverActivity.this);
            }
        });
        SimpleArrayMap buildings = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getBuildings();
        Venue currentVenue = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue();
        this.f1020t = findViewById(C1266R.C1269id.action_buttons_container);
        this.f1020t.setTranslationY(0.0f);
        if (currentVenue == null || currentVenue.multiBuildingMode || buildings.size() != 1) {
            i = 0;
        } else {
            i = Math.round(TypedValue.applyDimension(1, 72.0f, getResources().getDisplayMetrics())) + 0;
            this.f1009i.setVisibility(4);
            List<Floor> list = ((Building) buildings.get(buildings.keyAt(0))).floors;
            if (list != null && list.size() == 1) {
                i += Math.round(TypedValue.applyDimension(1, 72.0f, getResources().getDisplayMetrics()));
                this.f1007g.setVisibility(4);
            }
        }
        this.f1020t.setTranslationY((float) i);
        this.f1010j = new CirclePickerView(this, (HorizontalScrollView) this.f1020t.findViewById(C1266R.C1269id.buildings_scroll_view), this);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i2 = 0;
        while (true) {
            str = "B";
            if (i2 >= buildings.size()) {
                break;
            }
            Building building = (Building) buildings.get((String) buildings.keyAt(i2));
            if (!building.outdoor) {
                Text text = building.name;
                if (text != null) {
                    str = text.getText();
                }
                arrayList.add(str);
                arrayList2.add(building.f1329id);
            }
            i2++;
        }
        this.f1010j.mo28768a((String[]) arrayList.toArray(new String[0]), (String[]) arrayList2.toArray(new String[0]));
        this.f1009i.setEnabled(true);
        this.f1009i.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (MapHoverActivity.this.f1010j.f967f) {
                    MapHoverActivity.this.f1009i.setVisibility(4);
                    MapHoverActivity.this.f1010j.mo28765a();
                    Venue currentVenue = NaviBeesManager.getInstance(MapHoverActivity.this.getApplication()).getMetaDataManager().getCurrentVenue();
                    if (currentVenue != null && currentVenue.globalMode) {
                        MapHoverActivity.this.onBuildingButtonClick();
                    }
                }
            }
        });
        Building currentBuilding = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentBuilding();
        if (currentBuilding != null) {
            Text text2 = currentBuilding.name;
            if (text2 != null) {
                str = text2.getText();
            }
            m667a(this.f1009i, str, getResources().getString(C1266R.string.building_button_label));
        }
        this.f1008h = new CirclePickerView(this, (HorizontalScrollView) this.f1020t.findViewById(C1266R.C1269id.floors_scroll_view), this);
        this.f1007g.setEnabled(true);
        this.f1007g.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (MapHoverActivity.this.f1008h.f967f) {
                    MapHoverActivity.this.f1007g.setVisibility(4);
                    MapHoverActivity.this.f1008h.mo28765a();
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) MapHoverActivity.this.savedLocationButton.getLayoutParams();
                    marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin + 200);
                    if (MapHoverActivity.this.f993R) {
                        MapHoverActivity.this.savedLocationButton.setLayoutParams(marginLayoutParams);
                    }
                    MapHoverActivity.this.onFloorButtonClick();
                }
            }
        });
        m736v();
    }

    /* renamed from: s */
    private void m730s() {
        this.f1017q = (float) getResources().getDimensionPixelSize(C1266R.dimen.bottom_sheet_preview_height);
        this.f1018r = (float) getResources().getDimensionPixelSize(C1266R.dimen.nested_scroll_view_height);
        this.f1021u = findViewById(C1266R.C1269id.bottom_sheet);
        this.f1022v = BottomSheetBehavior.from(this.f1021u);
        this.f1022v.setState(5);
        this.f1023w = (NestedScrollView) findViewById(C1266R.C1269id.nested_scroll_view);
        this.f1023w.setOnScrollChangeListener(new OnScrollChangeListener() {
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                MapHoverActivity.this.f1021u.requestLayout();
            }
        });
        this.f1019s = (ImageView) findViewById(C1266R.C1269id.imgPoiIcon);
        this.f978C = (TextView) findViewById(C1266R.C1269id.poi_description);
        this.f1024x = false;
        this.f1022v.setBottomSheetCallback(this.f992Q);
        this.startNavigationIV = (ImageView) findViewById(C1266R.C1269id.directions_fab);
        this.startNavigationIV.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MapHoverActivity mapHoverActivity = MapHoverActivity.this;
                if (mapHoverActivity.currentlySelectedPOI != null || mapHoverActivity.destinationLocation != null) {
                    MapHoverActivity.this.onStartNavigationClick();
                    MapHoverActivity.this.onMyLocationClick(true);
                }
            }
        });
        this.clearRouteIV = (ImageView) findViewById(C1266R.C1269id.clear_route_fab);
        this.clearRouteIV.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (MapHoverActivity.this.m718m()) {
                    MapHoverActivity.this.f1001a.setDrawerLockMode(0);
                }
                MapHoverActivity.this.collapsedBottomSheet();
                if (MapHoverActivity.enableRoutingWhenOutOfVenue) {
                    MapHoverActivity.this.clearStartSearchView.callOnClick();
                }
                if (MapHoverActivity.this.f993R) {
                    MapHoverActivity.this.startNavigationIV.setVisibility(0);
                }
                MapHoverActivity.this.clearRouteIV.setVisibility(4);
                MapHoverActivity.this.showNavigationInstructionsBar(false);
                MapHoverActivity.this.m693c(true);
                MapHoverActivity.this.f1003c.setExpanded(true);
                MapHoverActivity.this.f983H.setVisibility(8);
                MapHoverActivity.this.m726q();
                MapHoverActivity.this.m724p();
                MapHoverActivity.this.onClearRouteClick();
            }
        });
        this.f982G = this.f1021u.findViewById(C1266R.C1269id.bottom_sheet_preview);
        this.f983H = this.f1021u.findViewById(C1266R.C1269id.bottom_sheet_preview_routing);
        this.f1025y = (TextView) this.f982G.findViewById(C1266R.C1269id.poiName);
        this.f1026z = (TextView) this.f982G.findViewById(C1266R.C1269id.poi_floor);
        this.f976A = (TextView) this.f982G.findViewById(C1266R.C1269id.travel_time);
        this.f977B = (TextView) this.f1021u.findViewById(C1266R.C1269id.poi_category);
        this.f979D = (TextView) this.f1021u.findViewById(C1266R.C1269id.poi_WorkingStatus);
        this.f980E = (TextView) this.f1021u.findViewById(C1266R.C1269id.poi_WorkingTimes);
        this.f981F = (ListView) this.f1021u.findViewById(C1266R.C1269id.poi_contacts_list);
        this.f984I = (TextView) this.f983H.findViewById(C1266R.C1269id.remaining_travel_time);
        this.f985J = (TextView) this.f983H.findViewById(C1266R.C1269id.remaining_travel_length);
        this.f986K = this.f983H.findViewById(C1266R.C1269id.travel_progress);
        FrameLayout frameLayout = (FrameLayout) this.f1023w.findViewById(C1266R.C1269id.custom_bottom_layout);
        View pOIDescriptionView = getPOIDescriptionView();
        if (pOIDescriptionView != null && frameLayout.getChildCount() == 0) {
            View pOIDescriptionView2 = getPOIDescriptionView();
            pOIDescriptionView2.setLayoutParams(new LayoutParams(-1, -1));
            frameLayout.addView(pOIDescriptionView2);
        }
        if (pOIDescriptionView == null) {
            frameLayout.setVisibility(4);
        }
    }

    /* renamed from: t */
    private void m732t() {
        this.f1011k = (FrameLayout) findViewById(C1266R.C1269id.NavBar);
        this.f1012l = (TextView) this.f1011k.findViewById(C1266R.C1269id.textViewNavigationInstruction);
        this.f1013m = (ImageView) this.f1011k.findViewById(C1266R.C1269id.imageViewNavigation);
    }

    /* renamed from: u */
    private void m734u() {
        this.f1004d = (Toolbar) findViewById(C1266R.C1269id.toolbar);
        setSupportActionBar(this.f1004d);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (m716l()) {
                supportActionBar.setDisplayShowHomeEnabled(true);
            } else {
                supportActionBar.setHomeAsUpIndicator(C1266R.C1268drawable.com_navibees_sdk_ic_menu);
            }
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f1004d.setBackgroundColor(-1);
        this.clearSearchView = this.f1004d.findViewById(C1266R.C1269id.search_clear);
        View view = this.clearSearchView;
        if (view != null) {
            view.setVisibility(4);
            this.clearSearchView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MapHoverActivity mapHoverActivity = MapHoverActivity.this;
                    mapHoverActivity.currentlySelectedPOI = null;
                    mapHoverActivity.m692c();
                    MapHoverActivity.this.onClearSearchViewClick();
                    MapHoverActivity.this.f1022v.setHideable(true);
                    MapHoverActivity.this.f1022v.setState(5);
                }
            });
        }
        this.clearStartSearchView = this.f1004d.findViewById(C1266R.C1269id.search_start_clear);
        View view2 = this.clearStartSearchView;
        if (view2 != null) {
            view2.setVisibility(4);
            this.clearStartSearchView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MapHoverActivity.selectedOrigin = null;
                    MapHoverActivity.this.showStartPointSearchBar(false);
                    MapHoverActivity.this.m697d();
                }
            });
        }
        this.searchStartEditText = (EditText) this.f1004d.findViewById(C1266R.C1269id.search_start_edit_text);
        this.searchEditText = (EditText) this.f1004d.findViewById(C1266R.C1269id.search_edit_text);
        this.searchEditText.setFocusable(false);
        if (this.searchEditText != null) {
            String string = getResources().getString(C1266R.string.search_query_hint);
            String text = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentVenue().name.getText();
            if (text != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(string);
                sb.append(" ");
                sb.append(text);
                string = sb.toString();
            }
            this.searchEditText.setHint(string);
            this.searchEditText.setCursorVisible(false);
            this.searchEditText.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MapHoverActivity.isClickedOnSearchStartPoint = false;
                    MapHoverActivity mapHoverActivity = MapHoverActivity.this;
                    mapHoverActivity.gotoRouteToActivity(mapHoverActivity.searchEditText.getText().toString(), "");
                }
            });
        }
        this.searchStartEditText.setFocusable(false);
        if (this.searchStartEditText != null) {
            this.searchStartEditText.setHint(getResources().getString(C1266R.string.search_start_hint));
            this.searchStartEditText.setCursorVisible(false);
            this.searchStartEditText.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MapHoverActivity.isClickedOnSearchStartPoint = true;
                    MapHoverActivity mapHoverActivity = MapHoverActivity.this;
                    mapHoverActivity.gotoRouteToActivity(mapHoverActivity.searchStartEditText.getText().toString(), MapHoverActivity.this.searchEditText.getText().toString());
                }
            });
        }
        this.f1003c = (AppBarLayout) findViewById(C1266R.C1269id.app_bar_layout);
        this.f1003c.setVisibility(0);
    }

    /* renamed from: v */
    private void m736v() {
        Building currentBuilding = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().getCurrentBuilding();
        if (currentBuilding != null) {
            List<Floor> list = currentBuilding.floors;
            if (list != null) {
                String[] strArr = new String[list.size()];
                String[] strArr2 = new String[currentBuilding.floors.size()];
                for (int i = 0; i < currentBuilding.floors.size(); i++) {
                    strArr[i] = ((Floor) currentBuilding.floors.get(i)).name.getText();
                    strArr2[i] = ((Floor) currentBuilding.floors.get(i)).f1331id;
                }
                this.f1008h.mo28768a(strArr, strArr2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initiateRouting(Object obj, MapHoverInterface mapHoverInterface) {
        if (obj instanceof Facility) {
            this.currentlySelectedFacility = (Facility) obj;
        } else {
            this.currentlySelectedFacility = null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m693c(boolean z) {
        if (!z) {
            this.savedLocationButton.setVisibility(4);
        } else if (NaviBeesManager.getInstance(getApplication()).getSavedLocation(this) == null) {
            this.savedLocationButton.setVisibility(4);
        } else if (this.showSavedLocationButton) {
            this.savedLocationButton.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m697d() {
        this.clearStartSearchView.setVisibility(4);
        this.searchStartEditText.setText("");
        this.searchStartEditText.clearFocus();
        this.searchStartEditText.setCursorVisible(false);
    }

    /* renamed from: e */
    private int m699e(String str) {
        try {
            return getResources().getIdentifier(str, "raw", getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m686b(POI poi, double d, int i, String str) {
        String str2;
        String str3;
        String str4 = " ~ ";
        String str5 = "";
        if (poi != null) {
            Text text = poi.name;
            if (text != null) {
                this.f1025y.setText(text.getText());
            } else {
                this.f1025y.setText(getResources().getString(C1266R.string.default_poi_label_bottom_sheet));
            }
            if (str != null) {
                str3 = m681b(str);
            } else {
                str3 = str5;
            }
            this.f1026z.setText(str3);
            String str6 = poi.categoryId;
            HashMap<String, POICategory> hashMap = NaviBeesManager.getInstance(getApplication()).getMetaDataManager().navibeesApp.categories;
            if (hashMap != null) {
                POICategory pOICategory = (POICategory) hashMap.get(str6);
                if (pOICategory != null) {
                    this.f977B.setText(pOICategory.name.getText());
                } else {
                    this.f977B.setText(str5);
                }
            }
            if (enableRoutingWhenOutOfVenue) {
                this.f976A.setVisibility(4);
            } else {
                this.f976A.setText(m679b(i));
            }
            this.f984I.setText(m679b(i));
            TextView textView = this.f985J;
            StringBuilder sb = new StringBuilder();
            sb.append(m661a((int) Math.ceil(d)));
            sb.append(str4);
            sb.append(str3);
            textView.setText(sb.toString());
            this.f986K.getLayoutParams().width = 0;
            this.f986K.requestLayout();
            String[] stringArray = getResources().getStringArray(C1266R.array.WorkingTime);
            if (stringArray != null && stringArray.length > 0) {
                this.f980E.setText(m663a(stringArray[Calendar.getInstance().get(7) - 1], this.f979D));
            }
            Image image = poi.logo;
            if (image == null || TextUtils.isEmpty(image.url)) {
                this.f1019s.setImageDrawable(null);
            } else {
                this.f1019s.setVisibility(0);
                ((RequestBuilder) ((RequestBuilder) Glide.with((FragmentActivity) this).load(poi.logo.url).thumbnail(0.1f).sizeMultiplier(0.4f)).fitCenter()).into(this.f1019s);
            }
            if (poi.categoryId != null) {
                String text2 = ((POICategory) NaviBeesManager.getInstance(getApplication()).getMetaDataManager().navibeesApp.categories.get(poi.categoryId)).name.getText();
                TextView textView2 = this.f977B;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Category: ");
                sb2.append(text2);
                textView2.setText(sb2.toString());
            }
            Text text3 = poi.description;
            if (text3 != null) {
                this.f978C.setText(text3.getText());
            } else {
                this.f978C.setText(str5);
            }
            List<Contact> list = poi.contacts;
            if (list != null && list.size() > 0) {
                ArrayList arrayList = new ArrayList();
                for (Contact contact : poi.contacts) {
                    String str7 = contact.phoneNumber;
                    if (str7 != null) {
                        arrayList.add(str7);
                    }
                }
                this.f981F.setAdapter(new ArrayAdapter(this, 17367043, arrayList));
            } else if (this.f981F.getAdapter() != null) {
                ((ArrayAdapter) this.f981F.getAdapter()).clear();
                ((ArrayAdapter) this.f981F.getAdapter()).notifyDataSetChanged();
            }
        } else if (this.destinationLocation != null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(this.f989N.format(this.destinationLocation.f1332x));
            sb3.append(", ");
            sb3.append(this.f989N.format(this.destinationLocation.f1333y));
            String sb4 = sb3.toString();
            if (!TextUtils.isEmpty(this.destinationLocationName)) {
                this.f1025y.setText(this.destinationLocationName);
            } else {
                this.f1025y.setText(sb4);
            }
            if (str != null) {
                str2 = m681b(str);
            } else {
                str2 = str5;
            }
            this.f1026z.setText(str2);
            if (enableRoutingWhenOutOfVenue) {
                this.f976A.setVisibility(4);
            } else {
                this.f976A.setText(m679b(i));
            }
            this.f984I.setText(m679b(i));
            TextView textView3 = this.f985J;
            StringBuilder sb5 = new StringBuilder();
            sb5.append(m661a((int) Math.ceil(d)));
            sb5.append(str4);
            sb5.append(str2);
            textView3.setText(sb5.toString());
            this.f986K.getLayoutParams().width = 0;
            this.f986K.requestLayout();
            this.f980E.setText(str5);
            if (this.f981F.getAdapter() != null) {
                ((ArrayAdapter) this.f981F.getAdapter()).clear();
                ((ArrayAdapter) this.f981F.getAdapter()).notifyDataSetChanged();
            }
        }
    }

    /* renamed from: e */
    private void m701e() {
        CirclePickerView circlePickerView = this.f1010j;
        if (!circlePickerView.f967f) {
            circlePickerView.mo28766a((OnHideFinishedListener) new OnHideFinishedListener() {
                /* renamed from: a */
                public void mo28779a() {
                    MapHoverActivity.this.f1009i.setVisibility(0);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void initiateRouting(IndoorLocation indoorLocation, String str, MapHoverInterface mapHoverInterface) {
        this.currentlySelectedFacility = null;
        this.destinationLocation = indoorLocation;
        this.destinationLocationName = str;
    }

    /* renamed from: a */
    private String m663a(String str, TextView textView) {
        String str2;
        String str3;
        TextView textView2 = textView;
        String[] split = str.split(",");
        double d = ((double) Calendar.getInstance().get(11)) + ((double) (((float) Calendar.getInstance().get(12)) / 60.0f));
        Double valueOf = Double.valueOf(Double.parseDouble(split[0]));
        Double valueOf2 = Double.valueOf(Double.parseDouble(split[1]));
        if (d < 6.0d) {
            d += 24.0d;
        }
        if (valueOf.doubleValue() > d || valueOf2.doubleValue() < d) {
            textView2.setText(getResources().getString(C1266R.string.close_now));
        } else {
            textView2.setText(getResources().getString(C1266R.string.open_now));
        }
        int i = valueOf.doubleValue() >= 25.0d ? 24 : 12;
        String str4 = " PM";
        String str5 = " AM";
        String str6 = ":";
        if (valueOf.doubleValue() > 12.0d) {
            StringBuilder sb = new StringBuilder();
            sb.append(valueOf.intValue() - i);
            sb.append(str6);
            sb.append(m691c(split[0]));
            sb.append((valueOf.doubleValue() < 12.0d || valueOf.doubleValue() >= 24.0d) ? str5 : str4);
            str2 = sb.toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(valueOf.intValue());
            sb2.append(str6);
            sb2.append(m691c(split[0]));
            sb2.append(str5);
            str2 = sb2.toString();
        }
        int i2 = valueOf2.doubleValue() >= 25.0d ? 24 : 12;
        if (valueOf2.doubleValue() > 12.0d) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(valueOf2.intValue() - i2);
            sb3.append(str6);
            sb3.append(m691c(split[1]));
            if (valueOf2.doubleValue() >= 12.0d && valueOf2.doubleValue() < 24.0d) {
                str5 = str4;
            }
            sb3.append(str5);
            str3 = sb3.toString();
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(valueOf2.intValue());
            sb4.append(str6);
            sb4.append(m691c(split[1]));
            sb4.append(str5);
            str3 = sb4.toString();
        }
        StringBuilder sb5 = new StringBuilder();
        sb5.append(str2);
        sb5.append(" to ");
        sb5.append(str3);
        return sb5.toString();
    }

    /* renamed from: d */
    private int m695d(String str) {
        try {
            return getResources().getIdentifier(str, "drawable", getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: c */
    private String m691c(String str) {
        int round = Math.round((((float) Math.round(Float.parseFloat(str))) - Float.parseFloat(str)) * 60.0f);
        if (round >= 10) {
            return String.valueOf(round);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("0");
        sb.append(String.valueOf(round));
        return sb.toString();
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m692c() {
        this.clearSearchView.setVisibility(4);
        this.searchEditText.setText("");
        this.searchEditText.clearFocus();
        this.searchEditText.setCursorVisible(false);
    }

    public void updateNavigationMessage(String str, String str2, String str3) {
        RouteInterface routeInterface = this.f987L;
        if (routeInterface != null) {
            routeInterface.onNavigationInstructionComputed(str, str3);
        }
        int d = m695d(str3);
        if (d != -1) {
            this.f1013m.setImageResource(d);
        }
        if (str != null) {
            showNavigationInstructionsBar(true);
            m693c(false);
            this.f1012l.setText(str);
            if (NaviBeesManager.getAppLanguage().equals("ar")) {
                m677a(m664a(str2));
                if (this.f996U != null) {
                    m724p();
                }
                this.f996U = new MediaPlayer();
                startPlayingTracks();
                return;
            }
            TTSManager tTSManager = this.f988M;
            if (tTSManager != null) {
                tTSManager.setTtsListener(this);
            }
            this.f988M.speak(str);
        }
    }

    /* renamed from: a */
    private void m676a(String str, String str2) {
        Intent intent = new Intent(getApplicationContext(), getRouteToActivity());
        intent.putExtra(NaviBeesConstants.ROUTE_TO_CATEGORY_SORT_TYPE_KEY, getRouteToCategorySortType());
        intent.putExtra(NaviBeesConstants.ROUTE_TO_POI_SORT_TYPE_KEY, getRouteToPOISortType());
        intent.putExtra(NaviBeesConstants.ROUTE_TO_FACILITY_SORT_TYPE_KEY, getRouteToFacilitySortType());
        intent.putExtra(NaviBeesConstants.ROUTE_TO_INITIAL_SEARCH_TERM_KEY, str);
        intent.putExtra(NaviBeesConstants.ROUTE_TO_DESTINATION_SEARCH_TERM_KEY, str2);
        startActivityForResult(intent, 0);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m667a(Button button, String str, String str2) {
        try {
            button.setText(String.format(str2, new Object[]{str}));
            String charSequence = button.getText().toString();
            int length = str.length() + 1;
            SpannableString spannableString = new SpannableString(charSequence);
            spannableString.setSpan(new StyleSpan(1), 0, str.length(), 33);
            spannableString.setSpan(new AbsoluteSizeSpan((int) NaviBeesUtils.setTextSize(button, Math.round(TypedValue.applyDimension(1, 56.0f, getResources().getDisplayMetrics()))), false), 0, str.length(), 33);
            spannableString.setSpan(new AbsoluteSizeSpan(8, true), length, charSequence.length(), 33);
            spannableString.setSpan(new ForegroundColorSpan(-7829368), length, charSequence.length(), 33);
            button.setText(spannableString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m678a(final boolean z) {
        runOnUiThread(new Runnable() {
            public void run() {
                MapHoverActivity.this.startNavigationIV.setEnabled(z);
            }
        });
    }

    /* renamed from: a */
    private void m665a(double d, int i, String str, boolean z) {
        POI poi = this.currentlySelectedPOI;
        if (poi != null) {
            m675a(poi, d, i, str);
            clearDisplayedRoute();
            clearDisplayedRoutingInstructions();
            showDestinationMarker(this.currentlySelectedPOI);
            stopAutomaticMode();
            if (z) {
                zoomToPOI(this.currentlySelectedPOI);
                return;
            }
            return;
        }
        IndoorLocation indoorLocation = this.destinationLocation;
        if (indoorLocation != null) {
            m674a(indoorLocation, d, i, str);
            clearDisplayedRoute();
            clearDisplayedRoutingInstructions();
            showDestinationMarker(this.destinationLocation);
            stopAutomaticMode();
        }
    }

    /* renamed from: a */
    private void m675a(POI poi, double d, int i, String str) {
        final POI poi2 = poi;
        final double d2 = d;
        final int i2 = i;
        final String str2 = str;
        C157217 r0 = new Runnable() {
            public void run() {
                MapHoverActivity.this.f1003c.setExpanded(true);
                if (MapHoverActivity.this.f993R) {
                    MapHoverActivity.this.startNavigationIV.setVisibility(0);
                }
                MapHoverActivity.this.clearRouteIV.setVisibility(4);
                POI poi = poi2;
                if (poi.name != null) {
                    MapHoverActivity.this.searchEditText.setText(poi.getDescProperty());
                }
                MapHoverActivity.this.clearSearchView.setVisibility(0);
                if (NaviBeesManager.getInstance(MapHoverActivity.this.getApplication()).getPositionManager().getLastLocationCoordinateLatLong() == null && MapHoverActivity.enableRoutingWhenOutOfVenue && MapHoverActivity.selectedOrigin == null) {
                    MapHoverActivity.this.clearDisplayedRoute();
                    MapHoverActivity.this.showDestinationMarker(poi2);
                }
                MapHoverActivity.this.m720n();
                MapHoverActivity.this.m721o();
                MapHoverActivity.this.f1022v.setPeekHeight((int) MapHoverActivity.this.f1017q);
                if (MapHoverActivity.this.f1022v.getState() == 5) {
                    MapHoverActivity.this.f1022v.setState(3);
                } else if (MapHoverActivity.this.f1022v.getState() == 3) {
                    MapHoverActivity.this.f1022v.setState(4);
                }
                MapHoverActivity.this.m686b(poi2, d2, i2, str2);
                MapHoverActivity.this.onPlaceSelected(poi2, null);
            }
        };
        runOnUiThread(r0);
    }

    /* renamed from: a */
    private void m674a(IndoorLocation indoorLocation, double d, int i, String str) {
        final IndoorLocation indoorLocation2 = indoorLocation;
        final double d2 = d;
        final int i2 = i;
        final String str2 = str;
        C157318 r0 = new Runnable() {
            public void run() {
                MapHoverActivity.this.f1003c.setExpanded(true);
                if (MapHoverActivity.this.f993R) {
                    MapHoverActivity.this.startNavigationIV.setVisibility(0);
                }
                MapHoverActivity.this.clearRouteIV.setVisibility(4);
                if (indoorLocation2 != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(MapHoverActivity.this.f989N.format(indoorLocation2.f1332x));
                    sb.append(", ");
                    sb.append(MapHoverActivity.this.f989N.format(indoorLocation2.f1333y));
                    String sb2 = sb.toString();
                    if (!TextUtils.isEmpty(MapHoverActivity.this.destinationLocationName)) {
                        MapHoverActivity mapHoverActivity = MapHoverActivity.this;
                        mapHoverActivity.searchEditText.setText(mapHoverActivity.destinationLocationName);
                    } else {
                        MapHoverActivity.this.searchEditText.setText(sb2);
                    }
                } else {
                    MapHoverActivity.this.destinationLocationName = null;
                }
                MapHoverActivity.this.clearSearchView.setVisibility(0);
                if (NaviBeesManager.getInstance(MapHoverActivity.this.getApplication()).getPositionManager().getLastLocationCoordinateLatLong() == null && MapHoverActivity.enableRoutingWhenOutOfVenue && MapHoverActivity.selectedOrigin == null) {
                    MapHoverActivity.this.clearDisplayedRoute();
                    MapHoverActivity.this.showDestinationMarker(indoorLocation2);
                }
                MapHoverActivity mapHoverActivity2 = MapHoverActivity.this;
                if (mapHoverActivity2.showBottomSheet) {
                    mapHoverActivity2.m721o();
                }
                MapHoverActivity mapHoverActivity3 = MapHoverActivity.this;
                if (mapHoverActivity3.showBottomSheet) {
                    mapHoverActivity3.f1022v.setPeekHeight((int) MapHoverActivity.this.f1017q);
                }
                if (MapHoverActivity.this.f993R) {
                    MapHoverActivity.this.m720n();
                }
                if (MapHoverActivity.this.f1022v.getState() == 5) {
                    MapHoverActivity.this.f1022v.setState(3);
                } else if (MapHoverActivity.this.f1022v.getState() == 3) {
                    MapHoverActivity.this.f1022v.setState(4);
                }
                MapHoverActivity mapHoverActivity4 = MapHoverActivity.this;
                if (mapHoverActivity4.showBottomSheet) {
                    mapHoverActivity4.m686b(null, d2, i2, str2);
                }
                MapHoverActivity.this.onPlaceSelected(null, indoorLocation2);
            }
        };
        runOnUiThread(r0);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public String m661a(int i) {
        return String.format(getResources().getString(C1266R.string.bottom_sheet_remaining_travel_length_label), new Object[]{new DecimalFormat("##").format((long) i)});
    }

    /* renamed from: a */
    private ArrayList<String> m664a(String str) {
        String str2;
        if (str != null) {
            String str3 = "";
            if (!str.equals(str3)) {
                ArrayList<String> arrayList = new ArrayList<>();
                for (String replace = str.replace("\"", str3); !replace.equals(str3); replace = str2) {
                    String[] strArr = this.f995T;
                    int length = strArr.length;
                    str2 = replace;
                    for (int i = 0; i < length; i++) {
                        String str4 = strArr[i];
                        if (str2.startsWith(str4)) {
                            String str5 = "You have arrived";
                            String lowerCase = str4.toLowerCase().equals(str5.toLowerCase()) ? str4 : str4.toLowerCase();
                            if (lowerCase.equals(str5)) {
                                lowerCase = "you_have_arrived";
                            } else if (lowerCase.equals("you have arrived")) {
                                lowerCase = "you_have_arrived2";
                            }
                            arrayList.add(lowerCase.replace(" ", "_").replace("-", str3).replace("for", "fors"));
                            str2 = str2.substring(str4.length()).trim();
                        }
                        if (str2.equals(str3)) {
                            break;
                        }
                    }
                    if (str2.equals(replace)) {
                        break;
                    }
                }
                return arrayList;
            }
        }
        return null;
    }

    /* renamed from: a */
    private void m677a(ArrayList<String> arrayList) {
        this.f997V = new int[arrayList.size()];
        int i = 0;
        this.f998W = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int e = m699e((String) it.next());
            if (e != -1) {
                this.f997V[i] = e;
            }
            i++;
        }
    }

    /* renamed from: a */
    private void m668a(NavigationView navigationView) {
        Menu menu = navigationView.getMenu();
        int i = m709i();
        if (i != -1) {
            getMenuInflater().inflate(i, menu);
            int childCount = navigationView.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = navigationView.getChildAt(i2);
                if (childAt != null && (childAt instanceof ListView)) {
                    ((BaseAdapter) ((HeaderViewListAdapter) ((ListView) childAt).getAdapter()).getWrappedAdapter()).notifyDataSetChanged();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public String m679b(int i) {
        String str;
        String str2;
        if (i >= 60) {
            str2 = getResources().getString(C1266R.string.bottom_sheet_preview_travel_time_min_label);
            str = new DecimalFormat("##.#").format(((double) i) / 60.0d);
        } else {
            str2 = getResources().getString(C1266R.string.bottom_sheet_preview_travel_time_sec_label);
            str = new DecimalFormat("##").format((long) i);
        }
        return String.format(str2, new Object[]{str});
    }

    /* renamed from: b */
    private void m682b() {
        if (VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setStatusBarColor(Color.parseColor("#20000000"));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m687b(String str, String str2) {
        Builder builder = new Builder(this);
        builder.setTitle("").setMessage(str2).setPositiveButton(getString(C1266R.string.f216ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MapHoverActivity.this.clearRouteIV.callOnClick();
                MapHoverActivity.this.f994S = false;
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
        TTSManager tTSManager = this.f988M;
        if (tTSManager != null) {
            tTSManager.speak(getString(C1266R.string.dialog_arrived_message));
        }
    }

    /* renamed from: b */
    private String m681b(String str) {
        return String.format(getResources().getString(C1266R.string.floor_number_label_bottom_sheet), new Object[]{str});
    }

    /* renamed from: b */
    private void m684b(NavigationView navigationView) {
        addHeaderRunTime(navigationView);
        m668a(navigationView);
        navigationView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                MapHoverActivity.this.m683b(menuItem);
                MapHoverActivity.this.f1001a.closeDrawers();
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m683b(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == C1266R.C1269id.com_navibees_sdk_places_to_go) {
            String str = "";
            gotoRouteToActivity(str, str);
        } else if (itemId == C1266R.C1269id.com_navibees_sdk_activities) {
            gotoActivitiesActivity();
        } else {
            m666a(menuItem);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m688b(boolean z) {
        if (z) {
            this.isCompassActive = true;
            this.compassImage.setImageResource(C1266R.C1268drawable.compass_mode_enabled);
            return;
        }
        this.isCompassActive = false;
        this.compassImage.setImageResource(C1266R.C1268drawable.compass_mode_disabled);
    }
}
