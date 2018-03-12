package com.zsc.zzc.educloud.presenter.contract;


import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.User;
import com.zsc.zzc.educloud.model.bean.VideoInfor;

import java.util.List;

/**
 * Description: CollectionContract
 * Creator: cp
 * date: 2016/9/29 12:19
 */
public interface MineContract {

    interface View extends BaseView {

        void showContent(List<VideoInfor> list, User user);

    }

    interface Presenter extends BasePresenter<View> {
        void getHistoryData();

        void delAllHistory();

    }
}
