// (c)2016 Flipboard Inc, All Rights Reserved.

package com.zsc.zzc.educloud.model.http.api;

import com.zsc.zzc.educloud.model.bean.Forum;
import com.zsc.zzc.educloud.model.bean.Major;
import com.zsc.zzc.educloud.model.bean.User;
import com.zsc.zzc.educloud.model.bean.VideoAssess;
import com.zsc.zzc.educloud.model.bean.VideoInfor;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Description: VideoApis
 * Creator: yxc
 * date: 2016/9/8 14:05
 */

public interface VideoApis {
    //String HOST = "http://api.svipmovie.com/front/";
    String HOST = "http://47.93.11.130:8080/educloud/";

    /*
    推荐页
     */
    @GET("getRecommendVideos")
    Observable<VideoHttpResponse<List<VideoInfor>>> getRecommendVideos(@Query("currPage") int currPage, @Query("pageSize") int pageSize);

    @GET("getRecommentRollVideos")
    Observable<VideoHttpResponse<List<VideoInfor>>> getRollVideos();

    /*
    课程详细信息
     */
    @GET("getVideoDetailed")
    Observable<VideoHttpResponse<VideoInfor>> getVideoDetailed(@Query("videoId") int videoId);

    /*
    搜索课程
     */

    @GET("searchVideos")
    Observable<VideoHttpResponse<List<VideoInfor>>>searchVideos(@Query("currPage") int currPage, @Query("pageSize") int pageSize, @Query("videoTile") String videoTile);

    /*
    分类课程
     */
    @GET("cgdetailedVideos")
    Observable<VideoHttpResponse<List<VideoInfor>>>cgdetailedVideos(
            @Query("currPage") int currPage, @Query("pageSize") int pageSize,
            @Query("majorId") int majorId, @Query("categoryId") int categoryId,
            @Query("cgdetailedId") int cgdetailedId);

    /*
    获取分类列表
     */
    @GET("getMajors")
    Observable<VideoHttpResponse<List<Major>>> getMajors();

    /*
    课程评论
     */
    @GET("videoAssessesList")
    Observable<VideoHttpResponse<List<VideoAssess>>>getVideoAssesses(@Query("currPage") int currPage, @Query("pageSize") int pageSize, @Query("videoId") int videoId);

    /*
    登录
     */
    @GET("Login")
    Observable<VideoHttpResponse<User>>Login(@Query("userAccount") String userAccount, @Query("userPass") String userPass);



    @FormUrlEncoded
    @POST("RegisterUser")
    Observable<VideoHttpResponse<Integer>> Register(@Field("userAccount") String userAccount,
                                                    @Field("userName") String userName,
                                                    @Field("userPass") String userPass,
                                                    @Field("code") String code);
    /*@GET("RegisterUser")
    Observable<VideoHttpResponse<Integer>> Register(@Query("userAccount") String userAccount,
                                                    @Query("userName") String userName,
                                                    @Query("userPass") String userPass,
                                                    @Query("code") String code);*/

   /*
   发送验证码
    */
    @FormUrlEncoded
    @POST("confirmation")
    Observable<VideoHttpResponse<Integer>> Confirmation(@Field("phone") String phone);

    /*
    获取个人课程表
     */
    @GET("getUserSchedule")
    Observable<VideoHttpResponse<List<VideoInfor>>> getSchedule(@Query("userId") int userId);

    /*
    获取咨询列表
     */
    @GET("forumsList")
    Observable<VideoHttpResponse<List<Forum>>> getForumList(@Query("videoId") int videoId,
                                                            @Query("currPage") int currPage,
                                                            @Query("pageSize") int pageSize);

    /*
    发表评论
     */
    @FormUrlEncoded
    @POST
    void publishAssess(@Field("videoId")int videoId,
                       @Field("score")int score,
                       @Field("contents")String contents,
                       @Field("userId")int userId);

    /*
    发表咨询
     */
    @FormUrlEncoded
    @POST
    void publishForum(@Field("videoId")int videoId,
                      @Field("contents")String contents,
                      @Field("userId")int userId);

    /*
    添加到个人课程表
     */
    @FormUrlEncoded
    @POST
    Observable<Boolean> addSchedule(@Field("userId")int userId,
                                    @Field("videoId")int videoId);

}
