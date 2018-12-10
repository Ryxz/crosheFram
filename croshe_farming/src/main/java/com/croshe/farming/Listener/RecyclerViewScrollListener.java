package com.croshe.farming.Listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.croshe.crosheandroidbase.util.L;
import com.croshe.farming.AppContext;

/**
 * RecyclerView 滑动监听事件
 */
public class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    Context context;
    OnScrolledListener onScrolledListener;
    public RecyclerViewScrollListener(OnScrolledListener onScrolledListener) {
        super();
        this.onScrolledListener = onScrolledListener;this.context=context;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        onScrolledListener.onScrolled(recyclerView, dx, dy);
    }


    //当屏幕停止滚动时为0；当屏幕滚动且用户使用的触碰或手指还在屏幕上时为1；由于用户的操作，屏幕产生惯性滑动时为2
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

        L.d("TAG",newState+"");
        //根据newState状态做处理
        switch (newState) {
            case 0:
                Glide.with(AppContext.getInstance().getApplicationContext()).resumeRequests();
                break;

            case 1:
                Glide.with(AppContext.getInstance().getApplicationContext()).pauseRequests();
                break;

            case 2:
                Glide.with(AppContext.getInstance().getApplicationContext()).pauseRequests();
                break;
        }
    }

}
