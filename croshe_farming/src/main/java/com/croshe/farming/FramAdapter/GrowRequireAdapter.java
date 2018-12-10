package com.croshe.farming.FramAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.GrowEntity;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.R;
import com.croshe.farming.activity.ProductDetailsActivity;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.util.DialogUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class GrowRequireAdapter extends RecyclerView.Adapter<GrowRequireAdapter.GrowView> {
    private Context context;
    private List<GrowEntity> list;

    public GrowRequireAdapter(Context context,List<GrowEntity> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public GrowView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grow_require,parent,false);
        return new GrowView(view);
    }

    @Override
    public void onBindViewHolder(final GrowView holder, int position) {
        final GrowEntity grow = list.get(position);

        if (grow.getActionTitle()!=null){
            ProductInfo product = grow.getProduct();
            if (product!=null){
                holder.text_product_name.setText(product.getProductName());
            }

        }
        if (grow.getActionTypeStr()!=null){
            holder.text_benefit.setText(grow.getActionTitle());
        }
        holder.text_used_num.setText(String.valueOf(grow.getNumber())+" "+grow.getUnitStr());
        if (grow.getActionState() == 0){
            holder.ll_unoperation.setVisibility(View.VISIBLE);
            holder.ll_operation.setVisibility(View.GONE);
        }else {
            holder.ll_unoperation.setVisibility(View.GONE);
            holder.ll_operation.setVisibility(View.VISIBLE);
        }
        holder.ll_unoperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showYesOrCancelDialog(context,((Activity)context).getLayoutInflater(), "是否确定操作",
                        new DialogUtil.OnBtnClickListener() {
                            @Override
                            public void onClick(Dialog dialog) {
                                dialog.dismiss();
                                getActionFarm(grow.getActionId(),grow.getActionType(),grow.getTargetId());
                            }
                        },
                        new DialogUtil.OnBtnClickListener() {
                            @Override
                            public void onClick(Dialog dialog) {
                                dialog.dismiss();
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getActionFarm(int actionId,int type,final int targetId){
        int id = Integer.parseInt(AppContext.getInstance().getUserInfo().getUserId());
        HttpRequest.actionFarm(context, id, actionId, type, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                context.sendBroadcast(new Intent("refreshgrowlist"));
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                if (msg.equals("仓库材料不足，请先前去购买")){
                    context.startActivity(new Intent(context, ProductDetailsActivity.class).putExtra("productId",String.valueOf(targetId)));
                }
            }
        });
    }
    public class GrowView extends RecyclerView.ViewHolder{
        private TextView text_product_name,text_benefit,text_used_num;
        private LinearLayout ll_unoperation,ll_operation;
        public GrowView(View itemView) {
            super(itemView);
            text_product_name = (TextView) itemView.findViewById(R.id.text_product_name);
            text_benefit = (TextView) itemView.findViewById(R.id.text_benefit);
            text_used_num = (TextView) itemView.findViewById(R.id.text_used_num);
            ll_unoperation = (LinearLayout) itemView.findViewById(R.id.ll_unoperation);
            ll_operation = (LinearLayout) itemView.findViewById(R.id.ll_operation);

        }
    }
}
