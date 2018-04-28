package com.zsc.zzc.educloud.ui.fragments;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.BaseFragment;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.model.bean.Section;
import com.zsc.zzc.educloud.presenter.VideoInfoPresenter;
import com.zsc.zzc.educloud.ui.adapter.ChapterAdapter;
import com.zsc.zzc.educloud.utils.StringUtils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import butterknife.BindView;

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
    public void setData(Course videoInfo) {
        adapter=new ChapterAdapter(getContext(),videoInfo.getListSections(),this);
        listView.setAdapter(adapter);
    }

    @Override
    public void clickListener(AdapterView<?> parent, View view, int position, long id) {
        Section chapterDetailed=(Section) parent.getAdapter().getItem(position);
        Log.e("视频播放路径：",StringUtils.getHostVideo(chapterDetailed.getCourseId(),chapterDetailed.getSubTitle()));
        listener.sendURL(StringUtils.getHostVideo(chapterDetailed.getCourseId(),chapterDetailed.getId()));
        postBroadcast(chapterDetailed.getId());
    }

    private void postBroadcast(String commentId){
        Intent intent=new Intent("comid");
        intent.putExtra("change","yes");
        intent.putExtra("commentId",commentId);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
        Log.e("发送广播",commentId);
    }

    public interface URLListener{
        public abstract void sendURL(String videoRUL);
    }
}
