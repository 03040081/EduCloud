package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.Category;
import com.zsc.zzc.educloud.model.bean.CategoryDetailed;
import com.zsc.zzc.educloud.model.bean.Major;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;
import com.zsc.zzc.educloud.model.net.RetrofitHelper;
import com.zsc.zzc.educloud.presenter.contract.ClassificationContract;
import com.zsc.zzc.educloud.utils.RxUtil;
import com.zsc.zzc.educloud.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Description: ClassificationPresenter
 * Creator: yxc
 * date: 2016/9/21 17:55
 */
public class ClassificationPresenter extends RxPresenter<ClassificationContract.View> implements ClassificationContract.Presenter {
    int page = 0;

    @Inject
    public ClassificationPresenter() {
    }

    @Override
    public void onRefresh() {
        page = 0;
        getMajorList();
    }

    private void getMajorList() {
        Subscription rxSubscription= RetrofitHelper.getVideoApi().getMajors()
                .compose(RxUtil.<VideoHttpResponse<List<Major>>>rxSchedulerHelper())
                .compose(RxUtil.<List<Major>>handleResult())
                .subscribe(new Action1<List<Major>>() {
                    @Override
                    public void call(List<Major> majors) {
                        if (majors != null) {
                            for(int i=0;i<majors.size();i++){
                                List<Category> categoryList=majors.get(i).getCategoryList();
                                for (int j=0;j<categoryList.size();j++){
                                    List<CategoryDetailed> detailedList=categoryList.get(j).getCategoryDetailedList();
                                    for (int k=0;k<detailedList.size();k++){
                                        majors.get(i).getCategoryList().get(j).getCategoryDetailedList().get(k).setMajorId(majors.get(i).getMajorId());
                                        majors.get(i).getCategoryList().get(j).getCategoryDetailedList().get(k).setCategoryId(majors.get(i).getCategoryList().get(j).getCategoryId());
                                    }
                                }
                            }
                            mView.showContent(majors);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }
}
