// (c)2016 Flipboard Inc, All Rights Reserved.

package com.zsc.zzc.educloud.model.http.api;

import com.zsc.zzc.educloud.model.bean.Category;
import com.zsc.zzc.educloud.model.bean.Comment;
import com.zsc.zzc.educloud.model.bean.Course;
import com.zsc.zzc.educloud.model.bean.User;
import com.zsc.zzc.educloud.model.http.response.VideoHttpResponse;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public interface VideoApis {
    //String HOST = "http://47.93.11.130:8080/educloud/";
    String HOST = "http://47.93.11.130:8080/zsccloud/";


    /*
    推荐课程
    ------------------------待服务器修改
     */
    @GET("course/recommendcourse")
    Observable<VideoHttpResponse<List<Course>>> getRecommendVideos();

    /*
    轮播图，原为首要推荐
     */
    @GET("course/rollcourse")
    Observable<VideoHttpResponse<List<Course>>> getRollVideos();

    @GET("course/dbcourseById")
    Observable<VideoHttpResponse<List<Course>>> getVideoDetailed(@Query("id") String videoId);

     /*
    搜索课程
     */

    @GET("course/dbcourseByName")
    Observable<VideoHttpResponse<List<Course>>>searchVideos(@Query("name") String name, @Query("currPage") int currPage);


    /*
   分类课程
    */
    @GET("course/dbcourse")
    Observable<VideoHttpResponse<List<Course>>>cgdetailedVideosForTag(
            @Query("tag_id")String tag_id,
            @Query("profession_id") String profession_id,
            @Query("currPage") int currPage);

    /*
    获取分类列表
    @根据 专业 分类
     */
    @GET("category/tag")
    Observable<VideoHttpResponse<List<Category>>> getMajors();

    /*
        根据学校 分类
        @ 待用
     */
    @GET("college/peofession")
    Observable<VideoHttpResponse<List<Category>>> getCollege();

    /*
    课程评论 由课程的评论 改为对课程的章节评论
    （改动）初步设定 使用广播刷新fragment
     */
    @GET("comment/select")
    Observable<VideoHttpResponse<List<Comment>>>getVideoAssesses(
            @Query("sectionId")String sectionId,
            @Query("currPage")int currPage);

    /*
    发表评论
     */
    @FormUrlEncoded
    @POST("comment/insert")
    Observable<VideoHttpResponse<String>> publishAssess(
            @Field("sectionId")String sectionId,
            @Field("userId")String userId,
            @Field("content")String content);

    /*
     * 登录
     */
    @GET("user/login")
    Observable<VideoHttpResponse<User>>Login(
            @Query("account") String userAccount,
            @Query("password") String userPass);

    //====================================================== 以下为废弃方案==============================================================

    /*
    推荐页
     */
    /*@GET("getRecommendVideos")
    Observable<VideoHttpResponse<List<VideoInfor>>> getRecommendVideos(@Query("currPage") int currPage, @Query("pageSize") int pageSize);*/

    /*@GET("getRecommentRollVideos")
    Observable<VideoHttpResponse<List<VideoInfor>>> getRollVideos();*/

    /*
    课程详细信息
     */
   /* @GET("getVideoDetailed")
    Observable<VideoHttpResponse<VideoInfor>> getVideoDetailed(@Query("videoId") int videoId);*/

    /*
    搜索课程
     */

    /*@GET("searchVideos")
    Observable<VideoHttpResponse<List<VideoInfor>>>searchVideos(@Query("currPage") int currPage, @Query("pageSize") int pageSize, @Query("videoTile") String videoTile);
*/
    /*
    分类课程
     */
   /* @GET("cgdetailedVideos")
    Observable<VideoHttpResponse<List<VideoInfor>>>cgdetailedVideos(
            @Query("currPage") int currPage, @Query("pageSize") int pageSize,
            @Query("majorId") int majorId, @Query("categoryId") int categoryId,
            @Query("cgdetailedId") int cgdetailedId);*/

    /*
    获取分类列表
     */
    /*@GET("getMajors")
    Observable<VideoHttpResponse<List<Major>>> getMajors();*/

    /*
    课程评论
     */
    /*@GET("videoAssessesList")
    Observable<VideoHttpResponse<List<VideoAssess>>>getVideoAssesses(@Query("currPage") int currPage, @Query("pageSize") int pageSize, @Query("videoId") int videoId);
*/
    /*
    登录
     */
    /*@GET("Login")
    Observable<VideoHttpResponse<User>>Login(@Query("userAccount") String userAccount, @Query("userPass") String userPass);*/



    /*@FormUrlEncoded
    @POST("RegisterUser")
    Observable<VideoHttpResponse<Integer>> Register(@Field("userAccount") String userAccount,
                                                    @Field("userName") String userName,
                                                    @Field("userPass") String userPass,
                                                    @Field("code") String code);*/
    /*@GET("RegisterUser")
    Observable<VideoHttpResponse<Integer>> Register(@Query("userAccount") String userAccount,
                                                    @Query("userName") String userName,
                                                    @Query("userPass") String userPass,
                                                    @Query("code") String code);*/

   /*
   发送验证码
    */
   /* @FormUrlEncoded
    @POST("confirmation")
    Observable<VideoHttpResponse<Integer>> Confirmation(@Field("phone") String phone);*/

    /*
    获取个人课程表
     */
    /*@GET("getUserSchedule")
    Observable<VideoHttpResponse<List<VideoInfor>>> getSchedule(@Query("userId") int userId);*/

    /*
    获取咨询列表
     */
    /*@GET("forumsList")
    Observable<VideoHttpResponse<List<Forum>>> getForumList(@Query("videoId") int videoId,
                                                            @Query("currPage") int currPage,
                                                            @Query("pageSize") int pageSize);
*/
    /*
    发表评论
     */
    /*@FormUrlEncoded
    @POST("publishAssess")
    Observable<VideoHttpResponse<Boolean>> publishAssess(@Field("videoId")int videoId,
                       @Field("contents")String contents,
                       @Field("userId")int userId);*/

    /*
    发表咨询
     */
    /*@FormUrlEncoded
    @POST("publishForum")
    Observable<VideoHttpResponse<Boolean>> publishForum(@Field("videoId")int videoId,
                      @Field("contents")String contents,
                      @Field("userId")int userId);*/

    /*
    添加到个人课程表
     */
    /*@FormUrlEncoded
    @POST("addSchedule")
    Observable<VideoHttpResponse<Boolean>> addSchedule(@Field("userId")int userId,
                                    @Field("videoId")int videoId);*/

}
