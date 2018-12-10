package com.croshe.farming.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.BasketInfo;
import com.croshe.farming.R;
import com.croshe.farming.activity.ProductDetailsActivity;
import com.croshe.farming.server.ServerUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/7.
 */
//菜篮子
public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHloder> {
    private List<BasketInfo> list;
    private Context context;
    public  boolean isselect;
    private String price,imgpath;
    private String signprice,area;  //选择不同数量和面积
    private int foodCarId;
    private int carId;
    Map<String, BasketInfo> basketInfoMap =new HashMap<>();
    private List<Integer> carIds = new ArrayList<>();//菜篮子Id
    private List<String> areas = new ArrayList<>();//收获面积
    private List<Integer> nums = new ArrayList<>();//收获数量
    private List<Integer> harvestType = new ArrayList<>();//收获方式
    private int type;
    private String total; //总价
    private int numbers;
    private int number=0;
    private List<String> allprice = new ArrayList<>();
    private List<String> allmoney = new ArrayList<>();//所选择的全部的钱

    public BasketAdapter(List<BasketInfo> list, Context context){
        this.list=list;
        this.context=context;
        registerBoradcastReceiver();
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("changeArea");
        myIntentFilter.addAction("numchange");
        myIntentFilter.addAction("changeAdapter");

        // 注册广播
        context.registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("changeArea")) {
                signprice = intent.getStringExtra("signprice");
                area = intent.getStringExtra("area");
                foodCarId = intent.getIntExtra("foodCarId",0);
                notifyDataSetChanged();
                Summary();
            }else if (action.equals("numchange")){
                numbers = intent.getIntExtra("numbers",0);
                carId = intent.getIntExtra("CarId",0);
                total = intent.getStringExtra("total");
                notifyDataSetChanged();
                Summary();
            }else if (action.equals("changeAdapter")){
                for (int i = 0;i<list.size();i++){
                    list.get(i).setAllmoney(0.0);
                    list.get(i).setArea("-2");
                    list.get(i).setNum(0);
                }
                area = null;
                numbers = 0;
                signprice = null;
                total = null;
                foodCarId = -1;
                carId = -1;
                notifyDataSetChanged();
            }
        }
    };
    @Override
    public BasketAdapter.ViewHloder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_basket,viewGroup,false);
        return new ViewHloder(view);
    }

    @Override
    public void onBindViewHolder(final BasketAdapter.ViewHloder viewHloder, final int i) {
        if(null!=list.get(i).getProduct()&&list.get(i).getProduct().toString().length()>0) {
            viewHloder.tv_buy_1.setText(list.get(i).getProduct().getProductName());
            AppContext.getInstance().displayImage(ServerUrl.mainUrl + list.get(i).getProduct().getMatureImage(), viewHloder.iv_buy);
        }
        viewHloder.iv_xuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.get(i).isselecte()) {
                    list.get(i).setIsselecte(true);
                    checkIsQx();
                    Summary();
                    notifyDataSetChanged();
                }else{
                    list.get(i).setIsselecte(false);
                    checkIsQx();
                    Summary();
                    notifyDataSetChanged();
                }
            }
        });
        if(list.get(i).isselecte()){
            viewHloder.iv_xuanze.setImageResource(R.mipmap.xuanzhong);
        }else{
            viewHloder.iv_xuanze.setImageResource(R.mipmap.weixuanzhong);
        }


        //判断按照面积还是数量收获
        if (list.get(i).getHarvestedType()==0||list.get(i).getHarvestedType()==2){
            viewHloder.ll_basket.setVisibility(View.VISIBLE);
            viewHloder.ll_num_basket.setVisibility(View.GONE);
        }else if (list.get(i).getHarvestedType()==1||list.get(i).getHarvestedType()==3){
            viewHloder.ll_basket.setVisibility(View.GONE);
            viewHloder.ll_num_basket.setVisibility(View.VISIBLE);
        }
        //选择收货面积
        viewHloder.ll_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).getProduct()!=null){
                    imgpath = list.get(i).getProduct().getMatureImage();
                    price = String.valueOf(list.get(i).getProduct().getProductPrice());
                }
                context.sendBroadcast(new Intent("showArea")
                        .putExtra("imgpath",imgpath)
                        .putExtra("price",price)
                        .putExtra("foodCarId",list.get(i).getFoodCarId())
                        .putExtra("productId",list.get(i).getProductId())
                        .putExtra("harvestType",0)
                        .putExtra("harvestArea",list.get(i).getHarvestArea()));
            }
        });
        //选择收货数量
        viewHloder.ll_num_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).getProduct()!=null){
                    imgpath = list.get(i).getProduct().getMatureImage();
                    price = String.valueOf(list.get(i).getProduct().getProductPrice());
                }
                context.sendBroadcast(new Intent("chooseNum")
                        .putExtra("imgpath",imgpath)
                        .putExtra("price",price)
                        .putExtra("foodId",list.get(i).getFoodCarId())
                        .putExtra("harvestTyped",1)
                        .putExtra("harvestNumber",list.get(i).getHarvestNumber())
                        .putExtra("chooseJg",list.get(i).getNum()));
            }
        });
        //判断收获面积
