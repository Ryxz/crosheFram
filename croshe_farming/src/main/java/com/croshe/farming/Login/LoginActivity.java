package com.croshe.farming.Login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.UserInfo;
import com.croshe.farming.Entity.UserSet;
import com.croshe.farming.MainActivity;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.HashMap;
import java.util.Map;
//登录
public class LoginActivity extends CrosheBaseActivity {
    private TextView tv_registered;
    private EditText et_pwd,et_phone;
    private CheckBox cb_pwd;
    private TextView tv_zhuce;
    private ImageView iv_left,iv_bukejian_1;
    private LinearLayout iv_bukejian;
    private Button btu_longin;
    private ImageView iv_weixing,iv_qq;
    private boolean isselt;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
        initClick();
    }


        @Override
    public void initView() {
        tv_registered= (TextView) findViewById(R.id.tv_registered);
        et_phone= (EditText) findViewById(R.id.et_phone_number);
        et_pwd= (EditText) findViewById(R.id.et_pwd);
        cb_pwd= (CheckBox) findViewById(R.id.cb_pwd);
        tv_zhuce= (TextView) findViewById(R.id.tv_zhuce);
        iv_left= (ImageView) findViewById(R.id.iv_left);
        iv_bukejian= (LinearLayout) findViewById(R.id.iv_bukejian);
        btu_longin= (Button) findViewById(R.id.button_longin);
        iv_weixing= (ImageView) findViewById(R.id.iv_wenxing);
        iv_qq = (ImageView) findViewById(R.id.iv_qq);
        iv_bukejian_1= (ImageView) findViewById(R.id.iv_bukejian_1);
    }

    @Override
    public void initData() {
        preferences = getSharedPreferences("MyText", Context.MODE_PRIVATE);
        String phone = preferences.getString("username","");
        String pwd=preferences.getString("Password","");
        boolean isselected=preferences.getBoolean("isselected",false);
        et_phone.setText(phone);
        et_pwd.setText(pwd);
        cb_pwd.setChecked(isselected);
        if(!StringUtils.isEmpty(AppContext.getInstance().getUserInfo().getUserId())){
            Intent in  =new Intent(this,MainActivity.class);
            startActivity(in);
            finish();
        }
    }

    @Override
    public void initClick() {
        tv_registered.setOnClickListener(this);
        tv_zhuce.setOnClickListener(this);
        iv_left.setOnClickListener(this);
        iv_bukejian.setOnClickListener(this);
        btu_longin.setOnClickListener(this);
        tv_zhuce.setOnClickListener(this);
        iv_weixing.setOnClickListener(this);
        iv_qq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
               switch (v.getId()){
                   case R.id.iv_left:
                       finish();
                       break;
                   case R.id.tv_registered:
                       Intent in1 =new Intent(LoginActivity.this,RegisteredActivity.class);
                       startActivity(in1);
                       break;
                   case R.id.iv_bukejian:
                       if(!isselt) {
                           iv_bukejian_1.setImageResource(R.mipmap.keijian);
                           et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                       }else {

                           et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                           iv_bukejian_1.setImageResource(R.mipmap.bukejian);
                       }
                       isselt=!isselt;
                       break;
                   case R.id.tv_zhuce:
                       Intent in  =new Intent(this,ForgetPwd01Activity.class);
                       startActivity(in);
                       finish();

                       break;
                   case R.id.iv_wenxing:
                       Intent in2 =new Intent();
                       break;
                   case R.id.iv_qq:
                       Intent in3 =new Intent();
                       break;
                   case R.id.button_longin:
                       if (!StringUtils.isMobileNum(et_phone.getText().toString())) {
                           Toast.makeText(this, "手机号码不正确", Toast.LENGTH_SHORT).show();
                           break;
                       }
                       if(!et_pwd.getText().toString().matches("[a-zA-Z0-9]{6,24}")){
                           Toast.makeText(this,"密码格式不正确",Toast.LENGTH_SHORT).show();
                           break;
                       }
                       login();
                       break;
               }
    }
    public void login(){
        Map<String,Object> map =  new HashMap<>();
        map.put("userPhone",et_phone.getText().toString());
        map.put("userPassword",et_pwd.getText().toString());
        ServerRequest.requestHttp(this, ServerUrl.login, map, "登录中", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                UserInfo userInfo = JSON.parseObject(json,UserInfo.class);
                if(null!=userInfo) {
                    AppContext.getInstance().saveUserInfo(userInfo);
                    Intent in = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(in);
                }

                SharedPreferences.Editor editor = preferences.edit(); //需要获取SharedPreferences的编辑对象
                if(cb_pwd.isChecked()) {
                    editor.putString("username", et_phone.getText().toString()); //向preferences写入数据：
                    editor.putString("Password", et_pwd.getText().toString());
                    editor.putBoolean("isselected",true);
                    editor.commit();// 向preferences文件中提交数据：
                }else{
                    editor.clear();
                    editor.commit();// 向preferences文件中提交数据
                }
                finish();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
