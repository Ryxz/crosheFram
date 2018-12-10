package com.croshe.farming.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.Toasts;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.CommentModel;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.Entity.UserInfo;
import com.croshe.farming.PersonalCenter.SetPasswordActivity;
import com.croshe.farming.R;
import com.croshe.farming.pay.AliPayResult;
import com.croshe.farming.pay.AliPayUtils;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.util.Constant;
import com.croshe.farming.util.PassWordDialog;
import com.jungly.gridpasswordview.GridPasswordView;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/5.
 */

public class CashierActivity extends CrosheBaseActivity {
    private String code;
    private String orderpirce;
    private TextView text_pay_price,text_order_code;
    private ImageView img_back_cashier,img_select01,img_select02,img_select03;
    private LinearLayout ll_pay,ll_select01,ll_select02,ll_select03;
    private int tag = 1;
    ProgressDialog progressDialog;
    private OrderModels orderModels;
    private LinearLayout ll_to_choose_address;
    private String orderTitle;
    private double orderMoney;
    private UserInfo userInfo = AppContext.getInstance().getUserInfo();
    public static int type = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cashier);
        type = getIntent().getIntExtra("type",0);
        initView();
        initData();
        initClick();
        registerBoradcastReceiver();
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("wxPay");
        // 注册广播
        myIntentFilter.addAction("refreshDistribution");
        registerReceiver(mBroadcastReceiver, myIntentFilter);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("wxPay")) {
                int result = intent.getExtras().getInt("result");
                if (result == 0) {
                    // 支付成功
                    Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                    finish();
                    EventBus.getDefault().post("refresh");
                    EventBus.getDefault().post("refreshwarehouse");
                    context.sendBroadcast(new Intent("refreshDistribution").putExtra("type",type));
                    if (type == 2){
                        context.sendBroadcast(new Intent("changeAdapter"));
                        startActivity(new Intent(context,CropListActivity.class));
                    }
                } else {
                    // 支付失败
                    Toast.makeText(context, "支付取消", Toast.LENGTH_SHORT).show();
                }

            }
            if (action.equals("refreshDistribution")){
                handler.sendEmptyMessageDelayed(0,200);
            }
        }
    };

    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            finish();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }
    @Override
    public void initView() {
        ll_to_choose_address = (LinearLayout) findViewById(R.id.ll_to_choose_address);
        text_pay_price = (TextView) findViewById(R.id.text_pay_price);
        text_order_code = (TextView) findViewById(R.id.text_order_code);
        img_back_cashier = (ImageView) findViewById(R.id.img_back_cashier);
        ll_pay = (LinearLayout) findViewById(R.id.ll_pay);
        ll_select01 = (LinearLayout) findViewById(R.id.ll_select01);
        ll_select02 = (LinearLayout) findViewById(R.id.ll_select02);
        ll_select03 = (LinearLayout) findViewById(R.id.ll_select03);
        img_select01 = (ImageView) findViewById(R.id.img_select01);
        img_select02 = (ImageView) findViewById(R.id.img_select02);
        img_select03 = (ImageView) findViewById(R.id.img_select03);
    }

    @Override
    public void initData() {
        code = getIntent().getStringExtra("code");
        orderpirce = getIntent().getStringExtra("price");
        orderModels = (OrderModels) getIntent().getSerializableExtra("order");
        text_pay_price.setText(orderpirce);
        text_order_code.setText(code);
        orderMoney = Double.parseDouble(orderpirce);
        orderTitle = "购物订单";
        type = getIntent().getIntExtra("type",0);
    }

    @Override
    public void initClick() {
        img_back_cashier.setOnClickListener(this);
        ll_pay.setOnClickListener(this);
        ll_select01.setOnClickListener(this);
        ll_select02.setOnClickListener(this);
        ll_select03.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_cashier:
                finish();
                break;
            case R.id.ll_select01:
                tag = 1;
                img_select01.setVisibility(View.VISIBLE);
                img_select02.setVisibility(View.GONE);
                img_select03.setVisibility(View.GONE);
                break;
            case R.id.ll_select02:
                tag = 2;
                img_select02.setVisibility(View.VISIBLE);
                img_select01.setVisibility(View.GONE);
                img_select03.setVisibility(View.GONE);
                break;
            case R.id.ll_select03:
                tag = 3;
                img_select03.setVisibility(View.VISIBLE);
                img_select02.setVisibility(View.GONE);
                img_select01.setVisibility(View.GONE);
                break;
            case R.id.ll_pay:
                if (tag == 1){
                    if (userInfo.getPayPassword()==null){
                        context.startActivity(new Intent(this, SetPasswordActivity.class));
                    }else {
                        PassWordDialog passWordDialog = new PassWordDialog(context,code,orderMoney);
                        passWordDialog.show();

                    }
                }else if (tag == 3){
                    doWxPay();
                }else {
                    doAliPay();
                }
                break;
        }
    }
//

    //微信支付
    public void doWxPay(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在打开微信中..");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
        HttpRequest.doWxPay(context, code, orderMoney, orderTitle, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                progressDialog.dismiss();
                Map<String, String> map = JSON.parseObject(json, Map.class);
                if (null != map && map.size() > 0) {
                    PayReq req = new PayReq();
                    req.appId = map.get("appid");
                    req.partnerId = map.get("partnerid");
                    req.prepayId = map.get("prepayid");
                    req.nonceStr = map.get("noncestr");
                    req.timeStamp = map.get("timestamp");
                    req.packageValue = map.get("package");
                    req.sign = map.get("sign");
                    IWXAPI api = WXAPIFactory.createWXAPI(CashierActivity.this, req.appId, false);
                    api.registerApp(req.appId);
                    api.sendReq(req);
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void doAliPay(){
        HttpRequest.doAliPay(context, code, orderpirce, orderTitle, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                AliPayUtils.pay((Activity) context, new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        // TODO Auto-generated method stub
                        switch (msg.what) {
                            case 1: {
                                try {
                                    AliPayResult payResult = new AliPayResult((String) msg.obj);
                                    String resultStatus = payResult.getResultStatus();
//                                    L.d("TAG", "------------------" + resultStatus);
                                    // 判断resultStatus
                                    // 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                                    if (TextUtils.equals(resultStatus, "9000")) {// 支付宝支付成功
                                        Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                                        sendRedInfo();
//                                        dismiss();
//                                        ((Activity) context).finish();
                                    } else {
                                        Toast.makeText(context, "支付取消!", Toast.LENGTH_LONG).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }

                    }
                }, json);

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    public void sendRedInfo(){
        EventBus.getDefault().post("refresh");
        EventBus.getDefault().post("refreshwarehouse");
        context.sendBroadcast(new Intent("refreshDistribution").putExtra("type",type));
        if (type == 2){
            context.sendBroadcast(new Intent("changeAdapter"));
            startActivity(new Intent(context,CropListActivity.class));
        }
    }




    @Override
    public void finish() {
        super.finish();
    }
}
