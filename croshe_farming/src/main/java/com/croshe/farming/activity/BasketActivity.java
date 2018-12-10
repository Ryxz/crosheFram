package com.croshe.farming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.R;
import com.croshe.farming.fragment.Fragment04;
import com.croshe.farming.fragment.MarketFragment02;

/**
 * Created by Administrator on 2017/7/19.
 */

public class BasketActivity extends CrosheBaseActivity {

    private LinearLayout container;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_shop_car);
        initView();
    }

    @Override
    public void initView() {
        MarketFragment02 marketFragment02=new MarketFragment02();
        container=f(R.id.ll_container);
        getSupportFragmentManager().beginTransaction().add(R.id.ll_container, marketFragment02).commit();
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
