package com.zsc.zzc.educloud.presenter.contract;

import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.Course;

import java.util.List;

public interface ScheduleContract {

    interface View extends BaseView {
        void showContent(List<Course> viewRes);

        void refreshFaild(String msg);

    }
    interface Presenter extends BasePresenter<View> {
        void onRefresh();
    }
}
