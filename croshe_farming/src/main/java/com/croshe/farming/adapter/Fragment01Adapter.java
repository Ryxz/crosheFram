package com.croshe.farming.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheViewPageView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.NewsInfo;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.Entity.ScrollFigureInFo;
import com.croshe.farming.Entity.SignListEntity;
import com.croshe.farming.PersonalCenter.SignListActivity;
import com.croshe.farming.R;
import com.croshe.farming.View.AutoPollRecyclerView;
import com.croshe.farming.activity.FramMessageActivity;
import com.croshe.farming.activity.FramMessageDetailActivity;
import com.croshe.farming.activity.ProductAcivity;
import com.croshe.farming.activity.ProductDetailsActivity;
import com.croshe.farming.farm.FarmViewActivity;
import com.croshe.farming.farm.PackageRecommendedActivity;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.LogRecord;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/5/24.
 */
//首页
public class Fragment01Adapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int headview = 0;//头部
    private int contentview = 1;//内容
    //    滚动图
    private List<String> foList = new ArrayList<>();
    //    农场头条
    private List<NewsInfo> Newinfo = new ArrayList<>();
    private List<ProductInfo> list = new ArrayList<>();
    Context context;
    List<NewsInfo> foLists = new ArrayList<>();

    public Fragment01Adapter(Context context, List<ProductInfo> list) {
        this.context = context;
        this.list = list;
        getIndexActivity();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == headview) {
            View headView = LayoutInflater.from(context).inflate(R.layout.item_fragment01_headview, parent, false);
            return new Fragment01HeadView(headView);
        } else if (viewType == contentview) {
            View contextview = LayoutInflater.from(context).inflate(R.layout.item_fragmen01_context, parent, false);
            return new Fragment01ContextView(contextview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof Fragment01HeadView) {
            ((Fragment01HeadView) holder).ll_viewpage.removeAllViews();
            //轮播图
            final CrosheViewPageView crosheViewPageView = new CrosheViewPageView(context, foList, 150);
            ((Fragment01HeadView) holder).ll_viewpage.addView(crosheViewPageView);
            Map<String, Object> map = new HashMap<>();
            ServerRequest.requestHttp(context, ServerUrl.getNewsTop, map, "", new ServerResultListener() {
                @Override
                public void onSuccess(String json, String msg) {
                    foLists = JSON.parseArray(json, NewsInfo.class);
                    Newinfo.clear();
                    if (!StringUtils.isEmpty(foLists.toString())) {
                        Newinfo.addAll(foLists);
                        ((Fragment01HeadView) holder).tv_tltie.setText(Newinfo.get(0).getNewsTitle());
                        ((Fragment01HeadView) holder).tv_tltie_1.setText(Newinfo.get(1).getNewsTitle());
                    }
                }

                @Override
                public void onFailure(String msg) {

                }
            });
            ((Fragment01HeadView) holder).tv_tltie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, FramMessageDetailActivity.class).putExtra("newsurl",foLists.get(0).getNewsUrl()));
                }
            });
            ((Fragment01HeadView) holder).tv_tltie_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, FramMessageDetailActivity.class).putExtra("newsurl",foLists.get(1).getNewsUrl()));
                }
            });


            AppContext.getInstance().displayImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1165964641,356468177&fm=200&gp=0.jpg", ((Fragment01HeadView) holder).circleImageView1);
            AppContext.getInstance().displayImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1165964641,356468177&fm=200&gp=0.jpg", ((Fragment01HeadView) holder).circleImageView2);
            AppContext.getInstance().displayImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1165964641,356468177&fm=200&gp=0.jpg", ((Fragment01HeadView) holder).circleImageView3);
            AppContext.getInstance().displayImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1165964641,356468177&fm=200&gp=0.jpg", ((Fragment01HeadView) holder).circleImageView4);
            AppContext.getInstance().displayImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495718416360&di=d68f47b484d8b9e3cd88a15a20c06149&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F40%2F79%2F77K58PIC7xI_1024.jpg", ((Fragment01HeadView) holder).home_imageview1);
            AppContext.getInstance().displayImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495718416360&di=d68f47b484d8b9e3cd88a15a20c06149&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F40%2F79%2F77K58PIC7xI_1024.jpg", ((Fragment01HeadView) holder).home_imageview2);
            AppContext.getInstance().displayImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495718416360&di=d68f47b484d8b9e3cd88a15a20c06149&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F40%2F79%2F77K58PIC7xI_1024.jpg", ((Fragment01HeadView) holder).home_imageview3);
            AppContext.getInstance().displayImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495718416360&di=d68f47b484d8b9e3cd88a15a20c06149&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F40%2F79%2F77K58PIC7xI_1024.jpg", ((Fragment01HeadView) holder).home_imageview4);
//           //联系客服
             ((Fragment01HeadView) holder).ll_fragment01_service.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     showGlobal();

                 }
             });
//            查看我的农场
            ((Fragment01HeadView) holder).ll_fragment01_findfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 Intent in = new Intent(context, FarmViewActivity.class);
                    context.startActivity(in);
                }
            });
