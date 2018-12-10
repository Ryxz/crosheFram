package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/24.
 */
//                "setMsgVibrat":1,
//                "setUserId":4,
//                "setMsgNewNotice":0,
//                "setMsgVoice":0,
//                "setWifiVersion":1,
//                "setId":1

public class UserSet implements Serializable {
    String setMsgVibrat;
    String setMsgNewNotice;
    String setMsgVoice;
    String setId;

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getSetMsgNewNotice() {
        return setMsgNewNotice;
    }

    public void setSetMsgNewNotice(String setMsgNewNotice) {
        this.setMsgNewNotice = setMsgNewNotice;
    }

    public String getSetMsgVibrat() {
        return setMsgVibrat;
    }

    public void setSetMsgVibrat(String setMsgVibrat) {
        this.setMsgVibrat = setMsgVibrat;
    }

    public String getSetMsgVoice() {
        return setMsgVoice;
    }

    public void setSetMsgVoice(String setMsgVoice) {
        this.setMsgVoice = setMsgVoice;
    }
}

