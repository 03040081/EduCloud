package com.zsc.zzc.educloud.model.http;

import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.model.http.api.VideoApis;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class RetrofitHelper1 implements HttpHelper {

    private VideoApis mVideoApis;
    //private GankApis mGankApis;
    
    @Inject
    public RetrofitHelper1(VideoApis videoApis) {
        this.mVideoApis = videoApis;
    }


    @Override
    public Observable<VideoHttpResponse<List<Course>>> fetchRecommendVideos() {
        return mVideoApis.getRecommendVideos();
    }

}
