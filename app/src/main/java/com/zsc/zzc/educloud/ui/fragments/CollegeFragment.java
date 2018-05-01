package com.zsc.zzc.educloud.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.BaseMvpFragment;
import com.zsc.zzc.educloud.model.bean.College;
import com.zsc.zzc.educloud.model.bean.Profession;
import com.zsc.zzc.educloud.presenter.CollegePresenter;
import com.zsc.zzc.educloud.presenter.contract.CollegeContract;
import com.zsc.zzc.educloud.ui.adapter.CollegeAdapter;
import com.zsc.zzc.educloud.utils.EventUtil;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 21191 on 2018/4/29.
 */

public class CollegeFragment extends BaseMvpFragment<CollegePresenter> implements CollegeContract.View,AdapterView.OnItemClickListener {

    private List<College> collegeList;
    @BindView(R.id.list_major)
    ListView listView;
    private CollegeAdapter adapter;
    public static int mPosition;

    @Override
    protected void initView(LayoutInflater inflater) {

    }

    @Override
    protected void initEvent() {
        mPresenter.onRefresh();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPosition=position;
        adapter.notifyDataSetChanged();
        ProfessionFragment professionFragment=new ProfessionFragment();
        FragmentTransaction transaction=getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_category,professionFragment);
        Bundle args=new Bundle();
        List<Profession> categories=collegeList.get(mPosition).getListProfessions();
        args.putSerializable(ProfessionFragment.TAG, (Serializable) categories);
        professionFragment.setArguments(args);
        transaction.commit();
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    public void showContent(List<College> collegeRes) {
        collegeList=collegeRes;
        if(collegeRes!=null&&adapter==null){
            adapter=new CollegeAdapter(getContext(),collegeList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
            ProfessionFragment professionFragment=new ProfessionFragment();
            FragmentTransaction transaction=getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_category,professionFragment);
            List<Profession> professionList=collegeList.get(mPosition).getListProfessions();
            Bundle args=new Bundle();
            args.putSerializable(ProfessionFragment.TAG, (Serializable) professionList);
            professionFragment.setArguments(args);
            transaction.commit();
        }
    }

    @Override
    public void refreshFaild(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_classification;
    }
}
