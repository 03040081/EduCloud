package com.zsc.zzc.educloud.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.SwipeBackActivity;
import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.presenter.VideoListPresenter;
import com.zsc.zzc.educloud.presenter.contract.VideoListContract;
import com.zsc.zzc.educloud.ui.adapter.VideoListAdapter;
import com.zsc.zzc.educloud.utils.EventUtil;
import com.zsc.zzc.educloud.utils.ScreenUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoListActivity extends SwipeBackActivity<VideoListPresenter> implements VideoListContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    //String mTitle = "";
    //String mCatalogId = "";
    int majorId,categoryId,cgdetailedId;

    @BindView(R.id.title_name)
    TextView mTitleName;

    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    VideoListAdapter mAdapter;

    VideoInfor videoInfor;
    int pageSize = 10;

    @Override
    protected int getLayout() {
        return R.layout.activity_video_list;
    }

    @Override
    protected void initView() {
        mTitleName.setText("专题课程");////////////////
        mRecyclerView.setAdapterWithProgress(mAdapter = new VideoListAdapter(mContext));
        mRecyclerView.setErrorView(R.layout.view_error);
        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        gridLayoutManager.setSpanSizeLookup(mAdapter.obtainGridSpanSizeLookUp(2));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(mContext, 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mRecyclerView.addItemDecoration(itemDecoration);
        onRefresh();
    }

    @Override
    protected void initEvent() {
        mTitleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EventUtil.isFastDoubleClick()) {
                    mRecyclerView.scrollToPosition(0);
                }
            }
        });
        mRecyclerView.setRefreshListener(this);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //videoInfo = BeanUtil.VideoType2VideoInfo(mAdapter.getItem(position), videoInfo);
                //VideoInfoActivity.start(mContext, videoInfo);
                //?????????????????????????????????????????????????????????????????????????????
                videoInfor=mAdapter.getItem(position);
                VideoInfoActivity.start(mContext,videoInfor);
            }
        });
        mAdapter.setError(R.layout.view_error_footer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.resumeMore();
            }
        });
        mRecyclerView.getErrorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.showProgress();
                onRefresh();
            }
        });
    }

    @OnClick(R.id.rl_back)
    public void back() {
        if (mContext instanceof VideoListActivity) {
            finish();
        }
    }

    @Override
    public void refreshFaild(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
        mRecyclerView.showError();
    }

    @Override
    public void loadMoreFaild(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
        mAdapter.pauseMore();
    }

    public void clearFooter() {
        mAdapter.setMore(new View(mContext), this);
        mAdapter.setError(new View(mContext));
        mAdapter.setNoMore(new View(mContext));
    }

    @Override
    public void showContent(List<VideoInfor> list) {
        mAdapter.clear();
        if (list != null && list.size() < pageSize) {
            clearFooter();
        }
        mAdapter.addAll(list);
    }

    @Override
    public void showMoreContent(List<VideoInfor> list) {
        mAdapter.addAll(list);
    }


    @Override
    public void onLoadMore() {
        mPresenter.loadMore();
    }

    @Override
    public void onRefresh() {
        //mPresenter.onRefresh(mCatalogId);
        mPresenter.onRefresh(majorId,categoryId,cgdetailedId);
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    protected void getIntentData() {
        majorId=Integer.valueOf(getIntent().getStringExtra("majorId"));
        categoryId=Integer.valueOf(getIntent().getStringExtra("categoryId"));
        cgdetailedId=Integer.valueOf(getIntent().getStringExtra("cgdetaliedId"));
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    /*
    分类查找显示页
     */
    public static void start(Context context, int majorId,int categoryId,int cgdetaliedId) {
        Intent starter = new Intent(context, VideoListActivity.class);
        starter.putExtra("majorId",String.valueOf(majorId));
        starter.putExtra("categoryId",String.valueOf(categoryId));
        starter.putExtra("cgdetaliedId",String.valueOf(cgdetaliedId));
        context.startActivity(starter);
    }
}