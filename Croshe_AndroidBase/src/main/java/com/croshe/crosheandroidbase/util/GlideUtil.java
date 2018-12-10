package com.croshe.crosheandroidbase.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * 图片加载工具类
 */

public class GlideUtil {

    /**
     * 普通加载图片方法
     *
     * @param context   上下文
     * @param imgUrl    图片地址
     * @param imageView 控件
     */
    public static void displayImage(Context context, String imgUrl, ImageView imageView) {
        Glide.with(context).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().crossFade().into(imageView);
    }

    /**
     * @param context   上下文
     * @param imgUrl    图片地址
     * @param imageView 控件
     * @param defImg    图片加载时显示的图片
     * @param errorImg  图片加载错误显示的图
     */
    public static void displayImage(Context context, String imgUrl, ImageView imageView, int defImg, int errorImg) {
        Glide.with(context).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL).error(errorImg).placeholder(defImg).centerCrop().crossFade().into(imageView);
    }
}
