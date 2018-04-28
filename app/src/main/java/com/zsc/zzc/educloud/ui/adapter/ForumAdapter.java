/*
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

public class ForumAdapter extends RecyclerArrayAdapter<Forum> {

    public ForumAdapter(Context context){
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ForumViewHolder(parent);
    }
    class ForumViewHolder extends BaseViewHolder<Forum>{
        RoundedImageView avatar;
        TextView tv_nick;
        TextView tv_time;
        TextView tv_like;
        TextView tv_content;

        public ForumViewHolder(ViewGroup parent){
            super(parent, R.layout.item_forum);
            avatar=$(R.id.avatar);
            tv_nick=$(R.id.tv_nick);
            tv_time=$(R.id.tv_time);
            tv_like=$(R.id.tv_like);
            tv_content=$(R.id.tv_content);
        }
        @Override
        public void setData(Forum data){
            tv_nick.setText(data.getUser().getUserName());
            tv_content.setText(data.getContents());
            ////...........
            if(!TextUtils.isEmpty(data.getUser().getFaceImg())){
                ImageLoader.load(getContext(),data.getUser().getFaceImg(),avatar);
            }
        }
    }
}
*/
