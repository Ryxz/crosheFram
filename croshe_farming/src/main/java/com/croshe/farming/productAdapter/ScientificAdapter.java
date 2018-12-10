package com.croshe.farming.productAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.Entity.PackageDetailsModel;
import com.croshe.farming.Entity.PackageModel;
import com.croshe.farming.Entity.Product;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.Entity.StandardNameEntity;
import com.croshe.farming.R;
import com.croshe.farming.activity.CashierActivity;
import com.croshe.farming.activity.ProductDetailsActivity;
import com.croshe.farming.adapter.ProductDetailAdapter;
import com.croshe.farming.farm.PackageDetailasActivity;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */

public class ScientificAdapter extends RecyclerView.Adapter<ScientificAdapter.ScientificView> {
    private List<PackageModel> list ;
    private Context context;
    private ScientificItemAdapter adapter;
    private String packagePirce,packageId;

    public ScientificAdapter(Context context,List<PackageModel> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ScientificView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scientific,parent,false);
        return new ScientificView(view);
    }

    @Override
    public void onBindViewHolder(final ScientificView holder, final int position) {
        holder.package_name.setText(list.get(position).getPackageName());
        holder.package_price.setText("￥"+list.get(position).getPackagePrice());
        holder.package_save.setText("节省：￥"+list.get(position).getPackageSave());
        final List<PackageDetailsModel> detailsModels = list.get(position).getPackageDetailsModels();
        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+ProductDetailAdapter.imgPath,holder.img1,AppContext.image_display_options);
        holder.img_more.setVisibility(View.GONE);
        holder.img2.setImageResource(0);
//        holder.img3.setImageResource(0);
        if (detailsModels!=null&&detailsModels.size()>0){
        if (detailsModels.size()>=1){
            holder.ll_imgs_02.setVisibility(View.VISIBLE);
            ProductInfo product1 = detailsModels.get(0).getProduct();
            String imgpath = product1.getProductSmallImage();
            ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath,holder.img2, AppContext.image_display_options);
            if (detailsModels.size()>=2){
                holder.img_more.setVisibility(View.VISIBLE);
            }
//            if (detailsModels.size()>=2){
//                holder.ll_imgs_03.setVisibility(View.VISIBLE);
//                ProductInfo product2 = detailsModels.get(1).getProduct();
//                String imgpath2 = product2.getProductSmallImage();
//                ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath2,holder.img3, AppContext.image_display_options);

//            }
        }
        }
        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProductDetailsActivity.class)
                        .putExtra("productId",String.valueOf(detailsModels.get(0).getProduct().getProductId())));
            }
        });
