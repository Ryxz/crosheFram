package com.croshe.farming.Login;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisteredActivity extends CrosheBaseActivity {
    private EditText et_pwd_2,et_yanzhen;
    private Button btu_yanzheng;
    private Button btu_xiayibu;
    private ImageView iv_left;
    private CheckBox cb_my;
    private Button bt_daojishi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_two);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        et_pwd_2= (EditText) findViewById(R.id.et_psweed_2);
        et_yanzhen= (EditText) findViewById(R.id.et_yaoqingma);
        btu_xiayibu= (Button) findViewById(R.id.button_xiayibu);
        btu_yanzheng= (Button) findViewById(R.id.bt_yanzhenma_2);
        iv_left= (ImageView) findViewById(R.id.iv_left_1);
        cb_my= (CheckBox) findViewById(R.id.cb_my);
        bt_daojishi= (Button) findViewById(R.id.bt_daojishi);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        btu_xiayibu.setOnClickListener(this);
        btu_yanzheng.setOnClickListener(this);
        iv_left.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
                 case R.id.iv_left_1:
                     finish();
                     break;
                 case R.id.bt_yanzhenma_2:
                     if (count == 59){
                         yanzhen();
                     }
                     break;
                 case R.id.button_xiayibu:
                     if(null==et_pwd_2.toString()||et_pwd_2.getText().toString().length()<=0) {
                         Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                         break;
                     }
                    if(null==et_yanzhen.toString()||et_yanzhen.getText().toString().length()<=0){
                        Toast.makeText(this,"验证码不能为空",Toast.LENGTH_SHORT).show();
                        break;
                    }else if(!cb_my.isChecked()){
                        Toast.makeText(this,"没有同意服务条款",Toast.LENGTH_SHORT).show();
                        break;
                    }
                     Intent in = new Intent(RegisteredActivity.this,RegisteredTwoActivity.class);
                     in.putExtra("phone",et_pwd_2.getText().toString());
                     in.putExtra("yanzheng",et_yanzhen.getText().toString());
                     startActivity(in);
                     finish();

             }
    }
//    获取验证码
    public void yanzhen(){
        if (!StringUtils.isMobileNum(et_pwd_2.getText().toString())) {
            Toast.makeText(this, "手机号码不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("phone",et_pwd_2.getText().toString());
        map.put("smsType",0);
        ServerRequest.requestHttp(this, ServerUrl.sendSMS, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(RegisteredActivity.this, msg, Toast.LENGTH_SHORT).show();
                bt_daojishi.setVisibility(View.VISIBLE);
                btu_yanzheng.setVisibility(View.GONE);
                handler.sendEmptyMessageDelayed(0,1000);
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
    int count = 59;
    Handler handler =new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(count!=0) {
               handler.sendEmptyMessageDelayed(0,1000);
                bt_daojishi.setText("重新发送("+count+ ")");
                bt_daojishi.setEnabled(false);
                count--;
            }else{
                bt_daojishi.setVisibility(View.GONE);
                btu_yanzheng.setVisibility(View.VISIBLE);
                count=59;
                bt_daojishi.setText("重新发送("+count+ ")");
            }
        }

    };
}

