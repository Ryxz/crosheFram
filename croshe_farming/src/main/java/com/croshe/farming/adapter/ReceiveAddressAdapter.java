package com.croshe.farming.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.ReceiveAddInfo;
import com.croshe.farming.R;
import com.croshe.farming.activity.NewAddressActivity;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.util.DialogUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/6/27.
 */

public class ReceiveAddressAdapter extends RecyclerView.Adapter<ReceiveAddressAdapter.AddressView> {
    private Context context;
    private List<ReceiveAddInfo> list ;
    private int style;
    public ReceiveAddressAdapter(Context context,List<ReceiveAddInfo> list,int style){
        this.context = context;
        this.list = list;
        this.style = style;
    }

    @Override
    public AddressView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receive_address,parent,false);
        return new AddressView(view);
    }

    @Override
    public void onBindViewHolder(final AddressView holder, final int position) {
        final ReceiveAddInfo receiveInfo = list.get(position);
        holder.receiveName.setText(receiveInfo.getUserName());
        String phone = receiveInfo.getUserPhone();
        holder.receivePhone1.setText(phone);
//        holder.receivePhone2.setText(phone.substring(phone.length()-4));
        holder.receiveAddress.setText(receiveInfo.getProvince()+", "+receiveInfo.getCity()
                + ", "+receiveInfo.getArea()+", "+receiveInfo.getStreet()+", "+receiveInfo.getDetails());
        //删除收货地址
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showYesOrCancelDialog(context, ((Activity)context).getLayoutInflater(), "系统提醒", "是否确认删除?",
                        new DialogUtil.OnBtnClickListener() {
                            @Override
                            public void onClick(Dialog dialog) {
                                dialog.dismiss();
                                delAddress(receiveInfo.getAddressId());
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
        //编辑收货地址
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, NewAddressActivity.class)
                        .putExtra("name",receiveInfo.getUserName())
                        .putExtra("phone", receiveInfo.getUserPhone())
                        .putExtra("address1",receiveInfo.getProvince())
                        .putExtra("address2",receiveInfo.getCity())
                        .putExtra("address3",receiveInfo.getArea())
                        .putExtra("street",receiveInfo.getStreet())
                        .putExtra("address",receiveInfo.getDetails())
                        .putExtra("code",receiveInfo.getPostal()));
            }
        });
        holder.ll_select_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAddress(receiveInfo.getAddressId());
                notifyDataSetChanged();
            }
        });

            holder.ll_all_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (style == 1){
//                        makeAddress(receiveInfo.getAddressId());
                        context.sendBroadcast(new Intent("changeAddress"));
                        context.sendBroadcast(new Intent("address")
                                .putExtra("receiveInfo",receiveInfo));
                }
                }
            });

        if ("0".equals(receiveInfo.getAddressState())){
            holder.img_select_adr.setVisibility(View.GONE);
            holder.img_unselect_adr.setVisibility(View.VISIBLE);
        }else if ("1".equals(receiveInfo.getAddressState())){
            holder.img_select_adr.setVisibility(View.VISIBLE);
            holder.img_unselect_adr.setVisibility(View.GONE);
        }

        if (receiveInfo.getAddressType() == 0){
            holder.ll_equal_address.setVisibility(View.GONE);
            holder.ll_service_message.setVisibility(View.VISIBLE);
        }else {
            holder.ll_service_message.setVisibility(View.GONE);
            holder.ll_equal_address.setVisibility(View.VISIBLE);
        }
    }
    //删除地址
    public void delAddress(String addressId){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("addressId",addressId);
        ServerRequest.requestHttp(context, ServerUrl.deleteUserAddress, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                EventBus.getDefault().post("refreshaddress");
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
    //设置默认地址
    public void makeAddress(String addressId){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("addressId",addressId);
        ServerRequest.requestHttp(context, ServerUrl.setDefaultUserAddress, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                if (style == 1){

                }else {
                    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                }
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class AddressView extends RecyclerView.ViewHolder{
        private TextView receiveName,receivePhone1,receivePhone2,receiveAddress,text_moren_address;
        private ImageView img_edit,img_delete;
        private ImageView img_select_adr,img_unselect_adr;
        private LinearLayout ll_select_address,ll_all_address,ll_equal_address,ll_service_message;

        public AddressView(View itemView) {
            super(itemView);
            img_select_adr = (ImageView) itemView.findViewById(R.id.img_select_adr);
            img_unselect_adr = (ImageView) itemView.findViewById(R.id.img_unselect_adr);
            img_edit = (ImageView) itemView.findViewById(R.id.img_edit);
            img_delete= (ImageView) itemView.findViewById(R.id.img_delete);
            receiveName = (TextView) itemView.findViewById(R.id.text_receive_name);
            receivePhone1 = (TextView) itemView.findViewById(R.id.text_phone_01);
            receivePhone2 = (TextView) itemView.findViewById(R.id.text_phone_02);
            receiveAddress = (TextView) itemView.findViewById(R.id.text_receive_address);
            ll_select_address = (LinearLayout) itemView.findViewById(R.id.ll_select_address);
            ll_all_address = (LinearLayout) itemView.findViewById(R.id.ll_all_address);
            text_moren_address = (TextView) itemView.findViewById(R.id.text_moren_address);
            ll_equal_address = (LinearLayout) itemView.findViewById(R.id.ll_equal_address);
            ll_service_message = (LinearLayout) itemView.findViewById(R.id.ll_service_message);
        }
    }
}
