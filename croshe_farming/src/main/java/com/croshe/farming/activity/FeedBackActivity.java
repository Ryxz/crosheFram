package com.croshe.farming.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.croshe.crosheandroidbase.activity.ImagePagerActivity;
import com.croshe.crosheandroidbase.activity.SelectImageActivity;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.DensityUtils;
import com.croshe.crosheandroidbase.util.ImageUtils;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.crosheandroidbase.view.Toasts;
import com.croshe.farming.AppContext;
import com.croshe.farming.R;
import com.croshe.farming.View.MyGridView;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;
import com.croshe.farming.util.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 意见反馈
 * Created by Administrator on 2017/6/27.
 */

public class FeedBackActivity extends CrosheBaseActivity {
    private LinearLayout ll_feedtype1,ll_feedtype2,ll_feedtype3,ll_feedtype4,ll_feed_submit;
    private EditText edit_feed;
    private ImageView img_account_back;
    private TextView text_wordnums;
    private String adviceType=Constant.OpinionType01;
    List<String> listImgZJ = new ArrayList<>();
    List<String> listImg = new ArrayList<>();
    private GridViewAdapter gridViewAdapter;
    private MyGridView gview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_feedback);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        listImg.add("");
        ll_feedtype1 = (LinearLayout) findViewById(R.id.ll_feedtype1);
        ll_feedtype2 = (LinearLayout) findViewById(R.id.ll_feedtype2);
        ll_feedtype3 = (LinearLayout) findViewById(R.id.ll_feedtype3);
        ll_feedtype4 = (LinearLayout) findViewById(R.id.ll_feedtype4);
        edit_feed = (EditText) findViewById(R.id.edit_feed);
        text_wordnums = (TextView) findViewById(R.id.text_wordnums);
        ll_feed_submit = (LinearLayout) findViewById(R.id.ll_feed_submit);
        gview = (MyGridView) findViewById(R.id.gview_send_ss);
        gridViewAdapter = new GridViewAdapter(this, listImg,3);
        gview.setAdapter(gridViewAdapter);
        img_account_back = (ImageView) findViewById(R.id.img_account_back);

        edit_feed.addTextChangedListener(new TextWatcher(){

            //顾名思义，是文本改变后你想做什么？在此方法中加相应的代码
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                //设定在EditText里所输入的数据同步显示在TextView
                text_wordnums.setText(String.valueOf(edit_feed.getText().toString().length()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                // TODO Auto-generated method stub

            }

        });
    }

    @Override
    public void initData() {
        ll_feedtype1.setBackgroundResource(R.color.orange);
        ll_feedtype2.setBackgroundResource(R.color.white);
        ll_feedtype3.setBackgroundResource(R.color.white);
        ll_feedtype4.setBackgroundResource(R.color.white);
    }

    @Override
    public void initClick() {
        ll_feedtype1.setOnClickListener(this);
        ll_feedtype2.setOnClickListener(this);
        ll_feedtype3.setOnClickListener(this);
        ll_feedtype4.setOnClickListener(this);
        edit_feed.setOnClickListener(this);
        img_account_back.setOnClickListener(this);
        text_wordnums.setOnClickListener(this);
        ll_feed_submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_feedtype1:
                adviceType= Constant.OpinionType01;
                ll_feedtype1.setBackgroundResource(R.color.orange);
                ll_feedtype2.setBackgroundResource(R.color.white);
                ll_feedtype3.setBackgroundResource(R.color.white);
                ll_feedtype4.setBackgroundResource(R.color.white);
                break;
            case R.id.ll_feedtype2:
                adviceType= Constant.OpinionType02;
                ll_feedtype2.setBackgroundResource(R.color.orange);
                ll_feedtype1.setBackgroundResource(R.color.white);
                ll_feedtype3.setBackgroundResource(R.color.white);
                ll_feedtype4.setBackgroundResource(R.color.white);
                break;
            case R.id.ll_feedtype3:
                adviceType= Constant.OpinionType03;
                ll_feedtype3.setBackgroundResource(R.color.orange);
                ll_feedtype1.setBackgroundResource(R.color.white);
                ll_feedtype2.setBackgroundResource(R.color.white);
                ll_feedtype4.setBackgroundResource(R.color.white);
                break;
            case R.id.ll_feedtype4:
                adviceType= Constant.OpinionType04;
                ll_feedtype4.setBackgroundResource(R.color.orange);
                ll_feedtype2.setBackgroundResource(R.color.white);
                ll_feedtype3.setBackgroundResource(R.color.white);
                ll_feedtype1.setBackgroundResource(R.color.white);
                break;
            case R.id.ll_feed_submit:
                submit();
                break;
            case R.id.img_account_back:
                finish();
                break;
        }
    }



