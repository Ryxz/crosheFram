package com.croshe.farming.Listener;

/**
 * Created by Administrator on 2017/7/21.
 */

import android.support.v7.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 滑动自动加载监听器
 */
public class AutoLoadScrollListener extends RecyclerView.OnScrollListener {

    private ImageLoader imageLoader;
//        private final boolean pauseOnScroll;
//        private final boolean pauseOnFling;

    public AutoLoadScrollListener(ImageLoader imageLoader) {
        super();
//            this.pauseOnScroll = pauseOnScroll;
//            this.pauseOnFling = pauseOnFling;
        this.imageLoader = imageLoader;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

    }

    //当屏幕停止滚动时为0；当屏幕滚动且用户使用的触碰或手指还在屏幕上时为1；由于用户的操作，屏幕产生惯性滑动时为2
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

        //根据newState状态做处理
        if (imageLoader != null) {
            switch (newState) {
                case 0:
                    imageLoader.resume();
                    break;

                case 1:
                    imageLoader.pause();
                    break;

                case 2:
                    imageLoader.pause();
                    break;
            }
        }
    }
}