package com.croshe.farming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.extend.CrosheFragmentBaseActivity;
import com.croshe.farming.R;
import com.croshe.farming.fragment.DistributionFragment;
import com.croshe.farming.fragment.ShoppingOrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class MyOrderActivity extends CrosheFragmentBaseActivity implements View.OnClickListener{
    private LinearLayout ll_shop,ll_pes;
    private TextView shoporder1,pesorder1;
    private LinearLayout img_order_back;
    private FragmentManager fragmentManager;
    private List<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment = new Fragment();
    private int currentIndex = 0;
    private FragmentTransaction mFragmentTransaction;
    Fragment[] mFragment = new Fragment[2];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_myorder);
        initView();
        initClick();
    }

    public void initView() {
        ll_shop = (LinearLayout) findViewById(R.id.ll_shop);
        ll_pes = (LinearLayout) findViewById(R.id.ll_pes);
        shoporder1 = (TextView) findViewById(R.id.text_shoporder1);
        pesorder1 = (TextView) findViewById(R.id.text_pesorder1);

        img_order_back = (LinearLayout) findViewById(R.id.img_order_back);
        mFragment[0] = this.getSupportFragmentManager().findFragmentById(R.id.orderFragment1);
        mFragment[1] = this.getSupportFragmentManager().findFragmentById(R.id.orderFragment2);
        mFragmentTransaction =  getSupportFragmentManager().beginTransaction().hide(mFragment[0]).hide(mFragment[1]);
        mFragmentTransaction.show(mFragment[0]).commit();
    }

    public void initData() {

    }

    public void initClick() {
        ll_shop.setOnClickListener(this);
        ll_pes.setOnClickListener(this);
        img_order_back.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_shop:
                ll_shop.setBackgroundResource(R.drawable.bg_whitelefet);
                shoporder1.setTextColor(getResources().getColor(R.color.orange));
                ll_pes.setBackgroundResource(R.drawable.alert_orange_right_5);
                pesorder1.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 0;
                mFragmentTransaction=  getSupportFragmentManager().beginTransaction().hide(mFragment[0]).hide(mFragment[1]);
                mFragmentTransaction.show(mFragment[currentIndex]).commit();
                break;
            case R.id.ll_pes:
                ll_shop.setBackgroundResource(R.drawable.alert_orange_left_5);
                shoporder1.setTextColor(getResources().getColor(R.color.white));
                ll_pes.setBackgroundResource(R.drawable.bg_whiteright);
                pesorder1.setTextColor(getResources().getColor(R.color.orange));
                currentIndex = 1;
                mFragmentTransaction=  getSupportFragmentManager().beginTransaction().hide(mFragment[0]).hide(mFragment[1]);
                mFragmentTransaction.show(mFragment[currentIndex]).commit();
                break;
            case R.id.img_order_back:
                finish();
                break;
        }

    }

    /**
     * 使用show() hide()切换页面
     * 显示fragment
     */
    private void showFragment(){

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //如果之前没有添加过
//        if(!fragments.get(currentIndex).isAdded()){
//
//        }else{
//            transaction.hide(currentFragment).show(fragments.get(currentIndex));
//        }

        if (currentFragment != null) {
            transaction.remove(currentFragment);
        }
//        transaction.add(R.id.content,fragments.get(currentIndex));

        currentFragment = fragments.get(currentIndex);

        transaction.commit();

    }
}
