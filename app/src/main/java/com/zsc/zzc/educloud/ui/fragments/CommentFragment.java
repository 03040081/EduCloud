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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.BaseMvpFragment;
import com.zsc.zzc.educloud.model.bean.Comment;
import com.zsc.zzc.educloud.presenter.CommentPresenter;
import com.zsc.zzc.educloud.presenter.LoginPresenter;
import com.zsc.zzc.educloud.presenter.VideoInfoPresenter;
import com.zsc.zzc.educloud.presenter.contract.CommentContract;
import com.zsc.zzc.educloud.ui.adapter.CommentAdapter;
import com.zsc.zzc.educloud.utils.EventUtil;
import com.zsc.zzc.educloud.utils.ScreenUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommentFragment extends BaseMvpFragment<CommentPresenter> implements CommentContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    TextView tv_empty;

    @BindView(R.id.tv_contents_assess)
    EditText tvcontents;
    @BindView(R.id.bt_post_assess)
    Button btassess;

    CommentAdapter adapter;

    int pageSize = 20;

    String chapterId="";
    String userId="";

    LocalBroadcastManager broadcastManager;

    @Override
    protected int getLayout() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        EventBus.getDefault().register(this);
        recyclerView.setAdapterWithProgress(adapter = new CommentAdapter(mContext));
        recyclerView.setErrorView(R.layout.view_error);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(getContext(), 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);
        tv_empty = (TextView) recyclerView.getEmptyView();
        tv_empty.setText("暂无评论");
    }

    @Override
    protected void initEvent() {
        recyclerView.setRefreshListener(this);
        adapter.setError(R.layout.view_error_footer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.resumeMore();
            }
        });
        recyclerView.getErrorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.showProgress();
                onRefresh();
            }
        });

        registerReceiver();

        userId= LoginPresenter.getUserId();
        if(userId!=null&&!"".equals(userId))
            btassess.setEnabled(true);
    }

    @OnClick(R.id.bt_post_assess)
    public void onClick(View view){
        String txt=tvcontents.getText().toString().trim();
        if(txt.length()>0&&!userId.equals("")){
            mPresenter.postComment(chapterId,userId,txt);
            tvcontents.setText("");
            hintKbTwo();
        }else{
            Log.e("评论","NULL");
            Toast.makeText(mContext,"文本不能为空",Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void refreshFaild(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
        recyclerView.showError();
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.onRefresh();
    }

    public void clearFooter() {
        adapter.setMore(new View(mContext), this);
        adapter.setError(new View(mContext));
        adapter.setNoMore(new View(mContext));
    }

    @Override
    public void showContent(List<Comment> list) {
        adapter.clear();
        if (list != null && list.size() < pageSize) {
            clearFooter();
        }
        adapter.addAll(list);
        if (adapter.getCount()<1){
            recyclerView.setEmptyView(tv_empty);
        }
    }

    @Override
    public void showMoreContent(List<Comment> list) {
        adapter.addAll(list);
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadMore();
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Subscriber(tag = VideoInfoPresenter.Put_FirstChapterId)
    public void setData(String firstChapterId) {
        Log.e("检查ChapterId传输状态:  ", String.valueOf(firstChapterId));
        this.chapterId=firstChapterId;
        mPresenter.setMediaId(chapterId);
        mPresenter.onRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    private void hintKbTwo() {
        InputMethodManager imm=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()){
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void registerReceiver(){
        broadcastManager=LocalBroadcastManager.getInstance(mContext);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("comid");
        broadcastManager.registerReceiver(mAdDownLoadReceiver,intentFilter);
    }

    private BroadcastReceiver mAdDownLoadReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String change=intent.getStringExtra("change");
            chapterId=intent.getStringExtra("commentId");
            Log.e("接收到广播",chapterId);
            if("yes".equals(change)){
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.setMediaId(chapterId);
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
