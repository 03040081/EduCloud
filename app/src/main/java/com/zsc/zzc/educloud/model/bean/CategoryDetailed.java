package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;

/**
 * Created by 21191 on 2018/2/13.
 */

public class CategoryDetailed implements Serializable {
    private int cgdetailedId;
    private String cgdetailedName;

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    private int majorId;
    private int categoryId;

    public CategoryDetailed(int cgdetailedId, String cgdetailedName) {
        this.cgdetailedId = cgdetailedId;
        this.cgdetailedName = cgdetailedName;
    }

    public CategoryDetailed() {
    }

    public int getCgdetailedId() {
        return cgdetailedId;
    }

    public void setCgdetailedId(int cgdetailedId) {
        this.cgdetailedId = cgdetailedId;
    }

    public String getCgdetailedName() {
        return cgdetailedName;
    }

    public void setCgdetailedName(String cgdetailedName) {
        this.cgdetailedName = cgdetailedName;
    }
}
