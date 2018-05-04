package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;

/**
 * Created by 21191 on 2018/5/4.
 */

public class Learn implements Serializable {

    private String id;

    private String userId;

    private String courseId;

    private Integer state;

    private String createdTime;

    private Course course;

    public Learn(){}

    public Learn(String id, String userId, String courseId, Integer state, String createdTime, Course course) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.state = state;
        this.createdTime = createdTime;
        this.course = course;
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
