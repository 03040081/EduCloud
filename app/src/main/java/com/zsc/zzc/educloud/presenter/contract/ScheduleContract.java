package com.zsc.zzc.educloud.presenter.contract;

import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.VideoInfor;

import java.util.List;

/**
 * Created by 21191 on 2018/3/7.
 */

public interface ScheduleContract {

    interface View extends BaseView {
        void showContent(List<VideoInfor> viewRes);

        void refreshFaild(String msg);

    }
    interface Presenter extends BasePresenter<View> {
        void onRefresh(int userId);
    }
}
