package com.croshe.farming.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.Entity.OrderDeatils;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.R;
import com.croshe.farming.productAdapter.OrderDetailAdapter;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class OrdeDetailActivity extends CrosheBaseActivity {
    private TextView text_head,order_receive_name,order_phone_01,order_phone_02,address_ps,
            human_price,all_manager_price,order_price,order_ture_pirce,jf_price,order_detail_code,
            order_time,order_pay_time;
    private LinearLayout ll_back,ll_address_ps,ll_human_price,ll_manager_price,ll_connect_ny;
    private RecyclerView recycler_order_detail;
    public static String orderCode;
    private List<OrderDeatils> list = new ArrayList<>();
    private OrderDetailAdapter adapter;
    public static int orderId;
    public static int orderType;
    private String phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order_detail);
        EventBus.getDefault().register(this);
        orderCode = getIntent().getStringExtra("orderCode");
        orderType = getIntent().getIntExtra("orderType",0);
        initView();
        initData();
        initClick();
    }
    @Subscribe
    public void onEvent(String action) {
        if (action.equals("finish")) {
            finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("订单详情");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_address_ps = (LinearLayout) findViewById(R.id.ll_address_ps);
        ll_human_price = (LinearLayout) findViewById(R.id.ll_human_price);
        ll_manager_price = (LinearLayout) findViewById(R.id.ll_manager_price);
        ll_connect_ny = (LinearLayout) findViewById(R.id.ll_connect_ny);
        order_receive_name = (TextView) findViewById(R.id.text_order_receive_name);
        order_phone_01 = (TextView) findViewById(R.id.text_order_phone_01);
        order_phone_02 = (TextView) findViewById(R.id.text_order_phone_02);
        address_ps = (TextView) findViewById(R.id.text_address_ps);
        human_price = (TextView) findViewById(R.id.text_human_price);
        all_manager_price = (TextView) findViewById(R.id.text_all_manager_price);
        order_price = (TextView) findViewById(R.id.text_order_price);
        order_ture_pirce = (TextView) findViewById(R.id.text_order_ture_pirce);
        jf_price = (TextView) findViewById(R.id.text_jf_price);
        order_detail_code = (TextView) findViewById(R.id.text_order_detail_code);
        order_time = (TextView) findViewById(R.id.text_order_time);
        order_pay_time = (TextView) findViewById(R.id.text_order_pay_time);
        adapter = new OrderDetailAdapter(context,list);
        recycler_order_detail = (RecyclerView) findViewById(R.id.recycler_order_detail);
        recycler_order_detail.setAdapter(adapter);
        recycler_order_detail.setLayoutManager(new LinearLayoutManager(this));
        showOrderDetails();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
        ll_connect_ny.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_connect_ny:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
        }
    }
    public void showOrderDetails(){
        HttpRequest.showOrderDetails(context, orderCode, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                OrderModels orderModels = JSON.parseObject(json,OrderModels.class);
                if (orderModels!=null){
                    phone = orderModels.getOrderPhone();
                    if (orderModels.getOrderType()==1){
                        ll_address_ps.setVisibility(View.VISIBLE);
                        order_receive_name.setText(orderModels.getUserName());
                        if (orderModels.getUserPhone()!=null){
                            order_phone_01.setText(orderModels.getUserPhone());
//                            order_phone_02.setText(orderModels.getUserPhone().substring(7,10));
                        }
                        address_ps.setText(orderModels.getProvince() +orderModels.getCity()+
                                orderModels.getArea()+orderModels.getDetails());
                    }
                    order_price.setText("￥"+String.valueOf(orderModels.getOrderPrice()));
                    order_ture_pirce.setText("￥"+String.valueOf(orderModels.getOrderTruePrice()));
                    order_detail_code.setText(orderModels.getOrderCode());
                    order_time.setText(orderModels.getOrderDateTime());
                    order_pay_time.setText(orderModels.getOrderPayTime());
                    List<OrderDeatils> deatils = orderModels.getDeatils();
                    list.clear();
                    if (deatils!=null&&deatils.size()>=0){
                        list.addAll(deatils);
                    }
                    orderId = orderModels.getOrderId();
                    if (orderModels.getScoreDeducted() == null){
                        jf_price.setText("￥0.00");
                    }else {
                        jf_price.setText("￥"+orderModels.getScoreDeducted());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
