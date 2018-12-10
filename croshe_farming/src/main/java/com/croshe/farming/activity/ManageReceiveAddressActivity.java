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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.Entity.ReceiveAddInfo;
import com.croshe.farming.R;
import com.croshe.farming.adapter.ReceiveAddressAdapter;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.utils.L;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 管理收货地址
 * Created by Administrator on 2017/6/27.
 */

public class ManageReceiveAddressActivity extends CrosheBaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate{
    private RecyclerView recycler_manage_address;
    private BGARefreshLayout mRefreshLayout;
    private TextView ll_add_address,text_manage_address_head;
    private ReceiveAddressAdapter addressAdapter;
    List<ReceiveAddInfo> list = new ArrayList<>();
    private ImageView img_back;
    private String name;
    private int style ;
    private LinearLayout ll_null_joural;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_manage_address);
        style = 0;
        name = getIntent().getStringExtra("chooseAddress");
        if (name!=null){
            style = 1;
        }
        initView();
        initData();
        initClick();
        registerBoradcastReceiver();
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("changeAddress");
        myIntentFilter.addAction("refreshAddress");
        // 注册广播
        registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("changeAddress")) {
                finish();
            }else if (action.equals("refreshAddress")){
                getAddress();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        getAddress();
    }

    @Override
    public void initView() {
        ll_null_joural = (LinearLayout) findViewById(R.id.ll_wu_address);
        ll_add_address = (TextView) findViewById(R.id.ll_add_address);
        img_back = (ImageView) findViewById(R.id.img_address_back);
        text_manage_address_head = (TextView) findViewById(R.id.text_manage_address_head);
        addressAdapter = new ReceiveAddressAdapter(context,list,style);
        recycler_manage_address = (RecyclerView) findViewById(R.id.recyclerView_manage_address);
        recycler_manage_address.setLayoutManager(new LinearLayoutManager(this));
        recycler_manage_address.setAdapter(addressAdapter);
        mRefreshLayout = (BGARefreshLayout) findViewById(R.id.mRefreshLayout_receive);
        mRefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder bgaMoocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(context, true);
        bgaMoocStyleRefreshViewHolder.setUltimateColor(android.R.color.white);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(context, true));
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void initData() {
        if (name!=null){
            text_manage_address_head.setText(name);
        }
    }

    @Override
    public void initClick() {
        ll_add_address.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_add_address:
                startActivity(new Intent(this,NewAddressActivity.class));
                break;
            case R.id.img_address_back:
                finish();
                break;
        }
    }
    int page = 1;
    //获得收货地址
    public void getAddress(){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("page", 1);
        ServerRequest.requestHttp(this, ServerUrl.showUserAddress, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<ReceiveAddInfo> addInfoslist = JSON.parseArray(json,ReceiveAddInfo.class);
                list.clear();
                if (addInfoslist!=null&&addInfoslist.size()>0){
                    list.addAll(addInfoslist);
                    page = 1;
                    mRefreshLayout.setVisibility(View.VISIBLE);
                } else {
                    ll_null_joural.setVisibility(View.VISIBLE);
                    mRefreshLayout.setVisibility(View.GONE);
                }
                addressAdapter.notifyDataSetChanged();
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
                List<ReceiveAddInfo> addInfoslist = JSON.parseArray(json,ReceiveAddInfo.class);
                if (addInfoslist!=null&&addInfoslist.size()>0){
                    list.addAll(addInfoslist);
                    addressAdapter.notifyDataSetChanged();
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
        getAddress();
    }

    /**
     * 开始加载更多。由于监听了ScrollView、RecyclerView、AbsListView滚动到底部的事件，所以这里采用返回boolean来处理是否加载更多。否则使用endLoadingMore方法会失效
     *
     * @param refreshLayout
     * @return 如果要开始加载更多则返回true，否则返回false。（返回false的场景：没有网络、一共只有x页数据并且已经加载了x页数据了）
     */
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (null != list && list.size() >= 3) {
            getAddressLoad();
        } else {
            return false;
        }
        return true;
    }
}
