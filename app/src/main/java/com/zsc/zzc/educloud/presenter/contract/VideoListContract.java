package com.zsc.zzc.educloud.presenter.contract;

import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.VideoInfor;

import java.util.List;

public interface VideoListContract {

    interface View extends BaseView {

        void refreshFaild(String msg);

        void loadMoreFaild(String msg);

        void showContent(List<VideoInfor> list);

        void showMoreContent(List<VideoInfor> list);
    }

    interface Presenter extends BasePresenter<View> {

        void onRefresh(int majorId, int categoryId, int cgdetaliedId);

        void loadMore();

    }
}
