package com.zsc.zzc.educloud.ui.fragments;

import android.view.LayoutInflater;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.BaseFragment;
import com.zsc.zzc.educloud.widget.SwipeViewPager;

import butterknife.BindView;

/**
 * Created by 21191 on 2018/4/29.
 */

public class ClassificationAllFragment extends BaseFragment {

    @BindView(R.id.title_name)
    TextView titleName;

    @BindView(R.id.viewpager)
    SwipeViewPager mViewpager;
    @BindView(R.id.viewpagertab)
    SmartTabLayout mViewpagertab;


    @Override
    protected int getLayout() {
        return R.layout.fragment_classification_all;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        titleName.setText("分类");
        FragmentPagerItemAdapter adapter=new FragmentPagerItemAdapter(
                getChildFragmentManager(), FragmentPagerItems.with(mContext)
                .add("专业",ClassificationFragment.class)
                .add("学校",CollegeFragment.class)
                .create());

        mViewpager.setOffscreenPageLimit(2);
        mViewpager.setAdapter(adapter);
        mViewpagertab.setViewPager(mViewpager);
    }
}
