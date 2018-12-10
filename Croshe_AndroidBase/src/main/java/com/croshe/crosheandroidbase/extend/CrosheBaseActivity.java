package com.croshe.crosheandroidbase.extend;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.croshe.crosheandroidbase.R;

import java.util.ArrayList;

/**
 * 视图基类
 */

public abstract class CrosheBaseActivity extends AppCompatActivity implements View.OnClickListener {

    public Context context;
    public ImageView croshe_iv_back, croshe_iv_right;
    public TextView croshe_tv_right_title, croshe_tv_title, croshe_tv_left_title;
    public LinearLayout croshe_ll_back, croshe_ll_right;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    /**
     * 简化findbyId
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T f(int id) {
        return (T) findViewById(id);
    }

    /**
     * 初始化tool
     */
    public void initToolBar() {
        try {
            croshe_iv_back = f(R.id.croshe_iv_back);
            croshe_tv_right_title = f(R.id.croshe_tv_right_title);
            croshe_tv_title = f(R.id.croshe_tv_title);
            croshe_iv_right = f(R.id.croshe_iv_right);
            croshe_tv_left_title = f(R.id.croshe_tv_left_title);
            croshe_ll_back = f(R.id.croshe_ll_back);
            croshe_ll_right = f(R.id.croshe_ll_right);
            croshe_ll_back.setOnClickListener(this);
            croshe_ll_right.setOnClickListener(this);
        } catch (Exception e) {
            Toast.makeText(context, "未加载toolbar布局", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        croshe_tv_title.setText(title);
    }

    /**
     * 设置返回图标
     *
     * @param img
     */
    public void setBackImg(int img) {
        croshe_iv_back.setImageResource(img);
    }

    /**
     * 设置右边图标
     *
     * @param img
     */
    public void setRightImg(int img) {
        croshe_iv_right.setVisibility(View.VISIBLE);
        croshe_iv_right.setImageResource(img);
    }

    /**
     * 设置右边文字
     *
     * @param text
     */
    public void setRightText(String text) {
        croshe_tv_right_title.setVisibility(View.VISIBLE);
        croshe_tv_right_title.setText(text);
    }

    /**
     * 设置左边文字
     *
     * @param text
     */
    public void setLeftText(String text) {
        croshe_tv_left_title.setVisibility(View.VISIBLE);
        croshe_tv_left_title.setText(text);
    }


//    public static ArrayList<CrosheBaseActivity> BaseList = new ArrayList<CrosheBaseActivity>();
//    // 一键退出
//    protected void finishAll() {
//        for (CrosheBaseActivity y : BaseList) {
//            y.finish();
//        }
//    }
    public abstract void initView();

    public abstract void initData();

    public abstract void initClick();


}
