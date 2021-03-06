package com.zsc.zzc.educloud.presenter.contract;


import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.Course;

import java.util.List;
public interface CollectionContract {

    interface View extends BaseView {


        void showContent(List<Course> list);

    }

    interface Presenter extends BasePresenter<View> {
        void getData(int type);

        void getCollectData();

        void delAllDatas();

        void getRecordData();

        int getType();
    }
}
