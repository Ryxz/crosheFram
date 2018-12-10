package com.croshe.farming.farm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/15.
 */

public class PlantNumAcyivity extends CrosheBaseActivity {
    private TextView textView;
    private LinearLayout ll_back;
    private EditText edit_choose_num;
    private LinearLayout ll_num_qd;
    private int storeId,style;
    private String userFarmId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storeId = getIntent().getIntExtra("storeId",0);
        userFarmId = getIntent().getStringExtra("userFarmId");
        style = getIntent().getIntExtra("style",0);
        setContentView(R.layout.layout_choose_num);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        textView = (TextView) findViewById(R.id.text_head);
        textView.setText("选择数量");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        edit_choose_num = (EditText) findViewById(R.id.edit_choose_num);
        ll_num_qd = (LinearLayout) findViewById(R.id.ll_num_qd);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
        ll_num_qd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_num_qd:
                if (StringUtils.isEmpty(edit_choose_num.getText().toString())){
                    Toast.makeText(context,"请输入数量",Toast.LENGTH_SHORT).show();
                    return;
                }
                farmingLand();
                break;
        }
    }

    public void farmingLand(){
        Map<String,Object> map=new HashMap<>();
        map.put("storeId",storeId);//仓库ID
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("userFarmId",userFarmId);//用户农场ID
        map.put("count",edit_choose_num.getText().toString());
        map.put("landType",style);//土地类型

        ServerRequest.requestHttp(context, ServerUrl.farmingLand, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                finish();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
