package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 21191 on 2018/4/25.
 */

public class College implements Serializable {

    private String id;

    private String name;

    private List<Profession> listProfessions;

    public College(){}

    public College(String id, String name, List<Profession> listProfessions) {
        this.id = id;
        this.name = name;
        this.listProfessions = listProfessions;
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

    public List<Profession> getListProfessions() {
        return listProfessions;
    }

    public void setListProfessions(List<Profession> listProfessions) {
        this.listProfessions = listProfessions;
    }
}
