package com.croshe.farming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class BindMobileActivity extends CrosheBaseActivity {
    private String phone;
    private String verification;
    private EditText edit_phone,edit_verification;
    private LinearLayout ll_bind_determine;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bind_mobile);
    }

    @Override
    public void initView() {
        edit_phone = (EditText) findViewById(R.id.text_bind_phone);
        edit_verification = (EditText) findViewById(R.id.text_verification_code);
        ll_bind_determine = (LinearLayout) findViewById(R.id.ll_bind_determine);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        ll_bind_determine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_bind_determine:
                bindMobile();
                break;
        }
    }
    public void bindMobile(){
        phone = edit_phone.getText().toString();
        verification = edit_verification.getText().toString();
        if (StringUtils.isEmpty(phone)){
            Toast.makeText(context,"请填写手机号码",Toast.LENGTH_SHORT).show();

        }
        if (StringUtils.isEmpty(verification)){
            Toast.makeText(context,"请输入验证码",Toast.LENGTH_SHORT).show();

        }
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("phone",phone);
        map.put("code",verification);
        ServerRequest.requestHttp(this, ServerUrl.bindUserPhone, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
