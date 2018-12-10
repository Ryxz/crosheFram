package com.croshe.farming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.R;
import com.croshe.farming.fragment.Fragment02;
import com.croshe.farming.fragment.Fragment04;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ProductAcivity extends CrosheBaseActivity {
    private LinearLayout ll_context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product);
        initView();
    }

    @Override
    public void initView() {
        Fragment02 fragment02=new Fragment02();
        ll_context=f(R.id.ll_context);
        getSupportFragmentManager().beginTransaction().add(R.id.ll_context, fragment02).commit();

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
