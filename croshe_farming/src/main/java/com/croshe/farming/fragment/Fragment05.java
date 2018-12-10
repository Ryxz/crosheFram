package com.croshe.farming.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.UserInfo;
import com.croshe.farming.PersonalCenter.MyWarehouseActivity;
import com.croshe.farming.PersonalCenter.PersonalMessageActivity;
import com.croshe.farming.PersonalCenter.SeTupActivity;
import com.croshe.farming.R;
import com.croshe.farming.activity.BalanceActivity;
import com.croshe.farming.activity.MyOrderActivity;
import com.croshe.farming.PersonalCenter.SignListActivity;
import com.croshe.farming.product.ProductCollectionActivity;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by niuyongwei on 17/5/23.
 */

public class Fragment05 extends Fragment implements View.OnClickListener{
    private ImageView iv_avatars;
    private TextView tv_nick_name;
    private TextView tv_moeny,tv_Sign,tv_Sign_1;
    private LinearLayout ll_my_1,ll_my_2,ll_my_3,ll_my_4,ll_my_5,ll_sign,img_fragment1_up;
    private UserInfo userInfo = AppContext.getInstance().getUserInfo();
    private String newImg,newName;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_05, container, false);
        return view;
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("changeUserInfo");
        // 注册广播
        getActivity().registerReceiver(mBroadcastReceiver, myIntentFilter);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("changeUserInfo")) {
                newImg = intent.getStringExtra("newImg");
                newName = intent.getStringExtra("newName");
                tv_nick_name.setText(newName);
                String Imageview  = newImg;
                ImageLoader.getInstance().displayImage("file://"+Imageview,iv_avatars,AppContext.image_display_options);
            }

        }
    };
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        refreshUser();
        if (userInfo.getIfSign()==0){
            tv_Sign.setText("签到");
        }else {
            tv_Sign.setText("今日已签到");
            ll_sign.setBackgroundResource(R.drawable.bg_orange_20);
            tv_Sign.setTextColor(getResources().getColor(R.color.white));
        }
        initDate();
        initEvent();
        registerBoradcastReceiver();
    }


    public void initView(){
        img_fragment1_up= (LinearLayout) getActivity().findViewById(R.id.img_fragment1_up_1);
        iv_avatars= (ImageView) getActivity().findViewById(R.id.iv_avatars);
        tv_nick_name= (TextView) getActivity().findViewById(R.id.tv_nick_name);
        tv_moeny= (TextView) getActivity().findViewById(R.id.tv_moeny);
        tv_Sign= (TextView) getActivity().findViewById(R.id.tv_Sign);
        tv_Sign_1= (TextView) getActivity().findViewById(R.id.tv_Sign_1);
        ll_sign= (LinearLayout) getActivity().findViewById(R.id.ll_sign);
        ll_my_1= (LinearLayout) getActivity().findViewById(R.id.ll_my_1);
        ll_my_2= (LinearLayout) getActivity().findViewById(R.id.ll_my_2);
        ll_my_3= (LinearLayout) getActivity().findViewById(R.id.ll_my_3);
        ll_my_4= (LinearLayout) getActivity().findViewById(R.id.ll_my_4);
        ll_my_5= (LinearLayout) getActivity().findViewById(R.id.ll_my_5);

    }
    public void initDate(){

    }

    @Override
    public void onResume() {
        super.onResume();
        registerBoradcastReceiver();
    }

    public void initEvent(){
        img_fragment1_up.setOnClickListener(this);
        iv_avatars.setOnClickListener(this);
        ll_my_1.setOnClickListener(this);
        ll_my_2.setOnClickListener(this);
        ll_my_3.setOnClickListener(this);
        ll_my_4.setOnClickListener(this);
        ll_sign.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_avatars:
                startActivity(new Intent(getActivity(), PersonalMessageActivity.class));
                break;
            case R.id.img_fragment1_up_1:
                Intent in = new Intent(getActivity(), SeTupActivity.class);
                startActivity(in);
                break;
            case R.id.ll_sign:
                if (userInfo.getIfSign()==0){
                    tv_Sign.setText("今日已签到");
                    ll_sign.setBackgroundResource(R.drawable.bg_orange_20);
                    tv_Sign.setTextColor(getResources().getColor(R.color.white));
                    userInfo.setIfSign(1);
                    signDay();
                    AppContext.getInstance().saveUserInfo(userInfo);
                    tv_moeny.setText(AppContext.getInstance().getUserInfo().getSumMonthScore());
                }else {
                    getActivity().startActivity(new Intent(getActivity(), SignListActivity.class));
                }
                break;
            case R.id.ll_my_1:
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
                break;
            case R.id.ll_my_2:
                Intent in1 = new Intent(getActivity(), MyWarehouseActivity.class);
                startActivity(in1);
                break;
            case R.id.ll_my_3:
                startActivity(new Intent(getActivity(), BalanceActivity.class));
                break;
            case R.id.ll_my_4:
                startActivity(new Intent(getActivity(), ProductCollectionActivity.class));
                break;
        }
    }

    public void signDay(){
        HttpRequest.sign(getActivity(), new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                refreshUser();
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void refreshUser(){
        HttpRequest.refreshUser(getContext(), new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                UserInfo userInfo = JSON.parseObject(json,UserInfo.class);
                AppContext.getInstance().saveUserInfo(userInfo);
                String Imageview  = AppContext.getInstance().getUserInfo().getUserHeadImg();
                ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+Imageview,iv_avatars,AppContext.image_display_options);
                tv_nick_name.setText(AppContext.getInstance().getUserInfo().getUserNickName());
                tv_moeny.setText(AppContext.getInstance().getUserInfo().getSumMonthScore());
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }

        });
    }
}
