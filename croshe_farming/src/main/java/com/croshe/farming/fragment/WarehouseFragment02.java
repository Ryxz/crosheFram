package com.croshe.farming.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.BasketInfo;
import com.croshe.farming.Entity.Product;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.Entity.TypeClassEnumInfo;
import com.croshe.farming.Entity.WarehouseInfo;
import com.croshe.farming.R;
import com.croshe.farming.activity.OrderConfirmActivity;
import com.croshe.farming.adapter.AgriculturalAdapter;
import com.croshe.farming.adapter.AgriculturalAdapter01;
import com.croshe.farming.adapter.ListAdapter;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static com.croshe.farming.R.id.tv_my_2;

/**
 * Created by Administrator on 2017/6/9.
 */
//我的收成
public class WarehouseFragment02 extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate,View.OnClickListener{
    private BGARefreshLayout bgalayout_warehouse01;
    private RecyclerView er_warehouse02;
    private int id=0;
    private List<BasketInfo> warehouseInfoList = new ArrayList<>();
    private AgriculturalAdapter01 agriculturalAdapter01;
    private TextView tv_xuanze,tv_xuanze_1;
    private LinearLayout ll_to_distribution;
    private List<Integer> numbers = new ArrayList<>();
    private List<String> storeIds = new ArrayList<>();
    private List<ProductInfo> products = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.warehouse_frafment02,container,false);
        return view;
    }

    @Subscribe
    public void onEvent(String action) {
        if (action.equals("canDeliver")) {
            ll_to_distribution.setVisibility(View.VISIBLE);
        }
        if (action.equals("noDeliver")){
            ll_to_distribution.setVisibility(View.GONE);
        }else if (action.equals("refreshwarehouse")){
            getCropList();
        }

    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("wareHouseShCh");
        myIntentFilter.addAction("refreshWare2");
        myIntentFilter.addAction("refreshDistribution");
        // 注册广播
        getContext().registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("wareHouseShCh")) {
                numbers = intent.getIntegerArrayListExtra("numbers");
                storeIds = intent.getStringArrayListExtra("storeIds");
                products = (List<ProductInfo>) intent.getSerializableExtra("products");
            }else if (action.equals("refreshWare2")){
                getCropList();
            }else if (action.equals("refreshDistribution")){
                getCropList();
            }
        }
    };
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initDate();
        registerBoradcastReceiver();
    }

    public void initView() {
        tv_xuanze= (TextView) getActivity().findViewById(R.id.tv_xuanze);
        tv_xuanze_1= (TextView) getActivity().findViewById(R.id.tv_xuanze_1);
        bgalayout_warehouse01= (BGARefreshLayout) getActivity().findViewById(R.id.bgalayout_warehouse03);
        bgalayout_warehouse01.setDelegate(this);
        bgalayout_warehouse01.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        er_warehouse02= (RecyclerView) getActivity().findViewById(R.id.er_warehouse03);
        ll_to_distribution = (LinearLayout) getActivity().findViewById(R.id.ll_to_distribution);
    }

    public void initDate() {
        tv_xuanze.setOnClickListener( this);
        tv_xuanze_1.setOnClickListener( this);
        ll_to_distribution.setOnClickListener(this);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        er_warehouse02.setLayoutManager(manager1);
        agriculturalAdapter01 = new AgriculturalAdapter01(warehouseInfoList,getActivity());
        er_warehouse02.setAdapter(agriculturalAdapter01);
        getCropList();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getCropList();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    //    获取我的仓库列表收获
    public void getCropList(){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("typeType",id);
        ServerRequest.requestHttp(getContext(), ServerUrl.getCropList, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
             WarehouseInfo warehouseInfo= JSON.parseObject(json,WarehouseInfo.class);
                if(!StringUtils.isEmpty(warehouseInfo.toString())){
                    String count=warehouseInfo.getCount();
                    Intent in = new Intent("shouchengcount");
                    in.putExtra("count1",count);
                    getContext().sendBroadcast(in);
                    warehouseInfoList.clear();
                    warehouseInfoList.addAll( warehouseInfo.getUserStoreModels());
                }
                bgalayout_warehouse01.endRefreshing();
                bgalayout_warehouse01.endLoadingMore();
                agriculturalAdapter01.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.tv_xuanze:
                 id=0;
                 tv_xuanze.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
                 tv_xuanze.setBackgroundResource(R.color.colorbaise);
                 tv_xuanze_1.setTextColor(getContext().getResources().getColor(R.color.colorbaise));
                 tv_xuanze_1.setBackgroundResource(R.color.colorPrimary);
                 getCropList();
                 break;
             case R.id.tv_xuanze_1:
                 id=1;
                 tv_xuanze.setTextColor(getContext().getResources().getColor(R.color.colorbaise));
                 tv_xuanze.setBackgroundResource(R.color.colorPrimary);
                 tv_xuanze_1.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
                 tv_xuanze_1.setBackgroundResource(R.color.colorbaise);
                 getCropList();
                 break;
             //去配送
             case R.id.ll_to_distribution:
                 startActivity(new Intent(getActivity(), OrderConfirmActivity.class)
                         .putExtra("nums", (Serializable) numbers)
                         .putExtra("ids", (Serializable) storeIds)
                         .putExtra("products", (Serializable) products));
                 break;
         }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
