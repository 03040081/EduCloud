package com.zsc.zzc.educloud.presenter.contract;

import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.VideoInfor;

import java.util.List;

public interface RecommendContract {

    interface View extends BaseView {

        void showContent(List<VideoInfor> videoRes);

        void refreshFaild(String msg);

        void loadMoreFaild(String msg);

        void showMoreContent(List<VideoInfor> list);

        void showRollContent(List<VideoInfor> list);
    }

    interface Presenter extends BasePresenter<View> {
        void onRefresh();

        void loadMore();
    }
}
