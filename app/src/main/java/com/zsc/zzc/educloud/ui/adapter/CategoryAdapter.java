package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.model.bean.Tag;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<Tag> tags =new ArrayList<Tag>();
    //private MyClickListener myClickListener;

    /*public interface MyClickListener{
        public void clickListener(AdapterView<?> parent, View view, int position, long id);
    }*/

    public CategoryAdapter(Context context, List<Tag> tags ){
        this.context=context;
        this.tags=tags;
        //this.myClickListener=myClickListener;
    }

    @Override
    public int getCount() {
        return tags.size();
    }

    @Override
    public Object getItem(int position) {
        return tags.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=View.inflate(context, R.layout.item_category,null);
        TextView textView=(TextView)view.findViewById(R.id.categoryname);
        textView.setText(tags.get(position).getName());

        /*HodeView hodeView=null;
        if(convertView==null){
            hodeView=new HodeView();
            convertView=View.inflate(context, R.layout.item_category,null);
            hodeView.cgdetailedgv=(MyGridView) convertView.findViewById(R.id.categorydetailedgrid);
            hodeView.topCategoryName=(TextView)convertView.findViewById(R.id.categoryname);
            convertView.setTag(hodeView);
        }else{
            hodeView=(HodeView)convertView.getTag();
        }
        CategoryDetailedAdapter adapterCD=new CategoryDetailedAdapter(context,
                categoryArrayList.get(position).getCategoryDetailedList());
        hodeView.cgdetailedgv.setAdapter(adapterCD);
        hodeView.topCategoryName.setText(categoryArrayList.get(position).getCategoryName());
        hodeView.cgdetailedgv.setOnItemClickListener(this);*/

        return view;
    }
  /*  class HodeView{
        public MyGridView cgdetailedgv;
        public TextView topCategoryName;
    }*/

    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        myClickListener.clickListener(parent, view, position, id);
    }*/
}
