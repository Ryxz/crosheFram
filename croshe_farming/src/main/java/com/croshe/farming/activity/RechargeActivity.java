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
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.R;
import com.croshe.farming.pay.AliPayResult;
import com.croshe.farming.pay.AliPayUtils;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/2.
 */

public class RechargeActivity extends CrosheBaseActivity {
    private TextView text_head,ll_payRecharge;
    private EditText edit_recharge;
    private LinearLayout ll_back,ll_recharge1,ll_recharge2;
    private ImageView img_select02,img_select03;
    ProgressDialog progressDialog;
    private int state = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recharge);
        initView();
        initClick();
        registerBoradcastReceiver();
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("wxPay");
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
                    context.sendBroadcast(new Intent("Recharge"));
                } else {
                    // 支付失败
                    Toast.makeText(context, "支付取消", Toast.LENGTH_SHORT).show();
                }

            }
        }
    };

    @Override
    public void initView() {
        ll_back = f(R.id.ll_back);
        text_head = f(R.id.text_head);
        text_head.setText("充值");
        edit_recharge = f(R.id.edit_recharge);
        edit_recharge.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ll_recharge1 = f(R.id.ll_recharge1);
        ll_recharge2 = f(R.id.ll_recharge2);
        img_select02 = f(R.id.img_select02);
        img_select03 = f(R.id.img_select03);
        ll_payRecharge = f(R.id.ll_payRecharge);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
        ll_recharge2.setOnClickListener(this);
        ll_recharge1.setOnClickListener(this);
        ll_payRecharge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_recharge1:
                state = 1;
                img_select02.setVisibility(View.VISIBLE);
                img_select03.setVisibility(View.GONE);
                break;
            case R.id.ll_recharge2:
                state = 2;
                img_select03.setVisibility(View.VISIBLE);
                img_select02.setVisibility(View.GONE);
                break;
            case R.id.ll_payRecharge:
                recharge();
                if (state == 0){
                    Toast.makeText(context,"请选择一种支付方式",Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }
    }

    public void recharge(){
        if (StringUtils.isEmpty(edit_recharge.getText().toString())){
            Toast.makeText(context,"金额不可为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Double money = Double.valueOf(edit_recharge.getText().toString());
        HttpRequest.recharge(context, money, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                OrderModels orderModels = JSON.parseObject(json,OrderModels.class);
                code = orderModels.getOrderCode();
                orderMoney = orderModels.getOrderPrice();
                if (state == 2){
                    doWxPay();
                }else {
                    doAliPay();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, "最小金额要高于0.01元", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String code;
    private Double orderMoney;
    private String orderTitle = "充值订单";

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
                    IWXAPI api = WXAPIFactory.createWXAPI(context, req.appId, false);
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


    //ali支付
    public void doAliPay(){
        String orderpirce = String.valueOf(orderMoney);
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
                                        context.sendBroadcast(new Intent("Recharge"));
                                        finish();
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
}
