package com.croshe.farming.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheViewPageView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.Collocation;
import com.croshe.farming.Entity.CommentModel;
import com.croshe.farming.Entity.Images;
import com.croshe.farming.Entity.ImgInfo;
import com.croshe.farming.Entity.PackageDetailsModel;
import com.croshe.farming.Entity.PackageModel;
import com.croshe.farming.Entity.Product;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.R;
import com.croshe.farming.View.MyGridView;
import com.croshe.farming.activity.ProductDetailsActivity;
import com.croshe.farming.product.ProductPWDetailActivity;
import com.croshe.farming.product.ScientificCollocationActivity;
import com.croshe.farming.product.ShowAllEvaluateActivity;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 */

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.PdetailHeadView> {
    private Context context;
    private List<String> foList = new ArrayList<>();
    private List<Product> list;
    private String productId;
    private List<String> keyList = new ArrayList<>();
    private GridViewkeyAdapter gridViewkeyAdapter;
    public static String imgPath,imgName;

    public ProductDetailAdapter(Context context,List<Product> list,String productId){
        this.context = context;
        this.list = list;
        this.productId = productId;
    }

    @Override
    public PdetailHeadView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_details_head, parent, false);
        return new PdetailHeadView(view);
    }

    @Override
    public void onBindViewHolder(final PdetailHeadView holder, int position) {
        final Product product = list.get(position);
        if (product.getProductSmallImage()!=null){
            imgPath = product.getProductSmallImage();
        }
        imgName = list.get(position).getProductName();
        holder.text_subtitle.setText(list.get(position).getProductName());
        holder.ll_pdetail_contair.removeAllViews();
        List<ImgInfo> imgs =list.get(position).getProductImages();
        if (imgs!=null&&imgs.size()>0){
            for (int i = 0;i<imgs.size();i++){
                foList.add(ServerUrl.mainUrl + imgs.get(i).getFilePath());
            }
        }
        List<String> keysList = list.get(position).getProductKeys();
        if (keysList!=null&&keysList.size()>0){
            keyList.clear();
            for (int i = 0;i<keysList.size();i++){
                String key = String.valueOf(keysList.get(i));
                keyList.add(key);
            }
            gridViewkeyAdapter = new GridViewkeyAdapter(keyList,context);
            holder.grid_product_key.setNumColumns(4);
            holder.grid_product_key.setAdapter(gridViewkeyAdapter);
        }
        CrosheViewPageView crosheViewPageView = new CrosheViewPageView(context, foList, 235);
        holder.ll_pdetail_contair.addView(crosheViewPageView);
        holder.text_price1.setText(list.get(position).getProductPrice());


        Collocation collocation= product.getCollocation();
        if(null!=collocation){
            if (!"0".equals(collocation.getCountPackage())){
                holder.text_pack_num.setText(collocation.getCountPackage());
                final List<PackageModel> models = collocation.getPackageModels();
                if (models!=null&&models.size()>0){
                    holder.text_pack_piece.setText(models.get(0).getPackagePrice());
                    holder.text_pack_save.setText(models.get(0).getPackageSave());
                    List<PackageDetailsModel> detailsModels = models.get(0).getPackageDetailsModels();
                    if (detailsModels!=null&&detailsModels.size()>0){
                        ProductInfo product1 = detailsModels.get(0).getProduct();
                        String imgpath = product1.getProductSmallImage();
                        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath,holder.img_pack1, AppContext.image_display_options);
                        if (detailsModels.size()>=2){
                            holder.img_pack2.setVisibility(View.VISIBLE);
                            holder.img_add2.setVisibility(View.VISIBLE);
                            ProductInfo product2 = detailsModels.get(1).getProduct();
                            String imgpath2 = product2.getProductSmallImage();
                            ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath2,holder.img_pack2, AppContext.image_display_options);
                        }
                        holder.text_pro_num.setText(detailsModels.size()+"");
                    }
                }
                holder.ll_pack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, ScientificCollocationActivity.class)
                                .putExtra("productId", productId));
                    }
                });
            }else {
                holder.ll_pack.setVisibility(View.GONE);
            }
        }

        List<CommentModel> comments = list.get(position).getCommentlist();
        if (comments!=null&&comments.size()>0){
            CommentModel comment = comments.get(0);
            holder.ll_eval_top.setVisibility(View.VISIBLE);
            if (comment!=null){
                holder.text_goodrate.setText(list.get(position).getRate());
                holder.text_buy_name.setText(comment.getUserNickName());
                holder.text_buy_eval.setText(comment.getCommentContent());
                holder.text_buy_time.setText(comment.getCommentDateTime());
                float rate = Float.parseFloat(comment.getCommentLevel());
                holder.rating_comment_p.setRating(rate);
                String img = comment.getUserHeadImg();
                ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+img,holder.img_buyer_head,AppContext.image_display_options);
                if (comment.getCommentImages()!=null&&comment.getCommentImages().size()>0){
                    List<Images> commentImg = comment.getCommentImages();
                    if (commentImg.size()>=1){
                        String evalimg1 = commentImg.get(0).getFilePath();
                        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+evalimg1,holder.img_eavl1,AppContext.image_display_options);
                        if (commentImg.size()>=2){
                            String evalimg2 = commentImg.get(1).getFilePath();
                            ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+evalimg2,holder.img_eavl2,AppContext.image_display_options);
                            if (commentImg.size()==3){
                                String evalimg3 = commentImg.get(2).getFilePath();
                                ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+evalimg3,holder.img_eavl3,AppContext.image_display_options);
                            }
                        }
                    }
                }else {
                    holder.ll_img_eavl.setVisibility(View.GONE);
                }
            }
        }else {
            holder.ll_eval.setVisibility(View.GONE);
            holder.ll_alleval.setVisibility(View.GONE);
            holder.ll_uneval.setVisibility(View.VISIBLE);
        }

        final List<ProductInfo> proList = list.get(position).getProductList();
        if (proList!=null&&proList.size()>0){
            String imgpath = proList.get(0).getProductSmallImage();
            ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath,holder.imageView1,AppContext.image_display_options);
            holder.text_recommend1.setText(proList.get(0).getProductName());
            holder.tj_price1.setText("￥"+String.valueOf(proList.get(0).getProductPrice()));
            String imgpath1 = proList.get(1).getProductSmallImage();
            ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath1,holder.imageView2,AppContext.image_display_options);
            holder.text_recommend2.setText(proList.get(1).getProductName());
            holder.tj_price2.setText("￥"+String.valueOf(proList.get(1).getProductPrice()));
            String imgpath2 = proList.get(2).getProductSmallImage();
            ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath2,holder.imageView3,AppContext.image_display_options);
            holder.text_recommend3.setText(proList.get(2).getProductName());
            holder.tj_price3.setText("￥"+String.valueOf(proList.get(2).getProductPrice()));
        }
        //查看商品的图文详情
        holder.ll_pic_w_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProductPWDetailActivity.class).putExtra("productId",productId));
            }
        });
        //查看商品详情的全部评论
        holder.ll_alleval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ShowAllEvaluateActivity.class).putExtra("productId",productId));
            }
        });
        holder.ll_tj1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (proList!=null&&proList.size()>0){
                    context.startActivity(new Intent(context, ProductDetailsActivity.class).putExtra("productId",String.valueOf(proList.get(0).getProductId())));
                }
            }
        });
        holder.ll_tj2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (proList!=null&&proList.size()>0){
                    context.startActivity(new Intent(context, ProductDetailsActivity.class).putExtra("productId",String.valueOf(proList.get(1).getProductId())));
                }
            }
        });
        holder.ll_tj3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (proList!=null&&proList.size()>0){
                    context.startActivity(new Intent(context, ProductDetailsActivity.class).putExtra("productId",String.valueOf(proList.get(2).getProductId())));
                }
            }
        });

