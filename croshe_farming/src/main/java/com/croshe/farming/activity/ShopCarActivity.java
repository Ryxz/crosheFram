package com.croshe.farming.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.R;
import com.croshe.farming.fragment.Fragment04;

/**
 * Created by Administrator on 2017/7/6.
 */

public class ShopCarActivity extends CrosheBaseActivity {
    private LinearLayout container;
    private int type = 0 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_shop_car);
        type = getIntent().getIntExtra("type",0);
        initView();
        registerBoradcastReceiver();
    }
    @Override
    public void initView() {
        Fragment04 fragment04 = new Fragment04();
        fragment04.setStyle(type);
        container=f(R.id.ll_container);
        getSupportFragmentManager().beginTransaction().add(R.id.ll_container, fragment04).commit();
    }
    public void registerBoradcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("f4finish");
        registerReceiver(myBroadcastReceive, intentFilter);
    }


    public BroadcastReceiver myBroadcastReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("f4finish")){
                finish();
            }
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        type = 0;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public void onClick(View v) {

    }
}
