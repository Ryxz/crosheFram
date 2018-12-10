package com.croshe.farming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.croshe.farming.Entity.CanGrowInfo;
import com.croshe.farming.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */
//往期种植
public class PastBreedingAdapter extends RecyclerView.Adapter<PastBreedingAdapter.ViewHolder> {
    private List<CanGrowInfo> list;
    private Context context;
    public PastBreedingAdapter(List<CanGrowInfo> list,Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public PastBreedingAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pastbreed,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PastBreedingAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_past_1.setText(list.get(i).getLeaseArea()+"㎡");
        viewHolder.tv_past_2.setText(list.get(i).getOutLan());
        viewHolder.tv_past_3.setText(list.get(i).getOutNumber());
        viewHolder.tv_past_4.setText(list.get(i).getOutCost()+"元");

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_past_1,tv_past_2,tv_past_3,tv_past_4;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_past_1= (TextView) itemView.findViewById(R.id.tv_past_1);
            tv_past_2= (TextView) itemView.findViewById(R.id.tv_past_2);
            tv_past_3= (TextView) itemView.findViewById(R.id.tv_past_3);
            tv_past_4= (TextView) itemView.findViewById(R.id.tv_past_4);

        }
    }
}
