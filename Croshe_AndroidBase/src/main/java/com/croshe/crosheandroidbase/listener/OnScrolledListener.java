package com.croshe.crosheandroidbase.listener;


import android.support.v7.widget.RecyclerView;

/**
 * RecyclerView 滚动监听
 */

public abstract class OnScrolledListener {
    public abstract void onScrolled(RecyclerView recyclerView, int dx, int dy);
}
