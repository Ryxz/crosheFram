package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/21.
 */

public class OrderNumEntity implements Serializable {

    /**
     * dps : 4
     * dpj : 0
     * yqs : 0
     * yps : 0
     */

    private int dps;
    private int dpj;
    private int yqs;
    private int yps;

    public int getDps() {
        return dps;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public int getDpj() {
        return dpj;
    }

    public void setDpj(int dpj) {
        this.dpj = dpj;
    }

    public int getYqs() {
        return yqs;
    }

    public void setYqs(int yqs) {
        this.yqs = yqs;
    }

    public int getYps() {
        return yps;
    }

    public void setYps(int yps) {
        this.yps = yps;
    }
}
