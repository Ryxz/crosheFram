package com.croshe.farming.farm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheViewPageView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.CommentInfo;
import com.croshe.farming.Entity.CostEntity;
import com.croshe.farming.Entity.FileEntity;
import com.croshe.farming.Entity.FormActionEntity;
import com.croshe.farming.Entity.FramCultivationEntity;
import com.croshe.farming.Entity.ImgInfo;
import com.croshe.farming.Entity.JournalEntity;
import com.croshe.farming.Entity.MonitorsEntity;
import com.croshe.farming.FramAdapter.CostAdapter;
import com.croshe.farming.FramAdapter.JournalImageAdapter;
import com.croshe.farming.R;
import com.croshe.farming.activity.BasketActivity;
import com.croshe.farming.activity.ShopCarActivity;
import com.croshe.farming.adapter.CommentAdapter;
import com.croshe.farming.adapter.GridAdapter;
import com.croshe.farming.adapter.ProductGridAdapter;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//我的农场详情
public class MyFramDetailasActivity extends CrosheBaseActivity {
    private LinearLayout fragment01_viewpage_2,ll_manure,ll_set_01,ll_set_02,view_back,ll_joural,ll_null_joural;
    private ImageView iv_message_2,
            img_cancel,//弹出框（暂不使用）
            img_fram_huifu;//日志回复
    private TextView tv_my_fram_name,fram_yz_area,fram_yz_num;
    private TextView tv_nickname02,tv_nickname03,tv_nickname04;
    private TextView tv_nickname,tv_nickname1,tv_nickname2,tv_nickname3,tv_nickname4;
    private TextView fram_context,fram_journal_time;
    private LinearLayout ll_video,ll_video_1,iv_left_8;
    private TextView tv_breedingdetails;
    private RecyclerView recyclerView_img,
            recycler_costing,         //成本
            recycler_fram_journal_img;//养殖日志的图片
    private RecyclerView recyclerView_dialogue;
    private TextView btu_1,btu_2,btu_3,btu_4,btu_5,text_num_5,text_num_1,text_num_2;
    private RelativeLayout relative_tv_1,relative_tv_5,relative_num_5,relative_num_1,relative_num_2;
    private String detailsId;
    private GridAdapter adapter;
    private TextView tv_pyte;
    private LinearLayout ll_planting,ll_farming;
    private LinearLayout ll_farming_duihua,ll_farming_rizhi;
//    图片
    private List<String> imglist1 = new ArrayList<>();
//    对话
    List<CommentInfo> comlist = new ArrayList<>();
    private CommentAdapter commentAdapter;
    private int productId;
    private RecyclerView recycler_product;
    private ProductGridAdapter gridAdapter;//弹出框的
    private JournalImageAdapter imageAdapter;//日志图片
    private List<FramCultivationEntity> list = new ArrayList<>();

    //成本
    private CostAdapter costAdapter;
    private List<CostEntity> costList = new ArrayList<>();//生长成本
    private List<FileEntity> fileList = new ArrayList<>();//视频
    private List<MonitorsEntity> monitorsList = new ArrayList<>();
    private List<ImgInfo> imglist = new ArrayList<>();//日志图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fram_detailas);
        Intent in = getIntent();
        detailsId=in.getStringExtra("detailsId");
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        fragment01_viewpage_2= (LinearLayout) findViewById(R.id.fragment01_viewpage_2);
        iv_left_8= (LinearLayout) findViewById(R.id.iv_left_8);
        iv_message_2= (ImageView) findViewById(R.id.iv_message_2);
        tv_nickname= (TextView) findViewById(R.id.tv_nickname);
        tv_nickname1= (TextView) findViewById(R.id.tv_nickname_1);
        tv_nickname2= (TextView) findViewById(R.id.tv_nickname_2);
        tv_nickname3= (TextView) findViewById(R.id.tv_nickname_3);
        tv_nickname4= (TextView) findViewById(R.id.tv_nickname_4);
        fram_yz_area= (TextView) findViewById(R.id.fram_yz_area);
        fram_yz_num= (TextView) findViewById(R.id.fram_yz_num);

