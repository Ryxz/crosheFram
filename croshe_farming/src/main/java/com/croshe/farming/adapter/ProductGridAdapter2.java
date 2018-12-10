package com.croshe.farming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.FramCultivationEntity;
import com.croshe.farming.Entity.ProductInfo;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class ProductGridAdapter2  extends BaseAdapter {

    private List<FramCultivationEntity> list;
    private Context context;
    private int tag = 0;
    private int contextview = 1;
    private int imgview = 0;


    public ProductGridAdapter2(Context context, List<FramCultivationEntity> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ProductView productView;
        if (convertView == null){
            productView = new ProductView();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product_grid, null);
            productView.img_choose = (ImageView) convertView.findViewById(R.id.img_choose);
            productView.text_product_name = (TextView) convertView.findViewById(R.id.text_product_name);
            productView.img_product_grid = (ImageView) convertView.findViewById(R.id.img_product_grid);
            productView.relative_product = (RelativeLayout) convertView.findViewById(R.id.relative_product);
            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams)  productView.relative_product.getLayoutParams(); //取控件textView当前的布局参数
            linearParams.height = 1110;
            linearParams.width = 250;
            productView.relative_product.setLayoutParams(linearParams);
            final ProductInfo productInfo = list.get(position).getPoductModel();
            if (!StringUtils.isEmpty(productInfo.getProductSmallImage())) {
                String img = productInfo.getProductSmallImage();
                ImageLoader.getInstance().displayImage(ServerUrl.mainUrl + img, productView.img_product_grid, AppContext.image_display_options);
            }
            productView.text_product_name.setText(productInfo.getProductName());
            if (tag == 0) {
                productInfo.setCheck(false);
            }
            productView.img_product_grid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).getPoductModel().setCheck(false);
                    }
                    list.get(position).getPoductModel().setCheck(true);
                    notifyDataSetChanged();
                    tag =1 ;
                }


            });
            if (productInfo.isCheck()) {
                productView.img_choose.setVisibility(View.VISIBLE);
            }else {
                productView.img_choose.setVisibility(View.GONE);
            }
            convertView.setTag(productView);
        }else {
            productView = (ProductGridAdapter2.ProductView) convertView.getTag();
        }


        return convertView;
    }
    public class ProductView {
        private ImageView img_product_grid, img_choose;
        private TextView text_product_name;
        private RelativeLayout relative_product;
    }

}