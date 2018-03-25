package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;
import com.zsc.zzc.educloud.model.net.RetrofitHelper;
import com.zsc.zzc.educloud.presenter.contract.ScheduleContract;
import com.zsc.zzc.educloud.utils.RxUtil;
import com.zsc.zzc.educloud.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

public class SchedulePresenter extends RxPresenter<ScheduleContract.View> implements ScheduleContract.Presenter {


    @Inject
    public SchedulePresenter(){}

    @Override
    public void onRefresh(int userId) {
        if(userId>0) {
            getScheduleInfo(userId);
        }
    }

    private void getScheduleInfo(int userId){
        Subscription rxSubscription= RetrofitHelper.getVideoApi().getSchedule(userId)
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
    }
}
