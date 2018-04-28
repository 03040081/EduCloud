package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.Collection;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.model.bean.Profession;
import com.zsc.zzc.educloud.model.bean.Teacher;
import com.zsc.zzc.educloud.model.db.RealmHelper;
import com.zsc.zzc.educloud.presenter.contract.ScheduleContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SchedulePresenter extends RxPresenter<ScheduleContract.View> implements ScheduleContract.Presenter {

    //private String userId="";

    @Inject
    public SchedulePresenter(){
    }

    /*public void setUserId(String userId) {
        this.userId = userId;
    }*/

    @Override
    public void onRefresh() {
        getScheduleInfo();
    }

    private void getScheduleInfo(){

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

        /*Subscription rxSubscription= RetrofitHelper.getVideoApi().getSchedule(userId)
                .compose(RxUtil.<VideoHttpResponse<List<VideoInfor>>>rxSchedulerHelper())
                .compose(RxUtil.<List<VideoInfor>>handleResult())
                .subscribe(new Action1<List<VideoInfor>>() {
                    @Override
                    public void call(List<VideoInfor> videoInfors) {
                        if (videoInfors != null) {
                            mView.showContent(videoInfors);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);*/
    }
}
