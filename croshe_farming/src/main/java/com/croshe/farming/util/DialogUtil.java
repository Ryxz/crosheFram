package com.croshe.farming.util;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.croshe.farming.R;

/**
 * Created by Administrator on 2017/6/28.
 */

public class DialogUtil {
    public static void showYesOrCancelDialog(Context context, LayoutInflater inflater, String title, String content, final OnBtnClickListener yesBtnOnClickListener, final OnBtnClickListener cancelBtnOnClickListener) {
        View view = inflater.inflate(R.layout.layout_alert_dialog, null);
        final Dialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.getWindow().setContentView(view);
        ((TextView) view.findViewById(R.id.tvDialogTitle)).setText(title);
        ((TextView) view.findViewById(R.id.tvDialogContent)).setText(content);
        (view.findViewById(R.id.btnYes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesBtnOnClickListener.onClick(alertDialog);
            }
        });
        (view.findViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelBtnOnClickListener.onClick(alertDialog);
            }
        });
    }
    public static void showYesOrCancelDialog(Context context, LayoutInflater inflater, String content, final OnBtnClickListener yesBtnOnClickListener, final OnBtnClickListener cancelBtnOnClickListener) {
        View view = inflater.inflate(R.layout.layout_alert_dialog, null);
        final Dialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.getWindow().setContentView(view);
        ((TextView) view.findViewById(R.id.tvDialogTitle)).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.tvDialogContent)).setText(content);
        (view.findViewById(R.id.btnYes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesBtnOnClickListener.onClick(alertDialog);
            }
        });
        (view.findViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelBtnOnClickListener.onClick(alertDialog);
            }
        });
    }


    public interface OnBtnClickListener {
        void onClick(Dialog dialog);
    }
}
