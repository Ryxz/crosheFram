package com.croshe.crosheandroidbase;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.croshe.crosheandroidbase.activity.SelectImageActivity;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tapadoo.alerter.Alerter;

/**
 * App初始化基类
 */
public class CroSheAppContext extends Application {
    public static CroSheAppContext appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext=this;
        initImageLoader(this);
        appContext = this;
    }

    public static CroSheAppContext getInstance() {
        if (appContext == null)
            return new CroSheAppContext();
        return appContext;
    }

    /**
     * 图片框架初始化
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 1)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024).build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    public static DisplayImageOptions image_display_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.android_base_img_default)
            .showImageForEmptyUri(R.drawable.android_base_img_default)
            .showImageOnFail(R.drawable.android_base_img_default)
            .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2).build();


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

    Alerter alert;

    public void showAlert(Activity context, String title, String content) {
        if (null != alert) {
            alert.hide();
        }
        alert = Alerter.create(context);
        alert.setTitle(title);
        alert.setText(content);
        alert.setBackgroundColor(R.color.colorPrimary);
        alert.setDuration(2000);
        alert.show();
    }
}
