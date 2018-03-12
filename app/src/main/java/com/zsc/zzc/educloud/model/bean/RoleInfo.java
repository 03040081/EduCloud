package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by 21191 on 2018/2/13.
 */

public class RoleInfo extends RealmObject implements Serializable {
    private int roleId;
    private String roleName;

    public RoleInfo(){}

    public RoleInfo(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
