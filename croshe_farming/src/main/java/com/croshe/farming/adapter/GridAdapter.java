package com.croshe.farming.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerUrl;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHloder> {
    private List<String> list;
    private Context context;
    private OnMyItemClickListener listener;
    private RecyclerView recyclerView;
    public Boolean isphoto=false;

    public GridAdapter(List<String> list, Context context){
        this.list=list;
        this.context=context;
    }

    static class ViewHloder extends RecyclerView.ViewHolder {
        ImageView iv_age;
        public ViewHloder(View itemView) {
            super(itemView);
            iv_age= (ImageView) itemView.findViewById(R.id.iv_imgage_ablu);

        }
    }

    @Override
    public ViewHloder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.img,parent,false);
       ViewHloder holder = new ViewHloder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GridAdapter.ViewHloder holder, final int position) {
        AppContext.getInstance().displayImage(list.get(position),holder.iv_age);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    // 接口回调
    public interface OnMyItemClickListener{
        void onMyItemClick(int position);
    }
    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }

}
