package com.croshe.crosheandroidbase.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.croshe.crosheandroidbase.CroSheAppContext;
import com.croshe.crosheandroidbase.R;
import com.croshe.crosheandroidbase.util.DensityUtils;
import com.croshe.crosheandroidbase.util.GlideUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


/**
 * Created by niuyongwei on 17/4/22.
 */
//自己滚动的ViewPageView
public class CrosheViewPageView extends FrameLayout {
    public CrosheViewPageView(Context context, List<String> images, int height) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.croshe_my_viewpage, this);
        Banner banner = (Banner) findViewById(R.id.banner);
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) banner.getLayoutParams();
        linearParams.height = DensityUtils.dip2px(context, height);
        banner.setLayoutParams(linearParams);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            GlideUtil.displayImage(context,(String) path, imageView);
        }

    }

}
