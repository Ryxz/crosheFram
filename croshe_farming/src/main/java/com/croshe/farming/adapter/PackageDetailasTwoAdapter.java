package com.croshe.farming.adapter;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.EvaluationInfo;
import com.croshe.farming.Entity.ImgInfo;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */

public class PackageDetailasTwoAdapter extends RecyclerView.Adapter<PackageDetailasTwoAdapter.ViewHloder> {
    private List<EvaluationInfo> list;
    private Context context;
    public PackageDetailasTwoAdapter(List<EvaluationInfo> list,Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public PackageDetailasTwoAdapter.ViewHloder onCreateViewHolder(ViewGroup viewGroup, int i) {
       View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_packagedetailastwo,viewGroup,false);
        ViewHloder hloder = new ViewHloder(view);
        return hloder;
    }

    @Override
    public void onBindViewHolder(PackageDetailasTwoAdapter.ViewHloder viewHloder, int i) {
        List<String> list1 = new ArrayList<>();
        GridAdapter adapter = new GridAdapter(list1, context);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(context);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        viewHloder.recyclerView_imgs.setLayoutManager(layoutManager1);
        viewHloder.recyclerView_imgs.setAdapter(adapter);

        AppContext.getInstance().displayImage(ServerUrl.mainUrl + list.get(i).getUserHeadImg(),viewHloder.iv_yonghui);
        viewHloder.tv_name_nick.setText(list.get(i).getUserName());
        viewHloder.tv_yonghuipinjia.setText(list.get(i).getCommentContent());

        if (null!=list.get(i).getCommentImages()&&list.get(i).getCommentImages().size()>0) {
            for (ImgInfo fo : list.get(i).getCommentImages()) {
                list1.add(ServerUrl.mainUrl + fo.getFilePath());
            }
                adapter.notifyDataSetChanged();
        }
        float rate = Float.parseFloat(list.get(i).getCommentLevel());
        viewHloder.rating_comment_peva.setRating(rate);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

   static class ViewHloder extends RecyclerView.ViewHolder {
       ImageView iv_yonghui,iv_star1,iv_star2,iv_star3,iv_star4,iv_star5;
       TextView tv_name_nick,tv_date,tv_yonghuipinjia;
       RecyclerView recyclerView_imgs;
       private RatingBar rating_comment_peva;
        public ViewHloder(View itemView) {
            super(itemView);
            iv_yonghui= (ImageView) itemView.findViewById(R.id.iv_yonghuitouxiang);
            iv_star1= (ImageView) itemView.findViewById(R.id.star_1);
            iv_star2= (ImageView) itemView.findViewById(R.id.star_2);
            iv_star3= (ImageView) itemView.findViewById(R.id.star_3);
            iv_star4= (ImageView) itemView.findViewById(R.id.star_4);
            iv_star5= (ImageView) itemView.findViewById(R.id.star_5);
            tv_name_nick= (TextView) itemView.findViewById(R.id.tv_name_nick);
            tv_date= (TextView) itemView.findViewById(R.id.tv_date);
            tv_yonghuipinjia= (TextView) itemView.findViewById(R.id.tv_yonghuipinjia);
            recyclerView_imgs= (RecyclerView) itemView.findViewById(R.id.recyclerView_imgs);
            rating_comment_peva = (RatingBar) itemView.findViewById(R.id.rating_comment_peva);
        }
    }
}
