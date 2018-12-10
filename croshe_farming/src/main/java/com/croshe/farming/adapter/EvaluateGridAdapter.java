package com.croshe.farming.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.croshe.crosheandroidbase.activity.ImagePagerActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.PersonalCenter.EvaluateActivity;
import com.croshe.farming.R;
import com.croshe.farming.server.L;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class EvaluateGridAdapter  extends RecyclerView.Adapter<EvaluateGridAdapter.ViewHolder>{
    List<String> list = new ArrayList<>();
    Context context;

    public EvaluateGridAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evaluate_img, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (!StringUtils.isEmpty(list.get(position))) {
            ImageLoader.getInstance().displayImage(list.get(position), holder.iv_photo, AppContext.image_display_options);
            File file = new File(list.get(position).replace("file://", ""));
            L.d("NIU", file.exists() + "");
        }

        holder.img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.sendBroadcast(new Intent("ondeleEvaluteImg").putExtra("imgUrl", list.get(position)));
                String imgUrl = list.get(position);
                boolean isdel = list.contains(imgUrl);
                if (isdel) {
                    list.remove(imgUrl);
                }
                notifyDataSetChanged();
            }
        });


        holder.iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strImg = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    strImg[i] = list.get(i);
                }
                context.startActivity(new Intent(context, ImagePagerActivity.class).putExtra("image_index", position).putExtra("image_urls", strImg));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_photo, img_close;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);
            img_close = (ImageView) itemView.findViewById(R.id.img_close);
        }
    }
}

