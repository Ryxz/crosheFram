package com.croshe.farming.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.BasketInfo;
import com.croshe.farming.Entity.OrderDeatils;
import com.croshe.farming.Entity.OrderInfo;
import com.croshe.farming.Entity.OrderModels;
import com.croshe.farming.R;
import com.croshe.farming.activity.CashierActivity;
import com.croshe.farming.adapter.BasketAdapter;
import com.croshe.farming.adapter.SeedsAdapter;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/5/25.
 */
//菜篮子
public class MarketFragment02 extends Fragment implements View.OnClickListener,BGARefreshLayout.BGARefreshLayoutDelegate{
    private BGARefreshLayout refresh_market_02;
    private RecyclerView recycler_market_02;
    private List<BasketInfo> basketInfoList = new ArrayList<>();
    private BasketAdapter adapter;
    private LinearLayout ll_baske,ll_harvest,ll_jian,ll_jia,ll_img_harvest,
            ll_show,ll_style1,ll_style2,ll_style3,ll_style4,ll_style5,//弹出框
            ll_area1,ll_area2,ll_area3,ll_area4,ll_area5,ll_choose_basket,ll_backgroud,ll_choose_basket_2;
    private ImageView iv_harvest,img_cancel,img_product,img_unc,img_c;
    private TextView tv_money_harvest,tv_number_harvest,text_choose_area,text_leftover,
            text_change_price,text_area1,text_area2,text_area3,text_area4,text_area5;
    private TextView text_change_num;
    private EditText et_number;
    private boolean isover = false;//是否全选
    private int harvestType,foodCarId,productId,foodId,harvestTyped;
//    private Double area;//面积
    private String area;
    private EditText edit_enter_area;
    private String signprice ="0";
    int numbers = 1;//加减
    private String price;
    private String total;
    private int tag ;
    private double money,allArea;//总价
    private int cannum = 1 ;//可以收货的数量
    private List<Integer> carIds = new ArrayList<>();//菜篮子Id
    private List<Double> harvestPrice = new ArrayList<>();//收获成本
    private List<Double> harvestArea = new ArrayList<>();//收获面积
    private List<Integer> nums = new ArrayList<>();//收获数量
    private List<Integer> harvestTypes = new ArrayList<>();//收获方式
    private List<Double> allmoney = new ArrayList<>();//所选择的全部的钱

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_market_02, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();
        registerBoradcastReceiver();
    }
    @Override
    public void onResume() {
        super.onResume();
        showUserFoodCar();
    }

    public void initView() {

        refresh_market_02= (BGARefreshLayout) getActivity().findViewById(R.id.refresh_market_02);
        refresh_market_02.setDelegate(this);
        refresh_market_02.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        edit_enter_area = (EditText) getActivity().findViewById(R.id.edit_enter_area);
        edit_enter_area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                int len = s.toString().length();
//                if (len == 1 && text.equals("0")) {
//                    s.clear();
//                }
            }
        });
        text_change_num = (TextView) getActivity().findViewById(R.id.text_change_num);

        text_leftover = (TextView) getActivity().findViewById(R.id.text_leftover);
        ll_harvest = (LinearLayout) getActivity().findViewById(R.id.ll_harvest);
        iv_harvest= (ImageView) getActivity().findViewById(R.id.iv_harvest);
        tv_money_harvest= (TextView) getActivity().findViewById(R.id.tv_money_harvest);
        tv_number_harvest= (TextView) getActivity().findViewById(R.id.tv_number_harvest);
        ll_baske= (LinearLayout) getActivity().findViewById(R.id.ll_baske);
        //用户菜篮子数据
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new BasketAdapter(basketInfoList,getActivity());
        recycler_market_02 = (RecyclerView) getActivity().findViewById(R.id.recycler_market_02);
        recycler_market_02.setAdapter(adapter);
        recycler_market_02.setLayoutManager(linearLayoutManager);
        //
        ll_img_harvest = (LinearLayout) getActivity().findViewById(R.id.ll_img_harvest);
        ll_choose_basket_2 = (LinearLayout) getActivity().findViewById(R.id.ll_choose_basket_2);
        text_choose_area= (TextView) getActivity().findViewById(R.id.text_choose_area);
        text_change_price= (TextView) getActivity().findViewById(R.id.text_change_price);
        ll_show= (LinearLayout) getActivity().findViewById(R.id.ll_show);
        ll_style1= (LinearLayout) getActivity().findViewById(R.id.ll_style1);
        ll_style2= (LinearLayout) getActivity().findViewById(R.id.ll_style2);
        ll_style3= (LinearLayout) getActivity().findViewById(R.id.ll_style3);
        ll_style4= (LinearLayout) getActivity().findViewById(R.id.ll_style4);
        ll_style5= (LinearLayout) getActivity().findViewById(R.id.ll_style5);
        ll_area1= (LinearLayout) getActivity().findViewById(R.id.ll_area1);
        ll_area2= (LinearLayout) getActivity().findViewById(R.id.ll_area2);
        ll_area3= (LinearLayout) getActivity().findViewById(R.id.ll_area3);
        ll_area4= (LinearLayout) getActivity().findViewById(R.id.ll_area4);
        ll_area5= (LinearLayout) getActivity().findViewById(R.id.ll_area5);
        img_cancel = (ImageView) getActivity().findViewById(R.id.img_cancel1);
        ll_choose_basket= (LinearLayout) getActivity().findViewById(R.id.ll_choose_basket);
        img_product = (ImageView) getActivity().findViewById(R.id.img_product);
        text_area1= (TextView) getActivity().findViewById(R.id.text_area1);
        text_area2= (TextView) getActivity().findViewById(R.id.text_area2);
        text_area3= (TextView) getActivity().findViewById(R.id.text_area3);
        text_area4= (TextView) getActivity().findViewById(R.id.text_area4);
        text_area5= (TextView) getActivity().findViewById(R.id.text_area5);

        img_unc = (ImageView) getActivity(). findViewById(R.id.img_unc);
        img_c = (ImageView)  getActivity().findViewById(R.id.img_c);
        ll_jian = (LinearLayout) getActivity().findViewById(R.id.ll_jian);
        ll_jia = (LinearLayout)  getActivity().findViewById(R.id.ll_jia);
        ll_backgroud= (LinearLayout) getActivity().findViewById(R.id.ll_backgroud);
        ll_backgroud.setOnClickListener(this);
        et_number = (EditText) getActivity().findViewById(R.id.et_number);
        et_number.setSelection(et_number.getText().length());
        et_number.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                String text = s.toString();
                int len = s.toString().length();
                if (len == 1 && text.equals("0")) {
                    s.clear();
                }else {
                    if (!StringUtils.isEmpty(s.toString())){
                        int mm = Integer.parseInt(text);
                        if(mm>cannum){
                            Toast.makeText(getActivity(), "超过收货数量",Toast.LENGTH_SHORT).show();
                        }else {
                            HarvestCost(mm);
                        }
                    }
                }
            }
        });
        //在一开始声明TextWatcher，在afterTextChange内操作
        String number = String.valueOf(numbers);
