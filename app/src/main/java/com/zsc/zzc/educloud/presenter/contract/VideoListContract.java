package com.zsc.zzc.educloud.presenter.contract;

import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.Course;

import java.util.List;

public interface VideoListContract {

    interface View extends BaseView {

        void refreshFaild(String msg);

        void loadMoreFaild(String msg);

        void showContent(List<Course> list);

        void showMoreContent(List<Course> list);
    }

    interface Presenter extends BasePresenter<View> {

        void onRefresh(String tag_id,String profession_id);

        void loadMore();

    }
}
