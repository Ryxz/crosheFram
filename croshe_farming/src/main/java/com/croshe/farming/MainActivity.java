package com.croshe.farming;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.extend.CrosheFragmentBaseActivity;
import com.croshe.crosheandroidbase.util.BaseAppUtils;
import com.croshe.crosheandroidbase.util.OKHttpUtils;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.CrosheBottomTabMenuView;
import com.croshe.farming.Entity.VersionInfo;
import com.flyco.tablayout.listener.OnTabSelectListener;

public class MainActivity extends CrosheFragmentBaseActivity {

    private LinearLayout ll_boottom_tab;
    private Context context;
    Handler handler = new Handler(){

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFragment();
        createMenu();
        registerBoradcastReceiver();
        context = this;
//        checkVersion(context,new Handler(){
//
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                if (msg.what == 0) {
//                } else if (msg.what == 1) {
//                    finish();
//                } else if (msg.what == 2) {
//                } else if (msg.what == 3) {
//                    finish();
//                }
//            }
//        });
    }


    /**
     * 创建碎片
     */
    public void createFragment() {
        Fragment[] mFragment = new Fragment[5];
        mFragment[0] = this.mFragmentManager
                .findFragmentById(R.id.fragment_01);
        mFragment[1] = this.mFragmentManager
                .findFragmentById(R.id.fragment_02);
        mFragment[2] = this.mFragmentManager
                .findFragmentById(R.id.fragment_03);
        mFragment[3] = this.mFragmentManager
                .findFragmentById(R.id.fragment_04);
        mFragment[4] = this.mFragmentManager
                .findFragmentById(R.id.fragment_05);
        setFragment(mFragment);
    }

    /**
     * 创建底部菜单
     */
    public void createMenu() {
        ll_boottom_tab = (LinearLayout) findViewById(R.id.ll_boottom_tab);
        String[] titles = new String[]{
                "首页", "集市", "我的农场", "购物车", "个人中心"
        };
        int[] unSelect = new int[]{R.mipmap.img_home, R.mipmap.img_market, R.mipmap.img_fram, R.mipmap.img_shopping, R.mipmap.img_my};
        int[] Select = new int[]{R.mipmap.img_homeselect, R.mipmap.img_marketselect, R.mipmap.img_framselect, R.mipmap.img_shoppingselect, R.mipmap.img_myselect};
        CrosheBottomTabMenuView crosheBottomTabMenuView = new CrosheBottomTabMenuView(MainActivity.this, titles, unSelect, Select, getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                hideFragmentForIndex(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        ll_boottom_tab.addView(crosheBottomTabMenuView);
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("finish");

        // 注册广播
        registerReceiver(myBroadcastReceiver, myIntentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };


    public static void checkVersion(final Context context, final Handler handler) {
        OKHttpUtils.getInstance().get("http://in.croshe.com/app/checkVersion?id=37", new OKHttpUtils.HttpCallBack() {
            @Override
            public void onResult(boolean success, String response) {
                if (success) {
                    if (!StringUtils.isEmpty(response)) {
                        final VersionInfo versionInfo = JSON.parseObject(response, VersionInfo.class);
                        if (null != versionInfo) {
                            if (versionInfo.isSuccess()) {
                                if (AppContext.getInstance().getVersionCode() < versionInfo.getVersionCode()) {
                                    android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(context);
                                    alertDialog.setTitle("系统提示");
                                    alertDialog.setMessage(Html.fromHtml(versionInfo.getVersionDesc() + ""));
                                    alertDialog.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (!org.apache.commons.lang3.StringUtils.isEmpty(versionInfo.getDownUrl())) {
                                                new Handler().post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Intent intent = new Intent();
                                                            intent.setAction("android.intent.action.VIEW");
                                                            Uri content_url = Uri.parse(versionInfo.getDownUrl() + "");
                                                            intent.setData(content_url);
                                                            context.startActivity(intent);
//                                                            BaseAppUtils
//                                                                    .downloadFile(
//                                                                            context,
//                                                                            versionInfo.getDownUrl(),
//                                                                            BaseAppUtils
//                                                                                    .getApplicationName(context),
//                                                                            "正在下载"
//                                                                                    + BaseAppUtils
//                                                                                    .getApplicationName(context)
//                                                                                    + "中…");
                                                        } catch (Exception e) {


                                                        }
                                                    }
                                                });
                                            }
                                            dialog.dismiss();
                                            handler.sendEmptyMessage(1);
                                        }
                                    });

                                    alertDialog.setNegativeButton("", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            if (versionInfo.getVersionImportant() != 1) {
                                                handler.sendEmptyMessage(2);
                                            } else {
                                                handler.sendEmptyMessage(3);
                                            }

                                        }
                                    });
                                    Dialog dialog = alertDialog.create();
                                    dialog.setCancelable(false);
                                    dialog.setCanceledOnTouchOutside(false);
                                    dialog.show();
                                } else {
                                    handler.sendEmptyMessage(0);
                                }
                            }
                        }

                    }//
                }
            }
        });
    }

}
