package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/10.
 */

public class FormActionEntity implements Serializable {

    /**
     * shifei : 0
     * jialiao : 0
     * yongyao : 0
     */

    private int shifei;
    private int jialiao;
    private int yongyao;

    public int getShifei() {
        return shifei;
    }

    public void setShifei(int shifei) {
        this.shifei = shifei;
    }

    public int getJialiao() {
        return jialiao;
    }

    public void setJialiao(int jialiao) {
        this.jialiao = jialiao;
    }

    public int getYongyao() {
        return yongyao;
    }

    public void setYongyao(int yongyao) {
        this.yongyao = yongyao;
    }
}
