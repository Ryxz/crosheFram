package com.croshe.farming.farm;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.Entity.MonitorsEntity;
import com.croshe.farming.FramAdapter.InternetThingsAdapter;
import com.croshe.farming.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class InternetThingsActivity extends CrosheBaseActivity {
    private List<MonitorsEntity> monitorsList = new ArrayList<>();
    private RecyclerView recycler_things;
    private InternetThingsAdapter thingsAdapter;
    private TextView text_head;
    private LinearLayout ll_back,ll_recy_internet,ll_null_internet;
    private Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //要做的事情
            thingsAdapter.notifyDataSetChanged();
            handler.postDelayed(this, 3000);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_internet_things);
        monitorsList = (List<MonitorsEntity>) getIntent().getSerializableExtra("monitorsList");
        initView();
        initClick();
    }

    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("土地检测");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        thingsAdapter = new InternetThingsAdapter(context,monitorsList);
        recycler_things = (RecyclerView) findViewById(R.id.recycler_things);
        recycler_things.setAdapter(thingsAdapter);
        recycler_things.setLayoutManager(new LinearLayoutManager(this));
        ll_recy_internet = (LinearLayout) findViewById(R.id.ll_recy_internet);
        ll_null_internet = (LinearLayout) findViewById(R.id.ll_null_internet);
        if (monitorsList.size()==0){
            ll_null_internet.setVisibility(View.VISIBLE);
            ll_recy_internet.setVisibility(View.GONE);
        }
        handler.postDelayed(runnable, 3000);//每两秒执行一次runnable.
        handler.removeCallbacks(runnable);
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
        switch (v.getId()){
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
