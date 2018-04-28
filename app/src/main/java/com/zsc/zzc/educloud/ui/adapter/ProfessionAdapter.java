package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.model.bean.Profession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 21191 on 2018/4/29.
 */

public class ProfessionAdapter extends BaseAdapter {

    private Context context;
    private List<Profession> professions =new ArrayList<Profession>();

    public ProfessionAdapter(Context context, List<Profession> professions ){
        this.context=context;
        this.professions=professions;
    }

    @Override
    public int getCount() {
        return professions.size();
    }

    @Override
    public Object getItem(int position) {
        return professions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=View.inflate(context, R.layout.item_category,null);
        TextView textView=(TextView)view.findViewById(R.id.categoryname);
        textView.setText(professions.get(position).getName());
        return view;
    }
}
