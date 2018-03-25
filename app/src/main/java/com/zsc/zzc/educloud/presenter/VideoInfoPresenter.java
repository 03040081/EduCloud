package com.zsc.zzc.educloud.presenter;

import android.util.Log;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.Collection;
import com.zsc.zzc.educloud.model.bean.Record;
import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.model.db.RealmHelper;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;
import com.zsc.zzc.educloud.model.net.RetrofitHelper;
import com.zsc.zzc.educloud.presenter.contract.VideoInfoContract;
import com.zsc.zzc.educloud.utils.RxUtil;

import org.simple.eventbus.EventBus;

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

    public static final String Post_VideoURL="Post_VideoURL";

    final int WAIT_TIME = 200;
    VideoInfor result;
    int dataId = 0;
    String pic = "";

    @Inject
    public VideoInfoPresenter() {
    }

    public void prepareInfo(VideoInfor videoInfor) {
        mView.showContent(videoInfor);
        this.dataId = videoInfor.getVideoId();
        //Log.e("VideoId@@@@@@@@@@@@@ ",String.valueOf(this.dataId));
        this.pic = videoInfor.getPicUrl();
        getDetailData(videoInfor.getVideoId());
        setCollectState();
        putMediaId();
    }



    @Override
    public void getDetailData(int dataId) {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().getVideoDetailed(dataId)
                .compose(RxUtil.<VideoHttpResponse<VideoInfor>>rxSchedulerHelper())
                .compose(RxUtil.<VideoInfor>handleResult())
                .subscribe(new Action1<VideoInfor>() {
                    @Override
                    public void call(final VideoInfor res) {
                        if (res != null) {
                            mView.showContent(res);
                            result = res;
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

    @Override
    public void collect() {
        if (RealmHelper.getInstance().queryCollectionId(dataId)) {
            RealmHelper.getInstance().deleteCollection(dataId);
            mView.disCollect();
        } else {
            if (result != null) {
                Collection bean = new Collection();
                bean.setVideoId(dataId);
                bean.setPicUrl(pic);
                bean.setVideoTile(result.getVideoTile());
                bean.setTime(System.currentTimeMillis());
                bean.setStudySum(result.getStudySum());
                bean.setPrices(result.getPrices());
                bean.setRankName(result.getRank().getRankName());
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
                bean.setVideoId(dataId);
                bean.setPicUrl(pic);
                bean.setVideoTile(result.getVideoTile());
                bean.setTime(System.currentTimeMillis());
                bean.setStudySum(result.getStudySum());
                bean.setPrices(result.getPrices());
                bean.setRankName(result.getRank().getRankName());
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
