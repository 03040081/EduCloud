package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 21191 on 2018/2/13.
 */

public class Category implements Serializable {
    private int categoryId;
    private String categoryName;
    private List<CategoryDetailed> categoryDetailedList;

    private List<VideoInfor> listVideoInfo;

    public Category(int categoryId, String categoryName, List<CategoryDetailed> categoryDetailedList,
                    List<VideoInfor> listVideoInfo) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDetailedList = categoryDetailedList;
        this.listVideoInfo = listVideoInfo;
    }

    public List<CategoryDetailed> getCategoryDetailedList() {
        return categoryDetailedList;
    }

    public void setCategoryDetailedList(List<CategoryDetailed> categoryDetailedList) {
        this.categoryDetailedList = categoryDetailedList;
    }

    public Category() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<VideoInfor> getListVideoInfo() {
        return listVideoInfo;
    }

    public void setListVideoInfo(List<VideoInfor> listVideoInfo) {
        this.listVideoInfo = listVideoInfo;
    }
}
