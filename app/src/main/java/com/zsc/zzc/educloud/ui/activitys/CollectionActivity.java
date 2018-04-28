package com.zsc.zzc.educloud.ui.activitys;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.SwipeBackActivity;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.presenter.CollectionPresenter;
import com.zsc.zzc.educloud.presenter.VideoInfoPresenter;
import com.zsc.zzc.educloud.presenter.contract.CollectionContract;
import com.zsc.zzc.educloud.ui.adapter.VideoListAdapter;
import com.zsc.zzc.educloud.utils.EventUtil;
import com.zsc.zzc.educloud.utils.ScreenUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CollectionActivity extends SwipeBackActivity<CollectionPresenter> implements CollectionContract.View {

    @BindView(R.id.rl_collect_clear)
    RelativeLayout rlCollectClear;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    VideoListAdapter mAdapter;
    Course videoInfor;

    @Override
    protected int getLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        setTitle();
        rlCollectClear.setVisibility(View.VISIBLE);
        mRecyclerView.setAdapterWithProgress(mAdapter = new VideoListAdapter(mContext));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        gridLayoutManager.setSpanSizeLookup(mAdapter.obtainGridSpanSizeLookUp(2));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(mContext, 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mRecyclerView.addItemDecoration(itemDecoration);

        mPresenter.getData(0);
    }

    @Override
    protected void initEvent() {
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //videoInfo = BeanUtil.VideoType2VideoInfo(mAdapter.getItem(position), videoInfo);
                //VideoInfoActivity.start(mContext, videoInfo);
                //????????????????????????????????????????????????????
                videoInfor=(Course) mAdapter.getItem(position);
                VideoInfoActivity.start(mContext,videoInfor);
            }
        });
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }


    @Override
    public void showContent(List<Course> list) {
        mAdapter.clear();
        mAdapter.addAll(list);
    }

    @OnClick({R.id.rl_back, R.id.rl_collect_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                if (mContext instanceof CollectionActivity) {
                    finish();
                } else if (mContext instanceof HistoryActivity) {
                    finish();
                }
                break;
            case R.id.rl_collect_clear:
                mAdapter.clear();
                mPresenter.delAllDatas();
                break;
        }
    }

    private void setTitle() {
        if (mContext instanceof CollectionActivity) {
            titleName.setText("我的学习");
        } else if (mContext instanceof HistoryActivity) {
            titleName.setText("最近学习");
        }
    }

    @Subscriber(tag = VideoInfoPresenter.Refresh_Collection_List)
    public void setData(String tag) {
        mPresenter.getCollectData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
