package com.croshe.farming.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.util.ImageUtils;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.Product;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.R;
import com.croshe.farming.activity.ProductDetailsActivity;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/31.
 */

public class SeedsAdapter extends RecyclerView.Adapter<SeedsAdapter.ViewHloder> {

    private List<ProductInfo> list;
    private Context context;
    public SeedsAdapter (List<ProductInfo> list, Context context){
        this.list=list;
        this.context=context;
    }



    static class ViewHloder extends RecyclerView.ViewHolder {
        LinearLayout ll_seed;
        ImageView img;
        TextView text_goods_name__seed,text_goods_price__seed,text_goods_evaluate__seed,text_goods_rate_seed;
        public ViewHloder(View itemView) {
            super(itemView);
            ll_seed = (LinearLayout) itemView.findViewById(R.id.ll_seed);
            img= (ImageView) itemView.findViewById(R.id.image_goods_seed);
            text_goods_name__seed= (TextView) itemView.findViewById(R.id.text_goods_name__seed);
            text_goods_price__seed= (TextView) itemView.findViewById(R.id.text_goods_price__seed);
            text_goods_evaluate__seed= (TextView) itemView.findViewById(R.id.text_goods_evaluate__seed);
            text_goods_rate_seed= (TextView) itemView.findViewById(R.id.text_goods_rate_seed);
        }
    }

    public ViewHloder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seeds,parent,false);
        ViewHloder holder = new ViewHloder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SeedsAdapter.ViewHloder holder, final int position) {
        AppContext.getInstance().displayImage(ServerUrl.mainUrl+list.get(position).getProductSmallImage(), holder.img);
       holder.text_goods_name__seed.setText(list.get(position).getProductName());
        if(!StringUtils.isEmpty(list.get(position).getCount())) {
            holder.text_goods_evaluate__seed.setText(list.get(position).getCount() + "条评价");
        }else{
            holder.text_goods_evaluate__seed.setVisibility(View.GONE);
        }
        holder.text_goods_price__seed.setText("￥"+list.get(position).getProductPrice());
        if(!StringUtils.isEmpty(list.get(position).getRate())) {
            holder.text_goods_rate_seed.setText(list.get(position).getRate() + "%好评");
        }
        holder.ll_seed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productId = String.valueOf(list.get(position).getProductId());
                context.startActivity(new Intent(context, ProductDetailsActivity.class).putExtra("productId",productId));
            }
        });
    }


        @Override
    public int getItemCount() {

            return  list == null ? 0 : list.size();
    }




}
