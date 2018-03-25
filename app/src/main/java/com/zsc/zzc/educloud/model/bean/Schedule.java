package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;


public class Schedule implements Serializable {
    private int scheduleId;
    private int userId;
    private VideoInfor videoInfor;

    public Schedule(int scheduleId, int userId, VideoInfor videoInfor) {
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.videoInfor = videoInfor;
    }

    public Schedule() {
    }
    public int getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public VideoInfor getVideoInfor() {
        return videoInfor;
    }
    public void setVideoInfor(VideoInfor videoInfor) {
        this.videoInfor = videoInfor;
    }
}
