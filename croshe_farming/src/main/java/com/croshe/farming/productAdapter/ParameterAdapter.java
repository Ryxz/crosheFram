package com.croshe.farming.productAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.croshe.farming.Entity.StandardNameEntity;
import com.croshe.farming.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class ParameterAdapter extends RecyclerView.Adapter<ParameterAdapter.ParameterView> {
    private List<StandardNameEntity> list ;
    private Context context;

    public ParameterAdapter(Context context,List<StandardNameEntity> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ParameterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parameter,parent,false);
        return new ParameterView(view);
    }

    @Override
    public void onBindViewHolder(ParameterView holder, int position) {

        holder.text_name.setText(list.get(position).getName());
        holder.text_value.setText(list.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return  list == null ? 0 : list.size();
    }

    public class ParameterView extends RecyclerView.ViewHolder {
        private TextView text_name,text_value;
        public ParameterView(View itemView) {
            super(itemView);
            text_name = (TextView) itemView.findViewById(R.id.text_name);
            text_value = (TextView) itemView.findViewById(R.id.text_value);

        }
    }
}
