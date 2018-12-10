package com.croshe.farming.productAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.croshe.farming.Entity.SignListEntity;
import com.croshe.farming.R;
import com.croshe.farming.util.SelfDateTimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/15.
 */

public class SignListAdapter extends RecyclerView.Adapter<SignListAdapter.SignView> {
    private String sysDate;
    private List<SignListEntity> signList;
    private Context context;

    public SignListAdapter(Context context, List<SignListEntity> signList){
        this.context = context;
        this.signList = signList;
    }

    @Override
    public SignView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sign_list,parent,false);
        return new SignView(view);
    }

    @Override
    public void onBindViewHolder(SignView holder, int position) {
        if (signList.get(position).getIfSign() == 0){
            holder.img_sign.setVisibility(View.GONE);
            holder.img_unsign.setVisibility(View.VISIBLE);
            holder.text_day_jifen.setVisibility(View.GONE);
        }else {
            holder.img_sign.setVisibility(View.VISIBLE);
            holder.img_unsign.setVisibility(View.GONE);
            holder.text_day_jifen.setVisibility(View.VISIBLE);
            holder.text_day_jifen.setText("获得"+signList.get(position).getSignScore()+"积分");
        }
//        tvDateTime.setText(SelfDateTimeUtils.formatDateTime(signTime, "MM.dd"));
        holder.text_sign_time.setText(SelfDateTimeUtils.formatDateTime(signList.get(position).getSignTime(),"MM月dd号"));
    }

    @Override
    public int getItemCount() {
        return signList.size();
    }

    public class SignView extends RecyclerView.ViewHolder{
        private ImageView img_sign,img_unsign;
        private TextView text_sign_time,text_day_jifen;

        public SignView(View itemView) {
            super(itemView);
            img_sign = (ImageView) itemView.findViewById(R.id.img_sign);
            img_unsign = (ImageView) itemView.findViewById(R.id.img_unsign);
            text_sign_time = (TextView) itemView.findViewById(R.id.text_sign_time);
            text_day_jifen = (TextView) itemView.findViewById(R.id.text_day_jifen);
        }
    }
}
