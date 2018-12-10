package com.croshe.farming.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.BalanceInfo;
import com.croshe.farming.Entity.OrderDeatils;
import com.croshe.farming.Entity.OrderInfo;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.Entity.PackageInfo;
import com.croshe.farming.Entity.Product;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.R;
import com.croshe.farming.activity.CashierActivity;
import com.croshe.farming.activity.OrdeDetailActivity;
import com.croshe.farming.activity.ProductDetailsActivity;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.util.DialogUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ZPA on 2017/6/28.
 */

public class ShopOrderAdapter extends RecyclerView.Adapter<ShopOrderAdapter.ShopOrderView>{

    private Context context;
    private List<OrderModels> list ;
    private GridAdapter adapter;
    private OrderInfo orderInfo;
    List<String> imgs = new ArrayList<>();
    public ShopOrderAdapter(Context context,List<OrderModels> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ShopOrderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_order,parent,false);
        return new ShopOrderView(view);
    }

    @Override
    public void onBindViewHolder(ShopOrderView holder, final int position) {

        OrderModels order = list.get(position);
        holder.shop_ordername.setText(order.getOrderCode());
        holder.shop_order_money.setText(String.valueOf(order.getOrderTruePrice()));
        holder.shop_order_time.setText(order.getOrderDownTime().substring(0,16));
        List<OrderDeatils> orderDeatils = order.getOrderDeatils();
        for (int i = 0;i<orderDeatils.size();i++){
            holder.shop_order_num.setText(String.valueOf(orderDeatils.size()));
        }
        holder.img_pro1.setImageResource(0);
        holder.img_pro2.setImageResource(0);
        holder.img_pro3.setImageResource(0);
        holder.text_more.setVisibility(View.GONE);
        if (orderDeatils.size()>=1){
            if (list.get(position).getOrderDeatils().get(0).getOrderDeatilsType() == 2){
                if (orderDeatils.get(0).getPackagess()!=null){
                    PackageInfo packageInfo = orderDeatils.get(0).getPackagess();
                    String img = packageInfo.getPackageImage();
                    ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+img,holder.img_pro1,AppContext.image_display_options);
                }
            }else {
                ProductInfo product = orderDeatils.get(0).getProduct();
                if(product!=null){
                    String img = product.getProductSmallImage();
                    ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+img,holder.img_pro1,AppContext.image_display_options);
                }
                if (orderDeatils.size()>=2){
                    ProductInfo product1 = orderDeatils.get(1).getProduct();
                    if(product1!=null){
                        String img = product1.getProductSmallImage();
                        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+img,holder.img_pro2,AppContext.image_display_options);
                    }
                    if (orderDeatils.size()>=3){
                        ProductInfo product2 = orderDeatils.get(2).getProduct();
                        if(product2!=null){
                            String img = product2.getProductSmallImage();
                            ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+img,holder.img_pro3,AppContext.image_display_options);
                        }
                        if (orderDeatils.size()>3){
                            holder.text_more.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

        }


        if (list.get(position).getOrderState()== 1){
            holder.text_maypay.setText("件商品，实付款");
            holder.ll_buy_again.setVisibility(View.GONE);
            holder.ll_buy_now.setVisibility(View.GONE);
        }else {
            holder.text_maypay.setText("件商品，应付款");
            holder.ll_buy_again.setVisibility(View.GONE);
            holder.ll_buy_now.setVisibility(View.VISIBLE);
        }

        holder.ll_shop_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getOrderDeatils().get(0).getOrderDeatilsType() == 2){
                    context.startActivity(new Intent(context, OrdeDetailActivity.class)
                            .putExtra("orderCode",list.get(position).getOrderCode())
                            .putExtra("orderType",0));
                }else {
                    if (list.get(position).getOrderState() == 1){
                        context.startActivity(new Intent(context, OrdeDetailActivity.class)
                                .putExtra("orderCode",list.get(position).getOrderCode())
                                .putExtra("orderType",0));
                    }
                }
            }
        });

        holder.ll_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CashierActivity.class)
                        .putExtra("code",list.get(position).getOrderCode())
                        .putExtra("price",String.valueOf(list.get(position).getOrderTruePrice())));
            }
        });
        holder.ll_order_delete.setVisibility(View.VISIBLE);
        holder.ll_line.setVisibility(View.VISIBLE);
        holder.ll_order_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showYesOrCancelDialog(context, ((Activity)context).getLayoutInflater(), "温馨提示", "确认删除此订单?",
                        new DialogUtil.OnBtnClickListener() {
                            @Override
                            public void onClick(Dialog dialog) {
                                deleteOrder(list.get(position).getOrderId());
                                dialog.dismiss();
                            }
                        },
                        new DialogUtil.OnBtnClickListener() {
                            @Override
                            public void onClick(Dialog dialog) {
                                dialog.dismiss();
                            }
                        });
            }
        });
