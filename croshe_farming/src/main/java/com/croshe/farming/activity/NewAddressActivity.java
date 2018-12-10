package com.croshe.farming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.listener.OnOptionsSelectResultListener;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CroshePickerView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.ReceiveAddInfo;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * 编辑地址/新增地址
 * Created by Administrator on 2017/6/27.
 */

public class NewAddressActivity extends CrosheBaseActivity {
    private EditText receive_name,receive_phone,receive_street,receive_zip_code,receive_address;
    private LinearLayout ll_choose_address,ll_address_submit;
    private TextView add_address,add_address2,add_address3;
    private ImageView img_account_back;
    String name,phone,adr1,adr2,adr3,street,address,code;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_address);
        initView();
        initData();
        initClick();
    }
//
    @Override
    public void initView() {
        img_account_back = (ImageView) findViewById(R.id.img_account_back);
        ll_address_submit = (LinearLayout) findViewById(R.id.ll_address_submit);
        ll_choose_address = (LinearLayout) findViewById(R.id.ll_choose_address);
        receive_name = (EditText) findViewById(R.id.edit_receive_name);
        receive_phone = (EditText) findViewById(R.id.edit_receive_phone);
        receive_street = (EditText) findViewById(R.id.edit_receive_street);
        receive_zip_code = (EditText) findViewById(R.id.edit_receive_zip_code);
        add_address = (TextView) findViewById(R.id.text_add_address1);
        add_address2 = (TextView) findViewById(R.id.text_add_address2);
        add_address3 = (TextView) findViewById(R.id.text_add_address3);
        receive_address = (EditText) findViewById(R.id.edit_receive_address);
    }

    @Override
    public void initData() {
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        adr1 = getIntent().getStringExtra("address1");
        adr2 = getIntent().getStringExtra("address2");
        adr3 = getIntent().getStringExtra("address3");
        street = getIntent().getStringExtra("street");
        address = getIntent().getStringExtra("address");
        code = getIntent().getStringExtra("code");
        if (name!=null){
            receive_name.setText(name);
            receive_phone.setText(phone);
            add_address.setText(adr1);
            add_address2.setText(adr2);
            add_address3.setText(adr3);
            receive_street.setText(street);
            receive_address.setText(address);
            receive_zip_code.setText(code);
        }
    }

    @Override
    public void initClick() {
        ll_choose_address.setOnClickListener(this);
        ll_address_submit.setOnClickListener(this);
        img_account_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_choose_address:
                CroshePickerView.getInstance().showCityPickerView(context, new OnOptionsSelectResultListener() {

                    @Override
                    public void cityInfo(String province, String city, String area) {
                        add_address.setText(province);
                        add_address2.setText(city);
                        add_address3.setText(area);
                    }
                });
                break;
            case R.id.ll_address_submit:
                SendAddress();
                break;
            case R.id.img_account_back:
                finish();
                break;

        }
    }
    public void SendAddress(){
        name = receive_name.getText().toString();
        if (StringUtils.isEmpty(name)) {
            Toast.makeText(context, "请填写收货人姓名",Toast.LENGTH_SHORT).show();
            return;
        }
        phone = receive_phone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            Toast.makeText(context, "请填写收货人号码",Toast.LENGTH_SHORT).show();
            return;
        }else if (phone.length()<11){
            Toast.makeText(context, "号码錯誤",Toast.LENGTH_SHORT).show();
            return;
        }
        street = receive_street.getText().toString();
        if (StringUtils.isEmpty(street)) {
            Toast.makeText(context, "请填写收货人街道",Toast.LENGTH_SHORT).show();
            return;
        }
        code = receive_zip_code.getText().toString();
        if (StringUtils.isEmpty(code)) {
            Toast.makeText(context, "请填写邮编",Toast.LENGTH_SHORT).show();
            return;
        }else if (code.length()<6){
            Toast.makeText(context, "邮编錯誤",Toast.LENGTH_SHORT).show();
            return;
        }
        address = receive_address.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            Toast.makeText(context, "请填写准确收货地址",Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtils.isEmpty(add_address.toString())){
            Toast.makeText(context, "请选择收货人地址",Toast.LENGTH_SHORT).show();
            return;
        }
        adr1 = add_address.getText().toString();
        adr2 = add_address2.getText().toString();
        adr3 = add_address3.getText().toString();

        Map<String,Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("userName",name);
        map.put("userPhone",phone);
        map.put("province",adr1);
        map.put("city",adr2);
        map.put("area",adr3);
        map.put("street", street);
        map.put("postal",code);
        map.put("details",address);
        ServerRequest.requestHttp(this, ServerUrl.addUserAdress, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                context.sendBroadcast(new Intent("refreshAddress"));
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
