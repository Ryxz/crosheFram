package com.croshe.farming.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.Toasts;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.Listener.OnScrolledListener;
import com.croshe.farming.PersonalCenter.MessageBoxActivity;
import com.croshe.farming.R;
import com.croshe.farming.Listener.RecyclerViewScrollListener;
import com.croshe.farming.activity.GlobalSearchActivity;
import com.croshe.farming.adapter.Fragment01Adapter;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by niuyongwei on 17/5/23.
 */

public class Fragment01 extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate{
    private Fragment01Adapter fragment01Adapter;
    private RecyclerView recycler_view;
    private List<ProductInfo>  infoList = new ArrayList<>();
    private List<ProductInfo>  infos = new ArrayList<>();
    private BGARefreshLayout bga;
    private TextView edit_fragment1;
    private LinearLayout ll_search,ll_message;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_01, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ll_search = (LinearLayout) getActivity().findViewById(R.id.ll_search);
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GlobalSearchActivity.class));
            }
        });
        ll_message = (LinearLayout) getActivity().findViewById(R.id.ll_message);
        ll_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MessageBoxActivity.class));
            }
        });
        fragment01Adapter = new Fragment01Adapter(getActivity(), infoList);
        recycler_view = (RecyclerView) getActivity().findViewById(R.id.recyclerview_fragment1);
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view.setAdapter(fragment01Adapter);
//        刷新
        bga = (BGARefreshLayout) getActivity().findViewById(R.id.bga);
        bga.setDelegate(this);
        bga.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
//        edit_fragment1 = (TextView) getActivity().findViewById(R.id.edit_fragment1_1);
//        edit_fragment1.setCursorVisible(true);
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        bga.beginRefreshing();
    }
    public void getPublishProduct() {
        Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        ServerRequest.requestHttp(getActivity(), ServerUrl.getPublishProduct, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                infos = JSON.parseArray(json, ProductInfo.class);
                infoList.clear();
                if (!StringUtils.isEmpty(infos.toString())) {
                    infoList.addAll(infos);
                    ProductInfo info = new ProductInfo();
                    infoList.add(0,info);
                    page = 1;
                }
                fragment01Adapter.notifyDataSetChanged();
                bga.endRefreshing();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                bga.endRefreshing();
            }
        });
    }

    int page = 1;
    public void getPublishProductLoad(){
        page++;
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        ServerRequest.requestHttp(getActivity(), ServerUrl.getPublishProduct, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                infos= JSON.parseArray(json,ProductInfo.class);
                if(infos!=null&&infos.size()>0){
                    infoList.addAll(infos);
                }else{
                    page--;
                }
                fragment01Adapter.notifyDataSetChanged();
                bga.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                page--;
                bga.endLoadingMore();
            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getPublishProduct();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (infoList != null &&infoList.size()>=5) {
            getPublishProductLoad();
        } else {
            return false;
        }
        return true;
    }
    }