        tv_breedingdetails= (TextView) findViewById(R.id.tv_breedingdetails);
        ll_video= (LinearLayout) findViewById(R.id.ll_video);
        ll_video_1= (LinearLayout) findViewById(R.id.ll_video_1);
        recyclerView_img= (RecyclerView) findViewById(R.id.recyclerView_img);
        relative_tv_1 = (RelativeLayout) findViewById(R.id.relative_tv_1);
        relative_tv_5 = (RelativeLayout) findViewById(R.id.relative_tv_5);
        btu_1= (TextView) findViewById(R.id.tv_btu_1);
        btu_2= (TextView) findViewById(R.id.tv_btu_2);
        btu_3= (TextView) findViewById(R.id.tv_btu_3);
        btu_4 = (TextView) findViewById(R.id.tv_btu_4);
        btu_5 = (TextView) findViewById(R.id.tv_btu_5);
        relative_num_5 = f(R.id.relative_num_5);
        relative_num_1 = f(R.id.relative_num_1);
        relative_num_2 = f(R.id.relative_num_2);
        text_num_5 = f(R.id.text_num_5);
        text_num_1 = f(R.id.text_num_1);
        text_num_2 = f(R.id.text_num_2);
        ll_planting= (LinearLayout) findViewById(R.id.ll_planting);
        ll_farming= (LinearLayout) findViewById(R.id.ll_farming);
        tv_nickname02= (TextView) findViewById(R.id.tv_nickname_02);
        tv_nickname03= (TextView) findViewById(R.id.tv_nickname_03);
        tv_nickname04= (TextView) findViewById(R.id.tv_nickname_04);
        recyclerView_dialogue= (RecyclerView) findViewById(R.id.recyclerView_dialogue);
        tv_my_fram_name= (TextView) findViewById(R.id.tv_my_fram_name);
        ll_farming_duihua= (LinearLayout) findViewById(R.id.ll_farming_duihua);
        ll_farming_rizhi= (LinearLayout) findViewById(R.id.ll_farming_rizhi);
        ll_joural = (LinearLayout) findViewById(R.id.ll_joural);
        ll_null_joural = (LinearLayout) findViewById(R.id.ll_null_joural);
        ll_set_01 = (LinearLayout) findViewById(R.id.ll_set_01);
        ll_set_02 = (LinearLayout) findViewById(R.id.ll_set_02);
        view_back = (LinearLayout) findViewById(R.id.view_back);
        //弹出框的列表
        img_cancel = (ImageView) findViewById(R.id.img_cancel);
        ll_manure = (LinearLayout) findViewById(R.id.ll_manure);
        gridAdapter = new ProductGridAdapter(context,list);
        recycler_product = (RecyclerView) findViewById(R.id.recycler_product);
        recycler_product.setAdapter(gridAdapter);
        recycler_product.setLayoutManager(new GridLayoutManager(this,3));
        // 成本
        recycler_costing = (RecyclerView) findViewById(R.id.recycler_costing);
        costAdapter = new CostAdapter(context,costList,detailsId);
        recycler_costing.setAdapter(costAdapter);
        recycler_costing.setLayoutManager(new LinearLayoutManager(this));

        //日志
        fram_context = (TextView) findViewById(R.id.text_fram_context);
        fram_journal_time = (TextView) findViewById(R.id.text_fram_journal_time);
        img_fram_huifu = (ImageView) findViewById(R.id.img_fram_huifu);
        recycler_fram_journal_img = (RecyclerView) findViewById(R.id.recycler_fram_journal_img);
        imageAdapter = new JournalImageAdapter(context,imglist);
        recycler_fram_journal_img.setAdapter(imageAdapter);
        recycler_fram_journal_img.setLayoutManager(new GridLayoutManager(this,3));

    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager1=new LinearLayoutManager(this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_img.setLayoutManager(layoutManager1);
        adapter= new GridAdapter(imglist1,this);
        recyclerView_img.setAdapter(adapter);
        recyclerView_dialogue.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter(comlist,this);
        recyclerView_dialogue.setAdapter(commentAdapter);
        showFarmDetails();
        showUserFarmNewLog();
        countFormAction();
    }

