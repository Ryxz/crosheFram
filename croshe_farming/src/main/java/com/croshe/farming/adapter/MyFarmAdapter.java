package com.croshe.farming.adapter;

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
import com.croshe.farming.Entity.MyFramEntity;
import com.croshe.farming.Entity.UserInfo;
import com.croshe.farming.R;
import com.croshe.farming.farm.MyFramDetailasActivity;
import com.croshe.farming.farm.MyFramDetailasTwoActivity;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */

public class MyFarmAdapter extends RecyclerView.Adapter<MyFarmAdapter.ViewHLder> {
    private List<UserInfo> list;
    private Context context;
    public MyFarmAdapter(List<UserInfo> list, Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public MyFarmAdapter.ViewHLder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_fram,viewGroup,false);
        ViewHLder hlder = new ViewHLder(view);
        return hlder;
    }

    @Override
    public void onBindViewHolder(MyFarmAdapter.ViewHLder viewHLder, final int i) {

        final String detailsId=list.get(i).getDetailsId();
        final int landState=list.get(i).getState();
        viewHLder.iv__farming.setImageResource(0);
        if(landState==0){
            viewHLder.ll_fram_01.setVisibility(View.VISIBLE);
            viewHLder.ll_fram_02.setVisibility(View.GONE);
            viewHLder.tv_farming_num.setText(list.get(i).getProductCount()+"");
            viewHLder.tv_farming_area.setText(list.get(i).getUseSumArea()+"平米");
            viewHLder.tv_name_zaisha.setText("距成熟期大约：");
            viewHLder.tv_farming_1.setText(list.get(i).getProductName());
            viewHLder.tv_farming_2.setText(list.get(i).getDetailsStateStr()+"");
            viewHLder.tv_farming_3.setText(list.get(i).getLiveCycle());
            viewHLder.tv_farming_6.setText("生长状态：");
            viewHLder.tv_farming_2.setTextColor(context.getResources().getColor(R.color.colorhongse));
            viewHLder.tv_farming_3.setTextColor(context.getResources().getColor(R.color.colorhongse));
            viewHLder.tv_farming_7.setText("生长周期：");
            viewHLder.tv_farming_4.setText(list.get(i).getDieDay()+"天");
            viewHLder.tv_farming_5.setText(list.get(i).getProductionDay()+"天");
            AppContext.getInstance().displayImage(ServerUrl.mainUrl+list.get(i).getImg(),viewHLder.iv__farming);
            viewHLder.tv_farming_8.setVisibility(View.VISIBLE);
            viewHLder.tv_farming_9.setVisibility(View.VISIBLE);
        }else if(landState==2){
            viewHLder.ll_fram_01.setVisibility(View.VISIBLE);
            viewHLder.ll_fram_02.setVisibility(View.GONE);
            viewHLder.tv_farming_num.setText(list.get(i).getProductCount()+"");
            viewHLder.tv_farming_area.setText(list.get(i).getUseSumArea()+"平米");
            viewHLder.tv_farming_6.setText("生长状态：");
            viewHLder.tv_farming_2.setTextColor(context.getResources().getColor(R.color.colorhongse));
            viewHLder.tv_farming_3.setTextColor(context.getResources().getColor(R.color.colorhongse));
            viewHLder.tv_farming_7.setText("生长周期：");
            viewHLder.tv_name_zaisha.setText("距宰杀期大约：");
            viewHLder.tv_farming_1.setText(list.get(i).getProductName());
            viewHLder.tv_farming_2.setText(list.get(i).getDetailsStateStr()+"");
            viewHLder.tv_farming_3.setText(list.get(i).getLiveCycle());
            viewHLder.tv_farming_4.setText(list.get(i).getDieDay()+"天");
            viewHLder.tv_farming_5.setText(list.get(i).getProductionDay()+"天");
            AppContext.getInstance().displayImage(ServerUrl.mainUrl+list.get(i).getImg(),viewHLder.iv__farming);
            viewHLder.tv_farming_8.setVisibility(View.VISIBLE);
            viewHLder.tv_farming_9.setVisibility(View.VISIBLE);
        }else if(landState==1){
            if(null!=list.get(i).getLand().getLandImages()&&list.get(i).getLand().getLandImages().size()>0) {
                AppContext.getInstance().displayImage(ServerUrl.mainUrl + list.get(i).getLand().getLandImages().get(0).getFilePath(), viewHLder.iv__farming);
            }
            viewHLder.ll_fram_01.setVisibility(View.GONE);
            viewHLder.ll_fram_02.setVisibility(View.VISIBLE);
            viewHLder.farm_name.setText(list.get(i).getLand().getLandName());
            viewHLder.fram_area.setText("可种植面积"+list.get(i).getUserFarmArea()+"平米");
            viewHLder.text_goname.setText("去种植");

        }else if(landState==3){
            viewHLder.ll_fram_01.setVisibility(View.GONE);
            viewHLder.ll_fram_02.setVisibility(View.VISIBLE);
            if(null!=list.get(i).getLand().getLandImages()&&list.get(i).getLand().getLandImages().size()>0) {
                AppContext.getInstance().displayImage(ServerUrl.mainUrl + list.get(i).getLand().getLandImages().get(0).getFilePath(), viewHLder.iv__farming);
            }
            viewHLder.farm_name.setText(list.get(i).getLand().getLandName());
            viewHLder.fram_area.setText("养殖面积"+list.get(i).getUserFarmArea()+"平米");
            viewHLder.text_goname.setText("去养殖");
        }


        viewHLder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(landState==0||landState==2) {
                    Intent in = new Intent(context, MyFramDetailasActivity.class);
                    in.putExtra("detailsId", detailsId);
                    context.startActivity(in);
                }
            }
        });
        viewHLder.ll_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (landState == 1){
                    context.sendBroadcast(new Intent("showProduct")
                            .putExtra("style","1")
                            .putExtra("areas",list.get(i).getUserFarmArea())
                            .putExtra("userFarmId", list.get(i).getUserFarmId()));
                }else if (landState == 3){
                    context.sendBroadcast(new Intent("showProduct")
                            .putExtra("style","3")
                            .putExtra("areas",list.get(i).getUserFarmArea())
                            .putExtra("userFarmId", list.get(i).getUserFarmId()));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHLder extends RecyclerView.ViewHolder {
        ImageView iv__farming;
        TextView tv_name_zaisha,farm_name,fram_area,text_goname,tv_farming_num,tv_farming_area;
        TextView tv_farming_1,tv_farming_2,tv_farming_3,tv_farming_4,tv_farming_5,tv_farming_6,tv_farming_7;
        LinearLayout tv_farming_8,tv_farming_9,ll_fram_01,ll_fram_02,ll_go;
        public ViewHLder(View itemView) {
            super(itemView);
            iv__farming= (ImageView) itemView.findViewById(R.id.iv__farming);
            tv_farming_1= (TextView) itemView.findViewById(R.id.tv_farming_1);
            tv_farming_2= (TextView) itemView.findViewById(R.id.tv_farming_2);
            tv_farming_3= (TextView) itemView.findViewById(R.id.tv_farming_3);
            tv_farming_4= (TextView) itemView.findViewById(R.id.tv_farming_4);
            tv_farming_5= (TextView) itemView.findViewById(R.id.tv_farming_5);
            tv_name_zaisha= (TextView) itemView.findViewById(R.id.tv_name_zaisha);
            tv_farming_6= (TextView) itemView.findViewById(R.id.tv_farming_6);
            tv_farming_7= (TextView) itemView.findViewById(R.id.tv_farming_7);
            tv_farming_8= (LinearLayout) itemView.findViewById(R.id.ll_farming_8);
            tv_farming_9= (LinearLayout) itemView.findViewById(R.id.ll_farming_9);

            tv_farming_num= (TextView) itemView.findViewById(R.id.tv_farming_num);
            tv_farming_area= (TextView) itemView.findViewById(R.id.tv_farming_area);

            text_goname= (TextView) itemView.findViewById(R.id.text_goname);
            farm_name= (TextView) itemView.findViewById(R.id.farm_name);
            fram_area= (TextView) itemView.findViewById(R.id.fram_area);
            ll_fram_01= (LinearLayout) itemView.findViewById(R.id.ll_fram_01);
            ll_fram_02= (LinearLayout) itemView.findViewById(R.id.ll_fram_02);
            ll_go= (LinearLayout) itemView.findViewById(R.id.ll_go);

        }

    }
}
