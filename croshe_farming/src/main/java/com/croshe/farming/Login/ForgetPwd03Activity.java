package com.croshe.farming.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ForgetPwd03Activity extends CrosheBaseActivity {
     private EditText et_new_pwd,et_new_pwd_1;
    private ImageView iv_left;
    private Button btu_determine;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd03);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        et_new_pwd= (EditText) findViewById(R.id.et_new_pwd);
        et_new_pwd_1= (EditText) findViewById(R.id.et_new_pwd_1);
        iv_left= (ImageView) findViewById(R.id.iv_left_5);
        btu_determine= (Button) findViewById(R.id.btu_determine);
    }

    @Override
    public void initData() {
        Intent in =getIntent();
         phone=in.getStringExtra("phone");
    }

    @Override
    public void initClick() {
        btu_determine.setOnClickListener(this);
        iv_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
              switch (v.getId()){
                  case R.id.iv_left_5:
                      finish();
                      break;
                  case R.id.btu_determine:
                      if(!et_new_pwd.getText().toString()
                              .matches("[a-zA-Z0-9]{6,24}")){
                          Toast.makeText(this,"密码格式不正确",Toast.LENGTH_SHORT).show();
                          break;
                      }
                      if(!et_new_pwd_1.getText().toString()
                              .matches("[a-zA-Z0-9]{6,24}")){
                          Toast.makeText(this,"密码格式不正确",Toast.LENGTH_SHORT).show();
                          break;
                      }
                      if(!et_new_pwd_1.getText().toString().equals(et_new_pwd.getText().toString())){
                          Toast.makeText(this,"2次输入的密码不一致",Toast.LENGTH_SHORT).show();
                          break;
                      }
                      forgetPwdFour();
                      break;
              }
    }
    public void forgetPwdFour(){
        Map<String,Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("newPwd",et_new_pwd.getText().toString());
        map.put("reNewPwd",et_new_pwd_1.getText().toString());
        ServerRequest.requestHttp(this, ServerUrl.forgetPwdFour, map, "重置中", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(ForgetPwd03Activity.this,msg,Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(ForgetPwd03Activity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
