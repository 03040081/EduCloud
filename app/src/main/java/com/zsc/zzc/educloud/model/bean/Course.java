package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 21191 on 2018/4/25.
 */

public class Course implements Serializable{

    private String id;

    private String name;

    private String teacherId;

    private String professionId;

    private String tagId;

    private String icon;

    private Integer type;

    private String intro;

    private String fitPeople;

    private List<Section> listSections;

    private Profession profession;

    private Teacher teacher;

    public Course(){}

    public Course(String id, String name, String teacherId, String professionId, String tagId, String icon, Integer type, String intro, String fitPeople, List<Section> listSections) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
        this.professionId = professionId;
        this.tagId = tagId;
        this.icon = icon;
        this.type = type;
        this.intro = intro;
        this.fitPeople = fitPeople;
        this.listSections = listSections;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getFitPeople() {
        return fitPeople;
    }

    public void setFitPeople(String fitPeople) {
        this.fitPeople = fitPeople;
    }

    public List<Section> getListSections() {
        return listSections;
    }

    public void setListSections(List<Section> listSections) {
        this.listSections = listSections;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
