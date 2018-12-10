package com.croshe.farming.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.BasketInfo;
import com.croshe.farming.R;
import com.croshe.farming.activity.ProductDetailsActivity;
import com.croshe.farming.server.ServerUrl;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class AgriculturalAdapter extends RecyclerView.Adapter<AgriculturalAdapter.ViewHLoder> {
    private List<BasketInfo> list;
    private Context context;
    public AgriculturalAdapter(List<BasketInfo> list,Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public AgriculturalAdapter.ViewHLoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_agricultural,viewGroup,false);
        ViewHLoder hLoder = new ViewHLoder(view);
        return hLoder;
    }

    @Override
    public void onBindViewHolder(AgriculturalAdapter.ViewHLoder viewHLoder, final int i) {
        if(null!=list.get(i).getProduct().getProductSmallImage()&&list.get(i).getProduct().getProductSmallImage().length()>0) {
            AppContext.getInstance().displayImage(ServerUrl.mainUrl + list.get(i).getProduct().getProductSmallImage(), viewHLoder.iv_agr);
        }
        viewHLoder.tv_agr.setText(list.get(i).getProduct().getProductName());
        viewHLoder.tv_agr_1.setText("数量："+list.get(i).getNumber());
        viewHLoder.tv_agr_2.setText("规格："+list.get(i).getStandard().getStandardName());
        viewHLoder.btu_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProductDetailsActivity.class)
                        .putExtra("productId",String.valueOf(list.get(i).getProductId())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHLoder extends RecyclerView.ViewHolder {
       ImageView iv_agr;
        TextView tv_agr,tv_agr_1,tv_agr_2;
        LinearLayout btu_buy;
        public ViewHLoder(View itemView) {
            super(itemView);
            iv_agr= (ImageView) itemView.findViewById(R.id.iv_agr);
            tv_agr= (TextView) itemView.findViewById(R.id.tv_agr);
            tv_agr_1= (TextView) itemView.findViewById(R.id.tv_agr_1);
            tv_agr_2= (TextView) itemView.findViewById(R.id.tv_agr_2);
            btu_buy= (LinearLayout) itemView.findViewById(R.id.btu_buy);
        }
    }
}
