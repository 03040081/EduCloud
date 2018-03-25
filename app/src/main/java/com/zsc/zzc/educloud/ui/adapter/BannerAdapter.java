package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.component.ImageLoader;
import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.ui.activitys.VideoInfoActivity;

import java.util.List;

public class BannerAdapter extends StaticPagerAdapter {

    private Context ctx;
    private List<VideoInfor> list;

    public BannerAdapter(Context ctx, List<VideoInfor> list) {
        this.ctx = ctx;
        this.list = list;
        //removeEmpty(this.list);
    }

/*    private void removeEmpty(List<VideoInfor> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRecommend()==1) {
                list.remove(i);
                i--;
            }
        }
    }*/

    @Override
    public View getView(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(ctx);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundResource(R.mipmap.default_320);
        //加载图片
        ImageLoader.load(ctx, "http://47.93.11.130:8080/educloud/"+list.get(position).getPicUrl(), imageView);
        //点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoInfoActivity.start(ctx, list.get(position));
            }
        });
        return imageView;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}