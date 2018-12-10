package com.croshe.farming.farm;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.Entity.FramViewInfo;
import com.croshe.farming.R;
import com.croshe.farming.adapter.FramViewAdapter;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
//查看农场
public class FarmViewActivity extends CrosheBaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private BGARefreshLayout bgalayout_farm;
    private RecyclerView recyclerView;
    private List<FramViewInfo> framViewInfos = new ArrayList<>();
    private List<FramViewInfo> framViewInfoList = new ArrayList<>();
    private FramViewAdapter adapter;
    private ImageView iv_message;
    private LinearLayout iv_ldft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_view);

        initView();
        initData();
        initClick();

    }



    @Override
    public void initView() {
        iv_message= (ImageView) findViewById(R.id.iv_message);
        iv_ldft= (LinearLayout) findViewById(R.id.iv_left_6);
        bgalayout_farm= (BGARefreshLayout) findViewById(R.id.bgalayout_farm);
        recyclerView= (RecyclerView) findViewById(R.id.er_farm);
        //        刷新
        bgalayout_farm.setDelegate(this);
        bgalayout_farm.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter= new FramViewAdapter(framViewInfoList,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        showFarm();
    }

    @Override
    public void initClick() {
        iv_ldft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_left_6:
                    finish();
                    break;
            }
    }
//    查看农场
    public void showFarm(){
        Map<String,Object> map = new HashMap<>();
        map.put("page",1);
        ServerRequest.requestHttp(this, ServerUrl.showFarm, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                framViewInfos= JSON.parseArray(json,FramViewInfo.class);
                framViewInfoList.clear();
                if(!StringUtils.isEmpty(framViewInfos.toString())){
                    page=1;
                    framViewInfoList.addAll(framViewInfos);
                    adapter.notifyDataSetChanged();
                    bgalayout_farm.endRefreshing();
                }
            }

            @Override
            public void onFailure(String msg) {
                bgalayout_farm.endRefreshing();
            }
        });
    }
    int page=1;
    public void showFarmLoad(){
        page++;
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        ServerRequest.requestHttp(this, ServerUrl.showFarm, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                framViewInfos= JSON.parseArray(json,FramViewInfo.class);

                if(StringUtils.isEmpty(framViewInfos.toString())){
                    framViewInfoList.addAll(framViewInfos);
                    adapter.notifyDataSetChanged();

                }else {
                    page--;
                }
                bgalayout_farm.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                page--;
                bgalayout_farm.endLoadingMore();
            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        showFarm();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (null != framViewInfoList&&framViewInfoList.size()>=2) {
            showFarmLoad();
        } else {
            return false;
        }
        return true;
    }
    }

