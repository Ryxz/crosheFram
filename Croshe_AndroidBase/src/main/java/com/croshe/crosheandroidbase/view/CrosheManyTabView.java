package com.croshe.crosheandroidbase.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.croshe.crosheandroidbase.R;
import com.croshe.crosheandroidbase.entity.TabEntity;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * 无限tab滚动
 */

public class CrosheManyTabView extends FrameLayout {

    private SlidingTabLayout mTabLayout_2;
    private String[] mTitles = null;
    private int[] mIconUnselectIds = null;
    private int[] mIconSelectIds = null;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private OnTabSelectListener onTabSelectListener;
    private int selectCorlor;//选中时文字颜色
    private int unSelectCorlor;//未选中时文字颜色
    private  MyPagerAdapter myPagerAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private AppCompatActivity appCompatActivity;
    public CrosheManyTabView(AppCompatActivity appCompatActivity,Context context, String[] mTitles, int[] mIconUnselectIds, int mIconSelectIds[], int selectCorlor, int unSelectCorlor, OnTabSelectListener onTabSelectListener) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.croshe_many_tab_view, this);
        this.mTitles = mTitles;
        this.mIconUnselectIds = mIconUnselectIds;
        this.mIconSelectIds = mIconSelectIds;
        this.onTabSelectListener = onTabSelectListener;
        this.selectCorlor = selectCorlor;
        this.unSelectCorlor = unSelectCorlor;
        this.appCompatActivity=appCompatActivity;
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
        mTabLayout_2 = (SlidingTabLayout) findViewById(R.id.tl_3);
        for (String title : mTitles) {
            mFragments.add(CrosheSimpleCardFragment.getInstance(title));
        }
        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        myPagerAdapter = new MyPagerAdapter(appCompatActivity.getSupportFragmentManager());
        vp.setAdapter(myPagerAdapter);
        mTabLayout_2.setViewPager(vp);
        mTabLayout_2.setTextSelectColor(selectCorlor);
        mTabLayout_2.setTextUnselectColor(unSelectCorlor);
        mTabLayout_2.setOnTabSelectListener(onTabSelectListener);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
