package com.zsc.zzc.educloud.ui.fragments;

import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.BaseMvpFragment;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.model.bean.Learn;
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

    Course videoInfor;
    Learn learn=null;
    PopupMenu popupMenu=null;

    @Override
    protected void initView(LayoutInflater inflater) {
        EventBus.getDefault().register(this);
        titleName.setText("我的课表");
        recyclerView.setAdapterWithProgress(adapter=new ScheduleAdapter(getContext()));
        recyclerView.setErrorView(R.layout.view_error);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SpaceDecoration itemDecoration=new SpaceDecoration(ScreenUtil.dip2px(getContext(),8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);
        tv_empty=(TextView)recyclerView.getEmptyView();
        tv_empty.setText("您还没有添加课程");

    }

    @Override
    protected void initEvent() {

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                learn=adapter.getItem(position);
                VideoInfoActivity.start(mContext,learn.getCourse());
            }
        });
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {

                Learn learn=adapter.getItem(position);
                String id=learn.getId();
                DeleteDialog(id,position);
                return true;
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
                onRefresh();
            }
        });
        recyclerView.setRefreshListener(this);


        onRefresh();
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext,msg);
    }

    @Override
    public void showContent(List<Learn> viewRes) {
        if(viewRes==null||viewRes.size()==0){
            adapter.clear();
            recyclerView.setEmptyView(tv_empty);
        }else {
            adapter.clear();
            adapter.addAll(viewRes);
        }

    }

    @Override
    public void refreshFaild(String msg) {
        if(!TextUtils.isEmpty(msg)){
            showError(msg);
        }
        recyclerView.showError();
    }

    @Override
    public void showCallDelete(String res) {
        if ("OK".equals(res)){
            Toast.makeText(getActivity(),"移除成功",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(),"移除失败",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
        /*mPresenter.setUserId(LoginPresenter.getUserId());
        Log.e("LoginP.getUserId()",LoginPresenter.getUserId());*/
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_schedule;
    }

/*    @Subscriber(tag = WelcomePresenter.Put_UserId)
    public void setUserId(String userId) {
        mPresenter.setUserId(userId);
    }*/

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

/*
    public void showPopMenu(View view, final int position){
        popupMenu=new PopupMenu(mContext,view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_item,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Learn learn=adapter.getItem(position);
                String id=learn.getId();
                mPresenter.deleteLearn(id);
                adapter.remove(position);
                return false;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                //Toast.makeText(mContext, "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }
*/

    private void DeleteDialog(final String id, final int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setMessage("是否删除改课程！");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.deleteLearn(id);
                adapter.remove(position);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

}
