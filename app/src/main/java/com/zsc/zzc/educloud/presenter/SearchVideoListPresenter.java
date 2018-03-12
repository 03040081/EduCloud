package com.zsc.zzc.educloud.presenter;

import com.zsc.zzc.educloud.base.RxPresenter;
import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;
import com.zsc.zzc.educloud.model.net.RetrofitHelper;
import com.zsc.zzc.educloud.presenter.contract.SearchVideoListContract;
import com.zsc.zzc.educloud.utils.RxUtil;
import com.zsc.zzc.educloud.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

public class SearchVideoListPresenter extends RxPresenter<SearchVideoListContract.View> implements SearchVideoListContract.Presenter {
    int page = 1;

    String searchStr = "";

    @Inject
    public SearchVideoListPresenter() {
    }

    @Override
    public void onRefresh() {
        page = 1;
        if (searchStr != null && !searchStr.equals("")) {
            getSearchVideoList(10,searchStr);
        }
    }

    @Override
    public void loadMore() {
        page++;
        if (searchStr != null && !searchStr.equals("")) {
            getSearchVideoList(10,searchStr);
        }
    }

    @Override
    public void setSearchKey(String strSearchKey) {
        this.searchStr = strSearchKey;
    }

    /**
     * 搜索电影
     *
     * @param searchStr
     */
    private void getSearchVideoList(int pageSize,String searchStr) {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().searchVideos(page,pageSize,searchStr)
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


}
