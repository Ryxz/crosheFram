
package com.croshe.farming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.R;

/**
 * 支付详情
 * Created by Administrator on 2017/6/28.
 */

public class ExpenditureDetailsActivity extends CrosheBaseActivity {
    private TextView text_code,text_type,text_pay,text_pay_type,text_pay_time,text_pay_balance;
    private String code,type,pay,pay_type,time,balance;
    private ImageView img_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_expendituredetails);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        img_back = (ImageView) findViewById(R.id.img_expend_detail_back);
        text_code = (TextView) findViewById(R.id.text_code);
        text_type = (TextView) findViewById(R.id.text_type);
        text_pay = (TextView) findViewById(R.id.text_pay);
        text_pay_type = (TextView) findViewById(R.id.text_pay_type);
        text_pay_time = (TextView) findViewById(R.id.text_pay_time);
        text_pay_balance = (TextView) findViewById(R.id.text_pay_balance);
    }

    @Override
    public void initData() {
        code = getIntent().getStringExtra("code");
        type = getIntent().getStringExtra("type");
        pay = getIntent().getStringExtra("pay");
        pay_type = getIntent().getStringExtra("pay_type");
        time = getIntent().getStringExtra("time");
        balance = getIntent().getStringExtra("balance");
        text_code.setText(code);
        text_type.setText(type);
        text_pay.setText(pay);
        text_pay_type.setText(pay_type);
        text_pay_time.setText(time);
        text_pay_balance.setText(balance);

    }

    @Override
    public void initClick() {
        img_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_expend_detail_back:
                finish();
                break;
        }

    }
}
