package com.croshe.farming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.FramDetailsInfo;
import com.croshe.farming.Entity.PackageDetailsEntity;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerUrl;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class PackageDetailasAdapter extends RecyclerView.Adapter<PackageDetailasAdapter.ViewHloder> {
    private List<PackageDetailsEntity> list;
    private Context context;
    public PackageDetailasAdapter(List<PackageDetailsEntity> list,Context context){
        this.list=list;
        this.context=context;
    }

    @Override
    public PackageDetailasAdapter.ViewHloder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_packagedetailas,viewGroup,false);
        ViewHloder hloder  = new ViewHloder(view);
        return hloder;
    }

    @Override
    public void onBindViewHolder(PackageDetailasAdapter.ViewHloder viewHloder, int i) {
        if (list.get(i).getImg()!=null){
            AppContext.getInstance().displayImage(ServerUrl.mainUrl+list.get(i).getImg(),viewHloder.iv_package_data);
        }
        viewHloder.tv_name_deta.setText(list.get(i).getName());
//        viewHloder.tv_name_deta1.setText(list.get(i).get);
        viewHloder.tv_name_deta2.setText(list.get(i).getTargetNumber());
        viewHloder.tv_name_deta3.setText("￥"+list.get(i).getPrice());
        viewHloder.tv_name_deta4.setText(list.get(i).getReason());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHloder extends RecyclerView.ViewHolder {
        ImageView iv_package_data;
        TextView tv_name_deta,tv_name_deta1,tv_name_deta2,tv_name_deta3,tv_name_deta4,tv_name_deta5;
        public ViewHloder(View itemView) {
            super(itemView);
            iv_package_data= (ImageView) itemView.findViewById(R.id.iv_pavkage_deta);
            tv_name_deta= (TextView) itemView.findViewById(R.id.tv_name_deta);
            tv_name_deta1= (TextView) itemView.findViewById(R.id.tv_name_deta1);
            tv_name_deta2= (TextView) itemView.findViewById(R.id.tv_name_deta2);
            tv_name_deta3= (TextView) itemView.findViewById(R.id.tv_name_deta3);
            tv_name_deta4= (TextView) itemView.findViewById(R.id.tv_name_deta4);
        }
    }
}
