package com.croshe.farming.product;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.Product;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.Entity.StandardNameEntity;
import com.croshe.farming.R;
import com.croshe.farming.productAdapter.ParameterAdapter;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图文详情
 * Created by Administrator on 2017/7/7.
 */

public class ProductPWDetailActivity extends CrosheBaseActivity {

    private LinearLayout ll_see_img,ll_see_parameter,ll_big_picture,ll_null;
    private RelativeLayout ll_excel;
    private TextView text_see_img,text_arameter;
    private ImageView img_big,img_pw_back;
    private RecyclerView recyclerView_pw;
    private List<StandardNameEntity> list = new ArrayList<>();
    private String id;
    private ParameterAdapter parameterAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_picture_wz_detail);
        id = getIntent().getStringExtra("productId");
        initView();
        initClick();
    }

    @Override
    public void initView() {
        ll_see_img = (LinearLayout) findViewById(R.id.ll_see_img);
        ll_see_parameter = (LinearLayout) findViewById(R.id.ll_see_parameter);
        ll_big_picture = (LinearLayout) findViewById(R.id.ll_big_picture);
        ll_excel = (RelativeLayout) findViewById(R.id.ll_excel);
        ll_null = (LinearLayout) findViewById(R.id.ll_null);

        text_see_img = (TextView) findViewById(R.id.text_see_img);
        text_arameter = (TextView) findViewById(R.id.text_arameter);
        img_big = (ImageView) findViewById(R.id.img_big);
        img_pw_back = (ImageView) findViewById(R.id.img_pw_back);

        recyclerView_pw = (RecyclerView) findViewById(R.id.recyclerView_pw);
        parameterAdapter = new ParameterAdapter(context,list);
        recyclerView_pw.setAdapter(parameterAdapter);
        recyclerView_pw.setLayoutManager(new LinearLayoutManager(this));
        getProductDetail(id);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        ll_see_img.setOnClickListener(this);
        ll_see_parameter.setOnClickListener(this);
        img_pw_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_see_img:
                text_see_img.setTextColor(getResources().getColor(R.color.orange));
                text_arameter.setTextColor(Color.BLACK);
                ll_big_picture.setVisibility(View.VISIBLE);
                ll_excel.setVisibility(View.GONE);
                getProductDetail(id);
                break;
            case R.id.ll_see_parameter:
                text_see_img.setTextColor(Color.BLACK);
                text_arameter.setTextColor(getResources().getColor(R.color.orange));
                ll_big_picture.setVisibility(View.GONE);
                ll_excel.setVisibility(View.VISIBLE);
                getProductDetail(id);
                break;
            case R.id.img_pw_back:
                finish();
                break;
        }

    }
    //获得商品详情
    public void getProductDetail(String id){
        Map<String,Object> map = new HashMap<>();
        map.put("productId", id);
        ServerRequest.requestHttp(context, ServerUrl.getProductDetails, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Product products = JSON.parseObject(json,Product.class);
                if (products!=null){
                    String img = products.getProductBigImage();
                    ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+img,img_big,AppContext.image_display_options);
                    List<StandardNameEntity> standardlist = products.getStandardName();
                    list.clear();
                    if (standardlist!=null&&standardlist.size()>0){
                        list.addAll(standardlist);
                        parameterAdapter.notifyDataSetChanged();
                    }else {
                        ll_null.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();

            }
        });
    }
}
