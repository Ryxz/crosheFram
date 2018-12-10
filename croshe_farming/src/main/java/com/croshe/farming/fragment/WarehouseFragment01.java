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
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.BasketInfo;
import com.croshe.farming.Entity.TypeClassEnumInfo;
import com.croshe.farming.Entity.WarehouseInfo;
import com.croshe.farming.R;
import com.croshe.farming.adapter.AgriculturalAdapter;
import com.croshe.farming.adapter.ListAdapter;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/6/9.
 */
//我的农资
public class WarehouseFragment01 extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate{
    private BGARefreshLayout bgalayout_warehouse01;
    private RecyclerView er_warehouse01,er_warehouse02;
    private List<TypeClassEnumInfo> infos = new ArrayList<>();
    private List<TypeClassEnumInfo> infoList = new ArrayList<>();
    private ListAdapter adapter;
    private int id=0;

    private List<BasketInfo> warehouseInfoList = new ArrayList<>();
    private AgriculturalAdapter agriculturalAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.warehouse_frafment01,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initDate();
    }

    public void initView() {
        bgalayout_warehouse01= (BGARefreshLayout) getActivity().findViewById(R.id.bgalayout_warehouse01);
        bgalayout_warehouse01.setDelegate(this);
        bgalayout_warehouse01.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        er_warehouse01= (RecyclerView) getActivity().findViewById(R.id.er_warehouse01);
        er_warehouse02= (RecyclerView) getActivity().findViewById(R.id.er_warehouse02);
    }

    public void initDate() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        er_warehouse01.setLayoutManager(manager);
        agriculturalAdapter = new AgriculturalAdapter(warehouseInfoList,getActivity());
        er_warehouse01.setAdapter(agriculturalAdapter);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        er_warehouse02.setLayoutManager(manager1);
        adapter = new ListAdapter(infoList,getContext());
        er_warehouse02.setAdapter(adapter);
        TypeClassEnum();
        registerBoradcastReceiver();
        getList();
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("nongzi");

        // 注册广播
        getContext().registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            id = Integer.parseInt(intent.getStringExtra("id"));
            getList();
        }
    };

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getList();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
//    获取分类
    public void TypeClassEnum(){
        Map<String,Object> map = new HashMap<>();
        ServerRequest.requestHttp(getActivity(), ServerUrl.TypeClassEnum, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                infos= JSON.parseArray(json,TypeClassEnumInfo.class);
                if(!StringUtils.isEmpty(infos.toString())){
                    infoList.clear();
                    infoList.addAll(infos);
                }
                adapter.notifyDataSetChanged();
                bgalayout_warehouse01.endRefreshing();
                bgalayout_warehouse01.endLoadingMore();
            }
            @Override
            public void onFailure(String msg) {
            }
        });
    }
    //    获取我的仓库列表农资
    public void getList(){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("typeClass",id);
        ServerRequest.requestHttp(getContext(), ServerUrl.getList1, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
             WarehouseInfo warehouseInfo= JSON.parseObject(json,WarehouseInfo.class);
                if(!StringUtils.isEmpty(warehouseInfo.toString())){
                    String count=warehouseInfo.getCount();
                    Intent in = new Intent("nongzicount");
                    in.putExtra("count",count);
                    getContext().sendBroadcast(in);
                    warehouseInfoList.clear();
                    warehouseInfoList.addAll( warehouseInfo.getUserStoreModels());
                }
                bgalayout_warehouse01.endRefreshing();
                bgalayout_warehouse01.endLoadingMore();
                agriculturalAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(String msg) {
                bgalayout_warehouse01.endRefreshing();
                bgalayout_warehouse01.endLoadingMore();
            }
        });
    }


}
