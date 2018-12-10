package com.croshe.farming.Login;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ForgetPwd02Activity extends CrosheBaseActivity {
   private ImageView iv_left;
    private EditText et_phone_number,et_code;
    private Button btu_code,btu_validationcode,btu_code_1;
    int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd02);
        initView();
        initClick();
        initData();
    }

    @Override
    public void initView() {
        iv_left= (ImageView) findViewById(R.id.iv_left_4);
        et_phone_number= (EditText) findViewById(R.id.et_phone);
        et_code= (EditText) findViewById(R.id.et_code);
        btu_code= (Button) findViewById(R.id.bt_code);
        btu_validationcode= (Button) findViewById(R.id.btu_validationcode);
        btu_code_1= (Button) findViewById(R.id.bt_code_1);
    }

    @Override
    public void initData() {
           Intent in =getIntent();
        type=in.getIntExtra("type",-1);
    }

    @Override
    public void initClick() {
         iv_left.setOnClickListener(this);
        btu_code.setOnClickListener(this);
        btu_validationcode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
             switch (v.getId()){
                 case R.id.iv_left_4:
                     finish();
                     break;
                 case R.id.bt_code:
                     if(type==0){
                         if (!StringUtils.isMobileNum(et_phone_number.getText().toString())) {
                             Toast.makeText(this, "手机号码不正确", Toast.LENGTH_SHORT).show();
                             break;
                         }
                     }else{
                         if(!et_code.getText().toString()
                                 .matches("[\\w[.-]]+@[\\w[.-]]+[\\w]+")){
                             Toast.makeText(this,"邮箱格式不正确",Toast.LENGTH_SHORT).show();
                             break;
                         }
                     }
                     one();
                     break;
                 case R.id.btu_validationcode:
                     two();
                     break;
             }
    }
    //    安全验证
    public void two(){
        Map<String,Object> map = new HashMap<>();
        map.put("type",type);
        map.put("value",et_phone_number.getText().toString());
        map.put("code",et_code.getText().toString());
        ServerRequest.requestHttp(this, ServerUrl.forgetPwdThree, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(ForgetPwd02Activity.this, msg, Toast.LENGTH_SHORT).show();
                Intent in  = new Intent(ForgetPwd02Activity.this,ForgetPwd03Activity.class);
                in.putExtra("phone",et_phone_number.getText().toString());
                startActivity(in);
                finish();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(ForgetPwd02Activity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //    系统是否存在该用户
    public void one(){
        Map<String,Object> map = new HashMap<>();
        map.put("value",et_phone_number.getText().toString());
        ServerRequest.requestHttp(this, ServerUrl.forgetPwdOne, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
               Toast.makeText(ForgetPwd02Activity.this, msg, Toast.LENGTH_SHORT).show();
                yanzhen();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(ForgetPwd02Activity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //    验证码
    public void yanzhen(){
        Map<String,Object> map = new HashMap<>();
        map.put("phone",et_phone_number.getText().toString());
        map.put("smsType",3);
        ServerRequest.requestHttp(this, ServerUrl.sendSMS, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(ForgetPwd02Activity.this, msg, Toast.LENGTH_SHORT).show();
                btu_code_1.setVisibility(View.VISIBLE);
                btu_code.setVisibility(View.GONE);
                handler.sendEmptyMessageDelayed(0,1000);

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
    int count =59;
    Handler handler =new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(count!=0) {
                handler.sendEmptyMessageDelayed(0,1000);
                btu_code_1.setText(count + "");
                btu_code.setEnabled(false);
                count--;
            }else{
                btu_code_1.setVisibility(View.GONE);
                btu_code.setVisibility(View.VISIBLE);
                count=59;
                btu_code_1.setText(count + "");
            }
        }

    };
}
