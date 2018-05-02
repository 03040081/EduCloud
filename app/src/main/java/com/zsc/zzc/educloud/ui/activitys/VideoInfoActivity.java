package com.zsc.zzc.educloud.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.base.SwipeBackActivity;
import com.zsc.zzc.educloud.component.ImageLoader;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.model.bean.Section;
import com.zsc.zzc.educloud.presenter.VideoInfoPresenter;
import com.zsc.zzc.educloud.presenter.contract.VideoInfoContract;
import com.zsc.zzc.educloud.ui.fragments.ChapterFragment;
import com.zsc.zzc.educloud.ui.fragments.CommentFragment;
import com.zsc.zzc.educloud.ui.fragments.VideoIntroFragment;
import com.zsc.zzc.educloud.utils.EventUtil;
import com.zsc.zzc.educloud.utils.StringUtils;
import com.zsc.zzc.educloud.widget.LVGhost;
import com.zsc.zzc.educloud.widget.SwipeViewPager;

import org.simple.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoInfoActivity extends SwipeBackActivity<VideoInfoPresenter> implements VideoInfoContract.View, ChapterFragment.URLListener {

    Course videoInfor;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @BindView(R.id.title_name)
    TextView mTitleName;
    @BindView(R.id.viewpagertab)
    SmartTabLayout mViewpagertab;
    @BindView(R.id.viewpager)
    SwipeViewPager mViewpager;
    @BindView(R.id.circle_loading)
    LVGhost mLoading;
    @BindView(R.id.rl_collect)
    RelativeLayout rlCollect;

    Course videoRes;
    private Animation animation;
    ////////
    //ContentPagerAdapter mPagerAdapter;
    /*@BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.tab_viewpager)
    ViewPager viewPager;*/

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        animation = AnimationUtils.loadAnimation(mContext, R.anim.view_hand);
        rlCollect.setVisibility(View.VISIBLE);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(mContext)
                .add(R.string.video_intro, VideoIntroFragment.class)
                .add(R.string.video_chapter, ChapterFragment.class)
                .add(R.string.video_comment, CommentFragment.class)
                .create());
        //？？？？？？？？？？？？？？？？？？？？？？？
        mViewpager.setOffscreenPageLimit(3);//设置ViewGroup的缓存数量，否则会造成页面数据清除并导致页面空白

        mViewpager.setAdapter(adapter);
        mViewpagertab.setViewPager(mViewpager);
        //List<Fragment> fragments=initFragment();
        //mPagerAdapter=new ContentPagerAdapter(getSupportFragmentManager(),fragments);
        //mViewpager.setAdapter(adapter);
        //mViewpagertab.setViewPager(mViewpager);

        videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoplayer.backButton.setVisibility(View.GONE);
        videoplayer.titleTextView.setVisibility(View.GONE);
        videoplayer.tinyBackImageView.setVisibility(View.GONE);

        mPresenter.prepareInfo(videoInfor);
    }
    /*private List<Fragment> initFragment(){
        fragments=new ArrayList<>();
        Fragment fragment1=new CommentFragment();
        Fragment fragment2=new ChapterFragment();
        Fragment fragment3=new VideoIntroFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        return fragments;
    }*/
/*    private List<String> initTile(){
        tiles=new ArrayList<>();
        String a=new String("评论");
        String b=new String("章节");
        String c=new String("简介");
        tiles.add(a);
        tiles.add(b);
        tiles.add(c);
        return tiles;
    }*/
    /*final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }


        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tiles.get(position);

        }
    }*/
    @Override
    protected void getIntentData() {
        videoInfor = (Course) getIntent().getSerializableExtra("videoInfor");
        Log.e("getIntentData",videoInfor.getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_video_info;
    }

    @OnClick(R.id.rl_back)
    public void back() {
        finish();
    }

    @Override
    public void hidLoading() {
        mLoading.setVisibility(View.GONE);
    }

    @Override
    public void collected() {
        ivCollect.setBackgroundResource(R.mipmap.collection_select);
    }

    @Override
    public void disCollect() {
        ivCollect.setBackgroundResource(R.mipmap.collection);
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    public void showContent(Course videoRes) {
        this.videoRes = videoRes;
        mTitleName.setText(videoRes.getName());
        if (!TextUtils.isEmpty(videoRes.getIcon()))
            ImageLoader.load(mContext, videoRes.getIcon(), videoplayer.thumbImageView);
        //String videoURL="http://47.93.11.130:8080/educloud/"+videoRes.getListChapter().get(0).getListChapterDetailed().get(0).getVideoUrl();
        try {
            String courseSubId = videoRes.getListSections().get(0).getListChapter().get(0).getId();
            String courseIdURL=videoRes.getId();
            Log.e("视频路径courseSubId：",courseSubId);
            String videoURL=StringUtils.getHostVideo(courseIdURL,courseSubId);

            List<Section> tempList=videoRes.getListSections();
            String firstChapterId="";
            if(tempList!=null&&tempList.size()>0){
                List<Section> tempList2=tempList.get(0).getListChapter();
                if(tempList2!=null&&tempList2.size()>0){
                    firstChapterId =tempList2.get(0).getId();
                }
            }
            mPresenter.putFirstChapterId(firstChapterId);
            //postBroadcast(firstChapterId);
            Log.e("videoURL",videoURL);
            if (!TextUtils.isEmpty(videoURL)) {
                videoplayer.setUp(videoURL, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, videoRes.getName());
                videoplayer.onClick(videoplayer.thumbImageView);
            }
        }catch (Exception e){
            Log.e("VideoInfo视频路径出错",e.getMessage());
        }
    }

    @Override
    public void sendURL(String videoRUL) {
        if (!TextUtils.isEmpty(videoRUL)) {
            videoplayer.setUp(videoRUL,JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, videoRes.getName());
            videoplayer.onClick(videoplayer.thumbImageView);
        }
    }

    @OnClick(R.id.rl_collect)
    public void onClick() {
        if (videoRes != null) {
            ivCollect.startAnimation(animation);
            mPresenter.collect();
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    public static void start(Context context, Course videoInfor) {
        Intent starter = new Intent(context, VideoInfoActivity.class);
        starter.putExtra("videoInfor", videoInfor);
        Log.e("InforActivityStart: ",videoInfor.getName());
        context.startActivity(starter);
    }
/*
    private void postBroadcast(String commentId){
        Intent intent=new Intent("comid");
        intent.putExtra("change","yes");
        intent.putExtra("commentId",commentId);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        Log.e("发送广播",commentId);
    }*/

}