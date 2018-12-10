package com.croshe.farming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.R;
import com.croshe.farming.adapter.SeedsAdapter;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/7/24.
 */

public class GlobalSearchActivity extends CrosheBaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate{
    private LinearLayout ll_back;
    private String productName;
    private EditText edit_search;
    private BGARefreshLayout refresh_search;
    private RecyclerView recycler_search;
    private SeedsAdapter adapter;
    private List<ProductInfo> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        edit_search = (EditText) findViewById(R.id.edit_search);
        adapter = new SeedsAdapter(list,context);
        recycler_search = (RecyclerView) findViewById(R.id.recycler_search);
        recycler_search.setAdapter(adapter);
        recycler_search.setLayoutManager(new LinearLayoutManager(this));
        refresh_search = (BGARefreshLayout) findViewById(R.id.refresh_search);
        refresh_search.setDelegate(this);
        refresh_search.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
        BGAMoocStyleRefreshViewHolder bgaMoocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(this, true);
        edit_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    //隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (!StringUtils.isEmpty(edit_search.getText().toString())) {
                        productName = edit_search.getText().toString();
                        refresh_search.beginRefreshing();
                    } else {
                        Toast.makeText(context,"",Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });
        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = edit_search.getText().toString();
                if (StringUtils.isEmpty(str)) {
                    productName = "";
//                    mRefreshLayout.beginRefreshing();
                    list.clear();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Search();
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

    public void Search(){
        productName = edit_search.getText().toString();
        if (StringUtils.isEmpty(productName)){
            refresh_search.endRefreshing();
            return;
        }
        HttpRequest.search(context, productName, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<ProductInfo> products = JSON.parseArray(json,ProductInfo.class);
                list.clear();
                if (products!=null&&products.size()>0){
                    list.addAll(products);
                }else {
                    Toast.makeText(context,"暂无此商品",Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
                refresh_search.endRefreshing();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        Search();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
