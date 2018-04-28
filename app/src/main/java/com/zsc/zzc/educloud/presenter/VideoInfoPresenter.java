package com.zsc.zzc.educloud.presenter;

import android.util.Log;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.Collection;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.model.bean.Profession;
import com.zsc.zzc.educloud.model.bean.Record;
import com.zsc.zzc.educloud.model.bean.Section;
import com.zsc.zzc.educloud.model.bean.Teacher;
import com.zsc.zzc.educloud.model.db.RealmHelper;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;
import com.zsc.zzc.educloud.model.net.RetrofitHelper;
import com.zsc.zzc.educloud.presenter.contract.VideoInfoContract;
import com.zsc.zzc.educloud.utils.RxUtil;

import org.simple.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

public class VideoInfoPresenter extends RxPresenter<VideoInfoContract.View> implements VideoInfoContract.Presenter {

    public final static String Refresh_Video_Info = "Refresh_Video_Info";
    public final static String Put_DataId = "Put_DataId";
    public static final String Refresh_Collection_List = "Refresh_Collection_List";
    public static final String Refresh_History_List = "Refresh_History_List";
    public static final String Put_FirstChapterId="Put_FirstChapterId";

    public static final String Post_VideoURL="Post_VideoURL";

    final int WAIT_TIME = 200;
    Course result;

    String dataId = "";
    String firstChapterId="";

    String pic = "";

    @Inject
    public VideoInfoPresenter() {
    }

    public void prepareInfo(Course videoInfor) {
        mView.showContent(videoInfor);
        this.dataId = videoInfor.getId();
        List<Section> tempList=videoInfor.getListSections();
        if(tempList!=null&&tempList.size()>0){
            List<Section> tempList2=tempList.get(0).getListChapter();
            if(tempList2!=null&&tempList2.size()>0){
                this.firstChapterId =tempList2.get(0).getId();
            }
        }
        //Log.e("VideoId@@@@@@@@@@@@@ ",String.valueOf(this.dataId));
        this.pic = videoInfor.getIcon();
        getDetailData(videoInfor.getId());
        setCollectState();
        putMediaId();
        putFirstChapterId();
    }



    @Override
    public void getDetailData(String dataId) {
        Log.e("getDetailData",dataId);
        Subscription rxSubscription = RetrofitHelper.getVideoApi().getVideoDetailed(dataId)
                .compose(RxUtil.<VideoHttpResponse<List<Course>>>rxSchedulerHelper())
                .compose(RxUtil.<List<Course>>handleResult())
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(final List<Course> res) {
                        Course course=res.get(0);
                        if (course != null) {
                            Log.e("getDetailData call",course.getName()+course.getId());
                            mView.showContent(course);
                            result = course;
                            postData();
                            insertRecord();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.hidLoading();
                        mView.showError("数据加载失败");
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        mView.hidLoading();
                    }
                });
        addSubscribe(rxSubscription);
    }



    private void postData() {
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e("postData",result.getName());
                        EventBus.getDefault().post(result, Refresh_Video_Info);
                    }
                });
        addSubscribe(rxSubscription);
    }

    private void putMediaId() {
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e("VideoId是否存进事件： ",String.valueOf(dataId));
                        EventBus.getDefault().post(dataId, Put_DataId);
                    }
                });
        addSubscribe(rxSubscription);
    }

    private void putFirstChapterId() {
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e("firstChapterId是否存进事件： ",String.valueOf(firstChapterId));
                        EventBus.getDefault().post(firstChapterId, Put_FirstChapterId);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void collect() {
        if (RealmHelper.getInstance().queryCollectionId(dataId)) {
            RealmHelper.getInstance().deleteCollection(dataId);
            mView.disCollect();
        } else {
            if (result != null) {
                Collection bean = new Collection();
                bean.setId(dataId);
                bean.setIcon(pic);
                bean.setName(result.getName());
                bean.setTime(System.currentTimeMillis());
                bean.setIntro(result.getIntro());
                Profession profession=new Profession();
                profession.setName(result.getTeacher().getName());
                bean.setProfession(profession);
                Teacher teacher=new Teacher();
                teacher.setName(result.getTeacher().getName());
                bean.setTeacher(teacher);
                Log.e("info Course",result.getTeacher().getName());

                RealmHelper.getInstance().insertCollection(bean);
                mView.collected();
            }
        }
        //刷新收藏列表
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        EventBus.getDefault().post("", Refresh_Collection_List);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void insertRecord() {
        if (!RealmHelper.getInstance().queryRecordId(dataId)) {
            if (result != null) {
                Record bean = new Record();
                bean.setId(dataId);
                bean.setIcon(pic);
                bean.setName(result.getName());
                bean.setTime(System.currentTimeMillis());
                bean.setIntro(result.getIntro());
                Profession profession=new Profession();
                profession.setName(result.getTeacher().getName());
                bean.setProfession(profession);
                Teacher teacher=new Teacher();
                teacher.setName(result.getTeacher().getName());
                bean.setTeacher(teacher);
                /*bean.setStudySum(result.getStudySum());
                bean.setPrices(result.getPrices());
                bean.setRankName(result.getRank().getRankName());*/
                RealmHelper.getInstance().insertRecord(bean, MinePresenter.maxSize);
                //刷新收藏列表
                Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                        .compose(RxUtil.<Long>rxSchedulerHelper())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                EventBus.getDefault().post("", Refresh_History_List);
                            }
                        });
                addSubscribe(rxSubscription);
            }
        }
    }

    private void setCollectState() {
        if (RealmHelper.getInstance().queryCollectionId(dataId)) {
            mView.collected();
        } else {
            mView.disCollect();
        }
    }
}