//    adviceUserId 用户id：
//
//    adviceType 问题种类 {int} 查看路径：showEnums?enumName=AdviceTypeEnum：
//
//    adviceContent 反馈的内容：
//
//    adviceImages 反馈的文件

    public void submit(){
        if (null != listImgZJ && listImgZJ.size() > 0) {
        } else {
            Toast.makeText(context,"至少上传1张图片",Toast.LENGTH_SHORT).show();
            return;
        }
        String content = edit_feed.getText().toString();
        if (StringUtils.isEmpty(content)) {
            Toast.makeText(context, "请填写内容",Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("adviceUserId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("adviceType",adviceType);
        map.put("adviceContent",content);
        for (int i = 0; i < listImgZJ.size(); i++) {
            map.put("adviceImage[" + i + "]", new File(listImg.get(i).replace("file://", "")));
            ImageUtils.doCompressImage(listImg.get(i).replace("file://", ""), listImg.get(i).replace("file://", ""));
        }
        ServerRequest.requestHttp(context, ServerUrl.addAdvice, map, "", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();

            }
        });
    }

    int imgContent = 3;
    //选择图片
    public class GridViewAdapter extends BaseAdapter {
        Context context;
        List<String> list;
        int Multiple;

        public GridViewAdapter(Context _context, List<String> _list,int Multiple) {
            this.list = _list;
            this.context = _context;
            this.Multiple = Multiple;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.item_photo, null);
            ImageView iv_photo = (ImageView) convertView.findViewById(R.id.iv_photo);
            ImageView img_close = (ImageView) convertView.findViewById(R.id.img_close);
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) iv_photo.getLayoutParams(); //取控件textView当前的布局参数
            linearParams.height = (int) (DensityUtils.getWidthInPx(context) / 3);
            linearParams.width = (int) (DensityUtils.getWidthInPx(context) / 3);// 控件的宽强制设成30
            iv_photo.setLayoutParams(linearParams);
            if (!StringUtils.isEmpty(list.get(position))) {
                ImageLoader.getInstance().displayImage(list.get(position), iv_photo, AppContext.image_display_options);
                File file = new File(list.get(position).replace("file://", ""));
            }

            if (!StringUtils.isEmpty(list.get(position))) {
                img_close.setVisibility(View.VISIBLE);
            } else {
                img_close.setVisibility(View.GONE);
            }

            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listImgZJ.contains(list.get(position))) {
                        imgContent++;
                        listImgZJ.remove(list.get(position));
                        listImg.clear();
                        listImg.addAll(listImgZJ);
                        if (listImgZJ.size() != 3) {
                            listImg.add("");
                        }

                        notifyDataSetChanged();
                    }


                }
            });
            iv_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!StringUtils.isEmpty(list.get(position))) {
                        String[] strImg = new String[listImgZJ.size()];
                        for (int i = 0; i < listImgZJ.size(); i++) {
                            strImg[i] = listImgZJ.get(i);
                        }
                        context.startActivity(new Intent(context, ImagePagerActivity.class).putExtra("image_index", position).putExtra("image_urls", strImg));

                    } else {
                        if (position + 1 <= 3) {
                            selectImages(imgContent);
                        }
                    }
                }
            });

            return convertView;
        }

    }

    /**
     * 选择用户图片
     */
    public void selectImages(int imgContent) {
        startActivityForResult(new Intent(context, SelectImageActivity.class).putExtra("count", imgContent), 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == -1) {
                String[] strImg = (String[]) data.getExtras().get("paths");
                if (null != strImg && strImg.length > 0) {
                    listImg.clear();
                    imgContent = imgContent - strImg.length;
                    for (int i = 0; i < strImg.length; i++) {
                        listImgZJ.add(strImg[i]);
                    }
                    listImg.addAll(listImgZJ);
                    if (listImg.size() != 3) {
                        listImg.add("");
                    }
                    gridViewAdapter.notifyDataSetChanged();
                }

            }

        }
    }
}
