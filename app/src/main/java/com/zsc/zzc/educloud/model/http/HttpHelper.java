package com.zsc.zzc.educloud.model.http;

import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;

import java.util.List;

import rx.Observable;


public interface HttpHelper {

    //////////////////////////////////////////////////////////////////
    Observable<VideoHttpResponse<List<Course>>> fetchRecommendVideos();
    /////////////////

}
