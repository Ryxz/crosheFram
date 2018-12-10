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

import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.FramLandInfo;
import com.croshe.farming.R;
import com.croshe.farming.farm.MyFramDetailasTwoActivity;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */

public class FarmlandAdapter extends RecyclerView.Adapter<FarmlandAdapter.ViewHlder> {

    private List<FramLandInfo> list;
    private Context context;

    public  FarmlandAdapter(List<FramLandInfo> list,Context context){
         this.list=list;
        this.context=context;

    }
    @Override
    public FarmlandAdapter.ViewHlder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fram_land,viewGroup,false);
        ViewHlder hloder = new ViewHlder(view);
        return hloder;
    }

    @Override
    public void onBindViewHolder(FarmlandAdapter.ViewHlder viewHlder, final int i) {
        final int landState =list.get(i).getLandState();
        if (list.get(i).getLandImages()!=null&&list.get(i).getLandImages().size()>0){
            String land =ServerUrl.mainUrl+list.get(i).getLandImages().get(0).getFilePath();
//            AppContext.getInstance().displayImage(land,viewHlder.iv_land_farm);
            ImageLoader.getInstance().displayImage(land,viewHlder.iv_land_farm,AppContext.image_display_options);
        }
        viewHlder.tv_land_name.setText(list.get(i).getLandName());

//        if(landState==0) {
//            viewHlder.tv_land_area.setText( list.get(i).getLandName()+"");
//        }else if(landState==1){
//            viewHlder.tv_land_area.setText("推荐种殖："+ list.get(i).getLandTags());
//        }else if(landState==2){
//            viewHlder.tv_land_area.setText( list.get(i).getLandName());
//        }else{
//            viewHlder.tv_land_area.setText("推荐养殖："+ list.get(i).getLandTags());
//        }
        if(landState==0) {
            viewHlder.tv_land_area.setText("预计："+ list.get(i).getLandOutput());
        }else if(landState==1){
            viewHlder.tv_land_area.setText("种植面积："+ list.get(i).getLandArea()+"㎡");
        }else if(landState==2){
            viewHlder.tv_land_area.setText("预计："+ list.get(i).getLandOutput());
        }else{
            viewHlder.tv_land_area.setText("养殖面积："+ list.get(i).getLandArea()+"㎡");
        }
        viewHlder.ll_fram_land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MyFramDetailasTwoActivity.class)
                        .putExtra("landState",landState)
                        .putExtra("landId",list.get(i).getLandId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHlder extends RecyclerView.ViewHolder {
        ImageView iv_land_farm;
        TextView tv_land_name,tv_land_area,tv_land_area1;
        private LinearLayout ll_fram_land;
        public ViewHlder(View itemView) {
            super(itemView);
            iv_land_farm= (ImageView) itemView.findViewById(R.id.iv_land_farm_img);
            tv_land_name= (TextView) itemView.findViewById(R.id.tv_land_name);
            tv_land_area= (TextView) itemView.findViewById(R.id.tv_land_area);
            ll_fram_land = (LinearLayout) itemView.findViewById(R.id.ll_fram_land);
//            tv_land_area1= (TextView) itemView.findViewById(R.id.tv_land_area1);
        }
    }
}
