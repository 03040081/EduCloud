package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;

/**
 * Created by 21191 on 2018/4/25.
 */

public class Comment implements Serializable {

    private String id;

    private String sectionId;

    private String userId;

    private String content;

    private String createdTime;

    private User user;

    public Comment(){}

    public Comment(String id, String sectionId, String userId, String content, String createdTime, User user) {
        this.id = id;
        this.sectionId = sectionId;
        this.userId = userId;
        this.content = content;
        this.createdTime = createdTime;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
