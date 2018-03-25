package com.zsc.zzc.educloud.presenter;

import android.util.Log;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;
import com.zsc.zzc.educloud.model.net.RetrofitHelper;
import com.zsc.zzc.educloud.presenter.contract.RegisterContract;
import com.zsc.zzc.educloud.utils.RxUtil;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

public class RegisterPresenter extends RxPresenter<RegisterContract.View> implements RegisterContract.Presenter{

    @Inject
    public RegisterPresenter(){

    }

    @Override
    public void register(String userName,String userAccount, String userPass, String code) {
        Subscription rxSubscription= RetrofitHelper.getVideoApi().Register(userAccount,userName,userPass,code)
                .compose(RxUtil.<VideoHttpResponse<Integer>>rxSchedulerHelper())
                .compose(RxUtil.<Integer>handleResult())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if (integer == 1) {
                            mView.showResult("注册成功！");
                        } else {
                            mView.showResult("");
                        }
                    }

                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.getMessage());
                        Log.e("失败",throwable.getMessage());
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void confirm(String phone) {
        Subscription rxSubscription=RetrofitHelper.getVideoApi().Confirmation(phone)
                .compose(RxUtil.<VideoHttpResponse<Integer>>rxSchedulerHelper())
                .compose(RxUtil.<Integer>handleResult())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        addSubscribe(rxSubscription);
    }
}