//        holder.img_eavl1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String[] strImg = new String[list.size()];
//                for (int i = 0; i < list.size(); i++) {
//                    strImg[i] = ServerUrl.mainUrl+list.get(i).getFilePath();
//                }
//                AIntent.startShowImage(context,strImg);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
    public class PdetailHeadView extends RecyclerView.ViewHolder{
        LinearLayout ll_img_eavl,ll_eval,ll_alleval,ll_uneval,ll_pdetail_contair,ll_pack;
        TextView text_subtitle,text_price1,text_price2,text_pack_num,
                text_pack_save,text_pack_piece,text_pro_num,text_patname;
        ImageView img_pack1,img_pack2,img_add1,img_add2;
        ImageView img_buyer_head;
        MyGridView grid_product_key;
        private TextView text_buy_name,text_buy_time,text_buy_eval,text_goodrate;
        private ImageView imageView1,imageView2,imageView3,img_eavl1,img_eavl2,img_eavl3;
        private LinearLayout ll_pic_w_detail, ll_tj1,ll_tj2,ll_tj3,ll_eval_top;
        TextView text_recommend1,text_recommend2,text_recommend3,tj_price1,tj_price2,tj_price3;
        private RatingBar rating_comment_p;

        public PdetailHeadView(View itemView) {
            super(itemView);
            text_buy_name = (TextView) itemView.findViewById(R.id.text_buy_name);
            text_buy_time = (TextView) itemView.findViewById(R.id.text_buy_time);
            text_buy_eval = (TextView) itemView.findViewById(R.id.text_buy_eval);
            text_goodrate = (TextView) itemView.findViewById(R.id.text_goodrate);
            ll_pdetail_contair = (LinearLayout) itemView.findViewById(R.id.ll_pdetail_contair);
            ll_pack = (LinearLayout) itemView.findViewById(R.id.ll_pack);
            ll_eval_top = (LinearLayout) itemView.findViewById(R.id.ll_eval_top);
            text_subtitle = (TextView) itemView.findViewById(R.id.text_subtitle);
            text_price1 = (TextView) itemView.findViewById(R.id.text_price1);
            text_price2 = (TextView) itemView.findViewById(R.id.text_price2);
            text_patname = (TextView) itemView.findViewById(R.id.text_patname);
            text_pack_num = (TextView) itemView.findViewById(R.id.text_pack_num);
            text_pack_save = (TextView) itemView.findViewById(R.id.text_pack_save);
            text_pack_piece = (TextView) itemView.findViewById(R.id.text_pack_piece);
            text_pro_num = (TextView) itemView.findViewById(R.id.text_pro_num);

            img_pack1 = (ImageView) itemView.findViewById(R.id.img_pack1);
            img_pack2 = (ImageView) itemView.findViewById(R.id.img_pack2);
            img_add1 = (ImageView) itemView.findViewById(R.id.img_add1);
            img_add2 = (ImageView) itemView.findViewById(R.id.img_add2);

            img_buyer_head = (ImageView) itemView.findViewById(R.id.img_buyer_head);
            grid_product_key = (MyGridView) itemView.findViewById(R.id.grid_product_key);
            ll_alleval = (LinearLayout) itemView.findViewById(R.id.ll_alleval);
            ll_uneval = (LinearLayout) itemView.findViewById(R.id.ll_uneval);
            ll_img_eavl = (LinearLayout) itemView.findViewById(R.id.ll_img_eavl);
            ll_eval = (LinearLayout) itemView.findViewById(R.id.ll_eval);
            text_recommend1 = (TextView) itemView.findViewById(R.id.text_recommend1);
            text_recommend2 = (TextView) itemView.findViewById(R.id.text_recommend2);
            text_recommend3 = (TextView) itemView.findViewById(R.id.text_recommend3);
            rating_comment_p = (RatingBar) itemView.findViewById(R.id.rating_comment_p);
            tj_price1 = (TextView) itemView.findViewById(R.id.text_tj_price1);
            tj_price2 = (TextView) itemView.findViewById(R.id.text_tj_price2);
            tj_price3 = (TextView) itemView.findViewById(R.id.text_tj_price3);

            ll_tj1 = (LinearLayout) itemView.findViewById(R.id.ll_tj1);
            ll_tj2 = (LinearLayout) itemView.findViewById(R.id.ll_tj2);
            ll_tj3 = (LinearLayout) itemView.findViewById(R.id.ll_tj3);
            img_eavl1 = (ImageView) itemView.findViewById(R.id.img_eavl1);
            img_eavl2 = (ImageView) itemView.findViewById(R.id.img_eavl2);
            img_eavl3 = (ImageView) itemView.findViewById(R.id.img_eavl3);

            ll_pic_w_detail = (LinearLayout) itemView.findViewById(R.id.ll_pic_w_detail);

            imageView1 = (ImageView) itemView.findViewById(R.id.img_tui1);
            imageView2 = (ImageView) itemView.findViewById(R.id.img_tui2);
            imageView3 = (ImageView) itemView.findViewById(R.id.img_tui3);
        }
    }
}
