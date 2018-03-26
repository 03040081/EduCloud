package com.zsc.zzc.educloud.ui.fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.BaseMvpFragment;
import com.zsc.zzc.educloud.component.ImageLoader;
import com.zsc.zzc.educloud.model.bean.User;
import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.presenter.LoginPresenter;
import com.zsc.zzc.educloud.presenter.MinePresenter;
import com.zsc.zzc.educloud.presenter.VideoInfoPresenter;
import com.zsc.zzc.educloud.presenter.contract.MineContract;
import com.zsc.zzc.educloud.ui.activitys.CollectionActivity;
import com.zsc.zzc.educloud.ui.activitys.HistoryActivity;
import com.zsc.zzc.educloud.ui.activitys.LoginActivity;
import com.zsc.zzc.educloud.ui.activitys.SettingActivity;
import com.zsc.zzc.educloud.ui.activitys.VideoInfoActivity;
import com.zsc.zzc.educloud.ui.adapter.MineHistoryVideoListAdapter;
import com.zsc.zzc.educloud.utils.EventUtil;
import com.zsc.zzc.educloud.utils.ScreenUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zsc.zzc.educloud.R.id.recyclerView;

public class MineFragment extends BaseMvpFragment<MinePresenter> implements MineContract.View {
    //public static final String SET_THEME = "SET_THEME";
    MineHistoryVideoListAdapter mAdapter;
    VideoInfor videoInfor;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(recyclerView)
    EasyRecyclerView mRecyclerView;

    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.tv_collection)
    TextView tvCollection;
    /*@BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.tv_cat)
    TextView tvCat;*/
    @BindView(R.id.tv_download)
    TextView tvDownload;
    @BindView(R.id.tv_setting)
    TextView tvSetting;

    User user=null;

    //@BindView(R.id.username)
    //TextView username;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(LayoutInflater inflater) {

        EventBus.getDefault().register(this);
        ((AppCompatActivity) getContext()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        titleName.setText(getResources().getString(R.string.mine_title));

        mRecyclerView.setAdapter(mAdapter = new MineHistoryVideoListAdapter(mContext));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        gridLayoutManager.setSpanSizeLookup(mAdapter.obtainGridSpanSizeLookUp(3));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(mContext, 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mRecyclerView.addItemDecoration(itemDecoration);

        User user= LoginPresenter.getUserInfo();
        if(user==null){
            username.setText("点击登录");
            imgHead.setImageResource(R.drawable.mine_head);
        }
    }

    @Override
    protected void initEvent() {
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                videoInfor=mAdapter.getItem(position);
                VideoInfoActivity.start(mContext,videoInfor);
            }
        });

    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    public void showContent(List<VideoInfor> list,User user) {
        mAdapter.clear();
        mAdapter.addAll(list);
        if (list.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.GONE);
        }
        //Log.e("用户不存在","测试");
        if(user!=null) {
            this.user=user;
            username.setText(user.getUserName());
            if(user.getFaceImg()!=null&&!user.getFaceImg().equals("")){
                ImageLoader.load(mContext,"http://47.93.11.130:8080/educloud/"+user.getFaceImg(),imgHead);
            }
        }
    }



    @OnClick({R.id.rl_history, R.id.rl_collection,R.id.rl_download, R.id.img_setting,R.id.img_head})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_history:
                getContext().startActivity(new Intent(mContext, HistoryActivity.class));
                break;
            case R.id.rl_collection:
                getContext().startActivity(new Intent(mContext, CollectionActivity.class));
                break;
            case R.id.rl_download:
                //下载
                break;
            case R.id.img_setting:
                getContext().startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.img_head:
                getContext().startActivity(new Intent(mContext, LoginActivity.class));
        }
    }

    @Subscriber(tag = VideoInfoPresenter.Refresh_History_List)
    public void setData(String tag) {
        mPresenter.getHistoryData();
        mPresenter.getUserInfo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        mPresenter.getHistoryData();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
}