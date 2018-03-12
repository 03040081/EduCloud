package com.zsc.zzc.educloud.presenter.contract;

import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.User;

/**
 * Created by 21191 on 2018/3/3.
 */

public interface LoginContract {
    interface View extends BaseView {
        void saveContent(User user);

    }
    interface Presenter extends BasePresenter<View> {
        void login(String userAccount, String userPass);

        void insertUser(User user);


    }
}
