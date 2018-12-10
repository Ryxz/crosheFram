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
import android.widget.Toast;

import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.BasketInfo;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerUrl;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class AgriculturalAdapter01 extends RecyclerView.Adapter<AgriculturalAdapter01.ViewHLoder> {
    private List<BasketInfo> list;
    private Context context;
    private boolean xzCheck = false;
    private List<Integer> numbers = new ArrayList<>();
    private List<String> storeIds = new ArrayList<>();
    private List<ProductInfo> products = new ArrayList<>();
    public AgriculturalAdapter01(List<BasketInfo> list, Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public AgriculturalAdapter01.ViewHLoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_agricultural01,viewGroup,false);
        ViewHLoder hLoder = new ViewHLoder(view);
        return hLoder;
    }

    @Override
    public void onBindViewHolder(final AgriculturalAdapter01.ViewHLoder viewHLoder, final int i) {
        final ProductInfo productInfo = list.get(i).getProduct();
        if(null!=list.get(i).getProduct().getProductSmallImage()&&list.get(i).getProduct().getProductSmallImage().length()>0) {
            AppContext.getInstance().displayImage(ServerUrl.mainUrl + list.get(i).getProduct().getMatureImage(), viewHLoder.iv_agr);
        }
        viewHLoder.tv_agr_1.setText(list.get(i).getProduct().getProductName());
        viewHLoder.tv_agr.setText("数量："+String.valueOf(list.get(i).getNumber())+list.get(i).getNumberUnitStr());
        viewHLoder.tv_agr_2.setText("重量："+list.get(i).getWeight()+list.get(i).getWeightUnitStr());
        viewHLoder.market_number.setText(String.valueOf(list.get(i).getProduct().getScNum()));
        viewHLoder.ll_market_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numbes = list.get(i).getProduct().getScNum();
                if (numbes>= (list.get(i).getNumber())){
                    Toast.makeText(context,"已经是最大数量，不能再加了",Toast.LENGTH_SHORT).show();
                }else {
                    numbes ++;
                    list.get(i).getProduct().setScNum(numbes);
                }
                checkPs();
                notifyItemChanged(i);
            }
        });
        viewHLoder.ll_market_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numbes = list.get(i).getProduct().getScNum();
                if (numbes<=1){
                    Toast.makeText(context,"已经是最少数量，不能再减了",Toast.LENGTH_SHORT).show();
                }else {
                    numbes--;
                    list.get(i).getProduct().setScNum(numbes);
                }
                checkPs();
                notifyItemChanged(i);
            }
        });

        if (!productInfo.isCheck()){
            viewHLoder.btu_buy.setVisibility(View.VISIBLE);
            viewHLoder.btu_choose_shou.setVisibility(View.GONE);
        }else {
            viewHLoder.btu_buy.setVisibility(View.GONE);
            viewHLoder.btu_choose_shou.setVisibility(View.VISIBLE);
        }
        viewHLoder.ll_choose_ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!xzCheck){
                    productInfo.setCheck(true);
                    xzCheck = true;
                }else {
                    productInfo.setCheck(false);
                    xzCheck = false;
                }
                checkPs();
                checkIf();
                notifyDataSetChanged();
            }
        });
        checkIf();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void checkIf(){
        boolean ischeck = true;
        for (int i = 0;i<list.size();i++){
            if (list.get(i).getProduct()!=null){
                if (!list.get(i).getProduct().isCheck()){

                }else {
                    ischeck = false;
                    EventBus.getDefault().post("canDeliver");
                }
            }
        }
        if (ischeck){
            EventBus.getDefault().post("noDeliver");
        }
    }

    public void checkPs(){
        numbers.clear();
        storeIds.clear();
        products.clear();
        for (int i = 0;i<list.size();i++){
            if (list.get(i).getProduct().isCheck()){
                numbers.add(list.get(i).getProduct().getScNum());
                storeIds.add(list.get(i).getStoreId());
                products.add(list.get(i).getProduct());
            }
        }
        context.sendBroadcast(new Intent("wareHouseShCh")
                .putExtra("numbers", (Serializable) numbers)
                .putExtra("storeIds", (Serializable) storeIds)
                .putExtra("products", (Serializable) products));
    }
    static class ViewHLoder extends RecyclerView.ViewHolder {
        ImageView iv_agr;
        private TextView tv_agr,tv_agr_1,tv_agr_2,market_number;
        private ImageView btu_buy,btu_choose_shou;
        private LinearLayout ll_choose_ps,ll_market_jian,ll_market_jia;
        public ViewHLoder(View itemView) {
            super(itemView);
            iv_agr= (ImageView) itemView.findViewById(R.id.iv_shou);
            tv_agr= (TextView) itemView.findViewById(R.id.tv_shou);
            tv_agr_1= (TextView) itemView.findViewById(R.id.tv_shou_1);
            tv_agr_2= (TextView) itemView.findViewById(R.id.tv_shou_2);
            btu_buy= (ImageView) itemView.findViewById(R.id.btu_shou);
            btu_choose_shou = (ImageView) itemView.findViewById(R.id.btu_choose_shou);
            ll_choose_ps= (LinearLayout) itemView.findViewById(R.id.ll_choose_ps);
            ll_market_jian = (LinearLayout) itemView.findViewById(R.id.ll_market_jian);
            ll_market_jia = (LinearLayout) itemView.findViewById(R.id.ll_market_jia);
            market_number = (TextView) itemView.findViewById(R.id.text_market_number);
        }
    }

}
