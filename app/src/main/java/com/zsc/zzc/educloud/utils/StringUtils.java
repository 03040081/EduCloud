package com.zsc.zzc.educloud.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;

import java.util.Random;

public class StringUtils {

    private static final String HOSTIMG="http://47.93.11.130:8080/zsccloud/Images/";
    private static final String HOSTVIDEO="http://47.93.11.130:8080/zsccloud/Vedios/";

    private static final String FILEIMG="http://111.230.87.210/Images/";
    private static final String FILEVEDIO="http://111.230.87.210/Vedio/";

    public static String getHostImg(String str){
        return HOSTIMG+str;
    }

    public static String getHostVideo(String courseId,String subTitle){
        return HOSTVIDEO+courseId+"/"+subTitle+".mp4";
    }

    /**
     * 去掉特殊字符
     *
     * @param s
     * @return
     */
    public static String removeOtherCode(String s) {
        if (TextUtils.isEmpty(s))
            return "";
        s = s.replaceAll("\\<.*?>|\\n", "");
        return s;
    }

    /**
     * 判断非空
     *
     * @param str
     * @return
     */
    public static String isEmpty(String str) {
        String result = TextUtils.isEmpty(str) ? "" : str;
        return result;
    }

    /**
     * 根据Url获取catalogId
     *
     * @param url
     * @return
     */
    public static String getCatalogId(String url) {
        String catalogId = "";
        String key = "catalogId=";
        if (!TextUtils.isEmpty(url) && url.contains("="))
            catalogId = url.substring(url.indexOf(key) + key.length(), url.lastIndexOf("&"));
        return catalogId;
    }

    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max) % (max - min + 1) + min;
    }

    public static String getErrorMsg(String msg) {
        if (msg.contains("*")) {
            msg = msg.substring(msg.indexOf("*") + 1);
            return msg;
        } else
            return "";
    }

    public static void setIconDrawable(Context mContext, TextView view, IIcon icon, int size, int padding) {
        view.setCompoundDrawablesWithIntrinsicBounds(new IconicsDrawable(mContext)
                        .icon(icon)
                        .color(Color.WHITE)
                        .sizeDp(size),
                null, null, null);
        view.setCompoundDrawablePadding(ScreenUtil.dip2px(mContext, padding));
    }
}
