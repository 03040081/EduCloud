package com.zsc.zzc.educloud.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.BaseMvpFragment;
import com.zsc.zzc.educloud.model.bean.Category;
import com.zsc.zzc.educloud.model.bean.Tag;
import com.zsc.zzc.educloud.presenter.ClassificationPresenter;
import com.zsc.zzc.educloud.presenter.contract.ClassificationContract;
import com.zsc.zzc.educloud.ui.adapter.MajorAdapter;
import com.zsc.zzc.educloud.utils.EventUtil;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

public class ClassificationFragment extends BaseMvpFragment<ClassificationPresenter> implements ClassificationContract.View, SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener {

   /* @BindView(R.id.title_name)
    TextView titleName;*/

    private List<Category> majorList;
    @BindView(R.id.list_major)
    ListView listView;
    private MajorAdapter adapter;
    public static int mPosition;

    @Override
    protected void initView(LayoutInflater inflater) {
        //titleName.setText("分类");
    }

    @Override
    protected void initEvent() {
        mPresenter.onRefresh();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showContent(List<Category> majorRes) {
        majorList=majorRes;
        if(majorRes!=null&&adapter==null){
            adapter=new MajorAdapter(getContext(),majorList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
            CategoryFragment categoryFragment=new CategoryFragment();
            FragmentTransaction transaction=getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_category,categoryFragment);
            List<Tag> categories=majorList.get(mPosition).getListTags();
            Bundle args=new Bundle();
            args.putSerializable(CategoryFragment.TAG, (Serializable) categories);
            categoryFragment.setArguments(args);
            transaction.commit();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Log.e("输出响应",String.valueOf(position));
        mPosition=position;
        adapter.notifyDataSetChanged();
        CategoryFragment categoryFragment=new CategoryFragment();
        FragmentTransaction transaction=getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_category,categoryFragment);
        Bundle args=new Bundle();
        List<Tag> categories=majorList.get(mPosition).getListTags();
        args.putSerializable(CategoryFragment.TAG, (Serializable) categories);
        categoryFragment.setArguments(args);
        transaction.commit();

    }

    @Override
    public void refreshFaild(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
        //recyclerView.showError();
    }


    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_classification;
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }


}
