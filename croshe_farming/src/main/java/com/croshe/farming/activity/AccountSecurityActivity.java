package com.croshe.farming.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
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
import com.croshe.farming.Login.LoginActivity;
import com.croshe.farming.MainActivity;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.util.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/26.
 */

public class AccountSecurityActivity extends CrosheBaseActivity {
    TextView textView1,textView2,textView3,textView_head;
    private ImageView image_back,img_finish,image_select1,image_select2,image_select3;
    private LinearLayout ll_select1,ll_select2,ll_select3,ll_change;
    private LinearLayout ll_account_01,ll_account_02,ll_account_03,text_getsms,text_next,ll_determine_newpwd;
    private TextView txt_pwd_01,txt_pwd_02;
    private EditText edit_reset,edit_newpwd;
    private String type = Constant.OpinionType01;
    private TextView text_yanzehngcode,text_countdown,text_pwd_remind;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_accountsecurity);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        text_yanzehngcode = (TextView) findViewById(R.id.text_yanzehngcode);
        text_countdown = (TextView) findViewById(R.id.text_countdown);
        text_pwd_remind = (TextView) findViewById(R.id.text_pwd_remind);

        edit_reset = (EditText) findViewById(R.id.edit_reset);
        edit_newpwd = (EditText) findViewById(R.id.edit_newpwd);

        image_back = (ImageView) findViewById(R.id.img_account_back);
        img_finish = (ImageView) findViewById(R.id.img_account_finish);
        textView_head = (TextView) findViewById(R.id.text_account_head);
        textView1 = (TextView) findViewById(R.id.text_account01);
        textView2 = (TextView) findViewById(R.id.text_account02);
        textView3 = (TextView) findViewById(R.id.text_account03);

        ll_select1 = (LinearLayout) findViewById(R.id.ll_select01);
        ll_select2 = (LinearLayout) findViewById(R.id.ll_select02);
        ll_select3 = (LinearLayout) findViewById(R.id.ll_select03);

        image_select1 = (ImageView) findViewById(R.id.img_select01);
        image_select2 = (ImageView) findViewById(R.id.img_select02);
        image_select3 = (ImageView) findViewById(R.id.img_select03);
        ll_change = (LinearLayout) findViewById(R.id.ll_change);
        ll_account_01 = (LinearLayout) findViewById(R.id.ll_account_01);
        ll_account_02 = (LinearLayout) findViewById(R.id.ll_account_02);
        ll_account_03 = (LinearLayout) findViewById(R.id.ll_account_03);
        ll_determine_newpwd = (LinearLayout) findViewById(R.id.ll_determine_newpwd);
        text_getsms = (LinearLayout) findViewById(R.id.text_getsms);
        text_next = (LinearLayout) findViewById(R.id.text_next);
        txt_pwd_01 = (TextView) findViewById(R.id.text_pwd_01);
        txt_pwd_02 = (TextView) findViewById(R.id.text_pwd_02);
