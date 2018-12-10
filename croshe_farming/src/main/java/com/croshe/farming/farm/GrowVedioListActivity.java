package com.croshe.farming.farm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.Entity.FileEntity;
import com.croshe.farming.FramAdapter.GrowVedioAdapter;
import com.croshe.farming.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class GrowVedioListActivity extends CrosheBaseActivity {
    private TextView text_head;
    private LinearLayout ll_back,ll_vedio_list,ll_null_vedio;
    private GrowVedioAdapter vedioAdapter;
    private RecyclerView recycler_grow_vedio;
    private List<FileEntity> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_grow_vedio);
        list = (List<FileEntity>) getIntent().getSerializableExtra("fileList");
        initView();
        initClick();
    }

    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("生长视频");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_vedio_list = (LinearLayout) findViewById(R.id.ll_vedio_list);
        ll_null_vedio = (LinearLayout) findViewById(R.id.ll_null_vedio);

        recycler_grow_vedio = (RecyclerView) findViewById(R.id.recycler_grow_vedio);
        vedioAdapter = new GrowVedioAdapter(context,list);
        recycler_grow_vedio.setAdapter(vedioAdapter);
        recycler_grow_vedio.setLayoutManager(new LinearLayoutManager(this));
        if (list == null||list.size()==0){
            ll_vedio_list.setVisibility(View.GONE);
            ll_null_vedio.setVisibility(View.VISIBLE);
        }else {
            ll_vedio_list.setVisibility(View.VISIBLE);
            ll_null_vedio.setVisibility(View.GONE);
        }
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
