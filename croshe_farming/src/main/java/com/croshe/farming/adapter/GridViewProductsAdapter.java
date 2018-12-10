package com.croshe.farming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.crosheandroidbase.util.DensityUtils;
import com.croshe.farming.Entity.TypeInfo;
import com.croshe.farming.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/1.
 */

public class GridViewProductsAdapter extends BaseAdapter {
    private Context context;
    private List<TypeInfo> list;

    Map<String,TypeInfo> typeInfoMap =new HashMap<>();


    int Multiple;
    boolean ischeck = false;

    public GridViewProductsAdapter(Context context,List<TypeInfo> list,int Multiple){
        this.context = context;
        this.list = list;
        this.Multiple = Multiple;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final GridViewHolder holder;
        if (convertView == null){
            holder = new GridViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_product_type,parent,false);
            holder.textView = (TextView) convertView.findViewById(R.id.text_product_type);
            holder.ll_product = (LinearLayout) convertView.findViewById(R.id.ll_product_type);
            holder.textView.setText(list.get(position).getTypeName());
            convertView.setTag(holder);
        }else{
            holder = (GridViewHolder) convertView.getTag();
        }
        holder.ll_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.get(position).isAdd()){
                    list.get(position).setIscheck(true);
                    typeInfoMap.put(list.get(position).getTypeId()+"",list.get(position));
                    list.get(position).setAdd(true);
                }else {
                    list.get(position).setIscheck(false);
                    list.get(position).setAdd(false);
                    typeInfoMap.remove(list.get(position).getTypeId());
                }
                notifyDataSetChanged();
            }
        });

        if(list.get(position).ischeck()){
            holder.ll_product.setBackgroundResource(R.drawable.alert_orange_bg);
        }else{
            holder.ll_product.setBackgroundResource(R.drawable.back_seeds);
        }
        return convertView;
    }

    public class GridViewHolder{
        TextView textView;
        LinearLayout ll_product;
    }

    public Map<String, TypeInfo> getTypeInfoMap() {
        return typeInfoMap;
    }

    public void cleanMap() {
        if(null!=list&&list.size()>0){
            for (int i =0;i<list.size();i++){
                list.get(i).setIscheck(false);
            }
            notifyDataSetChanged();
        }
        typeInfoMap.clear();
    }


}
