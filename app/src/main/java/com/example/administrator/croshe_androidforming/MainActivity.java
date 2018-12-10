package com.example.administrator.croshe_androidforming;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.croshe.crosheandroidbase.extend.CrosheFragmentBaseActivity;
import com.croshe.crosheandroidbase.view.CrosheBottomTabMenuView;
import com.flyco.tablayout.listener.OnTabSelectListener;

public class MainActivity extends CrosheFragmentBaseActivity{
    private LinearLayout ll_boottom_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        createFragment();
//        createMenu();
    }

    /**
     * 创建碎片
     */
//    public void createFragment() {
//        Fragment[] mFragment = new Fragment[5];
//        mFragment[0] = this.mFragmentManager
//                .findFragmentById(R.id.fragment_01);
//        mFragment[1] = this.mFragmentManager
//                .findFragmentById(R.id.fragment_02);
//        mFragment[2] = this.mFragmentManager
//                .findFragmentById(R.id.fragment_03);
//        mFragment[3] = this.mFragmentManager
//                .findFragmentById(R.id.fragment_04);
//        mFragment[4] = this.mFragmentManager
//                .findFragmentById(R.id.fragment_05);
//        setFragment(mFragment);
//    }

    /*
    /底部菜单
     */
//    public void createMenu() {
//        ll_boottom_tab = (LinearLayout) findViewById(R.id.ll_boottom_tab);
//        String[] titles = new String[]{
//                "首页", "分类", "哈偶", "耶耶", "哦哦"
//        };
//        int[] unSelect = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
//        int[] Select = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
//        CrosheBottomTabMenuView crosheBottomTabMenuView = new CrosheBottomTabMenuView(MainActivity.this, titles, unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//                hideFragmentForIndex(position);
//            }
//
//            @Override
//            public void onTabReselect(int position) {
//
//            }
//        });
//        ll_boottom_tab.addView(crosheBottomTabMenuView);
//    }
//
//
//
}