//        String foodarea ;
        if (foodCarId == list.get(i).getFoodCarId()){
            if (!StringUtils.isEmpty(area)){
                list.get(i).setArea(area);
            }
            list.get(i).setAllmoney(Double.valueOf(signprice));
            if (list.get(i).isselecte()){
                Summary();
            }
        }
        if (list.get(i).getArea().equals("-2")){
            viewHloder.text_area.setText("选择收货面积");
        }else {
            viewHloder.text_area.setText(list.get(i).getArea()+"平米");
        }
        //判断收获数量
        if (carId == list.get(i).getFoodCarId()){
            if (numbers>0){
                list.get(i).setNum(numbers);
            }
            if (total!=null){
                list.get(i).setAllmoney(Double.valueOf(total));
                if (list.get(i).isselecte()){
                    Summary();
                }
            }
        }

        if (list.get(i).getNum()>0){
            viewHloder.text_basket_num.setText(String.valueOf(list.get(i).getNum())+"个");
        }else {
            viewHloder.text_basket_num.setText("选择收获数量");
        }
        if (list.get(i).getAllmoney()==null||list.get(i).getAllmoney() == 0.0){
            viewHloder.tv_buy_3.setText("￥"+String.valueOf(list.get(i).getHarvestPrice()));
        }else {
            viewHloder.tv_buy_3.setText("￥"+list.get(i).getAllmoney());
        }

        viewHloder.iv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProductDetailsActivity.class)
                        .putExtra("productId",String.valueOf(list.get(i).getProduct().getProductId())));
            }
        });


        for (int j = 0;j<list.size();j++){
            if (list.get(j).isselecte()){
                basketInfoMap.put(list.get(j).getFoodCarId()+"",list.get(j));
            }
        }
    }

    public void  getboole(boolean select){
        for(BasketInfo fo:list){
            fo.setIsselecte(select);
        }
        checkIsQx();
        Summary();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHloder extends RecyclerView.ViewHolder {
        ImageView iv_xuanze,iv_buy;
        TextView tv_buy_3,tv_buy_1,tv_basket,text_area,text_basket_num;
        LinearLayout ll_basket,ll_num_basket;
        public ViewHloder(View itemView) {
            super(itemView);
            ll_basket= (LinearLayout) itemView.findViewById(R.id.ll_basket);
            iv_xuanze= (ImageView) itemView.findViewById(R.id.iv_xuanze_1);
            iv_buy= (ImageView) itemView.findViewById(R.id.iv_buy_1);
            tv_buy_1= (TextView) itemView.findViewById(R.id.tv_buy_01);
            tv_buy_3= (TextView) itemView.findViewById(R.id.tv_buy_03);
            tv_basket= (TextView) itemView.findViewById(R.id.tv_basket);
            ll_num_basket= (LinearLayout) itemView.findViewById(R.id.ll_num_basket);
            text_area= (TextView) itemView.findViewById(R.id.text_area);
            text_basket_num = (TextView) itemView.findViewById(R.id.text_basket_num);
        }
    }



    public void Summary(){
        //收获
        carIds.clear();
        harvestType.clear();
        areas.clear();
        nums.clear();
        allmoney.clear();
        allprice.clear();
        number = 0;
        for( int a=0;a<list.size();a++) {
            if (list.get(a).isselecte()) {
                number++;
                if (list.get(a).getAllmoney()!=null&&list.get(a).getAllmoney()!=0.0){
                    allprice.add(String.valueOf(list.get(a).getAllmoney()));

                    //2017.12.19修改以下
                    allmoney.add(String.valueOf(list.get(a).getAllmoney()));
                    //以上
                }else {
                    allprice.add(String.valueOf(list.get(a).getHarvestPrice()));

                    //2017.12.19修改以下
                    allmoney.add(String.valueOf(list.get(a).getHarvestPrice()));
                    //以上
                }
                carIds.add(list.get(a).getFoodCarId());
                harvestType.add(list.get(a).getHarvestedType());
                areas.add(String.valueOf(list.get(a).getArea()));
                nums.add(list.get(a).getNum());
//                allmoney.add(String.valueOf(list.get(a).getAllmoney()));
            }
        }
        context.sendBroadcast(new Intent("seltce3")
                .putExtra("number", number)
                .putStringArrayListExtra("price", (ArrayList<String>) allprice)
                .putIntegerArrayListExtra("carIds", (ArrayList<Integer>) carIds)
                .putStringArrayListExtra("areas", (ArrayList<String>) areas)
                .putIntegerArrayListExtra("nums", (ArrayList<Integer>) nums)
                .putIntegerArrayListExtra("harvestType", (ArrayList<Integer>) harvestType)
                .putStringArrayListExtra("allmoney", (ArrayList<String>) allmoney));
    }
    //判断是否全部都选中
    public void checkIsQx(){
        boolean isCheck = true;
        for (int n = 0; n <list.size();n++){
            if (list.get(n).isselecte()){
            }else {
                isCheck = false;
                context.sendBroadcast(new Intent("closeselectall"));
            }
        }
        if (isCheck){
            context.sendBroadcast(new Intent("openselectall"));
        }
    }

}
