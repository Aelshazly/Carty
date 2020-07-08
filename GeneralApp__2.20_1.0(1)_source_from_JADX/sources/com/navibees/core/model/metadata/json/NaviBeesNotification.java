package com.navibees.core.model.metadata.json;

public class NaviBeesNotification {
    private String icon;
    private String message;
    private String photo;
    private String title;
    private String video;

    public String getIcon() {
        return this.icon;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getTitle() {
        return this.title;
    }

    public String getVideo() {
        return this.video;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setPhoto(String str) {
        this.photo = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setVideo(String str) {
        this.video = str;
    }
}
