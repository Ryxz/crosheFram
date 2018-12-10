package com.croshe.farming.activity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.UserInfo;
import com.croshe.farming.Entity.UserSet;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/24.
 */

public class MessageRemindActivity extends CrosheBaseActivity {
    private ImageView button1,button2,button3,button4,button5,button6;
    private String btn1,btn2,btn3,setId;
    private boolean change1 ,change2 ,change3 ;
    private LinearLayout ll_01,ll_02,ll_03;
    private boolean isCheck = false;
    private ImageView iv_back_3;
    private MessageRemindActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_messageremind);//userSet
//        registerBoradcastReceiver();
        UserInfo userInfo = AppContext.getInstance().getUserInfo();
        btn1 = userInfo.getUserSet().getSetMsgVoice();
        btn2 = userInfo.getUserSet().getSetMsgVibrat();
        btn3 = userInfo.getUserSet().getSetMsgNewNotice();
        setId = userInfo.getUserSet().getSetId();
        initView();
        initClick();
    }
    @Override
    public void initView() {

        iv_back_3 = (ImageView) findViewById(R.id.iv_back_3);
        ll_01 = (LinearLayout) findViewById(R.id.ll_01);
        ll_02 = (LinearLayout) findViewById(R.id.ll_02);
        ll_03 = (LinearLayout) findViewById(R.id.ll_03);

        button1 = (ImageView) findViewById(R.id.button_01);
        button2 = (ImageView) findViewById(R.id.button_02);
        button3 = (ImageView) findViewById(R.id.button_03);
        button4 = (ImageView) findViewById(R.id.button_04);
        button5 = (ImageView) findViewById(R.id.button_05);
        button6 = (ImageView) findViewById(R.id.button_06);
        if ("0".equals(btn1)){
            button1.setVisibility(View.VISIBLE);
            button2.setVisibility(View.GONE);
            change1 = true;
        }else if ("1".equals(btn1)){
            button1.setVisibility(View.GONE);
            button2.setVisibility(View.VISIBLE);
            change1 = false;
        }
        if ("0".equals(btn2)){
            button3.setVisibility(View.VISIBLE);
            button4.setVisibility(View.GONE);
            change2 = true;
        }else if ("1".equals(btn2)){
            button3.setVisibility(View.GONE);
            button4.setVisibility(View.VISIBLE);
            change2 = false;
        }
        if ("0".equals(btn3)){
            button5.setVisibility(View.VISIBLE);
            button6.setVisibility(View.GONE);
            change3 = true;
        }else if ("1".equals(btn3)){
            button5.setVisibility(View.GONE);
            button6.setVisibility(View.VISIBLE);
            change3 = false;
        }
    }

    @Override
    public void initData() {
        if (isCheck){
            Map<String,Object> map =  new HashMap<>();
            map.put("setId",setId);
            map.put("setMsgNewNotice",btn1);
            map.put("setMsgVibrat",btn2);
            map.put("setMsgVoice",btn3);
            ServerRequest.requestHttp(context, ServerUrl.userSet, map, "", new ServerResultListener() {
                @Override
                public void onSuccess(String json, String msg) {

                }

                @Override
                public void onFailure(String msg) {

                }
            });
        }
    }

    @Override
    public void initClick() {
        ll_01.setOnClickListener(this);
        ll_02.setOnClickListener(this);
        ll_03.setOnClickListener(this);
        iv_back_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_3:
                finish();
                break;
            case R.id.ll_01:
                if (change1){
                    change1 = false;
                    btn1 = "0";
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.GONE);
                    isCheck = true;
                }else {
                    change1 = true;
                    btn1 = "1";
                    button1.setVisibility(View.GONE);
                    button2.setVisibility(View.VISIBLE);
                    isCheck = true;
                }
                initData();
                break;
            case R.id.ll_02:
                if (change2){
                    change2 = false;
                    isCheck = true;
                    btn2 = "0";
                    button3.setVisibility(View.VISIBLE);
                    button4.setVisibility(View.GONE);
                    //震动一次
//                    vibrator.vibrate(VIBRATE_DURATION);
                    //第一个参数，指代一个震动的频率数组。每两个为一组，每组的第一个为等待时间，第二个为震动时间。
                    //        比如  [2000,500,100,400],会先等待2000毫秒，震动500，再等待100，震动400
                    //第二个参数，repest指代从 第几个索引（第一个数组参数） 的位置开始循环震动。
                    //会一直保持循环，我们需要用 vibrator.cancel()主动终止
//                    vibrator.vibrate(new long[]{300,500},0);
                    vibrator(context,100);
                }else {
                    change2 = true;
                    btn2 = "1";
                    button3.setVisibility(View.GONE);
                    button4.setVisibility(View.VISIBLE);
                    isCheck = true;
                }
                initData();
                break;
            case R.id.ll_03:
                if (change3){
                    change3 = false;
                    btn3 = "0";
                    button5.setVisibility(View.VISIBLE);
                    button6.setVisibility(View.GONE);
                    isCheck = true;
                }else {
                    change3 = true;
                    btn3 = "1";
                    button5.setVisibility(View.GONE);
                    button6.setVisibility(View.VISIBLE);
                    isCheck = true;
                }
                initData();
                break;
        }


    }
    /**
     * 震动
     * @param context
     * @param duration
     */
    public static void vibrator(Context context, int duration) {
        Vibrator vb = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vb.vibrate(duration);
    }

}
