package com.example.ux21a.slipgestrues.manualLine;

import java.util.ArrayList;

/**
 * Created by kuniatus on 2016/05/03.
 */
public class SampleLine {
    private String moji;
    public ArrayList<Float> slist = new ArrayList<Float>();

    public SampleLine(ArrayList<Float> slist) {
        this.slist = slist;
    }

    public String getMoji() {
        return moji;
    }

    public void setMoji(String moji) {
        this.moji = moji;
    }
}
