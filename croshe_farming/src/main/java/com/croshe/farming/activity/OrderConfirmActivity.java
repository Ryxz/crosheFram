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
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.OrderInfo;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.Entity.ReceiveAddInfo;
import com.croshe.farming.R;
import com.croshe.farming.productAdapter.OrderSingleAdapter;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配送订单的确认订单详情页面
 * Created by Administrator on 2017/7/5.
 */

public class OrderConfirmActivity extends CrosheBaseActivity {
    private ImageView img_corder_confirm,img_to_address;
    private LinearLayout ll_confirm_ps,ll_to_choose_address,ll_address,ll_null_address;
    private RecyclerView recycler_order_confirm;
    private TextView text_receive_name,text_phone_01,text_phone_02,text_receive_address;
    private OrderSingleAdapter singleAdapter;
    private List<ProductInfo> list = new ArrayList<>();
    private List<String> storeIds = new ArrayList<>();
    private List<Integer> numbers = new ArrayList<>();
    private String addressId;
    private int addressType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_orderconfirm);
        storeIds = getIntent().getStringArrayListExtra("ids");
        numbers = getIntent().getIntegerArrayListExtra("nums");
        list = (List<ProductInfo>) getIntent().getSerializableExtra("products");
        initView();
        getAddress();
        initClick();
        registerBoradcastReceiver();
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("address");
        // 注册广播
        registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("address")) {
                ReceiveAddInfo addInfo = (ReceiveAddInfo) intent.getSerializableExtra("receiveInfo");
                text_receive_name.setText(addInfo.getUserName());
                String phone = addInfo.getUserPhone();
                text_phone_01.setText(phone);
//                text_phone_02.setText(phone.substring(phone.length()-4));
                text_receive_address.setText(addInfo.getProvince()+", "+addInfo.getCity()
                        + ", "+addInfo.getArea()+", "+addInfo.getStreet()+", "+addInfo.getDetails());
                addressId = addInfo.getAddressId();
                addressType = addInfo.getAddressType();
//                getAddress();
            }
        }
    };
    @Override
    public void initView() {
        ll_confirm_ps = (LinearLayout) findViewById(R.id.ll_confirm_ps);
        ll_to_choose_address = (LinearLayout) findViewById(R.id.ll_to_choose_address);
        ll_address = (LinearLayout) findViewById(R.id.ll_address);
        ll_null_address = (LinearLayout) findViewById(R.id.ll_null_address);
        img_corder_confirm = (ImageView) findViewById(R.id.img_corder_confirm);
        img_to_address = (ImageView) findViewById(R.id.img_to_address);
        recycler_order_confirm = (RecyclerView) findViewById(R.id.recycler_order_confirm);
        text_receive_name = (TextView) findViewById(R.id.text_receive_name);
        text_phone_01 = (TextView) findViewById(R.id.text_phone_01);
        text_phone_02 = (TextView) findViewById(R.id.text_phone_02);
        text_receive_address = (TextView) findViewById(R.id.text_receive_address);
        singleAdapter = new OrderSingleAdapter(context,list);
        recycler_order_confirm.setAdapter(singleAdapter);
        recycler_order_confirm.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        img_corder_confirm.setOnClickListener(this);
        img_to_address.setOnClickListener(this);
        ll_confirm_ps.setOnClickListener(this);
        ll_to_choose_address.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_corder_confirm:
                finish();
                break;
            case R.id.ll_confirm_ps:
                addPeisongOrder();
                break;
            case R.id.ll_to_choose_address:
                startActivity(new Intent(this,ManageReceiveAddressActivity.class)
                        .putExtra("chooseAddress","选择收货地址"));
                break;
        }

    }
    public void getAddress(){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("page", "1");
        ServerRequest.requestHttp(this, ServerUrl.showUserAddress, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<ReceiveAddInfo> addInfoslist = JSON.parseArray(json,ReceiveAddInfo.class);
                if (addInfoslist.size()>0&&addInfoslist!=null){
                    ll_address.setVisibility(View.VISIBLE);
                    ll_null_address.setVisibility(View.GONE);
                    ReceiveAddInfo addInfo = addInfoslist.get(0);
                    text_receive_name.setText(addInfo.getUserName());
                    String phone = addInfo.getUserPhone();
                    text_phone_01.setText(phone.substring(0,3));
                    text_phone_02.setText(phone.substring(phone.length()-4));
                    text_receive_address.setText(addInfo.getProvince()+", "+addInfo.getCity()
                            + ", "+addInfo.getArea()+", "+addInfo.getStreet()+", "+addInfo.getDetails());
                    addressId = addInfo.getAddressId();
                    addressType = addInfo.getAddressType();
                }else {
                    ll_address.setVisibility(View.GONE);
                    ll_null_address.setVisibility(View.VISIBLE);
                }

            }
            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void addPeisongOrder(){
        if (StringUtils.isEmpty(addressId)){
            Toast.makeText(context,"请选择收货地址",Toast.LENGTH_SHORT).show();
            return;
        }
        HttpRequest.addPeisongOrder(context, storeIds, numbers, addressId, addressType, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                OrderModels orderInfo = JSON.parseObject(json,OrderModels.class);
                if (orderInfo!=null){
                    String orderCode = orderInfo.getOrderCode();
                    Double orderTurePrice = orderInfo.getOrderTruePrice();
                    startActivity(new Intent(OrderConfirmActivity.this,CashierActivity.class)
                            .putExtra("code",orderCode)
                            .putExtra("price",String.valueOf(orderTurePrice)));
                    sendBroadcast(new Intent("refreshWare2"));
                }
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
