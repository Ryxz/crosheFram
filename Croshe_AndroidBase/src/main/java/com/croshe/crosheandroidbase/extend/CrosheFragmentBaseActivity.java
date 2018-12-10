package com.croshe.crosheandroidbase.extend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * 主页面的基类
 */

public class CrosheFragmentBaseActivity extends AppCompatActivity {

    public Fragment[] mFragment;
    public FragmentManager mFragmentManager;
    public FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mFragmentManager = getSupportFragmentManager();
    }
    public static ArrayList<CrosheBaseActivity> BaseList = new ArrayList<CrosheBaseActivity>();
    // 一键退出
    protected void finishAll() {
        for (CrosheBaseActivity y : BaseList) {
            y.finish();
        }
    }

    /**
     * 设置碎片
     *
     * @param fragments
     */
    public void setFragment(Fragment[] fragments) {
        this.mFragment = fragments;
        initFragment();

    }

    public void initFragment() {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        for (int i = 0; i < mFragment.length; i++) {
            mFragmentTransaction.hide(mFragment[i]);
        }
        mFragmentTransaction.show(mFragment[0]).commit();
    }

    /**
     * 根据index隐藏碎片
     *
     * @param index
     */
    public void hideFragmentForIndex(int index) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        for (int i = 0; i < mFragment.length; i++) {
            mFragmentTransaction.hide(mFragment[i]);
        }
        mFragmentTransaction.show(mFragment[index]).commit();
    }
}
