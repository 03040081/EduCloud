package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.component.ImageLoader;
import com.zsc.zzc.educloud.model.bean.VideoInfor;

/**
 * Created by 21191 on 2018/3/8.
 */

public class ScheduleAdapter extends RecyclerArrayAdapter<VideoInfor> {

    public ScheduleAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScheduleViewHolder(parent);
    }

    class ScheduleViewHolder extends BaseViewHolder<VideoInfor> {
        ImageView imgPicture;
        TextView tv_title;
        TextView tv_descript;
        TextView tv_rank;
        TextView tv_hot;
        TextView tv_prices;

        public ScheduleViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_video);
            imgPicture = $(R.id.img_video);
            tv_title = $(R.id.tv_title);
            tv_descript=$(R.id.tv_descript);
            tv_rank=$(R.id.tv_rank);
            tv_hot=$(R.id.tv_hot);
            tv_prices=$(R.id.tv_prices);
            imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        @Override
        public void setData(VideoInfor data) {
            tv_title.setText(data.getVideoTile());
            if(data.getRank()!=null)
                tv_rank.setText(data.getRank().getRankName());
            tv_hot.setText(String.valueOf(data.getStudySum()));
            tv_prices.setText(String.valueOf(data.getPrices()));
            tv_descript.setText(data.getVideoDiscript());
            ImageLoader.load(getContext(), "http://47.93.11.130:8080/educloud/"+data.getPicUrl(), imgPicture);
        }
    }

}
