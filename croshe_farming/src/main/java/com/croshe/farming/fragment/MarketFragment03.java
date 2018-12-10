package com.croshe.farming.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.R;
import com.croshe.farming.adapter.SeedsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class MarketFragment03 extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private List<ProductInfo> seedlist= new ArrayList<>();
    //    private List<String> seedlist1= new ArrayList<>();
    private TextView tv1,tv2,tv3,tv4;
    private LinearLayout ll_all;
    private SeedsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_market_03,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();
        initDate();
    }
    public void initView(){
        recyclerView= (RecyclerView) getActivity().findViewById(R.id.tv_re_lei_3);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SeedsAdapter(seedlist,getActivity());
        recyclerView.setAdapter(adapter);

        tv1= (TextView) getActivity().findViewById(R.id.tv_pesticide_1);
        tv2= (TextView) getActivity().findViewById(R.id.tv_pesticide_2);
        tv3= (TextView) getActivity().findViewById(R.id.tv_pesticide_3);
        tv4= (TextView) getActivity().findViewById(R.id.tv_pesticide_4);
        ll_all= (LinearLayout) getActivity().findViewById(R.id.tv_all_3);
    }
    public  void initDate(){

    }
    public  void initEvent(){
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        ll_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_pesticide_1:
                tv1.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv2.setTextColor(getResources().getColor(R.color.colorAccent));
                tv3.setTextColor(getResources().getColor(R.color.colorAccent));
                tv4.setTextColor(getResources().getColor(R.color.colorAccent));
                break;

            case R.id.tv_pesticide_2:
                tv2.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv1.setTextColor(getResources().getColor(R.color.colorAccent));
                tv3.setTextColor(getResources().getColor(R.color.colorAccent));
                tv4.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.tv_pesticide_3:
                tv3.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv2.setTextColor(getResources().getColor(R.color.colorAccent));
                tv1.setTextColor(getResources().getColor(R.color.colorAccent));
                tv4.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.tv_pesticide_4:
                tv4.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv2.setTextColor(getResources().getColor(R.color.colorAccent));
                tv3.setTextColor(getResources().getColor(R.color.colorAccent));
                tv1.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
        }
    }
}
