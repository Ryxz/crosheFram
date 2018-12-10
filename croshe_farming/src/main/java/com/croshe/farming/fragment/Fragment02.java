package com.croshe.farming.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheManyTabView;
import com.croshe.crosheandroidbase.view.CrosheTabView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.Entity.ReceiveAddInfo;
import com.croshe.farming.Entity.TypeInfo;
import com.croshe.farming.PersonalCenter.MessageBoxActivity;
import com.croshe.farming.R;
import com.croshe.farming.View.MyGridView;
import com.croshe.farming.activity.GlobalSearchActivity;
import com.croshe.farming.adapter.GridViewProductsAdapter;
import com.croshe.farming.adapter.SeedsAdapter;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.server.ToastTime;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by niuyongwei on 17/5/23.
 */
//集市
public class Fragment02 extends Fragment implements View.OnClickListener,BGARefreshLayout.BGARefreshLayoutDelegate {
    private LinearLayout ll_r, ll_fragment02_r_1, ll_all_product, ll_item_product, ll_hideview, ll_all,ll_f_02;
    private List<TypeInfo> list = new ArrayList<>();
    private List<TypeInfo> list1 = new ArrayList<>();
    private LinearLayout ll_message;
    private RecyclerView recyclerView;
    private BGARefreshLayout refresh_product;
    private List<ProductInfo> seedlist = new ArrayList<>();
    private List<ProductInfo> productInfos = new ArrayList<>();
    private SeedsAdapter adapter;
    private ImageView img_01,img_02;
    private GridViewProductsAdapter gridAdapter;
    private MyGridView grid_product;
    Animation animation;
    Animation animation1;
    private TextView tv_reset, tv_confrim;
    List<TypeInfo> types = new ArrayList<>();
    public Context context;
    private int typeId;//父类型id
    private EditText edit_fragment1;
    private LinearLayout ll_search_02;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_02, container, false);
        context = getActivity();
        return view;
    }

    public void initView() {
        img_01 = (ImageView) getActivity().findViewById(R.id.img_01);
        img_02 = (ImageView) getActivity().findViewById(R.id.img_02);
        ll_f_02 = (LinearLayout) getActivity().findViewById(R.id.ll_f_02);
        ll_search_02 =(LinearLayout) getActivity().findViewById(R.id.ll_search_02);
        ll_search_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GlobalSearchActivity.class));
            }
        });
        ll_message = (LinearLayout) getActivity().findViewById(R.id.ll_message_f2);
        ll_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MessageBoxActivity.class));
            }
        });
//        edit_fragment1 = (EditText) getActivity().findViewById(R.id.edit_fragment1);
//        edit_fragment1.setCursorVisible(false);
//        edit_fragment1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                edit_fragment1.setCursorVisible(true);
//            }
//        });
        refresh_product = (BGARefreshLayout) getActivity().findViewById(R.id.refresh_product);
        refresh_product.setDelegate(this);
        refresh_product.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));

        ll_r = (LinearLayout) getActivity().findViewById(R.id.ll_fragment02_r);
        ll_fragment02_r_1 = (LinearLayout) getActivity().findViewById(R.id.ll_fragment02_r_1);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.tv_re_lei_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SeedsAdapter(seedlist, getActivity());
        recyclerView.setAdapter(adapter);
        ll_all_product = (LinearLayout) getActivity().findViewById(R.id.ll_all_product);
        ll_all_product.setOnClickListener(this);
        ll_item_product = (LinearLayout) getActivity().findViewById(R.id.ll_item_product);
        ll_hideview = (LinearLayout) getActivity().findViewById(R.id.ll_hideview);
        grid_product = (MyGridView) getActivity().findViewById(R.id.grid_product);
        ll_all = (LinearLayout) getActivity().findViewById(R.id.ll_all);

        animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.animation1);
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation);

        tv_reset = (TextView) getActivity().findViewById(R.id.tv_reset);
        tv_confrim = (TextView) getActivity().findViewById(R.id.tv_confrim);
        tv_reset.setOnClickListener(this);
        tv_confrim.setOnClickListener(this);
        ll_hideview.setOnClickListener(this);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ll_item_product.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void initData() {

    }

    int tag = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_all_product:
                if (tag == 0) {
                    img_01.setVisibility(View.GONE);
                    img_02.setVisibility(View.VISIBLE);
                    ll_all.setBackgroundColor(Color.WHITE);
                    ll_all_product.setBackgroundResource(R.drawable.back_white1);
                    ll_item_product.setVisibility(View.VISIBLE);
                    ll_hideview.setVisibility(View.VISIBLE);
                    ll_item_product.startAnimation(animation);
                    tag = 1;
                } else {
                    img_01.setVisibility(View.VISIBLE);
                    img_02.setVisibility(View.GONE);
                    ll_all.setBackgroundResource(R.color.colorhuise);
                    ll_all_product.setBackgroundResource(R.drawable.back_seeds);
                    ll_item_product.setVisibility(View.GONE);
                    ll_hideview.setVisibility(View.GONE);
                    ll_item_product.startAnimation(animation1);
                    tag = 0;
                }
                break;
            case R.id.tv_confrim:
                types.clear();
                Map<String, TypeInfo> typeMaps=gridAdapter.getTypeInfoMap();
                if(null!=typeMaps&&typeMaps.size()>0){
                    for (TypeInfo typeInfo:typeMaps.values()){
                        types.add(typeInfo);
                    }
                    if (types!=null&&types.size()>0){
                        getProductTypeData(types);
//                        gridAdapter.cleanMap();
                    }
                }
                ll_all.setBackgroundResource(R.color.colorhuise);
                ll_all_product.setBackgroundResource(R.drawable.back_seeds);
                ll_item_product.setVisibility(View.GONE);
                ll_hideview.setVisibility(View.GONE);
                img_01.setVisibility(View.VISIBLE);
                img_02.setVisibility(View.GONE);
                ll_all.setBackgroundResource(R.color.colorhuise);
                tag = 0;
                break;
            case R.id.tv_reset:
                gridAdapter.cleanMap();
