package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.component.ImageLoader;
import com.zsc.zzc.educloud.model.bean.Course;

public class MineHistoryVideoListAdapter extends RecyclerArrayAdapter<Course> {

    public MineHistoryVideoListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MineHistoryVideoListViewHolder(parent);
    }

    class MineHistoryVideoListViewHolder extends BaseViewHolder<Course> {
        ImageView imgPicture;
        TextView tv_title;

        public MineHistoryVideoListViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_mine_history);
            imgPicture = $(R.id.img_video);
            tv_title = $(R.id.tv_title);
            imgPicture.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        @Override
        public void setData(Course data) {
            tv_title.setText(data.getName());
            ViewGroup.LayoutParams params = imgPicture.getLayoutParams();

            DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
            int width = dm.widthPixels / 3;//宽度为屏幕宽度一半
//        int height = data.getHeight()*width/data.getWidth();//计算View的高度

            params.height = width;
            imgPicture.setLayoutParams(params);
            if (!TextUtils.isEmpty(data.getIcon()))
                ImageLoader.load(getContext(), data.getIcon(), imgPicture);
        }
    }
}
