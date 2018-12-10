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
import com.croshe.farming.Entity.CommentModel;
import com.croshe.farming.R;
import com.croshe.farming.productAdapter.ShowEvaluateAdapter;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/7/29.
 */

public class AllEvaluateAcitivity extends CrosheBaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate{
    private BGARefreshLayout refresh_show_alleva;
    private RecyclerView recycler_show_alleva;
    private TextView text_head;
    private LinearLayout ll_back;
    private String targetId;
    private ShowEvaluateAdapter adapter;
    private List<CommentModel> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_all_eva);
        targetId = getIntent().getStringExtra("packageId");
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("全部评论");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        refresh_show_alleva = (BGARefreshLayout) findViewById(R.id.refresh_alleva);
        refresh_show_alleva.setDelegate(this);
        refresh_show_alleva.setRefreshViewHolder(new BGANormalRefreshViewHolder(this,true));
        recycler_show_alleva = (RecyclerView) findViewById(R.id.recycler_alleva);
        adapter = new ShowEvaluateAdapter(context,list);
        recycler_show_alleva.setAdapter(adapter);
        recycler_show_alleva.setLayoutManager(new LinearLayoutManager(this));
        refresh_show_alleva.beginRefreshing();
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
    int page = 1;
    boolean isLoadDone=false;

    public void showAllComment(final int page){
        this.page = page;
        HttpRequest.getNewsComment(context, targetId, page, 6 , new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<CommentModel> commentModels = JSON.parseArray(json,CommentModel.class);
                if (page == 1) {
                    list.clear();
                }
                if (commentModels!=null&&commentModels.size()>0){
                    list.addAll(commentModels);
                    isLoadDone = commentModels.size() == 0;
                }else{
                    isLoadDone=true;
                }
                refresh_show_alleva.endRefreshing();
                refresh_show_alleva.endLoadingMore();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {
                refresh_show_alleva.endRefreshing();
                refresh_show_alleva.endLoadingMore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        showAllComment(1);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (!isLoadDone) {
            showAllComment(page + 1);
            return true;
        }
        return false;
    }
}