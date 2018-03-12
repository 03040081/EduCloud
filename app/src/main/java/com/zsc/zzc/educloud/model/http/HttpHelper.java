package com.zsc.zzc.educloud.model.http;

import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;

import java.util.List;

import rx.Observable;

/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @description:
 */

public interface HttpHelper {

    //////////////////////////////////////////////////////////////////
    Observable<VideoHttpResponse<List<VideoInfor>>> fetchRecommendVideos(int currPage, int pageSize);
    /////////////////

}
