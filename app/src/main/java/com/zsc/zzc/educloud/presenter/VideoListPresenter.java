package com.zsc.zzc.educloud.presenter;


import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;
import com.zsc.zzc.educloud.model.net.RetrofitHelper;
import com.zsc.zzc.educloud.presenter.contract.VideoListContract;
import com.zsc.zzc.educloud.utils.RxUtil;
import com.zsc.zzc.educloud.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

public class VideoListPresenter extends RxPresenter<VideoListContract.View> implements VideoListContract.Presenter {
    int page = 1;
    //String catalogId = "";
    int majorId;
    int categoryId;
    int cgdetaliedId;

    @Inject
    public VideoListPresenter() {
    }

    @Override
    public void onRefresh(int majorId,int categoryId,int cgdetaliedId) {
        //this.catalogId = catalogId;
        page = 1;
        this.majorId=majorId;
        this.categoryId=categoryId;
        this.cgdetaliedId=cgdetaliedId;
        getVideoList(majorId,categoryId,cgdetaliedId);
    }

    private void getVideoList(int majorId,int categoryId,int cgdetaliedId) {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().cgdetailedVideos(page,10,majorId,categoryId,cgdetaliedId)
                .compose(RxUtil.<VideoHttpResponse<List<VideoInfor>>>rxSchedulerHelper())
                .compose(RxUtil.<List<VideoInfor>>handleResult())
                .subscribe(new Action1<List<VideoInfor>>() {
                    @Override
                    public void call(List<VideoInfor> res) {
                        if (res != null) {
                            if (page == 1) {
                                mView.showContent(res);
                            } else {
                                mView.showMoreContent(res);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (page > 1) {
                            page--;
                        }
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }

    /**
     * 搜索电影
     *
     * @param searchStr
     */
    private void getSearchVideoList(String searchStr) {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().searchVideos(page,10,searchStr)
                .compose(RxUtil.<VideoHttpResponse<List<VideoInfor>>>rxSchedulerHelper())
                .compose(RxUtil.<List<VideoInfor>>handleResult())
                .subscribe(new Action1<List<VideoInfor>>() {
                    @Override
                    public void call(List<VideoInfor> res) {
                        if (res != null) {
                            if (page == 1) {
                                mView.showContent(res);
                            } else {
                                mView.showMoreContent(res);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (page > 1) {
                            page--;
                        }
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void loadMore() {
        page++;
        getVideoList(majorId,categoryId,cgdetaliedId);
    }

}
