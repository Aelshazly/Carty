package androidx.media.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.support.p000v4.media.session.MediaSessionCompat.Token;
import android.widget.RemoteViews;
import androidx.core.app.BundleCompat;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat.Action;
import androidx.core.app.NotificationCompat.Builder;
import androidx.core.app.NotificationCompat.Style;
import androidx.media.C1043R;

public class NotificationCompat {

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            if (VERSION.SDK_INT >= 24) {
                builder.getBuilder().setStyle(fillInMediaStyle(new android.app.Notification.DecoratedMediaCustomViewStyle()));
            } else {
                super.apply(builder);
            }
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            boolean createCustomContent = true;
            boolean hasContentView = this.mBuilder.getContentView() != null;
            if (VERSION.SDK_INT >= 21) {
                if (!hasContentView && this.mBuilder.getBigContentView() == null) {
                    createCustomContent = false;
                }
                if (createCustomContent) {
                    RemoteViews contentView = generateContentView();
                    if (hasContentView) {
                        buildIntoRemoteViews(contentView, this.mBuilder.getContentView());
                    }
                    setBackgroundColor(contentView);
                    return contentView;
                }
            } else {
                RemoteViews contentView2 = generateContentView();
                if (hasContentView) {
                    buildIntoRemoteViews(contentView2, this.mBuilder.getContentView());
                    return contentView2;
                }
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public int getContentViewLayoutResource() {
            if (this.mBuilder.getContentView() != null) {
                return C1043R.layout.notification_template_media_custom;
            }
            return super.getContentViewLayoutResource();
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
            RemoteViews innerView;
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getBigContentView() != null) {
                innerView = this.mBuilder.getBigContentView();
            } else {
                innerView = this.mBuilder.getContentView();
            }
            if (innerView == null) {
                return null;
            }
            RemoteViews bigContentView = generateBigContentView();
            buildIntoRemoteViews(bigContentView, innerView);
            if (VERSION.SDK_INT >= 21) {
                setBackgroundColor(bigContentView);
            }
            return bigContentView;
        }

