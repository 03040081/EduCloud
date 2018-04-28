package com.zsc.zzc.educloud.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.model.bean.Profession;
import com.zsc.zzc.educloud.ui.activitys.VideoListActivity;
import com.zsc.zzc.educloud.ui.adapter.ProfessionAdapter;
import com.zsc.zzc.educloud.widget.MyGridView;

import java.util.List;

/**
 * Created by 21191 on 2018/4/29.
 */

public class ProfessionFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG="ProfessionFragment";
    private ProfessionAdapter adapter;

    MyGridView myGridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_category, null);
        myGridView=(MyGridView) view.findViewById(R.id.listcategory);
        List<Profession> professionList= (List<Profession>) getArguments().getSerializable(ProfessionFragment.TAG);
        //Log.e("检查CategoryFragment ",professionList.get(0).getName());
        adapter=new ProfessionAdapter(this.getContext(),professionList);
        myGridView.setAdapter(adapter);
        myGridView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Profession profession=(Profession)parent.getAdapter().getItem(position);
        VideoListActivity.start(getContext(),null,profession.getId());
    }
}
