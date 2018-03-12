package com.zsc.zzc.educloud.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zsc.zzc.educloud.R;
import com.zsc.zzc.educloud.component.ImageLoader;
import com.zsc.zzc.educloud.model.bean.VideoAssess;


/**
 * Description: 评论列表
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class CommentAdapter extends RecyclerArrayAdapter<VideoAssess> {

    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(parent);
    }

    class CommentViewHolder extends BaseViewHolder<VideoAssess> {
        RoundedImageView avatar;
        TextView tv_nick;
        TextView tv_time;
        TextView tv_like;
        TextView tv_comment;

        public CommentViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_comment);
            avatar = $(R.id.avatar);
            tv_nick = $(R.id.tv_nick);
            tv_time = $(R.id.tv_time);
            tv_like = $(R.id.tv_like);
            tv_comment = $(R.id.tv_comment);
        }

        @Override
        public void setData(VideoAssess data) {
            tv_nick.setText(data.getUser().getUserName());
            //tv_time.setText(data.time);
            //tv_like.setText(data.likeNum);
            tv_comment.setText(data.getContents());
            if (!TextUtils.isEmpty(data.getUser().getFaceImg()))
                ImageLoader.load(getContext(), data.getUser().getFaceImg(), avatar);
        }
    }
}
