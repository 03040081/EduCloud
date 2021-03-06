package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.Collection;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.model.bean.Profession;
import com.zsc.zzc.educloud.model.bean.Record;
import com.zsc.zzc.educloud.model.bean.Teacher;
import com.zsc.zzc.educloud.model.db.RealmHelper;
import com.zsc.zzc.educloud.presenter.contract.CollectionContract;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CollectionPresenter extends RxPresenter<CollectionContract.View> implements CollectionContract.Presenter {
    int type = 0;//收藏:0; 历史:1:

    @Inject
    public CollectionPresenter() {
    }

    @Override
    public void getData(int type) {
        this.type = type;
        if (type == 0) {
            getCollectData();
        } else {
            getRecordData();
        }
    }

    @Override
    public void getCollectData() {
        List<Collection> collections = RealmHelper.getInstance().getCollectionList();
        List<Course> list = new ArrayList<>();
        //VideoType videoType;
        Course videoInfor=null;
        for (Collection collection : collections) {
            /*videoType = new VideoType();
            videoType.title = collection.title;
            videoType.pic = collection.pic;
            videoType.dataId = collection.getId();
            videoType.score = collection.getScore();
            videoType.airTime = collection.getAirTime();
            list.add(videoType);*/
            videoInfor=new Course();
            videoInfor.setId(collection.getId());
            videoInfor.setName(collection.getName());
            videoInfor.setIcon(collection.getIcon());
            videoInfor.setIntro(collection.getIntro());
            Profession profession=new Profession();
            profession.setName(collection.getProfession().getName());
            videoInfor.setProfession(profession);
            Teacher teacher=new Teacher();
            teacher.setName(collection.getTeacher().getName());
            videoInfor.setTeacher(teacher);
            /*Rank rank=new Rank();
            rank.setRankName(collection.getRankName());
            videoInfor.setRank(rank);
            videoInfor.setStudySum(collection.getStudySum());*/
            list.add(videoInfor);
        }
        mView.showContent(list);
    }

    @Override
    public void delAllDatas() {
        if (type == 0) {
            RealmHelper.getInstance().deleteAllCollection();
        } else {
            RealmHelper.getInstance().deleteAllRecord();
            EventBus.getDefault().post("", VideoInfoPresenter.Refresh_History_List);
        }
    }

    @Override
    public void getRecordData() {
        List<Record> records = RealmHelper.getInstance().getRecordList();
        //List<VideoType> list = new ArrayList<>();
        //VideoType videoType;
        List<Course> list=new ArrayList<>();
        Course videoInfor=null;
        for (Record record : records) {
            /*videoType = new VideoType();
            videoType.title = record.title;
            videoType.pic = record.pic;
            videoType.dataId = record.getId();
            list.add(videoType);*/
            videoInfor=new Course();
            videoInfor.setId(record.getId());
            videoInfor.setName(record.getName());
            videoInfor.setIcon(record.getIcon());
            videoInfor.setIntro(record.getIntro());
            Profession profession=new Profession();
            profession.setName(record.getProfession().getName());
            videoInfor.setProfession(profession);
            Teacher teacher=new Teacher();
            teacher.setName(record.getTeacher().getName());
            videoInfor.setTeacher(teacher);
            /*Rank rank=new Rank();
            rank.setRankName(record.getRankName());
            videoInfor.setRank(rank);
            videoInfor.setStudySum(record.getStudySum());*/

            list.add(videoInfor);
        }
        mView.showContent(list);
    }

    @Override
    public int getType() {
        return type;
    }
}
