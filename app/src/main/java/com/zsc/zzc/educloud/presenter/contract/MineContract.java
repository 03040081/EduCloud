package com.zsc.zzc.educloud.presenter.contract;


import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.model.bean.User;

import java.util.List;
public interface MineContract {

    interface View extends BaseView {

        void showContent(List<Course> list, User user);

    }

    interface Presenter extends BasePresenter<View> {
        void getHistoryData();

        void delAllHistory();

    }
}
