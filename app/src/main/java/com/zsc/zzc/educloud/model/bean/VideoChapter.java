package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;
import java.util.List;

public class VideoChapter implements Serializable {
    private int chapterId;
    private int videoId;
    private String chapterName;

    private List<ChapterDetailed> listChapterDetailed;

    public VideoChapter() {
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public VideoChapter(int chapterId, int videoId, String chapterName, List<ChapterDetailed> listChapterDetailed) {
        this.chapterId = chapterId;
        this.videoId = videoId;
        this.chapterName = chapterName;
        this.listChapterDetailed = listChapterDetailed;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public List<ChapterDetailed> getListChapterDetailed() {
        return listChapterDetailed;
    }

    public void setListChapterDetailed(List<ChapterDetailed> listChapterDetailed) {
        this.listChapterDetailed = listChapterDetailed;
    }

}
