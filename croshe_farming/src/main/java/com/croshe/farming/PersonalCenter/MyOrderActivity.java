package com.croshe.farming.PersonalCenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.R;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
//我的订单
public class MyOrderActivity extends CrosheBaseActivity {
//   private ImageView iv_back,iv_right;
//    Fragment[] mFragment = new Fragment[2];
//    private SegmentTabLayout tl_1;
//    private  String titles[]={"购物订单","配送订单"};
//    private FragmentTransaction mFragmentTransaction;
//    int idex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_order);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
//        tl_1 = (SegmentTabLayout) findViewById(R.id.tl_1);
//        iv_back = (ImageView) findViewById(R.id.iv_back);
//        iv_right = (ImageView) findViewById(R.id.iv_right);
//        tl_1.setTabData(titles);
//        createFragment();
    }

    public void createFragment() {

//        mFragment[0] = this.getSupportFragmentManager().findFragmentById(R.id.fragment_01);
//        mFragment[1] = this.getSupportFragmentManager().findFragmentById(R.id.fragment_02);
//
//        mFragmentTransaction=  getSupportFragmentManager().beginTransaction().hide(mFragment[0]).hide(mFragment[1]);
//        mFragmentTransaction.show(mFragment[0]).commit();

    }



    @Override
    public void initData() {
//        iv_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        iv_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//
//            }
//        });
    }

    @Override
    public void initClick() {

//        tl_1.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//                if(position==0){
//                    idex=0;
//                }else{
//                    idex=1;
//                }
//                mFragmentTransaction=  getSupportFragmentManager().beginTransaction().hide(mFragment[0]).hide(mFragment[1]);
//                mFragmentTransaction.show(mFragment[idex]).commit();
//            }
//
//            @Override
//            public void onTabReselect(int position) {
//
//            }
//        });
    }

    @Override
    public void onClick(View v) {

    }
}
