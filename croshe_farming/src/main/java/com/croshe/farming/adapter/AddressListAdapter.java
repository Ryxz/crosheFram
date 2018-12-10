package com.croshe.farming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.croshe.crosheandroidbase.view.CrosheViewPageView;
import com.croshe.farming.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuyongwei on 16/12/21.
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {
    List<String> list = new ArrayList<>();
    Context context;
    boolean isIntent;

    public AddressListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public AddressListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new AddressListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddressListAdapter.ViewHolder holder, final int position) {

        holder.ll_con.removeAllViews();
        List<String> list1 = new ArrayList<>();
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495541879513&di=62b6b17e2a0a38a7affa4029561733a3&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20170521%2F5bdc2d9d127a40a58ef95d76eb3a6510_th.jpg");
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495541879512&di=b6dd4efecf57aa20ad76e02d3dabf50c&imgtype=0&src=http%3A%2F%2Fimg1.gamedog.cn%2F2013%2F09%2F28%2F43-13092Q646420-51.jpg");
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495541879512&di=333cda512e4b1603f11fc8b33adedc51&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2F20170104%2F6982-fxzkfuk2022813.jpg");
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495541879511&di=73e40662629e22dd486491c3948902e9&imgtype=0&src=http%3A%2F%2Fpic33.nipic.com%2F20130910%2F1754547_121230122000_2.jpg");
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495541879509&di=e85fe1f2d0dfb84c042d6785e707a6e4&imgtype=0&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201602%2F20160221007.jpg");
        CrosheViewPageView crosheViewPageView = new CrosheViewPageView(context, list1, 100);
        holder.ll_con.addView(crosheViewPageView);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll_con;

        public ViewHolder(View itemView) {
            super(itemView);
            ll_con = (LinearLayout) itemView.findViewById(R.id.ll_con);
        }
    }

}
