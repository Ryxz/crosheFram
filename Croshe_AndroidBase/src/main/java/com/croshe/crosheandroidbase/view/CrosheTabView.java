package com.croshe.crosheandroidbase.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.croshe.crosheandroidbase.R;
import com.croshe.crosheandroidbase.entity.TabEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * 顶部tab选项卡适用于1-5个  根据自己实际情况而定
 */

public class CrosheTabView extends FrameLayout {

    private CommonTabLayout mTabLayout_2;
    private String[] mTitles = null;
    private int[] mIconUnselectIds = null;
    private int[] mIconSelectIds = null;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private OnTabSelectListener onTabSelectListener;
    private int selectCorlor;//选中时文字颜色
    private int unSelectCorlor;//未选中时文字颜色

    public CrosheTabView(Context context, String[] mTitles, int[] mIconUnselectIds, int mIconSelectIds[], int selectCorlor, int unSelectCorlor, OnTabSelectListener onTabSelectListener) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.croshe_tab_view, this);
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


    public void setTab(int position){
        mTabLayout_2.setCurrentTab(position);
    }


    public void initView() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout_2 = (CommonTabLayout) findViewById(R.id.tl_4);
        mTabLayout_2.setTabData(mTabEntities);
        mTabLayout_2.setTextSelectColor(selectCorlor);
        mTabLayout_2.setTextUnselectColor(unSelectCorlor);
        mTabLayout_2.setOnTabSelectListener(onTabSelectListener);
    }

    public void setMsg(int msg1,int msg2,int msg3,int msg4){
        if (msg1!=0){
            mTabLayout_2.showMsg(0,msg1);
        }
//        mTabLayout_2.showMsg(1,msg2);
//        mTabLayout_2.showMsg(2,msg3);
        if (msg4!=0){
            mTabLayout_2.showMsg(3,msg4);
        }
    }
    public void initDateView(){
        for (int i = 0; i < mTitles.length; i++){
        }
    }

    public OnTabSelectListener getOnTabSelectListener() {
        return onTabSelectListener;
    }

    public void setOnTabSelectListener(OnTabSelectListener onTabSelectListener) {
        this.onTabSelectListener = onTabSelectListener;
    }
}
