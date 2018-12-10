package com.croshe.farming.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.croshe.farming.Entity.TypeClassEnumInfo;
import com.croshe.farming.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHloder> {
    private List<TypeClassEnumInfo> list;
    private Context context;
    private boolean isover;
    public ListAdapter(List<TypeClassEnumInfo> list,Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public ListAdapter.ViewHloder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,viewGroup,false);
        ViewHloder hloder = new ViewHloder(view);
        return hloder;
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHloder viewHloder, final int i) {
        viewHloder.tv_list.setText(list.get(i).getText());
        final String id =list.get(i).getId();
        if(!isover) {
            list.get(0).setIsselect(true);
        }else{

        }
               if(list.get(i).isselect()){
                   viewHloder.itemView.setBackgroundResource(R.color.colorbaise);
                   viewHloder.tv_list.setTextColor(context.getResources().getColor(R.color.colorAccent));
               }else{
                   viewHloder.itemView.setBackgroundResource(R.color.colorPrimary);
                   viewHloder.tv_list.setTextColor(context.getResources().getColor(R.color.colorbaise));
               }
        viewHloder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isover=true;
                for(TypeClassEnumInfo fo:list){
                    fo.setIsselect(false);
                }
                list.get(i).setIsselect(true);
                notifyDataSetChanged();
                Intent in = new Intent("nongzi");
                in.putExtra("id",id);
                context.sendBroadcast(in);
                Intent in1 = new Intent("shouhuo");
                in.putExtra("id",id);
                context.sendBroadcast(in1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHloder extends RecyclerView.ViewHolder {
        TextView tv_list;
        public ViewHloder(View itemView) {
            super(itemView);
            tv_list= (TextView) itemView.findViewById(R.id.tv_list);
        }
    }
}
