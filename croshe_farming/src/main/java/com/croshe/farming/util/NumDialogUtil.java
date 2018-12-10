package com.croshe.farming.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.croshe.farming.R;

/**
 * Created by Administrator on 2017/7/17.
 */

//去种植养殖
public class NumDialogUtil extends Dialog {
    private TextView tv_dialog_num,tv_dialog_area;
    EditText edit_enter_num;
    private Button btn_Cancel,btn_Yes;

    public NumDialogUtil(@NonNull Context context, int num, String area, View.OnClickListener yesBtnOnClickListener, View.OnClickListener cancelBtnOnClickListener) {
        super(context,R.style.MyDialog);
        setContentView(R.layout.layout_alert_num_dialog);
        tv_dialog_num = (TextView) findViewById(R.id.tv_dialog_num);
        tv_dialog_area = (TextView) findViewById(R.id.tv_dialog_area);
        edit_enter_num = (EditText) findViewById(R.id.edit_enter_num);
        btn_Cancel = (Button) findViewById(R.id.btn_Cancel);
        btn_Yes = (Button) findViewById(R.id.btn_Yes);
        tv_dialog_num.setText(num+"");
        tv_dialog_area.setText(area);
        btn_Yes.setOnClickListener(yesBtnOnClickListener);
        btn_Cancel.setOnClickListener(cancelBtnOnClickListener);
    }

    public String getNums(){
        return edit_enter_num.getText().toString();
    }
}
