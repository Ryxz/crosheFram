package com.croshe.farming.productAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.OrderDeatils;
import com.croshe.farming.Entity.PackageInfo;
import com.croshe.farming.Entity.Product;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.PersonalCenter.EvaluateActivity;
import com.croshe.farming.R;
import com.croshe.farming.activity.OrdeDetailActivity;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailView> {

    private List<OrderDeatils> list;
    private Context context;
    public OrderDetailAdapter(Context context,List<OrderDeatils> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public OrderDetailView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail,parent,false);
        return new OrderDetailView(view);
    }

    @Override
    public void onBindViewHolder(OrderDetailView holder, final int position) {
        if (OrdeDetailActivity.orderType ==  1){
            holder.ll_order_detail_pj.setVisibility(View.GONE);
        }

        if (list.get(position).getOrderDeatilsType() == 2){
            final PackageInfo packageInfo = list.get(position).getPackagess();
            final String img = list.get(position).getPackagess().getPackageImage();
            ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+img,holder.img_order_detail,AppContext.image_display_options);
            holder.order_detail_name.setText(packageInfo.getPackageName());
            holder.order_detail_stand.setText("规格"+packageInfo.getPackageVarieties());
            holder.order_detail_price.setText("￥"+packageInfo.getPackagePrice());
            int a = Integer.parseInt(packageInfo.getPackageId());
            holder.ll_order_detail_pj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, EvaluateActivity.class)
                            .putExtra("imgpath",img)
                            .putExtra("productId", Integer.parseInt(packageInfo.getPackageId()))
                            .putExtra("targetType",6));
                }
            });
        }else if (list.get(position).getOrderDeatilsType() == 1){
            if (list.get(position).getProduct()!=null){
                final ProductInfo product = list.get(position).getProduct();
                final String imgpath = list.get(position).getProduct().getProductSmallImage();
                ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath,holder.img_order_detail,AppContext.image_display_options);
                holder.order_detail_name.setText(product.getProductName());
                holder.order_detail_stand.setText("规格："+list.get(position).getStandard().getStandardName());
                holder.order_detail_price.setText("￥"+product.getProductPrice());
                holder.ll_order_detail_pj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, EvaluateActivity.class)
                                .putExtra("imgpath",imgpath)
                                .putExtra("productId",product.getProductId())
                                .putExtra("targetType",1));
                    }
                });
            }
        }
        holder.order_detail_number.setText("数量："+String.valueOf(list.get(position).getCount()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderDetailView extends RecyclerView.ViewHolder{
        private ImageView img_order_detail;
        private TextView order_detail_name,order_detail_stand,order_detail_number,order_detail_price;
        private LinearLayout ll_order_detail_pj;

        public OrderDetailView(View itemView) {
            super(itemView);
            img_order_detail = (ImageView) itemView.findViewById(R.id.img_order_detail);
            order_detail_name = (TextView) itemView.findViewById(R.id.text_order_detail_name);
            order_detail_stand = (TextView) itemView.findViewById(R.id.text_order_detail_stand);
            order_detail_number = (TextView) itemView.findViewById(R.id.text_order_detail_number);
            order_detail_price = (TextView) itemView.findViewById(R.id.text_order_detail_price);
            ll_order_detail_pj = (LinearLayout) itemView.findViewById(R.id.ll_order_detail_pj);
        }
    }
}
