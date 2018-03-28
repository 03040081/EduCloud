package com.zsc.zzc.educloud.ui.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.BaseMvpFragment;
import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.presenter.LoginPresenter;
import com.zsc.zzc.educloud.presenter.SchedulePresenter;
import com.zsc.zzc.educloud.presenter.contract.ScheduleContract;
import com.zsc.zzc.educloud.ui.activitys.VideoInfoActivity;
import com.zsc.zzc.educloud.ui.adapter.ScheduleAdapter;
import com.zsc.zzc.educloud.utils.EventUtil;
import com.zsc.zzc.educloud.utils.ScreenUtil;

import org.simple.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

public class ScheduleFragment extends BaseMvpFragment<SchedulePresenter> implements ScheduleContract.View,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.title_name)
    TextView titleName;

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    TextView tv_empty;

    ScheduleAdapter adapter;

    VideoInfor videoInfor;

    private int userId = 0;

    LocalBroadcastManager broadcastManager;

    @Override
    protected void initView(LayoutInflater inflater) {
        EventBus.getDefault().register(this);
        titleName.setText("我的课程表");
        recyclerView.setAdapterWithProgress(adapter = new ScheduleAdapter(getContext()));
        recyclerView.setErrorView(R.layout.view_error);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(getContext(), 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);
        tv_empty=(TextView)recyclerView.getEmptyView();
        //this.userId = LoginPresenter.getUserId();
        if(userId>0){
            tv_empty.setText("您还没有添加课程");
        }else{
            Log.e("您还未登录",userId+"");
            tv_empty.setText("您还未登录");
            adapter.clear();
            recyclerView.setEmptyView(tv_empty);
        }
        ///////////////////////////////////////////
        Log.e("加载过后",String.valueOf(userId));
    }

    @Override
    protected void initEvent() {
        //this.userId = LoginPresenter.getUserId();
        Log.e("Schedule",String.valueOf(userId));

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                videoInfor = adapter.getItem(position);
                VideoInfoActivity.start(mContext, videoInfor);
            }
        });
        adapter.setError(R.layout.view_error_footer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.resumeMore();
            }
        });
        recyclerView.getErrorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.showProgress();
                ///////////////////////
                onRefresh();
            }
        });

        recyclerView.setRefreshListener(this);

        registerReceiver();



    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    public void showContent(List<VideoInfor> viewRes) {
        adapter.clear();
        adapter.addAll(viewRes);
    }

    @Override
    public void refreshFaild(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            showError(msg);
        }
        recyclerView.showError();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
        this.userId=LoginPresenter.getUserId();
        mPresenter.setUserId(this.userId);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_schedule;
    }

    @Override
    public  void onRefresh() {
        //userId=LoginPresenter.getUserId();

        mPresenter.onRefresh();
        Log.e("接收到广播"," "+userId);
    }


    private void registerReceiver() {
        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("jerry");
        broadcastManager.registerReceiver(mAdDownLoadReceiver, intentFilter);
    }

    private BroadcastReceiver mAdDownLoadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            final String change = intent.getStringExtra("change");
            userId=Integer.valueOf(intent.getStringExtra("userId"));
            if ("yes".equals(change)) {
                // 这地方只能在主线程中刷新UI,子线程中无效，因此用Handler来实现
                new Handler().post(new Runnable() {
                    public void run() {
                        Log.e("接收到广播",change+" "+userId);
                        onRefresh();
                    }
                });
            }
        }
    };

    public void onDetach(){
        super.onDetach();
        broadcastManager.unregisterReceiver(mAdDownLoadReceiver);
    }

}
