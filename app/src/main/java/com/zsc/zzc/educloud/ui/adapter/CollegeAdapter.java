package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.model.bean.College;
import com.zsc.zzc.educloud.ui.fragments.CollegeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 21191 on 2018/4/29.
 */

public class CollegeAdapter extends BaseAdapter {

    private Context context;
    private List<College> list=new ArrayList<College>();
    public static int mPosition;

    public CollegeAdapter(Context context,List<College> list){
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
        mPosition = position;
        tv.setText(list.get(position).getName());
        if (position == CollegeFragment.mPosition) {
            convertView.setBackgroundColor(Color.parseColor("#30FFFFFF"));
        } else {
            //convertView.setBackgroundColor(Color.parseColor("#000000"));
        }
        return convertView;
    }
}
