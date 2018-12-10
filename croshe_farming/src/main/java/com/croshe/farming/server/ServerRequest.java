package com.croshe.farming.server;

import android.app.ProgressDialog;
import android.content.Context;


import com.croshe.crosheandroidbase.http.OKHttpUtils;
import com.croshe.crosheandroidbase.listener.HttpCallBackImpl;
import com.croshe.crosheandroidbase.storage.Preference;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;




/**
 * Created by Administrator on 2017/5/5.
 */

public class ServerRequest {

    private static ProgressDialog progressDialog;


    /**
     * 网络请求Post
     */
    public static void requestHttp(final Context context, final String url, Map<String, Object> params, String log, final ServerResultListener serverResultListener) {
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
        OKHttpUtils.addFinalParams("cityName", Preference.getStringPreferences(context, "city", "北京"));
        OKHttpUtils.addFinalParams("areaName", Preference.getStringPreferences(context, "district", "北京"));
        OKHttpUtils.addFinalParams("latitude", Preference.getStringPreferences(context, "lat", "43.833513"));
        OKHttpUtils.addFinalParams("longitude", Preference.getStringPreferences(context, "lng", "125.288319"));
//        OKHttpUtils.addFinalParams("userId", AppContext.getInstance().getUserInfo().getUserId());
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
                    serverResultListener.onSuccess(data.toString(), message);
                } else {
                    serverResultListener.onFailure(message);

                }

            }
        });

    }

}
