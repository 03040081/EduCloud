package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;

/**
 * Created by 21191 on 2018/2/13.
 */

public class VideoAssess implements Serializable {

    private int assessId;
    private int videoId;
    private float score;
    private String contents;

    private User user;

    public VideoAssess() {
    }

    public VideoAssess(int assessId, int videoId, float score, String contents, User user) {
        this.assessId = assessId;
        this.videoId = videoId;
        this.score = score;
        this.contents = contents;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAssessId() {
        return assessId;
    }


    public void setAssessId(int assessId) {
        this.assessId = assessId;
    }


    public int getVideoId() {
        return videoId;
    }


    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }


    public float getScore() {
        return score;
    }


    public void setScore(float score) {
        this.score = score;
    }


    public String getContents() {
        return contents;
    }


    public void setContents(String contents) {
        this.contents = contents;
    }

}
