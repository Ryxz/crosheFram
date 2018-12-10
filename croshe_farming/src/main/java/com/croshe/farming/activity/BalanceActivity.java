package com.croshe.farming.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.AppContext;
import com.croshe.farming.R;
import com.croshe.farming.adapter.BalanceDetailAdapter;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class BalanceActivity extends CrosheBaseActivity {
    private TextView balance_detail,balance;
    private LinearLayout img_back;
    private LinearLayout ll_recharge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_balance);
        initView();
        initClick();
        getBalance();
        registerBoradcastReceiver();
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("Recharge");
        registerReceiver(mBroadcastReceiver, myIntentFilter);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("Recharge")) {
                getBalance();
            }
        }
    };


    @Override
    public void initView() {
        balance = (TextView) findViewById(R.id.text_balance);
        balance_detail = (TextView) findViewById(R.id.text_balance_detail);
        img_back = (LinearLayout) findViewById(R.id.img_balance_back);
        ll_recharge = (LinearLayout) findViewById(R.id.ll_recharge);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        balance_detail.setOnClickListener(this);
        img_back.setOnClickListener(this);
        ll_recharge.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_balance_detail:
                startActivity(new Intent(this, BalanceDetailsActivity.class));
                break;
            case R.id.img_balance_back:
                finish();
                break;
            case R.id.ll_recharge:
                startActivity(new Intent(this,RechargeActivity.class));
                break;
        }

    }
    public void getBalance(){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        ServerRequest.requestHttp(this, ServerUrl.showUserBalance, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                balance.setText(json+"");
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
