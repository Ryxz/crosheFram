package com.croshe.farming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.FramCultivationEntity;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.R;
import com.croshe.farming.product.ProductColletionAdapter;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */

public class ProductGridAdapter extends RecyclerView.Adapter<ProductGridAdapter.ProductView> {

    private List<FramCultivationEntity> list;
    private Context context;
    private int tag = 0;
    private int contextview = 1;
    private int imgview = 0;
    private int storeId = 0;
    private int storeCount = 0;

    public ProductGridAdapter(Context context,List<FramCultivationEntity> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ProductView onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_grid,parent,false);
        return new ProductView(view);
    }

    @Override
    public void onBindViewHolder(final ProductView holder, final int position) {
        final ProductInfo productInfo = list.get(position).getProduct();
        if (!StringUtils.isEmpty(productInfo.getProductSmallImage())){
            String img = productInfo.getProductSmallImage();
            ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+img,holder.img_product_grid, AppContext.image_display_options);
        }
        holder.text_product_name.setText(productInfo.getProductName());
        if (tag == 0){
            productInfo.setCheck(false);
        }
        holder.img_product_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0;i<list.size();i++){
                    list.get(i).getProduct().setCheck(false);
                    if (i == position){
                        list.get (position).getProduct().setCheck(true);
                    }
                }
                notifyDataSetChanged();
                tag = 1;
            }
        });
        if (productInfo.isCheck()){
            storeId = list.get(position).getStoreId();
            storeCount = list.get(position).getNumber();
            holder.img_choose.setVisibility(View.VISIBLE);
        }
        if (!productInfo.isCheck()) {
            holder.img_choose.setVisibility(View.GONE);
        }
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setStoreCount(int storeCount) {
        this.storeCount = storeCount;
    }

    public int getStoreId() {
        return storeId;
    }

    public int getStoreCount() {
        return storeCount;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ProductView extends RecyclerView.ViewHolder{
        private ImageView img_product_grid,img_choose;
        private TextView text_product_name;

        public ProductView(View itemView) {
            super(itemView);
            img_choose = (ImageView) itemView.findViewById(R.id.img_choose);
            text_product_name = (TextView) itemView.findViewById(R.id.text_product_name);
            img_product_grid = (ImageView) itemView.findViewById(R.id.img_product_grid);

        }
    }
}
