package com.croshe.farming.PersonalCenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.AppContext;
import com.croshe.farming.R;

/**
 * Created by Administrator on 2017/7/31.
 */

public class AboutOursActivity extends CrosheBaseActivity {
    private TextView text_head,text_version;
    private LinearLayout ll_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about_ours);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("关于我们");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        text_version = (TextView) findViewById(R.id.text_version);
    }

    @Override
    public void initData() {
        text_version.setText("版本号："+AppContext.getInstance().getVersionName());
    }

    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
