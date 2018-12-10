package com.croshe.farming.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.PackageInfo;
import com.croshe.farming.R;
import com.croshe.farming.farm.PackageDetailasActivity;
import com.croshe.farming.server.ServerUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */
//套餐推荐

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.ViewHloder> {
    private List<PackageInfo> list;
    private Context context;

    public PackageAdapter(List<PackageInfo> list,Context context,int landState){
        this.list=list;
        this.context=context;

    }
    @Override
    public PackageAdapter.ViewHloder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_package,viewGroup,false);
        ViewHloder hloder = new ViewHloder(view);
        return hloder;
    }

    @Override
    public void onBindViewHolder(PackageAdapter.ViewHloder viewHloder, int i) {
         int landState= list.get(i).getPackageType();
        final String packageId=list.get(i).getPackageId();
            AppContext.getInstance().displayImage(ServerUrl.mainUrl+list.get(i).getPackageImage(),viewHloder.iv_package2);
        if(null!=list.get(i).getImgs()&&list.get(i).getImgs().size()>0){

            AppContext.getInstance().displayImage(ServerUrl.mainUrl+list.get(i).getImgs().get(0),viewHloder.iv_package3);
            if(list.get(i).getImgs().size()>1) {
//                viewHloder.iv_jia.setVisibility(View.VISIBLE);
                AppContext.getInstance().displayImage(ServerUrl.mainUrl + list.get(i).getImgs().get(1), viewHloder.iv_package4);
            }else{
//                viewHloder.iv_jia.setVisibility(View.GONE);
            }
            if(list.get(i).getImgs().size()>2) {
//                viewHloder.iv_jia_1.setVisibility(View.VISIBLE);
                AppContext.getInstance().displayImage(ServerUrl.mainUrl + list.get(i).getImgs().get(2), viewHloder.iv_package5);
            }else{
//                viewHloder.iv_jia_1.setVisibility(View.GONE);
            }
        }else{

        }
              viewHloder.tv_package.setText(list.get(i).getPackageName());
            viewHloder.tv_package1.setText("已售"+list.get(i).getPackageSales());
        if(landState==0){
            viewHloder.tv_package7.setText("含水量≥" + list.get(i).getPackageWeight() + "%");
            viewHloder.tv_package8.setText("发芽率≥" + list.get(i).getPackageLife() + "%");
        }else {
            viewHloder.tv_package7.setText("均重：" + list.get(i).getPackageWeight() + "kg");
            viewHloder.tv_package8.setText("成活率≥" + list.get(i).getPackageLife() + "%");
        }
            viewHloder.tv_package9.setText("￥" + list.get(i).getPackagePrice());
            viewHloder.tv_package10.setText("立省：￥" + list.get(i).getPackageSave());
        viewHloder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, PackageDetailasActivity.class);
                in.putExtra("packageId",packageId);
                context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHloder extends RecyclerView.ViewHolder {
        TextView tv_package,tv_package1,tv_package7,tv_package8,tv_package9,tv_package10;
                ImageView iv_package2,iv_package3,iv_package4,iv_package5,iv_package6,iv_jia,iv_jia_1;
        public ViewHloder(View itemView) {
            super(itemView);
            tv_package= (TextView) itemView.findViewById(R.id.tv_package);
            tv_package1= (TextView) itemView.findViewById(R.id.tv_package_1);
            tv_package7= (TextView) itemView.findViewById(R.id.tv_package_7);
            tv_package8= (TextView) itemView.findViewById(R.id.tv_package_8);
            tv_package9= (TextView) itemView.findViewById(R.id.tv_package_9);
            tv_package10= (TextView) itemView.findViewById(R.id.tv_package_10);
            iv_package2= (ImageView) itemView.findViewById(R.id.iv_package_2);
            iv_package3= (ImageView) itemView.findViewById(R.id.iv_package_3);
            iv_package4= (ImageView) itemView.findViewById(R.id.iv_package_4);
            iv_package5= (ImageView) itemView.findViewById(R.id.iv_package_5);
//            iv_jia= (ImageView) itemView.findViewById(R.id.iv_jia);
//            iv_jia_1= (ImageView) itemView.findViewById(R.id.iv_jia_1);
        }
    }
}