//        holder.img3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(context, ProductDetailsActivity.class)
//                        .putExtra("productId",String.valueOf(detailsModels.get(1).getProduct().getProductId())));
//            }
//        });
        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+ProductDetailAdapter.imgPath,holder.img_details,AppContext.image_display_options);
        holder.text_product_details.setText(ProductDetailAdapter.imgName);
        holder.img_see_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.img_see_detail.setVisibility(View.GONE);
                holder.img_close_detail.setVisibility(View.VISIBLE);
                holder.ll_imgs.setVisibility(View.GONE);
                holder.ll_imgs_detail.setVisibility(View.VISIBLE);
            }
        });
        holder.img_close_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.img_see_detail.setVisibility(View.VISIBLE);
                holder.img_close_detail.setVisibility(View.GONE);
                holder.ll_imgs.setVisibility(View.VISIBLE);
                holder.ll_imgs_detail.setVisibility(View.GONE);
            }
        });
        adapter = new ScientificItemAdapter(context,detailsModels);
        holder.recycler_scientific_item.setAdapter(adapter);
        holder.recycler_scientific_item.setLayoutManager(new LinearLayoutManager(context));
        packagePirce = list.get(position).getPackagePrice();
        packageId = String.valueOf(list.get(position).getPackageId());
        holder.ll_buy_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyPackage();
            }
        });
    }
    public void buyPackage(){
        HttpRequest.buyPackage(context, packagePirce, packageId, 1, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                OrderModels order = JSON.parseObject(json,OrderModels.class);
                String code = order.getOrderCode();
                double orderpirce = order.getOrderTruePrice();
                context.startActivity(new Intent(context,CashierActivity.class)
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
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ScientificView extends RecyclerView.ViewHolder{
        private TextView package_name,package_price,package_save,text_product_details;
        private ImageView img_see_detail,img_close_detail,img1,img2,img3,img_details;
        private LinearLayout ll_imgs,ll_imgs_detail,ll_imgs_02,ll_imgs_03,ll_buy_it,img_more;
        private RecyclerView recycler_scientific_item;

        public ScientificView(View itemView) {
            super(itemView);
            package_name = (TextView) itemView.findViewById(R.id.text_package_name);
            package_price = (TextView) itemView.findViewById(R.id.text_package_price);
            package_save = (TextView) itemView.findViewById(R.id.text_package_save);
            img_see_detail = (ImageView) itemView.findViewById(R.id.img_see_detail);
            img_close_detail = (ImageView) itemView.findViewById(R.id.img_close_detail);
            img1 = (ImageView) itemView.findViewById(R.id.img_scientific_img1);
            img2 = (ImageView) itemView.findViewById(R.id.img_scientific_img2);
//            img3 = (ImageView) itemView.findViewById(R.id.img_scientific_img3);
            img_more = (LinearLayout) itemView.findViewById(R.id.img_more);
            ll_imgs = (LinearLayout) itemView.findViewById(R.id.ll_imgs);
            ll_imgs_detail = (LinearLayout) itemView.findViewById(R.id.ll_imgs_detail);
            img_details = (ImageView) itemView.findViewById(R.id.img_details);
            text_product_details = (TextView) itemView.findViewById(R.id.text_product_details);
            ll_imgs_02 = (LinearLayout) itemView.findViewById(R.id.ll_imgs_02);
//            ll_imgs_03 = (LinearLayout) itemView.findViewById(R.id.ll_imgs_03);
            ll_buy_it = (LinearLayout) itemView.findViewById(R.id.ll_buy_it);
            recycler_scientific_item = (RecyclerView) itemView.findViewById(R.id.recycler_scientific_item);
        }
    }

    public class ScientificItemAdapter extends RecyclerView.Adapter<ScientificItemAdapter.ScientificItemView>{
        private Context context;
        private List<PackageDetailsModel> packagelists;
        public ScientificItemAdapter(Context context,List<PackageDetailsModel> packagelists){
            this.context = context;
            this.packagelists = packagelists;
        }

        @Override
        public ScientificItemView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scientific_product,parent,false);
            return new ScientificItemView(view);
        }

        @Override
        public void onBindViewHolder(ScientificItemView holder, final int position) {
            holder.text_reason.setText(packagelists.get(position).getReason());
            holder.text_scientific_product_name.setText(packagelists.get(position).getName());
            String path = packagelists.get(position).getImg();
            ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+path,holder.img_scientific_product,AppContext.image_display_options);
            holder.img_scientific_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,ProductDetailsActivity.class)
                            .putExtra("productId",String.valueOf(packagelists.get(position).getProduct().getProductId())));
                }
            });
        }

        @Override
        public int getItemCount() {
            return packagelists.size();
        }

        public class ScientificItemView extends RecyclerView.ViewHolder{
            private ImageView img_scientific_product;
            private TextView text_scientific_product_name,text_reason;

            public ScientificItemView(View itemView) {
                super(itemView);
                img_scientific_product = (ImageView) itemView.findViewById(R.id.img_scientific_product);
                text_scientific_product_name = (TextView) itemView.findViewById(R.id.text_scientific_product_name);
                text_reason = (TextView) itemView.findViewById(R.id.text_reason);
            }
        }
    }

}
