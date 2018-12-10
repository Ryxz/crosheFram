package com.croshe.farming.farm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheTabView;
import com.croshe.farming.Entity.ImgInfo;
import com.croshe.farming.Entity.PackageInfo;
import com.croshe.farming.R;
import com.croshe.farming.adapter.PackageAdapter;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

//套餐推荐
public class PackageRecommendedActivity extends CrosheBaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate{
    private int landState=0;
    private LinearLayout ll_r,ll_go_back;
    private RecyclerView recyclerView_package;
    private BGARefreshLayout bgaRefreshLayout_package;
    private List<PackageInfo> packageInfoList=new ArrayList<>();
    private PackageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_recommended);
        initView();
        initData();
        initClick();
        String[] titles = new String[]{"种植", "养殖"};
        int[] unSelect = new int[]{0, 0};
        int[] Select = new int[]{0, 0};
        CrosheTabView crosheTabView = new CrosheTabView(this, titles, unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position==0) {
                    landState=0;
                } else if (position==1) {
                    landState = 1;
                }
                showPackageList();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        ll_r.addView(crosheTabView);
    }

    @Override
    public void initView() {
        ll_r= (LinearLayout) findViewById(R.id.ll_fragment04_r);
        ll_go_back= (LinearLayout) findViewById(R.id.ll_go_back);
        recyclerView_package= (RecyclerView) findViewById(R.id.recyclerView_package);
        bgaRefreshLayout_package= (BGARefreshLayout) findViewById(R.id.bgalayout_package);
        bgaRefreshLayout_package.setDelegate(this);
        bgaRefreshLayout_package.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new PackageAdapter(packageInfoList,this,landState);
        recyclerView_package.setLayoutManager(manager);
        recyclerView_package.setAdapter(adapter);
    }

    @Override
    public void initData() {
        showPackageList();
    }

    @Override
    public void initClick() {
        ll_go_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_go_back:
                finish();
                break;
        }
    }
    public void showPackageList(){
        Map<String,Object> map = new HashMap<>();
        map.put("page",1);
        map.put("packageType",landState);
        ServerRequest.requestHttp(this, ServerUrl.showPackageList, map, "获取中", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<PackageInfo> packageInfos= JSON.parseArray(json,PackageInfo.class);
                packageInfoList.clear();
                if(!StringUtils.isEmpty(packageInfos.toString())){
                    page=1;
                    packageInfoList.addAll(packageInfos);
                }
                adapter.notifyDataSetChanged();
                bgaRefreshLayout_package.endRefreshing();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
   int page=1;
    public void showPackageListLoad(){
        page++;
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("packageType",landState);
        ServerRequest.requestHttp(this, ServerUrl.showPackageList, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<PackageInfo> packageInfos = JSON.parseArray(json,PackageInfo.class);
                packageInfoList.clear();
                if(!StringUtils.isEmpty(packageInfos.toString())){
                    packageInfoList.addAll(packageInfos);
                    adapter.notifyDataSetChanged();
                }else{
                    page--;
                }
                bgaRefreshLayout_package.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                page--;
                bgaRefreshLayout_package.endLoadingMore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        showPackageList();

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if(null!=packageInfoList&&packageInfoList.size()>=10){
                 showPackageListLoad();
        }else{
            return false;
        }
        return true;
    }
}
