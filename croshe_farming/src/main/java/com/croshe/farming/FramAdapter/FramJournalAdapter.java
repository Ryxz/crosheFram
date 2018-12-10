package com.croshe.farming.FramAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.croshe.farming.R;

/**
 * Created by Administrator on 2017/7/13.
 */

public class FramJournalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int contentView = 0;//内容
    private int plContentView = 1;//评论
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == contentView) {
            return new JournalView(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journal, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == -1) {
            return plContentView;//评论
        } else {
            return contentView;//内容
        }
    }
    public class JournalView extends RecyclerView.ViewHolder{
        private TextView text_context,text_journal_time;
        private RecyclerView recycler_journal_img;
        private ImageView img_huifu;

        public JournalView(View itemView) {
            super(itemView);
            text_context = (TextView) itemView.findViewById(R.id.text_context);
            recycler_journal_img = (RecyclerView) itemView.findViewById(R.id.recycler_journal_img);
            text_journal_time = (TextView) itemView.findViewById(R.id.text_journal_time);
            img_huifu = (ImageView) itemView.findViewById(R.id.img_huifu);

        }
    }
}