    @Override
    public void initClick() {
        iv_left_8.setOnClickListener(this);
        iv_message_2.setOnClickListener(this);
        btu_3.setOnClickListener(this);
        img_cancel.setOnClickListener(this);
        btu_5.setOnClickListener(this);
        btu_1.setOnClickListener(this);
        btu_2.setOnClickListener(this);
        ll_video.setOnClickListener(this);
        ll_video_1.setOnClickListener(this);
    }
//[{"id":0,"text":"种子"},{"id":1,"text":"化肥"},{"id":2,"text":"农药"},{"id":3,"text":"饲料"},{"id":4,"text":"疫苗"},{"id":5,"text":"商品"}]
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.iv_left_8:
               finish();
               break;
           case R.id.iv_message_2:

               break;
           case R.id.img_cancel:
               ll_manure.setVisibility(View.GONE);
               ll_set_01.setVisibility(View.VISIBLE);
               ll_set_02.setVisibility(View.GONE);
               view_back.setVisibility(View.GONE);
               break;
           case R.id.tv_btu_5:
               startActivity(new Intent(this,GrowRequiredActivity.class).putExtra("detailId",detailsId).putExtra("type",2));
               break;
           case R.id.tv_btu_1:
               startActivity(new Intent(this,GrowRequiredActivity.class).putExtra("detailId",detailsId).putExtra("type",0));
               break;
           case R.id.tv_btu_2:
               startActivity(new Intent(this,GrowRequiredActivity.class).putExtra("detailId",detailsId).putExtra("type",1));
               break;
           case R.id.tv_btu_3:
               HttpRequest.joinUserFoodCar(context, AppContext.getInstance().getUserInfo().getUserId(), productId, detailsId, new ServerResultListener() {
                   @Override
                   public void onSuccess(String json, String msg) {
                       Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                       finish();
                       startActivity(new Intent(context, ShopCarActivity.class).putExtra("type",1));
                       sendBroadcast(new Intent("refreshbasket"));
                   }

                   @Override
                   public void onFailure(String msg) {
                       Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                   }
               });
               break;
           case R.id.ll_video:
               startActivity(new Intent(this,GrowVedioListActivity.class).putExtra("fileList", (Serializable) fileList));
               break;
           case R.id.ll_video_1:
               startActivity(new Intent(this,InternetThingsActivity.class).putExtra("monitorsList", (Serializable) monitorsList));
               break;
       }
    }
    public void showFarmDetails() {
        final List<String> list = new ArrayList<>();
        final List<String> imglist = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("detailsId", detailsId);
        ServerRequest.requestHttp(this, ServerUrl.showFarmDetails, map, "获取中", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                FramCultivationEntity cultivation = JSON.parseObject(json,FramCultivationEntity.class);
                if(cultivation!=null){
                    //轮播图
                    if (cultivation.getUserFarmImages()!=null){
                        for(ImgInfo fo:cultivation.getUserFarmImages()) {
                            list.add(ServerUrl.mainUrl+fo.getFilePath());
                        }
                    }
                    CrosheViewPageView crosheViewPageView = new CrosheViewPageView(context, list, 235);
                    fragment01_viewpage_2.addView(crosheViewPageView);
                    productId = cultivation.getProductId();
                    fram_yz_area.setText(cultivation.getUseSumArea()+"平米");
                    fram_yz_num.setText(cultivation.getProductCount()+"");
                    tv_nickname.setText(cultivation.getProductName());
                    tv_nickname1.setText(cultivation.getLiveState());
                    if(cultivation.getLandType() == 0){
                        tv_my_fram_name.setText("种植详情");
                        ll_planting.setVisibility(View.VISIBLE);
                        ll_farming.setVisibility(View.GONE);
                        tv_nickname02.setText(String.valueOf(cultivation.getSprayDay())+"天");
                        tv_nickname03.setText(String.valueOf(cultivation.getDieDay())+"天");
                        tv_nickname04.setText(String.valueOf(cultivation.getProductionDay())+"天");
                        relative_tv_1.setVisibility(View.GONE);
                        relative_tv_5.setVisibility(View.VISIBLE);
                    }else if(cultivation.getLandType() == 1){
                        tv_my_fram_name.setText("养殖详情");
                        ll_planting.setVisibility(View.GONE);
                        ll_farming.setVisibility(View.VISIBLE);
                        tv_nickname2.setText(String.valueOf(cultivation.getLiveCycle()));
                        tv_nickname3.setText(String.valueOf(cultivation.getDieDay()+"天"));
                        tv_nickname4.setText(String.valueOf(cultivation.getProductionDay()+"天"));
                        relative_tv_1.setVisibility(View.VISIBLE);
                        relative_tv_5.setVisibility(View.GONE);
                    }


                    //判断是否可以加入菜篮子
                    if(cultivation.getDetailsState() == 1){
                        btu_3.setVisibility(View.VISIBLE);
                        btu_4.setVisibility(View.GONE);
                    }else{
                        btu_3.setVisibility(View.GONE);
                        btu_4.setVisibility(View.VISIBLE);
                    }
                    //成本详情
                    List<CostEntity> costs = cultivation.getCosts();
                    costList.clear();
                    if (costs!=null&&costs.size()>0){
                        costList.addAll(costs);
                        costAdapter.notifyDataSetChanged();
                    }
                    List<MonitorsEntity> monitors = cultivation.getMonitors();
                    monitorsList.clear();
                    if (monitors.size()>0&&monitors!=null){
                        monitorsList.addAll(monitors);
                    }
                    List<FileEntity> files = cultivation.getDetailsVideos();
                    fileList.clear();
                    if (files.size()>0&&files!=null){
                        fileList.addAll(files);
                    }

                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getProduct(int typeClass){
        HttpRequest.getTypeUserStore(context, AppContext.getInstance().getUserInfo().getUserId(), typeClass, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<FramCultivationEntity> cultivations = JSON.parseArray(json,FramCultivationEntity.class);
                list.clear();
                if (cultivations!= null&&cultivations.size()>0){
                    list.addAll(cultivations);
                }
                gridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void showUserFarmNewLog(){
        HttpRequest.showUserFarmNewLog(context, detailsId, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                JournalEntity log = JSON.parseObject(json,JournalEntity.class);
                if (log!=null){
                    fram_context.setText(log.getLogContent());
                    fram_journal_time.setText(log.getLogTime().substring(0,10));
                    List<ImgInfo> imgs = log.getLogImages();
                    imglist.clear();
                    if (imgs!=null&&imgs.size()>0){
                        imglist.addAll(imgs);
                    }
                    ll_joural.setVisibility(View.VISIBLE);
                    ll_null_joural.setVisibility(View.GONE);
                }else {
                    ll_joural.setVisibility(View.GONE);
                }
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        countFormAction();
    }

    public void countFormAction(){
        HttpRequest.countFormAction(context, detailsId, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                FormActionEntity formAction = JSON.parseObject(json,FormActionEntity.class);
                if (formAction.getJialiao()>0){
                    text_num_1.setText(formAction.getJialiao());
                }else {
                    relative_num_1.setVisibility(View.GONE);
                }
                if (formAction.getShifei()>0){
                    text_num_5.setText(formAction.getShifei());
                }else {
                    relative_num_5.setVisibility(View.GONE);
                }
                if (formAction.getYongyao()>0){
                    text_num_2.setText(formAction.getYongyao());
                }else {
                    relative_num_2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
