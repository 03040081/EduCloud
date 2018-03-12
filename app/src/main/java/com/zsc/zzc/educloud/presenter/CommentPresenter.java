package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.VideoAssess;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;
import com.zsc.zzc.educloud.model.net.RetrofitHelper;
import com.zsc.zzc.educloud.presenter.contract.CommentContract;
import com.zsc.zzc.educloud.utils.RxUtil;
import com.zsc.zzc.educloud.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

public class CommentPresenter extends RxPresenter<CommentContract.View> implements CommentContract.Presenter {
    int page = 1;
    int mediaId=0 ;
    int pageSize=20;

    @Inject
    public CommentPresenter() {
    }

    @Override
    public void onRefresh() {
        page = 1;
        if (mediaId != 0) {
            getComment(mediaId);
        }
    }

    private void getComment(int mediaId) {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().getVideoAssesses(page,pageSize,mediaId)
                .compose(RxUtil.<VideoHttpResponse<List<VideoAssess>>>rxSchedulerHelper())
                .compose(RxUtil.<List<VideoAssess>>handleResult())
                .subscribe(new Action1<List<VideoAssess>>() {
                    @Override
                    public void call(List<VideoAssess> res) {
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
        if (mediaId != 0) {
            getComment(mediaId);
        }
    }

    @Override
    public void setMediaId(int id) {
        this.mediaId = id;
    }

}
