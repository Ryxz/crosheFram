package com.croshe.crosheandroidbase.http;

import android.app.ProgressDialog;
import android.content.Context;

import com.croshe.crosheandroidbase.listener.HttpCallBackImpl;
import com.croshe.crosheandroidbase.listener.ServerResultListenerOther;
import com.croshe.crosheandroidbase.util.L;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 服务器请求工具类
 */

public class ServerRequestUtil {
    private static ProgressDialog progressDialog;

    /**
     * @param context              上下文
     * @param url                  请求地址
     * @param params               请求参数
     * @param log                  加载提示文字
     * @param serverResultListener 返回结果监听
     */
    public static void requestHttp(final Context context, final String url, Map<String, Object> params, String log, final ServerResultListenerOther serverResultListener) {
        if (!StringUtils.isEmpty(log)) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(log);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        if (null != params && params.size() > 0) {
            L.d("TAG", "上传参数:" + params.toString());
            L.d("TAG", "请求路径:" + url);
        }

        OKHttpUtils.getInstance().post(url, params, new HttpCallBackImpl() {
            @Override
            public void onRequestResult(boolean success, String message, Object data, Object other) {
                L.d("TAG", "回调数据:" + success);
                L.d("TAG", "回调数据:" + message + "---" + url);
                L.d("TAG", "回调数据:" + data);
                System.out.print("**************" + data);
                if (null != progressDialog) {
                    progressDialog.dismiss();
                }
                if (success) {
                    serverResultListener.onSuccessOther(data.toString(), other.toString(), message);
                } else {
                    if (null != data) {
                        serverResultListener.onFailure(data.toString(), message);
                    } else {
                        serverResultListener.onFailure("", message);
                    }
                }

            }
        });

    }

}
