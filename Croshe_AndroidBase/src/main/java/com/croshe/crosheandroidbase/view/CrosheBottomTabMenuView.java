package com.croshe.crosheandroidbase.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.croshe.crosheandroidbase.R;
import com.croshe.crosheandroidbase.entity.TabEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * 底部菜单tab
 */

public class CrosheBottomTabMenuView extends FrameLayout {

    private CommonTabLayout mTabLayout_2;
    private String[] mTitles = null;
    private int[] mIconUnselectIds = null;
    private int[] mIconSelectIds = null;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private OnTabSelectListener onTabSelectListener;
    private int selectCorlor;//选中时文字颜色
    private int unSelectCorlor;//未选中时文字颜色
    private String type;

    /**
     * @param context             上下文
     * @param mTitles             标题集合
     * @param mIconUnselectIds    未选中图标集合
     * @param mIconSelectIds      选中图标集合
     * @param selectCorlor        选中文字颜色
     * @param unSelectCorlor      未选中文字颜色
     * @param onTabSelectListener tab监听
     */
    public CrosheBottomTabMenuView(Context context, String[] mTitles, int[] mIconUnselectIds, int mIconSelectIds[], int selectCorlor, int unSelectCorlor, OnTabSelectListener onTabSelectListener) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.croshe_bottom_tab_menu, this);
        this.mTitles = mTitles;
        this.mIconUnselectIds = mIconUnselectIds;
        this.mIconSelectIds = mIconSelectIds;
        this.onTabSelectListener = onTabSelectListener;
        this.selectCorlor = selectCorlor;
        this.unSelectCorlor = unSelectCorlor;
        if (mTitles.length != mIconUnselectIds.length) {
            return;
        }
        if (mTitles.length != mIconSelectIds.length) {
            return;
        }
        initView();
    }
    private LinearLayout ll_message;
    public CrosheBottomTabMenuView(Context context, String[] mTitles, int[] mIconUnselectIds, int mIconSelectIds[], int selectCorlor, int unSelectCorlor, OnTabSelectListener onTabSelectListener,String type) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.croshe_bottom_tab_menu, this);
        this.mTitles = mTitles;
        this.mIconUnselectIds = mIconUnselectIds;
        this.mIconSelectIds = mIconSelectIds;
        this.onTabSelectListener = onTabSelectListener;
        this.selectCorlor = selectCorlor;
        this.unSelectCorlor = unSelectCorlor;
        this.type = type;
        if (type!=null){
            ll_message = (LinearLayout) findViewById(R.id.ll_message);
            ll_message.setVisibility(VISIBLE);
        }
        if (mTitles.length != mIconUnselectIds.length) {
            return;
        }
        if (mTitles.length != mIconSelectIds.length) {
            return;
        }
        initView();
    }


    public void initView() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout_2 = (CommonTabLayout) findViewById(R.id.croshe_tl_2);
        mTabLayout_2.setTabData(mTabEntities);
        mTabLayout_2.setTextSelectColor(selectCorlor);
        mTabLayout_2.setTextUnselectColor(unSelectCorlor);
        mTabLayout_2.setOnTabSelectListener(onTabSelectListener);
    }
}
