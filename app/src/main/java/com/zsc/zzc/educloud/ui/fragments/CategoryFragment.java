package com.zsc.zzc.educloud.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.model.bean.Tag;
import com.zsc.zzc.educloud.ui.activitys.VideoListActivity;
import com.zsc.zzc.educloud.ui.adapter.CategoryAdapter;
import com.zsc.zzc.educloud.widget.MyGridView;

import java.util.List;

public class CategoryFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final String TAG = "CategoryFragment";
    private CategoryAdapter adapterCon;
    //@BindView(R.id.listcategory)
    MyGridView conList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_category, null);
        conList=(MyGridView) view.findViewById(R.id.listcategory);
        List<Tag> categorys= (List<Tag>) getArguments().getSerializable(CategoryFragment.TAG);
        Log.e("检查CategoryFragment ",categorys.get(0).getName());
        adapterCon=new CategoryAdapter(this.getContext(),categorys);
        conList.setAdapter(adapterCon);
        conList.setOnItemClickListener(this);
        return view;
    }

   /* @Override
    public void clickListener(AdapterView<?> parent, View view, int position, long id) {
        CategoryDetailed detailed=(CategoryDetailed)parent.getAdapter().getItem(position);
        Log.e("检查CategoryDetailed","majorId:"+detailed.getMajorId()+"categoryId:"+detailed.getCategoryId()+"detailedId:"+detailed.getCgdetailedId());
        VideoListActivity.start(getContext(),detailed.getMajorId(),detailed.getCategoryId(),detailed.getCgdetailedId());
    }*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tag tag=(Tag)parent.getAdapter().getItem(position);

        Log.e("CategoryFragment",tag.getName()+" "+tag.getId());
        VideoListActivity.start(getContext(),tag.getId(),null);
    }
}
