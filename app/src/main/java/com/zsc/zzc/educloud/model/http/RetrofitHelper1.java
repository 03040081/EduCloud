package com.zsc.zzc.educloud.model.http;

import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.model.http.api.VideoApis;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by codeest on 2016/8/3.
 */
public class RetrofitHelper1 implements HttpHelper {

    private VideoApis mVideoApis;
    //private GankApis mGankApis;
    
    @Inject
    public RetrofitHelper1(VideoApis videoApis) {
        this.mVideoApis = videoApis;
        //this.mGankApis = gankApis;
    }


    @Override
    public Observable<VideoHttpResponse<List<VideoInfor>>> fetchRecommendVideos(int currPage, int pageSize) {
        return mVideoApis.getRecommendVideos(currPage,pageSize);
    }

}
