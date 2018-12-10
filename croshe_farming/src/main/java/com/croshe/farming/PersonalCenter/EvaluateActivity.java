package com.croshe.farming.PersonalCenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.croshe.crosheandroidbase.activity.ImagePagerActivity;
import com.croshe.crosheandroidbase.activity.SelectImageActivity;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.ImageUtils;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Listener.AutoLoadScrollListener;
import com.croshe.farming.R;
import com.croshe.farming.activity.OrdeDetailActivity;
import com.croshe.farming.adapter.EvaluateGridAdapter;
import com.croshe.farming.server.HttpRequest;
import com.croshe.farming.server.L;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */

public class EvaluateActivity extends CrosheBaseActivity {
    private ImageView img_evalute,img_carmer;
    private TextView text_head;
    private LinearLayout ll_back,ll_send_evaluate;
    List<String> listImgZJ = new ArrayList<>();
    List<String> listImg = new ArrayList<>();
    private EvaluateGridAdapter evaluateGridAdapter;
    private RecyclerView recycler_evaluate;
    private EditText edit_evaluate;
    private int imgContent = 3;
    private String imgpath;
    private int targetId,targetType;
    private RatingBar rating_comment,rating_wufu,rating_sudu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_evaluate);
        imgpath = getIntent().getStringExtra("imgpath");
        targetType = getIntent().getIntExtra("targetType",0);
        targetId = getIntent().getIntExtra("productId",0);
        initView();
        initData();
        initClick();
        registerBoradcastReceiver();
    }
    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("ondeleEvaluteImg");
        // 注册广播
        registerReceiver(mBroadcastReceiver, myIntentFilter);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("ondeleEvaluteImg")) {
                String imgUrl = intent.getExtras().getString("imgUrl");
                boolean isdel = listImg.contains(imgUrl);
                boolean isdel01 = listImgZJ.contains(imgUrl);
                if (isdel) {
                    listImg.remove(imgUrl);
                }
                if (isdel01) {
                    listImgZJ.remove(imgUrl);
                }
            }

        }
    };

    @Override
    public void initView() {
        text_head = (TextView) findViewById(R.id.text_head);
        text_head.setText("评价");
        img_evalute = (ImageView) findViewById(R.id.img_evalute);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_send_evaluate = (LinearLayout) findViewById(R.id.ll_send_evaluate);
        img_carmer = (ImageView) findViewById(R.id.img_carmer);
        rating_comment = (RatingBar) findViewById(R.id.rating_comment);
        rating_sudu = (RatingBar) findViewById(R.id.rating_sudu);
        rating_wufu = (RatingBar) findViewById(R.id.rating_wufu);
        edit_evaluate = (EditText) findViewById(R.id.edit_evaluate);
        recycler_evaluate = (RecyclerView) findViewById(R.id.recycler_evaluate);
        evaluateGridAdapter = new EvaluateGridAdapter(context, listImg);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_evaluate.setLayoutManager(linearLayoutManager);
        recycler_evaluate.setAdapter(evaluateGridAdapter);
        recycler_evaluate.addOnScrollListener(new AutoLoadScrollListener(ImageLoader.getInstance()));

    }

    @Override
    public void initData() {
        ImageLoader.getInstance().displayImage(ServerUrl.mainUrl+imgpath,img_evalute,AppContext.image_display_options);
    }

    @Override
    public void initClick() {
        ll_back.setOnClickListener(this);
        img_carmer.setOnClickListener(this);
        ll_send_evaluate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.img_carmer:
                selectImages(imgContent);
                break;
            case R.id.ll_send_evaluate:
                comment();
                break;
        }
    }

    /**
     * 回复评论
     */
    public void addComment() {
        float fuwu = rating_wufu.getRating();
        float sudu = rating_sudu.getRating();
        float comment = rating_comment.getRating();
        final String content =edit_evaluate.getText().toString();
        if (StringUtils.isEmpty(content)) {
            Toast.makeText(this, "评价不可为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("commentContent", content);
        for (int i = 0; i < listImg.size(); i++) {
            map.put("talkImage[" + i + "]", new File(listImg.get(i).replace("file://", "")));
            ImageUtils.doCompressImage(listImg.get(i).replace("file://", ""), listImg.get(i).replace("file://", ""));
        }
        map.put("orderId", OrdeDetailActivity.orderId);
        map.put("targetType", 1);
        map.put("targetId", targetId);
        map.put("orderCode",OrdeDetailActivity.orderCode);
        map.put("commentLevel", comment);
        map.put("fuwu", fuwu);
        map.put("peisong", sudu);
//        map.put("parentId", parentId);
//        map.put("toUserId", toUserId);
        ServerRequest.requestHttp(context, ServerUrl.addComment, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                sendBroadcast(new Intent("finishEvaluate"));
                EventBus.getDefault().post("finish");
                finish();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 选择用户图片
     */
    public void selectImages(int imgContent) {
        startActivityForResult(new Intent(context, SelectImageActivity.class).putExtra("count", imgContent), 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        L.d("NIU", "requestCode:" + requestCode + "---resultCode:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == -1) {
                String[] strImg = (String[]) data.getExtras().get("paths");
                if (null != strImg && strImg.length > 0) {
                    listImg.clear();
                    imgContent = imgContent - strImg.length;
                    for (int i = 0; i < strImg.length; i++) {
                        L.d("NIU", strImg[i]);
                        listImgZJ.add(strImg[i]);
                    }
                    listImg.addAll(listImgZJ);
//
                    evaluateGridAdapter.notifyDataSetChanged();
                }

            }

        }
    }
    /**
     * 回复评论
     */
    public void comment(){
        float fuwu = rating_wufu.getRating();
        float sudu = rating_sudu.getRating();
        float comment = rating_comment.getRating();
        final String content =edit_evaluate.getText().toString();
        HttpRequest.addComment(context, content, listImg, OrdeDetailActivity.orderId, targetType, OrdeDetailActivity.orderCode,targetId, comment, fuwu, sudu, new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                sendBroadcast(new Intent("finishEvaluate"));
                EventBus.getDefault().post("finish");
                finish();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
