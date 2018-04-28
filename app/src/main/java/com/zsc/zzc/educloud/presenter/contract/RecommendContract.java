package com.zsc.zzc.educloud.presenter.contract;

import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.Course;

import java.util.List;

public interface RecommendContract {

    interface View extends BaseView {

        void showContent(List<Course> videoRes);

        void refreshFaild(String msg);

        void loadMoreFaild(String msg);

        void showMoreContent(List<Course> list);

        void showRollContent(List<Course> list);
    }

    interface Presenter extends BasePresenter<View> {
        void onRefresh();

        void loadMore();
    }
}