        /* access modifiers changed from: 0000 */
        public int getBigContentViewLayoutResource(int actionCount) {
            return actionCount <= 3 ? C1043R.layout.notification_template_big_media_narrow_custom : C1043R.layout.notification_template_big_media_custom;
        }

        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor builder) {
            RemoteViews innerView;
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getHeadsUpContentView() != null) {
                innerView = this.mBuilder.getHeadsUpContentView();
            } else {
                innerView = this.mBuilder.getContentView();
            }
            if (innerView == null) {
                return null;
            }
            RemoteViews headsUpContentView = generateBigContentView();
            buildIntoRemoteViews(headsUpContentView, innerView);
            if (VERSION.SDK_INT >= 21) {
                setBackgroundColor(headsUpContentView);
            }
            return headsUpContentView;
        }

        private void setBackgroundColor(RemoteViews views) {
            int color;
            if (this.mBuilder.getColor() != 0) {
                color = this.mBuilder.getColor();
            } else {
                color = this.mBuilder.mContext.getResources().getColor(C1043R.C1044color.notification_material_background_media_default_color);
            }
            views.setInt(C1043R.C1046id.status_bar_latest_event_content, "setBackgroundColor", color);
        }
    }

    public static class MediaStyle extends Style {
        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        int[] mActionsToShowInCompact = null;
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        Token mToken;

        public static Token getMediaSession(Notification notification) {
            Bundle extras = androidx.core.app.NotificationCompat.getExtras(notification);
            if (extras != null) {
                int i = VERSION.SDK_INT;
                String str = androidx.core.app.NotificationCompat.EXTRA_MEDIA_SESSION;
                if (i >= 21) {
                    Object tokenInner = extras.getParcelable(str);
                    if (tokenInner != null) {
                        return Token.fromToken(tokenInner);
                    }
                } else {
                    IBinder tokenInner2 = BundleCompat.getBinder(extras, str);
                    if (tokenInner2 != null) {
                        Parcel p = Parcel.obtain();
                        p.writeStrongBinder(tokenInner2);
                        p.setDataPosition(0);
                        Token token = (Token) Token.CREATOR.createFromParcel(p);
                        p.recycle();
                        return token;
                    }
                }
            }
            return null;
        }

        public MediaStyle() {
        }

        public MediaStyle(Builder builder) {
            setBuilder(builder);
        }

        public MediaStyle setShowActionsInCompactView(int... actions) {
            this.mActionsToShowInCompact = actions;
            return this;
        }

        public MediaStyle setMediaSession(Token token) {
            this.mToken = token;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean show) {
            if (VERSION.SDK_INT < 21) {
                this.mShowCancelButton = show;
            }
            return this;
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            this.mCancelButtonIntent = pendingIntent;
            return this;
        }

        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            if (VERSION.SDK_INT >= 21) {
                builder.getBuilder().setStyle(fillInMediaStyle(new android.app.Notification.MediaStyle()));
            } else if (this.mShowCancelButton) {
                builder.getBuilder().setOngoing(true);
            }
        }

        /* access modifiers changed from: 0000 */
        public android.app.Notification.MediaStyle fillInMediaStyle(android.app.Notification.MediaStyle style) {
            int[] iArr = this.mActionsToShowInCompact;
            if (iArr != null) {
                style.setShowActionsInCompactView(iArr);
            }
            Token token = this.mToken;
            if (token != null) {
                style.setMediaSession((MediaSession.Token) token.getToken());
            }
            return style;
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
            if (VERSION.SDK_INT >= 21) {
                return null;
            }
            return generateContentView();
        }

        /* access modifiers changed from: 0000 */
        public RemoteViews generateContentView() {
            int numActionsInCompact;
            RemoteViews view = applyStandardTemplate(false, getContentViewLayoutResource(), true);
            int numActions = this.mBuilder.mActions.size();
            int[] iArr = this.mActionsToShowInCompact;
            if (iArr == null) {
                numActionsInCompact = 0;
            } else {
                numActionsInCompact = Math.min(iArr.length, 3);
            }
            view.removeAllViews(C1043R.C1046id.media_actions);
            if (numActionsInCompact > 0) {
                int i = 0;
                while (i < numActionsInCompact) {
                    if (i < numActions) {
                        view.addView(C1043R.C1046id.media_actions, generateMediaActionButton((Action) this.mBuilder.mActions.get(this.mActionsToShowInCompact[i])));
                        i++;
                    } else {
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[]{Integer.valueOf(i), Integer.valueOf(numActions - 1)}));
                    }
                }
            }
            if (this.mShowCancelButton) {
                view.setViewVisibility(C1043R.C1046id.end_padder, 8);
                view.setViewVisibility(C1043R.C1046id.cancel_action, 0);
                view.setOnClickPendingIntent(C1043R.C1046id.cancel_action, this.mCancelButtonIntent);
                view.setInt(C1043R.C1046id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(C1043R.integer.cancel_button_image_alpha));
            } else {
                view.setViewVisibility(C1043R.C1046id.end_padder, 0);
                view.setViewVisibility(C1043R.C1046id.cancel_action, 8);
            }
            return view;
        }

        private RemoteViews generateMediaActionButton(Action action) {
            boolean tombstone = action.getActionIntent() == null;
            RemoteViews button = new RemoteViews(this.mBuilder.mContext.getPackageName(), C1043R.layout.notification_media_action);
            button.setImageViewResource(C1043R.C1046id.action0, action.getIcon());
            if (!tombstone) {
                button.setOnClickPendingIntent(C1043R.C1046id.action0, action.getActionIntent());
            }
            if (VERSION.SDK_INT >= 15) {
                button.setContentDescription(C1043R.C1046id.action0, action.getTitle());
            }
            return button;
        }

        /* access modifiers changed from: 0000 */
        public int getContentViewLayoutResource() {
            return C1043R.layout.notification_template_media;
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
            if (VERSION.SDK_INT >= 21) {
                return null;
            }
            return generateBigContentView();
        }

        /* access modifiers changed from: 0000 */
        public RemoteViews generateBigContentView() {
            int actionCount = Math.min(this.mBuilder.mActions.size(), 5);
            RemoteViews big = applyStandardTemplate(false, getBigContentViewLayoutResource(actionCount), false);
            big.removeAllViews(C1043R.C1046id.media_actions);
            if (actionCount > 0) {
                for (int i = 0; i < actionCount; i++) {
                    big.addView(C1043R.C1046id.media_actions, generateMediaActionButton((Action) this.mBuilder.mActions.get(i)));
                }
            }
            if (this.mShowCancelButton != 0) {
                big.setViewVisibility(C1043R.C1046id.cancel_action, 0);
                big.setInt(C1043R.C1046id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(C1043R.integer.cancel_button_image_alpha));
                big.setOnClickPendingIntent(C1043R.C1046id.cancel_action, this.mCancelButtonIntent);
            } else {
                big.setViewVisibility(C1043R.C1046id.cancel_action, 8);
            }
            return big;
        }

        /* access modifiers changed from: 0000 */
        public int getBigContentViewLayoutResource(int actionCount) {
            return actionCount <= 3 ? C1043R.layout.notification_template_big_media_narrow : C1043R.layout.notification_template_big_media;
        }
    }

    private NotificationCompat() {
    }
}