//        et_number.setText(number);
    }

    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("seltce1");
        myIntentFilter.addAction("seltce2");
        myIntentFilter.addAction("seltce3");
        myIntentFilter.addAction("refreshbasket");
        myIntentFilter.addAction("showArea");
        myIntentFilter.addAction("chooseNum");
        myIntentFilter.addAction("openselectall");
        myIntentFilter.addAction("closeselectall");
        myIntentFilter.addAction("refreshDistribution");
        // 注册广播
        getContext().registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("seltce1")) {
                if (basketInfoList.size()>0&&basketInfoList !=null){
                    ll_baske.setVisibility(View.VISIBLE);
                }
                adapter.isselect = true;
                adapter.notifyDataSetChanged();
            } else if (action.equals("seltce2")) {
                if (basketInfoList.size()>0&&basketInfoList !=null){
                    ll_baske.setVisibility(View.VISIBLE);
                }
                adapter.isselect = false;
                adapter.notifyDataSetChanged();
            } else if (action.equals("seltce3")) {
                int number = intent.getIntExtra("number", 0);
                List<String> list = intent.getStringArrayListExtra("price");
                money = 0;
                for (String fo : list) {
                    double a = Double.parseDouble(fo);
                    money = money + a;
                }
                String adc = String.valueOf(money);
                if (null != list && list.size() > 0) {
                    if (adc.length()>=5){
                        tv_money_harvest.setText("￥" + adc.substring(0,5));
                    }else {
                        tv_money_harvest.setText("￥" + money);
                    }
                    tv_number_harvest.setText("(" + number + ")");

                } else {
                    tv_money_harvest.setText("￥" + 0);
                    tv_number_harvest.setText("(" + 0 + ")");
                }
                carIds.clear();
                harvestArea.clear();
                nums.clear();
                harvestTypes.clear();
                allmoney.clear();
                carIds = intent.getIntegerArrayListExtra("carIds");
                List<String> areas = intent.getStringArrayListExtra("areas");
                for (int i = 0;i<areas.size();i++){
                    Double b = Double.valueOf(areas.get(i));
                    harvestArea.add(b);
                }
                nums = intent.getIntegerArrayListExtra("nums");
                harvestTypes = intent.getIntegerArrayListExtra("harvestType");
                List<String> amoney = intent.getStringArrayListExtra("allmoney");
                if (amoney == null&&amoney.size() == 0){
                    for (int i = 0;i<harvestTypes.size();i++){
                        allmoney.add(0.0);
                    }
                }else if(amoney!=null&&amoney.size()>0){
                    for (int i = 0;i<amoney.size();i++){
                        Double b;
                        if (amoney.get(i).equals("null")){
                            b = 0.0;
                        }else {
                            b = Double.valueOf(amoney.get(i));
                        }
                        allmoney.add(b);
                    }
                }

            }else if (action.equals("refreshbasket")){
                showUserFoodCar();
            }else if (action.equals("showArea")){
                ll_show.setVisibility(View.VISIBLE);
                text_change_price.setVisibility(View.GONE);
                ll_style1.setVisibility(View.GONE);
                ll_style2.setVisibility(View.VISIBLE);
                ll_style3.setVisibility(View.GONE);
                ll_style4.setVisibility(View.VISIBLE);
                ll_style5.setVisibility(View.GONE);
                ll_baske.setVisibility(View.GONE);
                ll_backgroud.setVisibility(View.VISIBLE);
                ll_choose_basket.setVisibility(View.VISIBLE);
                ll_choose_basket_2.setVisibility(View.GONE);
                text_choose_area.setText("请输入收货面积");
                String imgpath = intent.getStringExtra("imgpath");
                AppContext.getInstance().displayImage(ServerUrl.mainUrl + imgpath, img_product);
//                text_change_price.setText("￥ "+signprice);
                String id= intent.getStringExtra("productId");
                productId = Integer.parseInt(id);
                foodCarId = intent.getIntExtra("foodCarId",0);
                harvestType = intent.getIntExtra("harvestType",0);
                allArea = intent.getDoubleExtra("harvestArea",0.0);
                text_leftover.setText("剩余面积 "+String.valueOf(allArea)+" 平米");
            }else if (action.equals("chooseNum")){
                ll_show.setVisibility(View.VISIBLE);
                ll_style1.setVisibility(View.GONE);
                ll_style2.setVisibility(View.VISIBLE);
                ll_style3.setVisibility(View.GONE);
                ll_style4.setVisibility(View.GONE);
                ll_style5.setVisibility(View.VISIBLE);
                text_choose_area.setVisibility(View.GONE);
                ll_baske.setVisibility(View.GONE);
                ll_backgroud.setVisibility(View.VISIBLE);
                ll_choose_basket.setVisibility(View.GONE);
                ll_choose_basket_2.setVisibility(View.VISIBLE);
                String imgpath = intent.getStringExtra("imgpath");
                AppContext.getInstance().displayImage(ServerUrl.mainUrl + imgpath, img_product);
                price = intent.getStringExtra("price");
                foodId = intent.getIntExtra("foodId",0);
                harvestTyped = intent.getIntExtra("harvestTyped",0);
                cannum = 0;
                cannum = intent.getIntExtra("harvestNumber",0);
                String harvestNum = String.valueOf(cannum);
                text_change_num.setText("收获数量");
                text_leftover.setText("剩余数量 "+harvestNum);
                int jG = intent.getIntExtra("chooseJg",0);
                if (jG!=0 && jG>1){
                    numbers = jG;
                }
                number = String.valueOf(numbers);
                et_number.setText(number);
                HarvestCost(numbers);
            } else if (action.equals("openselectall")) {
                isover = true;
                iv_harvest.setImageResource(R.mipmap.xuanzhong);
            }
            else if (action.equals("closeselectall")) {
                isover = false;
                iv_harvest.setImageResource(R.mipmap.weixuanzhong);
            }else if (action.equals("refreshDistribution")){
                int type = intent.getIntExtra("type",0);
                if (type == 2){
                    showUserFoodCar();
                    ll_show.setVisibility(View.GONE);
                    ll_choose_basket.setVisibility(View.GONE);
                    ll_backgroud.setVisibility(View.GONE);
                }
            }
