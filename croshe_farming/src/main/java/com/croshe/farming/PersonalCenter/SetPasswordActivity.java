package com.croshe.farming.PersonalCenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.AppContext;
import com.croshe.farming.R;

/**
 * Created by Administrator on 2017/7/17.
 */

public class SetPasswordActivity extends CrosheBaseActivity {
    private TextView text_head,text_set_pay_password;
    private LinearLayout ll_back;
    private LinearLayout ll_password_1,ll_password_2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_set_password);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        text_set_pay_password = (TextView) findViewById(R.id.text_set_pay_password);
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("密码设置");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_password_1 = (LinearLayout) findViewById(R.id.ll_password_1);
        ll_password_2 = (LinearLayout) findViewById(R.id.ll_password_2);
        if (AppContext.getInstance().getUserInfo().getPayPassword()==null){
            text_set_pay_password.setText("设置支付密码");
        }else {
//            text_set_pay_password.setText("支付密码");
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
        ll_password_1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_password_1:
                startActivity(new Intent(SetPasswordActivity.this,SetPayPasswordActivity.class));
                break;
        }
    }
}
