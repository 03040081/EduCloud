package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 21191 on 2018/4/25.
 */

public class Section implements Serializable {

    private String id;

    private String courseId;

    private String title;

    private Integer index;

    private String subTitle;

    private Integer subIndex;

    private List<Section> listChapter;

    public Section(){}

    public Section(String id, String courseId, String title, Integer index, String subTitle, Integer subIndex, List<Section> listChapter) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.index = index;
        this.subTitle = subTitle;
        this.subIndex = subIndex;
        this.listChapter = listChapter;
    }

    public List<Section> getListChapter() {
        return listChapter;
    }

    public void setListChapter(List<Section> listChapter) {
        this.listChapter = listChapter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getSubIndex() {
        return subIndex;
    }

    public void setSubIndex(Integer subIndex) {
        this.subIndex = subIndex;
    }
}
