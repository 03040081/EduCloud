package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 21191 on 2018/4/25.
 */

public class Category implements Serializable{

    private String id;

    private String name;

    private List<Tag> listTags;

    public Category(){}

    public Category(String id, String name, List<Tag> listTags) {
        this.id = id;
        this.name = name;
        this.listTags = listTags;
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

    public List<Tag> getListTags() {
        return listTags;
    }

    public void setListTags(List<Tag> listTags) {
        this.listTags = listTags;
    }
}
