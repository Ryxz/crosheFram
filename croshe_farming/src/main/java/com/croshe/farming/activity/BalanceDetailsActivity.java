package com.croshe.farming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.BalanceInfo;
import com.croshe.farming.Entity.ReceiveAddInfo;
import com.croshe.farming.R;
import com.croshe.farming.adapter.BalanceDetailAdapter;
import com.croshe.farming.adapter.ReceiveAddressAdapter;
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
 * Created by Administrator on 2017/6/28.
 */

public class BalanceDetailsActivity extends CrosheBaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate{
    private RecyclerView recycler_detail;
    private BGARefreshLayout mRefreshLayout;
    private BalanceDetailAdapter detailAdapter;
    private List<BalanceInfo> list = new ArrayList<>();
    private ImageView img_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_balancedetails);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        img_back = (ImageView) findViewById(R.id.img_balance_detail_back);

        detailAdapter = new BalanceDetailAdapter(context,list);
        recycler_detail = (RecyclerView) findViewById(R.id.recyclerView_balance_detail);
        recycler_detail.setLayoutManager(new LinearLayoutManager(this));
        recycler_detail.setAdapter(detailAdapter);
        mRefreshLayout = (BGARefreshLayout) findViewById(R.id.mRefreshLayout_balance_detail);
        mRefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder bgaMoocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(context, true);
        bgaMoocStyleRefreshViewHolder.setUltimateColor(android.R.color.white);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(context, true));
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        img_back.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_balance_detail_back:
                finish();
                break;
        }
    }




    int page = 1;
    //获得明细列表
    public void refresh(){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("page", "1");
        ServerRequest.requestHttp(this, ServerUrl.showUserRecordMoney, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<BalanceInfo> balanceInfos = JSON.parseArray(json,BalanceInfo.class);
                list.clear();
                if (balanceInfos!=null&&balanceInfos.size()>0){
                    list.addAll(balanceInfos);
                    page = 1;
                    mRefreshLayout.setVisibility(View.VISIBLE);
                } else {
                    mRefreshLayout.setVisibility(View.GONE);
                }
                detailAdapter.notifyDataSetChanged();
                mRefreshLayout.endRefreshing();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                mRefreshLayout.endRefreshing();
                mRefreshLayout.setVisibility(View.GONE);
            }
        });
    }
    //停止刷新
    public void getAddressLoad(){
        page++;
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("page", page);
        ServerRequest.requestHttp(this, ServerUrl.showUserAddress, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<BalanceInfo> balanceInfos = JSON.parseArray(json,BalanceInfo.class);
                if (balanceInfos!=null&&balanceInfos.size()>0){
                    list.addAll(balanceInfos);
                    detailAdapter.notifyDataSetChanged();
                } else {
                    page--;
                }
                mRefreshLayout.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                page--;
                mRefreshLayout.endLoadingMore();
            }
        });
    }

    /**
     * 开始刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        refresh();
    }

    /**
     * 开始加载更多。由于监听了ScrollView、RecyclerView、AbsListView滚动到底部的事件，所以这里采用返回boolean来处理是否加载更多。否则使用endLoadingMore方法会失效
     *
     * @param refreshLayout
     * @return 如果要开始加载更多则返回true，否则返回false。（返回false的场景：没有网络、一共只有x页数据并且已经加载了x页数据了）
     */
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (null != list && list.size() >= 1) {
            getAddressLoad();
        } else {
            return false;
        }
        return true;
    }
}
