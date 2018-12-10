package com.croshe.crosheandroidbase.listener;

import com.croshe.crosheandroidbase.http.OKHttpUtils;

import org.json.JSONObject;


/**
 * 根据本公司指定的返回规则
 */
public abstract class HttpCallBackImpl implements OKHttpUtils.HttpCallBack {

    @Override
    public final void onResult(boolean success, String response) {
        try {
            if (success) {
                JSONObject jsonObject = new JSONObject(response);
                Object data;
                Object other;
                if (jsonObject.isNull("data")) {
                    data = "";
                } else {
                    data = jsonObject.get("data");
                }

                if (jsonObject.isNull("other")) {
                    other = "";
                } else {
                    other = jsonObject.get("other");
                }
                if (jsonObject.getBoolean("success")) {
                    onRequestResult(true, jsonObject.getString("message"), data, other);
                } else {
                    onRequestResult(false, jsonObject.getString("message"), data, other);
                }
            } else {
                onRequestResult(false, response, null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void onRequestResult(boolean success, String message, Object data, Object other);
}
