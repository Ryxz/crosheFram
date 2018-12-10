package com.croshe.farming.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.farming.Entity.BalanceInfo;
import com.croshe.farming.Entity.ReceiveAddInfo;
import com.croshe.farming.R;
import com.croshe.farming.activity.ExpenditureDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class BalanceDetailAdapter extends RecyclerView.Adapter<BalanceDetailAdapter.DetailView> {
    private Context context;
    private List<BalanceInfo> list ;

    public BalanceDetailAdapter(Context context,List<BalanceInfo> list){
        this.context = context;
        this.list = list;
    }


    public DetailView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_balancedetail,parent,false);
        return new DetailView(view);
    }

    public void onBindViewHolder(final DetailView holder, int position) {
        final BalanceInfo balanceInfo = list.get(position);
        String a = balanceInfo.getRecordDtails();
        holder.detail_name.setText(balanceInfo.getRecordDtails()+"");
        holder.detail_time.setText(balanceInfo.getRecordDateTime().substring(0,10)+"");
        holder.detail_balance.setText(balanceInfo.getBalance()+"");
        if (balanceInfo.getRecordTypeStr().indexOf("支出")!= -1){
            holder.detail_money.setText("- "+balanceInfo.getRecordMoney());
        }
        holder.ll_detail.setOnClickListener(new View.OnClickListener() {
//            code,type,pay,pay_type,time,balance;
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ExpenditureDetailsActivity.class)
                        .putExtra("code",balanceInfo.getRecordCode())
                        .putExtra("type",balanceInfo.getFlowTypeStr())
                        .putExtra("pay",balanceInfo.getRecordMoney())
                        .putExtra("pay_type",balanceInfo.getPayTypeStr())
                        .putExtra("time",balanceInfo.getRecordDateTime())
                        .putExtra("balance",balanceInfo.getBalance()));
            }
        });
    }

    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class DetailView extends RecyclerView.ViewHolder{
        private TextView detail_name,detail_time,detail_balance,detail_money;
        private LinearLayout ll_detail;

        public DetailView(View itemView) {
            super(itemView);
            ll_detail = (LinearLayout) itemView.findViewById(R.id.ll_detail);
            detail_name = (TextView) itemView.findViewById(R.id.text_detail);
            detail_time = (TextView) itemView.findViewById(R.id.text_detail_time);
            detail_balance = (TextView) itemView.findViewById(R.id.text_detail_balance);
            detail_money = (TextView) itemView.findViewById(R.id.text_detail_money);

        }
    }
}

