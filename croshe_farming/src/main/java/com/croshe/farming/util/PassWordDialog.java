package com.croshe.farming.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.Toasts;
import com.croshe.farming.AppContext;
import com.croshe.farming.R;
import com.croshe.farming.activity.CashierActivity;
import com.croshe.farming.activity.CropListActivity;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.jungly.gridpasswordview.GridPasswordView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/19.
 */

public class PassWordDialog extends Dialog{
    private GridPasswordView pswView;
    private String code;
    private TextView text_pay_momey,text_balance;
    private Context context;
    public PassWordDialog(@NonNull Context context,String code,Double paymoney) {
        super(context,R.style.MyDialog);
        this.context = context;
        setContentView(R.layout.layout_password_dialog);
        this.code = code;
        pswView = (GridPasswordView) findViewById(R.id.pswView);
        text_pay_momey = (TextView) findViewById(R.id.text_pay_momey);
        text_balance = (TextView) findViewById(R.id.text_balance);
        text_pay_momey.setText("￥"+paymoney);
        pswView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String pwd) {
//                Toasts.showTips(getContext(), R.mipmap.img_success, pwd);
//                                ViewUtils.closeKeyboard(pswView);
                if (StringUtils.isEmpty(pwd)) {
                    Toasts.showTips(getContext(),0, "请输入密码");
                    return;
                }

                if (pwd.length() < 6) {
                    Toasts.showTips(getContext(),0, "密码不正确");
                    return;
//                    pswView.clearPassword();
                }
                balancePay(pwd);
            }
        });
        getBalance();
    }
//    零钱支付
    public void balancePay(String pwd){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("orderCode",code);
        map.put("payPassword",pwd);
        ServerRequest.requestHttp(getContext(), ServerUrl.balancePay, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                context.sendBroadcast(new Intent("refreshDistribution").putExtra("type",CashierActivity.type));
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                if (CashierActivity.type == 2){
                    context.sendBroadcast(new Intent("changeAdapter"));
                    getContext().startActivity(new Intent(context,CropListActivity.class));
                }
                dismiss();


//                ((Activity) context).finish();

            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getBalance(){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        ServerRequest.requestHttp(getContext(), ServerUrl.showUserBalance, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                text_balance.setText("(￥"+json+")");
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
