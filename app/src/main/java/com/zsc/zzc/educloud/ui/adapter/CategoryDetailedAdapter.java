/*
package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsc.zzc.educloud.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailedAdapter extends BaseAdapter {

    private Context context;
    private List<CategoryDetailed> list=new ArrayList<CategoryDetailed>();

    public CategoryDetailedAdapter(Context context,List<CategoryDetailed> list){
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
        return list.get(position).getCgdetailedId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.item_categorydetailed,null);
        TextView view1=(TextView)view.findViewById(R.id.categorydetailedname);
        view1.setText(list.get(position).getCgdetailedName());
        //Log.e("Detailed@#$??????????: ",list.get(position).getCgdetailedName()+" "+String.valueOf(list.size()));
        //Log.e("Position : ",String.valueOf(position));
        return view;
    }
}
*/
