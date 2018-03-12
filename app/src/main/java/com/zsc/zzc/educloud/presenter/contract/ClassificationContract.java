package com.zsc.zzc.educloud.presenter.contract;

import com.zsc.zzc.educloud.base.BasePresenter;
import com.zsc.zzc.educloud.base.BaseView;
import com.zsc.zzc.educloud.model.bean.Major;

import java.util.List;

/**
 * Description: ClassificationContract
 * Creator: yxc
 * date: 2016/9/21 17:55
 */
public interface ClassificationContract {

    interface View extends BaseView {

        void showContent(List<Major> majorRes);

        void refreshFaild(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void onRefresh();
    }
}
