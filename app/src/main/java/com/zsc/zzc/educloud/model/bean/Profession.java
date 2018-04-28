package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by 21191 on 2018/4/25.
 */

public class Profession extends RealmObject implements Serializable {

    private String id;

    private String name;

    private String collegeId;

    public Profession(){}

    public Profession(String name, String collegeId) {
        this.name = name;
        this.collegeId = collegeId;
    }

    public Profession(String id, String name, String collegeId) {
        this.id = id;
        this.name = name;
        this.collegeId = collegeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }
}
