package com.croshe.farming.FramAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.croshe.farming.Entity.CostEntity;
import com.croshe.farming.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class CostDetailAdapter extends RecyclerView.Adapter<CostDetailAdapter.CostDetailView> {
    private Context context;
    private List<CostEntity> list;

    public CostDetailAdapter(Context context,List<CostEntity> list){
        this.context = context;
        this.list = list;

    }

    @Override
    public CostDetailView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cost_detail,parent,false);
        return new CostDetailView(view);
    }

    @Override
    public void onBindViewHolder(CostDetailView holder, int position) {
        holder.text_cost_name.setText(list.get(position).getCostTypeStr());
        holder.text_cost_price.setText(String.valueOf(list.get(position).getPrice())+"元");
        holder.text_cost_time.setText(list.get(position).getCostTime());
        if (list.get(position).getCostState() == 0){
            holder.text_cost_state.setText("已支付");
        }else {
            holder.text_cost_state.setText("未支付");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CostDetailView extends RecyclerView.ViewHolder{
        private TextView text_cost_name,text_cost_price,text_cost_time,text_cost_state;

        public CostDetailView(View itemView) {
            super(itemView);
            text_cost_name = (TextView) itemView.findViewById(R.id.text_cost_name);
            text_cost_price = (TextView) itemView.findViewById(R.id.text_cost_price);
            text_cost_time = (TextView) itemView.findViewById(R.id.text_cost_time);
            text_cost_state = (TextView) itemView.findViewById(R.id.text_cost_state);
        }
    }
}
