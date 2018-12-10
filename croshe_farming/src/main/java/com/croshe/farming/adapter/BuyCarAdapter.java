package com.croshe.farming.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.Toasts;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.BuyCarInfo;
import com.croshe.farming.Entity.CarEntity;
import com.croshe.farming.R;
import com.croshe.farming.activity.ProductDetailsActivity;
import com.croshe.farming.fragment.MarketFragment01;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.croshe.farming.server.ServerUrl.updateShopCar;
import static java.lang.Boolean.parseBoolean;

/* 购物车
 * Created by Administrator on 2017/6/7.
 */
public class BuyCarAdapter extends RecyclerView.Adapter<BuyCarAdapter.ViewHloder> {
    private List<BuyCarInfo> list;
    private Context context;
    private int goodsnumber1 = 1;
    private int number=0;
    private String count;
    List<String> price = new ArrayList<>();//总价
    List<String> id = new ArrayList<>();//被选中的商品ID
    public BuyCarAdapter( List<BuyCarInfo> list,Context context){
        this.list=list;
        this.context=context;
    }

    @Override
    public BuyCarAdapter.ViewHloder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_buy_car,viewGroup,false);
        return new ViewHloder(view);
    }

    @Override
    public void onBindViewHolder(final BuyCarAdapter.ViewHloder viewHloder, final int i) {

        final String carId = list.get(i).getCarId();
        viewHloder.et_number.setText(list.get(i).getProductCount()+"");
        viewHloder.text_product_num.setText("x "+list.get(i).getProductCount()+"");
        viewHloder.et_number.setFocusableInTouchMode(false);
        //编辑商品的数量
        viewHloder.ll_change_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHloder.ll_view_01.setVisibility(View.GONE);
                viewHloder.ll_view_02.setVisibility(View.VISIBLE);
                viewHloder.et_number.setText(list.get(i).getProductCount()+"");
            }
        });
        //可否编辑
        viewHloder.et_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHloder.et_number.setFocusableInTouchMode(true);
            }
        });
        //完成数量
        viewHloder.text_qdsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHloder.ll_view_01.setVisibility(View.VISIBLE);
                viewHloder.ll_view_02.setVisibility(View.GONE);
                viewHloder.et_number.setFocusableInTouchMode(false);
                final String number = viewHloder.et_number.getText().toString();
                int num = Integer.parseInt(number);
                HttpRequest.updateShopCar(context, list.get(i).getCarId(), num, new ServerResultListener() {
                    @Override
                    public void onSuccess(String json, String msg) {
                        CarEntity buycar = JSON.parseObject(json,CarEntity.class);
                        if (buycar!=null){
                            list.get(i).setProductCount(buycar.getProductCount());
                            viewHloder.text_product_num.setText("x "+number);
                            notifyItemChanged(i);
                            checkIsQx();
                            summary();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });
            }
        });
        //减
        viewHloder.ll_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(list.get(i).getProductCount());
                if (num>1){
                    num--;
                    HttpRequest.updateShopCar(context, list.get(i).getCarId(), num, new ServerResultListener() {
                        @Override
                        public void onSuccess(String json, String msg) {
                            CarEntity buycar = JSON.parseObject(json,CarEntity.class);
                            if (buycar!=null){
                                list.get(i).setProductCount(buycar.getProductCount());
                                viewHloder.et_number.setText(list.get(i).getProductCount()+"");
                            }
                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });
                }else {
                    Toast.makeText(context,"不能再减了",Toast.LENGTH_SHORT).show();
                }

            }
        });
        //加
        viewHloder.ll_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(list.get(i).getProductCount());
                num++;
                HttpRequest.updateShopCar(context, list.get(i).getCarId(), num, new ServerResultListener() {
                    @Override
                    public void onSuccess(String json, String msg) {
                        CarEntity buycar = JSON.parseObject(json,CarEntity.class);
                        if (buycar!=null){
//                            count = "0";
//                            count = buycar.getProductCount();
//                            list.get(i).setNum(Integer.parseInt(count));
                            list.get(i).setProductCount(buycar.getProductCount());
                            viewHloder.et_number.setText(list.get(i).getProductCount()+"");
//                            notifyItemChanged(i);
//                            checkIsQx();
//                            summary();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });
            }
        });
        AppContext.getInstance().displayImage(ServerUrl.mainUrl+list.get(i).getImg(),viewHloder.iv_buy);
        viewHloder.tv_buy_1.setText(list.get(i).getName());
        if(null!=list.get(i).getStandard()&&list.get(i).getStandard().toString().length()>0){
            viewHloder.tv_buy_2.setText("规格:"+list.get(i).getStandard().getStandardName());
            viewHloder.text_buy_02.setText("规格:"+list.get(i).getStandard().getStandardName());
        }

        viewHloder.tv_buy_3.setText("￥"+list.get(i).getProductPrice());


        if(list.get(i).isselecte()){
            viewHloder.iv_xuanze.setImageResource(R.mipmap.xuanzhong);
        }else{
            viewHloder.iv_xuanze.setImageResource(R.mipmap.weixuanzhong);
        }
        viewHloder.iv_xuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.get(i).isselecte()) {
                    list.get(i).setIsselecte(true);
                    checkIsQx();
                    summary();
                    notifyDataSetChanged();
                }else{
                    list.get(i).setIsselecte(false);
                    checkIsQx();
                    summary();
                    notifyDataSetChanged();
                }

            }
        });
        viewHloder.iv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProductDetailsActivity.class)
                        .putExtra("productId",String.valueOf(list.get(i).getProduct().getProductId())));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHloder extends RecyclerView.ViewHolder {
        private ImageView iv_xuanze,iv_buy;
        private TextView tv_buy_3,tv_buy_1,tv_buy_2,text_qdsl,text_buy_02,text_product_num;
        private LinearLayout ll_jian,ll_jia,ll_change_num,ll_view_02,ll_view_01;
        private EditText et_number;
        public ViewHloder(View itemView) {
            super(itemView);
            iv_xuanze= (ImageView) itemView.findViewById(R.id.iv_xuanze);
            iv_buy= (ImageView) itemView.findViewById(R.id.iv_buy);
            text_buy_02 = (TextView) itemView.findViewById(R.id.text_buy_02);
            text_product_num = (TextView) itemView.findViewById(R.id.text_product_num);
            tv_buy_1= (TextView) itemView.findViewById(R.id.tv_buy_1);
            tv_buy_2= (TextView) itemView.findViewById(R.id.tv_buy_2);
            tv_buy_3= (TextView) itemView.findViewById(R.id.tv_buy_3);
            text_qdsl = (TextView) itemView.findViewById(R.id.text_qdsl);
            ll_jian= (LinearLayout) itemView.findViewById(R.id.ll_jian);
            ll_jia= (LinearLayout) itemView.findViewById(R.id.ll_jia);
            ll_change_num= (LinearLayout) itemView.findViewById(R.id.ll_change_num);
            ll_view_02= (LinearLayout) itemView.findViewById(R.id.ll_view_02);
            ll_view_01= (LinearLayout) itemView.findViewById(R.id.ll_view_01);
            et_number= (EditText) itemView.findViewById(R.id.et_number);
        }
    }

    public void  getboole(boolean select){
           for(BuyCarInfo fo:list){
               fo.setIsselecte(select);
           }
        checkIsQx();
        summary();
        notifyDataSetChanged();
    }
    public void updateShopCar(String carId,int  productCount){
        Map<String,Object> map = new HashMap<>();
        map.put("carId",carId);
        map.put("productCount",productCount);
        ServerRequest.requestHttp(context, updateShopCar, map, "加载中", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                CarEntity buycar = JSON.parseObject(json,CarEntity.class);
                count = "0";
                count = buycar.getProductCount();
//                context.sendBroadcast(new Intent("onCloseMain3"));
                checkIsQx();
                summary();
            }
            @Override
            public void onFailure(String msg) {

            }
        });
    }

    public void summary(){
        number = 0;
        price.clear();
        id.clear();
        for( int a=0;a<list.size();a++) {
            if (list.get(a).isselecte()) {
                number++;
                id.add(list.get(a).getCarId()+"");
            }
        }
        context.sendBroadcast(new Intent("onCloseMain")
                .putExtra("number",number)
                .putStringArrayListExtra("id", (ArrayList<String>) id));
    }
    public void checkIsQx(){
        boolean isCheck = true;
        for (int n = 0; n <list.size();n++){
            if (list.get(n).isselecte()){

            }else {
                isCheck = false;
                context.sendBroadcast(new Intent("closeAll"));
            }
        }
        if (isCheck){
            context.sendBroadcast(new Intent("openAll"));
        }
    }
}
