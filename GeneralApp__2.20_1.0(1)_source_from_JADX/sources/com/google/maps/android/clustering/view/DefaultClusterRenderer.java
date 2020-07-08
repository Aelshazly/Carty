package com.google.maps.android.clustering.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue.IdleHandler;
import android.util.SparseArray;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.C1160R;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.ClusterManager.OnClusterClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterInfoWindowClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemInfoWindowClickListener;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.p006ui.IconGenerator;
import com.google.maps.android.p006ui.SquareTextView;
import com.google.maps.android.projection.SphericalMercatorProjection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import p008cz.msebera.android.httpclient.HttpStatus;

public class DefaultClusterRenderer<T extends ClusterItem> implements ClusterRenderer<T> {
    /* access modifiers changed from: private */
    public static final TimeInterpolator ANIMATION_INTERP = new DecelerateInterpolator();
    private static final int[] BUCKETS = {10, 20, 50, 100, 200, HttpStatus.SC_INTERNAL_SERVER_ERROR, 1000};
    /* access modifiers changed from: private */
    public static final boolean SHOULD_ANIMATE = (VERSION.SDK_INT >= 11);
    /* access modifiers changed from: private */
    public boolean mAnimate;
    /* access modifiers changed from: private */
    public OnClusterClickListener<T> mClickListener;
    /* access modifiers changed from: private */
    public final ClusterManager<T> mClusterManager;
    /* access modifiers changed from: private */
    public Map<Cluster<T>, Marker> mClusterToMarker = new HashMap();
    /* access modifiers changed from: private */
    public Set<? extends Cluster<T>> mClusters;
    private ShapeDrawable mColoredCircleBackground;
    private final float mDensity;
    private final IconGenerator mIconGenerator;
    private SparseArray<BitmapDescriptor> mIcons = new SparseArray<>();
    /* access modifiers changed from: private */
    public OnClusterInfoWindowClickListener<T> mInfoWindowClickListener;
    /* access modifiers changed from: private */
    public OnClusterItemClickListener<T> mItemClickListener;
    /* access modifiers changed from: private */
    public OnClusterItemInfoWindowClickListener<T> mItemInfoWindowClickListener;
    /* access modifiers changed from: private */
    public final GoogleMap mMap;
    /* access modifiers changed from: private */
    public MarkerCache<T> mMarkerCache = new MarkerCache<>();
    /* access modifiers changed from: private */
    public Map<Marker, Cluster<T>> mMarkerToCluster = new HashMap();
    /* access modifiers changed from: private */
    public Set<MarkerWithPosition> mMarkers = Collections.newSetFromMap(new ConcurrentHashMap());
    private int mMinClusterSize = 4;
    private final ViewModifier mViewModifier = new ViewModifier<>();
    /* access modifiers changed from: private */
    public float mZoom;

    private class AnimationTask extends AnimatorListenerAdapter implements AnimatorUpdateListener {
        private final LatLng from;
        private MarkerManager mMarkerManager;
        private boolean mRemoveOnComplete;
        private final Marker marker;
        private final MarkerWithPosition markerWithPosition;

        /* renamed from: to */
        private final LatLng f88to;

        private AnimationTask(MarkerWithPosition markerWithPosition2, LatLng from2, LatLng to) {
            this.markerWithPosition = markerWithPosition2;
            this.marker = markerWithPosition2.marker;
            this.from = from2;
            this.f88to = to;
        }

        public void perform() {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            valueAnimator.setInterpolator(DefaultClusterRenderer.ANIMATION_INTERP);
            valueAnimator.addUpdateListener(this);
            valueAnimator.addListener(this);
            valueAnimator.start();
        }

        public void onAnimationEnd(Animator animation) {
            if (this.mRemoveOnComplete) {
                DefaultClusterRenderer.this.mClusterToMarker.remove((Cluster) DefaultClusterRenderer.this.mMarkerToCluster.get(this.marker));
                DefaultClusterRenderer.this.mMarkerCache.remove(this.marker);
                DefaultClusterRenderer.this.mMarkerToCluster.remove(this.marker);
                this.mMarkerManager.remove(this.marker);
            }
            this.markerWithPosition.position = this.f88to;
        }