//        if (imgs != null&&imgs.size()>0){
//
//            int length = 190;  //定义一个长度
//            int size = imgs.size();  //得到集合长度
//            //获得屏幕分辨路
//            DisplayMetrics dm = new DisplayMetrics();
//            ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
//            float density = dm.density;//   Log.d(TAG, "handleMessage: "+density);
//            int gridviewWidth = (int) (size * (length + 10) * density);// int gridviewWidth = (int) (size * (length + 4) * density);
//            int itemWidth = (int) (length * density);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
//            holder.gview_shop.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
//            holder.gview_shop.setColumnWidth(itemWidth); // 设置列表项宽
//            holder.gview_shop.setHorizontalSpacing(2); // 设置列表项水平间距
//            holder.gview_shop.setStretchMode(GridView.NO_STRETCH);
//            holder.gview_shop.setNumColumns(imgs.size()); // 设置列数量=列表集合数
//
//            adapter = new GridAdapter(context,imgs);
//            holder.gview_shop.setAdapter(adapter);
//        }

    }

    public void deleteOrder(int orderId){
        HttpRequest.deleteOrder(context, orderId, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                context.sendBroadcast(new Intent("deleteRefrsh"));
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ShopOrderView extends RecyclerView.ViewHolder{
        private TextView shop_ordername,shop_order_time,shop_order_num,shop_order_money;
//        private GridView gview_shop;
        private TextView text_maypay,text_more;
        private LinearLayout ll_buy_now,ll_buy_again,ll_shop_order,ll_order_delete,ll_line;
        private ImageView img_pro1,img_pro2,img_pro3;

        public ShopOrderView(View itemView) {
            super(itemView);
            shop_ordername = (TextView) itemView.findViewById(R.id.text_shop_ordername);
            shop_order_time = (TextView) itemView.findViewById(R.id.text_shop_order_time);
            shop_order_num = (TextView) itemView.findViewById(R.id.text_shop_order_num);
            shop_order_money = (TextView) itemView.findViewById(R.id.text_shop_order_money);
            text_maypay = (TextView) itemView.findViewById(R.id.text_maypay);
//            gview_shop = (GridView) itemView.findViewById(R.id.gview_shop);
            ll_buy_now = (LinearLayout) itemView.findViewById(R.id.ll_buy_now);
            ll_buy_now = (LinearLayout) itemView.findViewById(R.id.ll_buy_now);
            ll_buy_again = (LinearLayout) itemView.findViewById(R.id.ll_buy_again);
            ll_buy_now = (LinearLayout) itemView.findViewById(R.id.ll_buy_now);
            ll_shop_order = (LinearLayout) itemView.findViewById(R.id.ll_shop_order);
            ll_order_delete = (LinearLayout) itemView.findViewById(R.id.ll_order_delete);
            ll_line = (LinearLayout) itemView.findViewById(R.id.ll_line);
            img_pro1 = (ImageView) itemView.findViewById(R.id.img_pro1);
            img_pro2 = (ImageView) itemView.findViewById(R.id.img_pro2);
            img_pro3 = (ImageView) itemView.findViewById(R.id.img_pro3);
            text_more = (TextView) itemView.findViewById(R.id.text_more);

        }
    }

    public class GridAdapter extends BaseAdapter{
        Context context;
        List<String> list;
        private HashMap<Integer,View> viewMap;

        @Override
        public int getCount() {
            return list.size();
        }
        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.context = context;
        }
        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            viewMap = new HashMap<>();
            if(!viewMap.containsKey(position) || viewMap.get(position) == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_photo,null);
                holder.image = (ImageView) convertView.findViewById(R.id.img_photo);
                convertView.setTag(holder);
                viewMap.put(position, convertView);
            }else{
                convertView = viewMap.get(position);
                holder = (ViewHolder) convertView.getTag();
            }
            if (!StringUtils.isEmpty(list.get(position))) {
                Glide.with(AppContext.getInstance().getApplicationContext()).load(ServerUrl.mainUrl + list.get(position)).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.image);
            }
            return convertView;
        }

        public class ViewHolder{
            ImageView image;
        }
    }
}
