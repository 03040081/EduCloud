package com.zsc.zzc.educloud.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.model.bean.Category;
import com.zsc.zzc.educloud.model.bean.CategoryDetailed;
import com.zsc.zzc.educloud.ui.activitys.VideoListActivity;
import com.zsc.zzc.educloud.ui.adapter.CategoryAdapter;

import java.util.List;

/**
 * Created by 21191 on 2018/2/26.
 */

public class CategoryFragment extends Fragment implements CategoryAdapter.MyClickListener {
    public static final String TAG = "CategoryFragment";
    private CategoryAdapter adapterCon;
    //@BindView(R.id.listcategory)
    ListView conList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_category, null);
        conList=(ListView)view.findViewById(R.id.listcategory);
        List<Category> categorys= (List<Category>) getArguments().getSerializable(CategoryFragment.TAG);
        //Log.e("检查 ",categorys.get(0).getCategoryName());
        adapterCon=new CategoryAdapter(this.getContext(),categorys,this);
        conList.setAdapter(adapterCon);
        return view;
    }

    @Override
    public void clickListener(AdapterView<?> parent, View view, int position, long id) {
        CategoryDetailed detailed=(CategoryDetailed)parent.getAdapter().getItem(position);
        Log.e("检查CategoryDetailed","majorId:"+detailed.getMajorId()+"categoryId:"+detailed.getCategoryId()+"detailedId:"+detailed.getCgdetailedId());
        VideoListActivity.start(getContext(),detailed.getMajorId(),detailed.getCategoryId(),detailed.getCgdetailedId());
    }
}
