package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by 21191 on 2018/4/25.
 */

public class Teacher extends RealmObject implements Serializable {

    private String id;

    private String userId;

    private String collegeId;

    private String name;

    private String grade;

    private String intro;

    private String avatar;

    private String createdTime;

    public Teacher(){}

    public Teacher(String id, String userId, String collegeId, String name, String grade, String intro, String avatar, String createdTime) {
        this.id = id;
        this.userId = userId;
        this.collegeId = collegeId;
        this.name = name;
        this.grade = grade;
        this.intro = intro;
        this.avatar = avatar;
        this.createdTime = createdTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
