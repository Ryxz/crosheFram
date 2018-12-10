package com.croshe.farming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.farming.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class GridViewkeyAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public GridViewkeyAdapter(List<String> list,Context context){
        this.list=list;
        this.context=context;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        final GridViewHolder holder;
        if (convertView == null){
            holder = new GridViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gridview_key,parent,false);
            holder.textView = (TextView) convertView.findViewById(R.id.text_key);
            holder.ll_product = (LinearLayout) convertView.findViewById(R.id.ll_key);
            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) holder.ll_product.getLayoutParams(); //取控件textView当前的布局参数
            linearParams.height = 50;
            linearParams.width = 250;// 控件的宽强制设成30
            holder.ll_product.setLayoutParams(linearParams);
            holder.textView.setText(list.get(position));
            convertView.setTag(holder);
        }else{
            holder = (GridViewHolder) convertView.getTag();
        }

        return convertView;
    }
    public class GridViewHolder{
        TextView textView;
        LinearLayout ll_product;
    }
}
