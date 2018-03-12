package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;

/**
 * Created by 21191 on 2018/2/13.
 */

public class Forum implements Serializable {
    private int forumId;
    private int videoId;
    private String contents;

    private User user;

    public Forum(int forumId, int videoId, String contents, User user) {
        this.forumId = forumId;
        this.videoId = videoId;
        this.contents = contents;
        this.user = user;
    }

    public Forum() {
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
