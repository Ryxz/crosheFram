package com.croshe.farming.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.Entity.Collocation;
import com.croshe.farming.Entity.PackageModel;
import com.croshe.farming.Entity.Product;
import com.croshe.farming.R;
import com.croshe.farming.productAdapter.ScientificAdapter;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/22.
 */

public class ScientificCollocationActivity extends CrosheBaseActivity {
    private RecyclerView recycler_scientific;
    private TextView text_head;
    private LinearLayout ll_back;
    private List<PackageModel> list = new ArrayList<>();
    private ScientificAdapter adapter;
    private String productId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_scientific);
        productId = getIntent().getStringExtra("productId");
        initView();
        initData();
        initClick();
        getCollcatioon();
    }

    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("科学搭配");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        adapter = new ScientificAdapter(context,list);
        recycler_scientific = (RecyclerView) findViewById(R.id.recycler_scientific);
        recycler_scientific.setLayoutManager(new LinearLayoutManager(this));
        recycler_scientific.setAdapter(adapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
    public void getCollcatioon(){
        Map<String,Object> map = new HashMap<>();
        map.put("detailsProductId",productId);
        ServerRequest.requestHttp(context, ServerUrl.showPackageDetails, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Collocation collocation = JSON.parseObject(json,Collocation.class);
                list.clear();
                if (collocation!=null){
                    List<PackageModel> models = collocation.getPackageModels();
                    if (models!=null&&models.size()>0){
                        list.addAll(models);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
