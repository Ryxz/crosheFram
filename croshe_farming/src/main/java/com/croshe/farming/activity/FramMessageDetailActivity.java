package com.croshe.farming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerUrl;

/**
 * Created by Administrator on 2017/7/15.
 */

public class FramMessageDetailActivity extends CrosheBaseActivity {
    private TextView text_head;
    private LinearLayout ll_back;
    private WebView web_fram;
    private String url;

    WebChromeClient wvcc = new WebChromeClient(){
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
//            text_title.setText("" +title);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fram_msg_detail);
        url = ServerUrl.mainUrl+getIntent().getStringExtra("newsurl");
        initView();
        initData();
        initClick();
        initWeb();
    }

    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("资讯详情");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        web_fram = (WebView) findViewById(R.id.fram_web);
    }

    @Override
    public void initData() {
        web_fram.getSettings().setJavaScriptEnabled(true);
        // 设置Web视图  
        web_fram.setWebViewClient(new MyWebView());
        web_fram.loadUrl(url);
    }

    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back:
                finish();
                break;
        }
    }
    public void initWeb(){
        // 设置setWebChromeClient对象
        web_fram.setWebChromeClient(wvcc);
        // 创建WebViewClient对象
        WebViewClient wvc = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
                web_fram.loadUrl(url);
                //消耗掉这个事件。Android中返回True的即到此为止吧,事件就会不会冒泡传递了，我们称之为消耗掉
                return true;
            }
        };
        web_fram.setWebViewClient(wvc);
    }
    // Web视图  
    private class MyWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
