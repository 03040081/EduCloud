package com.zsc.zzc.educloud.model.bean;

/**
 * Created by 21191 on 2018/3/4.
 */

public class ResponseMsg {
    private boolean flag;
    private String msg;

    public ResponseMsg(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
