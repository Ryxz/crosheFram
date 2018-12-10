package com.croshe.farming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.croshe.farming.AppContext;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerUrl;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHloder> {
    private List<String> list;
    private Context context;
    private boolean isover;
    private setOnitmelistener onitmelistener;


      public GridViewAdapter(List<String> list, boolean isover){
          this.list=list;
           this.isover=isover;

      }

   static class ViewHloder extends RecyclerView.ViewHolder {
       ImageView iv_age;
       LinearLayout ll_imgage;
        public ViewHloder(View itemView) {
            super(itemView);
            iv_age= (ImageView) itemView.findViewById(R.id.iv_imgage);
            ll_imgage= (LinearLayout) itemView.findViewById(R.id.ll_imgage_iv);
        }
    }

    @Override
    public GridViewAdapter.ViewHloder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_imgage,parent,false);
        ViewHloder holder = new ViewHloder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GridViewAdapter.ViewHloder holder, final int position) {
        if(!isover) {
            AppContext.getInstance().displayImage(list.get(position), holder.iv_age);
        }else{
            AppContext.getInstance().displayImage(ServerUrl.mainUrl+list.get(position), holder.iv_age);
        }
        if(!isover) {
            holder.ll_imgage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitmelistener.getint(position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


//接口
 public interface setOnitmelistener{
        public void getint(int position);
    }
//    回调接口
    public void getOnitmelistener(setOnitmelistener onitmelistener){
        this.onitmelistener=onitmelistener;
    }


}
