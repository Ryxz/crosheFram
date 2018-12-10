package com.croshe.farming.adapter;

import android.app.Activity;
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
import com.croshe.farming.Entity.OrderDeatils;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.Entity.Product;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.PersonalCenter.EvaluateActivity;
import com.croshe.farming.R;
import com.croshe.farming.activity.OrdeDetailActivity;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.util.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class DistributionAdapter extends RecyclerView.Adapter<DistributionAdapter.DistributionOrderView>{


    private Context context;
    private List<OrderModels> list ;
    List<String> imgs = new ArrayList<>();
    public DistributionAdapter(Context context,List<OrderModels> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public DistributionOrderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_order,parent,false);
        return new DistributionOrderView(view);
    }

    @Override
    public void onBindViewHolder(DistributionOrderView holder, final int position) {
        final OrderModels order = list.get(position);
        holder.shop_ordername.setText(order.getOrderCode());
        holder.shop_order_money.setText(String.valueOf(order.getOrderTruePrice()));
        //时间
        holder.shop_order_time.setText(order.getOrderDownTime().substring(0,16));
        List<OrderDeatils> orderDeatils = order.getOrderDeatils();
        for (int i = 0;i<orderDeatils.size();i++){
            holder.shop_order_num.setText(String.valueOf(orderDeatils.get(i).getCount()));
        }
        //图片
        if (orderDeatils.size()>=1){
            ProductInfo product = orderDeatils.get(0).getProduct();
            holder.img_pro1.setImageResource(0);
            holder.img_pro2.setImageResource(0);
            holder.img_pro3.setImageResource(0);
            holder.text_more.setVisibility(View.GONE);
            if(product!=null){
                String img = product.getProductSmallImage();
                ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+img,holder.img_pro1,AppContext.image_display_options);
            }
            if (orderDeatils.size()>=2){
                ProductInfo product1 = orderDeatils.get(1).getProduct();
                if(product!=null){
                    String img = product1.getProductSmallImage();
                    ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+img,holder.img_pro2,AppContext.image_display_options);
                }
                if (orderDeatils.size()>=3){
                    ProductInfo product2 = orderDeatils.get(2).getProduct();
                    if(product!=null){
                        String img = product2.getProductSmallImage();
                        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+img,holder.img_pro3,AppContext.image_display_options);
                    }
                    if (orderDeatils.size()>3){
                        holder.text_more.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        final int orderState = list.get(position).getOrderState();
        holder.text_shop_order_msg.setVisibility(View.VISIBLE);
        if (orderState == 2){
            holder.text_shop_order_msg.setText("待配送");
            holder.ll_reminde_delivery.setVisibility(View.VISIBLE);
            holder.ll_confirm_deliver.setVisibility(View.GONE);
            holder.ll_evaluate.setVisibility(View.GONE);
            holder.ll_all_question.setVisibility(View.VISIBLE);
        }else if (orderState == 3){
            holder.text_shop_order_msg.setText("已配送");
            holder.ll_reminde_delivery.setVisibility(View.GONE);
            holder.ll_confirm_deliver.setVisibility(View.VISIBLE);
            holder.ll_evaluate.setVisibility(View.GONE);
            holder.ll_all_question.setVisibility(View.VISIBLE);
        }else if (orderState == 4){
            holder.text_shop_order_msg.setText("已签收");
            holder.ll_all_question.setVisibility(View.VISIBLE);
            holder.ll_reminde_delivery.setVisibility(View.GONE);
            holder.ll_confirm_deliver.setVisibility(View.GONE);
//            holder.text_shop_order_msg.setText("待评价");
            holder.ll_evaluate.setVisibility(View.VISIBLE);
//            holder.ll_all_question.setVisibility(View.GONE);
        }
//        else if (orderState == 5){
//            holder.ll_all_question.setVisibility(View.VISIBLE);
//            holder.ll_reminde_delivery.setVisibility(View.GONE);
//            holder.ll_confirm_deliver.setVisibility(View.GONE);
//            holder.text_shop_order_msg.setText("待评价");
//            holder.ll_evaluate.setVisibility(View.VISIBLE);
//        }
        holder.ll_reminde_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequest.addPsMessage(context, order.getOrderId(), order.getOrderCode(), new ServerResultListener() {
                    @Override
                    public void onSuccess(String json, String msg) {
                        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String msg) {
                        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.ll_confirm_deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequest.setOrderState(context, order.getOrderId(), 4, new ServerResultListener() {
                    @Override
                    public void onSuccess(String json, String msg) {
                        context.sendBroadcast(new Intent("refreshOrder"));
                    }

                    @Override
                    public void onFailure(String msg) {
                        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.ll_shop_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderState == 2||orderState == 3||orderState == 4){
                    context.startActivity(new Intent(context, OrdeDetailActivity.class)
                            .putExtra("orderCode",list.get(position).getOrderCode())
                            .putExtra("orderType",1));

                }
            }
        });
        holder.ll_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, OrdeDetailActivity.class)
                        .putExtra("orderCode",list.get(position).getOrderCode())
                        .putExtra("orderType",0));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class DistributionOrderView extends RecyclerView.ViewHolder{
        private TextView shop_ordername,shop_order_time,shop_order_num,shop_order_money;
        private TextView text_maypay,text_more,text_shop_order_msg;
        private ImageView img_pro1,img_pro2,img_pro3;
        private LinearLayout ll_left,ll_confirm_deliver,ll_evaluate,ll_reminde_delivery,
                ll_all_question,ll_shop_order;

        public DistributionOrderView(View itemView) {
            super(itemView);
            shop_ordername = (TextView) itemView.findViewById(R.id.text_shop_ordername);
            shop_order_time = (TextView) itemView.findViewById(R.id.text_shop_order_time);
            shop_order_num = (TextView) itemView.findViewById(R.id.text_shop_order_num);
            shop_order_money = (TextView) itemView.findViewById(R.id.text_shop_order_money);
            img_pro1 = (ImageView) itemView.findViewById(R.id.img_pro1);
            img_pro2 = (ImageView) itemView.findViewById(R.id.img_pro2);
            img_pro3 = (ImageView) itemView.findViewById(R.id.img_pro3);
            text_more = (TextView) itemView.findViewById(R.id.text_more);
            text_maypay = (TextView) itemView.findViewById(R.id.text_maypay);
            text_shop_order_msg = (TextView) itemView.findViewById(R.id.text_shop_order_msg);
            ll_left = (LinearLayout) itemView.findViewById(R.id.ll_left);
            ll_confirm_deliver = (LinearLayout) itemView.findViewById(R.id.ll_confirm_deliver);
            ll_evaluate = (LinearLayout) itemView.findViewById(R.id.ll_evaluate);
            ll_reminde_delivery = (LinearLayout) itemView.findViewById(R.id.ll_reminde_delivery);
            ll_all_question = (LinearLayout) itemView.findViewById(R.id.ll_all_question);
            ll_shop_order = (LinearLayout) itemView.findViewById(R.id.ll_shop_order);
        }
    }

}

