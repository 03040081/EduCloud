package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;
import java.util.List;


public class Rank implements Serializable {
    private int rankId;
    private String rankName;
    private List<VideoInfor> listVideo;

    public Rank(){}

    public Rank(int rankId, String rankName, List<VideoInfor> listVideo) {
        this.rankId = rankId;
        this.rankName = rankName;
        this.listVideo = listVideo;
    }


    public int getRankId() {
        return rankId;
    }
    public void setRankId(int rankId) {
        this.rankId = rankId;
    }
    public String getRankName() {
        return rankName;
    }
    public void setRankName(String rankName) {
        this.rankName = rankName;
    }
    public List<VideoInfor> getListVideo() {
        return listVideo;
    }
    public void setListVideo(List<VideoInfor> listVideo) {
        this.listVideo = listVideo;
    }
}
