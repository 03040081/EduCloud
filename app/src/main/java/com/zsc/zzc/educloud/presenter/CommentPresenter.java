package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.Comment;
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
    String mediaId="" ;
    int pageSize=20;


    @Inject
    public CommentPresenter() {
    }

    @Override
    public void onRefresh() {
        page = 1;
        if (!"".equals(mediaId)) {
            getComment(mediaId);
        }
    }

    public void postComment(String sectionId,String userId,String content){
        Subscription rxSubscription=RetrofitHelper.getVideoApi().publishAssess(sectionId,userId,content)
                .compose(RxUtil.<VideoHttpResponse<String>>rxSchedulerHelper())
                .compose(RxUtil.<String>handleResult())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String bool) {
                        if(bool==null||!bool.equals("OK"))
                            mView.showError("发表失败");
                    }
                });
        addSubscribe(rxSubscription);
    }



    private void getComment(String sectionId) {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().getVideoAssesses(sectionId,page)
                .compose(RxUtil.<VideoHttpResponse<List<Comment>>>rxSchedulerHelper())
                .compose(RxUtil.<List<Comment>>handleResult())
                .subscribe(new Action1<List<Comment>>() {
                    @Override
                    public void call(List<Comment> res) {
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
        if (!"".equals(mediaId)) {
            getComment(mediaId);
        }
    }

    @Override
    public void setMediaId(String id) {
        this.mediaId = id;
    }


}
