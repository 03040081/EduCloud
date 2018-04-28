package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.College;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;
import com.zsc.zzc.educloud.model.net.RetrofitHelper;
import com.zsc.zzc.educloud.presenter.contract.CollegeContract;
import com.zsc.zzc.educloud.utils.RxUtil;
import com.zsc.zzc.educloud.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 21191 on 2018/4/29.
 */

public class CollegePresenter extends RxPresenter<CollegeContract.View> implements CollegeContract.Presenter {

    @Inject
    public CollegePresenter(){

    }

    @Override
    public void onRefresh() {
        getCollege();
    }

    private void getCollege(){
        Subscription rxSubscription= RetrofitHelper.getVideoApi().getCollege()
                .compose(RxUtil.<VideoHttpResponse<List<College>>>rxSchedulerHelper())
                .compose(RxUtil.<List<College>>handleResult())
                .subscribe(new Action1<List<College>>() {
                    @Override
                    public void call(List<College> colleges) {
                        if (colleges != null) {
                            mView.showContent(colleges);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }
}
