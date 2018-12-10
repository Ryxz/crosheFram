package com.croshe.farming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.croshe.farming.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHloder> {
    private List<String> list;
   private Context context;
    public NewsAdapter(List<String> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public NewsAdapter.ViewHloder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_newsadapter, viewGroup, false);
        ViewHloder hloder = new ViewHloder(view);
        return hloder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHloder viewHloder, int i) {
//        String data = list.get(i%list.size());
        viewHloder.tv_tlitle.setText(list.get(i) + "");
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHloder extends RecyclerView.ViewHolder {
        TextView tv_tlitle;

        public ViewHloder(View itemView) {
            super(itemView);
            tv_tlitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
