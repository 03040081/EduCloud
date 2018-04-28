package com.zsc.zzc.educloud.presenter.contract;

import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.College;

import java.util.List;

/**
 * Created by 21191 on 2018/4/29.
 */

public interface CollegeContract {
    interface View extends BaseView {

        void showContent(List<College> collegeRes);

        void refreshFaild(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void onRefresh();
    }
}
