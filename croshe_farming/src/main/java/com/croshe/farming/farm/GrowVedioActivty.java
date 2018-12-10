package com.croshe.farming.farm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerUrl;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2017/7/12.
 */

public class GrowVedioActivty extends CrosheBaseActivity{
    private JCVideoPlayerStandard jcVideoPlayerStandard;
    private String filePath;
    private String filename;
    private TextView text_head;
    private LinearLayout ll_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_vedio);
        filePath = ServerUrl.mainUrl+getIntent().getStringExtra("filePath");
        filename = getIntent().getStringExtra("filename");
        initView();
        initClick();
    }

    @Override
    public void initView() {
        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
//        str=str.Substring(str.Length-i);
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("");
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        String str = filePath.substring(filePath.length()-4);
        if (str.equals(".mp4")){
            jcVideoPlayerStandard.setUp(filePath , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, filename);
        }
//        jcVideoPlayerStandard.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
    }

    @Override
    public void initData() {

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
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
