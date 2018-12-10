package com.croshe.farming.productAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.CommentModel;
import com.croshe.farming.Entity.Images;
import com.croshe.farming.Entity.Product;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Comment;

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by Administrator on 2017/7/25.
 */

public class ShowEvaluateAdapter extends RecyclerView.Adapter<ShowEvaluateAdapter.ShowEvaluteView>{
    private List<CommentModel> list;
    private Context context;
    public ShowEvaluateAdapter(Context context, List<CommentModel> list){
        this.context = context;
        this.list = list;
    }


    @Override
    public ShowEvaluteView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_alleva,parent,false);
        return new ShowEvaluteView(view);
    }

    @Override
    public void onBindViewHolder(ShowEvaluteView holder, int position) {
        holder.text_buy_name.setText(list.get(position).getUserNickName());
        holder.text_buy_eval.setText(list.get(position).getCommentContent());
        holder.text_buy_time.setText(list.get(position).getCommentDateTime());
        float rate = Float.parseFloat(list.get(position).getCommentLevel());
        holder.rating_comment_eva.setRating(rate);
        String img = list.get(position).getUserHeadImg();
        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+img,holder.img_buyer_head, AppContext.image_display_options);
        holder.img_eavl1.setImageResource(0);
        holder.img_eavl2.setImageResource(0);
        holder.img_eavl3.setImageResource(0);
        if (list.get(position).getCommentImages()!=null&&list.get(position).getCommentImages().size()>0){
            List<Images> commentImg = list.get(position).getCommentImages();
            if (commentImg.size()>=1){
                String evalimg1 = commentImg.get(0).getFilePath();
                ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+evalimg1,holder.img_eavl1,AppContext.image_display_options);
                if (commentImg.size()>=2){
                    String evalimg2 = commentImg.get(1).getFilePath();
                    ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+evalimg2,holder.img_eavl2,AppContext.image_display_options);
                    if (commentImg.size()==3){
                        String evalimg3 = commentImg.get(2).getFilePath();
                        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+evalimg3,holder.img_eavl3,AppContext.image_display_options);
                    }
                }
            }
        }else {
            holder.ll_img_eavl.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return  list == null ? 0 : list.size();
    }

    public class ShowEvaluteView extends RecyclerView.ViewHolder{
        private ImageView img_buyer_head,img_eavl1,img_eavl2,img_eavl3;
        private TextView text_buy_name,text_buy_time,text_buy_eval;
        private LinearLayout ll_img_eavl;
        private RatingBar rating_comment_eva;

        public ShowEvaluteView(View itemView) {
            super(itemView);
            img_buyer_head = (ImageView) itemView.findViewById(R.id.img_buyer_head);
            text_buy_name = (TextView) itemView.findViewById(R.id.text_buy_name);
            text_buy_time = (TextView) itemView.findViewById(R.id.text_buy_time);
            text_buy_eval = (TextView) itemView.findViewById(R.id.text_buy_eval);
            ll_img_eavl = (LinearLayout) itemView.findViewById(R.id.ll_img_eavl);
            img_eavl1 = (ImageView) itemView.findViewById(R.id.img_eavl1);
            img_eavl2 = (ImageView) itemView.findViewById(R.id.img_eavl2);
            img_eavl3 = (ImageView) itemView.findViewById(R.id.img_eavl3);
            rating_comment_eva = (RatingBar) itemView.findViewById(R.id.rating_comment_eva);
        }
    }
}
