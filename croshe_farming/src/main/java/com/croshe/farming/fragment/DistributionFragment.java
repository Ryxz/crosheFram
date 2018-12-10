package com.croshe.farming.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.view.CrosheTabView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.OrderInfo;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.Entity.OrderNumEntity;
import com.croshe.farming.R;
import com.croshe.farming.adapter.DistributionAdapter;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.util.Constant;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 配送订单
 * Created by Administrator on 2017/7/1.
 */

public class DistributionFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate{
    LinearLayout ll_diehead;
    private RecyclerView recycler_shop;
    private BGARefreshLayout mRefreshLayout;
    private DistributionAdapter distributionAdapter;
    private List<OrderModels> list =new ArrayList<>();
    private int type=2;
    CrosheTabView crosheTabView;
    private int count1,count2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dietribution_view,container,false);
        return view;
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("refreshOrder");
        // 注册广播
        getContext().registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("refreshOrder")) {
                refresh();
            }
        }
    };


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ll_diehead = (LinearLayout) getActivity().findViewById(R.id.ll_diehead);
        distributionAdapter = new DistributionAdapter(getActivity(),list);
        recycler_shop = (RecyclerView)getActivity().findViewById(R.id.recyclerView_distribution);
        recycler_shop.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_shop.setAdapter(distributionAdapter);
        mRefreshLayout = (BGARefreshLayout)getActivity().findViewById(R.id.mRefreshLayout_distribution);
        mRefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder bgaMoocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(getActivity(), true);
        bgaMoocStyleRefreshViewHolder.setUltimateColor(android.R.color.white);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        mRefreshLayout.beginRefreshing();
        showMyOrdersCount();
        headChoose();
        registerBoradcastReceiver();
    }
    public void headChoose(){
        //选项卡固定个数
        //    {"id":2,"text":"待配送"},{"id":3,"text":"已配送"},{"id":4,"text":"已签收"},{"id":5,"text":"待评价"}]
        String[] titles = new String[]{"待配送", "已配送","已签收"};
        int[] unSelect = new int[]{0, 0, 0};
        int[] Select = new int[]{0, 0, 00};
        crosheTabView = new CrosheTabView(getActivity(), titles, unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0){
                    type = Constant.Type3;
                } else if(position == 1){
                    type = Constant.Type4;
                } else if (position == 2){
                    type = Constant.Type5;
                }
//                else {
//                    type = Constant.Type6;
//                }
                refresh();
            }
            @Override
            public void onTabReselect(int position) {

            }
        });

        ll_diehead.addView(crosheTabView);
    }
    int page = 1;
    public void refresh(){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("orderState",type);
        map.put("orderType", 1);
        map.put("page", "1");
        ServerRequest.requestHttp(getActivity(), ServerUrl.showMyOrders, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<OrderModels> orders = JSON.parseArray(json, OrderModels.class);
                list.clear();
                if (orders!=null&&orders.size()>0){
                    list.addAll(orders);
                    page = 1;
                    mRefreshLayout.setVisibility(View.VISIBLE);
                } else {
                    mRefreshLayout.setVisibility(View.GONE);
                }
                distributionAdapter.notifyDataSetChanged();
                mRefreshLayout.endRefreshing();
                mRefreshLayout.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                mRefreshLayout.endRefreshing();
            }
        });
    }
    //停止刷新
    public void refreshLoad(){
        page++;
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("orderState",type);
        map.put("orderType", 1);
        map.put("page", page);
        ServerRequest.requestHttp(getActivity(), ServerUrl.showMyOrders, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<OrderModels> orders = JSON.parseArray(json, OrderModels.class);
//                OrderInfo order = JSON.parseObject(json,OrderInfo.class);
//                List<OrderModels> orders = order.getOrderModels();
                if (orders!=null&&orders.size()>0){
                    list.addAll(orders);
                    distributionAdapter.notifyDataSetChanged();
                } else {
                    page--;
                }
                mRefreshLayout.endRefreshing();
                mRefreshLayout.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                page--;
                mRefreshLayout.endLoadingMore();
            }
        });
    }

    public void showMyOrdersCount(){
        HttpRequest.showMyOrdersCount(getActivity(), new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                OrderNumEntity orderNum = JSON.parseObject(json,OrderNumEntity.class);
                if (orderNum!=null){
                    count1 = orderNum.getDps();
                    count2 = orderNum.getDpj();
                    crosheTabView.setMsg(count1,0,0,count2);
                }

            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        refresh();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (list!=null&&list.size()>5){
            refreshLoad();
        }else {
            return false;
        }
        return true;
    }
}
