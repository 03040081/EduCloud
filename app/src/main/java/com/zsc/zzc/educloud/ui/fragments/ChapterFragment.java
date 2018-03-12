package com.zsc.zzc.educloud.ui.fragments;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.BaseFragment;
import com.zsc.zzc.educloud.model.bean.ChapterDetailed;
import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.presenter.VideoInfoPresenter;
import com.zsc.zzc.educloud.ui.adapter.ChapterAdapter;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import butterknife.BindView;

/**
 * Created by 21191 on 2018/2/24.
 */

public class ChapterFragment extends BaseFragment implements ChapterAdapter.MyClickListener {

    @BindView(R.id.chaptercontent)
    ListView listView;
    ChapterAdapter adapter;

    private URLListener listener;

    @Override
    protected int getLayout() {
        return R.layout.fragment_chapter;
    }

    protected void initView(LayoutInflater inflater){
        EventBus.getDefault().register(this);
        listener=(URLListener)getActivity();
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscriber(tag = VideoInfoPresenter.Refresh_Video_Info)
    public void setData(VideoInfor videoInfo) {
        adapter=new ChapterAdapter(getContext(),videoInfo.getListChapter(),this);
        listView.setAdapter(adapter);
    }

    @Override
    public void clickListener(AdapterView<?> parent, View view, int position, long id) {
        ChapterDetailed chapterDetailed=(ChapterDetailed) parent.getAdapter().getItem(position);
        Log.e("视频播放路径：",chapterDetailed.getVideoUrl());
        listener.sendURL("http://47.93.11.130:8080/educloud/"+chapterDetailed.getVideoUrl());
    }

    public interface URLListener{
        public abstract void sendURL(String videoRUL);
    }
}
