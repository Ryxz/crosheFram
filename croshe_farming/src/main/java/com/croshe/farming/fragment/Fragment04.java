package com.croshe.farming.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheTabView;
import com.croshe.farming.R;
import com.flyco.tablayout.listener.OnTabSelectListener;

/**
 * Created by niuyongwei on 17/5/23.
 */

public class Fragment04 extends Fragment implements View.OnClickListener {
    private LinearLayout ll_tltie,ll_back_dis;
    private Fragment[] fragments;
    private FragmentTransaction mFragmentTransaction;
    private TextView tv_editor;
    private boolean isocer;
    private CrosheTabView crosheTabView;
    int a=0;

    public int style;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_04, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initDate();
        initEvent();
    }

    public void initView(){
        tv_editor= (TextView) getActivity().findViewById(R.id.tv_editor);
        ll_tltie= (LinearLayout) getActivity().findViewById(R.id.ll_tltie);
        ll_back_dis = (LinearLayout) getActivity().findViewById(R.id.ll_back_dis);
        ll_back_dis.setOnClickListener(this);
        fragments = new Fragment[2];
        fragments[0] = this.getChildFragmentManager().findFragmentById(R.id.fragment_001);
        fragments[1] = this.getChildFragmentManager().findFragmentById(R.id.fragment_002);
        mFragmentTransaction=  getChildFragmentManager().beginTransaction().hide(fragments[1]).hide(fragments[0]);
        mFragmentTransaction.show(fragments[0]).commit();
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public void initDate(){
        String[] titles = new String[]{"购物车","菜篮子"};
        int[] unSelect = new int[]{0,0};
        int[] Select = new int[]{0,0};
        ll_tltie.removeAllViews();
        crosheTabView = new CrosheTabView(getActivity(), titles, unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if(position==0){
                    a=0;
                }else if(position==1){
                    a=1;
                }
                mFragmentTransaction=  getChildFragmentManager().beginTransaction().hide(fragments[1]).hide(fragments[0]);
                mFragmentTransaction.show(fragments[a]).commitAllowingStateLoss();
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        ll_tltie.addView(crosheTabView);
        if (style == 1){
            crosheTabView.setTab(1);
            ll_back_dis.setVisibility(View.VISIBLE);
            mFragmentTransaction=  getChildFragmentManager().beginTransaction().hide(fragments[1]).hide(fragments[0]);
            mFragmentTransaction.show(fragments[1]).commit();
        }else if (style == 2){
            ll_back_dis.setVisibility(View.VISIBLE);
        }
    }

    public void initEvent(){
        tv_editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isocer) {
                    Intent in = new Intent("onCloseMain1");
                    getContext().sendBroadcast(in);
                    Intent in1 = new Intent("seltce1");
                    getContext().sendBroadcast(in1);
                    tv_editor.setText("完成");
                }else{
                    Intent in = new Intent("onCloseMain2");
                    getContext().sendBroadcast(in);
                    Intent in1 = new Intent("seltce2");
                    getContext().sendBroadcast(in1);
                    tv_editor.setText("编辑");
                }
                isocer=!isocer;

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back_dis:
                getContext().sendBroadcast(new Intent("f4finish"));
                style = 0;
                handler.sendEmptyMessageDelayed(0,1000);
                break;
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ll_back_dis.setVisibility(View.GONE);
        }
    };
}
