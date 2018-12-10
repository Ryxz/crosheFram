package com.croshe.farming.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForgetPwd01Activity extends CrosheBaseActivity {
     private TextView tv_rb_1,tv_rb_2,tv_rb_3;
    private RadioButton rb_1,rb_2,rb_3;
    private Button btu_validation;
    private ImageView iv_left;
    private int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd01);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        tv_rb_1= (TextView) findViewById(R.id.tv_rb_1);
        tv_rb_2= (TextView) findViewById(R.id.tv_rb_2);
        tv_rb_3= (TextView) findViewById(R.id.tv_rb_3);
        rb_1= (RadioButton) findViewById(R.id.rb_1);
        rb_2= (RadioButton) findViewById(R.id.rb_2);
        rb_3= (RadioButton) findViewById(R.id.rb_3);
        btu_validation= (Button) findViewById(R.id.btu_validation);
        iv_left= (ImageView) findViewById(R.id.iv_left_2);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        iv_left.setOnClickListener(this);
        btu_validation.setOnClickListener(this);
        rb_1.setOnClickListener(this);
        rb_2.setOnClickListener(this);
        rb_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_left_2:
                finish();
                break;
            case R.id.btu_validation:
                  if(a==1){
                      Intent in = new Intent(this,ForgetPwd02Activity.class);
                      in.putExtra("type",0);
                      startActivity(in);
                      finish();
                  }else if(a==2){
                      Intent in = new Intent(this,ForgetPwd02Activity.class);
                      in.putExtra("type",1);
                      startActivity(in);
                      finish();
                  }else if(a==3){

                  }
                break;
            case R.id.rb_1:
                a=1;
                rb_2.setChecked(false);
                rb_3.setChecked(false);
                tv_rb_1.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_rb_2.setTextColor(getResources().getColor(R.color.colorAccent));
                tv_rb_3.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.rb_2:
                a=2;
                rb_1.setChecked(false);
                rb_3.setChecked(false);
                tv_rb_1.setTextColor(getResources().getColor(R.color.colorAccent));
                tv_rb_2.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_rb_3.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.rb_3:
                a=3;
                rb_1.setChecked(false);
                rb_2.setChecked(false);
                tv_rb_1.setTextColor(getResources().getColor(R.color.colorAccent));
                tv_rb_2.setTextColor(getResources().getColor(R.color.colorAccent));
                tv_rb_3.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

}
