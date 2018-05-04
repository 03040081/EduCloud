package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.component.ImageLoader;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.model.bean.Learn;

public class ScheduleAdapter extends RecyclerArrayAdapter<Learn> {

    public ScheduleAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScheduleViewHolder(parent);
    }

    class ScheduleViewHolder extends BaseViewHolder<Learn> {
        ImageView imgPicture;
        TextView tv_title;
        TextView tv_descript;
        TextView tv_rank;
        TextView tv_hot;
        TextView tv_profession;
        TextView tv_proname;

        public ScheduleViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_video);
            imgPicture = $(R.id.img_video);
            tv_title = $(R.id.tv_title);
            tv_descript=$(R.id.tv_descript);
            tv_rank=$(R.id.tv_rank);
            tv_hot=$(R.id.tv_hot);
            tv_profession=$(R.id.tv_profession);
            tv_proname=$(R.id.tv_proname);
            imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        @Override
        public void setData(Learn data) {
            Course course=data.getCourse();
            if(course!=null) {
                tv_title.setText(course.getName());
                tv_rank.setText("教师:");
                if (course.getTeacher() != null)
                    tv_hot.setText(course.getTeacher().getName());
                tv_profession.setText("专业:");
                if (course.getProfession() != null)
                    tv_proname.setText(course.getProfession().getName());
                tv_descript.setText(course.getIntro());
                if (!TextUtils.isEmpty(course.getIcon()))
                    ImageLoader.load(getContext(), course.getIcon(), imgPicture);
            }
        }

    }

}
