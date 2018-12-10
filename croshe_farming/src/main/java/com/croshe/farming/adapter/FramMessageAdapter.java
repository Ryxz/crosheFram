
package com.croshe.farming.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.NewsInfo;
import com.croshe.farming.R;
import com.croshe.farming.activity.FramMessageDetailActivity;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */

public class FramMessageAdapter extends RecyclerView.Adapter<FramMessageAdapter.FramMessageView> {

    Context context;
    List<NewsInfo> list;
    public FramMessageAdapter(Context context,List<NewsInfo> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public FramMessageView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fram_message,parent,false);
        return new FramMessageView(view);
    }

    @Override
    public void onBindViewHolder(FramMessageView holder, final int position) {
        holder.text_fram_meg.setText(list.get(position).getNewsTitle());
        String imgpath = list.get(position).getNewsImage();
        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath,holder.img_fram_meg, AppContext.image_display_options);
        holder.ll_fram_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, FramMessageDetailActivity.class)
                        .putExtra("newsurl",list.get(position).getNewsUrl()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class FramMessageView extends RecyclerView.ViewHolder{
        ImageView img_fram_meg;
        TextView text_fram_meg;
        private LinearLayout ll_fram_msg;

        public FramMessageView(View itemView) {
            super(itemView);
            text_fram_meg = (TextView) itemView.findViewById(R.id.text_fram_meg);
            img_fram_meg = (ImageView) itemView.findViewById(R.id.img_fram_meg);
            ll_fram_msg = (LinearLayout) itemView.findViewById(R.id.ll_fram_msg);
        }
    }
}
