package com.croshe.farming.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ObjectArraySerializer;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheTabView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.BuyCarInfo;
import com.croshe.farming.Entity.CarEntity;
import com.croshe.farming.Entity.OrderDeatils;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.R;
import com.croshe.farming.activity.CashierActivity;
import com.croshe.farming.adapter.BuyCarAdapter;
import com.croshe.farming.adapter.SeedsAdapter;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/*
 * 购物车
 * Created by Administrator on 2017/5/25.
 */
public class MarketFragment01 extends Fragment implements View.OnClickListener,BGARefreshLayout.BGARefreshLayoutDelegate {

    private RecyclerView tv_re_lei;
    private List<BuyCarInfo> buyCarInfoList = new ArrayList<>();
    private List<BuyCarInfo> buyCarInfos = new ArrayList<>();
    private BuyCarAdapter byadapter;
    private LinearLayout ll_jiesuan,ll_settlement;
    private ImageView iv_quanxuan;
    private LinearLayout ll_deleteQx,ll_jsQx;
    private boolean isover = false;//是否全选结算
    private boolean isover1 = false; //是否全部删除
    private LinearLayout tv_delete_1,ll_heji;
    private TextView tv_money_buy;
    private TextView tv_number_buy;
    private BGARefreshLayout bga_buy;
    private List<String> id = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private double money = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_market_01,container,false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();
        registerBoradcastReceiver();
    }

    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("onCloseMain");
        myIntentFilter.addAction("onCloseMain1");
        myIntentFilter.addAction("onCloseMain2");
        myIntentFilter.addAction("onCloseMain3");
        myIntentFilter.addAction("refrshbuycar");
        myIntentFilter.addAction("closeAll");
        myIntentFilter.addAction("openAll");
        myIntentFilter.addAction("refreshDistribution");
        // 注册广播
       getContext().registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            String action = intent.getAction();
            if(action.equals("onCloseMain")) {
                money = 0;
                list.clear();
                id.clear();
                int number = intent.getIntExtra("number", 0);
                id = intent.getStringArrayListExtra("id");
                getComputePrice();
                if (null != id && id.size() > 0) {
                    tv_number_buy.setText("(" + number + ")");
                } else {
                    tv_money_buy.setText("￥" + 0);
                    tv_number_buy.setText("(" + 0 + ")");
                }
            }else if(action.equals("onCloseMain1")) {
                if(null!=buyCarInfoList&&buyCarInfoList.size()>0) {
                    tv_delete_1.setVisibility(View.VISIBLE);
                    ll_heji.setVisibility(View.GONE);
                    ll_jiesuan.setVisibility(View.VISIBLE);
                    ll_settlement.setVisibility(View.GONE);
                }
            }else  if(action.equals("onCloseMain2")){
                if(null!=buyCarInfoList&&buyCarInfoList.size()>0) {
                    tv_delete_1.setVisibility(View.GONE);
                    ll_heji.setVisibility(View.VISIBLE);
                    ll_jiesuan.setVisibility(View.VISIBLE);
                    ll_settlement.setVisibility(View.VISIBLE);
                }
            }else if(action.equals("onCloseMain3")){
                showUserCar();
            }else if (action.equals("refrshbuycar")){
                showUserCar();
            }else if (action.equals("closeAll")){
                isover = false;
                iv_quanxuan.setImageResource(R.mipmap.weixuanzhong);
            }else if (action.equals("openAll")){
                isover = true;
                iv_quanxuan.setImageResource(R.mipmap.xuanzhong);
            }else if (action.equals("refreshDistribution")){
                showUserCar();
            }
        }
    };

    public void initView(){
        ll_heji = (LinearLayout) getActivity().findViewById(R.id.ll_heji);
        bga_buy= (BGARefreshLayout) getActivity().findViewById(R.id.bga_buy);
        bga_buy.setDelegate(this);
        bga_buy.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        tv_delete_1= (LinearLayout) getActivity().findViewById(R.id.tv_delete_1);
        iv_quanxuan= (ImageView) getActivity().findViewById(R.id.iv_quanxuan);
        ll_deleteQx = (LinearLayout) getActivity().findViewById(R.id.ll_deleteQx);
        ll_jsQx = (LinearLayout) getActivity().findViewById(R.id.ll_jsQx);

        tv_number_buy= (TextView) getActivity().findViewById(R.id.tv_number_buy);
        tv_money_buy= (TextView) getActivity().findViewById(R.id.tv_money_buy);
        ll_jiesuan= (LinearLayout) getActivity().findViewById(R.id.ll_jiesuan);
        byadapter= new BuyCarAdapter(buyCarInfoList,getActivity());
        tv_re_lei= (RecyclerView) getActivity().findViewById(R.id.tv_re_lei);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        tv_re_lei.setLayoutManager(linearLayoutManager);
        tv_re_lei.setAdapter(byadapter);
        ll_settlement = (LinearLayout) getActivity().findViewById(R.id.ll_settlement);
        showUserCar();
    }

    public  void initEvent(){
        ll_jsQx.setOnClickListener(this);
        tv_delete_1.setOnClickListener(this);
        ll_settlement.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_jsQx:
                    if(!isover) {
                        isover = true;
                        iv_quanxuan.setImageResource(R.mipmap.xuanzhong);
                        byadapter.getboole(true);
                    }else{
                        isover = false;
                        iv_quanxuan.setImageResource(R.mipmap.weixuanzhong);
                        byadapter.getboole(false);
                    }
                    break;
                case R.id.tv_delete_1:
                    if (id.size()>0&&id !=null){
                        deleteShopCar();
                    }else {
                        Toast.makeText(getActivity(),"请选择要删除的商品",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.ll_settlement:
                    if (id.size()>0&&id !=null){
                        settlementShopCar();
                    }else {
                        Toast.makeText(getActivity(),"请选择要结算的商品",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void showUserCar(){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        ServerRequest.requestHttp(getActivity(), ServerUrl.showUserCar, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                isover = false;
                iv_quanxuan.setImageResource(R.mipmap.weixuanzhong);
                tv_money_buy.setText("￥" + 0);
                tv_number_buy.setText("(" + 0 + ")");
                buyCarInfos= JSON.parseArray(json,BuyCarInfo.class);
                buyCarInfoList.clear();
                if(null!=buyCarInfos&&buyCarInfos.size()>0){
                    buyCarInfoList.addAll(buyCarInfos);
                    ll_jiesuan.setVisibility(View.VISIBLE);
                }else{
                    ll_jiesuan.setVisibility(View.GONE);
                }
                bga_buy.endRefreshing();
                bga_buy.endLoadingMore();
                byadapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myBroadcastReceiver);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        showUserCar();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
//    删除数据
    public void deleteShopCar(){
        Map<String,Object> map = new HashMap<String,Object>();
        for(int i=0;i<id.size();i++){
            map.put("carId["+i+"]",id.get(i));
        }
        ServerRequest.requestHttp(getActivity(), ServerUrl.deleteShopCar, map, "删除中...", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                showUserCar();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    public void getComputePrice(){
        HttpRequest.computePrice(getActivity(), id, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                CarEntity buycar = JSON.parseObject(json,CarEntity.class);
                if (buycar!=null){
                    money = buycar.getCountPrice();
                    tv_money_buy.setText("￥" + money);
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    public void settlementShopCar(){
        Map<String,Object> map = new HashMap<>();
        int userId = Integer.parseInt(AppContext.getInstance().getUserInfo().getUserId());
        for(int i=0;i<id.size();i++){
            int shopid = Integer.parseInt(id.get(i));
            map.put("carId["+i+"]",shopid);
        }
        map.put("userId",userId);
        map.put("orderPrice",money);
        ServerRequest.requestHttp(getActivity(), ServerUrl.addShopCarOrder, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                OrderModels order = JSON.parseObject(json,OrderModels.class);
                String code = order.getOrderCode();
                double orderpirce = order.getOrderTruePrice();
                getActivity().startActivity(new Intent(getActivity(), CashierActivity.class)
                        .putExtra("code",String.valueOf(code))
                        .putExtra("price",String.valueOf(orderpirce))
                        .putExtra("type",1));
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                showUserCar();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