        public void removeOnAnimationComplete(MarkerManager markerManager) {
            this.mMarkerManager = markerManager;
            this.mRemoveOnComplete = true;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fraction = valueAnimator.getAnimatedFraction();
            double lat = ((this.f88to.latitude - this.from.latitude) * ((double) fraction)) + this.from.latitude;
            double lngDelta = this.f88to.longitude - this.from.longitude;
            if (Math.abs(lngDelta) > 180.0d) {
                lngDelta -= Math.signum(lngDelta) * 360.0d;
            }
            this.marker.setPosition(new LatLng(lat, (((double) fraction) * lngDelta) + this.from.longitude));
        }
    }

    private class CreateMarkerTask {
        private final LatLng animateFrom;
        private final Cluster<T> cluster;
        private final Set<MarkerWithPosition> newMarkers;

        public CreateMarkerTask(Cluster<T> c, Set<MarkerWithPosition> markersAdded, LatLng animateFrom2) {
            this.cluster = c;
            this.newMarkers = markersAdded;
            this.animateFrom = animateFrom2;
        }

        /* access modifiers changed from: private */
        public void perform(MarkerModifier markerModifier) {
            MarkerWithPosition markerWithPosition;
            MarkerWithPosition markerWithPosition2;
            if (!DefaultClusterRenderer.this.shouldRenderAsCluster(this.cluster)) {
                for (T item : this.cluster.getItems()) {
                    Marker marker = DefaultClusterRenderer.this.mMarkerCache.get(item);
                    if (marker == null) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        LatLng latLng = this.animateFrom;
                        if (latLng != null) {
                            markerOptions.position(latLng);
                        } else {
                            markerOptions.position(item.getPosition());
                        }
                        if (item.getTitle() != null && item.getSnippet() != null) {
                            markerOptions.title(item.getTitle());
                            markerOptions.snippet(item.getSnippet());
                        } else if (item.getSnippet() != null) {
                            markerOptions.title(item.getSnippet());
                        } else if (item.getTitle() != null) {
                            markerOptions.title(item.getTitle());
                        }
                        DefaultClusterRenderer.this.onBeforeClusterItemRendered(item, markerOptions);
                        marker = DefaultClusterRenderer.this.mClusterManager.getMarkerCollection().addMarker(markerOptions);
                        markerWithPosition2 = new MarkerWithPosition(marker);
                        DefaultClusterRenderer.this.mMarkerCache.put(item, marker);
                        LatLng latLng2 = this.animateFrom;
                        if (latLng2 != null) {
                            markerModifier.animate(markerWithPosition2, latLng2, item.getPosition());
                        }
                    } else {
                        markerWithPosition2 = new MarkerWithPosition(marker);
                    }
                    DefaultClusterRenderer.this.onClusterItemRendered(item, marker);
                    this.newMarkers.add(markerWithPosition2);
                }
                return;
            }
            Marker marker2 = (Marker) DefaultClusterRenderer.this.mClusterToMarker.get(this.cluster);
            if (marker2 == null) {
                MarkerOptions markerOptions2 = new MarkerOptions();
                LatLng latLng3 = this.animateFrom;
                if (latLng3 == null) {
                    latLng3 = this.cluster.getPosition();
                }
                MarkerOptions markerOptions3 = markerOptions2.position(latLng3);
                DefaultClusterRenderer.this.onBeforeClusterRendered(this.cluster, markerOptions3);
                marker2 = DefaultClusterRenderer.this.mClusterManager.getClusterMarkerCollection().addMarker(markerOptions3);
                DefaultClusterRenderer.this.mMarkerToCluster.put(marker2, this.cluster);
                DefaultClusterRenderer.this.mClusterToMarker.put(this.cluster, marker2);
                markerWithPosition = new MarkerWithPosition(marker2);
                LatLng latLng4 = this.animateFrom;
                if (latLng4 != null) {
                    markerModifier.animate(markerWithPosition, latLng4, this.cluster.getPosition());
                }
            } else {
                markerWithPosition = new MarkerWithPosition(marker2);
            }
            DefaultClusterRenderer.this.onClusterRendered(this.cluster, marker2);
            this.newMarkers.add(markerWithPosition);
        }
    }

    private static class MarkerCache<T> {
        private Map<T, Marker> mCache;
        private Map<Marker, T> mCacheReverse;

        private MarkerCache() {
            this.mCache = new HashMap();
            this.mCacheReverse = new HashMap();
        }

        public Marker get(T item) {
            return (Marker) this.mCache.get(item);
        }

        public T get(Marker m) {
            return this.mCacheReverse.get(m);
        }

        public void put(T item, Marker m) {
            this.mCache.put(item, m);
            this.mCacheReverse.put(m, item);
        }

        public void remove(Marker m) {
            T item = this.mCacheReverse.get(m);
            this.mCacheReverse.remove(m);
            this.mCache.remove(item);
        }
    }

    private class MarkerModifier extends Handler implements IdleHandler {
        private static final int BLANK = 0;
        private final Condition busyCondition;
        private final Lock lock;
        private Queue<AnimationTask> mAnimationTasks;
        private Queue<CreateMarkerTask> mCreateMarkerTasks;
        private boolean mListenerAdded;
        private Queue<CreateMarkerTask> mOnScreenCreateMarkerTasks;
        private Queue<Marker> mOnScreenRemoveMarkerTasks;
        private Queue<Marker> mRemoveMarkerTasks;

        private MarkerModifier() {
            super(Looper.getMainLooper());
            this.lock = new ReentrantLock();
            this.busyCondition = this.lock.newCondition();
            this.mCreateMarkerTasks = new LinkedList();
            this.mOnScreenCreateMarkerTasks = new LinkedList();
            this.mRemoveMarkerTasks = new LinkedList();
            this.mOnScreenRemoveMarkerTasks = new LinkedList();
            this.mAnimationTasks = new LinkedList();
        }

        public void add(boolean priority, CreateMarkerTask c) {
            this.lock.lock();
            sendEmptyMessage(0);
            if (priority) {
                this.mOnScreenCreateMarkerTasks.add(c);
            } else {
                this.mCreateMarkerTasks.add(c);
            }
            this.lock.unlock();
        }

        public void remove(boolean priority, Marker m) {
            this.lock.lock();
            sendEmptyMessage(0);
            if (priority) {
                this.mOnScreenRemoveMarkerTasks.add(m);
            } else {
                this.mRemoveMarkerTasks.add(m);
            }
            this.lock.unlock();
        }

        public void animate(MarkerWithPosition marker, LatLng from, LatLng to) {
            this.lock.lock();
            Queue<AnimationTask> queue = this.mAnimationTasks;
            AnimationTask animationTask = new AnimationTask(marker, from, to);
            queue.add(animationTask);
            this.lock.unlock();
        }

        public void animateThenRemove(MarkerWithPosition marker, LatLng from, LatLng to) {
            this.lock.lock();
            AnimationTask animationTask = new AnimationTask(marker, from, to);
            animationTask.removeOnAnimationComplete(DefaultClusterRenderer.this.mClusterManager.getMarkerManager());
            this.mAnimationTasks.add(animationTask);
            this.lock.unlock();
        }

        public void handleMessage(Message msg) {
            if (!this.mListenerAdded) {
                Looper.myQueue().addIdleHandler(this);
                this.mListenerAdded = true;
            }
            removeMessages(0);
            this.lock.lock();
            int i = 0;
            while (i < 10) {
                try {
                    performNextTask();
                    i++;
                } catch (Throwable th) {
                    this.lock.unlock();
                    throw th;
                }
            }
            if (isBusy() == 0) {
                this.mListenerAdded = false;
                Looper.myQueue().removeIdleHandler(this);
                this.busyCondition.signalAll();
            } else {
                sendEmptyMessageDelayed(0, 10);
            }
            this.lock.unlock();
        }

        private void performNextTask() {
            if (!this.mOnScreenRemoveMarkerTasks.isEmpty()) {
                removeMarker((Marker) this.mOnScreenRemoveMarkerTasks.poll());
            } else if (!this.mAnimationTasks.isEmpty()) {
                ((AnimationTask) this.mAnimationTasks.poll()).perform();
            } else if (!this.mOnScreenCreateMarkerTasks.isEmpty()) {
                ((CreateMarkerTask) this.mOnScreenCreateMarkerTasks.poll()).perform(this);
            } else if (!this.mCreateMarkerTasks.isEmpty()) {
                ((CreateMarkerTask) this.mCreateMarkerTasks.poll()).perform(this);
            } else if (!this.mRemoveMarkerTasks.isEmpty()) {
                removeMarker((Marker) this.mRemoveMarkerTasks.poll());
            }
        }

        private void removeMarker(Marker m) {
            DefaultClusterRenderer.this.mClusterToMarker.remove((Cluster) DefaultClusterRenderer.this.mMarkerToCluster.get(m));
            DefaultClusterRenderer.this.mMarkerCache.remove(m);
            DefaultClusterRenderer.this.mMarkerToCluster.remove(m);
            DefaultClusterRenderer.this.mClusterManager.getMarkerManager().remove(m);
        }

        public boolean isBusy() {
            try {
                this.lock.lock();
                return !this.mCreateMarkerTasks.isEmpty() || !this.mOnScreenCreateMarkerTasks.isEmpty() || !this.mOnScreenRemoveMarkerTasks.isEmpty() || !this.mRemoveMarkerTasks.isEmpty() || !this.mAnimationTasks.isEmpty();
            } finally {
                this.lock.unlock();
            }
        }

        public void waitUntilFree() {
            while (isBusy()) {
                sendEmptyMessage(0);
                this.lock.lock();
                try {
                    if (isBusy()) {
                        this.busyCondition.await();
                    }
                    this.lock.unlock();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (Throwable th) {
                    this.lock.unlock();
                    throw th;
                }
            }
        }

        public boolean queueIdle() {
            sendEmptyMessage(0);
            return true;
        }
    }

    private static class MarkerWithPosition {
        /* access modifiers changed from: private */
        public final Marker marker;
        /* access modifiers changed from: private */
        public LatLng position;

        private MarkerWithPosition(Marker marker2) {
            this.marker = marker2;
            this.position = marker2.getPosition();
        }

        public boolean equals(Object other) {
            if (other instanceof MarkerWithPosition) {
                return this.marker.equals(((MarkerWithPosition) other).marker);
            }
            return false;
        }

        public int hashCode() {
            return this.marker.hashCode();
        }
    }

    private class RenderTask implements Runnable {
        final Set<? extends Cluster<T>> clusters;
        private Runnable mCallback;
        private float mMapZoom;
        private Projection mProjection;
        private SphericalMercatorProjection mSphericalMercatorProjection;

        private RenderTask(Set<? extends Cluster<T>> clusters2) {
            this.clusters = clusters2;
        }

        public void setCallback(Runnable callback) {
            this.mCallback = callback;
        }

        public void setProjection(Projection projection) {
            this.mProjection = projection;
        }

        public void setMapZoom(float zoom) {
            this.mMapZoom = zoom;
            this.mSphericalMercatorProjection = new SphericalMercatorProjection(Math.pow(2.0d, (double) Math.min(zoom, DefaultClusterRenderer.this.mZoom)) * 256.0d);
        }

        public void run() {
            Iterator it;
            List list;
            Object obj;
            if (this.clusters.equals(DefaultClusterRenderer.this.mClusters)) {
                this.mCallback.run();
                return;
            }
            MarkerModifier markerModifier = new MarkerModifier<>();
            float zoom = this.mMapZoom;
            boolean zoomingIn = zoom > DefaultClusterRenderer.this.mZoom;
            float zoomDelta = zoom - DefaultClusterRenderer.this.mZoom;
            Set<MarkerWithPosition> markersToRemove = DefaultClusterRenderer.this.mMarkers;
            LatLngBounds visibleBounds = this.mProjection.getVisibleRegion().latLngBounds;
            List list2 = null;
            if (DefaultClusterRenderer.this.mClusters != null && DefaultClusterRenderer.SHOULD_ANIMATE) {
                list2 = new ArrayList();
                for (Cluster<T> c : DefaultClusterRenderer.this.mClusters) {
                    if (DefaultClusterRenderer.this.shouldRenderAsCluster(c) && visibleBounds.contains(c.getPosition())) {
                        list2.add(this.mSphericalMercatorProjection.toPoint(c.getPosition()));
                    }
                }
            }
            Set<MarkerWithPosition> newMarkers = Collections.newSetFromMap(new ConcurrentHashMap());
            Iterator it2 = this.clusters.iterator();
            while (it2.hasNext()) {
                Cluster<T> c2 = (Cluster) it2.next();
                boolean onScreen = visibleBounds.contains(c2.getPosition());
                if (!zoomingIn || !onScreen || !DefaultClusterRenderer.SHOULD_ANIMATE) {
                    list = list2;
                    it = it2;
                    obj = null;
                    markerModifier.add(onScreen, new CreateMarkerTask(c2, newMarkers, null));
                } else {
                    Point closest = DefaultClusterRenderer.findClosestCluster(list2, this.mSphericalMercatorProjection.toPoint(c2.getPosition()));
                    if (closest == null || !DefaultClusterRenderer.this.mAnimate) {
                        list = list2;
                        it = it2;
                        markerModifier.add(true, new CreateMarkerTask(c2, newMarkers, null));
                    } else {
                        list = list2;
                        markerModifier.add(true, new CreateMarkerTask(c2, newMarkers, this.mSphericalMercatorProjection.toLatLng(closest)));
                        it = it2;
                    }
                    obj = null;
                }
                Object obj2 = obj;
                list2 = list;
                it2 = it;
            }
            List list3 = list2;
            markerModifier.waitUntilFree();
            markersToRemove.removeAll(newMarkers);
            List<Point> newClustersOnScreen = null;
            if (DefaultClusterRenderer.SHOULD_ANIMATE) {
                newClustersOnScreen = new ArrayList<>();
                for (Cluster<T> c3 : this.clusters) {
                    if (DefaultClusterRenderer.this.shouldRenderAsCluster(c3) && visibleBounds.contains(c3.getPosition())) {
                        newClustersOnScreen.add(this.mSphericalMercatorProjection.toPoint(c3.getPosition()));
                    }
                }
            }
            for (MarkerWithPosition marker : markersToRemove) {
                boolean onScreen2 = visibleBounds.contains(marker.position);
                if (zoomingIn || zoomDelta <= -3.0f || !onScreen2 || !DefaultClusterRenderer.SHOULD_ANIMATE) {
                    markerModifier.remove(onScreen2, marker.marker);
                } else {
                    Point closest2 = DefaultClusterRenderer.findClosestCluster(newClustersOnScreen, this.mSphericalMercatorProjection.toPoint(marker.position));
                    if (closest2 == null || !DefaultClusterRenderer.this.mAnimate) {
                        markerModifier.remove(true, marker.marker);
                    } else {
                        markerModifier.animateThenRemove(marker, marker.position, this.mSphericalMercatorProjection.toLatLng(closest2));
                    }
                }
            }
            markerModifier.waitUntilFree();
            DefaultClusterRenderer.this.mMarkers = newMarkers;
            DefaultClusterRenderer.this.mClusters = this.clusters;
            DefaultClusterRenderer.this.mZoom = zoom;
            this.mCallback.run();
        }
    }

    private class ViewModifier extends Handler {
        private static final int RUN_TASK = 0;
        private static final int TASK_FINISHED = 1;
        private RenderTask mNextClusters;
        private boolean mViewModificationInProgress;

        private ViewModifier() {
            this.mViewModificationInProgress = false;
            this.mNextClusters = null;
        }

        public void handleMessage(Message msg) {
            RenderTask renderTask;
            if (msg.what == 1) {
                this.mViewModificationInProgress = false;
                if (this.mNextClusters != null) {
                    sendEmptyMessage(0);
                }
                return;
            }
            removeMessages(0);
            if (!this.mViewModificationInProgress && this.mNextClusters != null) {
                Projection projection = DefaultClusterRenderer.this.mMap.getProjection();
                synchronized (this) {
                    renderTask = this.mNextClusters;
                    this.mNextClusters = null;
                    this.mViewModificationInProgress = true;
                }
                renderTask.setCallback(new Runnable() {
                    public void run() {
                        ViewModifier.this.sendEmptyMessage(1);
                    }
                });
                renderTask.setProjection(projection);
                renderTask.setMapZoom(DefaultClusterRenderer.this.mMap.getCameraPosition().zoom);
                new Thread(renderTask).start();
            }
        }

        public void queue(Set<? extends Cluster<T>> clusters) {
            synchronized (this) {
                this.mNextClusters = new RenderTask<>(clusters);
            }
            sendEmptyMessage(0);
        }
    }

    public DefaultClusterRenderer(Context context, GoogleMap map, ClusterManager<T> clusterManager) {
        this.mMap = map;
        this.mAnimate = true;
        this.mDensity = context.getResources().getDisplayMetrics().density;
        this.mIconGenerator = new IconGenerator(context);
        this.mIconGenerator.setContentView(makeSquareTextView(context));
        this.mIconGenerator.setTextAppearance(C1160R.style.amu_ClusterIcon_TextAppearance);
        this.mIconGenerator.setBackground(makeClusterBackground());
        this.mClusterManager = clusterManager;
    }

    public void onAdd() {
        this.mClusterManager.getMarkerCollection().setOnMarkerClickListener(new OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                return DefaultClusterRenderer.this.mItemClickListener != null && DefaultClusterRenderer.this.mItemClickListener.onClusterItemClick((ClusterItem) DefaultClusterRenderer.this.mMarkerCache.get(marker));
            }
        });
        this.mClusterManager.getMarkerCollection().setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
            public void onInfoWindowClick(Marker marker) {
                if (DefaultClusterRenderer.this.mItemInfoWindowClickListener != null) {
                    DefaultClusterRenderer.this.mItemInfoWindowClickListener.onClusterItemInfoWindowClick((ClusterItem) DefaultClusterRenderer.this.mMarkerCache.get(marker));
                }
            }
        });
        this.mClusterManager.getClusterMarkerCollection().setOnMarkerClickListener(new OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                return DefaultClusterRenderer.this.mClickListener != null && DefaultClusterRenderer.this.mClickListener.onClusterClick((Cluster) DefaultClusterRenderer.this.mMarkerToCluster.get(marker));
            }
        });
        this.mClusterManager.getClusterMarkerCollection().setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
            public void onInfoWindowClick(Marker marker) {
                if (DefaultClusterRenderer.this.mInfoWindowClickListener != null) {
                    DefaultClusterRenderer.this.mInfoWindowClickListener.onClusterInfoWindowClick((Cluster) DefaultClusterRenderer.this.mMarkerToCluster.get(marker));
                }
            }
        });
    }

    public void onRemove() {
        this.mClusterManager.getMarkerCollection().setOnMarkerClickListener(null);
        this.mClusterManager.getMarkerCollection().setOnInfoWindowClickListener(null);
        this.mClusterManager.getClusterMarkerCollection().setOnMarkerClickListener(null);
        this.mClusterManager.getClusterMarkerCollection().setOnInfoWindowClickListener(null);
    }

    private LayerDrawable makeClusterBackground() {
        this.mColoredCircleBackground = new ShapeDrawable(new OvalShape());
        ShapeDrawable outline = new ShapeDrawable(new OvalShape());
        outline.getPaint().setColor(-2130706433);
        LayerDrawable background = new LayerDrawable(new Drawable[]{outline, this.mColoredCircleBackground});
        int strokeWidth = (int) (this.mDensity * 3.0f);
        background.setLayerInset(1, strokeWidth, strokeWidth, strokeWidth, strokeWidth);
        return background;
    }

    private SquareTextView makeSquareTextView(Context context) {
        SquareTextView squareTextView = new SquareTextView(context);
        squareTextView.setLayoutParams(new LayoutParams(-2, -2));
        squareTextView.setId(C1160R.C1163id.amu_text);
        int twelveDpi = (int) (this.mDensity * 12.0f);
        squareTextView.setPadding(twelveDpi, twelveDpi, twelveDpi, twelveDpi);
        return squareTextView;
    }

    /* access modifiers changed from: protected */
    public int getColor(int clusterSize) {
        float size = Math.min((float) clusterSize, 300.0f);
        return Color.HSVToColor(new float[]{(((300.0f - size) * (300.0f - size)) / 90000.0f) * 220.0f, 1.0f, 0.6f});
    }

    /* access modifiers changed from: protected */
    public String getClusterText(int bucket) {
        if (bucket < BUCKETS[0]) {
            return String.valueOf(bucket);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(bucket));
        sb.append("+");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public int getBucket(Cluster<T> cluster) {
        int size = cluster.getSize();
        if (size <= BUCKETS[0]) {
            return size;
        }
        int i = 0;
        while (true) {
            int[] iArr = BUCKETS;
            if (i >= iArr.length - 1) {
                return iArr[iArr.length - 1];
            }
            if (size < iArr[i + 1]) {
                return iArr[i];
            }
            i++;
        }
    }

    public int getMinClusterSize() {
        return this.mMinClusterSize;
    }

    public void setMinClusterSize(int minClusterSize) {
        this.mMinClusterSize = minClusterSize;
    }

    /* access modifiers changed from: protected */
    public boolean shouldRenderAsCluster(Cluster<T> cluster) {
        return cluster.getSize() > this.mMinClusterSize;
    }

    public void onClustersChanged(Set<? extends Cluster<T>> clusters) {
        this.mViewModifier.queue(clusters);
    }

    public void setOnClusterClickListener(OnClusterClickListener<T> listener) {
        this.mClickListener = listener;
    }

    public void setOnClusterInfoWindowClickListener(OnClusterInfoWindowClickListener<T> listener) {
        this.mInfoWindowClickListener = listener;
    }

    public void setOnClusterItemClickListener(OnClusterItemClickListener<T> listener) {
        this.mItemClickListener = listener;
    }

    public void setOnClusterItemInfoWindowClickListener(OnClusterItemInfoWindowClickListener<T> listener) {
        this.mItemInfoWindowClickListener = listener;
    }

    public void setAnimation(boolean animate) {
        this.mAnimate = animate;
    }

    private static double distanceSquared(Point a, Point b) {
        return ((a.f89x - b.f89x) * (a.f89x - b.f89x)) + ((a.f90y - b.f90y) * (a.f90y - b.f90y));
    }

    /* access modifiers changed from: private */
    public static Point findClosestCluster(List<Point> markers, Point point) {
        if (markers == null || markers.isEmpty()) {
            return null;
        }
        double minDistSquared = 10000.0d;
        Point closest = null;
        for (Point candidate : markers) {
            double dist = distanceSquared(candidate, point);
            if (dist < minDistSquared) {
                closest = candidate;
                minDistSquared = dist;
            }
        }
        return closest;
    }

    /* access modifiers changed from: protected */
    public void onBeforeClusterItemRendered(T t, MarkerOptions markerOptions) {
    }

    /* access modifiers changed from: protected */
    public void onBeforeClusterRendered(Cluster<T> cluster, MarkerOptions markerOptions) {
        int bucket = getBucket(cluster);
        BitmapDescriptor descriptor = (BitmapDescriptor) this.mIcons.get(bucket);
        if (descriptor == null) {
            this.mColoredCircleBackground.getPaint().setColor(getColor(bucket));
            descriptor = BitmapDescriptorFactory.fromBitmap(this.mIconGenerator.makeIcon(getClusterText(bucket)));
            this.mIcons.put(bucket, descriptor);
        }
        markerOptions.icon(descriptor);
    }

    /* access modifiers changed from: protected */
    public void onClusterRendered(Cluster<T> cluster, Marker marker) {
    }

    /* access modifiers changed from: protected */
    public void onClusterItemRendered(T t, Marker marker) {
    }

    public Marker getMarker(T clusterItem) {
        return this.mMarkerCache.get(clusterItem);
    }

    public T getClusterItem(Marker marker) {
        return (ClusterItem) this.mMarkerCache.get(marker);
    }

    public Marker getMarker(Cluster<T> cluster) {
        return (Marker) this.mClusterToMarker.get(cluster);
    }

    public Cluster<T> getCluster(Marker marker) {
        return (Cluster) this.mMarkerToCluster.get(marker);
    }
}
