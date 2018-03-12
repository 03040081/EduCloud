package com.zsc.zzc.educloud.model.bean;

import java.io.Serializable;

/**
 * Created by 21191 on 2018/2/13.
 */

public class OrderInfo implements Serializable {
    private int orderId;
    private int userId;
    private VideoInfor videoInfo;
    private int orderState;

    public OrderInfo(int orderId, int userId, VideoInfor videoInfo, int orderState) {
        this.orderId = orderId;
        this.userId = userId;
        this.videoInfo = videoInfo;
        this.orderState = orderState;
    }
    public OrderInfo() {
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public VideoInfor getVideoInfo() {
        return videoInfo;
    }
    public void setVideoInfo(VideoInfor videoInfo) {
        this.videoInfo = videoInfo;
    }
    public VideoInfor getVideoId() {
        return videoInfo;
    }
    public void setVideoId(VideoInfor videoInfo) {
        this.videoInfo = videoInfo;
    }
    public int getOrderState() {
        return orderState;
    }
    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

}
