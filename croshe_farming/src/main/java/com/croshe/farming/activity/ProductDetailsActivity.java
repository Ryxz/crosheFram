package com.croshe.farming.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheBottomTabMenuView;
import com.croshe.crosheandroidbase.view.CrosheTabView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.Collocation;
import com.croshe.farming.Entity.CommentModel;
import com.croshe.farming.Entity.OrderDeatils;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.Entity.Product;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.Entity.ProductStands;
import com.croshe.farming.Entity.ProductType;
import com.croshe.farming.MainActivity;
import com.croshe.farming.R;
import com.croshe.farming.View.MyGridView;
import com.croshe.farming.adapter.GridViewProductsAdapter;
import com.croshe.farming.adapter.ProductDetailAdapter;
import com.croshe.farming.fragment.MarketFragment01;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.server.ToastTime;
import com.croshe.farming.util.Constant;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 商品详情
 * Created by Administrator on 2017/6/30.
 */

public class ProductDetailsActivity extends CrosheBaseActivity {
    private RecyclerView recycler_view;
    private ImageView img_cancel,img_product,img_unc,img_c;
    private LinearLayout ll_contair,ll_service,ll_collection,ll_shopcar,img_back;
    private String productId;
    private ProductDetailAdapter detailAdapter;
    List<Product> list = new ArrayList<>();
    private LinearLayout ll_add_buycar,ll_shop_d1,ll_shop_d2,ll_buy_now,ll_jian,ll_jia,
            ll_hideview,ll_add_buycars,ll_style;
    private RelativeLayout rel_product_style;
    private TextView text_price_add,text_surplus,text_types;
    private EditText et_number;
    int numbers = 1;
    String standardId;
    private MyGridView gridView;
    private ProGridAdapter gridAdapter;
    private String targetType;
    private int id,targetid,tag;
    private List<CommentModel> comments = new ArrayList<>();
    private List<ProductInfo> proList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product_details);
        productId = getIntent().getStringExtra("productId");
        initView();
        getProductDetail(productId);
        initClick();
    }



    @Override
    public void initView() {
        ll_contair = (LinearLayout) findViewById(R.id.ll_product_contair);
        img_back = (LinearLayout) findViewById(R.id.img_product_back);
        img_cancel = (ImageView) findViewById(R.id.img_cancel1);
        img_product = (ImageView) findViewById(R.id.img_product);
        text_price_add = (TextView) findViewById(R.id.text_price_add);
        text_surplus = (TextView) findViewById(R.id.text_surplus);
        text_types = (TextView) findViewById(R.id.text_types);

        img_unc = (ImageView) findViewById(R.id.img_unc);
        img_c = (ImageView) findViewById(R.id.img_c);


        ll_style = (LinearLayout) findViewById(R.id.ll_style);
        ll_add_buycars = (LinearLayout) findViewById(R.id.ll_add_buycars);
        ll_add_buycar = (LinearLayout) findViewById(R.id.ll_add_buycar);
        ll_shop_d1 = (LinearLayout) findViewById(R.id.ll_shop_d1);
        ll_shop_d2 = (LinearLayout) findViewById(R.id.ll_shop_d2);
        ll_buy_now = (LinearLayout) findViewById(R.id.ll_buy_now);
        ll_jian = (LinearLayout) findViewById(R.id.ll_jian);
        ll_jia = (LinearLayout) findViewById(R.id.ll_jia);
        ll_hideview = (LinearLayout) findViewById(R.id.ll_hideview);
        et_number = (EditText) findViewById(R.id.et_number);

        rel_product_style = (RelativeLayout) findViewById(R.id.rel_product_style);
        gridView = (MyGridView) findViewById(R.id.grid_product_style);
        recycler_view = (RecyclerView) findViewById(R.id.recyclerView_product_detail);
        detailAdapter = new ProductDetailAdapter(context,list,productId);
        recycler_view.setAdapter(detailAdapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        String number = String.valueOf(numbers);
        et_number.setText(number);
        et_number.setCursorVisible(false);
        et_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_number.setCursorVisible(true);
            }
        });
        ll_shopcar = (LinearLayout) findViewById(R.id.ll_shopcar);
        ll_service = (LinearLayout) findViewById(R.id.ll_service);
        ll_collection = (LinearLayout) findViewById(R.id.ll_collection);
        targetid = Integer.parseInt(productId);
        checkCollection();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        img_back.setOnClickListener(this);
        ll_add_buycar.setOnClickListener(this);
        img_cancel.setOnClickListener(this);
        ll_buy_now.setOnClickListener(this);
        ll_jia.setOnClickListener(this);
        ll_jian.setOnClickListener(this);
        ll_add_buycars.setOnClickListener(this);
        ll_service.setOnClickListener(this);
        ll_collection.setOnClickListener(this);
        ll_shopcar.setOnClickListener(this);
        ll_hideview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_product_back:
                finish();
                break;
            case R.id.ll_hideview:
                break;
            case R.id.ll_add_buycar:
                ll_hideview.setVisibility(View.VISIBLE);
                ll_shop_d2.setVisibility(View.VISIBLE);
                ll_shop_d1.setVisibility(View.GONE);
                rel_product_style.setVisibility(View.VISIBLE);
                ll_style.setVisibility(View.VISIBLE);
                break;
            case R.id.img_cancel1:
                ll_style.setVisibility(View.GONE);
                ll_hideview.setVisibility(View.GONE);
                ll_shop_d1.setVisibility(View.VISIBLE);
                ll_shop_d2.setVisibility(View.GONE);
                rel_product_style.setVisibility(View.GONE);
                break;
            case R.id.ll_buy_now:
                String price = text_price_add.getText().toString();
                Double money = Double.valueOf(price);
                String count = et_number.getText().toString();
                buyNow(productId,money,count,standardId);
                break;
            case R.id.ll_jia:
                numbers++;
                String number = String.valueOf(numbers);
                et_number.setText(number);
                break;
            case R.id.ll_jian:
                if (numbers>1){
                    numbers--;
                    String number2 = String.valueOf(numbers);
                    et_number.setText(number2);
                }else if(numbers == 1){
                    Toast toast = Toast.makeText(context, "不能再减了",Toast.LENGTH_LONG);
                    ToastTime.showMyToast(toast,1000);
                }
                break;
            case R.id.ll_add_buycars:
                targetType = Constant.OpinionType02;
                String price1 = text_price_add.getText().toString();
                Double money1 = Double.valueOf(price1);
                String count1 = et_number.getText().toString();
                addBuyCar(productId,money1,count1,standardId,targetType);
                break;
            case R.id.ll_service:
                //客服
                showGlobal();
                break;
            case R.id.ll_collection:
                //收藏
                if (tag == 1){
                    HttpRequest.addCollection(context,1, targetid,new ServerResultListener() {
                        @Override
                        public void onSuccess(String json, String msg) {
                            img_c.setVisibility(View.VISIBLE);
                            img_unc.setVisibility(View.GONE);
                            Toast toast = Toast.makeText(context, msg,Toast.LENGTH_LONG);
                            ToastTime.showMyToast(toast,500);
                        }

                        @Override
                        public void onFailure(String msg) {
                            Toast toast = Toast.makeText(context, msg,Toast.LENGTH_LONG);
                            ToastTime.showMyToast(toast,500);
                        }
                    });
                    tag = 0;
                }else if (tag == 0){
                    HttpRequest.delCollection(context,1, targetid,new ServerResultListener() {
                        @Override
                        public void onSuccess(String json, String msg) {
                            img_c.setVisibility(View.GONE);
                            img_unc.setVisibility(View.VISIBLE);
                            Toast toast = Toast.makeText(context, msg,Toast.LENGTH_LONG);
                            ToastTime.showMyToast(toast,500);
                        }

                        @Override
                        public void onFailure(String msg) {
                            Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                        }
                    });
                    tag = 1;
                }

                break;
            case R.id.ll_shopcar:
                //购物车
                startActivity(new Intent(context,ShopCarActivity.class).putExtra("type",2));
                break;
        }

    }

    String typeclass;
    String imgpath;
    String imgname;
    //获得商品详情
    public void getProductDetail(String id){
        Map<String,Object> map = new HashMap<>();
        map.put("productId", id);
        ServerRequest.requestHttp(context, ServerUrl.getProductDetails, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                 products = JSON.parseObject(json,Product.class);
                 list.clear();
                 if (products!=null){
                    getCollcatioon();
                 }

            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();

            }
        });
    }

    Product products;
    public void getCollcatioon( ){
        Map<String,Object> map = new HashMap<>();
        map.put("detailsProductId",productId);
        ServerRequest.requestHttp(context, ServerUrl.showPackageDetails, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Collocation collocation = JSON.parseObject(json,Collocation.class);
                if(null!=collocation){
                    products.setCollocation(collocation);
                }
                getProductDj();

            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getProductDj(){
        Map<String,Object> map = new HashMap<>();
        map.put("page",1);
        ServerRequest.requestHttp(context, ServerUrl.getPublishProduct, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<ProductInfo> productList = JSON.parseArray(json,ProductInfo.class);
                if (productList!=null&&productList.size()>0){
                    products.setProductList(productList);
                }
                showAllComment();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
     //评论 显示第一条//查看全部评论得到全部评论
    public void showAllComment(){
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("page",1);
        ServerRequest.requestHttp(context, ServerUrl.showAllComment, map, "加载中", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<CommentModel> commentlist = JSON.parseArray(json,CommentModel.class);
                if (commentlist!=null&&commentlist.size()>0){
                    products.setCommentlist(commentlist);
                }
                list.add(products);
                imgpath = products.getProductSmallImage();
                ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath,img_product, AppContext.image_display_options);
                imgname = products.getProductName();
                ProductType type = products.getType();
                typeclass = type.getTypeClass();
                List<ProductStands> standsList = products.getProductStandardModels();
                gridAdapter = new ProGridAdapter(standsList,context);
                gridView.setNumColumns(3);
                gridView.setAdapter(gridAdapter);
                detailAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

//立即购买
    public void buyNow(String id, Double pirce, final String count, String standardId){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("productId", id);
        map.put("price", pirce);
        map.put("count", count);
        map.put("standardId", standardId);

        ServerRequest.requestHttp(context, ServerUrl.buyProduct, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                OrderModels order = JSON.parseObject(json,OrderModels.class);
                String code = order.getOrderCode();
                double orderpirce = order.getOrderTruePrice();
                ll_hideview.setVisibility(View.GONE);
                ll_shop_d1.setVisibility(View.VISIBLE);
                ll_shop_d2.setVisibility(View.GONE);
                rel_product_style.setVisibility(View.GONE);
                ll_style.setVisibility(View.GONE);
                startActivity(new Intent(ProductDetailsActivity.this,CashierActivity.class)
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

    public void addBuyCar(String id, Double pirce, final String count, String standardId,String targetType){
        if (StringUtils.isEmpty(count)){
            Toast.makeText(context, "商品数量不可为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("targetType", targetType);
        map.put("targetId", id);
        map.put("productPrice", pirce);
        map.put("productCount", count);
        map.put("standardId", standardId);

        ServerRequest.requestHttp(context, ServerUrl.addShopCar, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                ll_hideview.setVisibility(View.GONE);
                ll_shop_d1.setVisibility(View.VISIBLE);
                ll_shop_d2.setVisibility(View.GONE);
                rel_product_style.setVisibility(View.GONE);
                ll_style.setVisibility(View.GONE);
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                sendBroadcast(new Intent("refrshbuycar"));
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();

            }
        });
    }
    //判断是否已经收藏
    public void checkCollection(){
        HttpRequest.checkCollection(context,1, targetid,new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                tag = 1;
                img_c.setVisibility(View.GONE);
                img_unc.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailure(String msg) {
                tag = 0;
                img_c.setVisibility(View.VISIBLE);
                img_unc.setVisibility(View.GONE);
            }
        });
    }
    public void showGlobal(){
        HttpRequest.showGlobal(context, 0, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                final String phone = json;
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

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    public class ProGridAdapter extends BaseAdapter{
        List<ProductStands> list;
        Context context;
        public ProGridAdapter(List<ProductStands> list,Context context){
            this.context = context;
            this.list = list;
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        int tag = 0;
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final GridViewHolder holder;
            if (convertView == null){
                holder = new GridViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_product,parent,false);
                holder.textView = (TextView) convertView.findViewById(R.id.text_product);
                holder.ll_product = (LinearLayout) convertView.findViewById(R.id.ll_product);
                holder.textView.setText(list.get(position).getStandardName());
                convertView.setTag(holder);
            }else{
                holder = (GridViewHolder) convertView.getTag();
            }

            if (tag == 0){
                list.get(0).setCheck("1");
            }
            holder.ll_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0;i<list.size();i++){
                        list.get(i).setCheck("0");
                        if (i == position){
                            list.get (position).setCheck("1");
                        }
                    }
                    gridAdapter.notifyDataSetChanged();
                    tag = 1;

                }
            });

            if (list.get(position).getCheck().equals("1")){
                holder.ll_product.setBackgroundResource(R.drawable.alert_orange_bg);
                standardId = list.get(position).getStandardId();
                text_types.setText(list.get(position).getStandardName());
                text_surplus.setText(list.get(position).getStock());
                text_price_add.setText(list.get(position).getStandardPrice()+"");
            }
            if (list.get(position).getCheck().equals("0")) {
            holder.ll_product.setBackgroundResource(R.drawable.back_seeds);
        }

            return convertView;
        }

        public class GridViewHolder{
            TextView textView;
            LinearLayout ll_product;
        }
    }


}
