package com.croshe.farming.farm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheViewPageView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.CanGrowInfo;
import com.croshe.farming.Entity.FramLandInfo;
import com.croshe.farming.Entity.ImgInfo;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.R;
import com.croshe.farming.activity.CashierActivity;
import com.croshe.farming.activity.ProductDetailsActivity;
import com.croshe.farming.adapter.PastBreedingAdapter;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//可养殖可种植
public class MyFramDetailasTwoActivity extends CrosheBaseActivity {
    private ImageView iv_message_2;
    private TextView tv_my_fram_qu;
    private TextView tv_framname,tv_framname_1,tv_price1,tv_price2,tv_price3,tv_price4,tv_price5;
    private LinearLayout fragment01_viewpage_5,ll_rent,ll_go_fram,iv_left_9;
    private TextView tv_issue;
    private RecyclerView recyclerView_issue;
    private Button btu_rent;
    private String landId;
    private TextView tv_seeds,tv_seeds3;
    private List<CanGrowInfo> canGrowInfoList = new ArrayList<>();
    private PastBreedingAdapter adapter;
    private LinearLayout ll_issue;
    private int landState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fram_detailas_two);
        initView();
        initClick();
        initData();
    }

    @Override
    public void initView() {
        ll_issue= (LinearLayout) findViewById(R.id.ll_issue);
        iv_left_9= (LinearLayout) findViewById(R.id.iv_left_9);
        iv_message_2= (ImageView) findViewById(R.id.iv_message_2);
        tv_my_fram_qu= (TextView) findViewById(R.id.tv_my_fram_qu);
        tv_framname= (TextView) findViewById(R.id.tv_framname);
        tv_framname_1= (TextView) findViewById(R.id.tv_framname_1);
        tv_price1= (TextView) findViewById(R.id.tv_price1);
        tv_price2= (TextView) findViewById(R.id.tv_price2);
        tv_price3= (TextView) findViewById(R.id.tv_price3);
        tv_price4= (TextView) findViewById(R.id.tv_price4);
        tv_price5= (TextView) findViewById(R.id.tv_price5);
        fragment01_viewpage_5= (LinearLayout) findViewById(R.id.fragment01_viewpage_5);
        tv_issue= (TextView) findViewById(R.id.tv_issue);
        recyclerView_issue= (RecyclerView) findViewById(R.id.recyclerView_issue);
//        btu_rent= (Button) findViewById(R.id.btu_rent);
        tv_seeds= (TextView) findViewById(R.id.tv_seeds);
        tv_seeds3= (TextView) findViewById(R.id.tv_seeds3);
        adapter = new PastBreedingAdapter(canGrowInfoList,this);
        ll_rent= (LinearLayout) findViewById(R.id.ll_rent);
        ll_go_fram= (LinearLayout) findViewById(R.id.ll_go_fram);

    }

    @Override
    public void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView_issue.setLayoutManager(manager);
        recyclerView_issue.setAdapter(adapter);

        Intent in = getIntent();
        landId = in.getStringExtra("landId");
        landState = in.getIntExtra("landState",0);
        if (landState == 0||landState==2){
            ll_rent.setVisibility(View.GONE);
        }else {
            ll_rent.setVisibility(View.VISIBLE);
        }
        showLandDetails();
    }

    @Override
    public void initClick() {
        iv_left_9.setOnClickListener(this);
        iv_message_2.setOnClickListener(this);
        ll_rent.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.iv_left_9:
             finish();
             break;
         case R.id.iv_message_2:
             break;
         case R.id.ll_rent:
             addLandOrder();
             break;
     }
    }
    public void showLandDetails(){
        final List<String> list = new ArrayList<>();
        Map<String,Object> map  = new HashMap<>();
        map.put("landId",landId);
        ServerRequest.requestHttp(this, ServerUrl.showLandDetails, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                FramLandInfo framLandInfo = JSON.parseObject(json,FramLandInfo.class);
                if(!StringUtils.isEmpty(framLandInfo.toString())){
                   if(framLandInfo.getLandState()==1){
                       tv_my_fram_qu.setText("去养植");
                       tv_framname_1.setText("推荐种植："+framLandInfo.getLandTags());
                       tv_seeds.setText("种子");
                       tv_seeds3.setText("施肥");
                   }else if (framLandInfo.getLandState() == 0){
                       tv_my_fram_qu.setText("已种植");
                       tv_framname_1.setText("推荐种植："+framLandInfo.getLandTags());
                       tv_seeds.setText("种子");
                       tv_seeds3.setText("施肥");
                   }else{
                       tv_my_fram_qu.setText("去种植");
                       tv_framname_1.setText("推荐养植："+framLandInfo.getLandTags());
                       tv_seeds.setText("苗种");
                       tv_seeds3.setText("饲料");
                   }
                    tv_framname.setText(framLandInfo.getLandName());
                    tv_price1.setText(framLandInfo.getSeedMoney()+"元");
                    tv_price2.setText(framLandInfo.getDrugMoney()+"元");
                    tv_price3.setText(framLandInfo.getFeedMoney()+"元");
                    tv_price4.setText(framLandInfo.getLandMoney()+"元");
                    tv_price5.setText(framLandInfo.getManageMoney()+"元");
                   if(framLandInfo.getLandImages()!=null&&framLandInfo.getLandImages().size()>0){
                       for(ImgInfo fo:framLandInfo.getLandImages()){
                           list.add(ServerUrl.mainUrl+fo.getFilePath());
                       }
                       CrosheViewPageView crosheViewPageView = new CrosheViewPageView(context, list, 185);
                       fragment01_viewpage_5.addView(crosheViewPageView);
                   }

                    if(!StringUtils.isEmpty(framLandInfo.getLandPastModels().toString())){
                        canGrowInfoList.clear();
                        canGrowInfoList.addAll(framLandInfo.getLandPastModels());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
//    int page;
//    public void myFramLandDetails(int type){
//        HttpRequest.getList(context, AppContext.getInstance().getUserInfo().getUserId(), page, type, new ServerResultListener() {
//            @Override
//            public void onSuccess(String json, String msg) {
//
//            }
//
//            @Override
//            public void onFailure(String msg) {
//
//            }
//        });
//    }
    public void addLandOrder(){
        HttpRequest.addLandOrder(context, landId, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                OrderModels order = JSON.parseObject(json,OrderModels.class);
                String code = order.getOrderCode();
                double orderpirce = order.getOrderTruePrice();
                startActivity(new Intent(MyFramDetailasTwoActivity.this,CashierActivity.class)
                        .putExtra("code",code)
                        .putExtra("price",String.valueOf(orderpirce))
                        .putExtra("order",order));
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

}