//        str=str.Substring(0,i);
// str=str.Substring(str.Length-i);
        String phone = AppContext.getInstance().getUserInfo().getUserPhone();
        txt_pwd_01.setText(phone.substring(0,3));
        txt_pwd_02.setText(phone.substring(phone.length()-4));
    }

    @Override
    public void initData() {
        image_select1.setVisibility(View.VISIBLE);
        image_select2.setVisibility(View.GONE);
        image_select3.setVisibility(View.GONE);
    }

    @Override
    public void initClick() {
        text_getsms.setOnClickListener(this);
        ll_select1.setOnClickListener(this);
        ll_select2.setOnClickListener(this);
        ll_select3.setOnClickListener(this);
        img_finish.setOnClickListener(this);
        image_back.setOnClickListener(this);
        ll_change.setOnClickListener(this);
        text_next.setOnClickListener(this);
        ll_determine_newpwd.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_select01:
                image_select1.setVisibility(View.VISIBLE);
                image_select2.setVisibility(View.GONE);
                image_select3.setVisibility(View.GONE);
                type = Constant.OpinionType01;
                break;
            case R.id.ll_select02:
                image_select2.setVisibility(View.VISIBLE);
                image_select1.setVisibility(View.GONE);
                image_select3.setVisibility(View.GONE);
                type = Constant.OpinionType02;
                break;
            case R.id.ll_select03:
                image_select3.setVisibility(View.VISIBLE);
                image_select2.setVisibility(View.GONE);
                image_select1.setVisibility(View.GONE);
                type = Constant.OpinionType03;
                break;
            case R.id.ll_change:
                if (type!=null){
                    ll_account_01.setVisibility(View.GONE);
                    ll_account_02.setVisibility(View.VISIBLE);
                    image_back.setVisibility(View.GONE);
                    img_finish.setVisibility(View.VISIBLE);
                    textView_head.setText("安全检测");
                    textView1.setTextColor(Color.BLACK);
                    textView2.setTextColor(getResources().getColor(R.color.orange));
                } else {
                    Toast.makeText(context,"请选择需要修改的选项",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_account_back:
                finish();
                break;
            case R.id.img_account_finish:
                finish();
                break;
            case R.id.text_getsms:
                if (count == 60){
                    getSMS();
                }
                break;
            case R.id.text_next:
                getNext();
                break;
            case R.id.ll_determine_newpwd:
                remind();
                break;

        }

    }
//验证验证码
    public void getNext(){
        String code = edit_reset.getText().toString();
        if (StringUtils.isEmpty(code)){
            Toast.makeText(context,"请填写验证码",Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("userPhone",AppContext.getInstance().getUserInfo().getUserPhone());
        map.put("code",code);
        map.put("type",type);
        ServerRequest.requestHttp(this, ServerUrl.modifyOne, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                ll_account_02.setVisibility(View.GONE);
                ll_account_03.setVisibility(View.VISIBLE);
                textView_head.setText("修改登录密码");
                textView1.setTextColor(Color.BLACK);
                textView2.setTextColor(Color.BLACK);
                textView3.setTextColor(getResources().getColor(R.color.orange));
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    //发送短信
    public void getSMS(){
        Map<String,Object> map = new HashMap<>();
        map.put("phone",AppContext.getInstance().getUserInfo().getUserPhone());
        map.put("smsType","3");
        ServerRequest.requestHttp(this, ServerUrl.sendSMS, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                text_countdown.setVisibility(View.VISIBLE);
                text_yanzehngcode.setVisibility(View.GONE);
                handler.sendEmptyMessageDelayed(0,0);
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
    //确认密码
    public void remind(){
        String value = edit_newpwd.getText().toString();
        if (StringUtils.isEmpty(value)){
            Toast.makeText(context,"请输入修改信息",Toast.LENGTH_SHORT).show();
            return;
        }
        if (type == Constant.OpinionType01){
            if(!value.matches("[a-zA-Z0-9]{6,24}")){
                Toast.makeText(this,"密码格式不正确",Toast.LENGTH_SHORT).show();
                return;
            }
        }else if (type == Constant.OpinionType02){
            text_pwd_remind.setText("  ");
            edit_newpwd.setHint("请输入新的手机号");
        }else if (type == Constant.OpinionType03){
            text_pwd_remind.setText("  ");
            edit_newpwd.setHint("请输入新的邮箱");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("value",value);
        map.put("type",type);
        ServerRequest.requestHttp(this, ServerUrl.modifyTwo, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
    int count = 60;
    Handler handler =new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(count!=0) {
                text_countdown.setText("重新发送("+count + ")");
                handler.sendEmptyMessageDelayed(0,1000);
                text_countdown.setEnabled(false);
                count--;
            }else{
                text_countdown.setVisibility(View.GONE);
                text_yanzehngcode.setVisibility(View.VISIBLE);
                count = 60;
                text_countdown.setText("重新发送("+count + ")");
            }
        }

    };
}
