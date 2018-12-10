package com.croshe.farming.farm;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheViewPageView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.EvaluationInfo;
import com.croshe.farming.Entity.FramDetailsInfo;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.Entity.PackageDetailsEntity;
import com.croshe.farming.Entity.PackageInfo;
import com.croshe.farming.R;
import com.croshe.farming.activity.CashierActivity;
import com.croshe.farming.activity.ProductDetailsActivity;
import com.croshe.farming.adapter.Fragment01Adapter;
import com.croshe.farming.adapter.PackageDetailasAdapter;
import com.croshe.farming.adapter.PackageDetailasTwoAdapter;
import com.croshe.farming.product.AllEvaluateAcitivity;
import com.croshe.farming.product.ShowAllEvaluateActivity;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//套餐详情
public class PackageDetailasActivity extends CrosheBaseActivity {
    private TextView tv_name_1,tv_name_2,tv_name_3,tv_name_4,tv_name_5,tv_name_6;
    private RecyclerView recyclerView_package,recyclerView_view;
    private TextView tv_features;
    private TextView tv_buy;
    private LinearLayout ll_view;
    private TextView tv_view_number;
    private LinearLayout tv_title_1,tv_title_2;
    private TextView tv_title_3,tv_title_4;
    private String packageId;
    private ImageView ll_viewpage_1,img_sc,img_unsc;
    private List<EvaluationInfo> evaluationInfoList = new ArrayList<>();
    private List<PackageDetailsEntity> framDetailsInfoList = new ArrayList<>();
    private PackageDetailasAdapter adapter;
    private PackageDetailasTwoAdapter adapter1;
    private LinearLayout ll_yonghui;
    private ImageView iv_left;
    private String packagePirce;
    private int tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detailas);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        img_sc = (ImageView) findViewById(R.id.img_sc);
        img_unsc = (ImageView) findViewById(R.id.img_unsc);
        iv_left= (ImageView) findViewById(R.id.iv_fanhui_1);
        ll_yonghui= (LinearLayout) findViewById(R.id.ll_yonghui);
        ll_viewpage_1= (ImageView) findViewById(R.id.ll_viewpage_1);
        tv_name_1= (TextView) findViewById(R.id.tv_name_1);
        tv_name_2= (TextView) findViewById(R.id.tv_name_2);
        tv_name_3= (TextView) findViewById(R.id.tv_name_3);
        tv_name_4= (TextView) findViewById(R.id.tv_name_4);
        tv_name_5= (TextView) findViewById(R.id.tv_name_5);
        tv_name_6= (TextView) findViewById(R.id.tv_name_6);
        recyclerView_package= (RecyclerView) findViewById(R.id.recyclerView_package_1);
        tv_features= (TextView) findViewById(R.id.tv_features_1);
        tv_buy= (TextView) findViewById(R.id.tv_buy);
        recyclerView_view= (RecyclerView) findViewById(R.id.recyclerView_view);
        ll_view= (LinearLayout) findViewById(R.id.ll_view);
        tv_view_number= (TextView) findViewById(R.id.tv_view_number);
        tv_title_1= (LinearLayout) findViewById(R.id.tv_title_1);
        tv_title_2= (LinearLayout) findViewById(R.id.tv_title_2);
        tv_title_3= (TextView) findViewById(R.id.tv_title_3);
        tv_title_4= (TextView) findViewById(R.id.tv_title_4);
        adapter= new PackageDetailasAdapter(framDetailsInfoList,this);

    }

    @Override
    public void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView_package.setLayoutManager(manager);
        recyclerView_package.setAdapter(adapter);
        Intent in =getIntent();
        packageId=in.getStringExtra("packageId");
        showPackageDetials();
        adapter1 = new PackageDetailasTwoAdapter(evaluationInfoList,this);
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        recyclerView_view.setLayoutManager(manager1);
        recyclerView_view.setAdapter(adapter1);
    }

    @Override
    public void initClick() {
        iv_left.setOnClickListener(this);
        tv_title_1.setOnClickListener(this);
        tv_title_2.setOnClickListener(this);
        tv_title_3.setOnClickListener(this);
        tv_title_4.setOnClickListener(this);
        ll_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.iv_fanhui_1:
                 finish();
                 break;
             case R.id.tv_title_1:
                 AlertDialog.Builder builder = new AlertDialog.Builder(context);
                 builder.setTitle("温馨提示");
                 builder.setMessage("是否拨打电话10086");

                 builder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         Intent intent =new Intent();
                         intent.setAction(Intent.ACTION_DIAL);
                         intent.setData(Uri.parse("tel:10086"));
                         context.startActivity(intent);
                     }
                 });
                 builder.setPositiveButton("取消",null);
                 AlertDialog alertdialog1 = builder.create();
                 alertdialog1.show();
                 break;
             case R.id.tv_title_2:
                 //收藏
                 if (tag == 1){
                     HttpRequest.addCollection(context,6, Integer.parseInt(packageId),new ServerResultListener() {
                         @Override
                         public void onSuccess(String json, String msg) {
                             img_sc.setVisibility(View.GONE);
                             img_unsc.setVisibility(View.VISIBLE);
                             Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                         }

                         @Override
                         public void onFailure(String msg) {
                             Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                         }
                     });
                     tag = 0;
                 }else if (tag == 0){
                     HttpRequest.delCollection(context,6, Integer.parseInt(packageId),new ServerResultListener() {
                         @Override
                         public void onSuccess(String json, String msg) {
                             img_sc.setVisibility(View.VISIBLE);
                             img_unsc.setVisibility(View.GONE);
                             Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                         }

                         @Override
                         public void onFailure(String msg) {
                             Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                         }
                     });
                     tag = 1;
                 }
                 break;
             case R.id.tv_title_3:
                 break;
             case R.id.tv_title_4:
                 buyPackage();
                 break;
             case R.id.ll_view:
                 startActivity(new Intent(this, AllEvaluateAcitivity.class).putExtra("packageId",packageId));
                 break;
         }
    }

    public void buyPackage(){
        HttpRequest.buyPackage(context, packagePirce, packageId, 1, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                OrderModels order = JSON.parseObject(json,OrderModels.class);
                String code = order.getOrderCode();
                double orderpirce = order.getOrderTruePrice();
                startActivity(new Intent(PackageDetailasActivity.this,CashierActivity.class)
                        .putExtra("code",code)
                        .putExtra("price",String.valueOf(orderpirce))
                        .putExtra("order",order));
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showPackageDetials(){
        Map<String,Object> map = new HashMap<>();
        map.put("packageId",packageId);
        ServerRequest.requestHttp(this, ServerUrl.showPackageDetials, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                PackageInfo packageInfo = JSON.parseObject(json,PackageInfo.class);
                if(!StringUtils.isEmpty(packageInfo.toString())){
                   if(!StringUtils.isEmpty(packageInfo.getPackageImage())){
                       AppContext.getInstance().displayImage(ServerUrl.mainUrl+packageInfo.getPackageImage(),ll_viewpage_1);
                   }
                    tv_name_1.setText(packageInfo.getPackageName());
                    tv_name_2.setText("已售"+packageInfo.getPackageSales());
                    if(packageInfo.getPackageType()==0) {
                        tv_name_3.setText("品种:" + packageInfo.getPackageVarieties());
                        tv_name_4.setText("含水量≥" + packageInfo.getPackageWeight()+"%");
                        tv_name_5.setText("发芽率≥" + packageInfo.getPackageLife()+"%");
                    }else{
                        tv_name_3.setText("种类:" + packageInfo.getPackageVarieties());
                        tv_name_4.setText("均重:" + packageInfo.getPackageWeight()+"kg");
                        tv_name_5.setText("成活率≥" + packageInfo.getPackageLife()+"%");
                    }
                    packagePirce = packageInfo.getPackagePrice();
                    tv_name_6.setText("￥" + packageInfo.getPackagePrice());
                    tv_features.setText(packageInfo.getPackageDetails());
                    tv_buy.setText(Html.fromHtml(packageInfo.getPackageBuyKnow()));

                    tv_view_number.setText("共" + packageInfo.getCountComment() + "条");
                    if(!StringUtils.isEmpty(packageInfo.getDetails().toString())){
                        framDetailsInfoList.clear();
                        framDetailsInfoList.addAll(packageInfo.getDetails());
                        adapter.notifyDataSetChanged();
                    }
                    if(null!=packageInfo.getComments()&&packageInfo.getComments().size()>0){
                        ll_yonghui.setVisibility(View.VISIBLE);
                        evaluationInfoList.clear();
                        evaluationInfoList.addAll(packageInfo.getComments());
                        adapter1.notifyDataSetChanged();
                    }else{
                        ll_yonghui.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
    //判断是否已经收藏
    public void checkCollection(){
        HttpRequest.checkCollection(context,6, Integer.parseInt(packageId),new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                tag = 1;
                img_sc.setVisibility(View.VISIBLE);
                img_unsc.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(String msg) {
                tag = 0;
                img_sc.setVisibility(View.GONE);
                img_unsc.setVisibility(View.VISIBLE);
            }
        });
    }
}
