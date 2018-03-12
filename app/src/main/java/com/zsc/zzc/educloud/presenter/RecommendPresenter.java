package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;
import com.zsc.zzc.educloud.model.net.RetrofitHelper;
import com.zsc.zzc.educloud.presenter.contract.RecommendContract;
import com.zsc.zzc.educloud.utils.RxUtil;
import com.zsc.zzc.educloud.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Description: RecommendPresenter
 * Creator: yxc
 * date: 2016/9/21 16:26
 */
public class RecommendPresenter extends RxPresenter<RecommendContract.View> implements RecommendContract.Presenter {

    int page = 1;
    int pageSize=10;

    @Inject
    public RecommendPresenter() {}

    @Override
    public void onRefresh() {
        page = 1;
        getRollVideos();
        getPageHomeInfo();
    }

    @Override
    public void loadMore() {
        page++;
        getPageHomeInfo();
    }

    private void getPageHomeInfo() {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().getRecommendVideos(page,pageSize)
                .compose(RxUtil.<VideoHttpResponse<List<VideoInfor>>>rxSchedulerHelper())
                .compose(RxUtil.<List<VideoInfor>>handleResult())
                .subscribe(new Action1<List<VideoInfor>>() {
                    @Override
                    public void call(final List<VideoInfor> res) {
                        if (res != null) {
                            if(page==1) {
                                mView.showContent(res);
                            }else{
                                mView.showMoreContent(res);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if(page>1){
                            page--;
                            //Log.e("分页结果测试：",String.valueOf(page));
                        }
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }
    private void getRollVideos(){
        Subscription rxSubscription=RetrofitHelper.getVideoApi().getRollVideos()
                .compose(RxUtil.<VideoHttpResponse<List<VideoInfor>>>rxSchedulerHelper())
                .compose(RxUtil.<List<VideoInfor>>handleResult())
                .subscribe(new Action1<List<VideoInfor>>() {
                    @Override
                    public void call(List<VideoInfor> videoInfors) {
                        mView.showRollContent(videoInfors);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
    }

}
