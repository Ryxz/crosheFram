package com.croshe.farming.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.croshe.crosheandroidbase.activity.CropImageActivity;
import com.croshe.crosheandroidbase.extend.CrosheBaseActivity;
import com.croshe.crosheandroidbase.util.StringUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.R;
import com.croshe.farming.server.ServerRequest;
import com.croshe.farming.server.ServerResultListener;
import com.croshe.farming.server.ServerUrl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RegisteredTwoActivity extends CrosheBaseActivity implements View.OnClickListener{
     private ImageView iv_photo,iv_back;
    private String paths;
    private EditText et_name,et_youxiang,et_pwd;
    private ImageView iv_eye;
    private Button btu_man,btu_nv,btu_zhuce;
    private int genber=0;
    private boolean issetelt;
    private CheckBox cb_fuwu;
    private String phone;
    private String yanzheng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
        initData();
        initClick();
    }

    @Override
    public void initView() {
        iv_photo= (ImageView) findViewById(R.id.iv_tianjiatongxiang);
        et_name= (EditText) findViewById(R.id.et_nicheng);
        et_youxiang= (EditText) findViewById(R.id.et_youxiang);
        et_pwd= (EditText) findViewById(R.id.et_mima);
        iv_eye= (ImageView) findViewById(R.id.yingcangmima);
        btu_man= (Button) findViewById(R.id.bt_man);
        btu_nv= (Button) findViewById(R.id.bt_nv);
        btu_zhuce= (Button) findViewById(R.id.bt_zhuce);
        iv_back= (ImageView) findViewById(R.id.iv_left);
        cb_fuwu= (CheckBox) findViewById(R.id.cb_fuwu);
    }

    @Override
    public void initData() {
    Intent in =getIntent();
         phone =in.getStringExtra("phone");
         yanzheng=in.getStringExtra("yanzheng");
    }

    @Override
    public void initClick() {
        iv_photo.setOnClickListener(this);
        btu_zhuce.setOnClickListener(this);
        btu_man.setOnClickListener(this);
        btu_nv.setOnClickListener(this);
        iv_eye.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.iv_tianjiatongxiang:
             AppContext.getInstance().goAlbumAndPhotograph(this, 1, 2000);
             break;
         case R.id.iv_left:
            finish();
             break;
         case R.id.bt_man:
            genber=0;
             btu_man.setBackgroundResource(R.drawable.head_backgoun1);
             btu_man.setTextColor(getResources().getColor(R.color.colorbaise));
             btu_nv.setBackgroundResource(R.drawable.my_shape_3);
             btu_nv.setTextColor(getResources().getColor(R.color.colorAccent));
             break;
         case R.id.bt_nv:
             genber=1;
             btu_nv.setBackgroundResource(R.drawable.head_backgoun1);
             btu_nv.setTextColor(getResources().getColor(R.color.colorbaise));
             btu_man.setBackgroundResource(R.drawable.my_shape_3);
             btu_man.setTextColor(getResources().getColor(R.color.colorAccent));
             break;
         case R.id.yingcangmima:
             if(!issetelt) {
                 iv_eye.setImageResource(R.mipmap.bukejian);
                 et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
             }else{
                 iv_eye.setImageResource(R.mipmap.keijian);
                 et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
             }
             issetelt=!issetelt;
             break;
         case R.id.bt_zhuce:
              if(null==paths||paths.length()<=0){
             Toast.makeText(this,"请上传头像",Toast.LENGTH_SHORT).show();
             break;
         }
             if(null==et_name.getText().toString()||et_name.getText().toString().length()<=0){
                 Toast.makeText(this,"请输入昵称",Toast.LENGTH_SHORT).show();
                 break;
             }
             if(!et_youxiang.getText().toString()
                     .matches("[\\w[.-]]+@[\\w[.-]]+[\\w]+")){
                 Toast.makeText(this,"邮箱格式不正确",Toast.LENGTH_SHORT).show();
                 break;
             }
             if(!et_pwd.getText().toString()
                     .matches("[a-zA-Z0-9]{6,24}")){
                 Toast.makeText(this,"密码格式不正确",Toast.LENGTH_SHORT).show();
                 break;
             }
             if(!cb_fuwu.isChecked()){
                 Toast.makeText(this,"是否同意服务条款",Toast.LENGTH_SHORT).show();
                 break;
             }
         registered();
             break;
     }
    }
    //获取图片的地址
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            if (resultCode == -1) {
                String[] paths = (String[]) data.getExtras().get("paths");
                if (null != paths && paths.length > 0) {
                    Intent intent = new Intent(this, CropImageActivity.class);
                    intent.putExtra(CropImageActivity.EXTRA_IMAGE_PATH, paths[0]);
                    startActivityForResult(intent, 3000);
                }

            }
        } else if (requestCode == 3000) {
            if (resultCode == -1) {
                paths = (String) data.getExtras().get("image_path");
//                ImageLoader.getInstance().displayImage("file://" + paths, img_head, AppContext.image_display_options);
//                获取的图片显示在Imageview
                AppContext.getInstance().displayImage("file://"+paths, iv_photo);
//            modifyUserInfo(event.getValue().toString(), null, -1);
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//                alertDialog.setTitle("系統提示");
//                alertDialog.setMessage("是否立即上传头像");
//                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
////                        updateUserInfo("", paths, "");
//                    }
//                });
//                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        AppContext.getInstance().displayImage(AppContext.getInstance().getUserInfo().getUserHeadImg(), iv_head);
//                    }
//                });
//                alertDialog.create().show();
            }

        }
    }
//    注册
    public void registered(){
        Map<String,Object> map = new HashMap<>();
        map.put("userHeadImg",new File(paths));
        map.put("userNickName",et_name.getText().toString());
        map.put("userPassword",et_pwd.getText().toString());
        map.put("userSex",genber);
        map.put("userPhone",phone);
        map.put("code",yanzheng);
        map.put("userEmail",et_youxiang.getText().toString());
        ServerRequest.requestHttp(this, ServerUrl.register, map, "注册中", new ServerResultListener() {
            @Override
            public void onSuccess(String json, String msg) {
                Toast.makeText(RegisteredTwoActivity.this,msg,Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(RegisteredTwoActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
