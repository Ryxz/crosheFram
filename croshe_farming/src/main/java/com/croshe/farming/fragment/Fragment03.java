package com.croshe.farming.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheTabView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.FramCultivationEntity;
import com.croshe.farming.Entity.MyFramEntity;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.Entity.UserInfo;
import com.croshe.farming.R;
import com.croshe.farming.View.MyGridView;
import com.croshe.farming.adapter.MyFarmAdapter;
import com.croshe.farming.adapter.ProductGridAdapter;
import com.croshe.farming.adapter.ProductGridAdapter2;
import com.croshe.farming.farm.PlantNumAcyivity;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.util.NumDialogUtil;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by niuyongwei on 17/5/23.
 */
//我的农场
public class Fragment03 extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate,View.OnClickListener{
    private LinearLayout ll_r;
    int index=0;
    private BGARefreshLayout bgaRefreshLayout;
    private RecyclerView recyclerView_my,recycler_product;
    private List<UserInfo> userInfos = new ArrayList<>();
    private List<UserInfo> userInfoList = new ArrayList<>();
    private MyFarmAdapter adapter;
    private LinearLayout ll_my_zui;
    private LinearLayout ll_my_yang,ll_fram_product,ll_fragemnt_back,ll_frament_03_null;
    private ImageView img_cancel;
    private ProductGridAdapter gridAdapter;
    private MyGridView grid_product_to;
    private List<FramCultivationEntity> list = new ArrayList<>();
    private TextView tv_qued;
    private String userFarmId,areas,enter_num;
    private int storeId;
    private  NumDialogUtil dialogUtil;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            enter_num = (String) msg.obj;
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_03, container, false);
        return view;
    }
    public void initView(){
        tv_qued = (TextView) getActivity().findViewById(R.id.tv_qued);
        tv_qued.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeId = gridAdapter.getStoreId();
                int storeCount = gridAdapter.getStoreCount();
                if (storeId == 0){
                    Toast.makeText(getActivity(),"请选择种植或养殖的产品",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    dialogUtil = new NumDialogUtil(getActivity(), storeCount, areas, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            enter_num = dialogUtil.getNums();
                            farmingLand();
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogUtil.dismiss();
                        }
                    });
                    dialogUtil.show();
                }
            }
        });
        ll_frament_03_null = (LinearLayout) getActivity().findViewById(R.id.ll_frament_03_null);
        ll_my_zui= (LinearLayout) getActivity().findViewById(R.id.ll_my_zui);
        ll_my_yang= (LinearLayout) getActivity().findViewById(R.id.ll_my_yang);
        img_cancel = (ImageView) getActivity().findViewById(R.id.img_cancel);
        ll_fram_product= (LinearLayout) getActivity().findViewById(R.id.ll_fram_product);
        ll_r = (LinearLayout) getActivity().findViewById(R.id.ll_fragment03_r);
        recyclerView_my= (RecyclerView) getActivity().findViewById(R.id.recyclerView_my);
        bgaRefreshLayout= (BGARefreshLayout) getActivity().findViewById(R.id.bgalayout_my);
        bgaRefreshLayout.setDelegate(this);
        bgaRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        adapter = new MyFarmAdapter(userInfoList,getContext());
        recyclerView_my.setLayoutManager(manager);
        recyclerView_my.setAdapter(adapter);

        ll_fragemnt_back = (LinearLayout) getActivity().findViewById(R.id.ll_fragemnt_back);
        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_fram_product.setVisibility(View.GONE);
                ll_fragemnt_back.setVisibility(View.GONE);
            }
        });
        ll_fragemnt_back.setOnClickListener(this);
        gridAdapter = new ProductGridAdapter(getContext(),list);
        recycler_product = (RecyclerView) getActivity().findViewById(R.id.recycler_product);
        recycler_product.setAdapter(gridAdapter);
        recycler_product.setLayoutManager(new GridLayoutManager(getActivity(),3));
    }

    public void initData(){
        showUserFarm();
        registerBoradcastReceiver();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        //选项卡固定个数
        String[] titles = new String[]{"已种植", "可种植","已养殖","可养殖"};
        int[] unSelect = new int[]{0, 0,0,0};
        int[] Select = new int[]{0, 0,0,0};
        CrosheTabView crosheTabView = new CrosheTabView(getActivity(), titles, unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {


            @Override
            public void onTabSelect(int position) {
                   if(position==0){
                       index=0;
                   }else if(position==1){
                       index=1;
                   }else if(position==2){
                       index=2;
                }else{
                       index=3;
                   }
                showUserFarm();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        ll_r.addView(crosheTabView);
    }
    public void registerBoradcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("showProduct");
        intentFilter.addAction("refreshbasket");
        getActivity().registerReceiver(myBroadcastReceive, intentFilter);
    }
    public BroadcastReceiver myBroadcastReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("showProduct")){
                ll_fram_product.setVisibility(View.VISIBLE);
                ll_fragemnt_back.setVisibility(View.VISIBLE);
                String dex = intent.getStringExtra("style");
                if (dex.equals("1")){
                    style = 0;
                }else {
                    style = 1;
                }
                gridAdapter.setStoreId(0);
                areas = intent.getStringExtra("areas");
                userFarmId = intent.getStringExtra("userFarmId");
                myFramLandDetails(style);
            }else if (action.equals("refreshbasket")){
                showUserFarm();
            }
        }
    };
    int style;
    public void showUserFarm(){
        Map<String,Object> map =new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("type",index);
        map.put("page",1);
        ServerRequest.requestHttp(getActivity(), ServerUrl.showUserFarm, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                userInfos= JSON.parseArray(json,UserInfo.class);
                userInfoList.clear();
                if(null!=userInfos&&userInfos.size()>0){
                    userInfoList.addAll(userInfos);
                    page=1;
                }else {
                }
                adapter.notifyDataSetChanged();
                bgaRefreshLayout.endRefreshing();
            }


            @Override
            public void onFailure(String msg) {
                bgaRefreshLayout.endRefreshing();
            }
        });
    }

    public void myFramLandDetails(int type){
        HttpRequest.getList(getActivity(), AppContext.getInstance().getUserInfo().getUserId(), page, type, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<FramCultivationEntity> cultivations = JSON.parseArray(json,FramCultivationEntity.class);
                list.clear();
                if (cultivations!= null&&cultivations.size()>0){
                    list.addAll(cultivations);
                }
                bgaRefreshLayout.endLoadingMore();
                gridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();

            }
        });
    }
    int page=1;
    public void showUserFarmLoad(){
        page++;
        Map<String,Object> map =new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("type",index);
        map.put("page",page);
        ServerRequest.requestHttp(getActivity(), ServerUrl.showUserFarm, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                userInfos= JSON.parseArray(json,UserInfo.class);
                if(!StringUtils.isEmpty(userInfos.toString())){
                    userInfoList.addAll(userInfos);
                }else{
                    page--;
                }
                adapter.notifyDataSetChanged();
                bgaRefreshLayout.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                page--;
                bgaRefreshLayout.endLoadingMore();
            }
        });
    }
    public void farmingLand(){
        Map<String,Object> map=new HashMap<>();
        if (StringUtils.isEmpty(enter_num)){
            Toast.makeText(getActivity(),"请输入数量",Toast.LENGTH_SHORT).show();
            return;
        }else {
            int num = Integer.parseInt(enter_num);
            if (num == 0){
                Toast.makeText(getActivity(),"数量不可为0",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        map.put("storeId",storeId);//仓库ID
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("userFarmId",userFarmId);//用户农场ID
        map.put("count",enter_num);
        map.put("landType",style);//土地类型

        ServerRequest.requestHttp(getActivity(), ServerUrl.farmingLand, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                ll_fram_product.setVisibility(View.GONE);
                ll_fragemnt_back.setVisibility(View.GONE);
                if (dialogUtil.isShowing()){
                    dialogUtil.dismiss();
                }
                gridAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
                showUserFarm();
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                gridAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        showUserFarm();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if(null!=userInfoList||userInfoList.size()>=10){
         showUserFarmLoad();
        }else{
            return false;
        }
        return true;
    }


    @Override
    public void onClick(View v) {

    }
}
