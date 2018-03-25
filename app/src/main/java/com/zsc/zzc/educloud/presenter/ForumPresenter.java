package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.Forum;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;
import com.zsc.zzc.educloud.model.net.RetrofitHelper;
import com.zsc.zzc.educloud.presenter.contract.ForumContract;
import com.zsc.zzc.educloud.utils.RxUtil;
import com.zsc.zzc.educloud.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

public class ForumPresenter extends RxPresenter<ForumContract.View> implements ForumContract.Presenter {

    int page=1;
    int mediaId=0;
    int pageSize=10;

    @Inject
    public ForumPresenter(){}

    @Override
    public void onRefresh() {
        page=1;
        if(mediaId>0){
            getForumList(mediaId);
        }
    }

    @Override
    public void loadMore() {
        page++;
        if(mediaId>0){
            getForumList(mediaId);
        }
    }

    @Override
    public void setMediaId(int id) {
        this.mediaId=id;
    }

    private void getForumList(int mediaId){
        Subscription rxSubscription= RetrofitHelper.getVideoApi().getForumList(mediaId,page,pageSize)
                .compose(RxUtil.<VideoHttpResponse<List<Forum>>>rxSchedulerHelper())
                .compose(RxUtil.<List<Forum>>handleResult())
                .subscribe(new Action1<List<Forum>>() {
                    @Override
                    public void call(List<Forum> list) {
                        if (list != null) {
                            if (page == 1) {
                                mView.showContent(list);
                            } else {
                                mView.showMoreContent(list);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if(page>1){
                            page--;
                        }
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }
}
