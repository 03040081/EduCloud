package com.zsc.zzc.educloud.ui.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.BaseFragment;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.presenter.VideoInfoPresenter;
import com.zsc.zzc.educloud.ui.adapter.RelatedAdapter;
import com.zsc.zzc.educloud.utils.ScreenUtil;
import com.zsc.zzc.educloud.utils.StringUtils;
import com.zsc.zzc.educloud.widget.TextViewExpandableAnimation;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoIntroFragment extends BaseFragment {

    @BindView(R.id.recyclerViewinfo)
    EasyRecyclerView recyclerView;
    TextViewExpandableAnimation tvExpand;
    View headerView;
    private Course videoRes=null;

    RelatedAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_video_intro;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        EventBus.getDefault().register(this);
        headerView = LayoutInflater.from(mContext).inflate(R.layout.intro_header, null);
        tvExpand = ButterKnife.findById(headerView, R.id.tv_expand);
        recyclerView.setAdapter(adapter = new RelatedAdapter(getContext()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        gridLayoutManager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(3));
        recyclerView.setLayoutManager(gridLayoutManager);
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(getContext(), 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);
        /*adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                VideoInfoActivity.start(getContext(), adapter.getItem(position));
                getActivity().finish();
            }
        });*/
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return headerView;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscriber(tag = VideoInfoPresenter.Refresh_Video_Info)
    public void setData(Course videoInfo) {
        videoRes=videoInfo;
        String dir = "课题：" + StringUtils.removeOtherCode(videoRes.getName());
        //String act = "级别：" + StringUtils.removeOtherCode(videoRes.getRank().getRankName());
        String des = dir + "\n"  + "\n" + "简介：" + StringUtils.removeOtherCode(videoRes.getIntro());
        tvExpand.setText(des);
        Log.e("检验路径 ： ",videoRes.getListSections().get(0).getListChapter().get(0).getSubTitle());
        //adapter.add(videoInfo);
        /*if (videoInfo.list.size() > 1)
            adapter.addAll(videoInfo.list.get(1).childList);
        else
            adapter.addAll(videoInfo.list.get(0).childList);*/
    }
}
