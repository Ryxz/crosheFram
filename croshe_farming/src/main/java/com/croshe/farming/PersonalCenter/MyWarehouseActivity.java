package com.croshe.farming.PersonalCenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.R;
import com.croshe.farming.fragment.WarehouseFragment01;
//我的仓库
public class MyWarehouseActivity extends CrosheBaseActivity  {
    private LinearLayout ll_my_1,ll_my_2;
    private LinearLayout iv_back_1;
    private TextView tv_my_1,tv_my_2,tv_my_3,tv_my_4;
    Fragment[] mFragment = new Fragment[2];
    int idex=0;
    private FragmentTransaction mFragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_warehouse);
        initView();
        initClick();
        initData();
    }
    @Override
    public void initView() {
        iv_back_1= (LinearLayout) findViewById(R.id.iv_back_1);
        ll_my_1= (LinearLayout) findViewById(R.id.ll_my_cangku);
        ll_my_2= (LinearLayout) findViewById(R.id.ll_my_cangku_1);
        tv_my_1= (TextView) findViewById(R.id.tv_my_1);
        tv_my_2= (TextView) findViewById(R.id.tv_my_2);
        tv_my_3= (TextView) findViewById(R.id.tv_my_3);
        tv_my_4= (TextView) findViewById(R.id.tv_my_4);
        mFragment[0] = this.getSupportFragmentManager().findFragmentById(R.id.WarehouseFragment01);
        mFragment[1] = this.getSupportFragmentManager().findFragmentById(R.id.WarehouseFragment02);
        mFragmentTransaction=  getSupportFragmentManager().beginTransaction().hide(mFragment[0]).hide(mFragment[1]);
        mFragmentTransaction.show(mFragment[0]).commit();
        registerBoradcastReceiver();
    }

    @Override
    public void initData() {
        tv_my_2.setText("("+0+")");
    }
    @Override
    public void initClick() {
        ll_my_1.setOnClickListener(this);
        ll_my_2.setOnClickListener(this);
        iv_back_1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.ll_my_cangku:
                 tv_my_1.setTextColor(getResources().getColor(R.color.colorPrimary));
                 tv_my_2.setTextColor(getResources().getColor(R.color.colorPrimary));
                 tv_my_3.setTextColor(getResources().getColor(R.color.colorAccent));
                 tv_my_4.setTextColor(getResources().getColor(R.color.colorAccent));
                 idex=0;
                 mFragmentTransaction=  getSupportFragmentManager().beginTransaction().hide(mFragment[0]).hide(mFragment[1]);
                 mFragmentTransaction.show(mFragment[idex]).commit();
                 break;
             case R.id.ll_my_cangku_1:
                 tv_my_3.setTextColor(getResources().getColor(R.color.colorPrimary));
                 tv_my_4.setTextColor(getResources().getColor(R.color.colorPrimary));
                 tv_my_1.setTextColor(getResources().getColor(R.color.colorAccent));
                 tv_my_2.setTextColor(getResources().getColor(R.color.colorAccent));
                 idex=1;
                 mFragmentTransaction=  getSupportFragmentManager().beginTransaction().hide(mFragment[0]).hide(mFragment[1]);
                 mFragmentTransaction.show(mFragment[idex]).commit();
                 break;
             case R.id.iv_back_1:
                 finish();
                 break;

         }

    }

    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("nongzicount");
        myIntentFilter.addAction("shouchengcount");

        // 注册广播
        registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String addAction = intent.getAction();
            String count = intent.getStringExtra("count");
            String count1 = intent.getStringExtra("count1");
            if (addAction.equals("nongzicount")) {
                if (!StringUtils.isEmpty(count)) {
                    tv_my_2.setText("(" + count + ")");
                } else {
                    tv_my_2.setText("(" + 0 + ")");
                }
            } else if(addAction.equals("shouchengcount")){
                if (!StringUtils.isEmpty(count1)) {
                    tv_my_4.setText("(" + count1 + ")");
                } else {
                    tv_my_4.setText("(" + 0 + ")");
                }

            }
        }
    };
}
