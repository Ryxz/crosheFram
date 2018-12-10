package com.croshe.farming.PersonalCenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.Toasts;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.UserInfo;
import com.croshe.farming.Login.RegisteredActivity;
import com.croshe.farming.R;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;

/**
 * Created by Administrator on 2017/7/19.
 */

public class SetPayPasswordActivity extends CrosheBaseActivity{
    private LinearLayout ll_back,ll_qd_payword;
    private TextView text_head;
    private String pay_word,user_phone,code;
    private EditText edit_set_payword,edit_user_phone,edit_payword_code;
    private UserInfo userInfo = AppContext.getInstance().getUserInfo();
    private Button bt_payword_yz,bt_payword_js;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_set_pay_password);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("密码设置");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        edit_set_payword = (EditText) findViewById(R.id.edit_set_payword);
        edit_user_phone = (EditText) findViewById(R.id.edit_user_phone);
        edit_payword_code = (EditText) findViewById(R.id.edit_payword_code);
        bt_payword_yz = (Button) findViewById(R.id.bt_payword_yz);
        bt_payword_js = (Button) findViewById(R.id.bt_payword_js);
        ll_qd_payword = (LinearLayout) findViewById(R.id.ll_qd_payword);
    }

    @Override
    public void initData() {
        edit_set_payword.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        edit_user_phone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        pay_word = edit_set_payword.getText().toString();
        user_phone = edit_user_phone.getText().toString();
        code = edit_payword_code.getText().toString();
    }

    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
        ll_qd_payword.setOnClickListener(this);
        bt_payword_yz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.bt_payword_yz:
                if(null==edit_user_phone.getText().toString()||edit_user_phone.getText().toString().length()<=0) {
                    Toasts.showTips(context,0,"手机号号码不可为空");
                    break;
                }else  if (!StringUtils.isMobileNum(edit_user_phone.getText().toString())) {
                    Toast.makeText(this, "手机号码不正确", Toast.LENGTH_SHORT).show();
                    break;
                }
                HttpRequest.sendSMS(context, edit_user_phone.getText().toString(), 4, new ServerResultListener() {
                    @Override
                    public void onSuccess(String json, String msg) {
                        bt_payword_yz.setVisibility(View.GONE);
                        bt_payword_js.setVisibility(View.VISIBLE);
                        handler.sendEmptyMessageDelayed(0,1000);
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String msg) {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.ll_qd_payword:
                if (user_phone!=null&&code!=null){
                    HttpRequest.modifyPayPassword(context, edit_set_payword.getText().toString(),edit_user_phone.getText().toString(),edit_payword_code.getText().toString(), new ServerResultListener() {
                        @Override
                        public void onSuccess(String json, String msg) {
                            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                            userInfo.setPayPassword(pay_word);
                            AppContext.getInstance().saveUserInfo(userInfo);
                            finish();
                        }

                        @Override
                        public void onFailure(String msg) {
                            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(context, "手机号码或者验证码为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    int count =59;
    Handler handler =new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(count!=0) {
                handler.sendEmptyMessageDelayed(0,1000);
                bt_payword_js.setText(count + "");
                bt_payword_js.setEnabled(false);
                count--;
            }else{
                bt_payword_js.setVisibility(View.GONE);
                bt_payword_yz.setVisibility(View.VISIBLE);
                count=59;
                bt_payword_js.setText(count + "");
            }
        }

    };
}
