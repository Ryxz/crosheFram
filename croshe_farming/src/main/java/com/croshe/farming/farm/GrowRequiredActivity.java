package com.croshe.farming.farm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.GrowEntity;
import com.croshe.farming.FramAdapter.GrowRequireAdapter;
import com.croshe.farming.R;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class GrowRequiredActivity extends CrosheBaseActivity{
    private TextView text_head;
    private LinearLayout ll_back,ll_null_joural;
    private RecyclerView recycler_grow;
    private int type;
    private String detailId;
    private List<GrowEntity> list = new ArrayList<>();
    private GrowRequireAdapter requireAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gorw_require);
        detailId = getIntent().getStringExtra("detailId");
        type = getIntent().getIntExtra("type",2);
        initView();
        initData();
        initClick();
        registerBoradcastReceiver();
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("refreshgrowlist");
        // 注册广播
        registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            String action = intent.getAction();
            if(action.equals("refreshgrowlist")) {
                getUserFarmAction(detailId,type);
            }
        }
    };

    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("生长所需");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        recycler_grow = (RecyclerView) findViewById(R.id.recycler_grow_require);
        requireAdapter = new GrowRequireAdapter(context,list);
        recycler_grow.setAdapter(requireAdapter);
        recycler_grow.setLayoutManager(new LinearLayoutManager(this));
        ll_null_joural = (LinearLayout) findViewById(R.id.ll_null_joural);
    }

    @Override
    public void initData() {
        getUserFarmAction(detailId,type);
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
    public void getUserFarmAction(String detailId,int type){
        HttpRequest.shoUserFarmAction(context, AppContext.getInstance().getUserInfo().getUserId(), detailId, type, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<GrowEntity> grows = JSON.parseArray(json,GrowEntity.class);
                list.clear();
                if (grows!=null&&grows.size()>0){
                    list.addAll(grows);
                }else {
                    ll_null_joural.setVisibility(View.VISIBLE);
                }
                requireAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

}
