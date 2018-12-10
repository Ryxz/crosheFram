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
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.CollectionEntity;
import com.croshe.farming.R;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/7/10.
 */

public class ProductCollectionActivity extends CrosheBaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate{
    private RecyclerView recycler_collection;
    private TextView text_head,text_right;
    private LinearLayout ll_back,ll_wu_collection,ll_have_collection;
    private List<CollectionEntity> list = new ArrayList<>();
    private ProductColletionAdapter colletionAdapter;
    private BGARefreshLayout refreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product_collection);
        initView();
        initClick();
        refresh(1);
    }

    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("商品收藏");
        text_right = (TextView) findViewById(R.id.text_head3);
        text_right.setText("清空");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_wu_collection = (LinearLayout) findViewById(R.id.ll_wu_collection);
        ll_have_collection = (LinearLayout) findViewById(R.id.ll_have_collection);
        recycler_collection = (RecyclerView) findViewById(R.id.recycler_collection);
        colletionAdapter = new ProductColletionAdapter(context,list);
        recycler_collection.setAdapter(colletionAdapter);
        recycler_collection.setLayoutManager(new LinearLayoutManager(this));
        refreshLayout = (BGARefreshLayout) findViewById(R.id.mRefreshLayout_collection);
        refreshLayout.setDelegate(this);
        refreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        text_right.setOnClickListener(this);
        ll_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_head3:
                cleanCollection();
                break;
            case R.id.ll_back:
                finish();
                break;

        }
    }

    int page = 1;
    boolean isLoadDone=false;
    public void refresh(final int page) {
        int id = Integer.parseInt(AppContext.getInstance().getUserInfo().getUserId());
        this.page = page;
        HttpRequest.collection(context, id, page, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<CollectionEntity> collectionList = JSON.parseArray(json,CollectionEntity.class);
                if (page == 1) {
                    list.clear();
                }
                if (collectionList!=null&&collectionList.size()>0){
                    list.addAll(collectionList);
                    isLoadDone = collectionList.size() == 0;
                }else{
                    if (page == 1){
                        ll_have_collection.setVisibility(View.GONE);
                        ll_wu_collection.setVisibility(View.VISIBLE);
                    }
                    isLoadDone=true;
                }
//                if (!(collectionList.size()>0)){
//                    ll_wu_collection.setVisibility(View.VISIBLE);
//                }
                colletionAdapter.notifyDataSetChanged();
                refreshLayout.endRefreshing();
                refreshLayout.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                refreshLayout.endRefreshing();
                refreshLayout.endLoadingMore();
            }
        });
    }
    public void cleanCollection(){
        HttpRequest.delAllUserProductCollection(context, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                refresh(1);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        refresh(1);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (!isLoadDone) {
            refresh(page + 1);
            return true;
        }
        return false;
    }
}
