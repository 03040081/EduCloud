package com.zsc.zzc.educloud.presenter;

import android.util.Log;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.Learn;
import com.zsc.zzc.educloud.model.bean.User;
import com.zsc.zzc.educloud.model.db.RealmHelper;
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

    private String userId="";

    @Inject
    public SchedulePresenter(){
        this.userId=getUserId();
    }

    public void setUserId(String userId){
        this.userId=userId;
    }

    @Override
    public void onRefresh() {
        getScheduleInfo();
    }

    private void getScheduleInfo(){
        Subscription rxSubscription= RetrofitHelper.getVideoApi().selectLearnsByUserId(userId)
                .compose(RxUtil.<VideoHttpResponse<List<Learn>>>rxSchedulerHelper())
                .compose(RxUtil.<List<Learn>>handleResult())
                .subscribe(new Action1<List<Learn>>() {
                    @Override
                    public void call(List<Learn> learns) {
                        if(learns!=null) {
                            mView.showContent(learns);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                        Log.e("getScheduleInfo错误",throwable.getMessage());
                    }
                });
        addSubscribe(rxSubscription);
    }

    public void deleteLearn(String id){
        Subscription rxSubscription=RetrofitHelper.getVideoApi().deleteLearn(id)
                .compose(RxUtil.<VideoHttpResponse<String>>rxSchedulerHelper())
                .compose(RxUtil.<String>handleResult())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mView.showCallDelete(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        addSubscribe(rxSubscription);
    }

    private String getUserId() {
        User user= RealmHelper.getInstance().getUserInfo();
        if(user!=null) {
           return user.getId();
        }
        return "";
    }
}
