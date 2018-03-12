package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.model.bean.Category;
import com.zsc.zzc.educloud.widget.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 21191 on 2018/2/26.
 */

public class CategoryAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private Context context;
    private List<Category> categoryArrayList =new ArrayList<Category>();
    private MyClickListener myClickListener;

    public interface MyClickListener{
        public void clickListener(AdapterView<?> parent, View view, int position, long id);
    }

    public CategoryAdapter(Context context, List<Category> categoryArrayList,MyClickListener myClickListener){
        this.context=context;
        this.categoryArrayList=categoryArrayList;
        this.myClickListener=myClickListener;
    }

    @Override
    public int getCount() {
        return categoryArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categoryArrayList.get(position).getCategoryId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HodeView hodeView=null;
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
        hodeView.cgdetailedgv.setOnItemClickListener(this);

        return convertView;
    }
    class HodeView{
        public MyGridView cgdetailedgv;
        public TextView topCategoryName;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        myClickListener.clickListener(parent, view, position, id);
    }
}