//            推荐套餐
            ((Fragment01HeadView) holder).ll_fragment01_package.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context, PackageRecommendedActivity.class);
                    context.startActivity(in);
                }
            });
            //农场资讯
            ((Fragment01HeadView) holder).ll_fragment01_firminformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, FramMessageActivity.class));
                }
            });
            //查看签到
            ((Fragment01HeadView) holder).ll_fragment01_signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, SignListActivity.class));
                }
            });
            ((Fragment01HeadView) holder).ll_t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ProductAcivity.class));
                }
            });
            ((Fragment01HeadView) holder).ll_t2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ProductAcivity.class));
                }
            });
            ((Fragment01HeadView) holder).ll_t3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ProductAcivity.class));
                }
            });
            ((Fragment01HeadView) holder).ll_t4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ProductAcivity.class));
                }
            });


        } else if (holder instanceof Fragment01ContextView) {
            ((Fragment01ContextView) holder).tv_goods_name.setText(list.get(position).getProductName());
            AppContext.getInstance().displayImage(ServerUrl.mainUrl+list.get(position).getProductSmallImage(), ((Fragment01ContextView) holder).image_goods);
            ((Fragment01ContextView) holder).tv_goods_price.setText("￥"+list.get(position).getProductPrice());
            ((Fragment01ContextView) holder).tv_goods_evaluate.setText(list.get(position).getCount()+"条评论");
            if (StringUtils.isEmpty(list.get(position).getRate())){
                ((Fragment01ContextView) holder).tv_goods_rate.setText(0+"%"+"好评");
            }else {
                ((Fragment01ContextView) holder).tv_goods_rate.setText(list.get(position).getRate()+"%"+"好评");
            }
            ((Fragment01ContextView) holder).btu_type.setText(list.get(position).getTypeName());
            ((Fragment01ContextView) holder).ll_context.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ProductDetailsActivity.class).putExtra("productId",String.valueOf(list.get(position).getProductId())));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            //头部
            return headview;
        } else {
            //内容
            return contentview;

        }

    }

    public class Fragment01HeadView extends RecyclerView.ViewHolder {
        public TextView tv_tltie,tv_tltie_1;
        public LinearLayout ll_viewpage,ll_fragment01_service,ll_fragment01_findfirm,ll_fragment01_package,ll_fragment01_signin,ll_fragment01_firminformation;
        private CircleImageView circleImageView1, circleImageView2, circleImageView3, circleImageView4;
        private ImageView home_imageview1, home_imageview2, home_imageview3, home_imageview4;
        private LinearLayout ll_t1,ll_t2,ll_t3,ll_t4;
        public Fragment01HeadView(View itemView) {
            super(itemView);
            tv_tltie= (TextView) itemView.findViewById(R.id.tv_title);
            tv_tltie_1= (TextView) itemView.findViewById(R.id.tv_title1);
            ll_viewpage = (LinearLayout) itemView.findViewById(R.id.fragment01_viewpage);
            circleImageView1 = (CircleImageView) itemView.findViewById(R.id.circleImageView01);
            circleImageView2 = (CircleImageView) itemView.findViewById(R.id.circleImageView02);
            circleImageView3 = (CircleImageView) itemView.findViewById(R.id.circleImageView03);
            circleImageView4 = (CircleImageView) itemView.findViewById(R.id.circleImageView04);
            home_imageview1 = (ImageView) itemView.findViewById(R.id.home_imageView1);
            home_imageview2 = (ImageView) itemView.findViewById(R.id.home_imageView2);
            home_imageview3 = (ImageView) itemView.findViewById(R.id.home_imageView3);
            home_imageview4 = (ImageView) itemView.findViewById(R.id.home_imageView4);
            ll_fragment01_service= (LinearLayout) itemView.findViewById(R.id.ll_fragment01_service);
            ll_fragment01_findfirm= (LinearLayout) itemView.findViewById(R.id.ll_fragment01_findfirm);
            ll_fragment01_package= (LinearLayout) itemView.findViewById(R.id.ll_fragment01_package);
            ll_fragment01_signin= (LinearLayout) itemView.findViewById(R.id.ll_fragment01_signin);
            ll_fragment01_firminformation= (LinearLayout) itemView.findViewById(R.id.ll_fragment01_firminformation);

            ll_t1 = (LinearLayout)itemView.findViewById(R.id.ll_t1);
            ll_t2 = (LinearLayout)itemView.findViewById(R.id.ll_t2);
            ll_t3 = (LinearLayout)itemView.findViewById(R.id.ll_t3);
            ll_t4 = (LinearLayout)itemView.findViewById(R.id.ll_t4);

        }
    }

    public class Fragment01ContextView extends RecyclerView.ViewHolder {
        private LinearLayout ll_context;
        private TextView tv_goods_name, tv_goods_price, tv_goods_evaluate, tv_goods_rate;
        private ImageView image_goods;
        private Button btu_type;

        public Fragment01ContextView(View itemView) {
            super(itemView);
            ll_context = (LinearLayout) itemView.findViewById(R.id.ll_fragment01_context);
            tv_goods_name = (TextView) itemView.findViewById(R.id.text_goods_name);
            tv_goods_price = (TextView) itemView.findViewById(R.id.text_goods_price);
            tv_goods_evaluate = (TextView) itemView.findViewById(R.id.text_goods_evaluate);
            tv_goods_rate = (TextView) itemView.findViewById(R.id.text_goods_rate);
            image_goods = (ImageView) itemView.findViewById(R.id.image_goods);
            btu_type= (Button) itemView.findViewById(R.id.btu_type);
        }
    }

    //滚动条
    public void getIndexActivity() {
        Map<String, Object> map = new HashMap<>();
        ServerRequest.requestHttp(context, ServerUrl.getIndexActivity, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<ScrollFigureInFo> foLists = new ArrayList<>();
                foLists = JSON.parseArray(json, ScrollFigureInFo.class);
                foList.clear();
                if (!StringUtils.isEmpty(foLists.toString())) {
                    for (ScrollFigureInFo info : foLists) {
                        foList.add(ServerUrl.mainUrl + info.getAdvertImage());
                    }
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(String msg) {

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



}





