package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.component.ImageLoader;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.utils.StringUtils;

public class VideoListAdapter extends RecyclerArrayAdapter<Course> {

    public VideoListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoListViewHolder(parent);
    }

    class VideoListViewHolder extends BaseViewHolder<Course> {
        ImageView imgPicture;
        TextView tv_title;
        TextView tv_rank;
        TextView tv_hot;
        TextView tv_profession;
        TextView tv_proname;

        public VideoListViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_related);
            imgPicture = $(R.id.img_video);
            tv_title = $(R.id.tv_title);
            tv_rank=$(R.id.tv_rank);
            tv_hot=$(R.id.tv_hot);
            tv_profession=$(R.id.tv_profession);
            tv_proname=$(R.id.tv_proname);
            imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        @Override
        public void setData(Course data) {
            tv_title.setText(data.getName());

            tv_rank.setText("教师:");
            tv_hot.setText(data.getTeacher().getName());
            tv_profession.setText("专业:");
            tv_proname.setText(data.getProfession().getName());

            /*tv_rank.setText(data.getRank().getRankName());
            tv_hot.setText(String.valueOf(data.getStudySum()));
            tv_prices.setText(String.valueOf(data.getPrices()));*/
            //ViewGroup.LayoutParams params = imgPicture.getLayoutParams();

            //DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
           // int width = dm.widthPixels / 2;//宽度为屏幕宽度一半
//        int height = data.getHeight()*width/data.getWidth();//计算View的高度

            //params.height = (int) (width * 0.73);
            //imgPicture.setLayoutParams(params);
            ImageLoader.load(getContext(), StringUtils.getHostImg(data.getIcon()), imgPicture);
        }
    }

}
