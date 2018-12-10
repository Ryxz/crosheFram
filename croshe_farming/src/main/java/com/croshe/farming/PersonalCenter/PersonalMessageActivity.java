package com.croshe.farming.PersonalCenter;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.CroSheAppContext;
import com.croshe.crosheandroidbase.activity.CropImageActivity;
import com.croshe.crosheandroidbase.activity.SelectImageActivity;
import com.croshe.crosheandroidbase.entity.ProvinceBean;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.listener.OnOptionsSelectResultListener;
import com.croshe.crosheandroidbase.view.CroshePickerView;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.UserInfo;
import com.croshe.farming.R;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.L;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.util.DialogUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 个人信息
 * Created by Administrator on 2017/7/31.
 */

public class PersonalMessageActivity extends CrosheBaseActivity {
    private TextView text_head,text_com,text_phone,text_head3,edit_sex;
    private LinearLayout ll_back,ll_change_sex;
    private EditText edit_name;
    private ImageView img_head;
    private boolean isChange = false;
    private String imgpath,sex;
    private List<String> listImgZJ = new ArrayList<>();
    List<String> listImg = new ArrayList<>();
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_personal_msg);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("个人信息");
        text_head3 = (TextView) findViewById(R.id.text_head3);
        text_head3.setText("修改");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_sex = (TextView) findViewById(R.id.edit_sex);
        text_com = (TextView) findViewById(R.id.text_com);
        text_phone = (TextView) findViewById(R.id.text_phone);
        img_head = (ImageView) findViewById(R.id.img_head);
        ll_change_sex = (LinearLayout) findViewById(R.id.ll_change_sex);
        edit_name.setCursorVisible(false);
        edit_sex.setCursorVisible(false);
        edit_name.setEnabled(false);
        edit_sex.setEnabled(false);
    }

    @Override
    public void initData() {
        edit_name.setText(AppContext.getInstance().getUserInfo().getUserNickName());
        if (AppContext.getInstance().getUserInfo().getUserSex().equals("1")) {
            edit_sex.setText("女");
            sex = "1";
        }else {
            edit_sex.setText("男");
            sex = "0";
        }
        text_phone.setText(AppContext.getInstance().getUserInfo().getUserPhone());
        text_com.setText(AppContext.getInstance().getUserInfo().getUserEmail());
        imgpath = AppContext.getInstance().getUserInfo().getUserHeadImg();
        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath,img_head,AppContext.image_display_options);
    }

    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
        text_head3.setOnClickListener(this);
        img_head.setOnClickListener(this);
        ll_change_sex.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back:
                finish();
                break;
            case R.id.text_head3:
                if (!isChange){
                    text_head3.setText("确定");
                    edit_name.setCursorVisible(true);
                    edit_name.setEnabled(true);
                    isChange = true;
                }else {
                    DialogUtil.showYesOrCancelDialog(context,LayoutInflater.from(context), "系统提醒", "确认修改", new DialogUtil.OnBtnClickListener() {
                        @Override
                        public void onClick(Dialog dialog) {
                            text_head3.setText("修改");
                            if (StringUtils.isEmpty(edit_name.getText().toString())){
                                Toast.makeText(context,"姓名不可为空",Toast.LENGTH_SHORT).show();
                                return;
                            }else {
                                name = edit_name.getText().toString();
                            }
                            modifyUser();
                            dialog.dismiss();
                        }
                    }, new DialogUtil.OnBtnClickListener() {
                        @Override
                        public void onClick(Dialog dialog) {
                            text_head3.setText("修改");
                            dialog.dismiss();
                        }
                    });
                    edit_name.setCursorVisible(false);
                    edit_name.setEnabled(false);
                    isChange = false;
                }

                break;
            case R.id.img_head:
                if (isChange){
                    AppContext.getInstance().goAlbumAndPhotograph(context, 1, 2000);
                }
                break;
            case R.id.ll_change_sex:
                if (isChange){
                    ArrayList<ProvinceBean> options1Items = new ArrayList<>();
                    //选项1
                    options1Items.add(new ProvinceBean(0, "男", "", ""));
                    options1Items.add(new ProvinceBean(1, "女", "", ""));
                    CroshePickerView.getInstance().showCondition(context, "性别", options1Items, new OnOptionsSelectResultListener() {
                        @Override
                        public void cityInfo(String province, String city, String area) {
                            edit_sex.setText(province);
                            if (province == "男"){
                                sex = "0";
                            }else {
                                sex = "1";
                            }
                        }
                    });
                }
                break;
        }
    }
    public void modifyUser(){
        HttpRequest.modifyUser(context, name, sex, imgpath, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                sendBroadcast(new Intent("changeUserInfo")
                        .putExtra("newImg",imgpath)
                        .putExtra("newName",name));
                Refresh();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
    public void Refresh(){
        HttpRequest.refreshUser(context, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                UserInfo userInfo = JSON.parseObject(json,UserInfo.class);
                AppContext.getInstance().saveUserInfo(userInfo);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            if (resultCode == -1) {
                String[] paths = (String[]) data.getExtras().get("paths");
                if (null != paths && paths.length > 0) {
                    Intent intent = new Intent(context, CropImageActivity.class);
                    intent.putExtra(CropImageActivity.EXTRA_IMAGE_PATH, paths[0]);
                    startActivityForResult(intent, 3000);
                }

            }
        } else if (requestCode == 3000) {
            if (resultCode == -1) {
                imgpath = (String) data.getExtras().get("image_path");
                ImageLoader.getInstance().displayImage("file://" + imgpath, img_head, AppContext.image_display_options);
//                AppContext.getInstance().displayImage("file://" + imgpath, img_head);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("系統提示");
                alertDialog.setMessage("是否立即上传头像");
                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        modifyUser();
                    }
                });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+AppContext.getInstance().getUserInfo().getUserHeadImg(), img_head);
                    }
                });
                alertDialog.create().show();
            }

        }
    }

}