//            else if ()
        }
    };



    public void initEvent() {
        img_cancel.setOnClickListener(this);
        ll_area1.setOnClickListener(this);
        ll_area2.setOnClickListener(this);
        ll_area3.setOnClickListener(this);
        ll_area4.setOnClickListener(this);
        ll_area5.setOnClickListener(this);
        ll_choose_basket.setOnClickListener(this);
        ll_choose_basket_2.setOnClickListener(this);
        ll_harvest.setOnClickListener(this);
        ll_jia.setOnClickListener(this);
        ll_jian.setOnClickListener(this);
        ll_img_harvest.setOnClickListener(this);
    }

    private String number;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_img_harvest:
                if(!isover) {
                    isover = true;
                    iv_harvest.setImageResource(R.mipmap.xuanzhong);
                    adapter.getboole(true);
                }else{
                    isover = false;
                    iv_harvest.setImageResource(R.mipmap.weixuanzhong);
                    adapter.getboole(false);
                }
                break;
            case R.id.ll_jia:
                if (numbers<cannum){
                    numbers++;
                }else if(numbers>=cannum){
                    Toast.makeText(getActivity(), "已经全部收获，不能再加了",Toast.LENGTH_SHORT).show();
                }
                number = String.valueOf(numbers);
                et_number.setText(number);
                HarvestCost(numbers);
                break;
            case R.id.ll_jian:
                if (numbers>1){
                    numbers--;
                    number = String.valueOf(numbers);
                    et_number.setText(number);
                    HarvestCost(numbers);
                }else if(numbers == 1){
                    Toast.makeText(getActivity(), "不能再减了",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_cancel1:
                ll_backgroud.setVisibility(View.GONE);
                ll_show.setVisibility(View.GONE);
                ll_baske.setVisibility(View.VISIBLE);
                ll_choose_basket.setVisibility(View.GONE);
                ll_choose_basket_2.setVisibility(View.GONE);
                numbers = 1;
                number = String.valueOf(numbers);
                et_number.setText(number);
                break;
            case R.id.ll_choose_basket://确定
                area = edit_enter_area.getText().toString();
                if (StringUtils.isEmpty(area)){
                    Toast.makeText(getActivity(),"面积不可为空",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Double darea = Double.valueOf(area);
                    if (darea>allArea){
                        Toast.makeText(getActivity(),"剩余面积不足",Toast.LENGTH_SHORT).show();
                        return;
                    }else if (darea == 0){
                        Toast.makeText(getActivity(),"收货面积不可为0",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        calculationHarvestCost(area,foodCarId);
                    }
                }
                break;
            case R.id.ll_choose_basket_2:
                if (!StringUtils.isEmpty(et_number.getText().toString())){
                    String mm = et_number.getText().toString();
                    numbers = Integer.parseInt(mm);
                    if(numbers>cannum){
                        Toast.makeText(getActivity(), "超过收货数量",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        ll_backgroud.setVisibility(View.GONE);
                        ll_show.setVisibility(View.GONE);
                        ll_baske.setVisibility(View.VISIBLE);
                        ll_choose_basket.setVisibility(View.GONE);
                        ll_choose_basket_2.setVisibility(View.GONE);
                        getActivity().sendBroadcast(new Intent("numchange")
                                .putExtra("numbers",numbers)
                                .putExtra("CarId",foodId)
                                .putExtra("total",total));
                        numbers = 1;
                        number = String.valueOf(numbers);
                        et_number.setText(number);
                    }
                }else {
                    Toast.makeText(getActivity(), "数量不可为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
            case R.id.ll_harvest:
                if (carIds!= null&&carIds.size()>0){
                    payOrder();
                }else {
                    Toast.makeText(getActivity(),"请选择收获的产品",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    //用户菜篮子
    public void showUserFoodCar(){
        Map<String,Object> map  = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        ServerRequest.requestHttp(getActivity(), ServerUrl.showUserFoodCar, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                List<BasketInfo> basketInfos = JSON.parseArray(json,BasketInfo.class);
                basketInfoList.clear();
                if(basketInfos.size()>0&&basketInfos!=null){
                    basketInfoList.addAll(basketInfos);
                    ll_baske.setVisibility(View.VISIBLE);
                }else {
                    ll_baske.setVisibility(View.GONE);
                }
                tv_money_harvest.setText("￥" + 0);
                tv_number_harvest.setText("(" + 0 + ")");
                iv_harvest.setImageResource(R.mipmap.weixuanzhong);
                isover = false;
                refresh_market_02.endRefreshing();
                refresh_market_02.endLoadingMore();
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(String msg) {
            }
        });
    }

    //计算单个的面积
    public void calculationHarvestCost(String harvestArea,int foodcarId){
        HttpRequest.calculationHarvestCost(getContext(), foodcarId, harvestArea, harvestType, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                text_change_price.setText("￥ "+json);
                signprice = json;
                ll_backgroud.setVisibility(View.GONE);
                ll_show.setVisibility(View.GONE);
                ll_baske.setVisibility(View.VISIBLE);
                ll_choose_basket.setVisibility(View.GONE);
                ll_choose_basket_2.setVisibility(View.GONE);
                getActivity().sendBroadcast(new Intent("changeArea")
                        .putExtra("area",area)
                        .putExtra("signprice",signprice)
                        .putExtra("foodCarId",foodCarId));
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();

            }
        });
    }
    //计算数量
    public void HarvestCost(int harvestNumber){
        HttpRequest.HarvestCost(getContext(), foodId , harvestNumber, harvestTyped, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                text_change_price.setText("￥ "+json);
                total = json;
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
//    菜篮子收获
    public void payOrder(){
        Double orderPrice = money;
        Map<String,Object> map=new HashMap<>();
        int userId = Integer.parseInt(AppContext.appContext.getUserInfo().getUserId());
        map.put("userId",userId);
        map.put("orderPrice",orderPrice);
        for(int i=0;i<carIds.size();i++){
            int id = carIds.get(i);
            map.put("foodCar["+i+"].foodCarId",id);
        }
        for(int i=0;i<harvestArea.size();i++){
            Double harea = harvestArea.get(i);
            map.put("foodCar["+i+"].harvestArea",harea);
        }
        for(int i=0;i<nums.size();i++){
            int num = nums.get(i);
            map.put("foodCar["+i+"].harvestNumber",num);
        }
        for(int i=0;i<harvestTypes.size();i++){
            int type = harvestTypes.get(i);
            map.put("foodCar["+i+"].harvestType",type);
        }
        for(int i=0;i<allmoney.size();i++){
            Double price = allmoney.get(i);
            map.put("foodCar["+i+"].harvestPrice",price);
        }
        ServerRequest.requestHttp(getActivity(), ServerUrl.payOrder, map, "加载中", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                OrderModels order = JSON.parseObject(json,OrderModels.class);
                getActivity().startActivity(new Intent(getActivity(), CashierActivity.class)
                        .putExtra("code",order.getOrderCode())
                        .putExtra("price",String.valueOf(order.getOrderTruePrice()))
                        .putExtra("type",2));
                carIds.clear();
                harvestArea.clear();
                nums.clear();
                harvestTypes.clear();
                allmoney.clear();
                tv_money_harvest.setText("￥" + 0);
                tv_number_harvest.setText("(" + 0 + ")");
                iv_harvest.setImageResource(R.mipmap.weixuanzhong);
                isover = false;
                showUserFoodCar();

                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(String msg) {
                ll_backgroud.setVisibility(View.GONE);
                ll_show.setVisibility(View.GONE);
                ll_baske.setVisibility(View.VISIBLE);
                ll_choose_basket.setVisibility(View.GONE);
                ll_choose_basket_2.setVisibility(View.GONE);
                numbers = 1;
                number = String.valueOf(numbers);
                et_number.setText(number);
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Subscribe
    public void onEvent(String action) {
        if (action.equals("refresh")) {
            showUserFoodCar();

        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        showUserFoodCar();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        getActivity().unregisterReceiver(myBroadcastReceiver);
    }
}
