package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;


public class Collection extends RealmObject implements Serializable {
    int videoId;
    long time;
    public String videoTile;
    public String picUrl;
    public int studySum;
    public double prices;
    public String rankName;
    //public String airTime;
    // public String score;

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public void setTime(long time){
        this.time=time;
    }
    public long getTime(){
        return this.time;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getVideoTile() {
        return videoTile;
    }

    public void setVideoTile(String videoTile) {
        this.videoTile = videoTile;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getStudySum() {
        return studySum;
    }

    public void setStudySum(int studySum) {
        this.studySum = studySum;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

}
