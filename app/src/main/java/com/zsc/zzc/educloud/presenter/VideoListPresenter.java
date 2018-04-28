package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.Course;
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

    String tag_id,profession_id;

    @Inject
    public VideoListPresenter() {
    }

    @Override
    public void onRefresh(String tag_id,String profession_id) {
        //this.catalogId = catalogId;
        page = 1;
        /*this.majorId=majorId;
        this.categoryId=categoryId;
        this.cgdetaliedId=cgdetaliedId;*/
        this.tag_id=tag_id;
        this.profession_id=profession_id;
        getVideoList(tag_id,profession_id);
    }

    private void getVideoList(String tag_id,String type) {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().cgdetailedVideosForTag(tag_id,type,page)
                .compose(RxUtil.<VideoHttpResponse<List<Course>>>rxSchedulerHelper())
                .compose(RxUtil.<List<Course>>handleResult())
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> res) {
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
        Subscription rxSubscription = RetrofitHelper.getVideoApi().searchVideos(searchStr,page)
                .compose(RxUtil.<VideoHttpResponse<List<Course>>>rxSchedulerHelper())
                .compose(RxUtil.<List<Course>>handleResult())
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> res) {
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
        getVideoList(tag_id,profession_id);
    }

}
