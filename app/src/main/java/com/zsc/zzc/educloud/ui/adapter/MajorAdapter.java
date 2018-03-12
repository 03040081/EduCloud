package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.model.bean.Major;
import com.zsc.zzc.educloud.ui.fragments.ClassificationFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 21191 on 2018/2/26.
 */

public class MajorAdapter extends BaseAdapter {

    private Context context;
    private List<Major> list=new ArrayList<Major>();
    public static int mPosition;

    public MajorAdapter(Context context,List<Major> list){
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_major, null);
        TextView tv = (TextView) convertView.findViewById(R.id.majorname);
        mPosition = list.get(position).getMajorId();
        tv.setText(list.get(position).getMajorName());
        if (position == ClassificationFragment.mPosition) {
            convertView.setBackgroundColor(Color.parseColor("#e08c6ba4"));
        } else {
            //convertView.setBackgroundColor(Color.parseColor("#000000"));
        }
        return convertView;
    }
}
