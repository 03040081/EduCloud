package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 21191 on 2018/2/13.
 */

public class VideoInfor implements Serializable {
    private int videoId;
    private String videoTile;
    private String videoDiscript;
    private int studySum;
    private double prices;
    private int recommend;
    private String picUrl;

    private Rank rank;
    private Major major;
    private Category category;
    private CategoryDetailed categoryDetailed;

    private List<VideoChapter> listChapter;
    private List<VideoAssess> listAssess;
    private List<Forum> listForum;

    public VideoInfor(){}


    public VideoInfor(int videoId, String videoTile, String videoDiscript, int studySum, double prices, int recommend,
                     String picUrl, Rank rank, Major major, Category category, CategoryDetailed categoryDetailed,
                     List<VideoChapter> listChapter, List<VideoAssess> listAssess, List<Forum> listForum) {
        this.videoId = videoId;
        this.videoTile = videoTile;
        this.videoDiscript = videoDiscript;
        this.studySum = studySum;
        this.prices = prices;
        this.recommend = recommend;
        this.picUrl = picUrl;
        this.rank = rank;
        this.major = major;
        this.category = category;
        this.categoryDetailed = categoryDetailed;
        this.listChapter = listChapter;
        this.listAssess = listAssess;
        this.listForum = listForum;
    }


    public String getPicUrl() {
        return picUrl;
    }


    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }





    public Major getMajor() {
        return major;
    }



    public void setMajor(Major major) {
        this.major = major;
    }



    public List<VideoChapter> getListChapter() {
        return listChapter;
    }

    public void setListChapter(List<VideoChapter> listChapter) {
        this.listChapter = listChapter;
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

    public String getVideoDiscript() {
        return videoDiscript;
    }

    public void setVideoDiscript(String videoDiscript) {
        this.videoDiscript = videoDiscript;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public CategoryDetailed getCategoryDetailed() {
        return categoryDetailed;
    }

    public void setCategoryDetailed(CategoryDetailed categoryDetailed) {
        this.categoryDetailed = categoryDetailed;
    }

    public List<VideoAssess> getListAssess() {
        return listAssess;
    }

    public void setListAssess(List<VideoAssess> listAssess) {
        this.listAssess = listAssess;
    }

    public List<Forum> getListForum() {
        return listForum;
    }

    public void setListForum(List<Forum> listForum) {
        this.listForum = listForum;
    }
}
