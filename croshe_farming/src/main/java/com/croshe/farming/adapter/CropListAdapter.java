package com.croshe.farming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.croshe.farming.Entity.CropEntity;
import com.croshe.farming.R;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class CropListAdapter extends RecyclerView.Adapter<CropListAdapter.CropView> {
    private Context context;
    private List<CropEntity> list;

    public CropListAdapter(Context context,List<CropEntity> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public CropView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agricultural01,parent,false);
        return new CropView(view);
    }

    @Override
    public void onBindViewHolder(CropView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class CropView extends RecyclerView.ViewHolder{

        public CropView(View itemView) {
            super(itemView);
        }
    }
}
