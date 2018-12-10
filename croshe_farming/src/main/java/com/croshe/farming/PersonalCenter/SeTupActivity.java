package com.croshe.farming.PersonalCenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.view.Toasts;
import com.croshe.farming.AppContext;
import com.croshe.farming.Login.LoginActivity;
import com.croshe.farming.R;
import com.croshe.farming.activity.AccountSecurityActivity;
import com.croshe.farming.activity.FeedBackActivity;
import com.croshe.farming.activity.ManageReceiveAddressActivity;
import com.croshe.farming.activity.MessageRemindActivity;
import com.croshe.farming.util.DialogUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class SeTupActivity extends CrosheBaseActivity {
    private LinearLayout ll_settup_1, ll_settup_2, ll_settup_3, ll_settup_4, ll_settup_5, ll_settup_6, ll_settup_7,ll_settup_8;
    private ImageView  iv_right;
    private LinearLayout ll_exit,iv_left;
    private TextView text_cashe;
    private DecimalFormat df = new DecimalFormat("###,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se_tup);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        ll_settup_1 = (LinearLayout) findViewById(R.id.ll_settup_1);
        ll_settup_2 = (LinearLayout) findViewById(R.id.ll_settup_2);
        ll_settup_3 = (LinearLayout) findViewById(R.id.ll_settup_3);
        ll_settup_4 = (LinearLayout) findViewById(R.id.ll_settup_4);
        ll_settup_5 = (LinearLayout) findViewById(R.id.ll_settup_5);
        ll_settup_6 = (LinearLayout) findViewById(R.id.ll_settup_about_ours);
        ll_settup_7 = (LinearLayout) findViewById(R.id.ll_settup_seedback);
        ll_settup_8 = (LinearLayout) findViewById(R.id.ll_settup_8);
        iv_left = (LinearLayout) findViewById(R.id.iv_back_2);
        iv_right = (ImageView) findViewById(R.id.iv_message_3);
        ll_exit = (LinearLayout) findViewById(R.id.ll_exit);
        text_cashe = (TextView) findViewById(R.id.text_cashe);
    }

    @Override
    public void initData() {
        long size = (long) Math.floor(checkCacheSize(new File(ImageLoader.getInstance().getDiskCache().getDirectory().getPath())) / 1024.0 / 1024.0);
        text_cashe.setText(formatSize(size) + "");
    }

    @Override
    public void initClick() {
        ll_exit.setOnClickListener(this);
        iv_left.setOnClickListener(this);
        ll_settup_1.setOnClickListener(this);
        ll_settup_2.setOnClickListener(this);
        ll_settup_3.setOnClickListener(this);
        ll_settup_4.setOnClickListener(this);
        ll_settup_5.setOnClickListener(this);
        ll_settup_6.setOnClickListener(this);
        ll_settup_7.setOnClickListener(this);
        ll_settup_8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_exit:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SeTupActivity.this);
                alertDialog.setTitle("系統提示");
                alertDialog.setMessage("确定要退出吗？");
                alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AppContext.getInstance().cleanUserInfo();
                                Intent in1  = new Intent("finish");
                                sendBroadcast(in1);
                                Intent in = new Intent(SeTupActivity.this, LoginActivity.class);
                                startActivity(in);
                                finish();
                                dialog.dismiss();
                            }
                        });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.create().show();
                break;
            case R.id.iv_back_2:
                finish();
                break;
            /**
             * 地址管理
             */
            case R.id.ll_settup_1:
                startActivity(new Intent(this, ManageReceiveAddressActivity.class));
                break;

            case R.id.ll_settup_2:
                /**
                 * 账号安全
                 */
                startActivity(new Intent(this, AccountSecurityActivity.class));
                break;
            case R.id.ll_settup_3:
                /**
                 * 通知消息提醒
                 */
                startActivity(new Intent(this, MessageRemindActivity.class));
                break;
            case R.id.ll_settup_4:
                DialogUtil.showYesOrCancelDialog(context, ((Activity)context).getLayoutInflater(), "系统提醒", "确定消除缓存",
                        new DialogUtil.OnBtnClickListener() {
                            @Override
                            public void onClick(Dialog dialog) {
                                dialog.dismiss();
                                ImageLoader.getInstance().clearDiskCache();
                                Toast.makeText(context,"清除成功",Toast.LENGTH_SHORT).show();
                                text_cashe.setText("0MB");
                            }
                        },
                        new DialogUtil.OnBtnClickListener() {
                            @Override
                            public void onClick(Dialog dialog) {
                                dialog.dismiss();
                            }
                        });
                break;
            case R.id.ll_settup_about_ours:
                startActivity(new Intent(this, AboutOursActivity.class));
                break;
            case R.id.ll_settup_seedback:
                /**
                 * 意见反馈
                 */
                startActivity(new Intent(this, FeedBackActivity.class));
                break;
            case R.id.ll_settup_5:
                startActivity(new Intent(this, NormalQuestionAcitivity.class));
                break;
            case R.id.ll_settup_8:
                startActivity(new Intent(this,SetPayPasswordActivity.class));
                break;
            }



        }
    public long checkCacheSize(File file) {
        long currLength = 0;
        if (file.isDirectory()) {
            for (File fl : file.listFiles()) {
                currLength += checkCacheSize(fl);
            }
        } else {
            currLength += file.length();
        }
        return currLength;
    }
    public String formatSize(long size) {
        BigDecimal bd = new BigDecimal(size);
        return df.format(bd) + "MB";
    }
}



