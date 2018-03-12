package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.Rank;
import com.zsc.zzc.educloud.model.bean.Record;
import com.zsc.zzc.educloud.model.bean.User;
import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.model.db.RealmHelper;
import com.zsc.zzc.educloud.presenter.contract.MineContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Description: CollectionPresenter
 * Creator: yxc
 * date: 2016/9/29 12:19
 */
public class MinePresenter extends RxPresenter<MineContract.View> implements MineContract.Presenter {
    public static final int maxSize = 30;

    @Inject
    public MinePresenter() {
    }

    @Override
    public void getHistoryData() {
        List<Record> records = RealmHelper.getInstance().getRecordList();
        //List<VideoType> list = new ArrayList<>();
        //VideoType videoType;
        List<VideoInfor> list=new ArrayList<>();
        VideoInfor videoInfor=null;
        int maxlinth = records.size() <= 3 ? records.size() : 3;
        for (int i = 0; i < maxlinth; i++) {
            Record record = records.get(i);
            /*videoType = new VideoType();
            videoType.title = record.title;
            videoType.pic = record.pic;
            videoType.dataId = record.getId();
            list.add(videoType);*/
            videoInfor=new VideoInfor();
            videoInfor.setVideoId(record.getVideoId());
            videoInfor.setVideoTile(record.getVideoTile());
            videoInfor.setPicUrl(record.getPicUrl());
            videoInfor.setPrices(record.getPrices());
            Rank rank=new Rank();
            rank.setRankName(record.getRankName());
            videoInfor.setRank(rank);
            videoInfor.setStudySum(record.getStudySum());
            list.add(videoInfor);
        }
        User user=getUserInfo();
        mView.showContent(list,user);
    }

    @Override
    public void delAllHistory() {
        RealmHelper.getInstance().deleteAllRecord();
    }

    public User getUserInfo() {
        User user=RealmHelper.getInstance().getUserInfo();
        return user;
    }

}
