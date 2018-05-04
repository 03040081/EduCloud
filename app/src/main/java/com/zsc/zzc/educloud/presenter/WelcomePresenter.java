package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.presenter.contract.WelcomeContract;
import com.zsc.zzc.educloud.utils.RxUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {

    private static final int COUNT_DOWN_TIME = 2200;
    final int WAIT_TIME = 100;
    String userId=null;
    public static final String Put_UserId="Put_UserId";

    @Inject
    public WelcomePresenter() {
    }

    @Override
    public void getWelcomeData() {

        mView.showContent(getImgData());
        startCountDown();
    }

    private void startCountDown() {
        Subscription rxSubscription = Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mView.jumpToMain();
                    }
                });
        addSubscribe(rxSubscription);
    }

    private List<String> getImgData() {
        List<String> imgs = new ArrayList<>();
        imgs.add("file:///android_asset/a.jpg");
        imgs.add("file:///android_asset/b.jpg");
        imgs.add("file:///android_asset/c.jpg");
//        imgs.add("file:///android_asset/d.jpg");
        imgs.add("file:///android_asset/e.jpg");

        imgs.add("file:///android_asset/f.jpg");
        imgs.add("file:///android_asset/g.jpg");
        return imgs;
    }



}
