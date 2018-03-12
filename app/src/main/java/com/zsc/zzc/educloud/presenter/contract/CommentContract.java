package com.zsc.zzc.educloud.presenter.contract;


import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.VideoAssess;

import java.util.List;

/**
 * Description: CommentContract
 * Creator: yxc
 * date: 2016/10/18 13:21
 */
public interface CommentContract {

    interface View extends BaseView {

        void refreshFaild(String msg);

        void showContent(List<VideoAssess> list);

        void showMoreContent(List<VideoAssess> list);
    }

    interface Presenter extends BasePresenter<View> {

        void onRefresh();

        void loadMore();

        void setMediaId(int id);

    }
}
