package com.croshe.farming.product;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.CollectionEntity;
import com.croshe.farming.R;
import com.croshe.farming.activity.ProductDetailsActivity;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */

public class ProductColletionAdapter extends RecyclerView.Adapter<ProductColletionAdapter.ColletionView> {
    private Context context;
    private List<CollectionEntity> list;
    public ProductColletionAdapter(Context context,List<CollectionEntity> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ColletionView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection,parent,false);
        return new ColletionView(view);
    }

    @Override
    public void onBindViewHolder(ColletionView holder, final int position) {
        final CollectionEntity collection = list.get(position);
        String imgpath = collection.getProductImg();
        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath,holder.img_collection, AppContext.image_display_options);
        holder.collection_style.setText("￥"+String.valueOf(list.get(position).getProductPrice()));
        holder.collection_name.setText(collection.getProductName());
        holder.ll_collection_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProductDetailsActivity.class)
                        .putExtra("productId",String.valueOf(list.get(position).getTargetId())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ColletionView extends RecyclerView.ViewHolder{
        private ImageView img_collection;
        private TextView collection_name,collection_style;
        private LinearLayout ll_collection_buy;

        public ColletionView(View itemView) {
            super(itemView);
            img_collection = (ImageView) itemView.findViewById(R.id.img_collection);
            collection_name = (TextView) itemView.findViewById(R.id.text_collection_name);
            collection_style = (TextView) itemView.findViewById(R.id.text_collection_style);
            ll_collection_buy = (LinearLayout) itemView.findViewById(R.id.ll_collection_buy);

        }
    }
}
