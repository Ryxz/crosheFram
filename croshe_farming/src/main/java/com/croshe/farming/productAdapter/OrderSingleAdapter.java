package com.croshe.farming.productAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.R;
import com.croshe.farming.server.L;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */

public class OrderSingleAdapter extends RecyclerView.Adapter<OrderSingleAdapter.SingleView> {
    private Context context;
    private List<ProductInfo> list;
    public OrderSingleAdapter(Context context,List<ProductInfo> list){
        this.context = context;
        this.list = list;
    }


    @Override
    public SingleView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_order,parent,false);
        return new SingleView(view);
    }

    @Override
    public void onBindViewHolder(SingleView holder, int position) {
        String imgpath = list.get(position).getProductSmallImage();
        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath,holder.img_single, AppContext.image_display_options);
        holder.text_single_name.setText(list.get(position).getProductName());
        holder.signle_num.setText("x "+String.valueOf(list.get(position).getScNum()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SingleView extends RecyclerView.ViewHolder{
        private ImageView img_single;
        private TextView text_single_name,signle_num;

        public SingleView(View itemView) {
            super(itemView);
            img_single = (ImageView) itemView.findViewById(R.id.img_single);
            text_single_name = (TextView) itemView.findViewById(R.id.text_single_name);
            signle_num = (TextView) itemView.findViewById(R.id.signle_num);
        }
    }
}
