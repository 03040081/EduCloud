package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

public class User extends RealmObject implements Serializable {
    private int userId;
    private String userName;
    private String userAccount;
    @Ignore
    private String userPass;
    private int locked=0;
    private RoleInfo role;
    private String faceImg;
    private String token;

    public User(){}



    public User(int userId, String userName, String userAccount, String userPass, int locked, RoleInfo role,
                String faceImg,String token) {
        this.userId = userId;
        this.userName = userName;
        this.userAccount = userAccount;
        this.userPass = userPass;
        this.locked = locked;
        this.role = role;
        this.faceImg = faceImg;
        this.token=token;
    }



    public User(String userName, String userAccount, String userPass, int locked, RoleInfo role, String faceImg,String token) {
        this.userName = userName;
        this.userAccount = userAccount;
        this.userPass = userPass;
        this.locked = locked;
        this.role = role;
        this.faceImg = faceImg;
        this.token=token;
    }
    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", userAccount=" + userAccount + ", userPass="
                + userPass + ", locked=" + locked + ", role=" + role + ", faceImg=" + faceImg + "]";
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserAccount() {
        return userAccount;
    }
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    public String getUserPass() {
        return userPass;
    }
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    public int getLocked() {
        return locked;
    }
    public void setLocked(int locked) {
        this.locked = locked;
    }
    public RoleInfo getRole() {
        return role;
    }
    public void setRole(RoleInfo role) {
        this.role = role;
    }
    public String getFaceImg() {
        return faceImg;
    }
    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }
}
