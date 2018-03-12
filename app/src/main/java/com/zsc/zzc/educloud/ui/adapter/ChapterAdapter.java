package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.model.bean.VideoChapter;
import com.zsc.zzc.educloud.widget.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 21191 on 2018/2/24.
 */

public class ChapterAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{

    private Context context;
    private List<VideoChapter> list=new ArrayList<>();
    private MyClickListener myClickListener;

    public interface MyClickListener{
        public void clickListener(AdapterView<?> parent, View view, int position, long id);
    }

    public ChapterAdapter(Context context,List<VideoChapter> list,
                          MyClickListener myClickListener){
        this.context=context;
        this.list=list;
        this.myClickListener=myClickListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getChapterId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HodeView hodeView=null;
        if(convertView==null){
            hodeView=new HodeView();
            convertView=View.inflate(context, R.layout.chapter_item,null);
            hodeView.topchaptername=(TextView) convertView.findViewById(R.id.chantername);
            hodeView.chapter=(MyListView) convertView.findViewById(R.id.listdetailed);
            convertView.setTag(hodeView);
        }else{
            hodeView=(HodeView)convertView.getTag();
        }

        final ChapterDetailedAdapter adapter=new ChapterDetailedAdapter(context,
                list.get(position).getListChapterDetailed());
        hodeView.chapter.setAdapter(adapter);
        hodeView.topchaptername.setText(list.get(position).getChapterName());
        hodeView.chapter.setOnItemClickListener(this);
        /*hodeView.chapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChapterDetailed chapterDetailed=(ChapterDetailed) adapter.getItem(position);
                Log.e("视频播放路径：",chapterDetailed.getVideoUrl());
                Intent intent=new Intent(context, VideoInfoActivity.class);
                intent.putExtra("videoUrl",chapterDetailed.getVideoUrl());

            }
        });*/
        return convertView;
    }
    class HodeView{
        public MyListView chapter;
        public TextView topchaptername;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        myClickListener.clickListener(parent,view,position,id);
    }
}