//                ll_all.setBackgroundResource(R.color.colorhuise);
//                ll_all_product.setBackgroundResource(R.drawable.back_seeds);
//                ll_item_product.setVisibility(View.GONE);
//                ll_hideview.setVisibility(View.GONE);
//                img_01.setVisibility(View.VISIBLE);
//                img_02.setVisibility(View.GONE);
//                ll_all.setBackgroundResource(R.color.colorhuise);
                tag = 0;
                break;
            case R.id.ll_hideview:
                break;

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
        getProductType();

    }

    //父类
    public void getProductType() {
        Map<String, Object> map = new HashMap<>();

        ServerRequest.requestHttp(getContext(), ServerUrl.getProductType, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                list.clear();
                list = JSON.parseArray(json, TypeInfo.class);
                if (null != list && list.size() > 0) {
                    typeId=list.get(0).getTypeId();
                    final String[] titles = new String[list.size()];
                    int[] unSelect = new int[list.size()];
                    int[] Select = new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {


                        titles[i] = list.get(i).getTypeName();
                        unSelect[i] = 0;
                        Select[i] = 0;
                    }
                    CrosheManyTabView crosheManyTabView = new CrosheManyTabView((AppCompatActivity) getActivity(),context, titles, unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {
                        @Override
                        public void onTabSelect(int position) {
                            //父类id
                            typeId = list.get(position).getTypeId();
                            //查询父类下的分类
                            getProductTypecrid(typeId);
//                            getProductType(typeId);
                            ll_fragment02_r_1.removeAllViews();
                            ll_item_product.setVisibility(View.GONE);
                            ll_hideview.setVisibility(View.GONE);
                            img_01.setVisibility(View.VISIBLE);
                            img_02.setVisibility(View.GONE);
                            ll_all.setBackgroundResource(R.color.colorhuise);
                            ll_all_product.setBackgroundResource(R.drawable.back_seeds);
                            tag = 0;
                        }

                        @Override
                        public void onTabReselect(int position) {

                        }
                    });
                    ll_r.removeAllViews();
                    ll_r.addView(crosheManyTabView);


                    int typeId = list.get(0).getTypeId();
                    getProductTypecrid(typeId);
                }

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    List<TypeInfo> pnames = new ArrayList<>();
    String[] titles = null;
    int[] unSelect;
    int[] Select;
    List<TypeInfo> typeInfoList = new ArrayList<>();
    List<TypeInfo> typeInfos=new ArrayList<>();

    //子类
    public void getProductTypecrid(final int parentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", parentId);
        ServerRequest.requestHttp(getContext(), ServerUrl.getProductType, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                list1.clear();   // 子类分类
                pnames.clear();  //下拉框的list
                typeInfos.clear();  //首个数据
                typeInfoList.clear();
                list1 = JSON.parseArray(json, TypeInfo.class);
                if (null!=list1&&list1.size()>0) {
                    typeInfos.add(list1.get(0));
                    if (list1.size() < 4) {
                        titles = new String[list1.size()];
                        unSelect = new int[list1.size()];
                        Select = new int[list1.size()];
                        for (int i = 0; i < list1.size(); i++) {
                            titles[i] = list1.get(i).getTypeName();
                            unSelect[i] = 0;
                            Select[i] = 0;
                        }
                    }else {
                        titles = new String[4];
                        unSelect = new int[4];
                        Select = new int[4];
                        for (int i = 0; i < 4; i++) {
                            titles[i] = list1.get(i).getTypeName();
                            unSelect[i] = 0;
                            Select[i] = 0;
                        }
                    }
                    pnames.addAll(list1);
                    gridAdapter = new GridViewProductsAdapter(getActivity(), pnames, 4);
                    grid_product.setNumColumns(4);
                    grid_product.setAdapter(gridAdapter);

                    //选项卡固定个数
                    CrosheTabView crosheTabView = new CrosheTabView(getActivity(), titles, unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {
                        @Override
                        public void onTabSelect(int position) {
                            typeInfoList.clear();
                            List<TypeInfo> typeInfos = new ArrayList<>();
                            typeInfos.add(list1.get(position));
                            typeInfoList.add(list1.get(position));
                            getProductTypeData(typeInfos);
                        }

                        @Override
                        public void onTabReselect(int position) {

                        }
                    });
                    ll_fragment02_r_1.removeAllViews();
                    ll_fragment02_r_1.addView(crosheTabView);
//                    if (typeInfoList != null&&typeInfoList.size()>0){
//                        getProductTypeData(typeInfoList);
//                    }else {
//
//                    }
                    getProductTypeData(typeInfos);
                }else {
                    ll_f_02.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
                refresh_product.endRefreshing();
                refresh_product.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                adapter.notifyDataSetChanged();
                refresh_product.endRefreshing();
                refresh_product.endLoadingMore();
            }
        });
    }
//    typeId[i] 加载产品
    private int page = 1;
    public void getProductTypeData(List<TypeInfo> list) {
        Map<String, Object> map = new HashMap<>();
        map.put("typeId",typeId);
        for (int i = 0;i<list.size();i++){
            map.put("typeIds["+i+"]", list.get(i).getTypeId());
        }
        map.put("page",1);
        ServerRequest.requestHttp(getActivity(), ServerUrl.showProduct, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                productInfos = JSON.parseArray(json, ProductInfo.class);
                seedlist.clear();
                if (!StringUtils.isEmpty(productInfos.toString())) {
                    seedlist.addAll(productInfos);
                    page = 1;
                }
                adapter.notifyDataSetChanged();
                refresh_product.endRefreshing();
                refresh_product.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
                seedlist.clear();
                adapter.notifyDataSetChanged();
                refresh_product.endRefreshing();
                refresh_product.endLoadingMore();
                Toast toast=Toast.makeText(getActivity(),"暂无商品",Toast.LENGTH_LONG);
                ToastTime.showMyToast(toast,1000);
            }
        });
    }

    //page ++ 分页 停止加载
    public void getProductTypeLoad(List<TypeInfo> list) {
        page++;
        Map<String, Object> map = new HashMap<>();
        map.put("typeId",typeId);
        for (int i = 0;i<list.size();i++){
            map.put("typeIds["+i+"]", list.get(i).getTypeId());
        }
        map.put("page",page);
        ServerRequest.requestHttp(getActivity(), ServerUrl.showProduct, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                productInfos = JSON.parseArray(json, ProductInfo.class);
//                seedlist.clear();
                if (!StringUtils.isEmpty(productInfos.toString())) {
                    seedlist.addAll(productInfos);
                }else {
                    page--;
                }
                adapter.notifyDataSetChanged();
                refresh_product.endLoadingMore();
            }

            @Override
            public void onFailure(String msg) {
//                seedlist.clear();
//                adapter.notifyDataSetChanged();
                page--;
                refresh_product.endLoadingMore();
                Toast toast=Toast.makeText(getActivity(),"暂无更多",Toast.LENGTH_LONG);
                ToastTime.showMyToast(toast,1000);
            }
        });
    }

    public void getProductType(int TypeId) {
        Map<String, Object> map = new HashMap<>();
        map.put("typeId", TypeId);
        ServerRequest.requestHttp(getActivity(), ServerUrl.showProduct, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                productInfos = JSON.parseArray(json, ProductInfo.class);
                seedlist.clear();
                if (!StringUtils.isEmpty(productInfos.toString())) {
                    seedlist.addAll(productInfos);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (typeInfoList != null&&typeInfoList.size()>0){
            getProductTypeData(typeInfoList);
        }else {
            getProductTypecrid(typeId);
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (null != list && list.size() >= 5) {
            getProductTypeLoad(typeInfoList);
//            if (typeInfoList != null&&typeInfoList.size()>0){
//            }else {
//                getProductTypecrid(typeId);
//            }
        } else {
            return false;
        }
        return true;
    }


}



