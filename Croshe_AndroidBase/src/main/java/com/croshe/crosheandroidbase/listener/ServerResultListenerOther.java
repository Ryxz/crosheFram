package com.croshe.crosheandroidbase.listener;

/**
 * 服务器返回结果监听
 */

public abstract class ServerResultListenerOther {

    /**
     * 请求成功
     *
     * @param json
     */
    public abstract void onSuccessOther(String json, String other, String msg);

    /**
     * 请求失败
     */
    public abstract void onFailure(String json, String msg);

}

