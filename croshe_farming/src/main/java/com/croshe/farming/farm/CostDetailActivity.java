package com.croshe.farming.farm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.CostEntity;
import com.croshe.farming.Entity.OrderInfo;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.FramAdapter.CostDetailAdapter;
import com.croshe.farming.R;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.L;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/7/12.
 */

public class CostDetailActivity extends CrosheBaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private TextView textView;
    private LinearLayout ll_back;
    private RecyclerView recycler_cost_list;
    private CostDetailAdapter detailAdapter;
    private List<CostEntity> list = new ArrayList<>();
    private BGARefreshLayout mRefreshLayout;
    private String textname ;
    private int costType;
    private String detailsId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cost_detail);
        textname = getIntent().getStringExtra("textname");
        costType = getIntent().getIntExtra("costType",0);
        detailsId = getIntent().getStringExtra("detailsId");
        initView();
        initClick();
    }

    @Override
    public void initView() {
        textView = (TextView) findViewById(R.id.text_head);
        textView.setText(textname);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        recycler_cost_list = (RecyclerView) findViewById(R.id.recycler_cost_list);
        detailAdapter = new CostDetailAdapter(context, list);
        recycler_cost_list.setAdapter(detailAdapter);
        recycler_cost_list.setLayoutManager(new LinearLayoutManager(this));
        mRefreshLayout = (BGARefreshLayout) findViewById(R.id.refresh_cost);
        mRefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder bgaMoocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(this, true);
        bgaMoocStyleRefreshViewHolder.setUltimateColor(android.R.color.white);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back:
                finish();
                break;
        }
    }
    int page = 1;
    boolean isLoadDone=false;
    public void refresh(final int page) {
        HttpRequest.showCostList(context, page, costType, detailsId, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<CostEntity> orders = JSON.parseArray(json, CostEntity.class);
                if (page == 1) {
                    list.clear();
                }
                if (orders != null && orders.size() > 0) {
                    list.addAll(orders);
                    isLoadDone = orders.size() == 0;
                } else{
                    isLoadDone=true;
                }
                detailAdapter.notifyDataSetChanged();
                mRefreshLayout.endRefreshing();
                mRefreshLayout.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
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
