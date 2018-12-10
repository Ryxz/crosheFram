package com.croshe.farming.PersonalCenter;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.DensityUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.Entity.SignEntity;
import com.croshe.farming.Entity.SignListEntity;
import com.croshe.farming.R;
import com.croshe.farming.View.SignWindow;
import com.croshe.farming.productAdapter.SignListAdapter;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.util.SelfDateTimeUtils;
import com.croshe.farming.util.SignListPopWindow;

import org.apache.commons.lang3.StringUtils;

import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/15.
 * 签到列表
 */

public class SignListActivity extends CrosheBaseActivity {
    private TextView text_head,text_sign_day,text_all_jifen;
    private LinearLayout ll_back,ll_show_pop,ll_sign_list;
    private RecyclerView recycler_sign;
    private SignListAdapter signAdapter;
    private LinearLayout ll_cancel;
    private List<SignListEntity> signList = new ArrayList<>();
    private PopupWindow popupWindow;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
    private Date date = new Date();
    private List<Integer> list = new ArrayList<>();
    public static int jifen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign_list);

        initView();
        initData();
        initClick();
        userSignList();
    }

    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("每日签到");
        text_sign_day = (TextView) findViewById(R.id.text_sign_day);
        text_all_jifen = (TextView) findViewById(R.id.text_all_jifen);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_show_pop = (LinearLayout) findViewById(R.id.ll_show_pop);
        ll_sign_list = (LinearLayout) findViewById(R.id.ll_sign_list);
        recycler_sign = (RecyclerView) findViewById(R.id.recycler_sign);
        signAdapter = new SignListAdapter(context,signList);
        recycler_sign.setAdapter(signAdapter);
        recycler_sign.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
        ll_show_pop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_show_pop:
                showPopwindow();
                ll_show_pop.setVisibility(View.GONE);
                break;
        }
    }
    //用户签到
    public void userSignList(){
        HttpRequest.userSignList(context, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                SignEntity signEntity = JSON.parseObject(json,SignEntity.class);
                if (signEntity!=null){
                    jifen = signEntity.getSumMonthScore();
                    text_sign_day.setText(String.valueOf(signEntity.getCountMonthDay())+"");
                    text_all_jifen.setText(String.valueOf(signEntity.getSumMonthScore())+"");
                    AppContext.getInstance().getUserInfo().setSumMonthScore(String.valueOf(signEntity.getSumMonthScore()));
                    List<SignListEntity> list = signEntity.getSigns();
                    if (list!= null&&list.size()>0){
                        signList.addAll(list);
                    }
                }
                signAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showPopwindow() {

        View view = LayoutInflater.from(context).inflate(R.layout.pop_window,null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        popupWindow = new PopupWindow(this,null,R.style.Transparent_Dialog);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(findViewById(R.id.ll_sign_list), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.setAnimationStyle(R.style.take_photo_anim);
//        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true); //设置PopupWindow的焦点
//        LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        FrameLayout.LayoutParams layoutParams= (FrameLayout.LayoutParams) view.getLayoutParams();
//        layoutParams.width= (int) DensityUtils.getWidthInPx(context);
//        view.setLayoutParams(layoutParams);
        ll_cancel = (LinearLayout) view.findViewById(R.id.ll_cancel);
        // 取消按钮

        ll_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                popupWindow.dismiss();
                ll_show_pop.setVisibility(View.VISIBLE);
            }
        });
        popupWindow.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (popupWindow.isShowing()) {
//                    popupWindow.dismiss();
//                    ll_show_pop.setVisibility(View.VISIBLE);
//                }
                if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                    ll_show_pop.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });
    }
}