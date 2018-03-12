package com.zsc.zzc.educloud.presenter.contract;


import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.Forum;

import java.util.List;

/**
 * Created by 21191 on 2018/3/10.
 */

public interface ForumContract {

    interface View extends BaseView {

        void refreshFaild(String msg);

        void showContent(List<Forum> list);

        void showMoreContent(List<Forum> list);
    }
    interface Presenter extends BasePresenter<View> {

        void onRefresh();

        void loadMore();

        void setMediaId(int id);
    }
}
