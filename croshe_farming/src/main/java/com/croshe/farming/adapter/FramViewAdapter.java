package com.croshe.farming.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.FramViewInfo;
import com.croshe.farming.R;
import com.croshe.farming.farm.FramDetailsActivity;
import com.croshe.farming.server.ServerUrl;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */

public class FramViewAdapter extends RecyclerView.Adapter<FramViewAdapter.ViewHloder> {
    private List<FramViewInfo> list;
    private Context context;
    public FramViewAdapter(List<FramViewInfo> list,Context context){
        this.list=list;
        this.context=context;
    }

    @Override
    public FramViewAdapter.ViewHloder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fram_view,viewGroup,false);
        ViewHloder hloder = new ViewHloder(view);
        return hloder;
    }

    @Override
    public void onBindViewHolder(FramViewAdapter.ViewHloder viewHloder, int i) {
        final String farmId=list.get(i).getFarmId();
        if (list.get(i).getFarmImages() != null&&list.get(i).getFarmImages().size()>0){
            AppContext.getInstance().displayImage(ServerUrl.mainUrl+list.get(i).getFarmImages().get(0).getFilePath(),viewHloder.iv_img_farm);
        }
//       viewHloder.tv_name.setText(list.get(i).getFarmDetails());
        viewHloder.tv_address.setText(list.get(i).getFarmAddress());
        viewHloder.tv_fram_name.setText(list.get(i).getFarmName());
        viewHloder.tv_features.setText("特色:"+list.get(i).getFarmTags()+"等");
        viewHloder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, FramDetailsActivity.class);
                in.putExtra("farmId",farmId);
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  list == null ? 0 : list.size();
    }

    static class ViewHloder extends RecyclerView.ViewHolder {

        ImageView iv_img_farm;
        TextView tv_name,tv_address,tv_fram_name,tv_features;
        public ViewHloder(View itemView) {
            super(itemView);
            iv_img_farm= (ImageView) itemView.findViewById(R.id.iv_img_farm);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
            tv_address= (TextView) itemView.findViewById(R.id.tv_address);
            tv_fram_name= (TextView) itemView.findViewById(R.id.tv_fram_name);
            tv_features= (TextView) itemView.findViewById(R.id.tv_features);
        }
    }
}
