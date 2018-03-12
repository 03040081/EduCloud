package com.zsc.zzc.educloud.presenter.contract;


import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.VideoInfor;

import java.util.List;

/**
 * Description: CollectionContract
 * Creator: yxc
 * date: 2016/9/29 12:06
 */
public interface CollectionContract {

    interface View extends BaseView {


        void showContent(List<VideoInfor> list);

    }

    interface Presenter extends BasePresenter<View> {
        void getData(int type);

        void getCollectData();

        void delAllDatas();

        void getRecordData();

        int getType();
    }
}
