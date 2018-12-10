package com.croshe.farming.FramAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.farming.Entity.FileEntity;
import com.croshe.farming.R;
import com.croshe.farming.farm.GrowVedioActivty;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class GrowVedioAdapter extends RecyclerView.Adapter<GrowVedioAdapter.VedioView> {

    private Context context;
    private List<FileEntity> list;

    public GrowVedioAdapter(Context context,List<FileEntity> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public VedioView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grow_vedio,parent,false);
        return new VedioView(view);
    }

    @Override
    public void onBindViewHolder(VedioView holder, final int position) {
        holder.text_vedio_time.setText(list.get(position).getFileDateTime());
        holder.ll_vedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, GrowVedioActivty.class)
                        .putExtra("filePath",list.get(position).getFilePath())
                        .putExtra("filename",list.get(position).getFileName()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return  list.size();
    }

    public class VedioView extends RecyclerView.ViewHolder{
        private TextView text_vedio_time;
        private LinearLayout ll_vedio;

        public VedioView(View itemView) {
            super(itemView);
            text_vedio_time = (TextView) itemView.findViewById(R.id.text_vedio_time);
            ll_vedio = (LinearLayout) itemView.findViewById(R.id.ll_vedio);

        }
    }
}
