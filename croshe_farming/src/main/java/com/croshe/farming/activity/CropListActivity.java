package com.croshe.farming.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.croshe.farming.Entity.BasketInfo;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.R;
import com.croshe.farming.adapter.AgriculturalAdapter01;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 收成
 * Created by Administrator on 2017/9/5.
 */

public class CropListActivity extends CrosheBaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private LinearLayout ll_back,ll_to_distribution;
    private TextView text_head;
    private BGARefreshLayout refresh_crop;
    private RecyclerView recycler_crop_list;
    private AgriculturalAdapter01 adapter01;
    private List<BasketInfo> list = new ArrayList<>();
    private List<Integer> numbers = new ArrayList<>();
    private List<String> storeIds = new ArrayList<>();
    private List<ProductInfo> products = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_crop_list);
        EventBus.getDefault().register(this);
        initView();
        initClick();
        initData();
    }
    @Subscribe
    public void onEvent(String action) {
        if (action.equals("canDeliver")) {
            ll_to_distribution.setVisibility(View.VISIBLE);
        }
        if (action.equals("noDeliver")){
            ll_to_distribution.setVisibility(View.GONE);
        }

    }

    @Override
    public void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        text_head = (TextView) findViewById(R.id.text_head);
        ll_to_distribution = (LinearLayout) findViewById(R.id.ll_to_distribution);
        adapter01 = new AgriculturalAdapter01(list,context);
        refresh_crop= (BGARefreshLayout)findViewById(R.id.refresh_crop);
        refresh_crop.setDelegate(this);
        refresh_crop.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
        recycler_crop_list = (RecyclerView) findViewById(R.id.recycler_crop_list);
        recycler_crop_list.setAdapter(adapter01);
        recycler_crop_list.setLayoutManager(new LinearLayoutManager(this));
        cropList();
        registerBoradcastReceiver();
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("wareHouseShCh");
        myIntentFilter.addAction("refreshDistribution");
        // 注册广播
        registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("wareHouseShCh")) {
                numbers = intent.getIntegerArrayListExtra("numbers");
                storeIds = intent.getStringArrayListExtra("storeIds");
                products = (List<ProductInfo>) intent.getSerializableExtra("products");
            }else if(action.equals("refreshDistribution")){
                cropList();
                ll_to_distribution.setVisibility(View.GONE);
            }
        }
    };
    @Override
    public void initData() {
        text_head.setText("我的收成");
    }


    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
        ll_to_distribution.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_to_distribution:
                startActivity(new Intent(context, OrderConfirmActivity.class)
                        .putExtra("nums", (Serializable) numbers)
                        .putExtra("ids", (Serializable) storeIds)
                        .putExtra("products", (Serializable) products));
                break;
        }
    }

    public void cropList(){
        HttpRequest.getCropList2(context, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<BasketInfo> basketInfos = JSON.parseArray(json,BasketInfo.class);
                list.clear();
                if (basketInfos!=null&&basketInfos.size()>0){
                    list.addAll(basketInfos);
                }
                adapter01.notifyDataSetChanged();
                refresh_crop.endLoadingMore();
                refresh_crop.endRefreshing();
            }

            @Override
            public void onFailure(String msg) {
                adapter01.notifyDataSetChanged();
                refresh_crop.endLoadingMore();
                refresh_crop.endRefreshing();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        cropList();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unregisterReceiver(myBroadcastReceiver);
    }
}
