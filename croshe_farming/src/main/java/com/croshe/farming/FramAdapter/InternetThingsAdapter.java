package com.croshe.farming.FramAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.croshe.farming.Entity.MonitorsEntity;
import com.croshe.farming.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class InternetThingsAdapter extends RecyclerView.Adapter<InternetThingsAdapter.ThingsView> {
    private List<MonitorsEntity> list;
    private Context context;
    public InternetThingsAdapter(Context context,List<MonitorsEntity> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ThingsView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_things,parent,false);
        return new ThingsView(view);
    }

    @Override
    public void onBindViewHolder(ThingsView holder, int position) {
        MonitorsEntity monitor = list.get(position);
        holder.things_name.setText(monitor.getName());
        holder.things_value.setText(String.valueOf(monitor.getValue())+" "+monitor.getUnit());
        holder.things_state.setText(monitor.getAlertdetails());
        holder.things_time.setText(monitor.getUpdateTime().substring(0,10));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ThingsView extends RecyclerView.ViewHolder{
        private TextView things_name,things_value,things_state,things_time;

        public ThingsView(View itemView) {
            super(itemView);
            things_name = (TextView) itemView.findViewById(R.id.things_name);
            things_value = (TextView) itemView.findViewById(R.id.things_value);
            things_state = (TextView) itemView.findViewById(R.id.things_state);
            things_time = (TextView) itemView.findViewById(R.id.things_time);

        }
    }
}
