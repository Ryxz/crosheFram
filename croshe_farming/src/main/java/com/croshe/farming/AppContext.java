package com.croshe.farming;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.croshe.crosheandroidbase.CroSheAppContext;
import com.croshe.crosheandroidbase.activity.SelectImageActivity;
import com.croshe.farming.Entity.UserInfo;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.List;

/**
 * Created by niuyongwei on 17/3/1.
 */

public class AppContext extends CroSheAppContext {
    public static AppContext appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "85b1ec4b3f", true);
        appContext = this;
        MultiDex.install(this);
    }

    public static AppContext getInstance() {
        if (appContext == null)
            return new AppContext();
        return appContext;
    }


    /**
     * 去拍照和相册
     *
     * @param context
     * @param imgContent
     * @param result
     */
    public void goAlbumAndPhotograph(Context context, int imgContent, int result) {
        boolean camera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
        boolean readFile = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
        boolean writeFile = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
//            boolean mount = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS) != PackageManager.PERMISSION_GRANTED;
        if (camera || readFile || writeFile) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0);
        } else {
            ((Activity) context).startActivityForResult(new Intent(context, SelectImageActivity.class).putExtra("count", imgContent), result);
        }
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 保存缓存用户信息
     *
     * @param user
     */
    public  void saveUserInfo(final UserInfo user) {
        if (user != null) {
            Storage.ClearUserInfo();
            Storage.saveUsersInfo(user);
        }
    }

    /**
     * 清除缓存用户信息
     *
     * @param
     */
    public void cleanUserInfo() {
        Storage.ClearUserInfo();
    }


    /**
     * 获取缓存用户信息
     *
     * @return
     */
    public UserInfo getUserInfo() {
        return Storage.GetUserInfo() == null ? new UserInfo() : Storage
                .GetUserInfo();
    }

    /**
     * 获取软件版本号
     *
     * @return
     */
    public int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    public String getVersionName() {
        return getPackageInfo().versionName;
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

//    public void saveUser(String  user) {
//        userList.add(user);
//    }
//    public List<String> getUserList() {
//        return userList;
//    }

    public void removeUser(String s) {

    }

/*
 *网络请求
 * */
public void displayImage(String imgUrl, ImageView imageView) {
    Glide.with(getApplicationContext()).load(imgUrl).placeholder(com.croshe.crosheandroidbase.R.drawable.android_base_img_default).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().crossFade().into(imageView);
}












}
