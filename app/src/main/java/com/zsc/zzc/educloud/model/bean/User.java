package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by 21191 on 2018/4/25.
 */

public class User extends RealmObject implements Serializable {

    private String id;

    private String username;

    private String avatar;

    private String birthday;

    private Boolean gender;

    private String email;

    private String phonenum;

    private String password;

    private Integer identity;

    private String createdTime;

    public User(){}

    public User(String id, String username, String avatar, String birthday, Boolean gender, String email, String phonenum, String password, Integer identity, String createdTime) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.phonenum = phonenum;
        this.password = password;
        this.identity = identity;
        this.createdTime = createdTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
