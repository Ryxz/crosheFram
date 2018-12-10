package com.croshe.farming.FramAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.croshe.farming.Entity.CostEntity;
import com.croshe.farming.R;
import com.croshe.farming.farm.CostDetailActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class CostAdapter extends RecyclerView.Adapter<CostAdapter.CostView> {
    private List<CostEntity> list;
    private Context context;
    private String detailsId;

    public CostAdapter(Context context,List<CostEntity> list,String detailsId){
        this.context = context;
        this.list = list;
        this.detailsId = detailsId;
    }

    @Override
    public CostView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cost,parent,false);
        return new CostView(view);
    }

    @Override
    public void onBindViewHolder(CostView holder, int position) {
        final CostEntity cost = list.get(position);
        holder.cost_name.setText(cost.getCostTypeStr());
        holder.cost_price.setText(String.valueOf(cost.getPrice())+"元");
        holder.cost_time.setText(cost.getCostTime());
        holder.text_to_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CostDetailActivity.class)
                        .putExtra("costType",cost.getCostType())
                        .putExtra("detailsId",detailsId)
                        .putExtra("textname",cost.getCostTypeStr()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class CostView extends RecyclerView.ViewHolder{
        private TextView cost_name,cost_price,cost_time,text_to_detail;

        public CostView(View itemView) {
            super(itemView);
            cost_name = (TextView) itemView.findViewById(R.id.cost_name);
            cost_price = (TextView) itemView.findViewById(R.id.cost_price);
            cost_time = (TextView) itemView.findViewById(R.id.cost_time);
            text_to_detail = (TextView) itemView.findViewById(R.id.text_to_detail);

        }
    }
}
