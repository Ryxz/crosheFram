package com.croshe.crosheandroidbase.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.croshe.crosheandroidbase.CroSheAppContext;
import com.croshe.crosheandroidbase.R;
import com.croshe.crosheandroidbase.util.BaseAppUtils;
import com.croshe.crosheandroidbase.util.DensityUtils;
import com.croshe.crosheandroidbase.util.GlideUtil;
import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.SaveCallback;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;


/**
 * 图片裁剪
 */
public class CropImageActivity extends Activity {


    public final static String EXTRA_IMAGE_PATH = "image_path";
    public final static String EXTRA_IMAGE_WIDTH = "image_width";
    public final static String EXTRA_IMAGE_HEIGHT = "image_height";
    public final static String EXTRA_IMAGE_QUALITY = "image_quality";
    public final static String EXTRA_CROP_FREE = "image_crop_free";
    private CropImageView cropImageView;
    private String imagePath;

    private Bundle bundle;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_crop_image);
        bundle = getIntent().getExtras();
        imagePath = bundle.getString(EXTRA_IMAGE_PATH);
        initView();
    }

    public void initView() {
        cropImageView = (CropImageView) findViewById(R.id.cropImageView);
        cropImageView.setMinFrameSizeInDp(150);

        if (bundle.getBoolean(EXTRA_CROP_FREE, true)) {
            cropImageView.setCropMode(CropImageView.CropMode.FREE);
        } else {
            cropImageView.setCropMode(CropImageView.CropMode.CUSTOM);
        }
        cropImageView.setCompressFormat(Bitmap.CompressFormat.JPEG);
        cropImageView.setCompressQuality(bundle.getInt(EXTRA_IMAGE_QUALITY, 100));

        cropImageView.setOutputMaxSize(DensityUtils.dip2px(context, bundle.getInt(EXTRA_IMAGE_WIDTH, 120)), DensityUtils.dip2px(context, bundle.getInt(EXTRA_IMAGE_HEIGHT, 120)));


        String path = imagePath;

        if (!imagePath.startsWith("file") && !imagePath.startsWith("http")) {
            path = "file://" + imagePath;
        }
        ImageLoader.getInstance().loadImage(path, CroSheAppContext.image_display_options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                cropImageView.setImageBitmap(loadedImage);
            }
        });


    }

    public void onClickByBase(View view) {
        if (view.getId() == R.id.sllBack) {
            this.finish();
        }
    }


    public void onClickByCrop(View v) {
        int i = v.getId();
        if (i == R.id.sllConfirm) {
            final File file = new File(BaseAppUtils.getAppCacheRootDirectory(context) + "/Crop/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cropImageView.startCrop(Uri.fromFile(file), null, new SaveCallback() {
                @Override
                public void onSuccess(Uri outputUri) {
                    Intent data = new Intent();
                    data.putExtra(EXTRA_IMAGE_PATH, file.getAbsolutePath());
                    setResult(RESULT_OK, data);

//                    EventBus.getDefault().post(EventDefault.build(CropImage, file.getAbsolutePath()));
                    finish();
                }

                @Override
                public void onError() {

                }
            });

        }
    }
}
