package com.croshe.farming.adapter;

import android.app.Activity;
import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.OrderDeatils;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.Entity.Product;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.util.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class PendEvaluationAdapter extends RecyclerView.Adapter<PendEvaluationAdapter.ShopOrderView>{


    private Context context;
    private List<OrderModels> list ;
    private GridAdapter adapter;
    public PendEvaluationAdapter(Context context,List<OrderModels> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ShopOrderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pend_eval,parent,false);
        return new ShopOrderView(view);
    }

    @Override
    public void onBindViewHolder(ShopOrderView holder, int position) {
//        OrderModels order = list.get(position);
//        holder.shop_ordername.setText(order.getUserName());
//        holder.shop_order_money.setText(order.getOrderTruePrice());
//        holder.datatime.setText(order.getOrderDownTime());
//        OrderDeatils orderDeatil = order.getOrderDeatils();
//        holder.shop_order_num.setText(orderDeatil.getCount());
//        List<Product> products = orderDeatil.getProducts();
//        List<String> imgs = new ArrayList<>();
//        if (products != null&&products.size()>0){
//            imgs.clear();
//            for (int i = 0;i<products.size();i++){
//                String img = products.get(i).getProductSmallImage();
//                imgs.add(img);
//            }
//            int length = 150;  //定义一个长度
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

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ShopOrderView extends RecyclerView.ViewHolder{
        private TextView shop_ordername,shop_order_num,shop_order_money,datatime;
        private GridView gview_shop;

        public ShopOrderView(View itemView) {
            super(itemView);
            datatime = (TextView) itemView.findViewById(R.id.text_datatime);
            shop_ordername = (TextView) itemView.findViewById(R.id.text_shop_ordername);
            shop_order_num = (TextView) itemView.findViewById(R.id.text_shop_order_num);
            shop_order_money = (TextView) itemView.findViewById(R.id.text_shop_order_money);

            gview_shop = (GridView) itemView.findViewById(R.id.gview_shop);
        }
    }

    public class GridAdapter extends BaseAdapter {
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
                holder = new GridAdapter.ViewHolder();
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