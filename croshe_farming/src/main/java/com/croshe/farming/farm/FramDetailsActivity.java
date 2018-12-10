package com.croshe.farming.farm;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheTabView;
import com.croshe.crosheandroidbase.view.CrosheViewPageView;
import com.croshe.farming.Entity.FramLandInfo;
import com.croshe.farming.Entity.FramViewInfo;
import com.croshe.farming.Entity.ImgInfo;
import com.croshe.farming.R;
import com.croshe.farming.adapter.FarmlandAdapter;
import com.croshe.farming.adapter.FramViewAdapter;
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

//进入农场详情
public class FramDetailsActivity extends CrosheBaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private LinearLayout ll_r;

    private LinearLayout fragment01_viewpage_1,ll_make_call,iv_left_7;
    private List<String> imglist = new ArrayList<>();
    private TextView tv_address_farm, tv_phone_farm, tv_farm_introduce, tv_fram_1, tv_fram_2, tv_fram_3, tv_fram_nickname_1;
    private ImageView iv_farm_1, iv_farm_2, iv_farm_3;
    private String farmId;
    private int landState = 0;
    private FarmlandAdapter adapter;
    private List<FramLandInfo> landInfos = new ArrayList<>();
    private List<FramLandInfo> landInfoList = new ArrayList<>();
    private BGARefreshLayout bgalayout_land;
    private RecyclerView recyclerView_land;
    private ImageView iv_mssage_1;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fram_details);
        initView();
        initData();
        initClick();
        String[] titles = new String[]{"已种植", "未种植", "已养殖", "未养殖"};
        int[] unSelect = new int[]{0, 0, 0, 0};
        int[] Select = new int[]{0, 0, 0, 0};
        CrosheTabView crosheTabView = new CrosheTabView(this, titles, unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {


            @Override
            public void onTabSelect(int position) {
                if (position==0) {
                    landState=0;
                } else if (position==1) {
                    landState=1;
                } else if (position==2) {
                    landState=2;
                } else if(position==3){
                    landState=3;
                }
                showLand();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        ll_r.addView(crosheTabView);
    }

    @Override
    public void initView() {
        ll_r = (LinearLayout) findViewById(R.id.ll_fragment03_r);
        fragment01_viewpage_1 = (LinearLayout) findViewById(R.id.fragment01_viewpage_1);
        tv_fram_nickname_1 = (TextView) findViewById(R.id.tv_fram_nickname_1);
        tv_address_farm = (TextView) findViewById(R.id.tv_address_farm);
        tv_phone_farm = (TextView) findViewById(R.id.tv_phone_farm);
        tv_farm_introduce = (TextView) findViewById(R.id.tv_farm_introduce);
        tv_fram_1 = (TextView) findViewById(R.id.tv_fram_1);
        tv_fram_2 = (TextView) findViewById(R.id.tv_fram_2);
        tv_fram_3 = (TextView) findViewById(R.id.tv_fram_3);
        tv_address_farm = (TextView) findViewById(R.id.tv_address_farm);
        iv_farm_1 = (ImageView) findViewById(R.id.iv_farm_1);
        iv_farm_2 = (ImageView) findViewById(R.id.iv_farm_2);
        iv_farm_3 = (ImageView) findViewById(R.id.iv_farm_3);
        iv_left_7= (LinearLayout) findViewById(R.id.iv_left_7);
        iv_mssage_1= (ImageView) findViewById(R.id.iv_message_1);
        recyclerView_land = (RecyclerView) findViewById(R.id.recyclerView_land);
        bgalayout_land = (BGARefreshLayout) findViewById(R.id.bgalayout_land);
        bgalayout_land.setDelegate(this);
        bgalayout_land.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
        ll_make_call = (LinearLayout) findViewById(R.id.ll_make_call);


    }

    @Override
    public void initData() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView_land .setLayoutManager(layoutManager);
        adapter= new FarmlandAdapter(landInfoList,this);
        recyclerView_land.setAdapter(adapter);

        Intent in = getIntent();
        farmId = in.getStringExtra("farmId");
        showFarmdetails();
        showLand();
    }

    @Override
    public void initClick() {
        iv_mssage_1.setOnClickListener(this);
        iv_left_7.setOnClickListener(this);
        ll_make_call.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.iv_left_7:
              finish();
              break;
          case R.id.ll_make_call:
              AlertDialog.Builder builder = new AlertDialog.Builder(context);
              builder.setTitle("温馨提示");
              builder.setMessage("是否拨打电话:"+phone);

              builder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phone));
                      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                      context.startActivity(intent);
                  }
              });
              builder.setPositiveButton("取消",null);
              AlertDialog alertdialog1 = builder.create();
              alertdialog1.show();
              break;
      }
    }

    //    农场详情
    public void showFarmdetails() {
        Map<String, Object> map = new HashMap<>();
        map.put("farmId", farmId);
        ServerRequest.requestHttp(this, ServerUrl.showFarmdetails, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                FramViewInfo viewInfo = JSON.parseObject(json, FramViewInfo.class);
                if (!StringUtils.isEmpty(viewInfo.toString())) {
                    for (ImgInfo info : viewInfo.getFarmImages()) {
                        imglist.add(ServerUrl.mainUrl + info.getFilePath());
                    }
                    //轮播图
                    CrosheViewPageView crosheViewPageView = new CrosheViewPageView(context, imglist, 185);
                    fragment01_viewpage_1.addView(crosheViewPageView);
                    tv_fram_nickname_1.setText(viewInfo.getFarmName());
                    tv_address_farm.setText(viewInfo.getFarmAddress());
                    phone = viewInfo.getFarmPhone();
                    tv_phone_farm.setText(viewInfo.getFarmPhone());
                    tv_farm_introduce.setText(viewInfo.getFarmDetails());
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    //    农场土地详情
    public void showLand() {
        Map<String, Object> map = new HashMap<>();
        map.put("farmId", farmId);
        map.put("landState", landState);
        ServerRequest.requestHttp(this, ServerUrl.showLand, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                landInfos = JSON.parseArray(json, FramLandInfo.class);
                landInfoList.clear();
                if (!StringUtils.isEmpty(landInfos.toString())) {
                    page = 1;
                    landInfoList.addAll(landInfos);
                }
                adapter.notifyDataSetChanged();
                bgalayout_land.endRefreshing();
                bgalayout_land.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    //    农场土地详情
    int page = 1;

    public void showLandload() {
        page++;
        Map<String, Object> map = new HashMap<>();
        map.put("farmId", farmId);
        map.put("landState", landState);
        map.put("page", page);
        ServerRequest.requestHttp(this, ServerUrl.showLand, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                landInfos = JSON.parseArray(json, FramLandInfo.class);
                if (!StringUtils.isEmpty(landInfos.toString())) {
                    landInfoList.addAll(landInfos);
                    adapter.notifyDataSetChanged();

                } else {
                    page--;
                }
                bgalayout_land.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                page--;
                bgalayout_land.endLoadingMore();
            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        showLand();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (null != landInfoList && landInfoList.size() >= 10) {
            showLandload();
        } else {
            return false;
        }
        return true;
    }
}
