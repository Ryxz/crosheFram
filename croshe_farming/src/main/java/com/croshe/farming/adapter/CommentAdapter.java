package com.croshe.farming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.Entity.CommentInfo;
import com.croshe.farming.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHloder> {
    private List<CommentInfo> list;
    private Context context ;
    public CommentAdapter(List<CommentInfo> list,Context context ){
        this.list=list;
        this.context=context;
    }
    @Override
    public CommentAdapter.ViewHloder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_commout,viewGroup,false);
        ViewHloder hloder = new ViewHloder(view);
        return hloder;
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHloder viewHloder, int i) {
                if(!StringUtils.isEmpty(list.get(i).getToUserId())){
                    viewHloder.tv_nickname_commout.setText(list.get(i).getUserName()+":");
                }else{
                    viewHloder.tv_nickname_commout.setText("我：");
                }
                viewHloder.tv_commout.setText(list.get(i).getCommentContent());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHloder extends RecyclerView.ViewHolder {
        TextView tv_nickname_commout,tv_commout;
        public ViewHloder(View itemView) {
            super(itemView);
            tv_nickname_commout= (TextView) itemView.findViewById(R.id.tv_nickname_commout);
            tv_commout= (TextView) itemView.findViewById(R.id.tv_commout);
        }
    }
}
