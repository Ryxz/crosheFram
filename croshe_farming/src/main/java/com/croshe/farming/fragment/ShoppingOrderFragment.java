package com.croshe.farming.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheFragmentBaseActivity;
import com.croshe.crosheandroidbase.view.CrosheTabView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.OrderInfo;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.R;
import com.croshe.farming.adapter.ShopOrderAdapter;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.util.DialogUtil;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/7/6.
 */
//[{"id":0,"text":"待付款"},{"id":1,"text":"已付款"}
//    [{"id":0,"text":"购物订单"},{"id":1,"text":"配送订单"}]
public class ShoppingOrderFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    LinearLayout ll_my_shop_order;
    private SwipeMenuRecyclerView recycler_shop;
    private BGARefreshLayout mRefreshLayout;
    ShopOrderAdapter orderAdapter;
    private List<OrderModels> list = new ArrayList<>();
    int type = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_my_shop_order, container, false);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ll_my_shop_order = (LinearLayout) getActivity().findViewById(R.id.ll_my_shop_order);
        orderAdapter = new ShopOrderAdapter(getActivity(), list);
        recycler_shop = (SwipeMenuRecyclerView) getActivity().findViewById(R.id.recyclerView_shopping);
        recycler_shop.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_shop.setAdapter(orderAdapter);
        mRefreshLayout = (BGARefreshLayout) getActivity().findViewById(R.id.mRefreshLayout_shopping);
        mRefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder bgaMoocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(getActivity(), true);
        bgaMoocStyleRefreshViewHolder.setUltimateColor(android.R.color.white);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        mRefreshLayout.beginRefreshing();
        headChoose();
        registerBoradcastReceiver();
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("refreshDistribution");
        myIntentFilter.addAction("deleteRefrsh");
        myIntentFilter.addAction("finishEvaluate");
        // 注册广播
        getContext().registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("refreshDistribution")){
                refresh(1);
            }else if (action.equals("deleteRefrsh")){
                refresh(1);
            }else if(action.equals("finishEvaluate")){
                refresh(1);
            }
        }
    };



    public void headChoose() {
        //选项卡固定个数
        String[] titles = new String[]{"已支付", "未支付",};
        int[] unSelect = new int[]{0, 0};
        int[] Select = new int[]{0, 0};
        CrosheTabView crosheTabView = new CrosheTabView(getActivity(), titles,
                unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    type = 1;
                    getActivity().sendBroadcast(new Intent("chanceType1"));
                } else if (position == 1) {
                    type = 0;
                    getActivity().sendBroadcast(new Intent("chanceType0"));
                }
                refresh(1);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        ll_my_shop_order.addView(crosheTabView);
    }

    int page = 1;
    boolean isLoadDone=false;

    public void refresh(final int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("orderState", type);
        map.put("orderType", 0);
        map.put("page", page);
        this.page = page;
        ServerRequest.requestHttp(getActivity(), ServerUrl.showMyOrders, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<OrderModels> orders = JSON.parseArray(json, OrderModels.class);
                if (page == 1) {
                    list.clear();
                }
                if (orders != null && orders.size() > 0) {
                    list.addAll(orders);
                    isLoadDone = orders.size() == 0;
                }else{
                    isLoadDone=true;
                }
                orderAdapter.notifyDataSetChanged();
                mRefreshLayout.endRefreshing();
                mRefreshLayout.endLoadingMore();

            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                mRefreshLayout.endRefreshing();
                mRefreshLayout.endLoadingMore();
            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        refresh(1);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (!isLoadDone) {
            refresh(page + 1);
            return true;
        }
        return false;
    }
}
