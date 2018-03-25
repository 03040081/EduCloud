package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.model.bean.ChapterDetailed;

import java.util.ArrayList;
import java.util.List;


public class ChapterDetailedAdapter extends BaseAdapter {

    private Context context;
    private List<ChapterDetailed> list=new ArrayList<>();

    public ChapterDetailedAdapter(Context context,List<ChapterDetailed> list
                                  ){
        this.context=context;
        this.list=list;
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
        return list.get(position).getDetailedId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.chapterdetailed_item ,null);
        TextView view1=(TextView)view.findViewById(R.id.chapterdetailedname);
        view1.setText(list.get(position).getDetailedTile());
        return view;
        /*HolderView holderView=null;
        if(convertView==null){
            //View view=View.inflate(context, R.layout.chapterdetailed_item ,null);
            //TextView view1=(TextView)view.findViewById(R.id.chapterdetailedname);
            //view1.setText(list.get(position).getDetailedTile());
            convertView=View.inflate(context,R.layout.chapterdetailed_item,null);
            holderView=new HolderView();
            holderView.textView=(TextView)convertView.findViewById(R.id.chapterdetailedname);
            convertView.setTag(holderView);
        }else{
            holderView=(HolderView)convertView.getTag();
        }
        holderView.textView.setText(list.get(position).getDetailedTile());
        holderView.textView.setTag(position);
        return convertView;*/
    }
/*    class HolderView{
        TextView textView;
    }*/


}
