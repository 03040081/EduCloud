package com.zsc.zzc.educloud.presenter;

import android.util.Log;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.User;
import com.zsc.zzc.educloud.model.db.RealmHelper;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;
import com.zsc.zzc.educloud.model.net.RetrofitHelper;
import com.zsc.zzc.educloud.presenter.contract.LoginContract;
import com.zsc.zzc.educloud.utils.RxUtil;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 21191 on 2018/3/3.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    public LoginPresenter(){

    }
    @Override
    public void login(String userAccount,String userPass) {
        Subscription rxSubscription= RetrofitHelper.getVideoApi().Login(userAccount,userPass)
                .compose(RxUtil.<VideoHttpResponse<User>>rxSchedulerHelper())
                .compose(RxUtil.<User>handleResult())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(final User user) {
                        if (user != null) {
                            mView.saveContent(user);
                            insertUser(user);
                            Log.e("Presenter用户：",user.getUserName());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.getMessage());
                        Log.e("登录失败：",throwable.getMessage());
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void insertUser(User user) {
        if (user!=null){
            RealmHelper.getInstance().insertUserInfo(user);
        }
    }
}
