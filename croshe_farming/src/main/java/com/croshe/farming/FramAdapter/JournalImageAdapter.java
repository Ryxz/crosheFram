package com.croshe.farming.FramAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.ImgInfo;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class JournalImageAdapter extends RecyclerView.Adapter<JournalImageAdapter.JournalImageView> {

    private Context context;
    private List<ImgInfo> list;
    public JournalImageAdapter(Context context, List<ImgInfo> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public JournalImageView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image,parent,false);
        return new JournalImageView(view);
    }

    @Override
    public void onBindViewHolder(JournalImageView holder, int position) {
        String imgpath = ServerUrl.mainUrl+list.get(position).getFilePath();
        ImageLoader.getInstance().displayImage(imgpath,holder.img_journal, AppContext.image_display_options);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class JournalImageView extends RecyclerView.ViewHolder{
        private ImageView img_journal;

        public JournalImageView(View itemView) {
            super(itemView);
            img_journal = (ImageView) itemView.findViewById(R.id.img_journal);
        }
    }
}
