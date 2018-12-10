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
import com.croshe.farming.Entity.NewsInfo;
import com.croshe.farming.R;
import com.croshe.farming.adapter.FramMessageAdapter;
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
 * Created by Administrator on 2017/7/1.
 */

public class FramMessageActivity extends CrosheBaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate{

    private ImageView img_frammsg_back;
    private BGARefreshLayout mBGARefreshLayout;
    private RecyclerView recyclerView;
    private FramMessageAdapter adapter;
    List<NewsInfo> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fram_message);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        img_frammsg_back = (ImageView) findViewById(R.id.img_frammsg_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_fram_message);
        adapter = new FramMessageAdapter(context,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mBGARefreshLayout = (BGARefreshLayout) findViewById(R.id.mRefreshLayout_fram_message);
        mBGARefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder bgaMoocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(context, true);
        bgaMoocStyleRefreshViewHolder.setUltimateColor(android.R.color.white);
        mBGARefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(context, true));
        mBGARefreshLayout.beginRefreshing();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        img_frammsg_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_frammsg_back:
                finish();
                break;
        }

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (null != list && list.size() >= 6) {
            loadRefresh();
        } else {
            return false;
        }
        return true;
    }
    int page = 1;
    public void getData(){
        Map<String,Object> map = new HashMap<>();
        map.put("page", "1");
        ServerRequest.requestHttp(this, ServerUrl.getNews, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<NewsInfo> newsInfos = JSON.parseArray(json,NewsInfo.class);
                list.clear();
                if (newsInfos!=null&&newsInfos.size()>0){
                    list.addAll(newsInfos);
                    page = 1;
                    mBGARefreshLayout.setVisibility(View.VISIBLE);
                } else {
                    mBGARefreshLayout.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
                mBGARefreshLayout.endRefreshing();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                mBGARefreshLayout.endRefreshing();
                mBGARefreshLayout.setVisibility(View.GONE);
            }
        });

    }
    //停止刷新
    public void loadRefresh(){
        page++;
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("page", page);
        ServerRequest.requestHttp(this, ServerUrl.showUserAddress, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<NewsInfo> newsInfos = JSON.parseArray(json,NewsInfo.class);
                if (newsInfos!=null&&newsInfos.size()>0){
                    list.addAll(newsInfos);
                    adapter.notifyDataSetChanged();
                } else {
                    page--;
                }
                mBGARefreshLayout.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                page--;
                mBGARefreshLayout.endLoadingMore();
            }
        });
    }
}
