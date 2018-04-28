package com.zsc.zzc.educloud.presenter.contract;


import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.Comment;

import java.util.List;

public interface CommentContract {

    interface View extends BaseView {

        void refreshFaild(String msg);

        void showContent(List<Comment> list);

        void showMoreContent(List<Comment> list);
    }

    interface Presenter extends BasePresenter<View> {

        void onRefresh();

        void loadMore();

        void setMediaId(String id);

    }
}
